<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@include file="global.jsp"%>
<html>
<head>
    <title>AdminUser</title>
</head>
<body>
<s:action name="header" executeResult="true" namespace="/"/>
<script>
    function showAddUserForm(){
        $("#addUserForm").show();
    }

    function addUser(){
        var email = $("#addEmail").val();
        var password = $("#addPassword").val();
        var credit = $("#addCredit").val();
        if(email.indexOf("@") < 0){
            showTip('Email地址格式错误','danger');
        }else{
            if(password.length < 1){
                $("#addPassword").val("000000");
            }
            if(credit.length < 1){
                $("#addCredit").val("0");
            }
            $("#addUserForm").submit();
        }
    }
</script>
<div class="products">

    <div class="container">
        <h3 align="center">用户管理</h3>
        <div id="tip"></div>
        <div id="cartinfo" class="cart-item">
            <div class="container">
                <div>
                    <h4>用户列表</h4>
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>用户ID</th>
                            <th>Email</th>
                            <th>密码</th>
                            <th>积分</th>
                            <th></th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <s:iterator value="#userList" status="st">
                            <tr>
                                <td><s:property value="userID"/></td>
                                <td><s:property value="email"/></td>
                                <td><s:property value="password"/></td>
                                <td><s:property value="credit"/></td>
                                <td><a href="<%=path%>/adminAction/resetPassword?userID=<s:property value="userID"/>">重置密码</a></td>
                                <td><a href="<%=path%>/adminAction/deleteUser?userID=<s:property value="userID"/>">删除账户</a></td>
                            </tr>
                        </s:iterator>
                        </tbody>
                    </table>
                    <a href="#" class="add-cart item_add" onclick="showAddUserForm()">添加账户</a>
                    <form id="addUserForm" style="display: none" action="<%=path%>/adminAction/addUser" method="post">
                        <input type="text" id="addEmail" name="email" placeholder="Email"/>
                        <input type="password" id="addPassword" name="password" placeholder="密码"/>
                        <input type="text" id="addCredit" name="credit" placeholder="积分" />
                        <a href="#" class="add-cart item_add" onclick="addUser()">添加</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>

</body>
</html>
