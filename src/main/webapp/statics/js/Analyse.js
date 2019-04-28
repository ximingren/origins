function analyse(room){
    var str;
    $.ajax({
        type:"post",
        data:{
            "room":room
        },
        url:"analyse",
        dataType:"json",
        success:function(data){
          var list=data.li;
            str= " <table class='table table-striped table-hover table-condensed table-responsive'> " +
            "<tr> " +
            "<th>水池&nbsp;<select onchange='analyse(this.value)'> " +
                "<option ></option> " +
            "<option value='101'>&nbsp;101</option> " +
            "<option value='103'>&nbsp;102</option> " +
            "<option value='3'>&nbsp;3</option> " +
            "<option value='4'>&nbsp;4</option> " +
            "<option value='5'>&nbsp;5</option> " +
            "</select></th> " +
                "<th>温度</th> " +
                "<th>PH</th> " +
                "<th>溶氧度</th> " +
                "<th>氮氧浓度</th> " +
                "<th>盐度</th> " +
                "<th>电导率</th> " +
                "</tr>	";
            str += "<tr>";
            str += "<th>平均</th>";
            $.each(list, function (i, v) {
                str += "<th>"+ v.average+"</th>";
            });
            str += "</tr>";
            str += "<tr>";
            str += "<th>最高</th>";
            $.each(list, function (i, v) {
                str += "<th>"+ v.max+"</th>";
            });
            str += "</tr>";
            str += "<tr>";
            str += "<th>最低</th>";
            $.each(list, function (i, v) {
                str += "<th>"+ v.min +"</th>";
            });
            str += "</tr>";
            str+="</table>";
            $('#analyseTable').html(str);
            /* /数据 */
        }
    });
}