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
    <title>员工管理</title>
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
                        <div class="panel-heading">系统管理
                        </div>

                        <ul class="list-group">
                            <li class="list-group-item active"><a href="/sys/user/index.do"
                                                                  style="color: white;">员工管理</a></li>
                            <li class="list-group-item"><a href="/sys/dept/queryAll.do">部门管理</a></li>
                            <li class="list-group-item"><a href="/sys/role/queryAll.do">角色管理</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-md-9">
                    <div class="panel panel-default">
                        <div class="panel-heading">数据信息
                            <button class="pull-right btn btn-primary btn-xs" data-toggle="modal"
                                    data-target="#addUser">添加用户
                            </button>
                        </div>
                        <table class="table table-bordered table-hover">
                            <thead>
                            <tr>
                                <th>编号</th>
                                <th>姓名</th>
                                <th>公司</th>
                                <th>部门</th>
                                <th>邮箱</th>
                                <th>性别</th>
                                <th>状态</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${empty users}">
                                <tr align="center">
                                    <td colspan="8"><span style="color:red">暂无数据</span></td>
                                </tr>
                            </c:if>
                            <c:forEach items="${users}" var="user">
                                <tr>
                                    <td>${user.id}</td>
                                    <td>${user.name}</td>
                                    <td>${user.company.name}</td>
                                    <td>${user.dept.name}</td>
                                    <td>${user.email}</td>
                                    <td>${user.sex==1?'男':'女'}</td>
                                    <td>
                                        <button class=" btn btn-xs ${user.enabled==1?'btn-primary':'btn-danger'}">${user.enabled==1?'启用':'禁用'}</button>
                                        <button class=" btn btn-xs ${user.locked==0?'btn-primary':'btn-danger'}">${user.locked==0?'解锁':'锁定'}</button>
                                    </td>
                                    <td>
                                        <button class="btn btn-warning btn-xs" data-target="#updateUser"
                                                data-id="${user.id}"
                                                data-username="${user.name}">修改
                                        </button>
                                        <button class="btn btn-primary btn-xs"
                                                data-target="#allocRole" data-id="${user.id}"
                                                data-username="${user.name}">分配角色
                                        </button>
                                        <button class="btn btn-danger btn-xs"
                                                data-href="/sys/user/delete.do?id=${user.id}"
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

<!-- 添加用户的modal -->
<div class="modal fade" id="addUser" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">添加用户</h4>
            </div>

            <form action="/sys/user/save.do" class="form-horizontal" method="post">
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
                                        <input type="text" value="${_SUBJECT.dept.name}" class="form-control" readonly
                                               placeholder="所属部门">
                                        <input type="hidden" name="deptId" value="${_SUBJECT.dept.id}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">员工姓名</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="name" class="form-control" placeholder="员工姓名">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">性别</label>
                                    <div class="col-sm-4">
                                        <label class="radio-inline">
                                            <input type="radio" name="sex" value="1"> 男
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="sex" value="0"> 女
                                        </label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">用户名</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="username" class="form-control" placeholder="用户名">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">密码</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="password" class="form-control" placeholder="密码">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">邮箱</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="email" class="form-control" placeholder="邮箱">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">状态</label>
                                    <div class="col-sm-4">
                                        <div>
                                            <label class="radio-inline">
                                                <input type="radio" name="locked" value="1"> 已锁定
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="locked" value="0"> 未锁定
                                            </label>
                                        </div>
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
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">备注</label>
                                    <div class="col-sm-4">
                                        <textarea name="description" rows="4" class="form-control"></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="reset" class="btn btn-warning">重置</button>
                    <button type="submit" class="btn btn-primary">添加</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- 修改用户的modal -->
