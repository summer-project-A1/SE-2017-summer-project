<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<%@include file="footer.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>Register</title>
</head>
<body>
    <form action="<%=path%>/userAction/register">
        新用户<br />
        <label>邮箱：</label><input type="text" name="email"></input><br />
        <label>密码：</label><input type="text" name="password"></input>
        <input type="submit" value="注册"></input>
    </form>
</body>
</html>