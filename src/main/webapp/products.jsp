<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>Products</title>
    <!-- Custom Theme files -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="" />


    <!-- js -->
    <script src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/bootstrap-3.1.1.min.js"></script>
    <!-- //js -->
    <!-- cart -->
    <script src="js/simpleCart.min.js"> </script>
    <!-- cart -->
    <!-- the jScrollPane script -->
    <script type="text/javascript" src="js/jquery.jscrollpane.min.js"></script>

    <!-- //the jScrollPane script -->
    <script type="text/javascript" src="js/jquery.mousewheel.js"></script>
    <!-- the mousewheel plugin -->



</head>
<body>


<script type="application/x-javascript">
    addEventListener
    ("load",
        function() { setTimeout(hideURLbar, 0); },
        false);
    function hideURLbar(){ window.scrollTo(0,1); }
</script>


<script type="text/javascript" id="sourcecode">
    $(function()
    {
        $('.scroll-pane').jScrollPane();
    });

    function showTip(tip,type){
        var $tip = $('#tip');
        $tip.attr('class', 'alert alert-' + type).text(tip).css('margin-left', - $tip.outerWidth() / 2).fadeIn(500).delay(1000).fadeOut(500);
    }

    function addToCart(bookID)
    {

        //showTip('Added to your cart!', 'success');
        console.log('amount: '+parseInt($('.item_quantity').first().val()));
        $.ajax({
            url: base_url + 'orderAction/addToCart',
            type: 'POST',
            data: {
                'bookID': bookID,
                'amount': $('.item_quantity').first().val()
            },
            success: function (msg) {
                //console.log(msg.success);

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

<style type="text/css">
    #tip {
        font-weight: bold;
        position: absolute;
        top: 50px;
        left: 50%;
        display: none;
        z-index: 9999;
    }
</style>




<!--products-->
<div class="products">
    <div class="container">
        <h2>图书浏览</h2>
        <div class="col-md-9 product-model-sec">






            <!--具体图书信息div-->





            <div class="product-grid">
                <a href="single.html">
                    <div class="more-product"><span> </span></div>
                    <div class="product-img b-link-stripe b-animate-go  thickbox">
                        <img src="images/m1.png" class="img-responsive" alt="">
                        <div class="b-wrapper">
                            <h4 class="b-animate b-from-left  b-delay03">
                                <button>View</button>
                            </h4>
                        </div>
                    </div>
                </a>
                <div class="product-info simpleCart_shelfItem">
                    <div class="product-info-cust prt_name">
                        <h4>实例 </h4>
                        <span class="item_price">打折后积分</span>
                        <div class="ofr">
                            <p class="pric1"><del>原始积分</del></p>
                            <p class="disc">折扣</p>
                        </div>
                        <input type="text" class="item_quantity" value="1" maxlength="8" />
                        <input type="button" class="item_add items" value="添加" onclick="addToCart(3)">
                        <div class="clearfix"> </div>
                    </div>
                </div>
            </div>







        </div>
        <div class="col-md-3 rsidebar span_1_of_left">
            <section  class="sky-form">
                <div class="product_right">
                    <h4 class="m_2"><span class="glyphicon glyphicon-minus" aria-hidden="true"></span>图书类型</h4>
                    <div class="tab1">
                        <ul class="place">
                            <li class="sort">小说</li>
                            <li class="by"><span class="glyphicon glyphicon-triangle-bottom" aria-hidden="true"></span></li>
                        </ul>
                        <div class="clearfix"> </div>
                        <div class="single-bottom">
                            <a href="#"><p>Cassata</p></a>
                            <a href="#"><p>Cheesecake</p></a>
                            <a href="#"><p>Coconut cake</p></a>
                            <a href="#"><p>Cupcake</p></a>
                        </div>
                    </div>
                    <div class="tab2">
                        <ul class="place">
                            <li class="sort">杂志</li>
                            <li class="by"><span class="glyphicon glyphicon-triangle-bottom" aria-hidden="true"></span></li>
                        </ul>
                        <div class="clearfix"> </div>
                        <div class="single-bottom">
                            <a href="#"><p>Delicious Cakes</p></a>
                            <a href="#"><p>Gingerbread</p></a>
                        </div>
                    </div>
                    <div class="tab3">
                        <ul class="place">
                            <li class="sort">教育书籍</li>
                            <li class="by"><span class="glyphicon glyphicon-triangle-bottom" aria-hidden="true"></span></li>
                        </ul>
                        <div class="clearfix"> </div>
                        <div class="single-bottom">
                            <a href="#"><p>Milk Cakes</p></a>
                            <a href="#"><p>Fruits Cakes</p></a>
                        </div>
                    </div>

                    <!--script-->
                    <script>
                        $(document).ready(function(){
                            $(".tab1 .single-bottom").hide();
                            $(".tab2 .single-bottom").hide();
                            $(".tab3 .single-bottom").hide();


                            $(".tab1 ul").click(function(){
                                $(".tab1 .single-bottom").slideToggle(300);
                                $(".tab2 .single-bottom").hide();
                                $(".tab3 .single-bottom").hide();

                            })
                            $(".tab2 ul").click(function(){
                                $(".tab2 .single-bottom").slideToggle(300);
                                $(".tab1 .single-bottom").hide();
                                $(".tab3 .single-bottom").hide();

                            })
                            $(".tab3 ul").click(function(){
                                $(".tab3 .single-bottom").slideToggle(300);
                                $(".tab1 .single-bottom").hide();
                                $(".tab2 .single-bottom").hide();

                            })
                        });
                    </script>
                    <!--//script -->
                </div>
            </section>
            <section  class="sky-form">
                <h4><span class="glyphicon glyphicon-minus" aria-hidden="true"></span>查询条件</h4>
                <div class="row row1 scroll-pane">

                    <div class="col col-4">
                        <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>可交换</label>
                        <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>可积分借阅</label>
                    </div>
                </div>
            </section>

        </div>
        <div class="clearfix"> </div>
    </div>
</div>
<!--//products-->
<div id="tip"></div>
<jsp:include page="footer.jsp"/>
</body>
</html>
