<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 头部区域 -->
<!-- Navigation -->
<nav class="top1 navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="index.html">溯源智能系统</a>

    </div>
    <!-- /.navbar-header -->
    <ul class="nav navbar-nav navbar-right ">
        <li class="dropdown"><a>欢迎您&nbsp;&nbsp;&nbsp;${user.mailName}</a></li>
    </ul>
    <div class="navbar-default sidebar" role="navigation">
        <div class="sidebar-nav navbar-collapse">
            <ul class="nav" id="side-menu">
                <li>
                    <a href="index.html"><i class="fa fa-home nav_icon"></i>首页</a>
                </li>
                <li>
                    <a href="#"><i class="fa fa-bar-chart-o nav_icon"></i>实时图表<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="chart.do?scope=today">折线图</a>
                        </li>
                    </ul>
                    <!-- /.nav-second-level -->
                </li>
                <li>
                    <a href="getMonitor.do"><i class="fa fa-warning nav_icon"></i>预警监控</a>
                </li>
                <c:choose>
                <c:when test="${role.menu_Id==null}">
                    <li>
                        <a href="#"><i class="fa fa-user nav_icon"></i>人员管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a href="change.do"><i class="fa fa-pencil-square nav_icon"></i>修改个人信息</a>
                            </li>
                        </ul>
                    </li>
                </c:when>
                <c:otherwise>
                <li>
                    <a href="#"><i class="fa fa-user nav_icon"></i>人员管理<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="getAddUser.do"><i class="fa fa-plus-square-o nav_icon"></i>添加新用户</a>
                        </li>
                        <li>
                            <a href="getAdjust.do"><i class="fa fa-retweet nav_icon"></i>职位调整</a>
                        </li>
                        <li>
                            <a href="change.do"><i class="fa fa-pencil-square nav_icon"></i>修改个人信息</a>
                        </li>
                    </ul>
                    </c:otherwise>
                    </c:choose>
                    <!-- /.nav-second-level -->
                </li>
                <li>
                    <a href="#"><i class="fa fa-comment nav_icon"></i>关于我们</a>
                </li>
                <li>
                    <a href="loginout.do" id="logout"><i class="fa fa-lock nav_icon"></i>退出</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<!-- jQuery -->