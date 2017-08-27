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
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<c:url value="/root/home"/> ">FluffyPets.com</a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li ><a href="<c:url value="/root/home"/> ">Products</a></li>
                <li><a href="<c:url value="/root/login"/>"><span class="glyphicon glyphicon-log-in"></span>Signin</a></li>
                <li class="active"><a href="<c:url value="/root/registration"/> "> <span class="glyphicon glyphicon-user"></span> My profile</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <span class="glyphicon glyphicon-shopping-cart"></span> My cart
                        <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">You are not autorised!</a></li>
                        <li class="divider"></li>
                        <li><a href="#">your cart is empty</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <div class="row">
        <div class="col-md-10 ">
            <form class="form-horizontal" method="get" action="<c:url value="/root/editProfile"/>">

                <!-- Form Name -->
                <legend>Welcome to your profile</legend>

                <div class="form-group">
                    <label class="col-md-4 control-label">Name (Full name)</label>
                    <div class="col-md-4">
                        <label>${requestScope.user.fullName}</label>
                    </div></div>

                <div class="form-group">
                    <label class="col-md-4 control-label">Date Of Birth</label>
                    <div class="col-md-4">
                        <label>${requestScope.user.birthDate}</label>
                    </div></div>

                <div class="form-group">
                    <label class="col-md-4 control-label">Gender:</label>
                    <div class="col-md-4">
                        <label class="col-md-4">${requestScope.user.gender}</label>
                    </div></div>

                <div class="form-group">
                    <label class="col-md-4 control-label">Marital Status:</label>
                    <div class="col-md-4">
                        <label class="col-md-4">${requestScope.user.status}</label>
                    </div></div>

                <div class="form-group">
                    <label class="col-md-4 control-label">Address</label>
                    <div class="col-md-4">
                        <label class="col-md-4">${requestScope.user.address}</label>
                    </div></div>

                <div class="form-group">
                    <label class="col-md-4 control-label">Phone number </label>
                    <div class="col-md-4">
                        <label class="col-md-4">${requestScope.user.telephone}</label>
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
