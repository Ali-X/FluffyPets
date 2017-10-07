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
    <div class="col-lg-2 col-md-3 col-xs-12 col-sm-12">
        <br>
        <br>

        <div id="sideMenu" class="list-group">
            <span href="#" class="list-group-item">
                <h1>Tools</h1>
            </span>

            <a href="/root/admin/storage" id="ordersStatistics" class="list-group-item active">
                <span class="glyphicon glyphicon-stats"></span>
                Statistics</a>
            <a href="/root/admin/orders" id="orderManagement" class="list-group-item">
                <span class="glyphicon glyphicon-credit-card"></span>
                Orders</a>
            <a href="/root/admin/users" id="users" class="list-group-item">
                <span class="glyphicon glyphicon-user"></span>
                Users</a>
        </div>
    </div>
    <div class="col-lg-10 col-md-9 col-xs-12 col-sm-12">
        <h2>Products in the storage</h2>
        <table class="table-condensed table-bordered">
            <thead>
            <tr>
                <th class="text-center">record Id</th>
                <th class="text-center">product Id</th>
                <th class="text-center">available</th>
                <th class="text-center">reserved</th>
                <th class="text-center">status</th>
                <th class="text-center">actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.records}" var="record">
                <form method="post" action="/root/admin/storage">
                    <tr>
                        <td>
                            <input type="number" name="recordId" value="${record.getId()}" readonly style="width: 75px">
                        </td>
                        <td>
                            <input type="number" value="${record.getProductId()}" disabled style="width: 75px">
                        </td>
                        <td>
                            <input type="number" name="available" value="${record.getAvailableHere()}"
                                   style="width: 75px">
                        </td>
                        <td>
                            <input type="number" name="reserved" value="${record.getReservedHere()}"
                                   style="width: 75px">
                        </td>
                        <td>
                            <select class="form-control bg-warning" name="status" style="width: 150px">
                                <c:forEach items="${requestScope.statuses}" var="statusi">
                                    <option value="${statusi.getValue()}"
                                            <c:if test="${not empty record}">
                                                <c:if test="${record.getStatus() eq statusi.getValue()}">selected="selected"</c:if>
                                            </c:if>
                                    ><c:out value="${statusi.getValue()}"/></option>
                                </c:forEach>

                            </select>
                        </td>
                        <td>
                            <button class="btn-link" value="update" name="command"><span
                                    class="glyphicon glyphicon-refresh"></span>
                                update
                            </button>

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
