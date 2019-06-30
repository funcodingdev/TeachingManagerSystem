<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: FJ
  Date: 2019/6/25
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>教学计划管理系统</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/layui/css/layui.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/login.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/layui/layui.js"></script>
    <c:if test="${!(empty message)}">
    <script type="text/javascript">
        alert('<c:out value="${message}"/>');
    </script>
        <c:remove var="message" scope="session"/>
    </c:if>
<body>
<div class="login-content">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>教学计划管理系统</legend>
    </fieldset>
    <form class="layui-form" action="<%=request.getContextPath()%>/LoginServlet?action=login" method="post">
        <div class="layui-form-item">
            <label class="layui-form-label">用户账号</label>
            <div class="layui-input-block">
                <input type="text" id="username" name="id" required lay-verify="required" placeholder="请输入用户名"
                       autocomplete="off" class="layui-input" value="${id}"/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">登陆密码</label>
            <div class="layui-input-block">
                <input type="password" id="password" name="password" required lay-verify="required" placeholder="请输入密码"
                       autocomplete="off" class="layui-input"/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">登陆角色</label>
            <div class="layui-input-block">
                <select id="role" name="role" lay-verify="required">
                    <option value="">请选择</option>
                    <option value="0">学生</option>
                    <option value="1">教师</option>
                    <option value="2">管理员</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block login-btn">
                <button class="layui-btn layui-btn-lg" lay-submit lay-filter="loginForm" id="loginBtn">登陆</button>
            </div>
        </div>
    </form>
</div>
<script>
    layui.use(['form', 'element', 'layer'], function () {
        var form = layui.form
            , element = layui.element//元素操作
            , layer = layui.layer
            , $ = layui.jquery;
        //监听提交
        form.on('submit(loginForm)', function (data) {
            // var username = data.field.username;
            // console.log(username);
            // if(username.length < 6){
            //     layer.msg("密码长度不能小于6位", {icon: 5}, 2000);
            //     return;
            // }
            return true;
        });
    });
</script>
<script type="text/javascript" color="0,0,255" opacity='0.7' zIndex="-2" count="150" src="<%=request.getContextPath()%>/res/js/canvas-nest.js"></script>
</body>
</html>
