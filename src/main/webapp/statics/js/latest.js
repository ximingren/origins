function latest(currentPage) {
    var str;
    var size = 3;
    $.ajax({
        type: "post",
        data: {
            "currentPage": currentPage
        },
        url: "latestData",
        dataType: "json",
        success: function (data) {
            var list = data.data;
            if (data.page.totalPage != 1) {
                str = '<div>';
                str += '<span style="float:right;">' + currentPage + '/' + data.page.totalPage + '页' ;
                str += '<a href="javascript:;"onclick="latest(' + (currentPage -1) + ');"> 上一页</a>';
                //当前页左侧数字按钮
                if (currentPage != data.page.totalPage) str += '<a href="javascript:;"onclick="latest(' + (currentPage + 1) + ');"> 下一页 </a>';
                //下一页
                //末页
                //总也数, 总记录数
                str+='</span>';
                str += '<div >';
                str += "</div>";
                //替换分页条
                $('#pageBar').html(str);
                /* /分页 */
            }

            str = "<div class='graph_box'>";
            $.each(list, function (i, v) {
                str += "<div class='col-md-4 grid_2'><div class='grid_1' style='padding: 30px;'>";
                str += "<h3>水  池 :" + v.node_num + "</h3>";
                str += "<h4>检测时间 :" + v.receive_time + "</h4>";
                str += "<h4>水  &nbsp;&nbsp;&nbsp; &nbsp;温 :" + v.tem + "</h4>";
                str += "<h4 style='margin-right: 22px;'>氨&nbsp;氮&nbsp;含量 :" + v.NH + "</h4>";
                str += "<h4>电&nbsp;导&nbsp; 度 :" + v.COND + "</h4>";
                str += "<h4>盐 &nbsp;&nbsp;&nbsp; &nbsp;度 :" + v.salt + "</h4>";
                str += "<h4>氧&nbsp;含&nbsp; 量 :" + v.DO + "</h4>";
                str += "<h4>PH   :&nbsp;&nbsp;&nbsp; &nbsp;" + v.PH + "</h4>";
                str += "</div></div>";
                if (i == 2) {
                    str += "<div class='clearfix'></div>";
                    str += "</div>";
                    str += "<div class='graph_box1' >";
                }
            });
            str += "<div class='clearfix'></div>";
            str += "</div>";
            $('#ajaxTable').html(str);
            /* /数据 */
        }
    });
}