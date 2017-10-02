<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login</title>
    <meta charset="utf-8">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css" rel="stylesheet">
</head>
<body>
<%--                                            BEGIN 0f navbar                                        --%>
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
                        <li><a href="<c:url value="/root/admin/createProduct"/>">
                            <span class="glyphicon glyphicon-edit"></span>
                            Create product</a></li>
                        <li><a href="<c:url value="/root/admin/users"/>">
                            <span class="glyphicon glyphicon-wrench"></span>
                            Admin page</a></li>
                    </c:if></c:if>

                <c:if test="${empty requestScope.user}">
                    <li class="active"><a href="<c:url value="/root/login"/>">
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
                                            <button class="btn-link" formaction="/root/takeFromCart"
                                                    name="productId" value="${cartItem.getProduct().getId()}">
                                                <span class="glyphicon glyphicon-minus"></span>
                                            </button>
                                                ${cartItem.getNumber()}
                                            <button class="btn-link" formaction="/root/addToCart"
                                                    name="productId" value="${cartItem.getProduct().getId()}">
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
                                    > Confirm your order
                                    </button>
                                </form>
                            </li>
                        </c:if>
                    </ul>
                </li>

            </ul>
        </div>
    </div>
</nav>
<%--                                            END 0f navbar                                        --%>
<div class="container">
    <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <div class="panel panel-info">
            <div class="panel-heading">
                <div class="panel-title">Sign In</div>
                <div style="float:right; font-size: 80%; position: relative; top:-20px"><a
                        href="<c:url value="/root/forgot"/>">Forgot password?</a></div>
            </div>

            <div style="padding-top:30px" class="panel-body">

                <form id="loginform" class="form-horizontal" method="post"
                      action="<c:url value="/root/login"/>">

                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <input id="login-username" type="text" name="userName"
                        <c:choose>
                        <c:when test="${not empty requestScope.validationUser.getFieldStatuses().get(0)}">
                        <c:if test="${requestScope.validationUser.getFieldStatuses().get(0).isStatus()}">class="form-control"</c:if>
                        <c:if test="${not requestScope.validationUser.getFieldStatuses().get(0).isStatus()}">class="form-control alert-danger"</c:if>
                            </c:when>
                            <c:otherwise>
                               class="form-control"
                        </c:otherwise>
                        </c:choose>
                               required placeholder="login" class="form-control">
                    </div>

                    <div style="margin-bottom: 5px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                        <input id="login-password" type="password" name="password"
                        <c:choose>
                        <c:when test="${not empty requestScope.validationUser.getFieldStatuses().get(1)}">
                               <c:if test="${requestScope.validationUser.getFieldStatuses().get(1).isStatus()}">class="form-control"</c:if>
                               <c:if test="${not requestScope.validationUser.getFieldStatuses().get(1).isStatus()}">class="form-control alert-danger"</c:if>
                        </c:when>
                        <c:otherwise>
                               class="form-control"
                        </c:otherwise>
                        </c:choose>
                               required placeholder="password">
                    </div>
                    <c:if test="${not empty requestScope.validationUser}"><label
                            class="text-danger">${requestScope.validationUser.getValidationMessage()}</label></c:if>
                    <div class="col-sm-12 controls text-center">
                        <button type="submit" id="btn-login" class="btn btn-success btn-lg ">Login</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- jQuery -->
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>
