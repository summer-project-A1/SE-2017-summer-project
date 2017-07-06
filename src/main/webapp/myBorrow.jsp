<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@include file="header.jsp"%>
<html>
<head>
    <title>myBorrow</title>
</head>
<body>
<br>
<br>
<div class="span2 col-md-3 single-grid1">
    <h3>我的账户</h3><br>
    <ul>
        <li><a href="#">个人信息</a></li>
        <li><a href="#">我的发布</a></li>
        <li><a href="#">我的借阅</a></li>
        <li><a href="#">我的交换</a></li>
        <li><a href="#">我的购买</a></li>
    </ul>
</div>
<h3 align="center">我的借阅</h3>
<br>
<div class="cart-item">
    <div class="container">
        <div id="bookID" class="cart-header">
            <div class="cart-sec simpleCart_shelfItem">
                <div class="cart-item cyc">
                    <img src="<%=path%>/images/m1.png" class="img-responsive" alt="">
                </div>
                <div class="cart-item-info">
                    <h4>
                        <a href="<%=path%>/bookAction/showBookProfile?bookID=7">
                            书名：书名</a><br>
                        <span>ISBN: 8888</span>
                    </h4>
                    <ul class="qty">
                        <li><p>作者：作者</p></li>
                        <li><p>分类：分类</p></li>
                    </ul>
                    <div class="delivery">
                        <p>应还日期：应换日期&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                        <p>归还日期：归还日期</p>
                        <div class="clearfix"></div>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
        <div id="bookID2" class="cart-header">
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
