<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="global.jsp"%>
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
<!-- header -->
<s:action name="header" executeResult="true" namespace="/"/><!-- home page -->

<script type="application/x-javascript">
    addEventListener
    ("load",
        function() { setTimeout(hideURLbar, 0); },
        false);
    function hideURLbar(){ window.scrollTo(0,1); }
</script>


<script type="text/javascript" id="sourcecode">

    var amountPerPage;
    var totalBookAmount;
    var pageCount;
    var currPage;
    var isLastBlock =<s:property value="#isLastPart"/>;

    $.urlParam = function(name){
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        if (r != null)
            return unescape(r[2]);
        return 0; //返回参数值
    }
    // showAllBooks?part=2
    var currBlock= parseInt($.urlParam('part')); //2
    if(currBlock==0){
        currBlock=1;
    }
    var firstPage = parseInt($.urlParam('firstPage'));
    if(firstPage==0){
        firstPage=1;
    }
    var prevBlock = currBlock-1;
    var succBlock = currBlock+1;

   function switchPage(pageNo){
        $('.product-grid').hide();
        $('.active').replaceWith("<li id='"+currPage+"'><a href='javascript:switchPage("+currPage+");'>"+currPage+"</a><li>");
        $('#'+pageNo).replaceWith("<li id='"+pageNo+"' class='active'>"+pageNo+"<li>");
        var end=(parseInt(pageNo)-1)*parseInt(amountPerPage)+parseInt(amountPerPage);
       console.log("end: "+end);
       console.log("last product id: "+parseInt(totalBookAmount));
       for(var j=(pageNo-1)*amountPerPage;j<end&&j<parseInt(totalBookAmount);j++){
            console.log("show product"+j);
            $('#product'+j).show();
        }
        currPage=pageNo;

    }



    //当页面第一次加载时
    $(document).ready(function() {
        currPage=firstPage;
        amountPerPage = $("#selectAmountPerPage").val();
        totalBookAmount = '<s:property value="#totalBookAmount"/>';
        pageCount = Math.ceil(totalBookAmount / amountPerPage);
        var lastPage= '<s:property value="#firstPage"/>';
        var succPage= firstPage+pageCount;
        if(lastPage>0&&prevBlock>0){
            $('#pagination-digg').append("<li class='previous'><a href='?part="+prevBlock+"&firstPage="+lastPage+"'>&laquo;上一部分 </a></li>");

        }
        else{
            lastPage=1;
        }
        if(pageCount>=1){
            for(var i=firstPage;i<succPage;i++){
                if(i==currPage){
                    $('#pagination-digg').append("<li id='"+i+"' class='active'>"+i+"<li>");

                }else{
                    $('#pagination-digg').append("<li id='"+i+"'><a href='javascript:switchPage("+i+");'>"+i+"</a><li>");
                }

            }
            $('.product-grid').hide();
            for(var j=0;j<amountPerPage&&j<parseInt(totalBookAmount);j++){
                $('#product'+j).show();
            }
        }
        if(!isLastBlock){
            $('#pagination-digg').append("<li class='next'><a href='?part="+succBlock+"&firstPage="+lastPage+"'>下一部分&raquo;</a></li>");

        }



        //当修改每页显示的数量后
        $("#selectAmountPerPage").change(function () {
            console.log("switch amount per page");
            currPage=firstPage;
            amountPerPage = $("#selectAmountPerPage").val();
            totalBookAmount = '<s:property value="#totalBookAmount"/>';
            pageCount = Math.ceil(totalBookAmount / amountPerPage);
            var lastPage= '<s:property value="#firstPage"/>';
            var succPage = firstPage + pageCount;
            $('#pagination-digg').empty();

            if(lastPage>0&&prevBlock>0){
                $('#pagination-digg').append("<li class='previous'><a href='?part="+prevBlock+"&firstPage="+lastPage+"'>&laquo;上一部分</a></li>");

            }
            else{
                lastPage=1;
            }
            if(pageCount>=1){
                for(var i=firstPage;i<succPage;i++){
                    if(i==currPage){
                        $('#pagination-digg').append("<li id='"+i+"' class='active'>"+i+"<li>");

                    }else{
                        $('#pagination-digg').append("<li id='"+i+"'><a href='javascript:switchPage("+i+");'>"+i+"</a><li>");
                    }

                }
                $('.product-grid').hide();
                var start=parseInt((currPage-1))*parseInt(amountPerPage);
                var end= parseInt(start)+parseInt(amountPerPage);
                console.log("end: "+end);
                console.log("last product id: "+parseInt(totalBookAmount));
                for(var j=start;j<end&&j<parseInt(totalBookAmount);j++){
                    console.log("show product"+j);
                    $('#product'+j).show();
                }
            }
            if(!isLastBlock){
                $('#pagination-digg').append("<li class='next'><a href='?part="+succBlock+"&firstPage="+lastPage+"'>下一部分 &raquo;</a></li>");
            }

        });
    });




    $(function()
    {
        $('.scroll-pane').jScrollPane();
    });



    //左侧分类栏js脚本
    $(document).ready(function(){
        $(".tab1 .single-bottom").hide();
    });

    //每页显示数量选框js脚本

</script>



