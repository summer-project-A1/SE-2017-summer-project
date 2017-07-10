<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@include file="header.jsp"%>
<html>
<head>
    <title>myBorrow</title>
    <style>
        #wrapper {
            background-color: #5D4B33;
            margin-right:70%;
        }
        @media ( min-width :768px) {
            .sidebar {
                z-index: 1;
                position: absolute;
                width:100%;
                margin-top: 51px;
                background-color: #5D4B33;
            }
        }
        #cartinfo{
            margin-top: 51px;
            margin-left:30%;
            margin-right:10%;
        }
    </style>

</head>
<body>

<script>
    function returnBook(bookID){
        var returnBtnID = "returnBtn"+bookID;
        var delayBtnID = "delayBtn"+bookID;
        var returnDateID = "returnDate"+bookID;
        $.ajax({
            url:'<%=path%>/',
            type:'POST',
            data:{
                'bookID':bookID
            },
            success:function(msg){
                if (msg.success) {
                    var returnDate = msg.returnDate;
                    $("#"+returnBtnID).remove();
                    $("#"+delayBtnID).remove();
                    $("#"+returnDateID).html("归还日期："+returnDate);
                    showTip('已归还图书！', 'success');

                }
                else {
                    showTip('发生错误！', 'danger');
                }
            },
            error:function(xhr,status,error){
                alert('status='+status+',error='+error);
            }

        });
    }

    function delayBook(bookID){
        var delayBtnID = "delayBtn"+bookID;
        var yhdateID = "yhdate"+bookID;
        $.ajax({
            url:'<%=path%>/',
            type:'POST',
            data:{
                'bookID':bookID
            },
            success:function(msg){
                if (msg.success) {
                    var yhdate = msg.yhdate;
                    $("#"+delayBtnID).remove();
                    $("#"+yhdateID).html("应还日期："+yhdate);
                    showTip('已续借图书！', 'success');

                }
                else {
                    showTip('发生错误！', 'danger');
                }
            },
            error:function(xhr,status,error){
                alert('status='+status+',error='+error);
            }

        });
    }

</script>

<!--
<div class="span2 col-md-3 single-grid1">
    <h3>我的账户</h3><br>
    <ul>
        <li><a href="#">个人信息</a></li>
        <li><a href="#">我的发布</a></li>
        <li><a href="#">我的借阅</a></li>
        <li><a href="#">我的交换</a></li>
        <li><a href="#">我的购买</a></li>
    </ul>
</div> -->

<div id="wrapper">
    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-static-top" role="navigation"
         style="margin-bottom: 0">

        <div class="nav navbar-header">
            <a class="navbar-brand" href="#">我的账户</a>
        </div>
        <div class="navbar-default sidebar" role="navigation">
            <div class="sidebar-nav navbar-collapse">
                <ul class="nav" id="side-menu">
                    <li><a href="#" style="color: #FFFFFF"><i class="fa fa-user fa-fw"></i>
                        全部图书</a></li>
                    <li><a href="#" style="color: #FFFFFF"><i class="fa fa-book fa-fw"></i>
                        个人信息</a></li>
                    <li><a href="#" style="color: #FFFFFF" ><i
                            class="fa fa-reorder fa-fw"></i>我的发布</a></li>
                    <li><a href="#" style="color: #FFFFFF" class="active"><i
                            class="fa fa-table fa-fw"></i> 我的借阅</a></li>
                    <li><a href="#" style="color: #FFFFFF"><i class="fa fa-user fa-fw"></i>
                        我的交换</a></li>
                    <li><a href="#" style="color: #FFFFFF"><i class="fa fa-user fa-fw"></i>
                        我的订单</a></li>
                </ul>
            </div>
            <!-- /.sidebar-collapse -->
        </div>
        <!-- /.navbar-static-side --> </nav>
