<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@include file="global.jsp"%>

<html>
<head>
    <title>Sign up</title>
</head>
<body>
<!-- header -->
<!-- header -->
<s:action name="header" executeResult="true" namespace="/"/><!-- home page -->
<script>



    $(document).ready(function(){
        $("#register_email").focus();
        $("#register_email").keyup(function(){
            var email = $("#register_email").val();
            var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
            if(!reg.test(email)){
                $("#available_status").html("<span style='color:red'>请输入正确的邮件地址</span>");
            }else{
                $("#available_status").html("<span></span>");
            }
        });

        $("#register_email").blur(function(){
            var email = $("#register_email").val();
            var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
            if(!reg.test(email)){
                $("#available_status").html("<span style='color:red'>请输入正确的邮件地址</span>");
            }else{
                var param = $("#register_email").serialize();
            $.ajax({
                url:"<%=path%>/authAction/checkEmailAvailable",
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
            var email = $("#register_email").val();
            var password = $("#register_password").val();
            var confirmpassword = $("#register_confirmpassword").val();
            var mobile = $("#mobile").val();
            var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
            if(reg.test(email) && password.length>0 && confirmpassword == password && mobile.length == 11){
                var params = $("#registerForm").serialize();
                $.ajax({
                    url: "<%=path%>/authAction/register",
                    type: "post",
                    data: params,
                    success: function (msg) {
                        if(msg.success){
                            showTip('注册成功!', 'success');
                            window.setTimeout("window.location='<%=path%>/index'",1500);
                        }
                        else{
                            //var msg = response.message;
                            showTip('注册失败!', 'danger');
                        }
                    }
                });
            }else{
                //alert("请重新输入注册信息");
                showTip('请重新输入注册信息!', 'danger');
            }
        });
    });
</script>



<script type="text/javascript" src="<%=path%>/js/jquery.cityselect.js"></script>
<script type="text/javascript">
    $(function() {
        $("#city_4").citySelect({
            nodata: "none",
            required:false
        });
    });
</script>
<style>
    @media ( min-width :768px) {

        .form-control-noNewline {
            width: 100px;
            display: inline;
        }

        .form-horizontal .form-group-auto {
            margin-right: 0px;
            margin-left: 0px;
        }
    }
</style>
<div class="account">
    <div class="container">
        <div id="tip"> </div>
        <div class="register" id="registerBox">
            <form id="registerForm" class="form-horizontal">
                <div class="register-top-grid">
                    <h3>用户注册</h3>
                    <div class="form-group form-group-auto">
                        <label>邮箱地址</label><font color="#FF0000">*</font>&nbsp;
                        <input type="text" name="registerInfo.email" class="form-control" id="register_email"><div id="available_status"></div>
                    </div>
                    <div class="form-group form-group-auto">
                        <label>密码</label><font color="#FF0000">*</font>&nbsp;
                        <input type="password" name="registerInfo.plainPassword" class="form-control" id="register_password"><div id="available_status2"></div>
                    </div>
                    <div class="form-group form-group-auto">
                        <label>确认密码</label><font color="#FF0000">*</font>&nbsp;
                        <input type="password" name="confirmpassword" class="form-control" id="register_confirmpassword"><div id="available_status3"></div>
                    </div>
                    <div class="form-group form-group-auto">
                        <label>昵称</label>&nbsp;
                        <input type="text" name="registerInfo.nickName" class="form-control" id="nickName">
                    </div>
                    <div class="form-group form-group-auto">
                        <label>性别</label>&nbsp;
                        <select id="gender" name="registerInfo.gender" class="form-control form-control-noNewline" >
                            <option>请选择</option>
                            <option>男</option>
                            <option>女</option>
                        </select>
                    </div>
                    <div class="form-group form-group-auto">
                        <label>手机</label><font color="#FF0000">*</font>&nbsp;
                        <input type="text" name="registerInfo.mobile" class="form-control" id="mobile"><div id="available_status4"></div>
                    </div>
                    <label>省市地区</label><font color="#FF0000">*</font>&nbsp;
                    <div class="form-group form-group-auto" id="city_4">
                        <select class="prov form-control form-control-noNewline" id="province" name="registerInfo.province" style="width:auto"></select>
                        <select class="city form-control form-control-noNewline"  disabled="disabled" id="city" name="registerInfo.city"  style="width:auto"></select>
                        <select class="dist form-control form-control-noNewline" disabled="disabled" id="district" name="registerInfo.district" style="width: auto"></select>
                    </div>
                    <div class="input">
                        <label>详细地址</label><font color="#FF0000">*</font>&nbsp;
                        <input type="text" class="form-control" name="registerInfo.address" id="address">
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
