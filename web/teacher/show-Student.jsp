<%--
  Created by IntelliJ IDEA.
  User: FJ
  Date: 2019/6/24
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title></title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/layui/css/layui.css" media="all">
    <script src="<%=request.getContextPath()%>/layui/layui.js"></script>
</head>
<body>
<form class="layui-form" action="" lay-filter="myForm">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">教学任务号</label>
            <div class="layui-input-block">
                <input type="text" id="teachingTaskNum" name="teachingTaskNum" lay-verify="required" autocomplete="off"
                       readonly class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">课程编号</label>
            <div class="layui-input-block">
                <input type="text" id="courseId" name="courseId" lay-verify="required" autocomplete="off"
                       readonly class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">课程名</label>
            <div class="layui-input-block">
                <input type="text" id="courseName" name="courseName" lay-verify="required" autocomplete="off"
                       readonly class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">上课地点</label>
            <div class="layui-input-block">
                <input type="text" id="location" name="location" lay-verify="required" autocomplete="off"
                       readonly class="layui-input">
            </div>
        </div>
    </div>
</form>

<table class="layui-hide" id="stuTable" lay-filter="stu"></table>

<script>
    layui.use(['form', 'laypage', 'layer', 'table', 'upload', 'element'], function () {
        var form = layui.form
            , laypage = layui.laypage //分页
            , layer = layui.layer //弹层
            , table = layui.table //表格
            , upload = layui.upload //上传
            , element = layui.element //元素操作
            , $ = layui.jquery;

        $(function () {
            var parent_json = eval('(' + parent.json + ')');
            form.val("myForm", {
                "teachingTaskNum": parent_json.teachingTaskNum,
                "courseId": parent_json.courseId,
                "courseName": parent_json.courseName,
                "location": parent_json.location
            })
        });
        var teachingTaskNum = $('#teachingTaskNum').val();
        //执行一个 table 实例
        table.render({
            elem: '#stuTable'
            , id: 'myTable'
            , height: 'full-100'
            , url: '<%=request.getContextPath()%>/StudentServlet?action=getSCGrade&teachingTaskNum=' + teachingTaskNum //数据接口
            , cellMinWidth: 80 //全局定义常规单元格的最小宽度
            , title: '学生表'
            , page: { //
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                ,curr: 1 //设定初始在第 1 页
                ,groups: 1 //只显示 1 个连续页码
                ,first: false //不显示首页
                ,last: false //不显示尾页
            }
            , limit: 15
            , limits: [15, 30, 45, 60]
            , even: true
            ,autoSort: false
            , curr: 1 //设定初始在第 1 页
            // , toolbar: 'default' //开启工具栏，此处显示默认图标
            , cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}
                , {field: 'stuId', title: 'ID', sort: true, fixed: 'left'}
                , {field: 'name', title: '姓名'}
                , {field: 'department', title: '学院'}
                , {field: 'sclass', title: '班级'}
                , {field: 'grade', title: '成绩', edit: 'text', sort: true}
            ]]
        });

        // //监听单元格编辑
        // table.on('edit(stu)', function (obj) {
        //     var value = obj.value //得到修改后的值
        //         , data = obj.data //得到所在行所有键值
        //         , field = obj.field; //得到字段
        //     layer.msg('[ID: ' + data.id + '] ' + field + ' 字段更改为：' + value);
        // });

        //监听行单击事件（单击事件为：rowDouble）
        table.on('rowDouble(myForm)', function(obj){
            var data = obj.data;
            console.log(JSON.stringify(data));
            layer.alert(JSON.stringify(data), {
                title: '当前行数据：'
            });

            //标注选中样式
            obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');
        });

        // 刷新表格
        function flushTab() {
            table.reload('myTable', {
                url: '<%=request.getContextPath()%>/StudentServlet?action=getSCGrade'
            });
        }

    });
</script>
</body>
</html>
