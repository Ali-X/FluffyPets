<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
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
                    <li><a href="<c:url value="/root/home"/> ">Products</a></li>
                    <li class="active"><a href="<c:url value="/root/login"/>">Signin</a></li>
                    <li><a href="<c:url value="/root/signup"/> "> Signup</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">My cart<b class="caret"></b></a>
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
        <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
            <div class="panel panel-info" >
                <div class="panel-heading">
                    <div class="panel-title">Sign In</div>
                    <div style="float:right; font-size: 80%; position: relative; top:-20px"><a href="<c:url value="/root/forgot"/>">Forgot password?</a></div>
                </div>

                <div style="padding-top:30px" class="panel-body" >

                    <!-- <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div> -->

                    <form id="loginform" class="form-horizontal" method="post" action="<c:url value="/root/login"/>">

                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                            <input id="login-username" type="text" class="form-control" name="userName" value="" placeholder="username or email">
                        </div>

                        <div style="margin-bottom: 5px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                            <input id="login-password" type="password" class="form-control" name="password" placeholder="password">
                        </div>

                        <div class="input-group">
                            <div class="checkbox">
                                <label>
                                    <input id="login-remember" type="checkbox" name="remember" value="1"> Remember me
                                </label>
                            </div>
                        </div>
                        <div class="col-sm-12 controls text-center">
                            <button type="submit" id="btn-login" href="#" class="btn btn-success btn-lg ">Login  </button>
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
</body>
</html>
