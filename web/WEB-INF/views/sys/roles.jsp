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
    <title>角色管理</title>
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
                            <li class="list-group-item"><a href="/sys/dept/queryAll.do">部门管理</a></li>
                            <li class="list-group-item active"><a href="/sys/role/queryAll.do" style="color: white;">角色管理</a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-md-9">
                    <div class="panel panel-default">
                        <div class="panel-heading">角色列表
                            <button class="btn btn-primary btn-xs pull-right"
                                    data-target="#addRole" data-companyid="${_SUBJECT.company.id}">添加角色
                            </button>
                        </div>
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>职务编号</th>
                                <th>职务名称</th>
                                <th>所属公司</th>
                                <th>所属部门</th>
                                <th>职务操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${empty roles}">
                                <tr align="center">
                                    <td colspan="3"><span style="color:red;">暂无数据</span></td>
                                </tr>
                            </c:if>
                            <c:forEach items="${roles}" var="dept">
                                <tr>
                                    <td>${dept.id}</td>
                                    <td>${dept.name}</td>
                                    <td>${dept.company.name}</td>
                                    <td>${dept.dept.name}</td>
                                    <td>
                                        <button class="btn btn-xs ${dept.enabled==1?'btn-primary':'btn-danger'}">${dept.enabled==1?'启用':'禁用'}</button>
                                    </td>
                                    <td>
                                        <button class="btn btn-primary btn-xs" data-rolename="${dept.name}"
                                                data-target="#allocResource" data-id="${dept.id}">分配资源
                                        </button>
                                        <button class="btn btn-warning btn-xs" data-target="#updateRole"
                                                data-id="${dept.id}" data-deptid="${dept.dept.id}"
                                                data-rolename="${dept.name}">修改
                                        </button>
                                        <button class="btn btn-danger btn-xs"
                                                data-href="/sys/role/delete.do?id=${dept.id}"
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
<!-- 添加角色的modal -->
<div class="modal fade" id="addRole" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">添加角色</h4>
            </div>

            <form action="/sys/role/save.do" class="form-horizontal" method="post">
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
                                    <label class="col-sm-2 control-label">所属部门</label>
                                    <div class="col-sm-4">
                                        <select id="deptSelectCon" name="deptId" class="form-control">
                                            <option value="-1">-请选择-</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">角色名称</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="name" class="form-control" placeholder="角色名称">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">状态</label>
                                    <div class="col-sm-4">
                                        <div>
                                            <label class="radio-inline">
                                                <input type="radio" name="enabled" value="1"> 启用
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="enabled" value="0"> 禁用
                                            </label>
                                        </div>
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
<!-- 修改角色的modal -->
<div class="modal fade" id="updateRole" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">修改角色</h4>
            </div>
            <form action="/sys/role/update.do" class="form-horizontal" method="post">
                <div class="modal-body">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">所属公司</label>
                                    <input type="hidden" name="id">
                                    <input type="hidden" name="deptId">
                                    <div class="col-sm-4">
                                        <input type="text" value="${_SUBJECT.company.name}" readonly
                                               class="form-control" placeholder="所属公司">
                                        <input type="hidden" name="companyId" value="${_SUBJECT.company.id}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">角色名称</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="name" class="form-control" placeholder="角色名称">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">状态</label>
                                    <div class="col-sm-4">
                                        <div>
                                            <label class="radio-inline">
                                                <input type="radio" name="enabled" value="1"> 启用
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="enabled" value="0"> 禁用
                                            </label>
                                        </div>
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

    //    添加角色前填充部门下拉框
    $(document).ready(function () {
        $("[data-target='#addRole']").click(function () {
            var companyId = $(this).data('companyid');
            var target = $(this).data('target');
            $.ajax({
                url: '/sys/dept/queryAllByCompany.do',
                data: {
                    companyId: companyId
                },
                dataType: 'json',
                success: function (data) {
                    $(target).modal('show');
                    //clear
                    $("#deptSelectCon").html("<option value=-1>-请选择-</option>");

                    $(target).on('shown.bs.modal', function () {
                        $.each(data, function (key, val) {
                            $("#deptSelectCon").append("<option value='" + val['id'] + "'>" + val['name'] + "</option>");
                        })
                    })
                }
            })
        });
        //修改角色时的数据回显
        $("[data-target='#updateRole']").click(function () {
            var roleId = $(this).data('id');
            var deptId = $(this).data('deptid');
            var roleName = $(this).data('rolename');
            var target = $(this).data('target');
            $.ajax({
                url: '/sys/role/queryById.do',
                data: {
                    roleId: roleId
                },
                dataType: 'json',
                success: function (data) {
                    $(target).modal('show');
                    $("#updateRole").find(".modal-title").html("");
                    $("#updateRole").find(".modal-title").html("修改角色：" + roleName);
                    $(target).on('shown.bs.modal', function () {
                        $("#updateRole").find(":input[name='name']").val(data['name']);
                        $("#updateRole").find(":input[name='id']").val(data['id']);
                        $("#updateRole").find(":input[name='deptId']").val(deptId);
                        if (data['enabled'] == 1) {
                            $($("#updateRole").find(":input[name='enabled']").get(0)).prop('checked', true);
                        } else {
                            $($("#updateRole").find(":input[name='enabled']").get(1)).prop('checked', true);
                        }
                    });
                }
            })
        })
    })
</script>
</body>
</html>