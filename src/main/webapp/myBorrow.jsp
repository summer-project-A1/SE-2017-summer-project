<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@include file="global.jsp"%>
<html>
<head>
    <title>myBorrow</title>
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
    function payBook(borrowID){
        var statusID = "status"+borrowID;
        var payDateID = 'payDate'+borrowID;
        var payBtnID = 'payBtn'+borrowID;
        $.ajax({
            url:'<%=path%>/borrowAction/payBorrow',
            type:'POST',
            data:{'borrowID':borrowID},
            success:function(msg){
                if(msg.success){
                    var payDate = msg.payDate;
                    showTip('支付成功！','success');
                    $("#"+statusID).html("当前状态：卖家未发货");
                    $("#"+payBtnID).remove();
                    $("#"+payDateID).html("付款日期："+payDate+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
                    $("#"+payDateID).show();
                }else{
                    showTip('发生错误！', 'danger');
                }
            },
            error:function(xhr,status,error){
                alert('status='+status+',error='+error);
            }
        });
    }

    function confirmReceipt(borrowID){
        var confirmBtnID = "confirmBtn"+borrowID;
        var statusID = "status"+borrowID;
        var borrowDateID = "borrowDate"+borrowID;
        var returnAddrID = "returnAddr"+borrowID;
        $.ajax({
           url:'<%=path%>/borrowAction/confirmReceipt',
           type:'POST',
           data:{'borrowID':borrowID},
           success:function(msg){
               if(msg.success){
                   var borrowDate = msg.borrowDate;
                   var returnAddr = msg.returnAddress;
                   showTip('已确认收货！','success');
                   $("#"+statusID).html("当前状态：买家未归还");
                   $("#"+confirmBtnID).remove();
                   $("#"+borrowDateID).html("收货日期："+borrowDate+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
                   $("#"+borrowDateID).show();
                   $("#"+returnAddrID).html("归还地址："+returnAddr+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
                   $("#"+returnAddrID).show();
                   //window.setTimeout("window.location='<%=path%>/borrowAction/showMyBorrow'",1500);
               }else{
                   showTip('发生错误！', 'danger');
               }
           },
            error:function(xhr,status,error){
                alert('status='+status+',error='+error);
            }
        });
    }

    function returnBook(borrowID){
        var returnBtnID = "returnBtn"+borrowID;
        var delayBtnID = "delayBtn"+borrowID;
        var returnDateID = "returnDate"+borrowID;
        var trackingNOID = "trackingNO"+borrowID;
        var trackingFormID = "tracking"+borrowID;
        var trackingNO1 = $("#"+trackingNOID).val();
        $.ajax({
            url:'<%=path%>/borrowAction/returnBook',
            type:'POST',
            data:{
                'borrowID':borrowID,
                'trackingNO1':trackingNO1,
            },
            success:function(msg){
                if (msg.success) {
                    var returnDate = msg.returnDate;
                    $("#"+returnBtnID).remove();
                    $("#"+delayBtnID).remove();
                    $("#"+returnDateID).html("归还日期："+returnDate+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
                    $("#"+returnDateID).show();
                    showTip('已归还图书！', 'success');
                    $("#"+trackingFormID).hide();
                }
                else {
                    showTip('发生错误！', 'danger');
                }
            },
            error:function(xhr,status,error){
                alert('status='+status+',error='+error);
            }

        });
    }

    function showReturnBook(borrowID){
        var trackingFormID = "tracking"+borrowID;
        $("#"+trackingFormID).show();
    }

    function delayBook(borrowID){
        var delayBtnID = "delayBtn"+borrowID;
        var yhdateID = "yhdate"+borrowID;
        $.ajax({
            url:'<%=path%>/borrowAction/delayBook',
            type:'POST',
            data:{
                'borrowID':borrowID
            },
            success:function(msg){
                if (msg.success) {
                    var yhdate = msg.yhdate;
                    $("#"+delayBtnID).remove();
                    $("#"+yhdateID).html("应还日期："+yhdate);
                    showTip('已续借图书！', 'success');

                }
                else {
                    showTip('发生错误！', 'danger');
                }
            },
            error:function(xhr,status,error){
                alert('status='+status+',error='+error);
            }

        });
    }

    function commentBook(borrowID){
        var commentFormID = "commentForm" + borrowID;
        $("#"+commentFormID).show();
    }

    function submitComment(borrowID){
        var commentID = "comment" + borrowID;
        var commentFormID = "commentForm" + borrowID;
        var commentContent = $("#"+commentID).val();
        if(commentContent.length==0){
            showTip('评论不可为空','danger');
        }else{
            $("#"+commentFormID).submit();
        }
    }

    function creditRating(borrowID){
        var creditRatingFormID = "creditRatingForm" + borrowID;
        $("#"+creditRatingFormID).show();
    }

    function submitRating(borrowID){
        var creaditRatingFormID = "creditRatingForm" + borrowID;
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
        <h3 align="center">我的借阅</h3>
        <div id="tip"></div>
        <div id="cartinfo" class="cart-item">
            <div class="container">
                <!-- 以下迭代显示尚未归还的图书 -->
                <s:iterator value="#borrowBook" status="map_state">
                    <div id="borrowBook<s:property value="borrowID"/>" class="cart-header">
                        <div class="cart-sec simpleCart_shelfItem">
                            <div class="cart-item cyc">
                                <img src="<%=path%>/imageAction/showImage?imageID=<s:property value="imageID"/>" class="img-responsive" alt="">
                            </div>
                            <div class="cart-item-info">
                                <h4>
                                    <a href="<%=path%>/bookAction/showBookProfile?bookID=<s:property value="bookID"/>">
                                        书名：<s:property value="bookName"/></a><br>
                                    <s:if test="status=='NOTPAYED">
                                        <span id="status<s:property value="borrowID"/>">当前状态：买家未付款</span>
                                    </s:if>
                                    <s:elseif test="status=='NOTSHIPPED'">
                                        <span id="status<s:property value="borrowID"/>">当前状态：卖家未发货</span>
                                    </s:elseif>
                                    <s:elseif test="status=='SHIPPED'">
                                        <span id="status<s:property value="borrowID"/>">当前状态：卖家已发货</span>
                                    </s:elseif>
                                    <s:elseif test="status=='NOTRETURNED'">
                                        <span id="status<s:property value="borrowID"/>">当前状态：买家未归还</span>
                                    </s:elseif>
                                    <s:elseif test="status=='RETURNED'">
                                        <span id="status<s:property value="borrowID"/>">当前状态：买家已归还，待卖家确认</span>
                                    </s:elseif>
                                    <s:elseif test="status=='COMPLETED'">
                                        <span id="status<s:property value="borrowID"/>">当前状态：借阅完成</span>
                                    </s:elseif>
                                </h4>
                                <ul class="qty">
                                    <li><p>作者：<s:property value="author"/></p></li>
                                    <li><p>分类：<s:property value="category1"/>&nbsp;&nbsp;<s:property value="category2"/></p></li>
                                    <li><p>借阅积分：<s:property value="borrowPrice"/></p></li>
                                    <li><p>出借人：</p></li>
                                </ul>
                                <div class="delivery">
                                    <s:if test="status=='NOTPAYED'">
                                        <p id="orderDate<s:property value="borrowID"/>">下单日期：<s:property value="orderDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                                        <p id="payDate<s:property value="borrowID"/>" style="display: none">付款日期：<s:property value="payDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><br>
                                        <p id="fhDate<s:property value="borrowID"/>" style="display: none">发货日期：<s:property value="fhDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                                        <p id="borrowDate<s:property value="borrowID"/>" style="display: none">收货日期：<s:property value="borrowDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><br>
                                        <p id="yhdate<s:property value="borrowID"/>" style="display: none">应还日期：<s:property value="yhDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                                        <p id="returnDate<s:property value="borrowID"/>" style="display: none">归还日期：<s:property value="returnDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><br>
                                        <p id="shDate<s:property value="borrowID"/>" style="display: none">完成日期：<s:property value="shDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                                        <p id="returnAddr<s:property value="borrowID"/>" style="display: none">归还地址：<s:property value="returnAddress"/></p><br>
                                        <a href="#" id="payBtn<s:property value="borrowID"/>" class="add-cart item_add" onclick="payBook(<s:property value="borrowID"/>)">支付</a>
                                    </s:if>
                                    <s:if test="status=='NOTSHIPPED'">
                                        <p id="orderDate<s:property value="borrowID"/>">下单日期：<s:property value="orderDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                                        <p id="payDate<s:property value="borrowID"/>">付款日期：<s:property value="payDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><br>
                                        <p id="fhDate<s:property value="borrowID"/>" style="display: none">发货日期：<s:property value="fhDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                                        <p id="borrowDate<s:property value="borrowID"/>" style="display: none">收货日期：<s:property value="borrowDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><br>
                                        <p id="yhdate<s:property value="borrowID"/>" style="display: none">应还日期：<s:property value="yhDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                                        <p id="returnDate<s:property value="borrowID"/>" style="display: none">归还日期：<s:property value="returnDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><br>
                                        <p id="shDate<s:property value="borrowID"/>" style="display: none">完成日期：<s:property value="shDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                                        <p id="returnAddr<s:property value="borrowID"/>" style="display: none">归还地址：<s:property value="returnAddress"/></p><br>
                                    </s:if>
                                    <s:elseif test="status=='SHIPPED'">
                                        <p id="orderDate<s:property value="borrowID"/>">下单日期：<s:property value="orderDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                                        <p id="payDate<s:property value="borrowID"/>">付款日期：<s:property value="payDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><br>
                                        <p id="fhDate<s:property value="borrowID"/>">发货日期：<s:property value="fhDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                                        <p id="borrowDate<s:property value="borrowID"/>" style="display: none">收货日期：<s:property value="borrowDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><br>
                                        <p id="yhdate<s:property value="borrowID"/>" style="display: none">应还日期：<s:property value="yhDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                                        <p id="returnDate<s:property value="borrowID"/>" style="display: none">归还日期：<s:property value="returnDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><br>
                                        <p id="shDate<s:property value="borrowID"/>" style="display: none">完成日期：<s:property value="shDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                                        <p id="returnAddr<s:property value="borrowID"/>" style="display: none">归还地址：<s:property value="returnAddress"/></p><br>
                                        <a href="#" id="confirmBtn<s:property value="borrowID"/>" class="add-cart item_add" onclick="confirmReceipt(<s:property value="borrowID"/>)">确认收货</a>
                                    </s:elseif>
                                    <s:elseif test="status=='NOTRETURNED'">
                                        <s:if test="delayCount==null">
                                            <p id="orderDate<s:property value="borrowID"/>">下单日期：<s:property value="orderDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                                            <p id="payDate<s:property value="borrowID"/>">付款日期：<s:property value="payDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><br>
                                            <p id="fhDate<s:property value="borrowID"/>">发货日期：<s:property value="fhDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                                            <p id="borrowDate<s:property value="borrowID"/>">收货日期：<s:property value="borrowDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><br>
                                            <p id="yhdate<s:property value="borrowID"/>">应还日期：<s:property value="yhDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                                            <p id="returnDate<s:property value="borrowID"/>" style="display: none">归还日期：<s:property value="returnDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><br>
                                            <p id="shDate<s:property value="borrowID"/>" style="display: none">完成日期：<s:property value="shDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                                            <p id="returnAddr<s:property value="borrowID"/>">归还地址：<s:property value="returnAddress"/></p><br>

                                            <a href="#" id="returnBtn<s:property value="borrowID"/>" class="add-cart item_add" onclick="showReturnBook(<s:property value="borrowID"/>)">归还</a>
                                            <a href="#" id="delayBtn<s:property value="borrowID"/>" class="add-cart item_add" onclick="delayBook(<s:property value="borrowID"/>)">续借</a>
                                            <form id="tracking<s:property value="borrowID"/>" style="display: none">
                                                <input type="text" id="trackingNO<s:property value="borrowID"/>" name="trackingNO1"/>
                                                <a href="#" class="add-cart item_add" onclick="returnBook(<s:property value="borrowID"/>)">提交</a>
                                            </form>
                                        </s:if>
                                        <s:elseif test="delayCount==1">
                                            <p id="orderDate<s:property value="borrowID"/>">下单日期：<s:property value="orderDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                                            <p id="payDate<s:property value="borrowID"/>">付款日期：<s:property value="payDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><br>
                                            <p id="fhDate<s:property value="borrowID"/>">发货日期：<s:property value="fhDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                                            <p id="borrowDate<s:property value="borrowID"/>">收货日期：<s:property value="borrowDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><br>
                                            <p id="yhdate<s:property value="borrowID"/>">应还日期：<s:property value="yhDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                                            <p id="returnDate<s:property value="borrowID"/>" style="display: none">归还日期：<s:property value="returnDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><br>
                                            <p id="shDate<s:property value="borrowID"/>" style="display: none">完成日期：<s:property value="shDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                                            <p id="returnAddr<s:property value="borrowID"/>">归还地址：<s:property value="returnAddress"/></p><br>

                                            <a href="#" id="returnBtn<s:property value="bookID"/>" class="add-cart item_add" onclick="returnBook(<s:property value="borrowID"/>)">归还</a>
                                        </s:elseif>
                                    </s:elseif>
                                    <s:elseif test="status=='RETURNED'">
                                        <p id="orderDate<s:property value="borrowID"/>">下单日期：<s:property value="orderDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                                        <p id="payDate<s:property value="borrowID"/>">付款日期：<s:property value="payDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><br>
                                        <p id="fhDate<s:property value="borrowID"/>">发货日期：<s:property value="fhDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                                        <p id="borrowDate<s:property value="borrowID"/>">收货日期：<s:property value="borrowDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><br>
                                        <p id="yhdate<s:property value="borrowID"/>">应还日期：<s:property value="yhDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                                        <p id="returnDate<s:property value="borrowID"/>">归还日期：<s:property value="returnDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><br>
                                        <p id="shDate<s:property value="borrowID"/>" style="display: none">完成日期：<s:property value="shDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                                        <p id="returnAddr<s:property value="borrowID"/>">归还地址：<s:property value="returnAddress"/></p><br>
                                    </s:elseif>
                                    <div class="clearfix"></div>
                                </div>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                </s:iterator>

        <!-- 以下迭代显示已归还的图书 -->
        <s:iterator value="#borrowHistoryBook" status="map_state">
            <div id="borrowHistory<s:property value="borrowID"/>" class="cart-header">
                <div class="cart-sec simpleCart_shelfItem">
                    <div class="cart-item cyc">
                        <img src="<%=path%>/imageAction/showImage?imageID=<s:property value="imageID"/>" class="img-responsive" alt="">
                    </div>
                    <div class="cart-item-info">
                        <h4>
                            <a href="<%=path%>/bookAction/showBookProfile?bookID=<s:property value="bookID"/>">
                                书名：<s:property value="bookName"/></a><br>
                            <span>当前状态：借阅已完成</span>
                        </h4>
                        <ul class="qty">
                            <li><p>作者：<s:property value="author"/></p></li>
                            <li><p>分类：<s:property value="category1"/>&nbsp;&nbsp;<s:property value="category2"/></p></li>
                            <li><p>借阅积分：<s:property value="borrowPrice"/></p></li>
                            <li><p>出借人：</p></li>
                        </ul>
                        <div class="delivery">
                            <p id="orderDate<s:property value="borrowID"/>">下单日期：<s:property value="orderDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                            <p id="payDate<s:property value="borrowID"/>">付款日期：<s:property value="payDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><br>
                            <p id="fhDate<s:property value="borrowID"/>">发货日期：<s:property value="fhDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                            <p id="borrowDate<s:property value="borrowID"/>">收货日期：<s:property value="borrowDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><br>
                            <p id="yhdate<s:property value="borrowID"/>">应还日期：<s:property value="yhDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                            <p id="returnDate<s:property value="borrowID"/>">归还日期：<s:property value="returnDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><br>
                            <p id="shDate<s:property value="borrowID"/>">完成日期：<s:property value="shDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><br>
                            <p id="returnAddr<s:property value="borrowID"/>">归还地址：<s:property value="returnAddress"/></p><br>

                            <s:if test="bookComment==null">
                            <a href="#" id="commentBtn<s:property value="bookID"/>" class="add-cart item_add" onclick="commentBook(<s:property value="borrowID"/>)">图书评论</a>
                            </s:if>
                            <s:if test="comment1==null">
                            <a href="#" id="creditRatingBtn<s:property value="bookID"/>" class="add-cart item_add" onclick="creditRating(<s:property value="borrowID"/>)">信用评价</a>
                            </s:if>
                            <form id="commentForm<s:property value="borrowID"/>" style="display: none" action="<%=path%>/commentAction/commentBook" method="post">
                                <input type="hidden" id="bookID<s:property value="bookID"/>" name="bookID" value="<s:property value="bookID"/>"/>
                                <input type="hidden" id="borrowID<s:property value="borrowID"/>" name="borrowID" value="<s:property value="borrowID"/>"/>
                                <textarea id="comment<s:property value="borrowID"/>" name="comment" class="form-control" rows="3"></textarea>
                                <a href="#" class="add-cart item_add" onclick="submitComment(<s:property value="borrowID"/>)">提交</a>
                            </form>
                            <form id="creditRatingForm<s:property value="borrowID"/>" style="display: none" action="<%=path%>/commentAction/honestyRatingWhenBorrow">
                                <input type="hidden" name="borrowID" value="<s:property value="borrowID"/>"/>
                                <select name="creditRating" class="form-control form-control-noNewline">
                                    <option value="-1">差评</option>
                                    <option value="0">中评</option>
                                    <option value="1">好评</option>
                                </select>
                                <a href="#" class="add-cart item_add" onclick="submitRating(<s:property value="borrowID"/>)">评价</a>
                                <div id="comment_status2"></div>
                            </form>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div><hr>
        </s:iterator>

        <!-- 测试样式 -->
                <div id="borrowBook<s:property value="bookID"/>" class="cart-header">
                    <div class="cart-sec simpleCart_shelfItem">
                        <div class="cart-item cyc">
                            <img src="<%=path%>/imageAction/showImage?imageID=<s:property value="imageID"/>" class="img-responsive" alt="">
                        </div>
                        <div class="cart-item-info">
                            <h4>
                                <a href="<%=path%>/bookAction/showBookProfile?bookID=<s:property value="bookID"/>">
                                    书名：<s:property value="bookName"/></a><br>
                                <span>ISBN:<s:property value="isbn"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：买家未支付</span>
                            </h4>
                            <ul class="qty">
                                <li><p>作者：<s:property value="author"/></p></li>
                                <li><p>分类：<s:property value="category1"/></p></li>
                                <li><p>借阅积分：<s:property value="borrowPrice"/></p></li>
                                <li><p>出借人：</p></li>
                            </ul>
                            <div class="delivery">
                                <p id="yhdate<s:property value="bookID"/>">下单日期：2017-07-17 20：00：000&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><br>
                                <a href="#" id="returnBtn<s:property value="bookID"/>" class="add-cart item_add" onclick="returnBook(<s:property value="bookID"/>)">支付</a>
                                <div class="clearfix"></div>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </div><hr>

        <div id="borrowBook<s:property value="bookID"/>" class="cart-header">
            <div class="cart-sec simpleCart_shelfItem">
                <div class="cart-item cyc">
                    <img src="<%=path%>/imageAction/showImage?imageID=<s:property value="imageID"/>" class="img-responsive" alt="">
                </div>
                <div class="cart-item-info">
                    <h4>
                        <a href="<%=path%>/bookAction/showBookProfile?bookID=<s:property value="bookID"/>">
                            书名：<s:property value="bookName"/></a><br>
                        <span>ISBN:<s:property value="isbn"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：卖家未发货</span>
                    </h4>
                    <ul class="qty">
                        <li><p>作者：<s:property value="author"/></p></li>
                        <li><p>分类：<s:property value="category1"/></p></li>
                        <li><p>借阅积分：<s:property value="borrowPrice"/></p></li>
                        <li><p>出借人：</p></li>
                    </ul>
                    <div class="delivery">
                        <p id="yhdate<s:property value="bookID"/>">下单日期：2017-07-17 20：00：000&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                        <p id="yhdate<s:property value="bookID"/>">付款日期：2017-07-17 20：00：000&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><br>
                        <div class="clearfix"></div>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div><hr>

        <div id="borrowBook<s:property value="bookID"/>" class="cart-header">
            <div class="cart-sec simpleCart_shelfItem">
                <div class="cart-item cyc">
                    <img src="<%=path%>/imageAction/showImage?imageID=<s:property value="imageID"/>" class="img-responsive" alt="">
                </div>
                <div class="cart-item-info">
                    <h4>
                        <a href="<%=path%>/bookAction/showBookProfile?bookID=<s:property value="bookID"/>">
                            书名：<s:property value="bookName"/></a><br>
                        <span>ISBN:<s:property value="isbn"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：卖家已发货</span>
                    </h4>
                    <ul class="qty">
                        <li><p>作者：<s:property value="author"/></p></li>
                        <li><p>分类：<s:property value="category1"/></p></li>
                        <li><p>借阅积分：<s:property value="borrowPrice"/></p></li>
                        <li><p>出借人：</p></li>
                    </ul>
                    <div class="delivery">
                        <p id="yhdate<s:property value="bookID"/>">下单日期：2017-07-17 20：00：000&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                        <p id="yhdate<s:property value="bookID"/>">付款日期：2017-07-17 20：00：000&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><br>
                        <p id="yhdate<s:property value="bookID"/>">发货日期：2017-07-17 20：00：000&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                        <p id="yhdate<s:property value="bookID"/>" style="display: none">收货日期：2017-07-17 20：00：000&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><br>
                        <p id="yhdate<s:property value="bookID"/>" style="display: none">应还日期：2017-07-17 20：00：000&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                        <p id="yhdate<s:property value="bookID"/>" style="display: none">归还日期：2017-07-17 20：00：000&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><br>
                        <p id="yhdate<s:property value="bookID"/>" style="display: none">完成日期：2017-07-17 20：00：000&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                        <p id="returnAddr<s:property value="borrowID"/>" style="display: none">归还地址：东川路800号</p><br>
                        <a href="#" id="returnBtn<s:property value="bookID"/>" class="add-cart item_add" onclick="returnBook(<s:property value="bookID"/>)">确认收货</a>
                        <div class="clearfix"></div>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div><hr>

        <div id="borrowBook<s:property value="bookID"/>" class="cart-header">
            <div class="cart-sec simpleCart_shelfItem">
                <div class="cart-item cyc">
                    <img src="<%=path%>/imageAction/showImage?imageID=<s:property value="imageID"/>" class="img-responsive" alt="">
                </div>
                <div class="cart-item-info">
                    <h4>
                        <a href="<%=path%>/bookAction/showBookProfile?bookID=<s:property value="bookID"/>">
                            书名：<s:property value="bookName"/></a><br>
                        <span>ISBN:<s:property value="isbn"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：买家未归还</span>
                    </h4>
                    <ul class="qty">
                        <li><p>作者：<s:property value="author"/></p></li>
                        <li><p>分类：<s:property value="category1"/></p></li>
                        <li><p>借阅积分：<s:property value="borrowPrice"/></p></li>
                        <li><p>出借人：</p></li>
                    </ul>
                    <div class="delivery">
                        <p id="yhdate<s:property value="bookID"/>">下单日期：2017-07-17 20：00：000&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                        <p id="yhdate<s:property value="bookID"/>">付款日期：2017-07-17 20：00：000&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><br>
                        <p id="yhdate<s:property value="bookID"/>">发货日期：2017-07-17 20：00：000&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                        <p id="yhdate<s:property value="bookID"/>">收货日期：2017-07-17 20：00：000&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><br>
                        <p id="yhdate<s:property value="bookID"/>">应还日期：2017-07-17 20：00：000&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><br>
                        <a href="#" id="returnBtn<s:property value="bookID"/>" class="add-cart item_add" onclick="returnBook(<s:property value="bookID"/>)">归还</a>
                        <a href="#" id="delayBtn<s:property value="bookID"/>" class="add-cart item_add" onclick="delayBook(<s:property value="bookID"/>)">续借</a>
                        <div class="clearfix"></div>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div><hr>

        <div id="borrowBook<s:property value="bookID"/>" class="cart-header">
            <div class="cart-sec simpleCart_shelfItem">
                <div class="cart-item cyc">
                    <img src="<%=path%>/imageAction/showImage?imageID=<s:property value="imageID"/>" class="img-responsive" alt="">
                </div>
                <div class="cart-item-info">
                    <h4>
                        <a href="<%=path%>/bookAction/showBookProfile?bookID=<s:property value="bookID"/>">
                            书名：<s:property value="bookName"/></a><br>
                        <span>ISBN:<s:property value="isbn"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：买家已归还,待卖家确认</span>
                    </h4>
                    <ul class="qty">
                        <li><p>作者：<s:property value="author"/></p></li>
                        <li><p>分类：<s:property value="category1"/></p></li>
                        <li><p>借阅积分：<s:property value="borrowPrice"/></p></li>
                        <li><p>出借人：</p></li>
                    </ul>
                    <div class="delivery">
                        <p id="yhdate<s:property value="bookID"/>">下单日期：2017-07-17 20：00：000&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                        <p id="yhdate<s:property value="bookID"/>">付款日期：2017-07-17 20：00：000&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><br>
                        <p id="yhdate<s:property value="bookID"/>">发货日期：2017-07-17 20：00：000&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                        <p id="yhdate<s:property value="bookID"/>">收货日期：2017-07-17 20：00：000&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><br>
                        <p id="yhdate<s:property value="bookID"/>">应还日期：2017-07-17 20：00：000&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                        <p id="yhdate<s:property value="bookID"/>">归还日期：2017-07-17 20：00：000&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><br>
                        <p id="yhdate<s:property value="bookID"/>" style="display: none">完成日期：2017-07-17 20：00：000&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                        <p id="returnAddr<s:property value="borrowID"/>">归还地址：东川路800号</p><br>
                        <div class="clearfix"></div>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div><hr>

                <div id="borrowBook<s:property value="bookID"/>" class="cart-header">
                    <div class="cart-sec simpleCart_shelfItem">
                        <div class="cart-item cyc">
                            <img src="<%=path%>/imageAction/showImage?imageID=<s:property value="imageID"/>" class="img-responsive" alt="">
                        </div>
                        <div class="cart-item-info">
                            <h4>
                                <a href="<%=path%>/bookAction/showBookProfile?bookID=<s:property value="bookID"/>">
                                    书名：<s:property value="bookName"/></a><br>
                                <span>ISBN:<s:property value="isbn"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：借阅已完成</span>
                            </h4>
                            <ul class="qty">
                                <li><p>作者：<s:property value="author"/></p></li>
                                <li><p>分类：<s:property value="category1"/></p></li>
                                <li><p>借阅积分：<s:property value="borrowPrice"/></p></li>
                                <li><p>出借人：</p></li>
                            </ul>
                            <div class="delivery">
                                <p id="yhdate<s:property value="bookID"/>">下单日期：2017-07-17 20：00：000&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                                <p id="yhdate<s:property value="bookID"/>">付款日期：2017-07-17 20：00：000&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><br>
                                <p id="yhdate<s:property value="bookID"/>">发货日期：2017-07-17 20：00：000&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                                <p id="yhdate<s:property value="bookID"/>">收货日期：2017-07-17 20：00：000&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><br>
                                <p id="yhdate<s:property value="bookID"/>">应还日期：2017-07-17 20：00：000&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                                <p id="yhdate<s:property value="bookID"/>">归还日期：2017-07-17 20：00：000&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><br>
                                <p id="yhdate<s:property value="bookID"/>">完成日期：2017-07-17 20：00：000&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                                <p id="returnAddr<s:property value="borrowID"/>">归还地址：东川路800号</p><br>
                                <a href="#" id="returnBtn<s:property value="bookID"/>" class="add-cart item_add" onclick="returnBook(<s:property value="bookID"/>)">图书评论</a>
                                <a href="#" id="delayBtn<s:property value="bookID"/>" class="add-cart item_add" onclick="delayBook(<s:property value="bookID"/>)">信用评价</a>
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

