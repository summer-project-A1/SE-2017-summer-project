<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@include file="global.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Pay</title>
    <style>
        #confirm
        {
            text-align:right;
        }
        #totalCredit
        {
            font-size:x-large;
            color:#FF0000;
            margin-bottom: 10px;
        }
    </style>

</head>
<body>
<!-- header -->
<s:action name="header" executeResult="true" namespace="/"/><!-- home page -->
<div class="products">

    <div class="container">
        <h2>订单确认</h2>
        <s:if test="#buyOrBorrow=='buy'">
        <s:iterator value="#orderProfileList" status="st">
        <div id="<s:property value="orderID"/>" class="cart-header">
            <div class="close-icon" onclick="deleteBook(<s:property value="bookID"/>)"> </div>
            <div class="cart-sec simpleCart_shelfItem">
                <div class="cart-item cyc">
                    <img src="<%=path%>/imageAction/showImage?imageID=<s:property value="imageID"/>" class="img-responsive" alt="">
                </div>
                <div class="cart-item-info">
                    <h3>
                        <a href="<%=path%>/bookAction/showBookProfile?bookID=<s:property value="bookID"/>">书名：<s:property value="bookName"/></a><br>
                        <span>ISBN: <s:property value="isbn"/> </span>
                    </h3>
                    <ul class="qty">
                        <li><p>作者：<s:property value="author"/> </p></li>
                        <li><p>分类：<s:property value="category1"/></p></li>
                        <li><p>分类：<s:property value="category2"/></p></li>
                    </ul>
                    <div class="delivery">
                            <p>所需积分：<s:property value="buyCredit"/></p>
                        <div class="clearfix"></div>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
    </s:iterator>
    <form id="orderInfo" style="display:none" action="<%=path%>/orderAction/confirmBuyOrder" method="post">
    	<s:iterator value="#orderProfileList" status="st">
        	<input name="orderIDList" type="hidden" value="<s:property value="orderID"/>">
        </s:iterator>
    </form>
            <div id="showAddress" class="address">
                <h3>收货地址</h3>
                <label id="orderAddrLabel"><s:property value="address"/></label><br>
            </div>
    <div id="confirm">
        <label>合计:</label>
        <label id="totalCredit1"><s:property value="#totalCredit"/></label><br>
        <a id="commit1" href="#" class="add-cart item_add">确认订单并付款</a>
    </div>
        </s:if>
        <s:elseif test="#buyOrBorrow=='borrow'">
            <s:iterator value="#borrowProfileList" status="st">
                <div id="<s:property value="bookID"/>" class="cart-header">
                    <div class="close-icon" onclick="deleteBook(<s:property value="bookID"/>)"> </div>
                    <div class="cart-sec simpleCart_shelfItem">
                        <div class="cart-item cyc">
                            <img src="<%=path%>/imageAction/showImage?imageID=<s:property value="imageID"/>" class="img-responsive" alt="">
                        </div>
                        <div class="cart-item-info">
                            <h3>
                                <a href="<%=path%>/bookAction/showBookProfile?bookID=<s:property value="bookID"/>">书名：<s:property value="bookName"/></a><br>
                                <span>ISBN: <s:property value="isbn"/> </span>
                            </h3>
                            <ul class="qty">
                                <li><p>作者：<s:property value="author"/> </p></li>
                                <li><p>分类：<s:property value="category1"/></p></li>
                                <li><p>分类：<s:property value="category2"/></p></li>
                            </ul>
                            <div class="delivery">
                                <p>所需积分：<s:property value="borrowCredit"/></p>
                                <div class="clearfix"></div>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </div>
            </s:iterator>
            <form id="borrowInfo" style="display:none" action="<%=path%>/borrowAction/confirmBuyOrder" method="post">
                <s:iterator value="#borrowProfileList" status="st">
                    <input name="borrowIDList" type="hidden" value="<s:property value="borrowID"/>">
                </s:iterator>
            </form>
            <div id="showAddress" class="address">
                <h3>收货地址</h3>
                <label id="borrowAddrLabel"><s:property value="address"/></label><br>
            </div>
            <div id="confirm">
                <label>合计:</label>
                <label id="totalCredit2"><s:property value="#totalCredit"/></label><br>
                <a id="commit2" href="#" class="add-cart item_add">确认订单并付款</a>
            </div>
        </s:elseif>
    </div>
</div>
<script type="text/javascript">
    $("#commit1").click(function(){
        $("#orderInfo").submit();
    });

    $("#commit2").click(function(){
        $("#borrowInfo").submit();
    })
</script>
</body>
</html>