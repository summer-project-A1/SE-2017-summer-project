<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@include file="header.jsp"%>
<html>
<head>
    <title><s:property value="#title"/></title>

    <!-- Custom Theme files -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="keywords" content="" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
</head>
<body>
<!-- header -->

<!-- home page -->
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
            <div class="col-md-8 gallery-grid glry-one">
                <a href="<%=path%>/bookAction/showBookProfile?bookID=<s:property value="#recommendBook.bookID"/>"><img src="images/g1.jpg" class="img-responsive" alt=""/>
                    <div class="gallery-info">
                        <p><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span> view</p>
                        <a class="shop" href="<%=path%>/bookAction/showBookProfile?bookID=<s:property value="#recommendBook.bookID"/>">SHOP NOW</a>
                        <div class="clearfix"> </div>
                    </div>
                </a>
                <div class="galy-info">
                    <p>Lorem Ipsum is simply</p>
                    <div class="galry">
                        <div class="prices">
                            <h5 class="item_price">$95.00</h5>
                        </div>
                        <div class="rating">
                            <span>鈽�</span>
                            <span>鈽�</span>
                            <span>鈽�</span>
                            <span>鈽�</span>
                            <span>鈽�</span>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </div>
            </div>
            <div class="col-md-4 gallery-grid glry-two">
                <a href="<%=path%>/bookAction/showBookProfile?bookID=<s:property value="#recommendBook.bookID"/>"><img src="images/g2.jpg" class="img-responsive" alt=""/>
                    <div class="gallery-info galrr-info-two">
                        <p><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span> view</p>
                        <a class="shop" href="<%=path%>/bookAction/showBookProfile?bookID=<s:property value="#recommendBook.bookID"/>">SHOP NOW</a>
                        <div class="clearfix"> </div>
                    </div>
                </a>
                <div class="galy-info">
                    <p>Lorem Ipsum is simply</p>
                    <div class="galry">
                        <div class="prices">
                            <h5 class="item_price">$95.00</h5>
                        </div>
                        <div class="rating">
                            <span>鈽�</span>
                            <span>鈽�</span>
                            <span>鈽�</span>
                            <span>鈽�</span>
                            <span>鈽�</span>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </div>
            </div>
            <!--struts 迭代器 迭代8次 -->
            <div class="col-md-3 gallery-grid ">
                <a href="<%=path%>/bookAction/showBookProfile?bookID=<s:property value="#recommendBook.bookID"/>"><img src="images/g3.png" class="img-responsive" alt=""/>
                    <div class="gallery-info">
                        <p><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span> view</p>
                        <a class="shop" href="single.html">SHOP NOW</a>
                        <div class="clearfix"> </div>
                    </div>
                </a>
                <div class="galy-info">
                    <p>Lorem Ipsum is simply</p>
                    <div class="galry">
                        <div class="prices">
                            <h5 class="item_price">$95.00</h5>
                        </div>
                        <div class="rating">
                            <span>鈽�</span>
                            <span>鈽�</span>
                            <span>鈽�</span>
                            <span>鈽�</span>
                            <span>鈽�</span>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </div>
            </div>
            <div class="col-md-3 gallery-grid ">
                <a href="products.html"><img src="images/g4.png" class="img-responsive" alt=""/>
                    <div class="gallery-info">
                        <p><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span> view</p>
                        <a class="shop" href="single.html">SHOP NOW</a>
                        <div class="clearfix"> </div>
                    </div>
                </a>
                <div class="galy-info">
                    <p>Lorem Ipsum is simply</p>
                    <div class="galry">
                        <div class="prices">
                            <h5 class="item_price">$95.00</h5>
                        </div>
                        <div class="rating">
                            <span>鈽�</span>
                            <span>鈽�</span>
                            <span>鈽�</span>
                            <span>鈽�</span>
                            <span>鈽�</span>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </div>
            </div>
            <div class="col-md-3 gallery-grid ">
                <a href="products.html"><img src="images/g5.png" class="img-responsive" alt=""/>
                    <div class="gallery-info">
                        <p><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span> view</p>
                        <a class="shop" href="single.html">SHOP NOW</a>
                        <div class="clearfix"> </div>
                    </div>
                </a>
                <div class="galy-info">
                    <p>Lorem Ipsum is simply</p>
                    <div class="galry">
                        <div class="prices">
                            <h5 class="item_price">$95.00</h5>
                        </div>
                        <div class="rating">
                            <span>鈽�</span>
                            <span>鈽�</span>
                            <span>鈽�</span>
                            <span>鈽�</span>
                            <span>鈽�</span>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </div>
            </div>
            <div class="col-md-3 gallery-grid ">
                <a href="products.html"><img src="images/g6.png" class="img-responsive" alt=""/>
                    <div class="gallery-info">
                        <p><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span> view</p>
                        <a class="shop" href="single.html">SHOP NOW</a>
                        <div class="clearfix"> </div>
                    </div>
                </a>
                <div class="galy-info">
                    <p>Lorem Ipsum is simply</p>
                    <div class="galry">
                        <div class="prices">
                            <h5 class="item_price">$95.00</h5>
                        </div>
                        <div class="rating">
                            <span>鈽�</span>
                            <span>鈽�</span>
                            <span>鈽�</span>
                            <span>鈽�</span>
                            <span>鈽�</span>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </div>
            </div>
            <div class="col-md-3 gallery-grid ">
                <a href="products.html"><img src="images/g7.png" class="img-responsive" alt=""/>
                    <div class="gallery-info">
                        <p><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span> view</p>
                        <a class="shop" href="single.html">SHOP NOW</a>
                        <div class="clearfix"> </div>
                    </div>
                </a>
                <div class="galy-info">
                    <p>Lorem Ipsum is simply</p>
                    <div class="galry">
                        <div class="prices">
                            <h5 class="item_price">$95.00</h5>
                        </div>
                        <div class="rating">
                            <span>鈽�</span>
                            <span>鈽�</span>
                            <span>鈽�</span>
                            <span>鈽�</span>
                            <span>鈽�</span>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </div>
            </div>
            <div class="col-md-3 gallery-grid ">
                <a href="products.html"><img src="images/g8.png" class="img-responsive" alt=""/>
                    <div class="gallery-info">
                        <p><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span> view</p>
                        <a class="shop" href="single.html">SHOP NOW</a>
                        <div class="clearfix"> </div>
                    </div>
                </a>
                <div class="galy-info">
                    <p>Lorem Ipsum is simply</p>
                    <div class="galry">
                        <div class="prices">
                            <h5 class="item_price">$95.00</h5>
                        </div>
                        <div class="rating">
                            <span>鈽�</span>
                            <span>鈽�</span>
                            <span>鈽�</span>
                            <span>鈽�</span>
                            <span>鈽�</span>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </div>
            </div>
            <div class="col-md-3 gallery-grid ">
                <a href="products.html"><img src="images/g9.png" class="img-responsive" alt=""/>
                    <div class="gallery-info">
                        <p><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span> view</p>
                        <a class="shop" href="single.html">SHOP NOW</a>
                        <div class="clearfix"> </div>
                    </div>
                </a>
                <div class="galy-info">
                    <p>Lorem Ipsum is simply</p>
                    <div class="galry">
                        <div class="prices">
                            <h5 class="item_price">$95.00</h5>
                        </div>
                        <div class="rating">
                            <span>鈽�</span>
                            <span>鈽�</span>
                            <span>鈽�</span>
                            <span>鈽�</span>
                            <span>鈽�</span>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </div>
            </div>
            <div class="col-md-3 gallery-grid ">
                <a href="products.html"><img src="images/g10.png" class="img-responsive" alt=""/>
                    <div class="gallery-info">
                        <p><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span> view</p>
                        <a class="shop" href="single.html">SHOP NOW</a>
                        <div class="clearfix"> </div>
                    </div>
                </a>
                <div class="galy-info">
                    <p>Lorem Ipsum is simply</p>
                    <div class="galry">
                        <div class="prices">
                            <h5 class="item_price">$95.00</h5>
                        </div>
                        <div class="rating">
                            <span>鈽�</span>
                            <span>鈽�</span>
                            <span>鈽�</span>
                            <span>鈽�</span>
                            <span>鈽�</span>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </div>
            </div>
        </div>
        <div id="tip"> </div>
    </div>
</div>

<!-- footer -->
<jsp:include page="footer.jsp"/>
</body>
</html>
