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
<script>
    function enterAddr(exchangeID){
        var addr2ID = "addr2"+exchangeID;
        $("#"+addr2ID).show();
    }

    function agreeExchange(exchangeID){
        var addr2ID = "addr2"+exchangeID;
        $("#"+addr2ID).submit();
    }

    function deliverExchangePassive(exchangeID){
        var formID = "deliverExchangePassiveForm"+exchangeID;
        $("#"+formID).show();
    }

    function fh2(exchangeID){
        var formID = "deliverExchangePassiveForm"+exchangeID;
        $("#"+formID).submit();
    }

    function deliverExchangeActive(exchangeID){
        var formID = "deliverExchangeActiveForm"+exchangeID;
        $("#"+formID).show();
    }

    function fh1(exchangeID){
        var formID = "deliverExchangeActiveForm"+exchangeID;
        $("#"+formID).submit();
    }

    /*
    function showComment1(exchange){
        var formID = "comment1Form"+exchange;
        $("#"+formID).show();
    }

    function comment1(exchange){
        var formID = "comment1Form"+exchange;
        $("#"+formID).submit();
    }

    function showComment2(exchange){
        var formID = "comment2Form"+exchange;
        $("#"+formID).show();
    }

    function comment2(exchange){
        var formID = "comment2Form"+exchange;
        $("#"+formID).submit();
    }
    */

    function submitRating(role,type,id){
        // role: "comment2" "comment1"
        // type: "Exchange" "ExchangeHistory"
        // id: exchangeID ehID
        var creditRatingFormID = "creditRatingForm-" + role + "-" + type + id;
            // for example: creditRatingForm-comment2-ExchangeHistory<s:property value="ehID"/> 
        var creditRatingBtnID = "creditRatingBtn-" + role + "-" + type + id;
            // for example: creditRatingBtn-comment2-ExchangeHistory<s:property value="ehID"/>
        var params = $("#"+creditRatingFormID).serialize();
        var actionUrl = '<%=path%>/commentAction/honestyRatingWhen'+type;
        $.ajax({
            url:actionUrl,
            type:'POST',
            data:params,
            success:function(msg){
                if(msg.success){
                    showTip('信用评价成功！','success');
                    $("#"+creditRatingBtnID).remove();
                    $("#"+creditRatingFormID).remove();
                }
            },
            error:function(xhr,status,error){
                alert('status='+status+',error='+error);
            }
        });
    }
    
    $(document).ready(function(){


        //$("#borrow-book-list").hide();
        $("#activeExchange-list").hide();


        $("#current-tab ul").click(function(){
            $("#current-tab .single-bottom").slideToggle(300);
        });





        $("#show-activeExchange-list").click(function(){
            $("#activeExchange-list").hide();
            $("#passiveExchange-list").hide();
            $("#activeExchange-list").show();

        });

        $("#show-passiveExchange-list").click(function(){
            $("#passiveExchange-list").hide();
            $("#activeExchange-list").hide();
            $("#passiveExchange-list").show();

        });


    });
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
                            <li class="sort"><a href="<%=path%>/userAction/showSellerCenter">卖家中心</a></li>
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
                            <li class="sort"><a href="<%=path%>/borrowAction/showMyBorrow">我的借阅</a></li>
                        </ul>
                        <div class="clearfix"> </div>
                    </div>
                    <div id="current-tab" class="tab1">
                        <ul class="place">
                            <li class="sort"><a href="#">我的交换</a></li>
                            <li class="by"><span class="glyphicon glyphicon-triangle-bottom" aria-hidden="true"></span></li>
                        </ul>
                        <div class="clearfix"> </div>
                        <div class="single-bottom">
                            <a id="show-passiveExchange-list" href="#"><p>向我申请的图书</p></a>
                            <a id="show-activeExchange-list" href="#"><p>我申请的图书</p></a>
                        </div>
                    </div>
                    <div class="tab1">
                        <ul class="place">
                            <li class="sort"><a href="#">我的购买</a></li>
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

        <h3 align="center">我的交换</h3>
        <div id="tip"></div>
        <div id="cartinfo" class="cart-item">
            <div class="container">

                <!-- 向我申请的 -->
                <div id="passiveExchange-list">
                <s:iterator value="#passiveExchange" status="st">
                    <div id="<s:property value="exchangeID"/>" class="cart-header">
                        <div class="cart-sec simpleCart_shelfItem">
                            <div class="cart-item cyc">
                                <img src="<%=path%>/imageAction/showImage?imageID=<s:property value="wantedBook.imageID"/>" class="img-responsive" alt="">
                            </div>
                            <div class="cart-item-info">
                                <h4>
                                    <s:if test="exchangeStatus=='WAITING'">
                                        <p id="status<s:property value="exchangeID"/>">订单号：<s:property value="exchangeID"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：等待</p>
                                    </s:if>
                                    <s:elseif test="exchangeStatus=='AGREED'">
                                        <p id="status<s:property value="exchangeID"/>">订单号：<s:property value="exchangeID"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：已同意</p>
                                    </s:elseif>
                                    <s:elseif test="exchangeStatus=='REJECTED'">
                                        <p id="status<s:property value="exchangeID"/>">订单号：<s:property value="exchangeID"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：拒绝</p>
                                    </s:elseif>
                                    <s:elseif test="exchangeStatus=='CANCELED'">
                                        <p id="status<s:property value="exchangeID"/>">订单号：<s:property value="exchangeID"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：已取消</p>
                                    </s:elseif>
                                    <s:elseif test="exchangeStatus=='COMPLETED'">
                                        <p id="status<s:property value="exchangeID"/>">订单号：<s:property value="exchangeID"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：已完成</p>
                                    </s:elseif>
                                </h4><br>
                                <ul class="qty">
                                    <li><p><a href="<%=path%>/bookAction/showBookProfile?bookID=<s:property value="wantedBookID"/>">
                                        换出的书：<s:property value="wantedBook.bookName"/></a></p></li>
                                </ul>
                                <div class="delivery">
                                    <s:if test="exchangeStatus=='AGREED'">
                                        <p>回复时间：<s:property value="responseDate"/></p><br>
                                        <p>我发货时间：<s:property value="fhDate2"/></p><br>
                                        <p>我收货时间：<s:property value="shDate2"/></p><br>
                                    </s:if>
                                    <s:elseif test="exchangeStatus=='REJECTED'">
                                        <p>回复时间：<s:property value="responseDate"/></p><br>
                                    </s:elseif>
                                </div>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="cart-sec simpleCart_shelfItem">
                            <div class="cart-item cyc">
                                <img src="<%=path%>/imageAction/showImage?imageID=<s:property value="hadBook.imageID"/>" class="img-responsive" alt="">
                            </div>
                            <div class="cart-item-info">
                                <ul class="qty">
                                    <li><p><a href="<%=path%>/bookAction/showBookProfile?bookID=<s:property value="hadBookID"/>">
                                        换入的书：<s:property value="hadBook.bookName"/></a></p></li>
                                </ul>
                                <div class="delivery">
                                    <p>申请时间：<s:property value="applyDate"/></p><br>
                                    <s:if test="exchangeStatus=='AGREED'">
                                        <p>对方发货时间：<s:property value="fhDate1"/></p><br>
                                        <p>对方发货物流单号：<s:property value="trackingNo1"/></p><br>
                                        <p>对方收货地址：<s:property value="address1"/></p><br>
                                        <p>对方收货时间：<s:property value="shDate1"/></p><br>
                                    </s:if>
                                    <s:if test="exchangeStatus=='WAITING'">
                                        <a href="#" id="" class="add-cart item_add" onclick="enterAddr(<s:property value="exchangeID"/>)" style="">同意申请</a>
                                        <a href="<%=path%>/exchangeAction/rejectExchange?exchangeID=<s:property value="exchangeID"/>" id="" class="add-cart item_add" onclick="" style="">拒绝申请</a>
                                        <form id="addr2<s:property value="exchangeID"/>" style="display: none" action="<%=path%>/exchangeAction/agreeExchange" method="post">
                                            <input type="hidden" name="exchangeID" value="<s:property value="exchangeID"/>"/>
                                            <input type="text" name="address2" placeholder="请填写您的地址"/>
                                            <a href="#" class="add-cart item_add" onclick="agreeExchange(<s:property value="exchangeID"/>)">提交</a>
                                        </form>

                                    </s:if>
                                    <s:elseif test="exchangeStatus=='AGREED'">
                                        <s:if test="fhDate2==null">
                                            <a href="#" id="" class="add-cart item_add" onclick="deliverExchangePassive(<s:property value="exchangeID"/>)" style="">发货</a>
                                        </s:if>
                                        <s:if test="shDate2==null&&fhDate1!=null">
                                            <a href="<%=path%>/exchangeAction/sh2?exchangeID=<s:property value="exchangeID"/>"  class="add-cart item_add"  style="">收货</a>
                                        </s:if>
                                        <form id="deliverExchangePassiveForm<s:property value="exchangeID"/>" style="display: none" action="<%=path%>/exchangeAction/fh2" method="post">
                                            <input type="hidden" name="exchangeID" value="<s:property value="exchangeID"/>"/>
                                            <input type="text" name="trackingNo2" placeholder="请填写物流单号"/>
                                            <a href="#" class="add-cart item_add" onclick="fh2(<s:property value="exchangeID"/>)">提交</a>
                                        </form>
                                    </s:elseif>
                                    <s:if test="comment2==null">
                                        <a id="creditRatingBtn-comment2-Exchange<s:property value="exchangeID"/>" href="#" class="add-cart item_add" onclick="$('#creditRatingForm-comment2-Exchange<s:property value="exchangeID"/>').show()">信用评价</a>
                                        <form id="creditRatingForm-comment2-Exchange<s:property value="exchangeID"/>" style="display: none" action="<%=path%>/commentAction/honestyRatingWhenExchange" method="post">
                                            <input type="hidden" name="exchangeID" value="<s:property value="exchangeID"/>"/>
                                            <select name="creditRating" class="form-control form-control-noNewline">
                                                <option value="-1">差评</option>
                                                <option value="0">中评</option>
                                                <option value="1">好评</option>
                                            </select>
                                            <a href="#" class="add-cart item_add" onclick="submitRating('comment2','Exchange',<s:property value="exchangeID"/>)">提交</a>
                                        </form>
                                    </s:if>
                                </div>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                    </div><hr>
                </s:iterator>

                    <!-- 向我申请的历史 -->
                    <s:iterator value="#passiveExchangeHistory" status="st">
                        <div id="<s:property value="ehID"/>" class="cart-header">
                            <div class="cart-sec simpleCart_shelfItem">
                                <div class="cart-item cyc">
                                    <img src="<%=path%>/imageAction/showImage?imageID=<s:property value="wantedBook.imageID"/>" class="img-responsive" alt="">
                                </div>
                                <div class="cart-item-info">
                                    <h4>
                                        <s:if test="exchangeStatus=='WAITING'">
                                            <p id="status<s:property value="exchangeID"/>">订单号：<s:property value="exchangeID"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：等待</p>
                                        </s:if>
                                        <s:elseif test="exchangeStatus=='AGREED'">
                                            <p id="status<s:property value="exchangeID"/>">订单号：<s:property value="exchangeID"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：已同意</p>
                                        </s:elseif>
                                        <s:elseif test="exchangeStatus=='REJECTED'">
                                            <p id="status<s:property value="exchangeID"/>">订单号：<s:property value="exchangeID"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：拒绝</p>
                                        </s:elseif>
                                        <s:elseif test="exchangeStatus=='CANCELED'">
                                            <p id="status<s:property value="exchangeID"/>">订单号：<s:property value="exchangeID"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：已取消</p>
                                        </s:elseif>
                                        <s:elseif test="exchangeStatus=='COMPLETED'">
                                            <p id="status<s:property value="exchangeID"/>">订单号：<s:property value="exchangeID"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：已完成</p>
                                        </s:elseif>
                                    </h4><br>
                                    <ul class="qty">
                                        <li><p><a href="<%=path%>/bookAction/showBookProfile?bookID=<s:property value="wantedBookID"/>">
                                            换出的书：<s:property value="wantedBook.bookName"/></a></p></li>
                                    </ul>
                                    <div class="delivery">
                                        <s:if test="exchangeStatus=='AGREED'||exchangeStatus=='COMPLETED'">
                                            <p>回复时间：<s:property value="responseDate"/></p><br>
                                            <p>我发货时间：<s:property value="fhDate2"/></p><br>
                                            <p>我收货时间：<s:property value="shDate2"/></p><br>
                                        </s:if>
                                        <s:elseif test="exchangeStatus=='REJECTED'">
                                            <p>回复时间：<s:property value="responseDate"/></p><br>
                                        </s:elseif>
                                    </div>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                            <div class="cart-sec simpleCart_shelfItem">
                                <div class="cart-item cyc">
                                    <img src="<%=path%>/imageAction/showImage?imageID=<s:property value="hadBook.imageID"/>" class="img-responsive" alt="">
                                </div>
                                <div class="cart-item-info">
                                    <ul class="qty">
                                        <li><p><a href="<%=path%>/bookAction/showBookProfile?bookID=<s:property value="hadBookID"/>">
                                            换入的书：<s:property value="hadBook.bookName"/></a></p></li>
                                    </ul>
                                    <div class="delivery">
                                        <p>申请时间：<s:property value="applyDate"/></p><br>
                                        <s:if test="exchangeStatus=='AGREED'||exchangeStatus=='COMPLETED'">
                                            <p>对方发货时间：<s:property value="fhDate1"/></p><br>
                                            <p>对方发货物流单号：<s:property value="trackingNo1"/></p><br>
                                            <p>对方收货地址：<s:property value="address1"/></p><br>
                                            <p>对方收货时间：<s:property value="shDate1"/></p><br>
                                        </s:if>
                                        <s:if test="comment2==null">
                                            <a id="creditRatingBtn-comment2-ExchangeHistory<s:property value="ehID"/>" href="#" class="add-cart item_add" onclick="$('#creditRatingForm-comment2-ExchangeHistory<s:property value="ehID"/>').show()">信用评价</a>
                                            <form id="creditRatingForm-comment2-ExchangeHistory<s:property value="ehID"/>" style="display: none" action="<%=path%>/commentAction/honestyRatingWhenExchangeHistory" method="post">
                                                <input type="hidden" name="ehID" value="<s:property value="ehID"/>"/>
                                                <select name="creditRating" class="form-control form-control-noNewline">
                                                    <option value="-1">差评</option>
                                                    <option value="0">中评</option>
                                                    <option value="1">好评</option>
                                                </select>
                                                <a href="#" class="add-cart item_add" onclick="submitRating('comment2','ExchangeHistory',<s:property value="ehID"/>)">提交</a>
                                            </form>
                                        </s:if>
                                    </div>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                        </div><hr>
                    </s:iterator>
                </div>

                <!-- 我申请的 -->
                <div id="activeExchange-list">
                    <s:iterator value="#activeExchange" status="st">
                        <div id="<s:property value="exchangeID"/>" class="cart-header">
                            <div class="cart-sec simpleCart_shelfItem">
                                <div class="cart-item cyc">
                                    <img src="<%=path%>/imageAction/showImage?imageID=<s:property value="hadBook.imageID"/>" class="img-responsive" alt="">
                                </div>
                                <div class="cart-item-info">
                                    <h4>
                                        <s:if test="exchangeStatus=='WAITING'">
                                            <p id="status<s:property value="exchangeID"/>">订单号：<s:property value="exchangeID"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：等待</p>
                                        </s:if>
                                        <s:elseif test="exchangeStatus=='AGREED'">
                                            <p id="status<s:property value="exchangeID"/>">订单号：<s:property value="exchangeID"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：已同意</p>
                                        </s:elseif>
                                        <s:elseif test="exchangeStatus=='REJECTED'">
                                            <p id="status<s:property value="exchangeID"/>">订单号：<s:property value="exchangeID"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：拒绝</p>
                                        </s:elseif>
                                        <s:elseif test="exchangeStatus=='CANCELED'">
                                            <p id="status<s:property value="exchangeID"/>">订单号：<s:property value="exchangeID"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：已取消</p>
                                        </s:elseif>
                                        <s:elseif test="exchangeStatus=='COMPLETED'">
                                            <p id="status<s:property value="exchangeID"/>">订单号：<s:property value="exchangeID"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：已完成</p>
                                        </s:elseif>
                                    </h4><br>
                                    <ul class="qty">
                                        <li><p><a href="<%=path%>/bookAction/showBookProfile?bookID=<s:property value="hadBookID"/>">
                                            换出的书：<s:property value="hadBook.bookName"/></a></p></li>
                                    </ul>
                                    <div class="delivery">
                                        <p>申请时间：<s:property value="applyDate"/></p><br>
                                        <s:if test="exchangeStatus=='AGREED'">
                                            <p>我发货时间：<s:property value="fhDate1"/></p><br>
                                            <p>我收货时间：<s:property value="shDate1"/></p><br>
                                        </s:if>
                                    </div>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                            <div class="cart-sec simpleCart_shelfItem">
                                <div class="cart-item cyc">
                                    <img src="<%=path%>/imageAction/showImage?imageID=<s:property value="wantedBook.imageID"/>" class="img-responsive" alt="">
                                </div>
                                <div class="cart-item-info">
                                    <ul class="qty">
                                        <li><p><a href="<%=path%>/bookAction/showBookProfile?bookID=<s:property value="wantedBookID"/>">
                                            换入的书：<s:property value="wantedBook.bookName"/></a></p></li>
                                    </ul>
                                    <div class="delivery">
                                        <s:if test="exchangeStatus=='AGREED'">
                                            <p>回复时间：<s:property value="responseDate"/></p><br>
                                            <p>对方发货时间：<s:property value="fhDate2"/></p><br>
                                            <p>对方发货物流单号：<s:property value="trackingNo2"/></p><br>
                                            <p>对方收货地址：<s:property value="address2"/></p><br>
                                            <p>对方收货时间：<s:property value="shDate2"/></p><br>
                                        </s:if>
                                        <s:elseif test="exchangeStatus=='REJECTED'">
                                            <p>回复时间：<s:property value="responseDate"/></p><br>
                                        </s:elseif>
                                        <s:if test="exchangeStatus=='WAITING'">
                                            <a href="<%=path%>/exchangeAction/cancelExchange?exchangeID=<s:property value="exchangeID"/>" id="" class="add-cart item_add" style="">取消申请</a>
                                        </s:if>
                                        <s:elseif test="exchangeStatus=='AGREED'">
                                            <s:if test="fhDate1==null">
                                                <a href="#" id="" class="add-cart item_add" onclick="deliverExchangeActive(<s:property value="exchangeID"/>)" style="">发货</a>
                                            </s:if>
                                            <s:if test="shDate1==null&&fhDate2!=null">
                                                <a href="<%=path%>/exchangeAction/sh1?exchangeID=<s:property value="exchangeID"/>"  class="add-cart item_add"  style="">收货</a>
                                            </s:if>
                                            <form id="deliverExchangeActiveForm<s:property value="exchangeID"/>" style="display: none" action="<%=path%>/exchangeAction/fh1" method="post">
                                                <input type="hidden" name="exchangeID" value="<s:property value="exchangeID"/>"/>
                                                <input type="text" name="trackingNo1" placeholder="请填写物流单号"/>
                                                <a href="#" class="add-cart item_add" onclick="fh1(<s:property value="exchangeID"/>)">提交</a>
                                            </form>
                                        </s:elseif>
                                        <s:if test="comment1==null">
                                            <a id="creditRatingBtn-comment1-Exchange<s:property value="exchangeID"/>" href="#" class="add-cart item_add" onclick="$('#creditRatingForm-comment1-Exchange<s:property value="exchangeID"/>').show()">信用评价</a>
                                            <form id="creditRatingForm-comment1-Exchange<s:property value="exchangeID"/>" style="display: none" action="<%=path%>/commentAction/honestyRatingWhenExchange" method="post">
                                                <input type="hidden" name="exchangeID" value="<s:property value="exchangeID"/>"/>
                                                <select name="creditRating" class="form-control form-control-noNewline">
                                                    <option value="-1">差评</option>
                                                    <option value="0">中评</option>
                                                    <option value="1">好评</option>
                                                </select>
                                                <a href="#" class="add-cart item_add" onclick="submitRating('comment1','Exchange',<s:property value="exchangeID"/>)">提交</a>
                                            </form>
                                        </s:if>
                                    </div>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                        </div><hr>
                    </s:iterator>

                    <!-- 我申请的历史 -->
                    <s:iterator value="#activeExchangeHistory" status="st">
                        <div id="<s:property value="exchangeID"/>" class="cart-header">
                            <div class="cart-sec simpleCart_shelfItem">
                                <div class="cart-item cyc">
                                    <img src="<%=path%>/imageAction/showImage?imageID=<s:property value="hadBook.imageID"/>" class="img-responsive" alt="">
                                </div>
                                <div class="cart-item-info">
                                    <h4>
                                        <s:if test="exchangeStatus=='WAITING'">
                                            <p id="status<s:property value="exchangeID"/>">订单号：<s:property value="exchangeID"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：等待</p>
                                        </s:if>
                                        <s:elseif test="exchangeStatus=='AGREED'">
                                            <p id="status<s:property value="exchangeID"/>">订单号：<s:property value="exchangeID"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：已同意</p>
                                        </s:elseif>
                                        <s:elseif test="exchangeStatus=='REJECTED'">
                                            <p id="status<s:property value="exchangeID"/>">订单号：<s:property value="exchangeID"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：拒绝</p>
                                        </s:elseif>
                                        <s:elseif test="exchangeStatus=='CANCELED'">
                                            <p id="status<s:property value="exchangeID"/>">订单号：<s:property value="exchangeID"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：已取消</p>
                                        </s:elseif>
                                        <s:elseif test="exchangeStatus=='COMPLETED'">
                                            <p id="status<s:property value="exchangeID"/>">订单号：<s:property value="exchangeID"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：已完成</p>
                                        </s:elseif>
                                    </h4><br>
                                    <ul class="qty">
                                        <li><p><a href="<%=path%>/bookAction/showBookProfile?bookID=<s:property value="hadBookID"/>">
                                            换出的书：<s:property value="hadBook.bookName"/></a></p></li>
                                    </ul>
                                    <div class="delivery">
                                        <p>申请时间：<s:property value="applyDate"/></p><br>
                                        <s:if test="exchangeStatus=='AGREED'||exchangeStatus=='COMPLETED'">
                                            <p>我发货时间：<s:property value="fhDate1"/></p><br>
                                            <p>我收货时间：<s:property value="shDate1"/></p><br>
                                        </s:if>
                                    </div>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                            <div class="cart-sec simpleCart_shelfItem">
                                <div class="cart-item cyc">
                                    <img src="<%=path%>/imageAction/showImage?imageID=<s:property value="wantedBook.imageID"/>" class="img-responsive" alt="">
                                </div>
                                <div class="cart-item-info">
                                    <ul class="qty">
                                        <li><p><a href="<%=path%>/bookAction/showBookProfile?bookID=<s:property value="wantedBookID"/>">
                                            换入的书：<s:property value="wantedBook.bookName"/></a></p></li>
                                    </ul>
                                    <div class="delivery">
                                        <s:if test="exchangeStatus=='AGREED'||exchangeStatus=='COMPLETED'">
                                            <p>回复时间：<s:property value="responseDate"/></p><br>
                                            <p>对方发货时间：<s:property value="fhDate2"/></p><br>
                                            <p>对方发货物流单号：<s:property value="trackingNo2"/></p><br>
                                            <p>对方收货地址：<s:property value="address2"/></p><br>
                                            <p>对方收货时间：<s:property value="shDate2"/></p><br>
                                        </s:if>
                                        <s:elseif test="exchangeStatus=='REJECTED'">
                                            <p>回复时间：<s:property value="responseDate"/></p><br>
                                        </s:elseif>
                                        <s:if test="comment1==null">
                                            <a id="creditRatingBtn-comment1-ExchangeHistory<s:property value="ehID"/>" href="#" class="add-cart item_add" onclick="$('#creditRatingForm-comment1-ExchangeHistory<s:property value="ehID"/>').show()">信用评价</a>
                                            <form id="creditRatingForm-comment1-ExchangeHistory<s:property value="ehID"/>" style="display: none" action="<%=path%>/commentAction/honestyRatingWhenExchangeHistory" method="post">
                                                <input type="hidden" name="ehID" value="<s:property value="ehID"/>"/>
                                                <select name="creditRating" class="form-control form-control-noNewline">
                                                    <option value="-1">差评</option>
                                                    <option value="0">中评</option>
                                                    <option value="1">好评</option>
                                                </select>
                                                <a href="#" class="add-cart item_add" onclick="submitRating('comment1','ExchangeHistory',<s:property value="ehID"/>)">提交</a>
                                            </form>
                                        </s:if>
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
</div>
</body>
</html>