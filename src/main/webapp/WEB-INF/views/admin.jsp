<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Fluffy Pets</title>
    <meta charset="utf-8">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css" rel="stylesheet">
</head>
<body>
<meta name="viewport" content="width=device-width, initial-scale=1">

<t:navbar activePage="admin"/>


<div class="row">
    <div class="col-lg-2 col-md-3 col-sm-12">
        <br>
        <br>

        <div id="sideMenu" class="list-group">
            <span href="#" class="list-group-item">
                <h1>Tools</h1>
            </span>

            <a href="/root/admin/storage" id="ordersStatistics" class="list-group-item">
                <span class="glyphicon glyphicon-stats"></span>
                Statistics</a>
            <a href="/root/admin/orders" id="orderManagement" class="list-group-item">
                <span class="glyphicon glyphicon-credit-card"></span>
                Orders</a>
            <a href="/root/admin/users" id="users" class="list-group-item active">
                <span class="glyphicon glyphicon-user"></span>
                Users</a>
        </div>
    </div>
    <div class="col-lg-10 col-md-9 col-sm-12">
        <h2>Users Table</h2>
        <table class="table-condensed table-bordered">
            <thead>
            <tr>
                <th class="text-center">UserId</th>
                <th class="text-center">User Name</th>
                <th class="text-center">Email</th>
                <th class="text-center">Role</th>
                <th class="text-center">Allowed actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.users}" var="userFromBase">
                <form method="post" action="/root/admin/users">
                    <tr>
                        <td>
                            <input type="number" name="userId" value="${userFromBase.getId()}" readonly style="width: 75px">
                        </td>
                        <td>
                            <input type="text" value="${userFromBase.getUserName()}" readonly disabled style="width: 150px">
                        </td>
                        <td>
                            <input type="email" value="${userFromBase.getEmail()}" readonly disabled style="width: 225px">
                        </td>
                        <td>
                            <input type="text" value="${userFromBase.getRoleString()}" readonly disabled style="width: 75px">
                        </td>
                        <td>
                            <c:if test="${userFromBase.getRoleString() ne 'user'}">
                                <button class="btn-link" value="user" name="command"><span
                                        class="glyphicon glyphicon-user"></span>
                                    make user
                                </button>
                            </c:if>
                            <c:if test="${userFromBase.getRoleString() ne 'courier'}">
                                <button class="btn-link" value="courier" name="command"><span
                                        class="glyphicon glyphicon-transfer"></span>
                                    make courier
                                </button>
                            </c:if>
                            <c:if test="${userFromBase.getRoleString() ne 'admin'}">
                                <button class="btn-link" value="admin" name="command"><span
                                        class="glyphicon glyphicon-wrench"></span> make admin
                                </button>
                            </c:if>
                            <c:if test="${userFromBase.getRoleString() ne 'admin'}">
                                <button class="btn-link" value="blocked" name="command"><span
                                        class="glyphicon glyphicon-remove"></span> block
                                </button>
                            </c:if>
                        </td>
                    </tr>
                </form>
            </c:forEach>
            </tbody>
        </table>


    </div>
</div>

</body>
</html>
