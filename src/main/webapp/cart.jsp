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
        <h2>我的购物车</h2>
        <script>
            function showTip(tip,type){
                var $tip = $('#tip');
                $tip.attr('class', 'alert alert-' + type).text(tip).css('margin-left', - $tip.outerWidth() / 2).fadeIn(500).delay(1000).fadeOut(500);
            }

            function deleteBook(bookID) {
                console.log("delete current book, bookid: "+bookID);
                $.ajax({
                    url: base_url+ 'orderAction/removeFromCart',
                    type:'POST',
                    data: {
                        'bookID' : bookID
                    },
                    success: function(msg){
                        if (msg.success) {
                            $("div").remove("#"+bookID);
                            showTip('Removed from your cart!', 'success');
                        }
                        else {
                            showTip('some thing go wrong', 'danger');
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
        <s:if test="#booksInCart==null">
            <h3>购物车为空</h3>
        </s:if>
        <s:iterator value="#booksInCart" status="st">


            <div id="<s:property value="bookID"/>" class="cart-header">
                <div class="close-icon" onclick="deleteBook(<s:property value="bookID"/>)"> </div>
                <div class="cart-sec simpleCart_shelfItem">
                    <div class="cart-item cyc">
                        <img src="<%=path%>/images/m1.png" class="img-responsive" alt="">
                    </div>
                    <div class="cart-item-info">
                        <h3><a href="#">书名：<s:property value="bookName"/> </a><span>ISBN: <s:property value="ISBN"/> </span></h3>
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
    </div>
</div>
<!--//checkout-->

<jsp:include page="footer.jsp"/>
</body>
</html>

