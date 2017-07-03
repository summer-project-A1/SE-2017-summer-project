<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>

<html>
<head>
    <title>header</title>

    <!-- Custom Theme files -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="keywords" content="" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!-- //Custom Theme files -->
    <link href="<%=path%>/css/bootstrap.css" type="text/css" rel="stylesheet" media="all">
    <link href="<%=path%>/css/style.css" type="text/css" rel="stylesheet" media="all">
    <link href="<%=path%>/css/form.css" rel="stylesheet" type="text/css" media="all" />
    <!-- js -->
    <script type="text/javascript">
        const base_url = '<%= basePath%>';
    </script>
    <script src="<%=path%>/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=path%>/js/bootstrap-3.1.1.min.js"></script>
    <script type="text/javascript" src="<%=path%>/js/jquery.validate.min.js"></script>
    <!-- //js -->
    <!-- cart -->
    <script src="<%=path%>/js/simpleCart.min.js"> </script>
    <!-- cart -->
    <!-- the jScrollPane script -->
    <script type="text/javascript" src="<%=path%>/js/jquery.jscrollpane.min.js"></script>

    <!-- //the jScrollPane script -->
    <script type="text/javascript" src="<%=path%>/js/jquery.mousewheel.js"></script>
    <!-- the mousewheel plugin -->

    <script>
        $(document).ready(function() {
            $("#email").focus();
            $("#email").keyup(function(){
                var email = $("#email").val();
                if(email.indexOf("@") < 0){
                    $("#status1").html("<span style='color:red'>请输入正确的邮件地址</span>");
                }else{
                    $("#status1").html("<span></span>");
                }
            });
            $("#email").blur(function(){
                var email = $("#email").val();
                if(email.indexOf("@") < 0){
                    $("#status1").html("<span style='color:red'>请输入正确的邮件地址</span>");
                }else{
                    $("#status1").html("<span></span>");
                }
            });
            $("#password").focus();
            $("#password").keyup(function(){
                var password = $("#password").val();
                if(password.length < 1){
                    $("#status2").html("<span style='color:red'>请输入密码</span>");
                }else{
                    $("#status2").html("<span></span>");
                }
            });
            $("#password").blur(function(){
                var password = $("#password").val();
                if(password.length < 1){
                    $("#status2").html("<span style='color:red'>请输入密码</span>");
                }else{
                    $("#status2").html("<span></span>");
                }
            });
            $("#login").click(function () {
                var params = $("#loginForm").serialize();
                $.ajax({
                    url: "<%=path%>/userAction/login",
                    type: "post",
                    data: params,
                    dataType: "text",
                    success: function (data) {
                        var response = eval("("+data+")");
                        if(response.result == false){
                            var msg = response.message;
                            alert(msg);
                        }
                        if(response.result == true){
                            var logout = "<label><a href='<%=path%>/userAction/logout'>"+"退出登录"+"</a><label>";
                            var replace = "<label>"+"欢迎您!"+response.email+"</label><br>"+logout;
                            $('#loginForm').html(replace);
                            alert(response.message);
                        }
                    }
                });
            });
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
                <h1 class="navbar-brand"><a href="<%=path%>/index.jsp">BookShare</a></h1>
            </div>
            <!--navbar-header-->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li><a href="index.jsp" class="active">首页</a></li>
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
                <%if(session.getAttribute("userinfo") == null){ %>
                <div id="loginBox">
                    <form id="loginForm">
                        <fieldset id="body">
                            <fieldset>
                                <label for="email">注册邮箱</label>
                                <input type="email" name="email" id="email" class="required email"><div id="status1"></div>
                            </fieldset>
                            <fieldset>
                                <label for="password">密码</label>
                                <input type="password" name="password" id="password" class="required"><div id="status2"></div>
                            </fieldset>
                            <input type="button" id="login" value="登录">
                        </fieldset>
                        <p>新用户 ? <a class="sign" href="<%=basePath%>signup.jsp">点击注册</a> <span><a href="#">忘记密码?</a></span></p>
                    </form>
                </div>
                   <%
                }else{ %>
                <div id="loginBox">
                    <form id="loginForm2">
                            <label>欢迎您！${sessionScope.userinfo.email}</label><br>
                            <label><a href="logout.action">退出登录</a></label>
                    </form>
                </div>
                <%
                } %>
            </div>
            <div class="header-right cart">
                <a href="cart.jsp"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span></a>
                <div class="cart-box">
                    <%
                        List l = (List)session.getAttribute("cart");
                        if(l != null) {
                            Map map = new HashMap();
                            for (int i = 0; i < l.size(); i++) {
                                map = (Map) l.get(i);
                                Set keyset = map.keySet();
                                Object[] obj = keyset.toArray();
                                String bookid = map.get(obj[2]).toString();
                                String bookname = map.get(obj[1]).toString();
                                String amount = map.get(obj[0]).toString();
                            %>
                    <h4>
                        <span>书名：<%=bookname%></span>    <span id="simpleCart_quantity" >数量：<%=amount%></span>
                    </h4>
                            <%
                            }
                        }else{
                        %>
                    <h4><span>购物车为空</span></h4>
                    <%}%>
                    <p><a href="#" class="simpleCart_empty">清空购物车</a></p>
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