<!--products-->
<div class="products">

    <div class="container">

        <h2>图书浏览</h2>

        <div class="col-md-9 product-model-sec">
            <div class="clearfix"> </div>
            <div class="form-group form-group-auto" style="float: right;">
                <label>每页显示数量</label>
                <select id="selectAmountPerPage" class="form-control form-control-noNewline"
                        style="width: 50%; display:inline;">
                    <option value="3" selected>3</option>
                    <option value="6">6</option>
                    <option value="9">9</option>
                </select>
            </div>
            <div class="clearfix"> </div>





            <!--具体图书信息div，使用struts迭代器-->
            <s:iterator value="#allBooks"  status="st">
                <div id="product<s:property value="#st.index"/>" class="product-grid" style="display: none;">
                    <a href="<%=path%>/bookAction/showBookProfile?bookID=<s:property value="bookID"/>">
                        <div class="more-product"><span> </span></div>
                        <div class="product-img b-link-stripe b-animate-go  thickbox">
                            <!--图书封面图片 ，需要imageID-->
                            <img src="<%=path%>/imageAction/showImage?imageID=<s:property value="imageID"/>" class="img-responsive" alt="">
                            <div class="b-wrapper">
                                <h4 class="b-animate b-from-left  b-delay03">
                                    <button>View</button>
                                </h4>
                            </div>
                        </div>
                    </a>
                    <div class="product-info simpleCart_shelfItem">
                        <div class="product-info-cust prt_name">
                            <h4><s:property value="bookName"/></h4>
                            <span class="book-isbn">ISBN:<s:property value="isbn"/></span>
                            <div class="ofr">
                                <p class="pric1">作者：<s:property value="author"/></p><br>
                                <p class="pric1">分类：<s:property value="category1"/>&nbsp;标签：<s:property value="category2"/></p><br>
                                <p class="disc">当前状态：<s:if test="bookStatus==IDLE">空闲</s:if>
                                    <s:elseif test="bookStatus==BORROWED">正被借阅</s:elseif>
                                    <s:else>已被交换或购买</s:else></p><br>
                                <p class="disc">预约状态：<s:if test="reserved==0">未被预约</s:if><s:else>已被预约</s:else></p>
                            </div>
                            <div class="clearfix"> </div>
                        </div>
                    </div>
                </div>
            </s:iterator>
            <div class="clearfix"> </div>
            <ul id="pagination-digg" style="float: right;">
            </ul>
        </div>
        <div class="col-md-3 rsidebar span_1_of_left">
            <section  class="sky-form">
                <div class="product_right">
                    <h4 class="m_2"><span class="glyphicon glyphicon-minus" aria-hidden="true"></span>图书类型</h4>
                    <s:iterator value="#category1List"  status="st1">
                        <div  id="cate<s:property value="#st1.index"/>" class="tab1">
                            <script>
                                $(document).ready(function () {
                                    $("#cate<s:property value='#st1.index'/> ul").click(function(){
                                        $(".tab1 .single-bottom").hide();
                                        $("#cate<s:property value='#st1.index'/> .single-bottom").slideToggle(300);

                                    });
                                });

                            </script>
                            <ul class="place">
                                <li class="sort"><s:property value="category1Name"/></li>
                                <li class="by"><span class="glyphicon glyphicon-triangle-bottom" aria-hidden="true"></span></li>
                            </ul>
                            <div class="clearfix"> </div>
                            <div class="single-bottom">
                                <a onclick=""><p><s:property value="category1Name"/></p></a>
                                <s:iterator value="category2List" status="st2">
                                    <a onclick=""><p><s:property value="category2Name"/></p></a>
                                </s:iterator>
                            </div>

                        </div>
                    </s:iterator>
                </div>
            </section>
            <section  class="sky-form">
                <h4><span class="glyphicon glyphicon-minus" aria-hidden="true"></span>图书状态</h4>
                <div class="row row1 scroll-pane">

                    <div class="col col-4">
                        <label class="checkbox"><input type="checkbox" name="checkbox" onclick=""><i></i>可交换</label>
                        <label class="checkbox"><input type="checkbox" name="checkbox" onclick=""><i></i>可积分借阅</label>
                        <label class="checkbox"><input type="checkbox" name="checkbox" onclick=""><i></i>可预约</label>
                    </div>
                </div>
            </section>
            <section  class="sky-form">
                <h4><span class="glyphicon glyphicon-minus" aria-hidden="true"></span>图书年份</h4>
                <div class="row row1 scroll-pane">

                    <div class="col col-4">
                        <a class="add-cart item_add" onclick="">2017</a>
                        <a class="add-cart item_add" onclick="">2016</a><br>
                        <a class="add-cart item_add" onclick="">2015</a>
                        <a class="add-cart item_add" onclick="">2014</a><br>
                    </div>
                </div>
            </section>


        </div>
        <div class="clearfix"> </div>



        <style type="text/css" media="screen">
            #pagination-digg li {
                border:0; margin:0; padding:0; font-size:11px;
                list-style:none; /* savers */ float:left;
            }
            #pagination-digg a {
                border:solid 1px #9aafe5; margin-right:2px;
            }
            #pagination-digg .previous-off,#pagination-digg .next-off  {
                border:solid 1px #DEDEDE; color:#888888; display:block; float:left;
                font-weight:bold; margin-right:2px; padding:3px 4px;
            }
            #pagination-digg .next a,#pagination-digg .previous a {
                font-weight:bold;
            }
            #pagination-digg .active {
                background:#2e6ab1; color:#FFFFFF; font-weight:bold; display:block;
                float:left; padding:4px 6px; /* savers */ margin-right:2px;
            }
            #pagination-digg a:link,#pagination-digg a:visited { color:#0e509e; display:block;
                float:left; padding:3px 6px; text-decoration:none;
            }
            #pagination-digg a:hover {
                border:solid 1px #0e509e;
            }
        </style>

    </div>
</div>
<!--//products-->
<jsp:include page="footer.jsp"/>
</body>
</html>
