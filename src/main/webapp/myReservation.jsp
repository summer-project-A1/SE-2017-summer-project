<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@include file="global.jsp"%>
<html>
<head>
    <title>myReservation</title>
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
    <style>
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
<s:action name="header" executeResult="true" namespace="/"/><!-- home page -->

<script>
    function cancelReservation(reserveID){
        $.ajax({
            url:'<%=path%>/reserveAction/cancelReservation',
            type:'POST',
            data:{'reserveID':reserveID},
            success:function(msg){
                if(msg.success){
                    showTip('取消成功！','success');
                    $("#"+reserveID).remove();
                }
            },
            error:function(xhr,status,error){
                alert('status='+status+',error='+error);
            }
        })
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

        <h3 align="center">我的预约</h3>
        <div id="tip"></div>
        <div id="cartinfo" class="cart-item">
            <div class="container">

                <!-- 迭代器显示订单信息 -->
                <s:iterator value="#reservationList" status="st">
                    <div id="<s:property value="reserveID"/>" class="cart-header">
                        <div class="cart-sec simpleCart_shelfItem">
                            <div class="cart-item cyc">
                                <img src="<%=path%>/imageAction/showImage?imageID=<s:property value="imageID"/>" class="img-responsive" alt="">
                            </div>
                            <div class="cart-item-info">
                                <h4>
                                    <s:if test="bookStatus=='BORROWED'">
                                        <p id="status<s:property value="reserveID"/>"><a href="<%=path%>/bookAction/showBookProfile?bookID=<s:property value="bookID"/>">
                                            书名：<s:property value="bookName"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：此书正被借阅</p>
                                    </s:if>
                                    <s:elseif test="bookStatus=='IDLE'">
                                        <p id="status<s:property value="reserveID"/>"><a href="<%=path%>/bookAction/showBookProfile?bookID=<s:property value="bookID"/>">
                                            书名：<s:property value="bookName"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：此书正空闲</p>
                                    </s:elseif>
                                </h4><br>
                                <ul class="qty">
                                    <li><p>出版社：<s:property value="press"/></p></li>
                                    <li><p>作者：<s:property value="author"/></p></li>
                                    <li><p>借阅积分：<s:property value="borrowCredit"/></p></li>
                                </ul>
                                <div class="delivery">
                                    <p>拥有者：<s:property value="ownerEmail"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                                    <p>当前排队人数：<s:property value="reserveAmt"/>人</p><br>
                                    <p>预约截止时间：<s:property value="due"/></p>
                                    <a href="#" class="add-cart item_add" onclick="cancelReservation(<s:property value="reserveID"/>)">取消预约</a>
                                    <div class="clearfix"></div>
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

<jsp:include page="footer.jsp"/>
</body>
</html>
