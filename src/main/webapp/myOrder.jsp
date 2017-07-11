<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="header.jsp"%>
<html>
<head>
    <title>myOrder</title>
    <style>
        #wrapper {
            background-color: #5D4B33;
            margin-right:70%;
        }
        @media ( min-width :768px) {
            .sidebar {
                z-index: 1;
                position: absolute;
                width:100%;
                margin-top: 51px;
                background-color: #5D4B33;
            }
        }
        #cartinfo{
            margin-top: 51px;
            margin-left:30%;
            margin-right:10%;
        }
    </style>
</head>
<body>
<div id="wrapper">
    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-static-top" role="navigation"
         style="margin-bottom: 0">

        <div class="nav navbar-header">
            <a class="navbar-brand" href="#">我的账户</a>
        </div>
        <div class="navbar-default sidebar" role="navigation">
            <div class="sidebar-nav navbar-collapse">
                <ul class="nav" id="side-menu">
                    <li><a href="#" style="color: #FFFFFF"><i class="fa fa-user fa-fw"></i>
                        全部图书</a></li>
                    <li><a href="#" style="color: #FFFFFF"><i class="fa fa-book fa-fw"></i>
                        个人信息</a></li>
                    <li><a href="#" style="color: #FFFFFF" ><i
                            class="fa fa-reorder fa-fw"></i>我的发布</a></li>
                    <li><a href="<%=path%>/borrowAction/showMyBorrow" style="color: #FFFFFF"><i
                            class="fa fa-table fa-fw"></i> 我的借阅</a></li>
                    <li><a href="#" style="color: #FFFFFF"><i class="fa fa-user fa-fw"></i>
                        我的交换</a></li>
                    <li><a href="#" style="color: #FFFFFF" class="active"><i class="fa fa-user fa-fw"></i>
                        我的购买</a></li>
                </ul>
            </div>
            <!-- /.sidebar-collapse -->
        </div>
        <!-- /.navbar-static-side --> </nav>
</div>
<br>
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
</body>
</html>
