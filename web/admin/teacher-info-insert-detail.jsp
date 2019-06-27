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
    <form id="myForm" class="layui-form" action="" style="margin: 27px" lay-filer="myForm">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">教师编号</label>
                <div class="layui-input-block">
                    <input type="text" id="id" name="id" lay-verify="required|number" placeholder="密码默认为教师编号" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">姓名</label>
                <div class="layui-input-block">
                    <input type="text" id="name" name="name" lay-verify="required" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-block" style="width: 186.4px;">
                    <select id="sex" name="sex" lay-verify="required">
                        <option value="男" selected="">男</option>
                        <option value="女">女</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">年龄</label>
                <div class="layui-input-block">
                    <input type="number" id="age" name="age" lay-verify="required|number" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">身份</label>
            <div class="layui-input-block">
                <select name="identity" lay-verify="required">
                    <option value="">请选择</option>
                    <option value="教授">教授</option>
                    <option value="副教授">副教授</option>
                    <option value="讲师">讲师</option>
                    <option value="助教">助教</option>
                </select>
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

        //监听提交
        form.on('submit(addForm)', function (message) {
            //注意：parent 是 JS 自带的全局对象，可用于操作父页面
            var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
            $.ajax({
                url: '<%=request.getContextPath()%>/TeacherServlet?action=insertTeacher',
                type: 'POST',
                data: {
                    id: message.field.id
                    , name: message.field.name
                    , sex: message.field.sex
                    , age: message.field.age
                    , identity: message.field.identity
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
            form.val("myForm", {
                "id": "123"
            });
        });
    });

</script>
</body>
</html>
