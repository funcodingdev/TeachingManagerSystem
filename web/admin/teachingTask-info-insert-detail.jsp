<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title></title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/layui/css/layui.css" media="all">
    <script src="<%=request.getContextPath()%>/layui/layui.js"></script>
</head>
<body>
<!-- 查询条件 -->
<div style="margin: 15px; border: 1px dotted #ccc; border-radius: 8px">
    <form id="myForm" class="layui-form" action="" style="margin: 27px" lay-filter="myForm">
        <div class="layui-form-item">
            <label class="layui-form-label">教学任务号</label>
            <div class="layui-input-block">
                <input type="text" id="teachingTaskNum" name="teachingTaskNum" lay-verify="required|number" placeholder="请输入教学任务号" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">课程名</label>
            <div class="layui-input-block">
                <select id="courseName" name="courseName" required lay-verify="required">
                    <option value="">请选择</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">教师编号</label>
            <div class="layui-input-block">
                <select id="teacherId" name="teacherId" required lay-verify="required">
                    <option value="">请选择</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">上课地点</label>
            <div class="layui-input-block">
                <input type="text" id="location" name="location" lay-verify="required" placeholder="请输入上课地点"
                       autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block login-btn">
                <button class="layui-btn  layui-btn-submit " lay-submit="" lay-filter="addForm">确定</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>

<script>
    layui.use(['layer', 'jquery', 'element', 'form'], function () {
        var layer = layui.layer
            , $ = layui.jquery
            , element = layui.element
            , form = layui.form;
        var type = "";
        //监听提交
        form.on('submit(addForm)', function (message) {
            //注意：parent 是 JS 自带的全局对象，可用于操作父页面
            var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
            $.ajax({
                url: '<%=request.getContextPath()%>/TeachingTaskServlet?action=insertTeachingTask',
                type: 'POST',
                data: {
                    teachingTaskNum: message.field.teachingTaskNum
                    , courseName: message.field.courseName
                    , teacherId: message.field.teacherId
                    , location: message.field.location
                },
                dataType: 'json',
                async: false,
                success: function (msg) {
                    if (msg == "true") {
                        layer.msg("添加成功", {icon: 6});
                        setTimeout(function () {
                            parent.layer.close(index);//关闭所有的弹出层
                        }, 1000);
                    } else {
                        layer.msg("添加失败", {icon: 5});
                    }
                }
            });
            return false;
        });
        $(function () {
            $.ajax({
                url: "<%=request.getContextPath()%>/CourseServlet?action=getAllCourseName",//请求地址
                type: "POST",//请求方式
                dataType: "json",//返回数据类型
                contentType: "application/json",
                async: false,//同步
                success: function (result) {
                    var data = result;
                    var proHtml = '';
                    for (var o in data) {
                        proHtml += '<option value="' + data[o] + '">' + data[o] + '</option>';
                    }
                    $("#courseName").html(proHtml);
                    form.render('select');
                },
                error: function () {
                    alert("fail");
                }
            });
            $.ajax({
                url: "<%=request.getContextPath()%>/TeacherServlet?action=getAllTeacher",//请求地址
                type: "POST",//请求方式
                dataType: "json",//返回数据类型
                contentType: "application/json",
                async: false,//同步
                success: function (result) {
                    var data = result;
                    var proHtml = '';
                    for (var i = 0;i<data.length;i++){
                        proHtml += '<option value="' + data[i]['id'] + '">' + data[i]['name'] +'('+data[i]['id']+')'+ '</option>';
                    }
                    $("#teacherId").html(proHtml);
                    form.render('select');
                },
                error: function () {
                    alert("fail");
                }
            });
        })
    });
</script>
</body>
</html>
