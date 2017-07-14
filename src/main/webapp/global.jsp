<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>

<html>
<head>

    <!-- Custom Theme files -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="keywords" content="" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!-- //Custom Theme files -->
    <link href="<%=path%>/css/bootstrap.css" type="text/css" rel="stylesheet" media="all">
    <link href="<%=path%>/css/style.css" type="text/css" rel="stylesheet" media="all">
    <link href="<%=path%>/css/form.css" rel="stylesheet" type="text/css" media="all" />
    <link href="<%=path%>/css/flexslider.css" rel="stylesheet"  type="text/css" media="screen" />
    <link href="<%=path%>/css/fileinput.css" rel="stylesheet" type="text/css"/>
    <link href="<%=path%>/css/fileinput.min.css" rel="stylesheet" type="text/css"/>
    <!-- js -->
    <script type="text/javascript">
        const base_url = '<%= basePath%>';
    </script>
    <script src="<%=path%>/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=path%>/js/bootstrap-3.1.1.min.js"></script>
    <script type="text/javascript" src="<%=path%>/js/jquery.validate.min.js"></script>
    <!-- //js -->
    <!-- cart -->
    <script src="<%=path%>/js/simpleCart.min.js"> </script>
    <!-- cart -->
    <!-- the jScrollPane script -->
    <script type="text/javascript" src="<%=path%>/js/jquery.jscrollpane.min.js"></script>

    <!-- //the jScrollPane script -->
    <script type="text/javascript" src="<%=path%>/js/jquery.mousewheel.js"></script>
    <!-- the mousewheel plugin -->

    <script>

        function showTip(tip,type){
            var $tip = $('#tip');
            $tip.attr('class', 'alert alert-' + type).text(tip).css('margin-left', - $tip.outerWidth() / 2).fadeIn(500).delay(1000).fadeOut(500);
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
</head>
</html>


