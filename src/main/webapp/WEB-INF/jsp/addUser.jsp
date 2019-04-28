<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Modern Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>添加新用户</title>
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
<body>
<script language="JavaScript">
    var userExist = "true";
    function addUser() {
        $("#form").form("submit", {
            url: "addUser.html",
            onSubmit: function () {
                if ($("#mailName").val().search(/\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/) == -1) {
                    alert("请输入正确的邮箱格式！");
                    return false;
                }
                if (userExist == "true") {
                    alert("该用户已存在");
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
                if ($("#roles_id").val() == "" || $("#roles_id").val() == null) {
                    alert("请选择职位");
                    return false;
                }
                userpassword = prompt("请输入你的密码:");
                if (userpassword != "${user.password}" && userpassword != null) {
                    alert("你输入的密码有误");
                    return false;
                }

                if (userpassword == null || userpassword == "") {
                    return false;
                }

                return true;
            },
            success: function (data) {
                var result = JSON.parse(data);
                if (result.success == true) {
                    $("#mailName").val("");
                    $("#password").val("");
                    $("#passwordAgain").val("");
                    alert("添加成功!");
                }
                else {
                    alert("添加失败!");
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
                        userExist = "false";
                    }
                    if (data == "true") {
                        $("#message").html("该用户已存在");
                        userExist = "true";
                    }
                    if (mailName == "") {
                        $("#message").html("");
                    }
                }
            })
        }
    }
</script>
<div id="wrapper">
    <jsp:include page="header.jsp"></jsp:include>
    <div id="page-wrapper">
        <div class="graphs">
            <!-- 添加新用户 -->
            <div class="content_bottom">
                <div class="col-md-8 span_3">
                    <h3>添加新用户</h3>
                    <div class="panel-body">
                        <form role="form" id="form"
                              class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-2 control-label">邮箱</label>
                                <div class="col-md-8">
                                    <div class="input-group">
									<span class="input-group-addon">
										<i class="fa fa-envelope-o"></i>
									</span>
                                        <input type="email" class="form-control1" placeholder="邮箱"
                                               required="required" id="mailName" name="mailName" onchange="findUser()">
                                    </div>
                                </div>
                                <div id="message" style=" text-align: -webkit-center;"></div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-2 control-label">密码</label>
                                <div class="col-md-8">
                                    <div class="input-group">
									<span class="input-group-addon">
										<i class="fa fa-key"></i>
									</span>
                                        <input type="password" class="form-control1" name="password" id="password"
                                               required="required" placeholder="6-16位密码"
                                               onkeyup="FirstVertify()">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-2 control-label">密码确认</label>
                                <div class="col-md-8">
                                    <div class="input-group">
									<span class="input-group-addon">
										<i class="fa fa-key"></i>
									</span>
                                        <input type="password" class="form-control1" placeholder="确认6-16位密码"
                                               required="required"
                                               name="passwordAgain" id="passwordAgain"
                                               onkeyup="FirstVertify()">
                                    </div>
                                </div>
                                <div id="passwordMessage" style=" text-align: -webkit-center;"></div>
                            </div>
                            <div class="form-group">
                                <label for="roles_id" class="col-sm-2 control-label">职位</label>
                                <div class="col-sm-8" id="option">
                                    <select name="roles_id" id="roles_id" class="form-control1">
                                        <c:if test="${fn:contains(role.menu_Id,'2')}">
                                            <option value='2'>经理</option>
                                        </c:if>
                                        <c:if test="${fn:contains(role.menu_Id,'3')}">
                                            <option value='3'>高管</option>
                                        </c:if>
                                        <c:if test="${fn:contains(role.menu_Id,'4')}">
                                            <option value='4'>员工</option>
                                        </c:if>
                                    </select></div>
                            </div>
                        </form>

                        <div class="row">
                            <div class="col-sm-8 col-sm-offset-2">
                                <button class="btn-group btn" onclick="addUser();">添加</button>
                            </div>
                        </div>

                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
            <!-- /#page-wrapper -->
        </div>
    </div>
    <jsp:include page="last.jsp"></jsp:include>
</body>
</html>