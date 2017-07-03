<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!- header -->
<jsp:include page="header.jsp"/>
<html>
<head>
    <title>Detail</title>
    <script src="<%=path%>/js/imagezoom.js"></script>
    <script defer src="<%=path%>/js/jquery.flexslider.js"></script>
    <link rel="stylesheet" href="css/flexslider.css" type="text/css" media="screen" />
    <script>
        $(window).load(function() {
            $('.flexslider').flexslider({
                animation: "slide",
                controlNav: "thumbnails"
            });
        });
    </script>
</head>
<body>

<div class="single">
    <div class="container">
        <div class="single-grids">
            <div class="col-md-4 single-grid">
                <div class="flexslider">
                    <ul class="slides">
                        <li data-thumb="images/s1.png">
                            <div class="thumb-image"> <img src="images/s1.png" data-imagezoom="true" class="img-responsive"> </div>
                        </li>
                        <li data-thumb="images/s2.png">
                            <div class="thumb-image"> <img src="images/s2.png" data-imagezoom="true" class="img-responsive"> </div>
                        </li>
                        <li data-thumb="images/s3.png">
                            <div class="thumb-image"> <img src="images/s3.png" data-imagezoom="true" class="img-responsive"> </div>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="col-md-4 single-grid simpleCart_shelfItem">
                <h3>书名<s:property value="bookProfile.bookName"/></h3>
                <p>此处放书的简介</p>
                <ul class="size">
                    <h3>作者</h3>
                    <li><span>作者<s:property value="bookProfile.author"/></span></li>
                </ul>
                <ul class="size">
                    <h3>出版社</h3>
                    <li><span>出版社<s:property value="bookProfile.press"/></span></li>
                </ul>
                <ul class="size">
                    <h3>积分要求</h3>
                </ul>
                <div class="galry">
                    <div class="prices">
                        <h5 class="item_price">购买积分：<s:property value="bookProfile.borrowCredit"/></h5>
                    </div>
                    <div class="rating">
                        <h5 class="item_price">借阅积分：<s:property value="bookProfile.exchangeCredit"/></h5>
                    </div>
                    <div class="clearfix"></div>
                </div>
                <div class="btn_form">
                    <a href="#" class="add-cart item_add">借阅</a>
                    <a href="#" class="add-cart item_add">交换</a>
                    <a href="#" class="add-cart item_add">购买</a>
                </div>
                <div class="tag">
                    <p>分类 : <a href="#"><s:property value="bookProfile.category"/></a></p>
                </div>
            </div>
            <div class="col-md-4 single-grid1">
                <h2>个人账户</h2>
                <ul>
                    <li><a href="products.jsp">全部图书</a></li>
                    <li><a href="myaccount.jsp">个人信息</a></li>
                    <li><a href="myrelease.jsp">我的发布</a></li>
                    <li><a href="myborrow.jsp">我的借阅</a></li>
                    <li><a href="myexchange.jsp">我的交换</a></li>
                    <li><a href="myorder.jsp">我的订单</a></li>
                </ul>
            </div>
            <div class="clearfix"> </div>
        </div>
    </div>
</div>
<!-- collapse -->
<div class="collpse tabs">
    <div class="container">
        <div class="panel-group collpse" id="accordion" role="tablist" aria-multiselectable="true">
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingOne">
                    <h4 class="panel-title">
                        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                            内容简介
                        </a>
                    </h4>
                </div>
                <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                    <div class="panel-body">
                        此处放内容简介<s:property value="bookProfile.intro"/>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingTwo">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                            其他信息
                        </a>
                    </h4>
                </div>
                <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                    <div class="panel-body">
                        出版时间：<s:property value="bookProfile."/>
                        页数：<s:property value="bookProfile."/>
                        版次：<s:property value="bookProfile."/>
                        装帧：<s:property value="bookProfile."/>
                        开本：<s:property value="bookProfile."/>
                        成色：<s:property value="bookProfile."/>
                        损毁情况：<s:property value="bookProfile."/>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingThree">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                            评论
                        </a>
                    </h4>
                </div>
                <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                    <div class="panel-body">
                        此处放图书评论
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>



</body>
</html>
<!- footer -->
<jsp:include page="footer.jsp"/>