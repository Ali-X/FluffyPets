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
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Навигация</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<c:url value="/root/home"/> ">FluffyPets.com</a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li class="active"><a href="<c:url value="/root/home"/> ">Products</a></li>
                <li><a href="<c:url value="/root/login"/>">Signin</a></li>
                <li><a href="<c:url value="/root/signup"/> ">Signup</a></li>
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
<div class="container-fluid">
    <div class="row">
        <div class="col-md-3 col-sm-4 visible-md visible-sm visible-lg"><img src="<c:url value="/resourseces/img/pooh.jpg"/>" height="250"></div>
        <div class="col-md-6 col-sm-5 col-xs-12 text-center"><img src="<c:url value="/resourseces/img/toys.jpg"/>" height="250"></div>
        <div class="col-md-3 col-sm-3 visible-md visible-sm visible-lg"><img src="<c:url value="/resourseces/img/redman.jpg"/>" height="250"></div>
    </div>
</div>

<br>

<div class="container-fluid">
    <div class="row">
        <div class="bg-info col-xs-6 col-sm-3 col-lg-2">
            <h4>Select the category </h4>
            <form>
                <div class="form-group">
                    <div class="checkbox">
                        <label><input type="checkbox" value="bears" checked="true">Teddy Bears</label>
                    </div>
                    <div class="checkbox">
                        <label><input type="checkbox" value="catsAndDogs" checked="true">Cats and Dogs</label>
                    </div>
                    <div class="checkbox">
                        <label><input type="checkbox" value="farm" checked="true">Farm Animalas</label>
                    </div>
                    <div class="checkbox">
                        <label><input type="checkbox" value="puppets" checked="true">Puppets</label>
                    </div>
                    <div class="checkbox">
                        <label><input type="checkbox" value="character" checked="true">Favorite Characters</label>
                    </div>
                    <div class="checkbox">
                        <label><input type="checkbox" value="big" checked="true">Jumbo-Sized</label>
                    </div>
                    <h4>Select price range</h4>
                    <div class="radio">
                        <label><input type="radio" name="price" checked="true">less 25$</label>
                    </div>
                    <div class="radio">
                        <label><input type="radio" name="price">25$-50$</label>
                    </div>
                    <div class="radio">
                        <label><input type="radio" name="price" >50$-100$</label>
                    </div>
                    <div class="radio">
                        <label><input type="radio" name="price" >100$-300$</label>
                    </div>
                    <div class="radio">
                        <label><input type="radio" name="price" >more than 300$</label>
                    </div>
                    <button type="submit" class="btn btn-info text-center btn-large">Select</button>
                </div></form></div>
        <div class="col-xs-6 col-sm-9 col-lg-10">
            <h4>the product view</h4>
        </div>
    </div>
</div>

<!-- jQuery -->
<script src="${pageContext.request.contextPath}/resourseces/js/jquery-3.2.1.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="${pageContext.request.contextPath}/resourseces/js/bootstrap.min.js"></script>
</body>
</html>
