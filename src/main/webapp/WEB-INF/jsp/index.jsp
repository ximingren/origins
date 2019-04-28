<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Modern Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>首页</title>
    <link href="${pageContext.request.contextPath}/statics/css/bootstrap.min.css" rel='stylesheet' type='text/css'/>
    <link href="${pageContext.request.contextPath}/statics/css/style.css" rel='stylesheet' type='text/css'/>
    <link href="${pageContext.request.contextPath}/statics/css/font-awesome.css" rel="stylesheet">
    <script language="JavaScript" src="${pageContext.request.contextPath}/statics/js/latest.js"></script>
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);
    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
    <!-- jQuery(necessary for Bootstrap's JavaScript plugins) -->
    <!-- jQuery -->
    <script src="${pageContext.request.contextPath}/statics/js/jquery.min.js"></script>
    <noscript>
        <p>本页面需要浏览器支持（启用）Javascript。</p>
    </noscript>
</head>
<script language="JavaScript">
    $(function () {
        latest(1);
    })
</script>
<body>
<div id="wrapper">
    <jsp:include page="header.jsp"></jsp:include>
    <div id="page-wrapper">
        <div class="graphs" id="ajaxTable">
        </div>
        <!-- /#page-wrapper -->
        <div id="pageBar">
            <div class="clearfix"></div>
        </div>
    </div>
</div>
<jsp:include page="last.jsp"></jsp:include>
</body>
</html>