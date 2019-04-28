<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Modern Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design"/>
    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath}/statics/css/bootstrap.min.css" rel='stylesheet' type='text/css'/>
    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/statics/css/mycss.css" rel='stylesheet' type='text/css'/>
    <link href="${pageContext.request.contextPath}/statics/css/font-awesome.css" rel="stylesheet">
    <!-- jQuery -->
    <script src="${pageContext.request.contextPath}/statics/js/jquery.min.js"></script>
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);
    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
</head>
<link href="${pageContext.request.contextPath}/statics/css/style.css" rel='stylesheet' type='text/css'/>
<script language="JavaScript">
    window.onload = function () {
        //记住密码功能
        var str = getCookie("loginInfo");
        str = str.substring(0, str.length);
        var username = str.split("#")[0];
        var password = str.split("#")[1];
        //自动填充用户名和密码
        $("#mailName").val(username);
        $("#password").val(password);

        if("${newUser}"=="true"){
            $("#mailName").val("");
            $("#password").val("");
        }
    };

    //获取cookie
    function getCookie(cname) {
        var name = cname + "=";
        var ca = document.cookie.split(';');
        for (var i = 0; i < ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) == ' ') c = c.substring(1);
            if (c.indexOf(name) != -1) return c.substring(name.length, c.length);
        }
        return "";
    }
    function remember() {
        if(!($("#rememberMe").prop("checked"))){
        $("#rememberMe").val("false");}
    }
</script>
<body id="login">
<div class="login-logo">
</div>
<h2 class="form-heading"></h2>
<div class="app-cam">
    ${userMessage}
    <form action="login.html" method="post" name="form" >
        <input type="email" id="mailName" name="mailName" required="required" class="text form-control-static" placeholder="邮箱">
        <input type="password" id="password" name="password" required="required" placeholder="密码">
        <div class="submit"><input type="submit" value="登录">
        </div>
        <div class="lgn-save">
            <label>
                <span style="float: left;"><input class="checkbox" id="rememberMe" name="rememberMe" type="checkbox"
                                                  value="true"  checked="checked"    onclick="remember();">记住密码 </span>
            </label>
            <p style="float: right;margin: 5px 0 0 0;"><a href="getForget.html">忘记密码 ?</a></p>
        </div>
        <ul class="new">
            <div class="clearfix"></div>
        </ul>
    </form>
</div>
<div class="copy_layout login">
    <small>Power by SCAU <a href="#"><i>D5 lab</i></a></small>
</div>
<!-- Bootstrap Core JavaScript -->
<script src="${pageContext.request.contextPath}/statics/js/bootstrap.min.js"></script>
<jsp:include page="last.jsp"></jsp:include>
</body>
</html>
