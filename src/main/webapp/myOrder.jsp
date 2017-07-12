<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="header.jsp"%>
<html>
<head>
    <title>myOrder</title>
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
    function creditRating(borrowID){
        var creditRatingFormID = "creditRatingForm" + borrowID;
        $("#"+creditRatingFormID).show();
    }

    function submitRating(borrowID){
        var creaditRatingFormID = "creditRatingForm" + borrowID;
        $("#"+creaditRatingFormID).submit();
    }
</script>

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
                    <li><a href="<%=path%>/borrowAction/showMyBorrow" style="color: #FFFFFF"><i
                            class="fa fa-table fa-fw"></i> 我的借阅</a></li>
                    <li><a href="#" style="color: #FFFFFF"><i class="fa fa-user fa-fw"></i>
                        我的交换</a></li>
                    <li><a href="#" style="color: #FFFFFF" class="active"><i class="fa fa-user fa-fw"></i>
                        我的购买</a></li>
                </ul>
            </div>
            <!-- /.sidebar-collapse -->
        </div>
        <!-- /.navbar-static-side --> </nav>
</div>
<br>
<h3 align="center">我的购买</h3>
<div id="tip"></div>
<div id="cartinfo" class="cart-item">
    <div class="container">

        <!-- 迭代器显示订单信息 -->
        <s:iterator value="#orderList" status="st">
            <s:url action="showOrderById" namespace="/orderAction" var="showOrderLink">
                <s:param name="orderID"><s:property value="orderID"/></s:param>
            </s:url>
            <s:url action="cancelOrder" namespace="/orderAction" var="cancelOrderLink">
                <s:param name="orderID"><s:property value="orderID"/></s:param>
            </s:url>
            <s:url action="confirmReceipt" namespace="/orderAction" var="confirmReceiptLink">
                <s:param name="orderID"><s:property value="orderID"/></s:param>
            </s:url>
            
            <div id="<s:property value="orderID"/>" class="cart-header">
                <div class="cart-sec simpleCart_shelfItem">
                    <div class="cart-item cyc">
                        <img src="<%=path%>/imageAction/showImage?imageID=<s:property value="imageID"/>" class="img-responsive" alt="">
                    </div>
                    <div class="cart-item-info">
                        <h4>
                            <s:if test="status=='notPayed'">
                                <p>订单号：<s:property value="orderID"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：未支付</p>
                            </s:if>
                            <s:elseif test="status=='canceled'">
                                <p>订单号：<s:property value="orderID"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：已取消</p>
                            </s:elseif>
                            <s:elseif test="status=='notShipped'">
                                <p>订单号：<s:property value="orderID"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：未发货</p>
                            </s:elseif>
                            <s:elseif test="status=='shipped'">
                                <p>订单号：<s:property value="orderID"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：已发货</p>
                            </s:elseif>
                            <s:elseif test="status=='completed'">
                                <p>订单号：<s:property value="orderID"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：已完成</p>
                            </s:elseif>
                        </h4><br>
                        <ul class="qty">
                            <li><p>书名：<s:property value="bookName"/></p></li>
                            <li><p>下单时间：<s:property value="orderDate"/></p></li>
                            <li><p>总积分：<s:property value="totalCredit"/></p></li>
                        </ul>
                        <div class="delivery">
                            <p>收货人：<s:property value="receiver"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                            <p>收货地址：<s:property value="address"/></p><br>
                            <s:if test="status=='notPayed'">
                                <a href="${showOrderLink}" class="add-cart item_add">支付</a>
                                <a href="${cancelOrderLink}" class="add-cart item_add">取消</a>
                            </s:if>
                            <s:elseif test="status=='canceled'">

                            </s:elseif>
                            <s:elseif test="status=='notShipped'">

                            </s:elseif>
                            <s:elseif test="status=='shipped'">
                                <a href="${confirmReceiptLink}" class="add-cart item_add" onclick="">确认收货</a>
                            </s:elseif>
                            <s:elseif test="status=='completed'">
                                <a href="#" id="creditRatingBtn<s:property value="orderID"/>" class="add-cart item_add" onclick="creditRating(<s:property value="orderID"/>)">信用评价</a>
                                <form id="creditRatingForm<s:property value="orderID"/>" action="" method="post" style="display: none">
                                    <select name="creditRating" class="form-control form-control-noNewline">
                                        <option value="-1">差评</option>
                                        <option value="0">中评</option>
                                        <option value="1">好评</option>
                                    </select>
                                    <a href="#" class="add-cart item_add" onclick="submitRating(<s:property value="orderID"/>)">评价</a>
                                </form>
                            </s:elseif>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                </div>
            </div>
            
        </s:iterator>
        
        
        
        
        
        <!-- 以下为静态展示 -->
        <div id="bookID1" class="cart-header">
            <div class="cart-sec simpleCart_shelfItem">
                <div class="cart-item cyc">
                    <img src="<%=path%>/images/m5.png" class="img-responsive" alt="">
                </div>
                <div class="cart-item-info">
                    <h4>
                        <a href="<%=path%>/">订单号:1580021000037&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：未支付</a>
                    </h4><br>
                    <ul class="qty">
                        <li><p>书名：黄政的自传</p></li>
                        <li><p>下单时间：2017-6-10   20:42:48</p></li>
                        <li><p>总积分：1024</p></li>
                    </ul>
                    <div class="delivery">
                        <p>收货人：黄政&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                        <p>收货地址：东川路800号</p><br>
                        <a href="${showOrderLink}" id="payBtn" class="add-cart item_add">支付</a>
                        <a href="${cancelOrderLink}" id="cancelBtn" class="add-cart item_add">取消</a>
                        <div class="clearfix"></div>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div><hr>
        <div id="bookID2" class="cart-header">
            <div class="cart-sec simpleCart_shelfItem">
                <div class="cart-item cyc">
                    <img src="<%=path%>/images/m5.png" class="img-responsive" alt="">
                </div>
                <div class="cart-item-info">
                    <h4>
                        <a href="<%=path%>/">订单号:1580021000037&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：未发货</a>
                    </h4><br>
                    <ul class="qty">
                        <li><p>书名：黄政的自传</p></li>
                        <li><p>下单时间：2017-6-10   20:42:48</p></li>
                        <li><p>总积分：524</p></li>
                    </ul>
                    <div class="delivery">
                        <p>收货人：沈斯杰&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                        <p>收货地址：东川路800号</p><br>
                        <div class="clearfix"></div>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div><hr>
        <div id="bookID3" class="cart-header">
            <div class="cart-sec simpleCart_shelfItem">
                <div class="cart-item cyc">
                    <img src="<%=path%>/images/m5.png" class="img-responsive" alt="">
                </div>
                <div class="cart-item-info">
                    <h4>
                        <a href="<%=path%>/">订单号:1580021000037&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：已发货</a>
                    </h4><br>
                    <ul class="qty">
                        <li><p>书名：黄政的自传</p></li>
                        <li><p>下单时间：2017-6-10   20:42:48</p></li>
                        <li><p>总积分：10240</p></li>
                    </ul>
                    <div class="delivery">
                        <p>收货人：高仓靖博&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                        <p>收货地址：东川路800号</p><br>
                        <a href="${confirmReceiptLink}" id="confirmBtn" class="add-cart item_add" onclick="">确认收货</a>
                        <div class="clearfix"></div>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div><hr>
        <div id="bookID4" class="cart-header">
            <div class="cart-sec simpleCart_shelfItem">
                <div class="cart-item cyc">
                    <img src="<%=path%>/images/m5.png" class="img-responsive" alt="">
                </div>
                <div class="cart-item-info">
                    <h4>
                        <a href="<%=path%>/">订单号:1580021000037&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：已完成</a>
                    </h4><br>
                    <ul class="qty">
                        <li><p>书名：黄政的自传</p></li>
                        <li><p>下单时间：2017-6-10   20:42:48</p></li>
                        <li><p>总积分：10240</p></li>
                    </ul>
                    <div class="delivery">
                        <p>收货人：Bjarne&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                        <p>收货地址：东川路800号</p><br>
                        <a href="#" id="commentBtn" class="add-cart item_add" onclick="">信用评价</a>
                        <div class="clearfix"></div>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div><hr>
        <div id="bookID4" class="cart-header">
            <div class="cart-sec simpleCart_shelfItem">
                <div class="cart-item cyc">
                    <img src="<%=path%>/images/m5.png" class="img-responsive" alt="">
                </div>
                <div class="cart-item-info">
                    <h4>
                        <a href="<%=path%>/">订单号:1580021000037&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：已取消</a>
                    </h4><br>
                    <ul class="qty">
                        <li><p>书名：黄政的自传</p></li>
                        <li><p>下单时间：2017-6-10   20:42:48</p></li>
                        <li><p>总积分：10240</p></li>
                    </ul>
                    <div class="delivery">
                        <p>收货人：Bjarne&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                        <p>收货地址：东川路800号</p><br>
                        <div class="clearfix"></div>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div><hr>
    </div>
</div>
</body>
</html>
