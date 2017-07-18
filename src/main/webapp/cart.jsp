<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="global.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>Cart</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="" />


</head>
<body>
<!-- header -->
<s:action name="header" executeResult="true" namespace="/"/><!-- home page -->

<!--cart-items-->
<div class="cart-items">
    <div class="container">
        <div id="tip"> </div>
        <script>
            function deleteBook(bookID) {
                console.log("delete current book, bookid: "+bookID);
                var method = '<s:property value="#buyOrBorrow"/>';
                var cmp = method;
                var url = "";
                if(cmp=="borrow"){
                    url = '/cartAction/removeFromBorrowCart';
                }
                if(cmp=="buy"){
                    url = '/cartAction/removeFromBuyCart';
                }
                $.ajax({
                    url: '<%=path%>'+ url,
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
</script>



        <s:if test="(#session.buyCart==null&&#session.borrowCart==null)||#booksInCart==null">
            <h3>购物车为空</h3>
            <h3><a href="<%=path%>/bookAction/showAllBooks">前去浏览图书</a></h3>
        </s:if>
        <div id="tip"></div>
        <s:else>
            <s:if test="#buyOrBorrow=='borrow'">
                <h3>借阅购物车</h3><br>
            </s:if>
            <s:elseif test="#buyOrBorrow=='buy'">
                <h3>购买购物车</h3><br>
            </s:elseif>
        <s:iterator value="#booksInCart" status="st">
            <div id="<s:property value="bookID"/>" class="cart-header">
                <div class="close-icon" onclick="deleteBook(<s:property value="bookID"/>)"></div>
                <div class="cart-sec simpleCart_shelfItem">
                    <div class="cart-item cyc">
                        <img src="<%=path%>/imageAction/showImage?imageID=<s:property value="imageID"/>" class="img-responsive" alt="">
                    </div>
                    <div class="cart-item-info">
                        <h3>
                            <a href="<%=path%>/bookAction/showBookProfile?bookID=<s:property value="bookID"/>">
                                书名：<s:property value="bookName"/></a><br>
                            <span>ISBN: <s:property value="isbn"/> </span>
                        </h3>
                        <ul class="qty">
                            <li><p>作者：<s:property value="author"/> </p></li>
                            <li><p>分类：<s:property value="category1"/> </p></li>
                            <li><p>标签：<s:property value="category2"/> </p></li>
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
                <button class="checkout-but" onclick="window.location.href='<%=path%>/borrowAction/borrowCheckout'">提交订单</button>
            </s:if>
            <s:elseif test="#buyOrBorrow=='buy'">
                <button class="checkout-but" onclick="window.location.href='<%=path%>/orderAction/buyCheckout'">提交订单</button>
            </s:elseif>
        </s:else>
    </div>
</div>
<!--//checkout-->

<jsp:include page="footer.jsp"/>
</body>
</html>

