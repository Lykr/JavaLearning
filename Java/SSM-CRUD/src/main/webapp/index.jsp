<%--
  Created by IntelliJ IDEA.
  User: lykr
  Date: 2021/1/30
  Time: 16:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>员工列表</title>
    <%--引入 JQuery--%>
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <%--引入样式--%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
</head>
<body>

<!-- 员工添加的模态框 -->
<div class="modal fade" id="addEmpModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">员工添加</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Emp Name</label>
                        <div class="col-sm-10">
                            <input type="text" name="name" class="form-control" id="input_add_empName" placeholder="empName">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Email</label>
                        <div class="col-sm-10">
                            <input type="text" name="email" class="form-control" id="input_add_empEmial" placeholder="empEmail">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Gender</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="input_add_empGender_1" value="M" checked="checked"> 男
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="input_add_empGender_2" value="F"> 女
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">DeptName</label>
                        <div class="col-sm-4">
                            <select class="form-control" name="dId" id="select_add_dept">

                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="save_emp">保存</button>
            </div>
        </div>
    </div>
</div>


<!-- 员工修改的模态框 -->
<div class="modal fade" id="updateEmpModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">员工添加</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Emp Name</label>
                        <div class="col-sm-10">
                            <p class="form-control-static" id="update_empName_status"></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Email</label>
                        <div class="col-sm-10">
                            <input type="text" name="email" class="form-control" id="input_update_empEmial" placeholder="empEmail">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Gender</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="input_update_empGender_1" value="M" checked="checked"> 男
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="input_update_empGender_2" value="F"> 女
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">DeptName</label>
                        <div class="col-sm-4">
                            <select class="form-control" name="dId" id="select_update_dept">

                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="update_emp">更新</button>
            </div>
        </div>
    </div>
</div>

