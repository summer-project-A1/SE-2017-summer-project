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

    <!-- Custom Theme files -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="keywords" content="" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!-- //Custom Theme files -->
    <link href="<%=path%>/css/bootstrap.css" type="text/css" rel="stylesheet" media="all">
    <link href="<%=path%>/css/style.css" type="text/css" rel="stylesheet" media="all">
    <link href="<%=path%>/css/form.css" rel="stylesheet" type="text/css" media="all" />
    <link href="<%=path%>/css/flexslider.css" rel="stylesheet"  type="text/css" media="screen" />
    <link href="<%=path%>/css/fileinput.css" rel="stylesheet" type="text/css"/>
    <link href="<%=path%>/css/fileinput.min.css" rel="stylesheet" type="text/css"/>
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

        function showTip(tip,type){
            var $tip = $('#tip');
            $tip.attr('class', 'alert alert-' + type).text(tip).css('margin-left', - $tip.outerWidth() / 2).fadeIn(500).delay(1000).fadeOut(500);
        }


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
                    url: "<%=path%>/authAction/login",
                    type: "post",
                    data: params,
                    dataType: "text",
                    success: function (data) {
                        var response = eval("("+data+")");
                        if(response.result == false){
                            showTip("登陆失败！","danger");
                            //alert(msg);
                        }
                        if(response.result == true){
                            //先替换原页面元素，等到用户刷新再真正判断session来载入真正的元素
                            var replace = "<label>"+"欢迎您!"+response.email+"</label><br>" +
                                "<label><a href='myrelease.jsp'>我的发布</a></label><br>" +
                                "<label><a href='<%=path%>/borrowAction/showMyBorrow'>我的借阅</a></label><br>" +
                                "<label><a href='myexchange.jsp'>我的交换</a></label><br>" +
                                "<label><a href='myorder.jsp'>我的订单</a></label><br>" +
                                "<label><a href='myreservation'>我的预约</a></label><br>"+
                                "<label><a href='<%=path%>/authAction/logout'>"+"退出登录"+"</a><label><br>";
                            $('#loginForm').html(replace);
                            showTip("登陆成功！","success");
                            //alert(response.message);
                        }
                    }
                });
            });
        });


    </script>
    <style type="text/css">
        #tip {
            font-weight: bold;
            position: absolute;
            top: 50px;
            left: 50%;
            display: none;
            z-index: 9999;
        }
    </style>
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
                <h1 class="navbar-brand"><a href="<%=path%>/index">BookShare</a></h1>
            </div>
            <!--navbar-header-->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li><a href="<%=path%>/index">首页</a></li>
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
                    <li class="dropdown grid"><a href=" <%=path%>/bookAction/showAllBooks" class="" >展示图书</a>
                    </li>
                    <li class="dropdown grid"><a href="<%=path%>/bookAction/showBookRelease" class="" >发布图书</a>
                    </li>
                    <li><a href="<%=basePath%>howToShare.jsp" class="" >如何分享</a>
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
                <a href="<%=path%>/userAction/showUserProfile"><span class="glyphicon glyphicon-user" aria-hidden="true"></span></a>
                <div id="loginBox">
                    <form id="loginForm">
                <s:if test="#session.userInfo==null">
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
                </s:if>
                <s:else>
                            <label>欢迎您！<s:property value="#session.userInfo.email"/></label><br>
                            <label><a href="myrelease.jsp">我的发布</a></label><br>
                            <label><a href="<%=path%>/borrowAction/showMyBorrow">我的借阅</a></label><br>
                            <label><a href="myexchange.jsp">我的交换</a></label><br>
                            <label><a href="myorder.jsp">我的订单</a></label><br>
                            <label><a href="myreservation">我的预约</a></label><br>
                            <label><a href="<%=path%>/authAction/logout">退出登录</a></label><br>
                        </form>
                    </div>
                </s:else>
                    </form>
                </div>
            </div>   
            <div class="header-right cart">
                <a href="<%=path%>/cartAction/showBuyCart"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span></a>
                <div class="cart-box">
                   <s:if test="#session.buyCart==null||#session.buyCart.size()==0">
                       <h4><span>购买购物车为空（刷新以更新）</span></h4>
                       <h4><a href="<%=path%>/bookAction/showAllBooks">前去浏览图书</a></h4>
                   </s:if>
                    <s:else>
                        <table class="table table-bordered">
                            <tr>
                            <th field="bookName" width="20%">书名</th>
                            <th field="amount" width="20%">数量</th>
                            </tr>
                            <s:iterator value="#session.buyCart" var="cartItem" status="st">
                                <tr>
                                    <td><s:property value="#cartItem.bookName"/></td>
                                    <td ><s:property value="#cartItem.amount"/></td>
                                </tr>
                        </s:iterator>
                        </table>
                        <p><a href="<%=path%>/orderAction/emptyBuyCart" class="simpleCart_empty">清空购物车</a></p>
                    </s:else>
                    <div class="clearfix"> </div>
                </div>
            </div>
                <div class="header-right borrow">
                <a href="<%=path%>/cartAction/showBorrowCart"><span class="glyphicon glyphicon-book" aria-hidden="true"></span></a>
                <div class="borrow-box">
                    <s:if test="#session.borrowCart==null||#session.borrowCart.size()==0">
                        <h4><span>借阅购物车为空（刷新以更新）</span></h4>
                        <h4><a href="<%=path%>/bookAction/showAllBooks">前去浏览图书</a></h4>
                    </s:if>
                    <s:else>
                        <table class="table table-bordered">
                            <tr>
                                <th field="bookName" width="20%">书名</th>
                                <th field="amount" width="20%">数量</th>
                            </tr>
                            <s:iterator value="#session.borrowCart" var="cartItem" status="st">
                                <tr>
                                    <td><s:property value="#cartItem.bookName"/></td>
                                    <td><s:property value="#cartItem.amount"/></td>
                                </tr>
                            </s:iterator>
                        </table>
                        <p><a href="<%=path%>/orderAction/emptyBorrowCart" class="simpleCart_empty">清空购物车</a></p>
                    </s:else>
                    <div class="clearfix"> </div>
                </div>
                </div>
            </div>
            <div class="clearfix"> </div>
        </div>
        <div class="clearfix"> </div>
    </div>
</div>
</body>
</html>


