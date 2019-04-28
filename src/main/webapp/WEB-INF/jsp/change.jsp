<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Modern Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>修改信息</title>
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
    <script src="${pageContext.request.contextPath}/statics/js/jquery.form.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/common.js"></script>
    <noscript>
        <p>本页面需要浏览器支持（启用）Javascript。</p>
    </noscript>
</head>
<script language="JavaScript">
    function sendMail() {
        var i = 60;
        var mail = $("#mailName").val();
        $.ajax({
                type: "post",
                url: "sendMail.do",
                data: {"mail": mail},
                success: function (data) {
                    if (data == "false") {
                        alert("发送失败！");
                        var interval = setInterval(wait, 1000);
                    }
                    else {
                        $("#Num").val(data);
                        alert(" 发送成功，请在邮箱中查找验证码");
                        var interval = setInterval(wait, 1000);}
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
            }
        );
    }
    function change() {
        $("#form").form("submit", {
            url: "userChange.do",
            onSubmit: function () {
                if ($("#Num").val() != $("#indentifyNum").val()) {
                    alert("验证码不正确");
                    return false;
                }
                if ($("#indentifyNum").val() == "") {
                    alert("验证码不能为空");
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
                if(password.length>16||password.length<6){
                    alert("密码长度在6到16位");
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
                    $("#indentifyNum").val("");
                    $("#password").val("");
                    $("#passwordAgain").val("");
                    alert("修改成功,下一次登录生效！");
                }
                else {
                    alert("修改失败!");
                }
            }
        });
    }

</script>
<body>
<div id="wrapper">
    <jsp:include page="header.jsp"></jsp:include>
    <div id="page-wrapper">
        <div class="graphs">
            <!----Calender -------->
            <!-- 表单 -->
            <h3>修改个人信息</h3>
            <form class="form-horizontal" id="form">
                <div class="form-group">
                    <div class="col-sm-8">
                        <input type="text" class="form-control1" type="text" id="mailName" name="mailName"
                               required="required" placeholder="邮箱" value="${user.mailName}" readonly="readonly"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-8">
                        <input type="text" class="form-control1" type="text" id="place_name" name="place_name"
                               required="required" placeholder="职位" value="${role.place_name}" readonly="readonly"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-8">
                        <input type="text" class="form-control1" id="indentifyNum" required="required"
                               placeholder="验证码">
                    </div>
                    <div class="col-sm-2">
                        <input type="button" id="btn" class="btn-group btn" onclick="sendMail();return false;"
                               value="发送验证码"></input>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-8">
                        <input type="password" class="form-control1" placeholder="6-16位密码"
                               minlength="6" maxlength="16" onkeyup='FirstVertify();' id="password" name="password">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-8">
                        <input type="password" class="form-control1" id="passwordAgain" name="passwordAgain"
                               minlength="6" maxlength="16" onkeyup='FirstVertify();' placeholder="确定6-16位新密码">
                    </div>
                    <div id="passwordMessage" style=" text-align: -webkit-center;"></div>
                </div>

                <div id="message"></div>
                <input type="hidden" id="roles_id" name="roles_id" value="${user.roles_id}">
                <input type="hidden" id="Num">
            </form>
            <div class="col-sm-6">
                <button class="btn-group btn" onclick="change();">修改</button>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
    <!-- /#wrapper -->
    <!-- Bootstrap Core JavaScript -->
</div>
<script src="${pageContext.request.contextPath}/statics/js/bootstrap.min.js"></script>
<jsp:include page="last.jsp"></jsp:include>
</body>
</html>
