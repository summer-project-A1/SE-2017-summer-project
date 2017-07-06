<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@include file="header.jsp"%>
<html>
<head>
    <title>myBorrow</title>
</head>
<body>
<nav class="navbar navbar-default navbar-static-top" role="navigation"
     style="margin-bottom: 0">
    <div class="navbar-default sidebar" role="navigation">
        <div class="sidebar-nav navbar-collapse">
            <div class="span2  col-xs-12 col-sm-3 col-md-2">
                <div class="col-md-4 single-grid1">
                    <h2>Account</h2>
                    <ul>
                        <li><a href="myProfile.jsp">个人信息</a></li>
                        <li><a href="myRelease.jsp">我的发布</a></li>
                        <li><a href="myBorrow.jsp">我的借阅</a></li>
                        <li><a href="myExchange.jsp">我的交换</a></li>
                        <li><a href="myOrder.jsp">我的购买</a></li>
                    </ul>
                </div>
                <div class="clearfix"> </div>
            </div>
        </div>
    </div>
</nav>
<div class="span2  col-xs-12 col-sm-3 col-md-2">
    <h2>我的账户</h2>
    <ul class="nav nav-pills nav-stacked">
        <li class="active"><a href="#">Home</a></li>
        <li><a href="#">Tutorials</a></li>
        <li><a href="#">Practice Editor </a></li>
        <li><a href="#">Gallery</a></li>
        <li><a href="#">Contact</a></li>
    </ul>
</div>
<div class="cart-item">
    <div class="container">
        <div id="bookID" class="cart-header">
            <div class="cart-sec simpleCart_shelfItem">
                <div class="cart-item cyc">
                    <img src="<%=path%>/images/m1.png" class="img-responsive" alt="">
                </div>
                <div class="cart-item-info">
                    <h3>
                        <a href="<%=path%>/bookAction/showBookProfile?bookID=7">
                            书名：书名</a><br>
                        <span>ISBN: 8888</span>
                    </h3>
                    <ul class="qty">
                        <li><p>作者：作者</p></li>
                        <li><p>分类：分类</p></li>
                    </ul>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>

</div>
</div>
</body>
</html>
