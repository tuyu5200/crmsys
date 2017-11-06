<%--
  User: walker
  Date: 2017/11/4
  Time: 20:20
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert Title Here</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet" href="/assets/bower_components/bootstrap/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="commons/navbar.jsp"/>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <div class="row">
                <div class="col-md-3">
                    <div class="panel panel-default">
                        <div class="panel-heading">系统管理</div>

                        <ul class="list-group">
                            <li class="list-group-item"><a href="/sys/user/index.do">员工管理</a></li>
                            <li class="list-group-item"><a href="/sys/dept/queryAll.do">部门管理</a></li>
                            <li class="list-group-item"><a href="/sys/role/queryAll.do">角色管理</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-md-9">
                    <div class="panel panel-default">
                        <div class="panel-heading">数据信息</div>
                        <div class="panel-body">
                            请选择功能
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="/assets/bower_components/jquery/jquery.min.js"></script>
<script src="/assets/bower_components/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>