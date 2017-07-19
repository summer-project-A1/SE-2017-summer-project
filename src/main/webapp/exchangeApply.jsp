<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="global.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Exchange Apply</title>
</head>
<body>
<div>
	<h3>确认交换信息</h3>
</div>
<div id="<s:property value="#wanted.getBookID()"/>" class="cart-header">
	<div class="cart-sec simpleCart_shelfItem">
    	<div class="cart-item cyc">
        	<img src="<%=path%>/imageAction/showImage?imageID=<s:property value="imageID"/>" class="img-responsive" alt="">
        </div>
        <div class="cart-item-info">
        	<h3>
            	<a href="<%=path%>/bookAction/showBookProfile?bookID=<s:property value="bookID"/>">
                   	书名：<s:property value="bookName"/>
                </a>
                <br>
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
<div>
<h4>请选择用于交换的图书</h4>
</div>
<div style="width:400px;height:400;overflow-y:scroll;">
	<s:iterator value="#allBooks"  status="st">
    	<div id="product<s:property value="#st.index"/>" class="product-grid" style="display: none;">
        	<a id="<s:property value="bookID"/>" onclick=select(this)>
            	<div class="more-product"><span></span></div>
                <div class="product-img b-link-stripe b-animate-go  thickbox">
                	<img src="<%=path%>/imageAction/showImage?imageID=<s:property value="imageID"/>" class="img-responsive" alt="">
                    <div class="b-wrapper">
                    	<h4 class="b-animate b-from-left  b-delay03">
                        	<button>选定</button>
                        </h4>
                    </div>
               	</div>
            </a>
            <div class="product-info simpleCart_shelfItem">
            	<div class="product-info-cust prt_name">
                	<h4><s:property value="bookName"/></h4>
                    <span class="book-isbn">ISBN:<s:property value="isbn"/></span>
                   	<div class="ofr">
                    	<p class="pric1">作者：<s:property value="author"/></p><br>
                        <p class="pric1">分类：<s:property value="category1"/>&nbsp;标签：<s:property value="category2"/></p><br>
                       	<p class="disc">当前状态：<s:if test="bookStatus==IDLE">空闲</s:if>
                        <s:elseif test="bookStatus==BORROWED">正被借阅</s:elseif>
                        <s:else>正被交换</s:else></p><br>
                        <p class="disc">预约状态：<s:if test="reserved==0">未被预约</s:if><s:else>已被预约</s:else></p>
                    </div>
                    <div class="clearfix"> </div>
                </div>
			</div>
		</div>
	</s:iterator>
</div>
<div>
	<h3>管理收货地址</h3>
	<form>
    	<s:iterator value="#defaultAddrList" status="st">
        	<div id="<s:property value="fullAddressID"/>">
       			<input type="radio" id="defaultAddr" name="address" value="<s:property value="fullAddressString"/>"/>
        		<label id="defaultAddrLabel" for="defaultAddr"><s:property value="fullAddressString"/></label><label >&nbsp;&nbsp;&nbsp;&nbsp;默认地址</label><br>
			</div>
      	</s:iterator>
		<s:iterator value="#addrList" status="st">
        	<div id="<s:property value="fullAddressID"/>">
            	<input type="radio" id="address<s:property value="fullAddressID"/>" name="address" value="<s:property value="fullAddressString"/>"/>
                <label id="addrLabel<s:property value="fullAddressID"/>" for="address<s:property value="fullAddressID"/>"><s:property value="fullAddressString"/></label><a href="#" id="setDefaultAddr<s:property value="fullAddressID"/>" onclick="setDefaultAddr('<s:property value="fullAddressID"/>')">&nbsp;&nbsp;&nbsp;&nbsp;设为默认地址&nbsp;&nbsp;&nbsp;&nbsp;</a>
                <a href="#" id="deleteAddr<s:property value="fullAddressID"/>" onclick="deleteAddress('<s:property value="fullAddressID"/>')">删除地址</a>
            </div>
        </s:iterator>
	</form>
    <a href="#newAddrForm" class="add-cart item_add" onclick="showAddrForm()">添加新地址</a>
   	<form id="newAddrForm" style="display: none">
		<div class="form-group form-group-auto"  id="city_4">
			<select class="prov form-control form-control-noNewline" id="province" name="province" style="width:auto"></select>
            <select class="city form-control form-control-noNewline"  disabled="disabled" id="city" name="city"  style="width:auto"></select>
 	        <select class="dist form-control form-control-noNewline" disabled="disabled" id="district" name="district" style="width: auto"></select>
 	        <label id=msg1></label>
        </div>
        <div class="input">
        	<label>详细地址</label><font color="#FF0000">*</font>&nbsp;<label id=msg2></label>
            <input type="text" id="newAddr" class="form-control" name="address" >
        </div>
   	    <a href="#newAddrForm" class="add-cart item_add" onclick="addNewAddr()">添加</a>
   	    <div style="display:none">
   	    	<input name=wantedID value=<s:property value="#wanted.getBookID()"/>>
   	    	<input name=hadID>
   	    </div>
	</form>
	<div>
		<button type="button" id=commit class="checkout-but" onclick="confirmApply()">提交申请</button>
	</div>
