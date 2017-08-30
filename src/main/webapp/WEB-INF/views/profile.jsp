<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My profile</title>
    <meta charset="utf-8">
    <link href="${pageContext.request.contextPath}/resourseces/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resourseces/css/bootstrap-theme.min.css" rel="stylesheet">
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

                <c:if test="${empty requestScope.user}">
                    <li><a href="<c:url value="/root/login"/>">
                        <span class="glyphicon glyphicon-log-in"></span>
                        Signin</a></li>
                    <li><a href="<c:url value="/root/signup"/>">
                        <span class="glyphicon glyphicon-ok"></span>
                        Signup</a></li>
                </c:if>

                <c:if test="${not empty requestScope.user}">
                    <li class="active"><a href="<c:url value="/root/profile"/>">
                        <span class="glyphicon glyphicon-user"></span>
                        My profile</a></li>

                    <li><a href="<c:url value="/root/logout"/>">    <%--todo: make logout--%>
                        <span class="glyphicon glyphicon-log-out"></span>
                        Logout</a></li>
                </c:if>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <span class="glyphicon glyphicon-shopping-cart"></span>My cart<b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <c:if test="${empty requestScope.user}">
                            <li><a href="#">You are not autorised!</a></li>
                        </c:if>
                        <c:if test="${not empty requestScope.user}">
                            <li><a href="#">Welcome ${requestScope.user.getUserName()}!</a></li>
                        </c:if>
                        <li class="divider"></li>
                        <c:if test="${empty requestScope.myCart}">
                            <li><a href="#">your cart is empty</a></li>
                        </c:if>
                    </ul>
                </li>

            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <div class="row">
        <div class="col-md-10 ">
            <form class="form-horizontal" method="get"
                  action="<c:url value="/root/editProfile"/>">

                <!-- Form Name -->
                <legend>Welcome to your profile</legend>

                <div class="form-group">
                    <label class="col-md-4 control-label">Name (Full name)</label>
                    <div class="col-md-4">
                        <label>${requestScope.user.getUserName()}</label>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label">Date Of Birth</label>
                    <div class="col-md-4">
                        <label>${requestScope.user.getPassword()}</label>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label">Gender:</label>
                    <div class="col-md-4">
                        <label class="col-md-4">${requestScope.user.getToken()}</label>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label">Marital Status:</label>
                    <div class="col-md-4">
                        <label class="col-md-4">${requestScope.user.getEmail()}</label>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label">Address</label>
                    <div class="col-md-4">
                        <label class="col-md-4">${requestScope.user.getRoleString()}</label>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label">Phone number </label>
                    <div class="col-md-4">
                        <label class="col-md-4">${requestScope.user.getId()}</label>
                    </div>
                </div>
                <button type="submit" class="btn btn-warning btn-lg center-block"> Edit profile</button>
            </form>
        </div>
    </div>
</div>
<!-- jQuery -->
<script src="${pageContext.request.contextPath}/resourseces/js/jquery-3.2.1.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="${pageContext.request.contextPath}/resourseces/js/bootstrap.min.js"></script>
</body>
</html>
