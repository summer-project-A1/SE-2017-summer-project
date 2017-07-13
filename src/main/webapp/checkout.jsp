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
    width: 50%;
}

.address h3 {
    font-size: large;
}
#newAddrForm {
    width: 50%;
}
@media ( min-width :768px) {

    .form-control-noNewline {
        width: 100px;
        display: inline;
    }

    .form-horizontal .form-group-auto {
        margin-right: 0px;
        margin-left: 0px;
    }

}


</style>

</head>
<body>
<script type="text/javascript" src="<%=basePath%>js/jquery.cityselect.js"></script>

<script>
    $(function() {
        $("#city_4").citySelect({
            nodata: "none",
            required:false
        });
    });

    function setDefaultAddr(addrID){
        $.ajax({
           url:'<%=path%>/userAction/setDefaultAddress',
           type:'POST',
           data:{'addrID':addrID},
           success:function(msg){
               var oldDefaultAddr = msg.oldDefaultAddr;
               var oldDefaultAddrID = msg.oldDefaultAddrID;
               var newDefaultAddr = msg.newDefaultAddr;
               var newDefaultAddrID = msg.newDefaultAddrID;

               var toDeleteAddr = "address"+newDefaultAddrID;
               var toDeleteLabel = "addrLabel"+newDefaultAddrID;
               var toDeleteLink = "setDefaultAddr"+newDefaultAddrID;

               var toAddAddr = "address"+oldDefaultAddrID;
               var toAddLabel = "addrLabel"+oldDefaultAddrID;
               var toAddLink = "setDefaultAddr"+oldDefaultAddrID;

               if(oldDefaultAddr != null && oldDefaultAddr != null){
                   var htmlstr1 = '<div id="'+oldDefaultAddrID+'"><input type="ratio" id="address'+oldDefaultAddrID+'" name="address" value="'+oldDefaultAddr+'"/>'
                   +'<label id="addrLabel'+oldDefaultAddrID+'" for="address'+oldDefaultAddrID+'">'+oldDefaultAddr+'</label><a href="#" id="setDefaultAddr'+oldDefaultAddrID+'" onclick="setDefaultAddr(\''+oldDefaultAddrID+'\')">设为默认地址</a>' +
                       '<a href="#" id="deleteAddr'+oldDefaultAddrID+'" onclick="deleteAddress(\''+oldDefaultAddrID+'\')">删除地址</a></div>';
                   showTip('更换默认地址成功','success');
                   $("#defaultAddr").html(newDefaultAddr);
                   $("#"+newDefaultAddrID).remove();
                   $("#addrForm").append(htmlstr1);
               }else{
                   showTip('更换默认地址成功','success');
                   var htmlstr3 = '<div id="'+newDefaultAddrID+'">'+'<input type="radio" id="defaultAddr" name="address" value="'+newDefaultAddr+'"/><label for="defaultAddr">'+newDefaultAddr+'</label><label id="defaultAddrLabel">默认地址</label></div>';
                   $("#"+newDefaultAddrID).remove();
                   $("#addrForm").append(htmlstr3);
               }

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
            var params = $("#newAddrForm").serialize();
            $.ajax({
                url:'<%=path%>/userAction/addAddress',
                type:'POST',
                data:params,
                success:function(msg){
                    showTip('添加新地址成功','success');
                    $("#newAddrForm").remove();
                    var newAddress = msg.newAddress;
                    var newAddressID = msg.newAddressID;
                    var htmlstr1 = '<div id="'+newAddressID+'"><input type="radio" id="address'+newAddressID+'" name="address" value="'+newAddress+'"/>'
                    +'<label id="addrLabel'+newAddressID+'" for="address'+newAddressID+'">'+newAddress+'</label><a href="#" id="setDefaultAddr'+newAddressID+'" onclick="setDefaultAddr(\''+newAddressID+'\')">设为默认地址&nbsp;&nbsp;&nbsp;&nbsp;</a>' +
                        '<a href="#" id="deleteAddr'+newAddressID+'" onclick="deleteAddress(\''+newAddressID+'\')">删除地址</a></div>';
                    $("#addrForm").append(htmlstr1);
                },
                error:function(xhr,status,error){
                    alert('status='+status+',error='+error);
                }
            });
        }
    }

    function deleteAddress(addrID){
        var addressID = "address"+addrID;
        var addrLabelID = "addrLabel"+addrID;
        var setDefaultID = "setDefaultAddr"+addrID;
        var deleteAddrID = "deleteAddr"+addrID;
        var divID = addrID;
        $.ajax({
            url:'<%=path%>/userAction/deleteAddress',
            type:'POST',
            data:{'addrID':addrID},
            success:function(msg){
                showTip('删除地址成功','success');
                /*
                $("#"+addressID).remove();
                $("#"+addrLabelID).remove();
                $("#"+setDefaultID).remove();
                $("#"+deleteAddrID).remove();*/
                $("#"+divID).remove();

            },
            error:function(xhr,status,error){
                alert('status='+status+',error='+error);
            }
        });
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

                <s:iterator value="#defaultAddrList" status="st">
                    <div id="<s:property value="fullAddressID"/>">
                <input type="radio" id="defaultAddr" name="address" value="<s:property value="fullAddressString"/>"/>
                <label for="defaultAddr"><s:property value="fullAddressString"/></label><label id="defaultAddrLabel">默认地址</label><br>
                    </div>
                </s:iterator>

                <s:iterator value="#addrList" status="st">
                    <div id="<s:property value="fullAddressID"/>">
                    <input type="radio" id="address<s:property value="fullAddressID"/>" name="address" value="<s:property value="fullAddressString"/>"/>
                    <label id="addrLabel<s:property value="fullAddressID"/>" for="address<s:property value="fullAddressID"/>"><s:property value="fullAddressString"/></label><a href="#" id="setDefaultAddr<s:property value="fullAddressID"/>" onclick="setDefaultAddr('<s:property value="fullAddressID"/>')">设为默认地址&nbsp;&nbsp;&nbsp;&nbsp;</a>
                    <a href="#" id="deleteAddr<s:property value="fullAddressID"/>" onclick="deleteAddress('<s:property value="fullAddressID"/>')">删除地址</a>
                    </div>
                </s:iterator>
            </form>
            <a href="#" class="add-cart item_add" onclick="showAddrForm()">添加新地址</a>
            <form id="newAddrForm" style="display: none">
                <div class="form-group form-group-auto"  id="city_4">
                    <select class="prov form-control form-control-noNewline" id="province" name="province" style="width:auto"></select>
                    <select class="city form-control form-control-noNewline"  disabled="disabled" id="city" name="city"  style="width:auto"></select>
                    <select class="dist form-control form-control-noNewline" disabled="disabled" id="district" name="district" style="width: auto"></select>
                </div>
                <div class="input">
                    <label>详细地址</label><font color="#FF0000">*</font>&nbsp;
                    <input type="text" id="newAddr" class="form-control" name="address" >
                </div>
                <a href="#" class="add-cart item_add" onclick="addNewAddr()">添加</a>
            </form>
        </div>




        <!-- 地址信息静态展示
        <div id="address" class="address">
            <form id="addrForm1" action="" method="post">
                <h3>管理收货地址</h3>

                <input type="radio" id="address1" name="address" value="address1" />
                <label for="address1">address1 默认地址</label> <br>
                <input type="radio" id="address2" name="address" value="address2"/>
                <label for="address2">address2</label> <a href="#" onclick="setDefaultAddr()">设为默认&nbsp;&nbsp;&nbsp;&nbsp;</a><a href="#" onclick="deleteAddr()">删除地址</a><br>
            </form>
            <a href="#" class="add-cart item_add" onclick="showAddrForm()">添加新地址</a>
            <form id="newAddrForm1" style="display: none" action="" method="post">
                <div class="form-group form-group-auto"  id="city_4">
                    <select class="prov form-control form-control-noNewline" id="province" name="province" style="width:auto"></select>
                    <select class="city form-control form-control-noNewline"  disabled="disabled" id="city" name="city"  style="width:auto"></select>
                    <select class="dist form-control form-control-noNewline" disabled="disabled" id="district" name="district" style="width: auto"></select>
                </div>
                <div class="input">
                    <label>详细地址</label><font color="#FF0000">*</font>&nbsp;
                    <input type="text" id="newAddr1" class="form-control" name="newAddress" >
                </div>
                <a href="#" class="add-cart item_add" onclick="">添加</a>
            </form>
        </div> -->


        <div id=confirm>
    	    <label >合计:</label>
    	    <label id=totalCredit>240</label><br>
        </div>
        <button type="button" id=commit class="checkout-but" onclick="gotoPay()">前去支付</button>

    </div>
</div>


</body>
</html>