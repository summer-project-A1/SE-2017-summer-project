<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="global.jsp"%>
<html>
<head>
    <title>AdminBook</title>
</head>
<body>
<s:action name="header" executeResult="true" namespace="/"/>
<script>
</script>
<div class="products">

    <div class="container">
        <h3 align="center">图书管理</h3>
        <div id="tip"></div>
        <div id="cartinfo" class="cart-item">
            <div class="container">
                <div>
                    <h4>图书列表</h4>
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>图书ID</th>
                            <th>封面</th>
                            <th>书名</th>
                            <th>ISBN</th>
                            <th>作者</th>
          					<th>出版社</th>
                            <th></th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <s:iterator value="#bookList" status="st">
                            <tr>
                                <td><s:property value="bookID"/></td>
                                <td><img src="<%=path%>/imageAction/showImage?imageID=<s:property value="imageID"/>" class="img-thumbnail" alt=""></td>
                                <td><s:property value="bookName"/></td>
                                <td><s:property value="isbn"/></td>
                                <td><s:property value="author"/></td>
                                <td><s:property value="press"/></td>
                                <s:if test="status=='IDLE'||status=='BORROWED'">
                                	<td><a href="<%=path%>/adminAction/deleteBook?bookID=<s:property value="bookID"/>">删除图书</a></td>
                                </s:if>
                            </tr>
                        </s:iterator>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>

</body>
</html>