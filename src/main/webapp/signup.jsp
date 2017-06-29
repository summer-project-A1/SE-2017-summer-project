<%--
  Created by IntelliJ IDEA.
  User: lvjiawei
  Date: 2017/6/29
  Time: 下午5:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <title>Sign up</title>
    <!-- Custom Theme files -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!-- //Custom Theme files -->
    <link href="css/bootstrap.css" type="text/css" rel="stylesheet" media="all">
    <link href="css/style.css" type="text/css" rel="stylesheet" media="all">
    <!-- js -->
    <script src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/bootstrap-3.1.1.min.js"></script>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery.validate.min.js"></script>
    <!-- //js -->
    <!-- cart -->
    <script src="js/simpleCart.min.js"> </script>
    <!-- cart -->


</head>
<body>
<!-- header -->
<jsp:include page="header.jsp"/>

<script>
    $(document).ready(function(){
        $("#register_email").focus();
        $("#register_email").keyup(function(){
            var email = $("#register_email").val();
            if(email.indexOf("@") < 0){
                $("#available_status").html("<span style='color:red'>请输入正确的邮件地址</span>");
            }else{
                $("#available_status").html("<span></span>");
            }
        });

        $("#register_email").blur(function(){
            var email = $("#register_email").val();
            if(email.indexOf("@") < 0){
                $("#available_status").html("<span style='color:red'>请输入正确的邮件地址</span>");
            }else{
            $.ajax({
                url:"",
                type:"get",
                date:email,
                dataType:"text",
                success: function (data) {
                    var response = eval("("+data+")");
                    if(response.result == false){
                        $("#available_status").html("<span style='color:red'>该用户名已存在</span>");
                    }
                    if(response.result == true){
                        $("#available_status").html("<span style='color:green'>该用户名可用</span>");
                    }
                }
            });}
        });
        $("#register_password").focus();
        $("#register_password").keyup(function(){
            var password = $("#register_password").val();
            if(password.length < 6 || password.length > 12){
                $("#available_status2").html("<span style='color:red'>密码在6-12位</span>");
            }else{
                $("#available_status2").html("<span style='color:green'>密码格式正确</span>");
            }
        });
        $("#register_password").blur(function(){
            var password = $("#register_password").val();
            if(password.length < 6 || password.length > 12){
                $("#available_status2").html("<span style='color:red'>密码在6-12位</span>");
            }else{
                $("#available_status2").html("<span style='color:green'>密码格式正确</span>");
            }
        });
        $("#register_confirmpassword").focus();
        $("#register_confirmpassword").keyup(function(){
            var confirmpassword = $("#register_confirmpassword").val();
            var password = $("#register_password").val();
            if(confirmpassword != password){
                $("#available_status3").html("<span style='color:red'>两次密码不一致</span>");
            }else{
                $("#available_status3").html("<span></span>");
            }
        });

        $("#register_confirmpassword").blur(function(){
            var confirmpassword = $("#register_confirmpassword").val();
            var password = $("#register_password").val();
            if(confirmpassword != password){
                $("#available_status3").html("<span style='color:red'>两次密码不一致</span>");
            }else{
                $("#available_status3").html("<span></span>");
            }
        });

        $("#register").click(function(){
            var params = $("#registerForm").serialize();
            $.ajax({
                url: "register",
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
                        alert(response.message);
                        window.location.href='index.jsp';
                    }
                }
            });
        });
    });
</script>

<div class="account">
    <div class="container">
        <div class="register" id="registerBox">
            <form id="registerForm">
                <div class="register-bottom-grid">
                    <h3>用户注册</h3>
                    <div class="input">
                        <span>邮箱地址<label>*</label></span>
                        <input type="text" name="email" id="register_email"><div id="available_status"></div>
                    </div>
                    <div class="input">
                        <span>密码<label>*</label></span>
                        <input type="password" name="password" id="register_password"><div id="available_status2"></div>
                    </div>
                    <div class="input">
                        <span>确认密码<label>*</label></span>
                        <input type="password" name="confirmpassword" id="register_confirmpassword"><div id="available_status3"></div>
                    </div>
                </div>
            </form>
            <div class="clearfix"> </div>
            <div class="register-but">
                <input type="button" value="submit" id="register">
                <div class="clearfix"> </div>
            </div>
        </div>
    </div>
</div>

<!-- footer -->
<jsp:include page="footer.jsp"/>

</body>
</html>
