<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@include file="header.jsp"%>
<html>
<head>
    <title>myBorrow</title>
    <style>

        @media ( min-width :768px) {
<<<<<<< HEAD
            #wrapper {
                background-color: #5D4B33;
                margin-right:70%;
            }
            .sidebar {
                z-index: 1;
                position: absolute;
                width:100%;
=======
            #cartinfo{
>>>>>>> refs/remotes/origin/master
                margin-top: 51px;
<<<<<<< HEAD
                background-color: #5D4B33;
            }
            #cartinfo{
                margin-top: 51px;
=======
>>>>>>> refs/remotes/origin/master
                margin-left:30%;
                margin-right:10%;
        }

        }
        @media ( min-width :1440px) {
<<<<<<< HEAD
            #wrapper {
                background-color: #5D4B33;
                margin-right:70%;
            }
            .sidebar {
                z-index: 1;
                position: absolute;
                width:100%;
                margin-top: 51px;
                background-color: #5D4B33;
            }
=======
>>>>>>> refs/remotes/origin/master
            #cartinfo{
                margin-top: 21px;
                margin-left:10%;
                margin-right:10%;
            }

        }
    </style>

</head>
<body>

<script>
    function confirmReceipt(borrowID){
        var confirmBtnID = "confirmBtn"+borrowID;
        $.ajax({
           url:'<%=path%>/borrowAction/confirmReceipt',
           type:'POST',
           data:{'borrowID':borrowID},
           success:function(msg){
               if(msg.success){
                   showTip('已确认收货！','success');
                   window.setTimeout("window.location='<%=path%>/borrowAction/showMyBorrow'",1500);
               }else{
                   showTip('发生错误！', 'danger');
               }
           },
            error:function(xhr,status,error){
                alert('status='+status+',error='+error);
            }
        });
    }

    function returnBook(bookID){
        var returnBtnID = "returnBtn"+bookID;
        var delayBtnID = "delayBtn"+bookID;
        var returnDateID = "returnDate"+bookID;
        $.ajax({
            url:'<%=path%>/borrowAction/returnBook',
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
            url:'<%=path%>/borrowAction/delayBook',
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

    function commentBook(bookID){
        var commentFormID = "commentForm" + bookID;
        $("#"+commentFormID).show();
    }

    function submitComment(bookID,borrowID){
        var commentID = "comment" + bookID;
        var commentFormID = "commentForm" + bookID;
        var commentContent = $("#"+commentID).val();
        if(commentContent.length==0){
            showTip('评论不可为空','danger');
        }else{
            $("#"+commentFormID).submit();
        }
    }

    function creditRating(bookID){
        var creditRatingFormID = "creditRatingForm" + bookID;
        $("#"+creditRatingFormID).show();
    }

    function submitRating(bookID){
        var creaditRatingFormID = "creditRatingForm" + bookID;
        $("#"+creaditRatingFormID).submit();
    }

</script>

<<<<<<< HEAD
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
                    <li><a href="<%=path%>/borrowAction/showMyBorrow" style="color: #FFFFFF" class="active"><i
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
=======
<div class="products">

>>>>>>> refs/remotes/origin/master
    <div class="container">
<<<<<<< HEAD
        <!-- 以下迭代显示尚未归还的图书 -->
        <s:iterator value="#borrowBook" status="map_state">
        <div id="borrowBook<s:property value="bookID"/>" class="cart-header">
            <div class="cart-sec simpleCart_shelfItem">
                <div class="cart-item cyc">
                    <img src="<%=path%>/imageAction/showImage?imageID=<s:property value="imageID"/>" class="img-responsive" alt="">
                </div>
                <div class="cart-item-info">
                    <h4>
                        <a href="<%=path%>/bookAction/showBookProfile?bookID=<s:property value="bookID"/>">
                            书名：<s:property value="bookName"/></a><br>
                        <s:if test="status=='notShipped'">
                            <span>ISBN:<s:property value="isbn"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：未发货</span>
                        </s:if>
                        <s:elseif test="status=='shipped'">
                            <span>ISBN:<s:property value="isbn"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：已发货</span>
                        </s:elseif>
                        <s:elseif test="status=='notReturned'">
                            <span>ISBN:<s:property value="isbn"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：未归还</span>
                        </s:elseif>
                        <s:elseif test="status=='returned'">
                            <span>ISBN:<s:property value="isbn"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：已归还，待确认</span>
                        </s:elseif>
                    </h4>
                    <ul class="qty">
                        <li><p>作者：<s:property value="author"/></p></li>
                        <li><p>分类：<s:property value="category1"/></p></li>
                        <li><p>借阅积分：<s:property value="borrowPrice"/></p></li>
                    </ul>
                    <div class="delivery">
                        <p id="yhdate<s:property value="bookID"/>">应还日期：<s:property value="yhDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                        <s:if test="status=='notShipped'">
                            <p id="returnDate<s:property value="bookID"/>">尚未归还</p>
                        </s:if>
                        <s:elseif test="status=='shipped'">
                            <p id="returnDate<s:property value="bookID"/>">尚未归还</p><br>
                            <a href="#" id="confirmBtn<s:property value="borrowID"/>" class="add-cart item_add" onclick="confirmReceipt(<s:property value="borrowID"/>)">确认收货</a>
                        </s:elseif>
                        <s:elseif test="status=='notReturned'">
                            <s:if test="returned==false && delayed==false">
                                <p id="returnDate<s:property value="bookID"/>">尚未归还</p><br>
                                <a href="#" id="returnBtn<s:property value="bookID"/>" class="add-cart item_add" onclick="returnBook(<s:property value="bookID"/>)">归还</a>
                                <a href="#" id="delayBtn<s:property value="bookID"/>" class="add-cart item_add" onclick="delayBook(<s:property value="bookID"/>)">续借</a>
                            </s:if>
                            <s:elseif test="returned==false && delayed==true">
                                <p id="returnDate<s:property value="bookID"/>">尚未归还</p><br>
                                <a href="#" id="returnBtn<s:property value="bookID"/>" class="add-cart item_add" onclick="returnBook(<s:property value="bookID"/>)">归还</a>
                            </s:elseif>
                        </s:elseif>
                        <s:elseif test="status=='returned'">
                            <p id="returnDate<s:property value="bookID"/>">已归还，待确认</p>
                        </s:elseif>
                        <div class="clearfix"></div>
=======
        <div class="col-md-3 rsiderbar span_1_of_left">
            <section class="sky-form">
                <div class="product_right">
                    <h3 class="m_2"><span class="glyphicon glyphicon-minus" aria-hidden="true"></span>操作选单</h3>

                    <div class="tab1">
                        <ul class="place">
                            <li class="sort"><a href="#">个人信息</a></li>
                        </ul>
                        <div class="clearfix"> </div>
>>>>>>> refs/remotes/origin/master
                    </div>
                    <div class="tab1">
                        <ul class="place">
                            <li class="sort"><a href="#">我的发布</a></li>
                        </ul>
                        <div class="clearfix"> </div>
                    </div>
                    <div class="tab1">
                        <ul class="place">
                            <li class="sort"><a href="#">我的借阅</a></li>
                        </ul>
                        <div class="clearfix"> </div>
                    </div>
                    <div class="tab1">
                        <ul class="place">
                            <li class="sort"><a href="#">我的交换</a></li>
                        </ul>
                        <div class="clearfix"> </div>
                    </div>
                    <div class="tab1">
                        <ul class="place">
                            <li class="sort"><a href="#">我的订单</a></li>
                        </ul>
                        <div class="clearfix"> </div>
                    </div>
                    <div class="clearfix"> </div>
                </div>
<<<<<<< HEAD
                <div class="clearfix"></div>
            </div>
        </div><hr>
        </s:iterator>
=======
            </section>
        </div>
        <h3 align="center">我的借阅</h3>
        <div id="tip"></div>
        <div id="cartinfo" class="cart-item">
            <div class="container">
                <!-- 以下迭代显示尚未归还的图书 -->
                <s:iterator value="#borrowBook" status="map_state">
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
>>>>>>> refs/remotes/origin/master

        <!-- 以下迭代显示已归还的图书 -->
        <s:iterator value="#borrowHistoryBook" status="map_state">
            <div id="borrowHistory<s:property value="bookID"/>" class="cart-header">
                <div class="cart-sec simpleCart_shelfItem">
                    <div class="cart-item cyc">
                        <img src="<%=path%>/imageAction/showImage?imageID=<s:property value="imageID"/>" class="img-responsive" alt="">
                    </div>
                    <div class="cart-item-info">
                        <h4>
                            <a href="<%=path%>/bookAction/showBookProfile?bookID=<s:property value="bookID"/>">
                                书名：<s:property value="bookName"/></a><br>
                            <span>ISBN:<s:property value="isbn"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：已完成</span>
                        </h4>
                        <ul class="qty">
                            <li><p>作者：<s:property value="author"/></p></li>
                            <li><p>分类：<s:property value="category"/></p></li>
                            <li><p>借阅积分：<s:property value="borrowPrice"/></p></li>
                        </ul>
                        <div class="delivery">
                            <p>应还日期：<s:property value="yhDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                            <p>归还日期：<s:property value="returnDate"/></p><br>
                            <a href="#" id="commentBtn<s:property value="bookID"/>" class="add-cart item_add" onclick="commentBook(<s:property value="bookID"/>)">图书评论</a>
                            <a href="#" id="creditRatingBtn<s:property value="bookID"/>" class="add-cart item_add" onclick="creditRating(<s:property value="bookID"/>)">信用评价</a>
                            <form id="commentForm<s:property value="bookID"/>" style="display: none" action="<%=path%>/commentAction/commentBook" method="post">
                                <input type="hidden" id="bookID<s:property value="bookID"/>" name="bookID" value="<s:property value="bookID"/>"/>
                                <input type="hidden" id="borrowID<s:property value="borrowID"/>" name="borrowID" value="<s:property value="borrowID"/>"/>
                                <textarea id="comment<s:property value="bookID"/>" name="comment" class="form-control" rows="3"></textarea>
                                <a href="#" class="add-cart item_add" onclick="submitComment(<s:property value="bookID"/>)">提交</a>
                            </form>
                            <form id="creditRatingForm<s:property value="bookID"/>" style="display: none">
                                <select name="creditRating" class="form-control form-control-noNewline">
                                    <option value="-1">差评</option>
                                    <option value="0">中评</option>
                                    <option value="1">好评</option>
                                </select>
                                <a href="#" class="add-cart item_add" onclick="submitRating(<s:property value="bookID"/>)">评价</a>
                                <div id="comment_status2"></div>
                            </form>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div><hr>
        </s:iterator>

        <!-- 测试样式 -->
        <div id="borrowBook<s:property value="bookID"/>" class="cart-header">
            <div class="cart-sec simpleCart_shelfItem">
                <div class="cart-item cyc">
                    <img src="<%=path%>/imageAction/showImage?imageID=<s:property value="imageID"/>" class="img-responsive" alt="">
                </div>
                <div class="cart-item-info">
                    <h4>
                        <a href="<%=path%>/bookAction/showBookProfile?bookID=<s:property value="bookID"/>">
                            书名：<s:property value="bookName"/></a><br>
                        <span>ISBN:<s:property value="isbn"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：未发货</span>
                    </h4>
                    <ul class="qty">
                        <li><p>作者：<s:property value="author"/></p></li>
                        <li><p>分类：<s:property value="category1"/></p></li>
                        <li><p>借阅积分：<s:property value="borrowPrice"/></p></li>
                    </ul>
                    <div class="delivery">
                        <p id="yhdate<s:property value="bookID"/>">应还日期：<s:property value="yhDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                            <p id="returnDate<s:property value="bookID"/>">尚未归还</p>
                        <div class="clearfix"></div>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div><hr>

        <div id="borrowBook<s:property value="bookID"/>" class="cart-header">
            <div class="cart-sec simpleCart_shelfItem">
                <div class="cart-item cyc">
                    <img src="<%=path%>/imageAction/showImage?imageID=<s:property value="imageID"/>" class="img-responsive" alt="">
                </div>
                <div class="cart-item-info">
                    <h4>
                        <a href="<%=path%>/bookAction/showBookProfile?bookID=<s:property value="bookID"/>">
                            书名：<s:property value="bookName"/></a><br>
                        <span>ISBN:<s:property value="isbn"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：已发货</span>
                    </h4>
                    <ul class="qty">
                        <li><p>作者：<s:property value="author"/></p></li>
                        <li><p>分类：<s:property value="category1"/></p></li>
                        <li><p>借阅积分：<s:property value="borrowPrice"/></p></li>
                    </ul>
                    <div class="delivery">
                        <p id="yhdate<s:property value="bookID"/>">应还日期：<s:property value="yhDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                            <p id="returnDate<s:property value="bookID"/>">尚未归还</p><br>
                            <a href="#" id="returnBtn<s:property value="bookID"/>" class="add-cart item_add" onclick="returnBook(<s:property value="bookID"/>)">确认收货</a>
                        <div class="clearfix"></div>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div><hr>

        <div id="borrowBook<s:property value="bookID"/>" class="cart-header">
            <div class="cart-sec simpleCart_shelfItem">
                <div class="cart-item cyc">
                    <img src="<%=path%>/imageAction/showImage?imageID=<s:property value="imageID"/>" class="img-responsive" alt="">
                </div>
                <div class="cart-item-info">
                    <h4>
                        <a href="<%=path%>/bookAction/showBookProfile?bookID=<s:property value="bookID"/>">
                            书名：<s:property value="bookName"/></a><br>
                        <span>ISBN:<s:property value="isbn"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：未归还</span>
                    </h4>
                    <ul class="qty">
                        <li><p>作者：<s:property value="author"/></p></li>
                        <li><p>分类：<s:property value="category1"/></p></li>
                        <li><p>借阅积分：<s:property value="borrowPrice"/></p></li>
                    </ul>
                    <div class="delivery">
                        <p id="yhdate<s:property value="bookID"/>">应还日期：<s:property value="yhDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                            <p id="returnDate<s:property value="bookID"/>">尚未归还</p><br>
                            <a href="#" id="returnBtn<s:property value="bookID"/>" class="add-cart item_add" onclick="returnBook(<s:property value="bookID"/>)">归还</a>
                            <a href="#" id="delayBtn<s:property value="bookID"/>" class="add-cart item_add" onclick="delayBook(<s:property value="bookID"/>)">续借</a>
                        <div class="clearfix"></div>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div><hr>

        <div id="borrowBook<s:property value="bookID"/>" class="cart-header">
            <div class="cart-sec simpleCart_shelfItem">
                <div class="cart-item cyc">
                    <img src="<%=path%>/imageAction/showImage?imageID=<s:property value="imageID"/>" class="img-responsive" alt="">
                </div>
                <div class="cart-item-info">
                    <h4>
                        <a href="<%=path%>/bookAction/showBookProfile?bookID=<s:property value="bookID"/>">
                            书名：<s:property value="bookName"/></a><br>
                        <span>ISBN:<s:property value="isbn"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态：已归还</span>
                    </h4>
                    <ul class="qty">
                        <li><p>作者：<s:property value="author"/></p></li>
                        <li><p>分类：<s:property value="category1"/></p></li>
                        <li><p>借阅积分：<s:property value="borrowPrice"/></p></li>
                    </ul>
                    <div class="delivery">
                        <p id="yhdate<s:property value="bookID"/>">应还日期：<s:property value="yhDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                        <p id="returnDate<s:property value="bookID"/>">已归还，待确认</p>
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

