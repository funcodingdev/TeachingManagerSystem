<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>教学计划管理系统</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/layui/css/layui.css" media="all">
</head>
<body>
<div>
    <%--顶部--%>
    <div>
        <div>
            <ul class="layui-nav">
                <li class="layui-nav-item layui-this"><a href="">首页</a></li>
                <li class="layui-nav-item">
                    <a href="<%=request.getContextPath()%>/student/select-Course.jsp" target="mainFrame">选课列表</a>
                </li>
                <li class="layui-nav-item">
                    <a href="<%=request.getContextPath()%>/student/retire-Course.jsp" target="mainFrame">已选课程</a>
                </li>
            </ul>
        </div>
        <div>
            <ul class="layui-nav layui-layout-right">
                <li class="layui-nav-item">
                    <a href="javascript:;">
                        <img src="<%=request.getContextPath()%>/res/image/headphoto/${obj.image}" class="layui-nav-img" alt="">${obj.name}
                    </a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a id="update_selfInfo">基本资料</a>
                        </dd>
                        <dd>
                            <a id="update_password">修改密码</a>
                        </dd>
                        <dd>
                            <a href="<%=request.getContextPath()%>/LoginServlet?action=exit">注销</a>
                        </dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>
    <%--内容区域--%>
    <div>
        <div style="padding: 15px;">
            <iframe id="option" name="mainFrame" src="<%=request.getContextPath()%>/index.jsp"
                    style="width: 100%;height: 90%;" frameborder="0">

            </iframe>
        </div>
    </div>
</div>
<script src="<%=request.getContextPath()%>/layui/layui.js"></script>
<script>
    //JavaScript代码区域
    layui.use(['form', 'laypage', 'layer', 'table', 'upload', 'element'], function () {
        var form = layui.form
            , laypage = layui.laypage //分页
            , layer = layui.layer //弹层
            , table = layui.table //表格
            , upload = layui.upload //上传
            , element = layui.element //元素操作
            , $ = layui.jquery;

        $(document).on('click', '#update_selfInfo', function () {
            layer.open({
                title: '修改学生信息',
                type: 2,
                skin: 'layui-layer-lan',
                closeBtn: 2,
                area: ["740px", "540px"], // 宽高
                content: '../student/student-info-update-detail.jsp',
                // end: function () {
                //     flushTab();
                // },
                success: function (layero, index) {
                    layer.msg(layero, index);
                }
            });
        });

        $(document).on('click', '#update_password', function () {
            layer.open({
                title: '修改学生密码',
                type: 2,
                skin: 'layui-layer-lan',
                closeBtn: 2,
                area: ["740px", "340px"], // 宽高
                content: '../student/student-info-update-pwd.jsp',
                // end: function () {
                //     flushTab();
                // },
                success: function (layero, index) {
                    layer.msg(layero, index);
                }
            });
        });

    });
</script>
</body>
</html>
