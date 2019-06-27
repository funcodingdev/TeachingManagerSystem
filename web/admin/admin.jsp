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
                    <a href="<%=request.getContextPath()%>/admin/teachingTask-info.jsp" target="mainFrame">教学计划</a>
                </li>
                <li class="layui-nav-item">
                    <a href="<%=request.getContextPath()%>/admin/course-info.jsp" target="mainFrame">课程信息</a>
                </li>
                <li class="layui-nav-item">
                    <a href="<%=request.getContextPath()%>/admin/teacher-info.jsp" target="mainFrame">教师信息</a>
                </li>
                <li class="layui-nav-item">
                    <a href="<%=request.getContextPath()%>/admin/student-info.jsp" target="mainFrame">学生信息</a>
                </li>
            </ul>
        </div>
        <div>
            <ul class="layui-nav layui-layout-right">
                <li class="layui-nav-item">
                    <a href="javascript:;">
                        <img src="<%=request.getContextPath()%>/res/image/headphoto/${obj.image}" class="layui-nav-img"
                             alt="">${obj.name}
                    </a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="">基本资料</a>
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
    layui.use('element', function () {
        var element = layui.element;
    });
</script>
</body>
</html>