</div>
<br>
<h3 align="center">我的借阅</h3>
<div id="tip"></div>
<div id="cartinfo" class="cart-item">
    <div class="container">
        <!-- 以下迭代显示尚未归还的图书 -->
        <s:iterator value="#borrowBookList" status="map_state">
        <div id="borrowBook<s:property value="bookID"/>" class="cart-header">
            <div class="cart-sec simpleCart_shelfItem">
                <div class="cart-item cyc">
                    <img src="<%=path%>/imageAction/showImage?imageID=<s:property value="imageID"/>" class="img-responsive" alt="">
                </div>
                <div class="cart-item-info">
                    <h4>
                        <a href="<%=path%>/bookAction/showBookProfile?bookID=<s:property value="bookID"/>">
                            书名：<s:property value="bookName"/></a><br>
                        <span>ISBN:<s:property value="isbn"/></span>
                    </h4>
                    <ul class="qty">
                        <li><p>作者：<s:property value="author"/></p></li>
                        <li><p>分类：<s:property value="category1"/></p></li>
                        <li><p>借阅积分：<s:property value="borrowPrice"/></p></li>
                    </ul>
                    <div class="delivery">
                        <p id="yhdate<s:property value="bookID"/>">应还日期：<s:property value="yhDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                        <s:if test="returned==false && delayed==false">
                            <p id="returnDate<s:property value="bookID"/>">尚未归还</p>
                            <button id="returnBtn<s:property value="bookID"/>" onclick="returnBook(<s:property value="bookID"/>)">归还</button>
                            <button id="delayBtn<s:property value="bookID"/>" onclick="delayBook(<s:property value="bookID"/>)">续借</button>
                        </s:if>
                        <s:elseif test="returned==false && delayed==true">
                            <p id="returnDate<s:property value="bookID"/>">尚未归还</p>
                            <button id="returnBtn<s:property value="bookID"/>" onclick="returnBook(<s:property value="bookID"/>)">归还</button>
                        </s:elseif>
                        <div class="clearfix"></div>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
        </s:iterator>

        <!-- 以下迭代显示已归还的图书 -->
        <s:iterator value="#borrowHistory" status="map_state">
            <div id="borrowHistory<s:property value="bookID"/>" class="cart-header">
                <div class="cart-sec simpleCart_shelfItem">
                    <div class="cart-item cyc">
                        <img src="<%=path%>/imageAction/showImage?imageID=<s:property value="imageID"/>" class="img-responsive" alt="">
                    </div>
                    <div class="cart-item-info">
                        <h4>
                            <a href="<%=path%>/bookAction/showBookProfile?bookID=<s:property value="bookID"/>">
                                书名：<s:property value="bookName"/></a><br>
                            <span>ISBN:<s:property value="isbn"/></span>
                        </h4>
                        <ul class="qty">
                            <li><p>作者：<s:property value="author"/></p></li>
                            <li><p>分类：<s:property value="category"/></p></li>
                            <li><p>借阅积分：<s:property value="borrowPrice"/></p></li>
                        </ul>
                        <div class="delivery">
                            <p>应还日期：<s:property value="yhDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                            <p>归还日期：<s:property value="returnDate"/></p>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                </div>
            </div>
        </s:iterator>

        <!-- 以下为页面展示 -->
        <div id="bookID1" class="cart-header">
            <div class="cart-sec simpleCart_shelfItem">
                <div class="cart-item cyc">
                    <img src="<%=path%>/images/m1.png" class="img-responsive" alt="">
                </div>
                <div class="cart-item-info">
                    <h4>
                        <a href="<%=path%>/bookAction/showBookProfile?bookID=1">
                            书名：计算机系统基础</a><br>
                        <span>ISBN: 8888888888</span>
                    </h4>
                    <ul class="qty">
                        <li><p>作者：臧斌宇</p></li>
                        <li><p>分类：言情小说</p></li>
                        <li><p>借阅积分：20</p></li>
                    </ul>
                    <div class="delivery">
                        <p>应还日期：2017年10月30日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                        <p>尚未归还</p>
                        <button>归还</button>
                        <button>续借</button>
                        <div class="clearfix"></div>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
        <div id="bookID2" class="cart-header">
            <div class="cart-sec simpleCart_shelfItem">
                <div class="cart-item cyc">
                    <img src="<%=path%>/images/m1.png" class="img-responsive" alt="">
                </div>
                <div class="cart-item-info">
                    <h4>
                        <a href="<%=path%>/bookAction/showBookProfile?bookID=7">
                            书名：计算机系统基础</a><br>
                        <span>ISBN: 8888</span>
                    </h4>
                    <ul class="qty">
                        <li><p>作者：臧斌宇</p></li>
                        <li><p>分类：言情小说</p></li>
                        <li><p>借阅积分：20</p></li>
                    </ul>
                    <div class="delivery">
                        <p>应还日期：2017年10月30日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                        <p>尚未归还</p>
                        <button>归还</button>
                        <div class="clearfix"></div>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
        <div id="bookID3" class="cart-header">
            <div class="cart-sec simpleCart_shelfItem">
                <div class="cart-item cyc">
                    <img src="<%=path%>/images/m1.png" class="img-responsive" alt="">
                </div>
                <div class="cart-item-info">
                    <h4>
                        <a href="<%=path%>/bookAction/showBookProfile?bookID=7">
                            书名：计算机系统基础</a><br>
                        <span>ISBN: 8888</span>
                    </h4>
                    <ul class="qty">
                        <li><p>作者：臧斌宇</p></li>
                        <li><p>分类：言情小说</p></li>
                        <li><p>借阅积分：20</p></li>
                    </ul>
                    <div class="delivery">
                        <p>应还日期：2017年10月30日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                        <p>归还日期：2017年10月15日</p>
                        <div class="clearfix"></div>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
</div>
</div>
</body>
</html>