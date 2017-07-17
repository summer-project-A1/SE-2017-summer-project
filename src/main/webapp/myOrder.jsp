<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="global.jsp"%>
<html>
<head>
    <title>myOrder</title>
    <style>

        @media ( min-width :768px) {
            #cartinfo{
                margin-top: 51px;
                margin-left:5%;
                margin-right:10%;
            }
        }
        @media ( min-width :1440px) {
            #cartinfo{
                margin-top: 21px;
                margin-left:10%;
                margin-right:10%;
            }

        }
    </style>
</head>
<body>
<!-- header -->
<s:action name="header" executeResult="true" namespace="/"/><!-- home page -->
<script>
    function payOrder(orderID){
        var statusID = "status"+orderID;
        var payDateID = "payDate"+orderID;
        var payBtnID = "payBtn"+orderID;
        var cancelBtnID = "cancelBtn"+orderID;
        $.ajax({
            url:'<%=path%>/orderAction/payOrder',
            type:'POST',
            data:{'orderID':orderID},
            success:function(msg){
                if(msg.success){
                    showTip('支付成功！','success');
                    $("#"+statusID).html("订单号："+orderID+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：未发货");
                    var payDate = msg.payDate;
                    $("#"+payDateID).html("付款时间："+payDate+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
                    $("#"+payDateID).show();
                    $("#"+payBtnID).remove();
                    $("#"+cancelBtnID).remove();
                }else {
                    showTip('发生错误！', 'danger');
                }
            },
            error:function(xhr,status,error){
                alert('status='+status+',error='+error);
            }
        })
    }

    function cancelOrder(orderID){
        var statusID = "status"+orderID;
        var payBtnID = "payBtn"+orderID;
        var cancelBtnID = "cancelBtn"+orderID;
        $.ajax({
            url:'<%=path%>/orderAction/cancelOrder',
            type:'POST',
            data:{'orderID':orderID},
            success:function(msg){
                if(msg.success){
                    showTip('取消成功！','success');
                    $("#"+statusID).html("订单号："+orderID+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：已取消");
                    $("#"+payBtnID).remove();
                    $("#"+cancelBtnID).remove();
                }else {
                    showTip('发生错误！', 'danger');
                }
            },
            error:function(xhr,status,error){
                alert('status='+status+',error='+error);
            }
        });
    }

    function confirmOrderReceipt(orderID){
        var statusID = "status"+orderID;
        var shDateID = "shDate"+orderID;
        var confirmOrderBtnID = "confirmOrderBtn"+orderID;
        $.ajax({
            url:'<%=path%>/orderAction/confirmOrderReceipt',
            type:'POST',
            data:{'orderID':orderID},
            success:function(msg){
                if(msg.success){
                    var shDate = msg.shDate;
                    showTip('收货成功！','success');
                    $("#"+statusID).html("订单号："+orderID+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：已收货");
                    $("#"+shDateID).html("收货时间："+shDate);
                    $("#"+shDateID).show();
                    $("#"+confirmOrderBtnID).remove();
                }else {
                    showTip('发生错误！', 'danger');
                }
            },
            error:function(xhr,status,error){
                alert('status='+status+',error='+error);
            }
        });
    }

    function creditRating(orderID){
        var creditRatingFormID = "creditRatingForm" + orderID;
        $("#"+creditRatingFormID).show();
    }

    function submitRating(orderID){
        var creaditRatingFormID = "creditRatingForm" + orderID;
        $("#"+creaditRatingFormID).submit();
    }
</script>
<div class="products">
    <div class="container">
        <div class="col-md-3 rsiderbar span_1_of_left">
            <section class="sky-form">
                <div class="product_right">
                    <h3 class="m_2"><span class="glyphicon glyphicon-minus" aria-hidden="true"></span>操作选单</h3>

                    <div class="tab1">
                        <ul class="place">
                            <li class="sort"><a href="#">个人信息</a></li>
                        </ul>
                        <div class="clearfix"> </div>
                    </div>
                    <div class="tab1">
                        <ul class="place">
                            <li class="sort"><a href="#">我的发布</a></li>
                        </ul>
                        <div class="clearfix"> </div>
                    </div>
                    <div class="tab1">
                        <ul class="place">
                            <li class="sort"><a href="#">我的借阅</a></li>
                        </ul>
                        <div class="clearfix"> </div>
                    </div>
                    <div class="tab1">
                        <ul class="place">
                            <li class="sort"><a href="#">我的交换</a></li>
                        </ul>
                        <div class="clearfix"> </div>
                    </div>
                    <div class="tab1">
                        <ul class="place">
                            <li class="sort"><a href="#">我的订单</a></li>
                        </ul>
                        <div class="clearfix"> </div>
                    </div>
                    <div class="clearfix"> </div>
                </div>
            </section>
        </div>

        <h3 align="center">我的购买</h3>
        <div id="tip"></div>
        <div id="cartinfo" class="cart-item">
            <div class="container">

                <!-- 迭代器显示订单信息 -->
                <s:iterator value="#orderList" status="st">
                    <div id="<s:property value="orderID"/>" class="cart-header">
                        <div class="cart-sec simpleCart_shelfItem">
                            <div class="cart-item cyc">
                                <img src="<%=path%>/imageAction/showImage?imageID=<s:property value="coverPicture"/>" class="img-responsive" alt="">
                            </div>
                            <div class="cart-item-info">
                                <h4>
                                    <s:if test="status=='NOTPAYED'">
                                        <p id="status<s:property value="orderID"/>">订单号：<s:property value="orderID"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：未支付</p>
                                    </s:if>
                                    <s:elseif test="status=='CANCELED'">
                                        <p id="status<s:property value="orderID"/>">订单号：<s:property value="orderID"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：已取消</p>
                                    </s:elseif>
                                    <s:elseif test="status=='NOTSHIPPED'">
                                        <p id="status<s:property value="orderID"/>">订单号：<s:property value="orderID"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：未发货</p>
                                    </s:elseif>
                                    <s:elseif test="status=='SHIPPED'">
                                        <p id="status<s:property value="orderID"/>">订单号：<s:property value="orderID"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：已发货</p>
                                    </s:elseif>
                                    <s:elseif test="status=='COMPLETED'">
                                        <p id="status<s:property value="orderID"/>">订单号：<s:property value="orderID"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：已完成</p>
                                    </s:elseif>
                                </h4><br>
                                <ul class="qty">
                                    <li><p>书名：<s:property value="bookName"/></p></li>
                                    <li><p>下单时间：<s:property value="orderDate"/></p></li>
                                    <li><p>总积分：<s:property value="totalCredit"/></p></li>
                                </ul>
                                <div class="delivery">
                                    <p>收货人：<s:property value="receiver"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                                    <p>收货地址：<s:property value="address"/></p><br>
                                    <s:if test="status=='NOTPAYED'">
                                        <p id="payDate<s:property value="orderID"/>" style="display: none">付款时间：<s:property value="payDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                                        <p id="fhDate<s:property value="orderID"/>" style="display: none">发货时间：<s:property value="fhDate"/></p><br>
                                        <p id="shDate<s:property value="orderID"/>" style="display: none">收货时间：<s:property value="shDate"/></p><br>

                                        <a href="#" id="payBtn<s:property value="orderID"/>" class="add-cart item_add" onclick="payOrder(<s:property value="orderID"/>)">支付</a>
                                        <a href="#" id="cancelBtn<s:property value="orderID"/>" class="add-cart item_add" onclick="cancelOrder(<s:property value="orderID"/>)">取消</a>
                                    </s:if>
                                    <s:elseif test="status=='CANCELED'">

                                    </s:elseif>
                                    <s:elseif test="status=='NOTSHIPPED'">
                                        <p id="payDate<s:property value="orderID"/>">付款时间：<s:property value="payDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                                        <p id="fhDate<s:property value="orderID"/>" style="display: none">发货时间：<s:property value="fhDate"/></p><br>
                                        <p id="shDate<s:property value="orderID"/>" style="display: none">收货时间：<s:property value="shDate"/></p><br>
                                    </s:elseif>
                                    <s:elseif test="status=='SHIPPED'">
                                        <p id="payDate<s:property value="orderID"/>">付款时间：<s:property value="payDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                                        <p id="fhDate<s:property value="orderID"/>">发货时间：<s:property value="fhDate"/></p><br>
                                        <p id="shDate<s:property value="orderID"/>" style="display: none">收货时间：<s:property value="shDate"/></p><br>

                                        <a id="confirmOrderBtn<s:property value="orderID"/>" href="#" class="add-cart item_add" onclick="confirmOrderReceipt(<s:property value="orderID"/>)">确认收货</a>
                                    </s:elseif>
                                    <s:elseif test="status=='COMPLETED'">
                                        <p id="payDate<s:property value="orderID"/>">付款时间：<s:property value="payDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                                        <p id="fhDate<s:property value="orderID"/>">发货时间：<s:property value="fhDate"/></p><br>
                                        <p id="shDate<s:property value="orderID"/>">收货时间：<s:property value="shDate"/></p><br>

                                        <a href="#" id="creditRatingBtn<s:property value="orderID"/>" class="add-cart item_add" onclick="creditRating(<s:property value="orderID"/>)">信用评价</a>
                                        <form id="creditRatingForm<s:property value="orderID"/>" action="<%=path%>/commentAction/honestyRatingWhenBuy" method="post" style="display: none">
                                            <input type="hidden" name="orderID" value="<s:property value="orderID"/>"/>
                                            <select name="creditRating" class="form-control form-control-noNewline">
                                                <option value="-1">差评</option>
                                                <option value="0">中评</option>
                                                <option value="1">好评</option>
                                            </select>
                                            <a href="#" class="add-cart item_add" onclick="submitRating(<s:property value="orderID"/>)">评价</a>
                                        </form>
                                    </s:elseif>
                                    <div class="clearfix"></div>
                                </div>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                    </div><hr>

                </s:iterator>





                <!-- 以下为静态展示 -->
                <div id="bookID1" class="cart-header">
                    <div class="cart-sec simpleCart_shelfItem">
                        <div class="cart-item cyc">
                            <img src="<%=path%>/images/m5.png" class="img-responsive" alt="">
                        </div>
                        <div class="cart-item-info">
                            <h4>
                                <a href="<%=path%>/">订单号:1580021000037&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：未支付</a>
                            </h4><br>
                            <ul class="qty">
                                <li><p>书名：黄政的自传</p></li>
                                <li><p>下单时间：2017-6-10   20:42:48</p></li>
                                <li><p>总积分：1024</p></li>
                            </ul>
                            <div class="delivery">
                                <p>收货人：黄政&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                                <p>收货地址：东川路800号</p><br>
                                <a href="${showOrderLink}" id="payBtn" class="add-cart item_add">支付</a>
                                <a href="${cancelOrderLink}" id="cancelBtn" class="add-cart item_add">取消</a>
                                <div class="clearfix"></div>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </div><hr>
                <div id="bookID2" class="cart-header">
                    <div class="cart-sec simpleCart_shelfItem">
                        <div class="cart-item cyc">
                            <img src="<%=path%>/images/m5.png" class="img-responsive" alt="">
                        </div>
                        <div class="cart-item-info">
                            <h4>
                                <a href="<%=path%>/">订单号:1580021000037&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：未发货</a>
                            </h4><br>
                            <ul class="qty">
                                <li><p>书名：黄政的自传</p></li>
                                <li><p>下单时间：2017-6-10   20:42:48</p></li>
                                <li><p>总积分：524</p></li>
                            </ul>
                            <div class="delivery">
                                <p>收货人：沈斯杰&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                                <p>收货地址：东川路800号</p><br>
                                <div class="clearfix"></div>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </div><hr>
                <div id="bookID3" class="cart-header">
                    <div class="cart-sec simpleCart_shelfItem">
                        <div class="cart-item cyc">
                            <img src="<%=path%>/images/m5.png" class="img-responsive" alt="">
                        </div>
                        <div class="cart-item-info">
                            <h4>
                                <a href="<%=path%>/">订单号:1580021000037&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：已发货</a>
                            </h4><br>
                            <ul class="qty">
                                <li><p>书名：黄政的自传</p></li>
                                <li><p>下单时间：2017-6-10   20:42:48</p></li>
                                <li><p>总积分：10240</p></li>
                            </ul>
                            <div class="delivery">
                                <p>收货人：高仓靖博&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                                <p>收货地址：东川路800号</p><br>
                                <p>付款时间：2017-6-10   20:42:48&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                                <p>发货时间：2017-6-10   20:42:48</p><br>
                                <a href="${confirmReceiptLink}" id="confirmBtn" class="add-cart item_add" onclick="">确认收货</a>
                                <div class="clearfix"></div>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </div><hr>
                <div id="bookID4" class="cart-header">
                    <div class="cart-sec simpleCart_shelfItem">
                        <div class="cart-item cyc">
                            <img src="<%=path%>/images/m5.png" class="img-responsive" alt="">
                        </div>
                        <div class="cart-item-info">
                            <h4>
                                <a href="<%=path%>/">订单号:1580021000037&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：已完成</a>
                            </h4><br>
                            <ul class="qty">
                                <li><p>书名：黄政的自传</p></li>
                                <li><p>下单时间：2017-6-10   20:42:48</p></li>
                                <li><p>总积分：10240</p></li>
                            </ul>
                            <div class="delivery">
                                <p>收货人：Bjarne&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                                <p>收货地址：东川路800号</p><br>
                                <p>付款时间：2017-6-10   20:42:48&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                                <p>发货时间：2017-6-10   20:42:48</p><br>
                                <p>收货时间：2017-6-10   20:42:48</p><br>
                                <a href="#" id="commentBtn" class="add-cart item_add" onclick="">信用评价</a>
                                <div class="clearfix"></div>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </div><hr>
                <div id="bookID4" class="cart-header">
                    <div class="cart-sec simpleCart_shelfItem">
                        <div class="cart-item cyc">
                            <img src="<%=path%>/images/m5.png" class="img-responsive" alt="">
                        </div>
                        <div class="cart-item-info">
                            <h4>
                                <a href="<%=path%>/">订单号:1580021000037&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：已取消</a>
                            </h4><br>
                            <ul class="qty">
                                <li><p>书名：黄政的自传</p></li>
                                <li><p>下单时间：2017-6-10   20:42:48</p></li>
                                <li><p>总积分：10240</p></li>
                            </ul>
                            <div class="delivery">
                                <p>收货人：Bjarne&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                                <p>收货地址：东川路800号</p><br>
                                <div class="clearfix"></div>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </div><hr>
            </div>
        </div>
    </div>
</div>
</body>
</html>