<div class="container">
    <%--标题--%>
    <div class="row">
        <div class="col-md-12">
            <h1>SSM-CRUD</h1>
        </div>
    </div>
    <%--新增和删除按钮--%>
    <div class="row">
        <div class="col-md-4 col-md-offset-8">
            <button class="btn btn-primary" id="add_emp_modal_btn">新增</button>
            <button class="btn btn-danger" id="del_all">删除</button>
        </div>
    </div>
    <%--显示表格数据--%>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover" id="emps_table">
                <thead>
                <tr>
                    <th>
                        <input type="checkbox" id="check_all">
                    </th>
                    <th>#</th>
                    <th>empName</th>
                    <th>gender</th>
                    <th>email</th>
                    <th>deptName</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
        </div>
    </div>
    <%--显示分页信息--%>
    <div class="row">
        <div class="row">
            <div class="col-md-6" id="page_info_area">

            </div>
            <div class="col-md-6" id="page_nav_area">

            </div>
        </div>
    </div>

    <script type="text/javascript">
        var totalRecord, currentPage;

        //页面加载完成后，发送 ajax 请求要求数据
        $(function () {
            to_page(1);
        });

        function to_page(pn) {
            $.ajax({
                url: "/emps",
                data: "pn=" + pn,
                type: "get",
                success: function (result) {
                    //1. 解析并显示员工信息
                    build_emps_table(result);
                    //2. 解析并显示分页信息
                    build_page_info(result);
                    //3. 解析显示分页条
                    build_page_nav(result);
                }
            });
        }

        //解析员工列表
        function build_emps_table(result) {
            $("#emps_table tbody").empty();
            var emps = result.extend.pageInfo.list;
            $.each(emps, function (index, item) {
                var checkBoxTd = $("<td><input type='checkbox' class='check_item' /></td>");
                var empIdTd = $("<td></td>").append(item.id);
                var empNameTd = $("<td></td>").append(item.name);
                var empGenderTd = $("<td></td>").append(item.gender === "M" ? "男" : "女");
                var empEmailTd = $("<td></td>").append(item.email);
                var empDeptNameTd = $("<td></td>").append(item.dept.name);
                var editBtn = $("<button></button>").addClass("btn btn-primary btn-sm edit_btn").append($("<span></span>")
                    .addClass("glyphicon glyphicon-pencil").append("编辑"));
                editBtn.attr("edit-id", item.id); //为按钮添加员工 id 属性值
                var delBtn = $("<button></button>").addClass("btn btn-danger btn-sm del_btn").append($("<span></span>")
                    .addClass("glyphicon glyphicon-trash").append("删除"));
                delBtn.attr("edit-id", item.id); //为按钮添加员工 id 属性值
                var btnTd = $("<td></td>").append(editBtn).append(" ").append(delBtn);
                $("<tr></tr>")
                    .append(checkBoxTd)
                    .append(empIdTd)
                    .append(empNameTd)
                    .append(empGenderTd)
                    .append(empEmailTd)
                    .append(empDeptNameTd)
                    .append(btnTd)
                .appendTo("#emps_table tbody");
            });
        }

        //解析显示分页信息
        function build_page_info(result) {
            $("#page_info_area").empty();
            var pageInfo = result.extend.pageInfo;
            $("#page_info_area").append("当前 " + pageInfo.pageNum + " 页，总共 " + pageInfo.pages + " 页，总共 " + pageInfo.total + " 条记录");
            totalRecord = pageInfo.total;
            currentPage = pageInfo.pageNum;
        }

        //解析显示分页条
        function build_page_nav(result) {
            $("#page_nav_area").empty();
            var ul = $("<ul></ul>").addClass("pagination");
            var pageInfo = result.extend.pageInfo;

            var firstPageLi = $("<li></li>").append($("<a></a>").attr("href", "#").append("首页"));
            var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));
            if (!pageInfo.hasPreviousPage) {
                firstPageLi.addClass("disabled");
                prePageLi.addClass("disabled");
            } else {
                firstPageLi.click(function () {
                    to_page(1);
                });
                prePageLi.click(function () {
                    to_page(pageInfo.pageNum - 1);
                });
            }

            var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
            var lastPageLi = $("<li></li>").append($("<a></a>").attr("href", "#").append("末页"));
            if (!pageInfo.hasNextPage) {
                nextPageLi.addClass("disabled");
                lastPageLi.addClass("disabled");
            } else {
                nextPageLi.click(function () {
                    to_page(pageInfo.pageNum + 1);
                });
                lastPageLi.click(function () {
                    to_page((pageInfo.pages));
                });
            }

            ul.append(firstPageLi).append(prePageLi)
            $.each(pageInfo.navigatepageNums, function (index, item) {
                var numLi = $("<li></li>").append($("<a></a>").attr("href", "#").append(item));
                if (pageInfo.pageNum === item) {
                    numLi.addClass("active");
                }
                numLi.click(function() {
                    to_page(item);
                })
                ul.append(numLi);
            });
            ul.append(nextPageLi).append(lastPageLi);
            var navEle = $("<nav></nav>").append(ul);
            navEle.appendTo("#page_nav_area");
        }

        //重置表格
        function reset_form(ele) {
            $(ele)[0].reset();
            $(ele).find("*").removeClass("has-success has-error");
            $(ele).find(".help-block").text("");
        }

        //点击按钮弹出模态框
        $("#add_emp_modal_btn").click(function () {
            //重置表单
            reset_form("#addEmpModal form")

            //查出部门信息
            getDepts("#select_add_dept");

            $("#addEmpModal").modal({
                backdrop:"static"
            });
        });

        //查出部门信息
        function getDepts(ele) {
            $(ele).empty();
            $.ajax({
                url:"/depts",
                type:"GET",
                success: function (result) {
                    var depts = result.extend.depts;

                    $.each(depts, function (index, item) {
                        var optionEle = $("<option></option>").append(item.name).attr("value", item.id);
                        optionEle.appendTo(ele);
                    });
                }
            })
        }

        //显示验证信息
        function show_validate_msg(ele, status, msg) {
            // 清除校验状态
            $(ele).parent().removeClass("has-success has-error");
            $(ele).next("span").text("");

            if (status === "success") {
                $(ele).parent().addClass("has-success");
                $(ele).next("span").text(msg);
            } else if (status === "error") {
                $(ele).parent().addClass("has-error");
                $(ele).next("span").text(msg);
            }
        }

        //校验
        function validate_add_form() {
            // 检验姓名
            var empName = $("#input_add_empName").val();
            var regName = /(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})/;
            if (!regName.test(empName)) {
                show_validate_msg("#input_add_empName", "error", "用户名格式错误");
                return false;
            } else {
                show_validate_msg("#input_add_empName", "success", "");
            }
            // 检验邮箱
            var email = $("#input_add_empEmial").val();
            var regEmail = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
            if (!regEmail.test(email)) {
                show_validate_msg("#input_add_empEmial", "error", "邮箱格式错误");
                return false;
            } else {
                show_validate_msg("#input_add_empEmial", "success", "");
            }
            return true;
        }

        //校验用户名是否重复
        $("#input_add_empName").change(function () {
            $.ajax({
                url:"/checkuser",
                type:"get",
                data:"name=" + this.value,
                success:function(result) {
                    if (result.code === 100) {
                        show_validate_msg("#input_add_empName", "success", "用户名可用");
                        $("#save_emp").attr("ajax-va", "success");
                    } else {
                        show_validate_msg("#input_add_empName", "error", result.extend.va_msg);
                        $("#save_emp").attr("ajax-va", "error");
                    }
                }
            });
        });

        //保存数据
        $("#save_emp").click(function () {
            // 验证数据
            if (!validate_add_form()) {
                return false;
            }

            // 判断数据库是否校验成功
            if($(this).attr("ajax-va") === "error") {
                return false;
            }

            // 将模态框的数据返回给服务器
            $.ajax({
                url:"/emp",
                type:"POST",
                data:$("#addEmpModal form").serialize(),
                success:function (result) {
                    if (result.code == 100) {
                        $("#addEmpModal").modal("hide");
                        to_page(totalRecord);
                    } else {
                        var errorFields = result.extend.errorFields;
                        if (errorFields.email != undefined) {
                            show_validate_msg("#input_add_empEmail", "error", errorFields.email);
                        }
                        if (errorFields.name != undefined) {
                            show_validate_msg("#input_add_empName", "error", errorFields.name);
                        }
                    }
                }
            });
        });

        //编辑按钮的单击事件
        $(document).on("click", ".edit_btn", function () {
            // 查员工信息
            getEmp($(this).attr("edit-id"));
            // 查部门信息
            getDepts("#updateEmpModal select");

            //把 id 传递给更新按钮
            $("#update_emp").attr("edit-id", $(this).attr("edit-id"));
            $("#updateEmpModal").modal({
                backdrop:"static"
            });
        });

        //获取员工信息
        function getEmp(id) {
            $.ajax({
                url: "/emp/" + id,
                type: "GET",
                success: function (result) {
                    var emp = result.extend.emp;
                    $("#update_empName_status").text(emp.name);
                    $("#input_update_empEmial").val(emp.email);
                    $("#updateEmpModal input[name=gender]").val([emp.gender]);
                    $("#updateEmpModal select").val([emp.dId]);
                }
            });
        }

        //更新按钮的单击事件
        $("#update_emp").click(function () {
            var email = $("#input_update_empEmial").val();
            var regEmail = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
            if (!regEmail.test(email)) {
                show_validate_msg("#input_update_empEmial", "error", "邮箱格式错误");
                return false;
            } else {
                show_validate_msg("#input_update_empEmial", "success", "");
            }

            $.ajax({
                url: "/emp/" + $(this).attr("edit-id"),
                //如果直接用 ajax 发 put 类型的请求，除了 url 上拼接的数据，其他数据都无法封装
                //原因是对于 post 类型的请求 tomcat 不会将请求体中的数据封装为 Map
                type: "PUT",
                data: $("#updateEmpModal form").serialize(),
                success: function (result) {
                    $("#updateEmpModal").modal("hide");
                    to_page(currentPage);
                }
            });
        });

        //删除按钮单击事件
        $(document).on("click", ".del_btn", function () {
            //确认删除对话框
            var empName = $(this).parents("tr").find("td:eq(2)").text();
            var empId = $(this).attr("edit-id");
            if (confirm("确认删除 " + empName + " 吗？")) {
                $.ajax({
                    url: "/emp/" + empId,
                    type: "DELETE",
                    success: function (result) {
                        alert("处理成功");
                        to_page(currentPage);
                    }
                });
            }
        });

        //全选删除
        $("#check_all").click(function () {
            var checked = $(this).prop("checked");
            $(".check_item").prop("checked", checked);
        });

        //根据选框数量需改全选框状态
        $(document).on("click", ".check_item", function () {
            var checked = $(".check_item:checked").length === $(".check_item").length;
            $("#check_all").prop("checked", checked);
        });

        //批量删除单击事件
        $("#del_all").click(function () {
            var empNames = "";
            var empIds = "";
            $.each($(".check_item:checked"), function () {
                var empId = $(this).parents("tr").find("td:eq(1)").text();
                var empName = $(this).parents("tr").find("td:eq(2)").text();
                empNames += empName + ", ";
                empIds += empId + "-";
            });
            empNames = empNames.substring(0, empNames.length - 1);
            empIds = empIds.substring(0, empIds.length - 1);

            if (confirm("是否删除 " + empNames + " 用户")) {
                $.ajax({
                    url: "/emp/" + empIds,
                    type: "DELETE",
                    success: function (result) {
                        alert(result.msg);
                        to_page(currentPage);
                    }
                });
            }
        });
    </script>
</div>

</body>
</html>
