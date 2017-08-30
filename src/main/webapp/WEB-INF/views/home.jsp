<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
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

                <li class="active"><a href="<c:url value="/root/home"/> ">Products</a></li>

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

                    <li class="text-warning"><a href="<c:url value="/root/logout"/>">    <%--todo: make logout--%>
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
<div class="container-fluid">
    <div class="row">
        <div class="col-md-3 col-sm-4 visible-md visible-sm visible-lg"><img
                src="<c:url value="/resourseces/img/pooh.jpg"/>" height="250"></div>
        <div class="col-md-6 col-sm-5 col-xs-12 text-center"><img src="<c:url value="/resourseces/img/toys.jpg"/>"
                                                                  height="250"></div>
        <div class="col-md-3 col-sm-3 visible-md visible-sm visible-lg"><img
                src="<c:url value="/resourseces/img/redman.jpg"/>" height="250"></div>
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
                        <label><input type="checkbox" value="bears">Teddy Bears</label>
                    </div>
                    <div class="checkbox">
                        <label><input type="checkbox" value="catsAndDogs">Cats and Dogs</label>
                    </div>
                    <div class="checkbox">
                        <label><input type="checkbox" value="farm">Farm Animalas</label>
                    </div>
                    <div class="checkbox">
                        <label><input type="checkbox" value="puppets">Puppets</label>
                    </div>
                    <div class="checkbox">
                        <label><input type="checkbox" value="character">Favorite Characters</label>
                    </div>
                    <div class="checkbox">
                        <label><input type="checkbox" value="big">Jumbo-Sized</label>
                    </div>
                    <h4>Select price range</h4>
                    <div class="radio">
                        <label><input type="radio" name="price">less 25$</label>
                    </div>
                    <div class="radio">
                        <label><input type="radio" name="price">25$-50$</label>
                    </div>
                    <div class="radio">
                        <label><input type="radio" name="price">50$-100$</label>
                    </div>
                    <div class="radio">
                        <label><input type="radio" name="price">100$-300$</label>
                    </div>
                    <div class="radio">
                        <label><input type="radio" name="price">more than 300$</label>
                    </div>
                    <button class="btn btn-info text-center btn-large">Select</button>
                </div>
            </form>
        </div>
        <div class="col-xs-6 col-sm-9 col-lg-10">
            <div class="row">

                <div class="col-sm-6 col-md-4">
                    <div class="thumbnail">
                        <a href="<c:url value="/resourseces/img/goods/CatsDogs/id1.jpg"/>" target="_blank">
                            <img src="<c:url value="/resourseces/img/goods/CatsDogs/id1.jpg"/>" alt="Nature" style="width:100%">
                            <div class="caption">
                                <p>Lorem ipsum donec id elit non mi porta gravida at eget metus.</p>
                            </div>
                        </a>
                        <div class="container">
                            <form class="form-horizontal" method="post">
                                <input type="hidden" name="goods_id" value="1234567">
                                <button class="btn btn-success btn-md" formaction="<c:url value="/root/addToCart"/>">Add to
                                    cart
                                </button>
                                <button class="btn btn-info btn-md" formaction="<c:url value="/root/productInfo"/>">More
                                    details
                                </button>
                            </form>
                        </div>
                    </div>
                </div>

                <div class="col-sm-6 col-md-4">
                    <div class="thumbnail">
                        <a href="<c:url value="/resourseces/img/goods/CatsDogs/id2.jpg"/>" target="_blank">
                            <img src="<c:url value="/resourseces/img/goods/CatsDogs/id2.jpg"/>" alt="Nature" style="width:100%">
                            <div class="caption">
                                <p>Lorem ipsum donec id elit non mi porta gravida at eget metus.</p>
                            </div>
                        </a>
                        <div class="container">
                            <form class="form-horizontal" method="post">
                                <input type="hidden" name="goods_id" value="12534567">
                                <button class="btn btn-success btn-md" formaction="<c:url value="/root/addToCart"/>">Add to
                                    cart
                                </button>
                                <button class="btn btn-info btn-md" formaction="<c:url value="/root/productInfo"/>">More
                                    details
                                </button>
                            </form>
                        </div>
                    </div>
                </div>

                <div class="col-sm-6 col-md-4">
                    <div class="thumbnail">
                        <a href="<c:url value="/resourseces/img/goods/CatsDogs/id4.jpg"/>" target="_blank">
                            <img src="<c:url value="/resourseces/img/goods/CatsDogs/id4.jpg"/>" alt="Nature" style="width:100%">
                            <div class="caption">
                                <p>Lorem ipsum donec id elit non mi porta gravida at eget metus.</p>
                            </div>
                        </a>
                        <div class="container">
                            <form class="form-horizontal" method="post">
                                <input type="hidden" name="goods_id" value="1267">
                                <button class="btn btn-success btn-md" formaction="<c:url value="/root/addToCart"/>">Add to
                                    cart
                                </button>
                                <button class="btn btn-info btn-md" formaction="<c:url value="/root/productInfo"/>">More
                                    details
                                </button>
                            </form>
                        </div>
                    </div>
                </div>

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
