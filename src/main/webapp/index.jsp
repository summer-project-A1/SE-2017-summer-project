<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@include file="global.jsp"%>
<html>
<head>
    <s:action name="header" executeResult="true" namespace="/"/>

    <title><s:property value="#title"/></title>

    <!-- Custom Theme files -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="keywords" content="" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
</head>
<body>
<!-- header -->
<div class="banner">
    <div class="container">
        <h2 class="hdng">图书<span>分享</span>交流平台</h2>
        <p>把你的闲置图书分享给别的书友吧！</p>
        <a href="<%=path%>/bookAction/showBookRelease">马上发布</a>
        <div class="banner-text">
            <img src="images/2.png" alt=""/>
        </div>
    </div>
</div>

<div class="gallery">
    <div class="container">
        <div class="gallery-grids">
            <h3 align="center">热门图书</h3><br>
            <!--struts 迭代器 迭代8次 -->
            <s:iterator value="#recommendBookList" status="#st">
                <div class="col-md-3 gallery-grid ">
                    <a href="<%=path%>/bookAction/showBookProfile?bookID=<s:property value="bookID"/>"><img src="<%=path%>/imageAction/showImage?imageID=<s:property value="imageID"/>" class="img-thumbnail" alt=""/>
                        <div class="gallery-info">
                            <p><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>浏览</p>
                            <a class="shop" href="<%=path%>/bookAction/showBookProfile?bookID=<s:property value="bookID"/>">查看详情</a>
                            <div class="clearfix"> </div>
                        </div>
                    </a>
                    <div class="galy-info">
                        <p><s:property value="bookName"/></p>
                    </div>
                </div>
            </s:iterator>

        </div>
        <div id="tip"> </div>
    </div>
</div>

<!-- footer -->
<jsp:include page="footer.jsp"/>
</body>
</html>
