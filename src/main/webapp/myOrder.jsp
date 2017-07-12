<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="header.jsp"%>
<html>
<head>
    <title>myOrder</title>
    <style>

        @media ( min-width :768px) {
        }
        #cartinfo{
            margin-top: 51px;
            margin-left:30%;
            margin-right:10%;
        }
    </style>
</head>
<body>
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
        <div id="bookID1" class="cart-header">
            <div class="cart-sec simpleCart_shelfItem">
                <div class="cart-item-info">
                    <h4>
                        <a href="<%=path%>/">订单号:1580021000037&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：未支付</a>
                    </h4><br>
                    <ul class="qty">
                        <li><p>下单时间：2017-6-10   20:42:48</p></li>
                        <li><p>总积分：1024</p></li>
                    </ul>
                    <div class="delivery">
                        <p>收货人：黄政&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                        <p>收货地址：东川路800号</p>
                        <div class="clearfix"></div>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div><hr>
        <div id="bookID2" class="cart-header">
            <div class="cart-sec simpleCart_shelfItem">
                <div class="cart-item-info">
                    <h4>
                        <a href="<%=path%>/">订单号:1580021000037&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：已完成</a>
                    </h4><br>
                    <ul class="qty">
                        <li><p>下单时间：2017-6-10   20:42:48</p></li>
                        <li><p>总积分：524</p></li>
                    </ul>
                    <div class="delivery">
                        <p>收货人：沈斯杰&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                        <p>收货地址：东川路800号</p>
                        <div class="clearfix"></div>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div><hr>
        <div id="bookID1" class="cart-header">
            <div class="cart-sec simpleCart_shelfItem">
                <div class="cart-item-info">
                    <h4>
                        <a href="<%=path%>/">订单号:1580021000037&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：已取消</a>
                    </h4><br>
                    <ul class="qty">
                        <li><p>下单时间：2017-6-10   20:42:48</p></li>
                        <li><p>总积分：10240</p></li>
                    </ul>
                    <div class="delivery">
                        <p>收货人：高仓靖博&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                        <p>收货地址：东川路800号</p>
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
