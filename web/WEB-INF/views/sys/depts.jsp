<%--
  User: walker
  Date: 2017/11/4
  Time: 20:21
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
    <title>部门管理</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet" href="/assets/bower_components/bootstrap/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="../commons/navbar.jsp"/>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <div class="row">
                <div class="col-md-3">
                    <div class="panel panel-default">
                        <div class="panel-heading">系统管理</div>

                        <ul class="list-group">
                            <li class="list-group-item"><a href="/sys/user/index.do">员工管理</a></li>
                            <li class="list-group-item active"><a href="/sys/dept/queryAll.do" style="color: white;">部门管理</a>
                            </li>
                            <li class="list-group-item "><a href="/sys/role/queryAll.do">角色管理</a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-md-9">
                    <div class="panel panel-default">
                        <div class="panel-heading">部门列表
                            <button class="btn btn-primary btn-xs pull-right" data-toggle="modal"
                                    data-target="#addDept" data-companyid="${_SUBJECT.company.id}">添加部门
                            </button>
                        </div>
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>部门编号</th>
                                <th>部门名称</th>
                                <th>部门描述</th>
                                <th>所属公司</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${empty depts}">
                                <tr align="center">
                                    <td colspan="3"><span style="color:red;">暂无数据</span></td>
                                </tr>
                            </c:if>
                            <c:forEach items="${depts}" var="dept">
                                <tr>
                                    <td>${dept.id}</td>
                                    <td>${dept.name}</td>
                                    <td>${dept.description}</td>
                                    <td>${dept.company.name}</td>
                                    <td>
                                        <button class="btn btn-warning btn-xs" data-target="#updateDept"
                                                data-id="${dept.id}" data-deptname="${dept.name}">修改
                                        </button>
                                        <button class="btn btn-danger btn-xs"
                                                data-href="/sys/dept/delete.do?id=${dept.id}"
                                                data-toggle="modal"
                                                data-target="#confirm-delete">删除
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 添加部门的modal -->
<div class="modal fade" id="addDept" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">添加部门</h4>
            </div>
            <form action="/sys/dept/save.do" class="form-horizontal" method="post">
                <div class="modal-body">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">所属公司</label>
                                    <div class="col-sm-4">
                                        <input type="text" value="${_SUBJECT.company.name}" readonly
                                               class="form-control" placeholder="所属公司">
                                        <input type="hidden" name="companyId" value="${_SUBJECT.company.id}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">部门名称</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="name" class="form-control" placeholder="角色名称">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">部门描述</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="description" class="form-control" placeholder="部门描述">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="submit" class="btn btn-primary">添加</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- 修改部门的modal -->
<div class="modal fade" id="updateDept" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">修改部门</h4>
            </div>
            <form action="/sys/dept/update.do" class="form-horizontal" method="post">
                <div class="modal-body">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">所属公司</label>
                                    <div class="col-sm-4">
                                        <input type="text" value="${_SUBJECT.company.name}" readonly
                                               class="form-control" placeholder="所属公司">
                                        <input type="hidden" name="companyId" value="${_SUBJECT.company.id}">
                                        <input type="hidden" name="id">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">部门名称</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="name" class="form-control" placeholder="角色名称">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">部门描述</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="description" class="form-control" placeholder="部门描述">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="submit" class="btn btn-primary">修改</button>
                </div>
            </form>
        </div>
    </div>
</div>

<%--删除时的确认框--%>
<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                请确认
            </div>
            <div class="modal-body">
                确认删除该记录吗？
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <a class="btn btn-danger btn-ok">删除记录</a>
            </div>
        </div>
    </div>
</div>


<script src="/assets/bower_components/jquery/jquery.min.js"></script>
<script src="/assets/bower_components/bootstrap/js/bootstrap.min.js"></script>
<script>
    <%--删除之前的提示--%>
    $('#confirm-delete').on('show.bs.modal', function (e) {
        $(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
    });

    // 修改部门
    $(document).ready(function () {
        $("[data-target='#updateDept']").click(function () {
            var deptId = $(this).data('id');
            var target = $(this).data('target');
            var deptName = $(this).data('deptname');

            $.ajax({
                url: '/sys/dept/queryById.do',
                data: {
                    deptId: deptId
                },
                dataType: 'json',
                success: function (data) {
                    $(target).modal('show');
                    $("#updateDept").find(".modal-title").html("");
                    $("#updateDept").find(".modal-title").html("修改部门：" + deptName);
                    $(target).on('shown.bs.modal', function () {
                        for (key in data) {
                            $("#updateDept").find(":input[name='" + key + "']").val(data[key]);
                        }
                    })
                }
            })
        })
    })
</script>
</body>
</html>