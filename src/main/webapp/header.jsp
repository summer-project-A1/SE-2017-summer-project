<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%//@include file="global.jsp"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>

<html>
<head>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="" />

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
                                <s:iterator value="#category1List">
                                    <div class="col-sm-3">
                                        <h4><s:property value="category1Name"/></h4>
                                        <ul class="multi-column-dropdown">
                                            <li><a class="list" href="<%=path%>/bookAction/showBooksByCategory1Name?category1Name=<s:property value="category1Name"/>"><s:property value="category1Name"/></a></li>
                                            <s:iterator value="category2List">
                                                <li><a class="list" href="<%=path%>/bookAction/showBooksByCategory2Name?category2Name=<s:property value="category2Name"/>"><s:property value="category2Name"/></a></li>
                                            </s:iterator>
                                        </ul>
                                    </div>
                                </s:iterator>
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


