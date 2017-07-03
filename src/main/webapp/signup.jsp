<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
                var param = $("#register_email").serialize();
            $.ajax({
                url:"<%=path%>/userAction/checkEmailAvailable",
                type:"get",
                data:param,
                dataType:"text",
                success: function (data) {
                    var response = eval("("+data+")");
                    //alert(response.result);
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

        $("#mobile").focus();
        $("#mobile").keyup(function(){
           var mobile = $("#mobile").val();
           if(mobile.length != 11){
               $("#available_status4").html("<span style='color: red'>请输入11位手机号</span>");
           }else{
               $("#available_status4").html("<span></span>");
           }
        });
        $("#mobile").blur(function(){
            var mobile = $("#mobile").val();
            if(mobile.length != 11){
                $("#available_status4").html("<span style='color: red'>请输入11位手机号</span>");
            }else{
                $("#available_status4").html("<span></span>");
            }
        });

        $("#register").click(function(){
            var params = $("#registerForm").serialize();
            $.ajax({
                url: "<%=path%>/userAction/register",
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

<script type="text/javascript" src="js/jquery.cityselect.js"></script>
<script type="text/javascript">
    $(function() {
        $("#city_4").citySelect({
            nodata: "none",
            required:false
        });
    });
</script>

<div class="account">
    <div class="container">
        <div class="register" id="registerBox">
            <form id="registerForm">
                <div class="register-top-grid">
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
                    <div class="input">
                        <span>姓名</span>
                        <input type="text" name="name" id="name">
                    </div>
                    <div class="input">
                        <span>性别</span>
                        <select id="gender" name="gender" >
                            <option>请选择</option>
                            <option>男</option>
                            <option>女</option>
                        </select>
                    </div>
                    <div class="input">
                        <span>手机<label>*</label></span>
                        <input type="text" name="mobile" id="mobile"><div id="available_status4"></div>
                    </div>
                    <span>省市地区<label>*</label></span>
                    <div class="input" id="city_4">
                        <select class="prov" id="province" name="province" style="width:auto"></select>
                        <select class="city"  disabled="disabled" id="city" name="city"  style="width:auto"></select>
                        <select class="dist" disabled="disabled" id="district" name="district" style="width: auto"></select>
                    </div>
                    <div class="input">
                        <span>详细地址<label>*</label></span>
                        <input type="text" name="address" id="address">
                    </div>
                    <div class="clearfix"> </div>
                <div class="register-but">
                    <input type="button" value="注册" id="register">
                    <div class="clearfix"> </div>
                </div>
                </div>
            </form>
        </div>
    </div>
</div>




<!-- footer -->
<jsp:include page="footer.jsp"/>

</body>
</html>
