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
    <form id="myForm" class="layui-form" action="#" style="margin: 27px">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">学号</label>
                <div class="layui-input-block">
                    <input type="text" id="id" name="id" lay-verify="required" placeholder="密码默认为学号" autocomplete="off"
                           class="layui-input">
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
                    <input type="number" id="age" name="age" lay-verify="required" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">院系</label>
                <div class="layui-input-block">
                    <select id="department" name="department" required lay-verify="required" lay-filter="department">
                        <option value="">请选择</option>
                        <option value="0">机械工程学院</option>
                        <option value="1">电气与信息工程学院</option>
                        <option value="2">材料科学与工程学院</option>
                        <option value="3">汽车工程学院</option>
                        <option value="4">经济管理学院</option>
                        <option value="5">马克思主义学院</option>
                        <option value="6">外国语学院</option>
                        <option value="7">理学院</option>
                        <option value="8">科技学院</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">班级</label>
                <div class="layui-input-block">
                    <select id="sClass" name="sClass" required lay-verify="required">
                        <option value=""></option>
                        <c:forEach items="${allSClass}" var="sclass">
                            <option value="${sclass.name}">${sclass.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">手机号</label>
            <div class="layui-input-block">
                <input type="number" name="phone" class="layui-input" placeholder="请输入手机号"
                       autocomplete="off" value="${stu.phone}"/>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block login-btn">
                <button id="submitBtn" class="layui-btn layui-btn-lg"  lay-filter="updateForm">确定</button>
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
        form.on('submit(updateForm)', function (data) {
            return false;
        });
        form.on('select(department)', function (data) {
            var department = $('#department').val();
            $.ajax({
                url: "<%=request.getContextPath()%>/SClassServlet?action=getAllClass&department=" + department,//请求地址
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
                    $("#sClass").html(proHtml);
                },
                error: function () {
                    alert("fail");
                }
            });
            form.render('select');
        });

        //注意：parent 是 JS 自带的全局对象，可用于操作父页面
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        //给父页面传值
        $('#submitBtn').on('click', function(){
            var id = $('#id').val();
            if(id == '' || id == undefined || id == null){
                parent.layer.msg('ID不能为空');
                return;
            }
            var name = $('#name').val();
            if(name == '' || name == undefined || name == null){
                parent.layer.msg('姓名不能为空');
                return;
            }
            var age = $('#age').val();
            if(age == '' || age == undefined || age == null){
                parent.layer.msg('年龄不能为空');
                return;
            }
            var department = $('#department').val();
            if(department == '' || department == undefined || department == null){
                parent.layer.msg('学院不能为空');
                return;
            }
            var sclass = $('#sClass').val();
            if(sclass == '' || sclass == undefined || sclass == null){
                parent.layer.msg('班级不能为空');
                return;
            }
            var array = $('#myForm').serializeArray();
            var obj = {};//分配内存空间
            for (var i = 0; i < array.length; i++) {//数据类型为"自定义类的字段名=数据"后台会自动对数据进行匹配
                obj[array[i].name] = array[i].value;
            }
            $.ajax({
                url: "<%=request.getContextPath()%>/StudentServlet?action=insertStudent",
                data:{formdata:JSON.stringify(obj)},//传递数据
                type:"post",
                dataType: "json",
                success: function (data) {
                    if(data != null){
                        parent.layer.close(index);
                    }else{
                        parent.layer.msg("插入失败");
                    }
                },
                fail:function(e){
                    alert("fail");
                },
                error:function (data) {
                    parent.layer.msg("插入失败");
                }
            });
        });
    });

    $(function () {
        $("#department option[value=${stu.department}]").prop("selected", true);
        $("#sClass option[value=${stu.sClass}]").prop("selected", true);
    });
</script>
</body>
</html>
