<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>实时图表</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Modern Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design"/>
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);
    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath}/statics/css/bootstrap.min.css" rel='stylesheet' type='text/css'/>
    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/statics/css/style.css" rel='stylesheet' type='text/css'/>
    <link href="${pageContext.request.contextPath}/statics/css/font-awesome.css" rel="stylesheet">
    <!-- jQuery -->
    <script src="${pageContext.request.contextPath}/statics/js/jquery.min.js"></script>    <!-- chart -->

    <script src="${pageContext.request.contextPath}/statics/js/echarts.min.js"></script>
    <!-- //chart -->
    <style>
        #main {
            width: 84%;
            height: 600px;
            float: left;
        }

        @media (max-width: 768px) {
            #main {
                width: 86% !important;
                height: 300px !important;
            }
        }
    </style>
</head>
<body>
<div id="wrapper">
    <!-- Navigation -->
    <jsp:include page="header.jsp"></jsp:include>
    <div id="page-wrapper" style="height: 680px;">
        <%--<label style=" font-size: x-large;">时间跨度:${scope}</label>--%>
        <span style="float: right; margin: 5px;">
            时间：
            <select onchange="change(this.value)">
                <option>${scope}</option>
                <c:if test="${scope!='今天'}">
                    <option value="today">今天</option>
                </c:if>
                <c:if test="${scope!='近周'}">
                    <option value="week">近周</option>
                </c:if>
                <c:if test="${scope!='全部'}">
                    <option value="all">全部</option>
                </c:if>
            </select>
            <input type="date" id="time" style="height: 25px;">
            <button style="height: 25px;" onclick="change($('#time').val())">提交</button>
            <input type="hidden" id="scope">
        </span>
        <div id="main"></div>
    </div>
    <!-- /#page-wrapper -->
</div>
<script src="${pageContext.request.contextPath}/statics/js/bootstrap.min.js"></script>
<jsp:include page="last.jsp"></jsp:include>
</body>
</html>
<script language="JavaScript">
    $(function () {
        var error = "${error}";
        if (error != "") {
            alert(error);
        }
    });
    var time = [];
    var tem = [];
    var NH = [];
    var COND = [];
    var salt = [];
    var DO = [];
    var PH = [];
    var websocket = null;
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://202.116.163.203:8080/origins/websocket");
    }
    else {
        alert("不支持websocket");
    }
    websocket.onopen = function () {
    };
    websocket.onerror = function () {
        alert("出现错误!");
    };
    window.onbeforeLoad = function () {
        websocket.close();
    };
    websocket.onmessage = function (event) {
        var t = JSON.parse(event.data);
        time.push(t.receive_time);
        tem.push(t.tem);
        NH.push(t.NH);
        COND.push(t.COND);
        salt.push(t.salt);
        DO.push(t.DO);
        PH.push(t.PH);
        myChart.setOption(option);
    };
    function change(scope) {
        if(scope!=""||scope!=null){
            window.location.href = "chart.do?scope=" + scope;
        }else {
            alert("请选择日期");
        }

    }
    function send(message) {
        websocket.send(message);
    }
    var myChart = echarts.init(document.getElementById('main'));
    // 绘制图表
    // 指定图表的配置项和数据
    option = {
        grid: {
            bottom: 80
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                animation: true
            }
        },
        color: ['#fc3517', '#ff0eec', '#000000', '#1209ff', '#20ff05', '#11e4a3'],
        legend: {
            data: ['水温℃', '氨氧含量', '电导度', '盐度', '含氧量', 'PH'],
            x: 'left'
        },
        dataZoom: [
            {
                show: true,
                realtime: false,
                start: 90,
                end: 100
            }
        ],
        xAxis: [
            {
                type: 'category',
                boundaryGap: false,
                axisLine: {onZero: false},
                data: (function () {
                    <c:forEach items="${list}" var="item">
                    time.push("${item.receive_time} \n");
                    </c:forEach>
                    return time;
                }())
            }
        ],
        yAxis: [
            {
                name: '',
                type: 'value',

            }
        ],
        series: [
            {
                name: '水温℃',
                type: 'line',
                animation: true,
                lineStyle: {
                    normal: {
                        width: 2,
                        color: '#fc3517'
                    }
                },
                data: (function () {
                    <c:forEach items="${list}" var="item">
                    tem.push("${item.tem}");
                    </c:forEach>
                    return tem;
                }())
            },
            {
                name: '氨氧含量',
                type: 'line',
                animation: true,
                lineStyle: {
                    normal: {
                        width: 2,
                        color: '#ff0eec'
                    }
                },
                data: (function () {
                    <c:forEach items="${list}" var="item">
                    NH.push("${item.NH}");
                    </c:forEach>
                    return NH;
                }())
            },
            {
                name: '电导度',
                type: 'line',
                animation: true,
                lineStyle: {
                    normal: {
                        width: 2,
                        color: '#000000'
                    }
                },
                data: (function () {
                    <c:forEach items="${list}" var="item">
                    COND.push("${item.COND}");
                    </c:forEach>
                    return COND;
                }())
            },
            {
                name: '盐度',
                type: 'line',
                animation: true,
                lineStyle: {
                    normal: {
                        width: 2,
                        color: '#1209ff'
                    }
                },
                data: (function () {
                    <c:forEach items="${list}" var="item">
                    salt.push("${item.salt}");
                    </c:forEach>
                    return salt;
                }())
            },
            {
                name: '含氧量',
                type: 'line',
                animation: true,

                lineStyle: {
                    normal: {
                        width: 2,
                        color: '#20ff05'
                    }
                },
                data: (function () {
                    <c:forEach items="${list}" var="item">
                    DO.push("${item.DO}");
                    </c:forEach>
                    return DO;
                }())
            },
            {
                name: 'PH',
                type: 'line',
                animation: true,
                lineStyle: {
                    normal: {
                        width: 2,
                        color: '#11e4a3'
                    }
                },
                data: (function () {
                    <c:forEach items="${list}" var="item">
                    PH.push("${item.PH}");
                    </c:forEach>
                    return PH;
                }())
            }
        ],
    };
    myChart.setOption(option);
    setInterval(function () {
        send("")
    }, 60 * 1000);
</script>
