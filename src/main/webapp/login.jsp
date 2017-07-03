<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
</head>
<body>
    <form action="<%=path%>/userAction/login">
        <label>邮箱：</label><input type="text" name="email"></input><br />
        <label>密码：</label><input type="text" name="password"></input>
        <input type="submit" value="登录"></input>
    </form>
</body>
</html>
