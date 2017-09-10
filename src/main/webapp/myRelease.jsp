<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="global.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>myExchange</title>
</head>
<body>
<s:action name="header" executeResult="true" namespace="/"/><!-- home page -->

<div class="products">
    <div class="container">
        <div class="col-md-3 rsiderbar span_1_of_left">
            <section class="sky-form">
                <div class="product_right">
                    <h3 class="m_2"><span class="glyphicon glyphicon-minus" aria-hidden="true"></span>操作选单</h3>

                    <div class="tab1">
                        <ul class="place">
                            <li class="sort"><a href="<%=path%>/userAction/showUserProfile">个人信息</a></li>
                        </ul>
                        <div class="clearfix"> </div>
                    </div>
                    <div class="tab1">
                        <ul class="place">
                            <li class="sort"><a href="<%=path%>/userAction/showSellerCenter">卖家中心</a></li>
                        </ul>
                        <div class="clearfix"> </div>
                    </div>
                    <div class="tab1">
                        <ul class="place">
                            <li class="sort"><a href="<%=path%>/bookAction/showUserReleasedBooks">我的发布</a></li>
                        </ul>
                        <div class="clearfix"> </div>
                    </div>
                    <div class="tab1">
                        <ul class="place">
                            <li class="sort"><a href="<%=path%>/borrowAction/showMyBorrow">我的借阅</a></li>
                        </ul>
                        <div class="clearfix"> </div>
                    </div>
                    <div class="tab1">
                        <ul class="place">
                            <li class="sort"><a href="<%=path%>/exchangeAction/showMyExchange">我的交换</a></li>
                        </ul>
                        <div class="clearfix"> </div>
                    </div>
                    <div class="tab1">
                        <ul class="place">
                            <li class="sort"><a href="<%=path%>/orderAction/showMyOrder">我的购买</a></li>
                        </ul>
                        <div class="clearfix"> </div>
                    </div>
                    <div class="tab1">
                        <ul class="place">
                            <li class="sort"><a href="<%=path%>/reserveAction/showMyReservation">我的预约</a></li>
                        </ul>
                        <div class="clearfix"> </div>
                    </div>
                    <div class="clearfix"> </div>
                </div>
            </section>
        </div>

        <h3 align="center">我的发布</h3>
        <div id="tip"></div>
        <div id="cartinfo" class="cart-item">
            <div class="container">

                <!-- 我的发布，按时间倒序排列 -->
                    <s:iterator value="#allBooks" status="st">
                        <div id="<s:property value="bookID"/>" class="cart-header">
                            <div class="cart-sec simpleCart_shelfItem">
                                <div class="cart-item cyc">
                                    <img src="<%=path%>/imageAction/showImage?imageID=<s:property value="imageID"/>" class="img-responsive" alt="">
                                </div>
                                <div class="cart-item-info">
                                    <h4>
                                        <a href="<%=path%>/bookAction/showBookProfile?bookID=<s:property value="bookID"/>">
                                            书名：<s:property value="bookName"/></a>
                                    </h4><br>
                                    <ul class="qty">
                                        <li><p>ISBN：<s:property value="isbn"/></p></li>
                                        <li><p>作者：<s:property value="author"/></p></li>
                                        <li><p>分类：<s:property value="category1"/>&nbsp;&nbsp;<s:property value="category2"/></p></li>

                                    </ul>
                                    <div class="delivery">
                                        <s:if test="canBorrow==1">
                                            <p>借阅积分：<s:property value="borrowCredit"/></p><br>
                                        </s:if>
                                        <s:if test="canExchange==1">
                                            <p>购买积分：<s:property value="buyCredit"/></p><br>
                                        </s:if>
                                        <s:if test="bookStatus=='EXCHANGED'">
                                            <p>当前状态：已被交换或购买</p>
                                        </s:if>
                                        <s:elseif test="bookStatus=='BORROWED'">
                                            <p>当前状态：正被借阅</p>
                                        </s:elseif>
                                        <s:else>
                                            <p>当前状态：空闲</p>
                                        </s:else>
                                        <s:if test="canBorrow==1||canExchange==1">
                                            <a href="<%=path%>/bookAction/" class="add-cart item_add">下架</a>
                                        </s:if>
                                        <s:elseif test="canBorrow==0&&canExchange==0">
                                            <a href="<%=path%>/bookAction/" class="add-cart item_add">修改图书信息并重新上架</a>
                                        </s:elseif>
                                    </div>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                        </div><hr>
                    </s:iterator>

            </div>
        </div>
    </div>
</div>

</body>
    <jsp:include page="footer.jsp"/>
</html>

