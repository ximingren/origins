<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Modern Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>预警监控</title>
    <link href="${pageContext.request.contextPath}/statics/css/bootstrap.min.css" rel='stylesheet' type='text/css'/>
    <link href="${pageContext.request.contextPath}/statics/css/style.css" rel='stylesheet' type='text/css'/>
    <link href="${pageContext.request.contextPath}/statics/css/font-awesome.css" rel="stylesheet">
    <script language="JavaScript" src="${pageContext.request.contextPath}/statics/js/jquery.min.js"></script>
    <script language="JavaScript" src="${pageContext.request.contextPath}/statics/js/latest.js"></script>
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);
    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
    <style>
        @media (max-width: 380px) {
            input[type='number'] {
                width: 112px !important;
            }
        }
    </style>
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
    $(function () {
        if ("${open}" == "true") {
            $("#monitor").attr("checked", true);
        }
        if ("${open}" == "false") {
            $("#monitor").attr("checked", false);
        }
    });
    function setFlag() {
        if ($("#monitor").prop("checked")) {
            window.location.href = "openMonitor.do?open=" + "true";
        }
        else {
            window.location.href = "openMonitor.do?open=" + "false";
        }
    }
    function vertify() {
        $("#form").form("submit", {
            url: "monitor.htm",
            onSubmit: function () {
                var uplimit = document.getElementsByName("uplimit");
                var lowlimit = document.getElementsByName("lowlimit");
                var maxlimit = document.getElementsByName("maxlimit");
                var minlimit = document.getElementsByName("minlimit");
                var name = document.getElementsByName("name");
                for (var i = 0; i < uplimit.length; i++) {
                    if (parseFloat(lowlimit[i].value) >= parseFloat(uplimit[i].value)) {
                        alert(name[i].value + "的上限必须比下限大");
                        return false;
                    }
                    if (parseFloat(maxlimit[i].value) < parseFloat(uplimit[i].value) || parseFloat(minlimit[i].value) > parseFloat(lowlimit[i].value)) {
                        alert(name[i].value + "可设置的范围在" + minlimit[i].value + "--" + maxlimit[i].value);
                        return false;
                    }

                }
                if (confirm("确定修改？") == false) {
                    return false;
                }
                return $(this).form("validate");
            },
            success: function (data) {
                var result = JSON.parse(data);
                if (result.success == true) {
                    alert("修改成功！");
                }
                else {
                    alert("修改失败!");
                }
            }
        });
    }
</script>
<div id="wrapper">
    <jsp:include page="header.jsp"></jsp:include>
    <div id="page-wrapper">
        <div class="graphs">
            <div class="content_bottom">
                <div class="col-md-8 span_3">
                    <hr>
                    <div class="checkbox-inline1">
                        <label>
                            <input type="checkbox" id="monitor" onchange="setFlag()">预警监控
                        </label>
                    </div>
                    <div class="bs-example1" data-example-id="contextual-table">
                        <table class="table table-bordered table-striped">
                            <caption>设置阔值</caption>
                            <thead>
                            <tr>
                                <th>数据种类</th>
                                <th>上限</th>
                                <th>下限</th>
                            </tr>
                            </thead>
                            <tbody>
                            <form id="form">
                                <c:choose>
                                <c:when test="${threshold!=null}">
                                <c:forEach items="${threshold}" var="e">
                                <tr>
                                    <th>${e.name}</th>
                                    <td><input type="number" name="uplimit" class="uplimit" value="${e.uplimit}"
                                               step="0.1"></td>
                                    <td><input type="number" name="lowlimit" class="lowlimit" value="${e.lowlimit}"
                                               step="0.1"></td>
                                    <input type="hidden" value="${e.max_limit}" name="maxlimit">
                                    <input type="hidden" value="${e.min_limit}" name="minlimit">
                                    <input type="hidden" value="${e.name}" name="name">
                                </tr>
                                </c:forEach>
                                </c:when>
                                </c:choose>
                            </tbody>
                        </table>
                    </div>
                </div>
                </form>
                <div class="panel-footer">
                    <div class="row">
                        <div class="col-sm-4 col-sm-offset-3">
                            <c:if test="${role.menu_Id!=null}">
                                <button class="btn" onclick="vertify();">修改</button>
                            </c:if>
                        </div>
                    </div>
                </div>

                <div class="clearfix"></div>
            </div>
            <div class="content_bottom">
                <div class="col-md-8 span_3">
                    <div class="bs-example1" data-example-id="contextual-table">
                        <table class="table table-bordered table-striped">
                            <caption>设备工作状态</caption>
                            <thead>
                            <tr>
                                <th>传感器</th>
                                <th>状态</th>
                                <th>备注</th>
                                <th>传感器</th>
                                <th>状态</th>
                                <th>备注</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:choose>
                                <c:when test="${additions!=null}">
                                    <c:forEach items="${additions}" var="e" varStatus="i">
                                        <c:if test="${(i.count%2)!=0}">
                                            <tr>
                                            <td>${e.name}</td>
                                            <c:choose>
                                                <c:when test="${e.addition.equals('异常')}">
                                                    <td style="color: red">${e.addition}</td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td>${e.addition}</td>
                                                </c:otherwise></c:choose>
                                            <td>${e.remarks}</td>
                                        </c:if>
                                        <c:if test="${(i.count%2)==0}">
                                            <td>${e.name}</td>
                                            <c:choose>
                                                <c:when test="${e.addition.equals('异常')}">
                                                    <td style="color: red">${e.addition}</td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td>${e.addition}</td>
                                                </c:otherwise></c:choose>
                                            <td>${e.remarks}</td>
                                            </tr>
                                        </c:if>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <td>请勾选预警监控</td>
                                </c:otherwise>
                            </c:choose>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
            <div class="clearfix"></div>
        </div>
        <!-- /#page-wrapper -->
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
