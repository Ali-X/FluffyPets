<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign up</title>
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
                <span class="sr-only">Navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<c:url value="/root/home"/> ">FluffyPets.com</a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="<c:url value="/root/home"/> ">Products</a></li>
                <li><a href="<c:url value="/root/login"/>">
                    <span class="glyphicon glyphicon-log-in"></span>Signin</a></li>
                <li class="active"><a href="<c:url value="/root/signup"/> ">Signup</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <span class="glyphicon glyphicon-shopping-cart"></span>My cart<b class="caret"></b></a>
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
                            <input type="email" required class="form-control"
                                   id="form-email" name="form-email" placeholder="Email Address">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="form-userName" class="col-md-3 control-label">Login</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control"
                                   required id="form-userName" name="form-userName"  placeholder="Enter login">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="form-password" class="col-md-3 control-label">Password</label>
                        <div class="col-md-9">
                            <input type="password" class="form-control"
                                   required id="form-password" name="form-password"  placeholder="Password">
                        </div>
                    </div>

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
<script src="${pageContext.request.contextPath}/resourseces/js/jquery-3.2.1.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="${pageContext.request.contextPath}/resourseces/js/bootstrap.min.js"></script>

<script>
    function validateForm() {
        var login = document.forms["signupform"]["form-userName"].value;
        var password = document.forms["signupform"]["form-password"].value;

        if (login.length < 5) {
            alert("Your login should be longer");
            return false;
        }
        if (password.length < 5) {
            alert("Your password should be longer");
            return false;
        }
    }
</script>

</body>
</html>
