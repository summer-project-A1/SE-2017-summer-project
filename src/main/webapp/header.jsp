<%@ page contentType="text/html; charset=UTF-8"%>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
<!DOCTYPE html>
<html>
<head>
    <!-- //Custom Theme files -->
    <link href="<%=path%>/css/bootstrap.css" type="text/css" rel="stylesheet" media="all">
    <link href="<%=path%>/css/style.css" type="text/css" rel="stylesheet" media="all">
    <link href="<%=path%>/css/form.css" rel="stylesheet" type="text/css" media="all" />
    <script type="text/javascript">
        const base_url = '<%= basePath%>';
    </script>
    <!-- js -->

    <script src="<%=path%>/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=path%>/js/bootstrap-3.1.1.min.js"></script>
    <!-- //js -->
    <!-- cart -->
    <script src="<%=path%>/js/simpleCart.min.js"> </script>
    <!-- the jScrollPane script -->
    <script type="text/javascript" src="<%=path%>/js/jquery.jscrollpane.min.js"></script>

    <!-- //the jScrollPane script -->
    <script type="text/javascript" src="<%=path%>/js/jquery.mousewheel.js"></script>
    <!-- the mousewheel plugin -->
</head>
</html>