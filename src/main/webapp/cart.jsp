<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>Cart</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="" />


</head>
<body>


<!--cart-items-->
<div class="cart-items">
    <div class="container">
        <div id="tip"> </div>
        <h2>我的购物车</h2>
        <script>
            function deleteBook(bookID) {
                console.log("delete current book, bookid: "+bookID);
                var method = '<s:property value="#buyOrBorrow"/>';
                var cmp = method;
                var url = "";
                if(cmp=="borrow"){
                    url = 'cartAction/removeFromBorrowCart';
                }
                if(cmp=="buy"){
                    url = 'cartAction/removeFromBuyCart';
                }
                $.ajax({
                    url: base_url+ url,
                    type:'POST',
                    data: {
                        'bookID' : bookID
                    },
                    success: function(msg){
                        if (msg.success) {
                            $("div").remove("#"+bookID);
                            showTip('从购物车删除!', 'success');
                        }
                        else {
                            showTip('无法删除，请刷新页面！', 'danger');
                        }
                    },
                    error:function(xhr,status,error){
                        alert('status='+status+',error='+error);
                    }

                });

            }
            /*
            function createOrder(){
                $.ajax({
                   url: base_url+ 'orderAction/createBuyOrder',
                   type: 'POST',
                   data:{},
                   success: function(msg){
                        if(msg.result == true){
                            showTip('结算成功','success');
                            window.setTimeout("window.location='<%=path%>/orderAction/check'",2000);
                        }else{
                            if(msg.book == true && msg.credit == true){
                                var info = '积分余额不足，图书已被借阅或交换';
                                showTip(info,'danger');
                            }
                            if(msg.credit == true){
                                var creditNotEnough = '积分余额不足';
                                showTip(creditNotEnough,'danger');
                            }
                            if(msg.book == true){
                                var bookWasSold = '图书已被借阅或交换';
                                showTip(bookWasSold,'danger');
                            }
                        }
                   },
                   error:function(xhr,status,error){
                       alert('status='+status+',error='+error);
                   }
                });
            }
            /*
             $('.close-button').on('click', function(c){
             console.log("delete current book.");
             $('.cart-header').fadeOut('slow', function(c){
             $('.cart-header').hide();
             });
             });
             */
        </script>



        <s:if test="(#session.buyCart==null&&#session.borrowCart==null)||#booksInCart==null">
            <h3>购物车为空</h3>
            <h3><a href="<%=path%>/bookAction/showAllBooks">前去浏览图书</a></h3>
        </s:if>
        <div id="tip"></div>
        <s:else>
            <s:if test="#buyOrBorrow=='borrow'">
                <h3>借阅清单</h3><br>
            </s:if>
            <s:elseif test="#buyOrBorrow=='buy'">
                <h3>购买清单</h3><br>
            </s:elseif>
        <s:iterator value="#booksInCart" status="st">
            <div id="<s:property value="bookID"/>" class="cart-header">
                <div class="close-icon" onclick="deleteBook(<s:property value="bookID"/>)"></div>
                <div class="cart-sec simpleCart_shelfItem">
                    <div class="cart-item cyc">
                        <img src="<%=path%>/images/m1.png" class="img-responsive" alt="">
                    </div>
                    <div class="cart-item-info">
                        <h3>
                            <a href="<%=path%>/bookAction/showBookProfile?bookID=<s:property value="bookID"/>">
                                书名：<s:property value="bookName"/></a><br>
                            <span>ISBN: <s:property value="isbn"/> </span>
                        </h3>
                        <ul class="qty">
                            <li><p>作者：<s:property value="author"/> </p></li>
                            <li><p>分类：<s:property value="category"/> </p></li>
                        </ul>
                        <div class="delivery">
                            <p>是否可交换：<s:property value="canExchange"/> </p>
                            <span>是否可借阅：<s:property value="canBorrow"/> </span>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
        </s:iterator>
            <s:if test="#buyOrBorrow=='borrow'">
                <button class="checkout-but" onclick="window.location.href='<%=path%>/orderAction/'">提交订单</button>
            </s:if>
            <s:elseif test="#buyOrBorrow=='buy'">
                <button class="checkout-but" onclick="window.location.href='<%=path%>/orderAction/'">提交订单</button>
            </s:elseif>
        </s:else>
    </div>
</div>
<!--//checkout-->

<jsp:include page="footer.jsp"/>
</body>
</html>

