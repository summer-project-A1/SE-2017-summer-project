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



<!--products-->
<div class="products">
    <div class="container">
        <h2>图书浏览</h2>
        <div class="col-md-9 product-model-sec">


            <!--具体图书信息div，使用struts迭代器-->
            <s:iterator value="#allBooks" status="st">
                <div class="product-grid">
                    <a href="<%=path%>/bookAction/showBookProfile?bookID=<s:property value="bookID"/>">
                        <div class="more-product"><span> </span></div>
                        <div class="product-img b-link-stripe b-animate-go  thickbox">
                            <!--图书图片 ，需要imageID-->
                            <img src="<%=path%>/images/m1.png" class="img-responsive" alt="">
                            <div class="b-wrapper">
                                <h4 class="b-animate b-from-left  b-delay03">
                                    <button>View</button>
                                </h4>
                            </div>
                        </div>
                    </a>
                    <div class="product-info simpleCart_shelfItem">
                        <div class="product-info-cust prt_name">
                            <h3><s:property value="bookName"/></h3>
                            <span class="book-isbn">ISBN:<s:property value="isbn"/></span>
                            <div class="ofr">
                                <p class="pric1">作者：<s:property value="author"/></p><br>
                                <p class="pric1">分类：<s:property value="category"/></p><br>
                                <p class="disc">当前状态：<s:if test="status.toString()==@common.constants.BookStatus@IDLE.toString()">空闲</s:if><s:elseif test="status.toString()==@common.constants.BookStatus@BORROWED.toString()">正被借阅</s:elseif><s:else>正被交换</s:else></p><br>
                                <p class="disc">预约状态：<s:if test="reserved==0">未被预约</s:if><s:else>已被预约</s:else></p>
                            </div>
                            <input type="text" class="item_quantity" value="1" maxlength="8" />
                            <input type="button" class="item_add items" value="添加" onclick="addToCart(<s:property value="bookID"/>)">
                            <div class="clearfix"> </div>
                        </div>
                    </div>
                </div>
            </s:iterator>

        </div>
        <div class="col-md-3 rsidebar span_1_of_left">
            <section  class="sky-form">
                <div class="product_right">
                    <h4 class="m_2"><span class="glyphicon glyphicon-minus" aria-hidden="true"></span>图书类型</h4>
                    <div class="tab1">
                        <ul class="place">
                            <li class="sort">类型1</li>
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
                            <li class="sort">类型2</li>
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
                            <li class="sort">类型3</li>
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
