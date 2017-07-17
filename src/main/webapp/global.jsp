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
                                "<label><a href='myaccount.jsp'>个人信息</a></label><br>" +
                                "<label><a href='myrelease.jsp'>我的发布</a></label><br>" +
                                "<label><a href='<%=path%>/borrowAction/showMyBorrow'>我的借阅</a></label><br>" +
                                "<label><a href='myexchange.jsp'>我的交换</a></label><br>" +
                                "<label><a href='<%=path%>/orderAction/showMyOrder'>我的购买</a></label><br>" +
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
</html>


