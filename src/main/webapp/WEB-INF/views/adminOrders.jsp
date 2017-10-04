<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8"%>

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

            <a href="#" id="ordersStatistics" class="list-group-item">
                <span class="glyphicon glyphicon-stats"></span>
                Statistics <span class="badge">314</span> </a>
            <a href="/root/admin/orders" id="orderManagement" class="list-group-item active">
                <span class="glyphicon glyphicon-credit-card"></span>
                Orders</a>
            <a href="/root/admin/users" id="users" class="list-group-item">
                <span class="glyphicon glyphicon-user"></span>
                Users</a>
        </div>
    </div>
    <div class="col-lg-10 col-md-9 col-xs-12 col-sm-12">
        <h2>Orders Table</h2>
        <table class="table-condensed table-bordered">
            <thead>
            <tr>
                <th class="text-center">order Id</th>
                <th class="text-center">user Id</th>
                <th class="text-center">order Date</th>
                <th class="text-center">order status</th>
                <th class="text-center">delivery date</th>
                <th class="text-center">actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.orders}" var="concreteOrder">
                <form method="post" action="/root/admin/orders">
                    <tr>
                        <td>
                            <input type="number" name="orderId" value="${concreteOrder.getOrderId()}" readonly>
                        </td>
                        <td>
                            <input type="number" name="userId" value="${concreteOrder.getUserId()}" readonly>
                        </td>
                        <td>
                            <input type="date" value="${concreteOrder.getOrderDate()}" readonly>
                        </td>
                        <td>
                            <input type="text" name="status" value="${concreteOrder.getStatus()}" class="text-success">
                        </td>
                        <td>
                            <input type="date" name="delivery" value="${concreteOrder.getDeliveryDate()}"
                                   class="text-success">
                        </td>
                        <td>
                            <button class="btn-link" value="update" name="action"><span
                                    class="glyphicon glyphicon-refresh"></span>
                                update
                            </button>

                            <button formaction="/root/admin/users" formmethod="post" class="btn-link" value="blocked"
                                    name="action"><span
                                    class="glyphicon glyphicon-remove"></span> block
                            </button>

                            <button formmethod="post" class="btn-link" value="delete" name="action"><span
                                    class="glyphicon glyphicon-remove-sign"></span> delete
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
