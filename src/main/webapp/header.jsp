<%@ page contentType="text/html; charset=UTF-8"%>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
<!DOCTYPE html>
<html>
<head>
    <!-- //Custom Theme files -->
    <link href="css/bootstrap.css" type="text/css" rel="stylesheet" media="all">
    <link href="css/style.css" type="text/css" rel="stylesheet" media="all">
    <link href="css/form.css" rel="stylesheet" type="text/css" media="all" />
    <script type="text/javascript">
        const base_url = '<%= basePath%>';
    </script>
</head>
</html>