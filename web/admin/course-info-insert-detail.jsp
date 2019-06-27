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
            <div class="layui-inline">
                <label class="layui-form-label">课程编号</label>
                <div class="layui-input-block">
                    <input type="text" id="id" name="id" lay-verify="required|number" placeholder="请输入课程编号" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">课程名</label>
                <div class="layui-input-block">
                    <input type="text" id="name" name="name" lay-verify="required" placeholder="请输入课程名" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">学分</label>
                <div class="layui-input-block">
                    <input type="text" id="credit" name="credit" lay-verify="required|number" placeholder="请输入学分" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">学时</label>
                <div class="layui-input-block">
                    <input type="text" id="period" name="period" lay-verify="required|number" placeholder="请输入学时" autocomplete="off"
                           class="layui-input">
                </div>
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
                url:'<%=request.getContextPath()%>/CourseServlet?action=insertCourse',
                type:'POST',
                data:{
                    id:message.field.id
                    ,name:message.field.name
                    ,credit:message.field.credit
                    ,period:message.field.period
                },
                dataType:'json',
                async: false,
                success:function (msg) {
                    if(msg == "true"){
                        layer.msg("添加成功", {icon: 6});
                        setTimeout(function(){
                            parent.layer.close(index);//关闭所有的弹出层
                        }, 1000);
                    }else{
                        layer.msg("添加失败", {icon: 5});
                    }
                }
            });
            return false;
        });

    });
</script>
</body>
</html>
