<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@include file="header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>checkout</title>
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
    font-size: large;
}
#totalCredit
{
	font-size:large;
	color:#080808;
	margin-bottom: 1%;
}

.address{
    margin-bottom:5%;
}

.address h3 {
    font-size: large;
}


</style>

</head>
<body>

<script>
    function setDefaultAddr(addrID){
        var oldDefaultAddrLabelID = "defaultAddrLabel";
        var newDefaultAddrLabelID = "newDefaultAddrLabel"+addrID;
        var newDefaultAddrLinkID = "setDefaultAddr"+addrID;
        $.ajax({
           url:'<%=path%>/',
           type:'POST',
           data:{'addrID':addrID},
           success:function(msg){
               showTip('更换默认地址成功','success');
               $("#"+oldDefaultAddrLabelID).html("");
               $("#defaultAddrLink").html("设为默认");
               $("#"+newDefaultAddrLabelID).html("默认地址");
               $("#"+newDefaultAddrLinkID).html("");

           },
            error:function(xhr,status,error){
                alert('status='+status+',error='+error);
            }
        });
    }

    function showAddrForm(){
        $("#newAddrForm").show();
    }

    function addNewAddr(){
        var newAddr = $("#newAddr").val();
        if(newAddr.length == 0){
            showTip('请输入新地址','danger');
        }else{
            $.ajax({
                url:'<%=path%>/',
                type:'POST',
                data:{'newAddress':newAddr},
                success:function(msg){
                    showTip('添加新地址成功','success');
                    $("#newAddrForm").remove();
                    var newAddress = msg.newAddr;
                    var newAddressID = msg.newAddrID;
                    var htmlstr1 = '<input type="ratio" id="address'+newAddressID+'" name="address" value="'+newAddress+'"/>';
                    var htmlstr2 = '<label for="address'+newAddressID+'">'+newAddress+'</label><label id="newDefaultAddrLabel'+newAddressID+'"></label><a href="#" id="setDefaultAddr'+newAddressID+'" onclick="setDefaultAddr('+newAddressID+')">设为默认地址</a>';
                    $("#addrForm").append(htmlstr1);
                    $("#addrForm").append(htmlstr2);
                },
                error:function(xhr,status,error){
                    alert('status='+status+',error='+error);
                }
            });
        }
    }

    function gotoPay(){
        $("#addrForm").submit();
    }
</script>

<div id="tip"></div>
<div class="cart-items">
    <div class="container">
		<s:if test="#action=='borrowCheckout'">
        	<h3 align="center">确认借阅订单信息</h3><br>
        </s:if>
        <s:else>
        	<h3 align="center">确认购买订单信息</h3><br>
        </s:else>
	<s:iterator value="#booksInOrder" status="st">
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
                    </ul>
                    <div class="delivery">
                    	<s:if test="#action=='borrowCheckout'">
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

        <div id="showAddress" class="address">
            <form id="addrForm" action="" method="post">
                <h3>管理收货地址</h3>
                <input type="hidden" name="action" value="<s:property value="#action"/>" />

                <input type="radio" id="defaultAddr" name="address" value="<s:property value="#defaultAddr"/>"/>
                <label for="defaultAddr"><s:property value="#defaultAddr"/></label><label id="defaultAddrLabel">默认地址</label><a href="#" id="defaultAddrLink"></a><br>

                <s:iterator value="#addrList" status="st">
                    <input type="radio" id="address<s:property value="addrID"/>" name="address" value="<s:property value="address"/>"/>
                    <label for="address<s:property value="addrID"/>"><s:property value="address"/></label><label id="newDefaultAddrLabel<s:property value="addrID"/>"></label><a href="#" id="setDefaultAddr<s:property value="addrID"/>" onclick="setDefaultAddr(<s:property value="addrID"/>)">设为默认地址</a>
                </s:iterator>
            </form>
            <a href="#" class="add-cart item_add" onclick="showAddrForm()">添加新地址</a>
            <form id="newAddrForm" style="display: none">
                <input type="text" id="newAddr" name="newAddress"/>
                <a href="#" class="add-cart item_add" onclick="addNewAddr()">添加</a>
            </form>
        </div>




        <!-- 地址信息静态展示 -->
        <div id="address" class="address">
            <form id="addrForm1" action="" method="post">
                <h3>管理收货地址</h3>

                <input type="radio" id="address1" name="address" value="address1" />
                <label for="address1">address1 默认地址</label> <br>
                <input type="radio" id="address2" name="address" value="address2"/>
                <label for="address2">address2</label> <a href="#" onclick="setDefaultAddr()">设为默认</a><br>
            </form>
            <a href="#" class="add-cart item_add" onclick="showAddrForm()">添加新地址</a>
            <form id="newAddrForm1" style="display: none" action="" method="post">
                <input type="text" id="newAddr1" name="newAddress"/>
                <a href="#" class="add-cart item_add" onclick="">添加</a>
            </form>
        </div>


        <div id=confirm>
    	    <label >合计:</label>
    	    <label id=totalCredit>240</label><br>
        </div>
        <button type="button" id=commit class="checkout-but" onclick="gotoPay()">前去支付</button>

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