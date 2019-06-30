<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="res/css/login.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/layui/css/layui.css" media="all">
    <script src="<%=request.getContextPath()%>/layui/layui.js"></script>

</head>
<body>
<div style="text-align: center;
    background-color: #fff;
    border-radius: 20px;
    width: 500px;
    height: 350px;
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%,-50%);">
    <h1>欢迎<span class="layui-bg-red">${role}</span><span class="layui-bg-blue">${obj.name}</span>登陆教学计划管理系统</h1>
</div>
<script type="text/javascript" color="0,0,255" opacity='0.7' zIndex="-2" count="150" src="<%=request.getContextPath()%>/res/js/canvas-nest.js"></script>
</body>
</html>