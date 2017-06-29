<%--
  Created by IntelliJ IDEA.
  User: lvjiawei
  Date: 2017/6/29
  Time: 下午4:56
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
    <title>Footer</title>
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
</head>
<body>


<!--subscribe-->
<div class="subscribe">
    <div class="container">
        <h3>Newsletter</h3>
        <form>
            <input type="text" class="text" value="Email" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = 'Email';}">
            <input type="submit" value="Subscribe">
        </form>
    </div>
</div>
<!--//subscribe-->
<!--footer-->
<div class="footer">
    <div class="container">
        <div class="footer-grids">
            <div class="col-md-2 footer-grid">
                <h4>关于我们</h4>
                <ul>
                    <li><a href="#">小组成员</a></li>
                    <li><a href="#">软件学院</a></li>
                    <li><a href="#">上海交通大学</a></li>
                </ul>
            </div>
            <div class="col-md-2 footer-grid">
                <h4>服务</h4>
                <ul>
                    <li><a href="#">售后支持</a></li>
                    <li><a href="#">客服中心</a></li>
                    <li><a href="#">联系我们</a></li>
                </ul>
            </div>
            <div class="col-md-2 footer-grid">
                <h4>帮助中心</h4>
                <ul>
                    <li><a href="#">借阅规则</a></li>
                    <li><a href="#">交换规则</a></li>
                    <li><a href="#">积分购买规则</a></li>
                </ul>
            </div>
        </div>
        <div class="clearfix"></div>
    </div>
</div>
</div>
<!--//footer-->
<div class="footer-bottom">
    <div class="container">
        <p>Copyright &copy; 2017.Book Sharing Platform All rights reserved.</p>
    </div>
</div>

</body>
</html>