</div>

<script>
function select(obj)
{
	$("input[name='hadID']").val("obj.id");
}

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
               var htmlstr1 = '<div id="'+oldDefaultAddrID+'"><input type="radio" id="address'+oldDefaultAddrID+'" name="address" value="'+oldDefaultAddr+'"/>'
               +'<label id="addrLabel'+oldDefaultAddrID+'" for="address'+oldDefaultAddrID+'">'+oldDefaultAddr+'</label><a href="#" id="setDefaultAddr'+oldDefaultAddrID+'" onclick="setDefaultAddr(\''+oldDefaultAddrID+'\')">设为默认地址&nbsp;&nbsp;&nbsp;&nbsp;</a>' +
                   '<a href="#" id="deleteAddr'+oldDefaultAddrID+'" onclick="deleteAddress(\''+oldDefaultAddrID+'\')">删除地址</a></div>';
               showTip('更换默认地址成功','success');
               $("#defaultAddr").val(newDefaultAddr);
               $("#defaultAddrLabel").html(newDefaultAddr);
               $("#"+newDefaultAddrID).remove();
               $("#addrForm").append(htmlstr1);
           }else{
               showTip('更换默认地址成功','success');
               var htmlstr3 = '<div id="'+newDefaultAddrID+'">'+'<input type="radio" id="defaultAddr" name="address" value="'+newDefaultAddr+'"/><label id="defaultAddrLabel" for="defaultAddr">'+newDefaultAddr+'</label><label>&nbsp;&nbsp;&nbsp;&nbsp;默认地址</label></div>';
               $("#"+newDefaultAddrID).remove();
               $("#addrForm").append(htmlstr3);
           }

       },
        error:function(xhr,status,error){
            alert('status='+status+',error='+error);
        }
    });
}

function showAddrForm()
{
    $("#newAddrForm").show();
}

function addNewAddr(){
    var newAddr = $("#newAddr").val();
    if(newAddr.length == 0)
    {
    	$("#msg2").attr("color","#FF0000");
    	$("#msg2").text('请输入新地址');
    }
    else
    {
        var params = $("#newAddrForm").serialize();
        $.ajax({
            url:'<%=path%>/userAction/addAddress',
            type:'POST',
            data:params,
            success:function(msg){
                showTip('添加新地址成功','success');
                $("#newAddrForm").hide();
                var newAddress = msg.newAddress;
                var newAddressID = msg.newAddressID;
                var htmlstr1 = '<div id="'+newAddressID+'"><input type="radio" id="address'+newAddressID+'" name="address" value="'+newAddress+'"/>'
                +'<label id="addrLabel'+newAddressID+'" for="address'+newAddressID+'">'+newAddress+'</label><a href="#" id="setDefaultAddr'+newAddressID+'" onclick="setDefaultAddr(\''+newAddressID+'\')">&nbsp;&nbsp;&nbsp;&nbsp;设为默认地址&nbsp;&nbsp;&nbsp;&nbsp;</a>' +
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

function confirmApply()
{
    $("#addrForm").submit();
}
</script>
</body>
</html>