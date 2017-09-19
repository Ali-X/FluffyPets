<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Fluffy Pets</title>
    <meta charset="utf-8">
    <link href="${pageContext.request.contextPath}/resourseces/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resourseces/css/bootstrap-theme.min.css" rel="stylesheet">
</head>
<body>
<meta name="viewport" content="width=device-width, initial-scale=1">
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="glyphicon glyphicon-align-justify"></span>
            </button>
            <a class="navbar-brand" href="<c:url value="/root/home"/> ">FluffyPets.com</a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">

                <li><a href="<c:url value="/root/home"/> ">Products</a></li>

                <c:if test="${not empty requestScope.user}">
                    <c:if test="${requestScope.user.getRoleString().equals('admin')}">
                        <li><a href="<c:url value="/root/createProduct"/>">
                            <span class="glyphicon glyphicon-edit"></span>
                            Create product</a></li>
                        <li class="active"><a href="<c:url value="/root/admin"/>">
                            <span class="glyphicon glyphicon-wrench"></span>
                            Admin page</a></li>
                    </c:if></c:if>

                <c:if test="${empty requestScope.user}">
                    <li><a href="<c:url value="/root/login"/>">
                        <span class="glyphicon glyphicon-log-in"></span>
                        Signin</a></li>
                    <li><a href="<c:url value="/root/signup"/>">
                        <span class="glyphicon glyphicon-ok"></span>
                        Signup</a></li>
                </c:if>

                <c:if test="${not empty requestScope.user}">
                    <li class="text-warning"><a href="<c:url value="/root/profile"/>">
                        <span class="glyphicon glyphicon-user"></span>
                        My profile</a></li>

                    <li class="text-warning"><a href="<c:url value="/root/logout"/>">
                        <span class="glyphicon glyphicon-log-out"></span>
                        Logout</a></li>
                </c:if>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <span class="glyphicon glyphicon-shopping-cart"></span>My cart<b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <c:if test="${empty requestScope.user}">
                            <li><a href="/root/login">You are not authorised, please login!</a></li>
                        </c:if>
                        <c:if test="${not empty requestScope.user}">
                            <li><a href="#">Welcome ${requestScope.user.getUserName()}!</a></li>
                        </c:if>
                        <c:if test="${not empty requestScope.cart.getProductInCarts()}">
                            <li class="divider"></li>
                            <c:forEach items="${requestScope.cart.getProductInCarts()}" var="cartItem">
                                <li class="text-center">
                                    <form method="post">
                                        <div class="row center-block">
                                                ${cartItem.getProduct().getName()} :
                                            <button class="btn-link"  formaction="/root/takeFromCart"
                                                    name="productId"    value="${cartItem.getProduct().getId()}" >
                                                <span class="glyphicon glyphicon-minus"></span>
                                            </button>
                                                ${cartItem.getNumber()}
                                            <button class="btn-link"  formaction="/root/addToCart"
                                                    name="productId" value="${cartItem.getProduct().getId()}" >
                                                <span class="glyphicon glyphicon-plus"></span>
                                            </button>
                                        </div>
                                    </form>
                                </li>
                            </c:forEach>
                            <li class="divider"></li>
                            <li class="text-center">
                                <form method="post">
                                    <button type="submit"
                                            <c:if test="${not empty requestScope.user}"> formaction="/root/makeOder" class="btn btn-danger"</c:if>
                                            <c:if test="${empty requestScope.user}"> class="btn btn-default" disabled="disabled"</c:if>
                                    > Confirm your order</button>
                                </form></li>
                        </c:if>
                    </ul>
                </li>

            </ul>
        </div>
    </div>
</nav>

<div class="row">
    <div class="col-lg-2 col-md-3 col-sm-12">
        <br>
        <br>

        <div id="sideMenu" class="list-group">
            <span href="#" class="list-group-item">
                <h1>Tools</h1>
            </span>

            <a href="#" id="ordersStatistics" class="list-group-item">
                <span class="glyphicon glyphicon-stats"></span>
                Statistics <span class="badge">314</span> </a>
            <a href="/root/adminOrders" id="orderManagement" class="list-group-item active">
                <span class="glyphicon glyphicon-credit-card"></span>
                Orders</a>
            <a href="/root/admin" id="users" class="list-group-item">
                <span class="glyphicon glyphicon-user"></span>
                Users</a>
        </div>
    </div>
    <div class="col-lg-10 col-md-9 col-sm-12">
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
                <form method="post" action="/root/adminOrders">
                    <tr>
                        <td>
                            <input type="number" name="orderId"  value="${concreteOrder.getOrderId()}" readonly>
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
                            <input type="date" name="delivery" value="${concreteOrder.getDeliveryDate()}" class="text-success">
                        </td>
                        <td>
                                <button class="btn-link" value="update" name="command"><span
                                        class="glyphicon glyphicon-refresh"></span>
                                    update
                                </button>

                                <button  formaction="/root/admin" formmethod="post" class="btn-link" value="blocked" name="command"><span
                                        class="glyphicon glyphicon-remove"></span> block
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
