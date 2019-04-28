<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>忘记密码</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Modern Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design"/>
    <!-- Bootstrap Core CSS -->
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);
    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
    <link href="${pageContext.request.contextPath}/statics/css/bootstrap.min.css" rel='stylesheet' type='text/css'/>
    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/statics/css/mycss.css" rel='stylesheet' type='text/css'/>
    <link href="${pageContext.request.contextPath}/statics/css/font-awesome.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/style.css" rel='stylesheet' type='text/css'/>
    <!-- jQuery -->
    <script src="${pageContext.request.contextPath}/statics/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/jquery.form.js"></script>
</head>
<script language="JavaScript">
    function sendMail() {
        var i = 60;
        var mailName = $("#mailName").val();
        if (mailName == null || mailName == "") {
            alert("邮箱地址不能为空！");
        } else {
            $.ajax({
                type: "post",
                url: "sendMail.do",
                data: {"mail": mailName},
                success: function (data) {
                    if (data == "false") {
                        alert("发送失败！");
                        var interval = setInterval(wait, 1000);
                    }
                    else {
                        $("#Num").val(data);
                        alert(" 发送成功，请在邮箱中查找验证码");
                        var interval = setInterval(wait, 1000);
                    }
                    function wait() {
                        if (i > 0) {
                            i--;
                            document.getElementById("btn").disabled = true;
                            $('#btn').val("重新发送(" + i + ")");
                        }
                        else {
                            document.getElementById("btn").disabled = false;
                            $('#btn').val("发送验证码");
                            clearInterval(interval);
                        }
                    }
                }
            });
        }
    }
    function forget() {
        var userExist = "true";
        $("#form").form("submit", {
            url: "forget.do",
            onSubmit: function () {
                if ($("#Num").val() != $("#indentifyNum").val()) {
                    alert("验证码不正确");
                    return false;
                }
                if ($("#indentifyNum").val() == "") {
                    alert("验证码不能为空");
                    return false;
                }
                if (userExist=="false") {
                    alert("找不到该用户");
                    return false;
                }
                var password = $("#password").val();
                var passwordagain = $("#passwordAgain").val();
                if (password == "" || passwordagain == "") {
                    alert("密码不能为空");
                    return false;
                }
                if (password.search(/\s/) != -1 || passwordagain.search(/\s/) != -1) {
                    alert("密码中不能有空格");
                    return false;
                }
                if (password.length > 16 || password.length < 6) {
                    alert("密码长度在6-16位");
                    return false;
                }
                if (password != passwordagain && passwordagain != "") {
                    alert("两次输入的密码不一致");
                    return false;
                }
                return true;
            },
            success: function (data) {
                var result = JSON.parse(data);
                if (result.success == true) {
                    alert("重置密码成功！");
                    window.location.href = "${pageContext.request.contextPath}/";
                }
                else {
                    alert("重置密码失败!");
                }
            }
        });
    }
    function findUser() {
        var mailName = $("#mailName").val();
        $("#message").html("");
        if (mailName != "") {
            $.ajax({
                url: "findUser",
                data: {
                    "mailName": mailName
                },
                type: "post",
                success: function (data) {
                    if (data == "false") {
                        $("#message").html("找不到该用户");
                        userExist="false";
                    }
                    if(data=="true"){
                        userExist="true";
                    }
                    if (mail == "") {
                        $("#message").html("");
                    }
                }
            })
        }
    }
</script>
<body id="login">
<div class="login-logo">
</div>
<h2 class="form-heading"></h2>
<div class="app-cam">
    <form id="form">
        <input type="email" id="mailName" name="mailName" required="required" class="email" placeholder="邮箱"
               style="background: #fff;">
        <input type="text" id="indentifyNum" name="indentifyNum" class="text" required="required" placeholder="验证码"
               style="width: 47%;background: #fff;">
        <input type="button" id="btn" onclick="sendMail();return false;" value="发送验证码"
               style="padding: 9px; margin: 2px; float:right; color: #999; font-size: 0.85em; outline: none; font-weight: 300; border: none; background: #ddd;border-radius: 2px; -webkit-border-radius: 2px;">
        </input>
        <input type="password" id="password" name="password" required="required" class="text" placeholder="6-16位新密码"
               style="background: #fff;">
        <input type="password" id="passwordAgain" name="passwordAgain" required="required" class="text"
               placeholder="确认6-16位新密码" style="background: #fff;">
        <input type="hidden" id="Num">
        <div id="message"></div>
    </form>
    <div class="submit"><input type="submit" value="修改" onclick="forget();">
    </div>
    <div class="lgn-save">
    </div>
    <ul class="new">
        <div class="clearfix"></div>
    </ul>
</div>
<div class="copy_layout login">
    <small>Power by SCAU <a href="#"><i>D5 lab</i></a></small>
</div>
<!-- Bootstrap Core JavaScript -->
<script src="${pageContext.request.contextPath}/statics/js/bootstrap.min.js"></script>
<jsp:include page="last.jsp"></jsp:include>
</body>
</html>
