<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!-- header -->
<%@include file="header.jsp"%>

<html>
<head>
    <title>Product Detail</title>
    <script src="<%=path%>/js/imagezoom.js"></script>
    <script defer src="<%=path%>/js/jquery.flexslider.js"></script>
    <link rel="stylesheet" href="<%=path%>/css/flexslider.css" type="text/css" media="screen" />
    <script>
        $(window).load(function() {
            $('.flexslider').flexslider({
                animation: "slide",
                controlNav: "thumbnails"
            });
        });
    </script>
    <script>
        function addToBorrowCart(bookID){
            $.ajax({
                url:'<%=path%>/cartAction/addToBorrowCart',
                type:'POST',
                data:{
                    'bookID': bookID,
                    'amount': "1"
                },
                success: function (msg) {
                    if (msg.success) {
                        showTip('添加成功!', 'success');
                    }
                    else {
                        showTip('添加失败', 'danger');
                    }
                },
                error:function(xhr,status,error){
                    alert('status='+status+',error='+error);
                }
            });
        }
    </script>
</head>
<body>

<div class="single">
    <div class="container">
        <div class="single-grids">
            <div class="col-md-4 single-grid">
                <div class="flexslider">
                    <ul class="slides">
                        <li data-thumb="<%=path%>/imageAction/showImage?imageID=<s:property value="#bookProfile.imageID"/>">
                            <div class="thumb-image"> <img src="<%=path%>/imageAction/showImage?imageID=<s:property value="#bookProfile.imageID"/>" data-imagezoom="true" class="img-responsive"> </div>
                        </li>
                        <li data-thumb="<%=path%>/images/s2.png">
                            <div class="thumb-image"> <img src="<%=path%>/images/s2.png" data-imagezoom="true" class="img-responsive"> </div>
                        </li>
                        <li data-thumb="<%=path%>/images/s3.png">
                            <div class="thumb-image"> <img src="<%=path%>/images/s3.png" data-imagezoom="true" class="img-responsive"> </div>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="col-md-4 single-grid simpleCart_shelfItem">
                <ul class="size">
                    <h3>书名</h3>
                    <li><span></span><s:property value="#bookProfile.bookName"/></li></li>
                </ul>

                <ul class="size">
                    <h3>作者</h3>
                    <li><span><s:property value="#bookProfile.author"/></span></li>
                </ul>
                <ul class="size">
                    <h3>ISBN</h3>
                    <li><span><s:property value="#bookProfile.isbn"/></span></li>
                </ul>
                <ul class="size">
                    <h3>出版社</h3>
                    <li><span><s:property value="#bookProfile.press"/></span></li>
                </ul>
                <ul class="size">
                    <h3>积分要求</h3>
                    <li><span>购买积分：<s:property value="#bookProfile.buyCredit"/></span></li>
                    <li><span>借阅积分：<s:property value="#bookProfile.borrowCredit"/></span></li>
                    <div class="clearfix"></div><br>
                </ul>
                <s:if test="#bookProfile.bookStatus=='BORROWED' && #bookProfile.reserved==false">
                    <p>此书正被借阅，可以预约</p>
                </s:if>
                <s:elseif test="#bookProfile.bookStatus=='BORROWED' && #bookProfile.reserved==true">
                    <p>此书正被借阅，已被预约</p>
                </s:elseif>
                <s:elseif test="#bookProfile.bookStatus=='EXCHANGED'">
                    <p>此书已被交换</p>
                </s:elseif>
                <s:elseif test="#bookProfile.canBorrow==true && #bookProfile.canExchange==true">
                    <p>此书可以借阅，可以交换</p>
                </s:elseif>
                <s:elseif test="#bookProfile.canBorrow==true && #bookProfile.canExchange==false">
                    <p>此书可以借阅</p>
                </s:elseif>
                <s:elseif test="#bookProfile.canBorrow==false && #bookProfile.canExchange==true">
                    <p>此书可以交换</p>
                </s:elseif>
                <div class="btn_form">
                    <s:if test="#bookProfile.bookStatus=='BORROWED'">
                        <s:if test="#bookProfile.reserved==false">
                            <s:if test="#bookProfile.canBorrow==true">
                                <a href="#" class="add-cart item_add">预约</a>
                            </s:if>
                        </s:if>
                    </s:if>
                    <s:elseif test="#bookProfile.bookStatus=='EXCHANGED'">

                    </s:elseif>
                    <s:else>
                        <s:if test="#bookProfile.canBorrow==true">
                            <a href="#" class="add-cart item_add" onclick="addToBorrowCart(<s:property value="#bookProfile.bookID"/>)">借阅</a>
                        </s:if>
                        <s:if test="#bookProfile.canExchange==true">
                            <a href="#" class="add-cart item_add">交换</a>
                            <a href="#" class="add-cart item_add">购买</a>
                        </s:if>
                    </s:else>
                </div>
                <div class="tag">
                    <p>分类 : <a href="#"><s:property value="#bookProfile.category1"/></a></p>
                    <p>标签 : <a href="#"><s:property value="#bookProfile.category2"/></a></p>
                </div>
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
                        <s:property value="#bookProfile.intro"/>
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
                        出版时间：<s:property value="#bookProfile.publishYear"/>年<s:property value="#bookProfile.publishMonth"/>月<br>
                        版次：<s:property value="#bookProfile.editionYear"/>年<s:property value="#bookProfile.editionMonth"/>月第<s:property value="#bookProfile.editionVersion"/>版<br>
                        页数：<s:property value="#bookProfile.page"/>页<br>
                        装帧：<s:property value="#bookProfile.bookBinding"/><br>
                        开本：<s:property value="#bookProfile.bookFormat"/><br>
                        成色：<s:property value="#bookProfile.bookQuality"/><br>
                        损毁情况：<s:property value="#bookProfile.bookDamage"/>
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


<div id="tip"></div>
</body>
</html>
<!-- footer -->
<jsp:include page="footer.jsp"/>