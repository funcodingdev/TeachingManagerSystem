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
    <form id="myForm" class="layui-form" action="#" lay-filter="myForm" method="post">
        <%--<div class="layui-form-item">--%>
        <%--<div class="layui-inline">--%>
        <%--<div class="layui-input-block">--%>
        <%--<img src="<%=request.getContextPath()%>/res/image/headphoto/man.png">--%>
        <%--</div>--%>
        <%--</div>--%>
        <%--</div>--%>
        <%--图片上传功能--%>
        <div class="layui-form-item">
            <div>
                <button type="button" name="url" class="layui-btn" id="test1">上传头像</button>
                <img class="layui-upload-img" id="photo" width="100" height="100">
                <p id="demoText"></p>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">学号</label>
                <div class="layui-input-block">
                    <input type="text" id="id" name="id" lay-verify="required|number" placeholder="请输入学号"
                           autocomplete="off"
                           readonly class="layui-input layui-disabled" value="${sessionScope.obj.id}">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">姓名</label>
                <div class="layui-input-block">
                    <input type="text" id="name" name="name" lay-verify="required" autocomplete="off"
                           readonly class="layui-input layui-disabled" value="${sessionScope.obj.name}">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-block">
                    <input type="text" id="sex" name="sex" lay-verify="required" autocomplete="off"
                           readonly class="layui-input layui-disabled" value="${sessionScope.obj.sex}">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">年龄</label>
                <div class="layui-input-block">
                    <input type="number" id="age" name="age" lay-verify="required|number" autocomplete="off"
                           placeholder="请输入年龄" class="layui-input" value="${sessionScope.obj.age}">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">学院</label>
                <div class="layui-input-block">
                    <input type="text" id="department" name="department" lay-verify="required" autocomplete="off"
                           readonly class="layui-input layui-disabled" value="${sessionScope.obj.department}">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">班级</label>
                <div class="layui-input-block">
                    <input type="text" id="sclass" name="sclass" lay-verify="required" autocomplete="off"
                           readonly class="layui-input layui-disabled" value="${sessionScope.obj.sclass}">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">手机号</label>
            <div class="layui-input-block">
                <input type="number" name="phone" class="layui-input" placeholder="请输入手机号" lay-verify="number"
                       autocomplete="off" value="${sessionScope.obj.phone}"/>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block login-btn">
                <button class="layui-btn  layui-btn-submit " lay-submit="" lay-filter="updateForm">修改</button>
            </div>
        </div>
    </form>
</div>

<script>
    layui.use(['layer', 'jquery', 'element', 'form', 'upload'], function () {
        var layer = layui.layer
            , $ = layui.jquery
            , element = layui.element
            , form = layui.form
            , upload = layui.upload;


        //上传头像的方法
        var uploadInst = upload.render({
            elem: '#test1'     /*根据绑定id，打开本地图片*/
            ,url: '/reg'  /*上传后台接受接口*/
            ,auto: false        /*true为选中图片直接提交，false为不提交根据bindAction属性上的id提交*/
            ,bindAction: '#get'
            ,drag:true
            ,auto: false
            ,choose:function(obj){
                //预读本地文件示例，不支持ie8
                obj.preview(function(index, file, result){
                    $('#photo').attr('src', result); //图片链接（base64）
                });
            }
            ,done: function(res){
                //如果上传失败
                if(res.code > 0){
                    return layer.msg('上传失败');
                }
                //上传成功
            }
            ,error: function(){
                //演示失败状态，并实现重传
                var demoText = $('#demoText');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function(){
                    uploadInst.upload();
                });
            }
        });

        //监听提交
        form.on('submit(updateForm)', function (message) {
            //注意：parent 是 JS 自带的全局对象，可用于操作父页面
            var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
            $.ajax({
                url: '<%=request.getContextPath()%>/StudentServlet?action=insertStudent',
                type: 'POST',
                data: {
                    id: message.field.id
                    , name: message.field.name
                    , sex: message.field.sex
                    , age: message.field.age
                    , department: message.field.department
                    , sClass: message.field.sClass
                },
                dataType: 'json',
                async: false,
                success: function (msg) {
                    if (msg == "true") {
                        // layer.closeAll('loading');
                        // layer.load(2);
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

    });

</script>
</body>
</html>
