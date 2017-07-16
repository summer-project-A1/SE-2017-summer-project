<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@include file="header.jsp"%>
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
<div class="products">

    <div class="container">
        <h2>订单确认</h2>
        <s:iterator value="#orderProfileList" status="st">
        <div id="<s:property value="bookID"/>" class="cart-header">
            <div class="close-icon" onclick="deleteBook(<s:property value="bookID"/>)"> </div>
            <div class="cart-sec simpleCart_shelfItem">
                <div class="cart-item cyc">
                    <img src="<%=path%>/imageAction/showImage?imageID=<s:property value="iamgeID"/>" class="img-responsive" alt="">
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
    <form id="orderInfo" style="display:none">
    	<s:iterator value="#orderProfileList" status="st">
        	<input name="orderID" type="hidden" value="<s:property value="orderID"/>">
        </s:iterator>
    </form>
    <div id=confirm>
        <label>合计:</label>
        <label id="totalCredit"><s:property value="#totalCredit"/></label><br>
        <!--<label id=totalCredit><s:property value="totalPrice"/></label><br>-->
        <a id="commit" href="#" class="add-cart item_add">确认订单并付款</a>
    </div>
    </div>
</div>
<script type="text/javascript">
    $("commit").click(function(){
        $("#orderInfo").submit();
    })
</script>
</body>
</html>