<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Modern Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>职位调整</title>
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
    <noscript>
        <p>本页面需要浏览器支持（启用）Javascript。</p>
    </noscript>
</head>
<body>
<script language="JavaScript">
    var userExist = "true";
    function change() {
        $("#form").form("submit", {
            url: "adjust.htm",
            onSubmit: function () {
                if ($("#mailName").val().search(/\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/) == -1) {
                    alert("请输入正确的邮箱格式！");
                    return false;
                }
                if ($("#mailName").val() == "${user.mailName}") {
                    alert("对不起，你不能修改自己的职位");
                    $("#mailName").val("");
                    return false;
                }
                if (userExist == "false") {
                    alert("找不到该用户");
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
                    alert("调整成功!");
                }
                if (result.success == "error") {
                    alert("不能调整职位等于或高于自己的用户!");
                }
                if (result.success == false) {
                    alert("调整失败!");
                }
                $("#mailName").val("")
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
                        userExist = "false";
                    }
                    if (data == "true") {
                        userExist = "true";
                    }
                    if (mail == "") {
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
            <div class="content_bottom">
                <div class="col-md-8 span_3">
                    <h3>职位调整</h3>
                    <div class="panel-body">
                        <form role="form" class="form-horizontal" id="form">
                            <div class="form-group">
                                <div class="col-md-8">
                                    <div class="input-group">
                                        <input type="email" id="mailName" name="mailName" class="form-control1"
                                               required="required" onblur="findUser();"
                                               placeholder="邮箱">
                                    </div>
                                </div>
                                <div id="message" style="text-align: center"></div>
                            </div>

                            <div class="form-group">
                                <div class="col-sm-8">
                                    <select name="roles_id" id="roles_id" class="form-control1">
                                        <span class="fa arrow"></span>
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
                                <button class="btn-group btn" onclick="change();">修改</button>
                            </div>
                        </div>

                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
        <!-- /#page-wrapper -->
    </div>
</div>
<!-- /#wrapper -->
<!-- Bootstrap Core JavaScript -->
<script src="${pageContext.request.contextPath}/statics/js/bootstrap.min.js"></script>
<jsp:include page="last.jsp"></jsp:include>
</body>
</html>
