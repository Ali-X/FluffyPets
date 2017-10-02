<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Sign up</title>
    <meta charset="utf-8">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css" rel="stylesheet">
</head>
<body>
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
                    <li><a href="<c:url value="/root/login"/>">
                        <span class="glyphicon glyphicon-log-in"></span>
                        Signin</a></li>
                    <li class="active"><a href="<c:url value="/root/signup"/>">
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

<div class="container">
    <div id="signupbox" style="margin-top:50px" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <div class="panel panel-info">
            <div class="panel-heading">
                <div class="panel-title">Sign up on Fluffy.pets.com</div>
            </div>
            <div class="panel-body">
                <form id="signupform" class="form-horizontal" method="post"
                      onsubmit="return validateForm()" action="<c:url value="/root/signup"/>">

                    <div class="form-group">
                        <label for="form-email" class="col-md-3 control-label">Email</label>
                        <div class="col-md-9">
                            <input type="email" required
                            <c:choose>
                            <c:when test="${not empty requestScope.validationSignUp.getFieldStatuses().get(0)}">
                                    value="${requestScope.validationSignUp.getValidationObject().getEmail()}"
                                   <c:if test="${requestScope.validationSignUp.getFieldStatuses().get(0).isStatus()}">class="form-control"</c:if>
                                   <c:if test="${not requestScope.validationSignUp.getFieldStatuses().get(0).isStatus()}">class="form-control alert-danger"</c:if>
                            </c:when>
                            <c:otherwise>class="form-control"
                            </c:otherwise>
                            </c:choose>
                                   id="form-email" name="email" placeholder="Email Address">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="form-userName" class="col-md-3 control-label">Login</label>
                        <div class="col-md-9">
                            <input type="text"
                            <c:choose>
                            <c:when test="${not empty requestScope.validationSignUp.getFieldStatuses().get(1)}">
                                   value="${requestScope.validationSignUp.getValidationObject().getUsername()}"
                                   <c:if test="${requestScope.validationSignUp.getFieldStatuses().get(1).isStatus()}">class="form-control"</c:if>
                                   <c:if test="${not requestScope.validationSignUp.getFieldStatuses().get(1).isStatus()}">class="form-control alert-danger"</c:if>
                            </c:when>
                                   <c:otherwise> class="form-control"
                            </c:otherwise>
                            </c:choose>
                                   required id="form-userName" name="username" placeholder="Enter login">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="form-password" class="col-md-3 control-label">Password</label>
                        <div class="col-md-9">
                            <input type="password"
                            <c:choose>
                            <c:when test="${not empty requestScope.validationSignUp.getFieldStatuses().get(2)}">
                                   <c:if test="${requestScope.validationSignUp.getFieldStatuses().get(2).isStatus()}">class="form-control"</c:if>
                                   <c:if test="${not requestScope.validationSignUp.getFieldStatuses().get(2).isStatus()}">class="form-control alert-danger"</c:if>
                            </c:when>
                                   <c:otherwise>class="form-control"
                            </c:otherwise>
                            </c:choose>
                                   required id="form-password" name="password" placeholder="Password">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="form-password2" class="col-md-3 control-label">Password</label>
                        <div class="col-md-9">
                            <input type="password"
                            <c:choose>
                            <c:when test="${not empty requestScope.validationSignUp.getFieldStatuses().get(3)}">
                                   <c:if test="${requestScope.validationSignUp.getFieldStatuses().get(3).isStatus()}">class="form-control"</c:if>
                                   <c:if test="${not requestScope.validationSignUp.getFieldStatuses().get(3).isStatus()}">class="form-control alert-danger"</c:if>
                            </c:when>
                                   <c:otherwise>class="form-control"
                            </c:otherwise>
                            </c:choose>
                                   required id="form-password2" name="password2" placeholder="repeat it">
                        </div>
                    </div>

                    <c:if test="${not empty requestScope.validationSignUp}"><label
                            class="text-danger">${requestScope.validationSignUp.getValidationMessage()}</label></c:if>

                    <div class="form-group">
                        <!-- Button -->
                        <div class="col-md-offset-3 col-md-9">
                            <button type="submit" class="btn btn-success btn-lg">
                                Sign Up
                            </button>
                        </div>
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
