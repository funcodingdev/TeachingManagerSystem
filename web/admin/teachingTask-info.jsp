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
<form class="layui-form" action="">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">课程名</label>
            <div class="layui-input-block">
                <input type="text" id="id" lay-verify="required" placeholder="请输入课程名" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <a id="searchId" class="layui-btn">查询</a>
        </div>
        <div class="layui-inline">
            <a id="addId" class="layui-btn layui-btn-normal">添加</a>
        </div>
    </div>
</form>

<table class="layui-hide" id="stuTable" lay-filter="stu"></table>
<script type="text/html" id="stuBar">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script>
    layui.use(['form', 'laypage', 'layer', 'table', 'upload', 'element'], function () {
        var form = layui.form
            , laypage = layui.laypage //分页
            , layer = layui.layer //弹层
            , table = layui.table //表格
            , upload = layui.upload //上传
            , element = layui.element //元素操作
            , $ = layui.jquery;

        //执行一个 table 实例
        table.render({
            elem: '#stuTable'
            , id: 'myTable'
            , height: 'full-100'
            , url: '<%=request.getContextPath()%>/TeachingTaskServlet?action=getTeachingTasks' //数据接口
            , cellMinWidth: 80 //全局定义常规单元格的最小宽度
            , title: '教学计划表'
            , page: { //
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                , curr: 1 //设定初始在第 1 页
                , groups: 1 //只显示 1 个连续页码
                , first: false //不显示首页
                , last: false //不显示尾页
            }
            , limit: 15
            , limits: [15, 30, 45, 60]
            , even: true
            , autoSort: false
            , curr: 1 //设定初始在第 1 页
            // , toolbar: 'default' //开启工具栏，此处显示默认图标
            , cols: [[ //表头
                {field: 'teachingTaskNum', title: '教学任务号', sort: true, fixed: 'left'}
                , {field: 'courseId', title: '课程编号', sort: true}
                , {field: 'courseName', title: '课程名'}
                , {field: 'teacherId', title: '教师编号', sort: true}
                , {field: 'teacherName', title: '教师姓名'}
                , {field: 'totalNum', title: '选课总人数', sort: true}
                , {field: 'location', title: '上课地点'}
                , {
                    field: 'startTime',
                    title: '开课时间',
                    sort: true,
                    templet: '<div>{{ layui.util.toDateString(d.startTime, "yyyy-MM-dd") }}</div>'
                }
                , {fixed: 'right', align: 'center', toolbar: '#stuBar'}
            ]]
        });
        //监听行工具事件
        table.on('tool(stu)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                , layEvent = obj.event; //获得 lay-event 对应的值
            if (layEvent === 'del') {
                layer.confirm('真的要删除此教学任务么', function (index) {
                    //向服务端发送删除指令
                    $.ajax({
                        type: 'get',
                        url: '<%=request.getContextPath()%>/TeachingTaskServlet?action=deleteTeachingTask',
                        data: {
                            teachingTaskNum: data.teachingTaskNum//传向后端的数据
                        },
                        contentType: 'application/json',
                        success: function (result) {
                            if (result.type == true) {
                                layer.msg(result.msg, {icon: 1}, {time: 2000});
                                flushTab();
                            } else {
                                layer.msg(result.msg, {icon: 2}, {time: 2000});
                            }
                        }
                    });
                });
            } else if (layEvent === 'edit') {
                // layer.msg('编辑操作' + JSON.stringify(data));
                json = JSON.stringify(data);
                layer.open({
                    title: '修改教学计划信息',
                    type: 2,
                    skin: 'layui-layer-lan',
                    closeBtn: 2,
                    area: ["740px", "460px"], // 宽高
                    content: '../admin/teachingTask-info-update-detail.jsp',
                    end: function () {
                        flushTab();
                    },
                    success: function (layero, index) {
                        layer.msg(layero, index);
                    }
                });
            }
        });
        // 单击搜索
        $("#searchId").click(function () {
            // 注意参数(myTable为表格id)
            var id = $("#id").val();
            table.reload('myTable', {
                url: '<%=request.getContextPath()%>/TeachingTaskServlet?action=getTeachingTasks&keyWord=' + id
            });
        });

        // 单击添加
        $("#addId").click(function () {
            layer.open({
                title: '添加教学计划信息',
                type: 2,
                skin: 'layui-layer-lan',
                closeBtn: 2,
                // skin: 'layui-layer-rim', // 加上边框
                area: ["740px", "680px"], // 宽高
                // maxmin: true, //开启最大化最小化按钮
                content: '../admin/teachingTask-info-insert-detail.jsp',
                end: function () {
                    flushTab();
                }
            });

        });

        // 刷新表格
        function flushTab() {
            // $(".layui-laypage-btn")[0].click();
            table.reload('myTable', {
                url: '<%=request.getContextPath()%>/TeachingTaskServlet?action=getTeachingTasks'
            });
        }

    });
</script>
</body>
</html>
