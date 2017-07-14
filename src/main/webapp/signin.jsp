<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@include file="global.jsp"%>

<html>
<head>
    <title>Sign in</title>
</head>
<body>
<!-- header -->

<script>



    $(document).ready(function(){
        $("#login_email").focus();
        $("#login_email").keyup(function(){
            var email = $("#login_email").val();
            if(email.indexOf("@") < 0){
                $("#available_status").html("<span style='color:red'>请输入正确的邮件地址</span>");
            }else{
                $("#available_status").html("<span></span>");
            }
        });
    });
</script>


<div class="account">
    <div class="container">
        <div id="tip"> </div>
        <div class="register" id="registerBox">
            <form id="registerForm" action="<%=path%>/authAction/signin" class="form-horizontal" method="post">
                <div class="register-top-grid">
                    <h3>请重新登录</h3>
                    <div class="form-group form-group-auto">
                        <label>邮箱地址</label><font color="#FF0000">*</font>&nbsp;
                        <input type="text" name="email" class="form-control" id="login_email"><div id="available_status"></div>
                    </div>
                    <div class="form-group form-group-auto">
                        <label>密码</label><font color="#FF0000">*</font>&nbsp;
                        <input type="password" name="password" class="form-control" id="login_password"><div id="available_status2"></div>
                    </div>


                    <div class="clearfix"> </div>
                    <div class="register-but">
                        <input type="submit" value="登陆" id="login">
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
