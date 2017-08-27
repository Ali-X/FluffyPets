<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit profile</title>
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
                <li><a href="<c:url value="/root/login"/>"><span class="glyphicon glyphicon-log-in"></span>Signout</a></li>
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
            <form class="form-horizontal">

                <!-- Form Name -->
                <legend>Fill your profile</legend>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="Fullname">Name (Full name)</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <div class="input-group-addon">
                                <i class="glyphicon glyphicon-user"> </i>
                            </div>
                            <input id="Fullname" name="Fullname" type="text" placeholder="Name (Full name)"
                                   class="form-control input-md">
                        </div>
                    </div>
                </div>


                <div class="form-group">
                    <label class="col-md-4 control-label" for="DateOfBirth">Date Of Birth</label>
                    <div class="col-md-4">

                        <div class="input-group">
                            <div class="input-group-addon">
                                <i class="glyphicon glyphicon-calendar"></i>
                            </div>
                            <input id="DateOfBirth" name="DateOfBirth" type="date" placeholder="Date Of Birth" class="form-control input-md">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label">Gender</label>
                    <div class="col-md-4">
                        <label class="radio-inline" for="Gender-0">
                            <input type="radio" name="Gender" id="Gender-0" value="Male" checked="checked">
                            Male
                        </label>
                        <label class="radio-inline" for="Gender-1">
                            <input type="radio" name="Gender" id="Gender-1" value="Female">
                            Female
                        </label>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label">Marital Status:</label>
                    <div class="col-md-4">
                        <label class="radio-inline" for="radios-0">
                            <input type="radio" name="radios" id="radios-0" value="Married" checked="checked">
                            Married
                        </label>
                        <label class="radio-inline" for="radios-1">
                            <input type="radio" name="radios" id="radios-1" value="Unmarried">
                            Unmarried
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label col-xs-12" for="Address">Address</label>
                    <div class="col-md-2  col-xs-4">
                        <input name="District" id="Address" type="text" placeholder="District" class="form-control input-md ">
                    </div>
                    <div class="col-md-2 col-xs-4">
                        <input name="Area" type="text" placeholder="Area" class="form-control input-md ">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 invisible col-xs-12" for="Address">Address</label>
                    <div class="col-md-2  col-xs-4">
                        <input name="Street" type="text" placeholder="Street" class="form-control input-md ">
                    </div>
                    <div class="col-md-2  col-xs-4">
                        <input name="App" type="text" placeholder="App. №" class="form-control input-md ">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="Phone number ">Phone number </label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <div class="input-group-addon">
                                <i class="glyphicon glyphicon-earphone"></i>

                            </div>
                            <input id="Phone number " name="Phone number " type="text" placeholder="Primary Phone number " class="form-control input-md">

                        </div>
                        <div class="input-group othertop">
                            <div class="input-group-addon">
                                <i class="glyphicon glyphicon-earphone"></i>

                            </div>
                            <input id="PhoneSecNumber" name="PhoneSecNumber" type="text" placeholder=" Secondary Phone number " class="form-control input-md">

                        </div>

                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label" ></label>
                    <div class="col-md-4">
                        <button type="submit" class="btn btn-success"><span class="glyphicon glyphicon-thumbs-up"></span> Submit</button>
                        <button type="reset" class="btn btn-danger" value=""><span class="glyphicon glyphicon-remove-sign"></span> Clear</button>
                    </div>
                </div>
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
