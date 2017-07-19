
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>



<!--subscribe-->
<!--
<div class="subscribe">
    <div class="container">
        <h3>Newsletter</h3>
        <form>
            <input type="text" class="text" value="Email" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = 'Email';}">
            <input type="submit" value="Subscribe">
        </form>
    </div>
</div>
-->
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
<!--//footer-->
<div class="footer-bottom">
    <div class="container">
        <p>Copyright &copy; 2017.Book Sharing Platform All rights reserved.</p>
    </div>
</div>