<div class="modal fade" id="updateUser" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">修改用户</h4>
            </div>

            <form action="/sys/user/update.do" class="form-horizontal" method="post">
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
                                        <input type="text" value="${_SUBJECT.dept.name}" class="form-control" readonly
                                               placeholder="所属部门">
                                        <input type="hidden" name="deptId" value="${_SUBJECT.dept.id}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">员工姓名</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="name" class="form-control" placeholder="员工姓名">
                                        <input type="hidden" name="id" id="updateUserId">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">性别</label>
                                    <div class="col-sm-4">
                                        <label class="radio-inline">
                                            <input type="radio" name="sex" value="1"> 男
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="sex" value="0"> 女
                                        </label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">用户名</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="username" class="form-control" placeholder="用户名">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">密码</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="password" class="form-control" placeholder="密码">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">邮箱</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="email" class="form-control" placeholder="邮箱">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">状态</label>
                                    <div class="col-sm-4">
                                        <div>
                                            <label class="radio-inline">
                                                <input type="radio" name="locked" value="1"> 已锁定
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="locked" value="0"> 未锁定
                                            </label>
                                        </div>
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
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">备注</label>
                                    <div class="col-sm-4">
                                        <textarea name="description" rows="4" class="form-control"></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="reset" class="btn btn-warning">重置</button>
                    <button type="submit" class="btn btn-primary">修改</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- 为用户分配角色的modal -->
<div class="modal fade" id="allocRole" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">title</h4>
            </div>

            <form action="/sys/user/allocRole.do" class="form-horizontal" method="post">
                <div class="modal-body">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th><input type="checkbox"></th>
                            <th>角色编号</th>
                            <th>角色名</th>
                            <th>所属公司</th>
                            <th>所属部门</th>
                        </tr>
                        </thead>
                        <tbody id="allocRoleCon">

                        </tbody>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="submit" class="btn btn-primary">分配角色</button>
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

    $(document).ready(function () {
        $("[data-target='#updateUser']").click(function () {
            var userId = $(this).data('id');
            var target = $(this).data('target');
            var name = $(this).data('username');
            $.ajax({
                url: '/sys/user/queryById.do',
                data: {
                    id: userId
                },
                dataType: 'json',
                success: function (data) {
                    console.log(data);
                    $(target).modal("show");
                    $(target).on("shown.bs.modal", function () {
                        $("#updateUserId").val(userId);
                        //清空
                        $("#updateUser").find('.modal-title').html("");
                        $("#updateUser").find('.modal-title').html("修改用户：" + name);
                        //数据回显
                        $.each(data, function (key, value) {
                            $("#updateUser").find(":input[name=" + key + "]").val(value);
                            //单独处理选中
                            if (key == 'sex') {
                                if (value == 1) {
                                    $($("#updateUser").find(":input[name=" + key + "]").get(0)).prop('checked', true);
                                } else {
                                    $($("#updateUser").find(":input[name=" + key + "]").get(1)).prop('checked', true);
                                }
                            } else if (key = 'enabled') {
                                if (value == 1) {
                                    $($("#updateUser").find(":input[name=" + key + "]").get(0)).prop('checked', true);
                                } else {
                                    $($("#updateUser").find(":input[name=" + key + "]").get(1)).prop('checked', true);
                                }
                            } else if (key == 'locked') {
                                if (value == 1) {
                                    $($("#updateUser").find(":input[name=" + key + "]").get(1)).prop('checked', true);
                                } else {
                                    $($("#updateUser").find(":input[name=" + key + "]").get(0)).prop('checked', true);
                                }
                            }

                        })
                    })
                }
            })
        });

//        为用户分配角色前加载角色信息
        $("[data-target='#allocRole']").click(function () {
            var userId = $(this).data('id');
            var target = $(this).data('target');
            var username = $(this).data('username');
            $.ajax({
                url: '/sys/user/queryAllRole.do',
                data: {
                    userId: userId
                },
                dataType: 'json',
                success: function (data) {
                    $("#allocRole").find(".modal-title").html("分配角色：" + username);
                    $(target).modal('show');
//                    显示模态框之前加载数据
                    $(target).on('shown.bs.modal', function () {
                        $("#allocRoleCon").html("");
                        $("#allocRoleCon").append("<input type='hidden' name='userId' value='" + userId + "' >");
                        $.each(data, function (key, val) {
                            $("#allocRoleCon").append("<tr><td><input type='checkbox' " + (val['checked'] ? 'checked' : '') + " name='roleIds' value='" + val['id'] + "'></td><td>" + val['id'] + "</td><td>" + val['name'] + "</td><td>" + val['company'] + "</td><td>" + val['dept'] + "</td></tr>")
                        })
                    })

                }
            })
        })
    })
</script>
</body>
</html>