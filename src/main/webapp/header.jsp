<%--
  Created by IntelliJ IDEA.
  User: lvjiawei
  Date: 2017/6/29
  Time: 上午11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>header</title>

    <!-- Custom Theme files -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!-- //Custom Theme files -->
    <link href="static/css/bootstrap.css" type="text/css" rel="stylesheet" media="all">
    <link href="static/css/style.css" type="text/css" rel="stylesheet" media="all">
    <!-- js -->
    <script src="static/js/jquery.min.js"></script>
    <script type="text/javascript" src="static/js/bootstrap-3.1.1.min.js"></script>
    <script type="text/javascript" src="static/js/jquery.min.js"></script>
    <script type="text/javascript" src="static/js/jquery.validate.min.js"></script>
    <!-- //js -->
    <!-- cart -->
    <script src="static/js/simpleCart.min.js"> </script>
    <!-- cart -->

    <script>
        $().ready(function() {
            $("#loginForm").validate();
        });
    </script>
</head>
<body>
<div class="header">
    <div class="container">
        <nav class="navbar navbar-default" role="navigation">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <h1 class="navbar-brand"><a  href="index.html">BookShare</a></h1>
            </div>
            <!--navbar-header-->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li><a href="index.html" class="active">首页</a></li>
                    <li class="dropdown grid">
                        <a href="#" class="dropdown-toggle list1" data-toggle="dropdown">寻找图书<b class="caret"></b></a>
                        <ul class="dropdown-menu multi-column columns-4">
                            <div class="row">
                                <div class="col-sm-3">
                                    <h4>小说</h4>
                                    <ul class="multi-column-dropdown">
                                        <li><a class="list" href="products.html">科幻小说</a></li>
                                        <li><a class="list" href="products.html">爱情小说</a></li>
                                        <li><a class="list" href="products.html">武侠小说</a></li>
                                        <li><a class="list" href="products.html">悬疑小说</a></li>
                                        <li><a class="list" href="products.html">奇幻小说</a></li>
                                        <li><a class="list" href="booklist.html">全部图书</a></li>
                                    </ul>
                                </div>
                                <div class="col-sm-3">
                                    <h4>文学</h4>
                                    <ul class="multi-column-dropdown">
                                        <li><a class="list" href="products.html">影视文学</a></li>
                                        <li><a class="list" href="products.html">散文随笔</a></li>
                                        <li><a class="list" href="products.html">诗歌词曲</a></li>
                                        <li><a class="list" href="products.html">纪实文学</a></li>
                                        <li><a class="list" href="products.html">民间文学</a></li>
                                    </ul>
                                </div>
                                <div class="col-sm-3">
                                    <h4>管理</h4>
                                    <ul class="multi-column-dropdown">
                                        <li><a class="list" href="products.html">管理学</a></li>
                                        <li><a class="list" href="products.html">财务管理</a></li>
                                        <li><a class="list" href="products.html">企业管理</a></li>
                                        <li><a class="list" href="products.html">人力资源管理</a></li>
                                        <li><a class="list" href="products.html">工商管理</a></li>
                                    </ul>
                                </div>
                                <div class="col-sm-3">
                                    <h4>教辅</h4>
                                    <ul class="multi-column-dropdown">
                                        <li><a class="list" href="products.html">小学</a></li>
                                        <li><a class="list" href="products.html">初中</a></li>
                                        <li><a class="list" href="products.html">中考</a></li>
                                        <li><a class="list" href="products.html">高考</a></li>
                                        <li><a class="list" href="products.html">成人教育</a></li>
                                    </ul>
                                </div>
                            </div>
                        </ul>
                    </li>
                    <li class="dropdown grid"><a href="bookrelease.html" class="" >发布图书</a>
                    </li>
                    <li><a href="how.html" class="" >如何分享</a>
                    </li>
                </ul>
                <!--/.navbar-collapse-->
            </div>
            <!--//navbar-header-->
        </nav>
        <div class="header-info">
            <div class="header-right search-box">
                <a href="#"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></a>
                <div class="search">
                    <form class="navbar-form">
                        <input type="text" class="form-control">
                        <button type="submit" class="btn btn-default" aria-label="Left Align">
                            搜索
                        </button>
                    </form>
                </div>
            </div>
            <div class="header-right login">
                <a href="myaccount.html"><span class="glyphicon glyphicon-user" aria-hidden="true"></span></a>
                <div id="loginBox">
                    <form id="loginForm" action="login" method="post">
                        <fieldset id="body">
                            <fieldset>
                                <label for="email">注册邮箱</label>
                                <input type="email" name="email" id="email" class="required email">
                            </fieldset>
                            <fieldset>
                                <label for="password">密码</label>
                                <input type="password" name="password" id="password">
                            </fieldset>
                            <input type="submit" id="login" value="登录">
                        </fieldset>
                        <p>新用户 ? <a class="sign" href="signup.html">点击注册</a> <span><a href="#">忘记密码?</a></span></p>
                    </form>
                </div>
            </div>
            <div class="header-right cart">
                <a href="cart.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span></a>
                <div class="cart-box">
                    <h4><a href="checkout.html">
                        <span class="simpleCart_total"> $0.00 </span> (<span id="simpleCart_quantity" class="simpleCart_quantity"> 0 </span>)
                    </a></h4>
                    <p><a href="javascript:;" class="simpleCart_empty">清空购物车</a></p>
                    <div class="clearfix"> </div>
                </div>
            </div>
            <div class="clearfix"> </div>
        </div>
        <div class="clearfix"> </div>
    </div>
</div>
</body>
</html>
