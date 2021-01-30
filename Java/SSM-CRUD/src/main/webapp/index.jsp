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
            <button class="btn btn-primary">新增</button>
            <button class="btn btn-danger">删除</button>
        </div>
    </div>
    <%--显示表格数据--%>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover" id="emps_table">
                <thead>
                <tr>
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
        //页面加载完成后，发送 ajax 请求要求数据
        $(function () {
            $.ajax({
                url: "/emps",
                data: "pn=1",
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
        });

        function build_emps_table(result) {
            var emps = result.extend.pageInfo.list;
            $.each(emps, function (index, item) {
                var empIdTd = $("<td></td>").append(item.id);
                var empNameTd = $("<td></td>").append(item.name);
                var empGenderTd = $("<td></td>").append(item.gender === "M" ? "男" : "女");
                var empEmailTd = $("<td></td>").append(item.email);
                var empDeptNameTd = $("<td></td>").append(item.dept.name);
                var editBtn = $("<button></button>").addClass("btn btn-primary btn-sm").append($("<span></span>")
                    .addClass("glyphicon glyphicon-pencil").append("编辑"));
                var delBtn = $("<button></button>").addClass("btn btn-danger btn-sm").append($("<span></span>")
                    .addClass("glyphicon glyphicon-trash").append("删除"));
                var btnTd = $("<td></td>").append(editBtn).append(" ").append(delBtn);
                $("<tr></tr>")
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
            var pageInfo = result.extend.pageInfo;
            $("#page_info_area").append("当前 " + pageInfo.pageNum + " 页，总共 " + pageInfo.pages + " 页，总共 " + pageInfo.total + " 条记录");
        }

        //解析显示分页条
        function build_page_nav(result) {
            var ul = $("<ul></ul>").addClass("pagination");
            var pageInfo = result.extend.pageInfo;
            var firstPageLi = $("<li></li>").append($("<a></a>").attr("href", "#").append("首页"));
            var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));

            var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
            var lastPageLi = $("<li></li>").append($("<a></a>").attr("href", "#").append("末页"));
            ul.append(firstPageLi).append(prePageLi)
            $.each(result.extend.pageInfo.navigatepageNums, function (index, item) {
               var numLi = $("<li></li>").append($("<a></a>").attr("href", "#").append(item));
               ul.append(numLi);
            });
            ul.append(nextPageLi).append(lastPageLi);
            var navEle = $("<nav></nav>").append(ul);
            navEle.appendTo("#page_nav_area");
        }

    </script>
</div>

</body>
</html>
