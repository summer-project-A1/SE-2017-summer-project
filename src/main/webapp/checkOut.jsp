<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@include file="../header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
#wapper
{
	text-align:center;
	width:80%;
}
#header
{
	font-size:x-large;
}
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
<div id="wapper">
	<div id=header>
		<label>确认</label>
		<s:if test="#action==borrowCheckOut">
        	<label id=type>借阅</label>
        </s:if>
        <s:else>
        	<label id=type>购买</label>
        </s:else>
        <label>订单</label>
	</div>
	<s:iterator value="#booksInOrder" status="st">
    	<div id="<s:property value="bookID"/>" class="cart-header">
        	<div class="close-icon" onclick="deleteBook(<s:property value="bookID"/>)"> </div>
            <div class="cart-sec simpleCart_shelfItem">
            	<div class="cart-item cyc">
                	<img src="<%=path%>/images/m1.png" class="img-responsive" alt="">
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
                    	<s:if test="#action==borrowCheckOut">
                    		<p>借阅所需积分：<s:property value="borrowCredit"/></p>
                    	</s:if>
                    	<s:else>
                        	<p>购买所需积分：<s:property value="buyCredit"/></p>
                        </s:else>
                        <div class="clearfix"></div>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
    </s:iterator>
    <form id=orderInfo style="display:none">
    	<input name=orderID type=hidden value=<s:property value="#orderID"/> >
    </form>
    <div id=confirm>
    	<label>合计:</label>
    	<label id=totalCredit><s:property value="totalCredit"/></label><br>
    	<button id=commit class="btn btn-primary"></button>
    </div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	var type=$("#type").html();
	if(type.indexOf("购买")>0)
		$("#commit").text("确认购买");
	else
		$("#commit").text("确认借阅");
});

$("commit").click(function(){
	$("#orderInfo").submit();
})
</script>
</body>
</html>