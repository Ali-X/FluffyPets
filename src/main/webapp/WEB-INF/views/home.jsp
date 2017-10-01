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

                <li class="active"><a href="<c:url value="/root/home"/> ">${requestScope.Products}</a></li>

                <c:if test="${not empty requestScope.user}">
                    <c:if test="${requestScope.user.getRoleString().equals('admin')}">
                        <li><a href="<c:url value="/root/admin/createProduct"/>">
                            <span class="glyphicon glyphicon-edit"></span>
                                ${requestScope.Create_product}</a></li>
                        <li><a href="<c:url value="/root/admin/users"/>">
                            <span class="glyphicon glyphicon-wrench"></span>
                                ${requestScope.Admin_page}</a></li>
                    </c:if></c:if>

                <c:if test="${empty requestScope.user}">
                    <li><a href="<c:url value="/root/login"/>">
                        <span class="glyphicon glyphicon-log-in"></span>
                            ${requestScope.Signin}</a></li>
                    <li><a href="<c:url value="/root/signup"/>">
                        <span class="glyphicon glyphicon-ok"></span>
                            ${requestScope.Signup}</a></li>
                </c:if>

                <c:if test="${not empty requestScope.user}">
                    <li class="text-warning"><a href="<c:url value="/root/profile"/>">
                        <span class="glyphicon glyphicon-user"></span>
                            ${requestScope.My_profile}</a></li>

                    <li class="text-warning"><a href="<c:url value="/root/logout"/>">
                        <span class="glyphicon glyphicon-log-out"></span>
                            ${requestScope.Logout}</a></li>
                </c:if>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <span class="glyphicon glyphicon-shopping-cart"></span>${requestScope.My_cart}<b
                            class="caret"></b></a>
                    <ul id="cartList" class="dropdown-menu">
                        <c:if test="${empty requestScope.user}">
                            <li style="width: 450px"><a href="/root/login">${requestScope.message_L}</a></li>
                        </c:if>
                        <c:if test="${not empty requestScope.user}">
                            <li style="width: 450px"><a href="#">Welcome ${requestScope.user.getUserName()}!</a></li>
                        </c:if>
                        <c:if test="${not empty requestScope.cart.getProductInCarts()}">
                            <li class="divider"></li>
                            <c:forEach items="${requestScope.cart.getProductInCarts()}" var="cartItem">
                                <li class="text-center" style="width: 450px">
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
                                    > ${requestScope.Confirm_your_order}
                                    </button>
                                </form>
                            </li>
                        </c:if>
                    </ul>
                </li>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <span class="glyphicon glyphicon-globe"></span>${requestScope.Language}<b class="caret"></b></a>
                    <ul id="lang" class="dropdown-menu">
                        <form method="post" name="locale" action="/root/internationalization">
                            <input name="page" value="home" hidden>
                            <li>
                                <button value="en_US" name="locale" class="btn-link">English</button>
                            </li>
                            <li>
                                <button value="uk_UA" name="locale" class="btn-link">Українська</button>
                            </li>
                        </form>
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
        <div class="bg-info col-xs-12 col-sm-3 col-lg-2">
            <form action="/root/selectGoods" method="post">
                <input name="formN" value="1" hidden="hidden">
                <div class="form-group">
                    <h4>${requestScope.Select_categories}</h4>

                    <c:forEach items="${categories}" var="category">
                        <div class="checkbox">
                            <label><input type="checkbox" checked="checked" name="${category.getName()}"
                                          value="${category.getName()}">
                                <c:if test="${isUa ne true}">${category.getName()}</c:if>
                                <c:if test="${isUa eq true}">${category.getNameUa()}</c:if>
                            </label>
                        </div>
                    </c:forEach>

                    <h4>${requestScope.Select_price_range}</h4>

                    <div class="radio">
                        <label><input type="radio" name="selectedPrice" checked="checked"
                                      value="all">${requestScope.All}</label>
                    </div>

                    <c:forEach items="${prices}" var="price">
                        <div class="radio">
                            <label><input type="radio" name="selectedPrice"
                                          value="${price.getLabel()}">
                                <c:if test="${isUa eq true}">${price.getLabelUa()}</c:if>
                                <c:if test="${isUa ne true}">${price.getLabel()}</c:if>
                            </label>
                        </div>
                    </c:forEach>
                    <div>
                        <label for="order">${requestScope.OrderLabel}</label>
                        <select id="order" name="order">
                            <option value="asc" selected="selected">${requestScope.IncreasePrice}</option>
                            <option value="desc">${requestScope.DecreasePrice}</option>
                        </select>
                    </div>
                    <br>
                    <button class="btn btn-info text-center btn-large" type="submit">${requestScope.Select}</button>
                </div>
            </form>
        </div>
        <div class="col-xs-12 col-sm-9 col-lg-10">
            <div class="row">
                <c:forEach items="${homePagePref.getProducts()}" var="product">

                    <div class="col-sm-6 col-md-4 col-lg-3">
                        <div class="thumbnail" style="height: 520px">
                            <img src="${initParam.get('localDir')}${product.getPictureURL()}"
                                 style="height: 320px">
                            <div class="caption">
                                <h4>${product.getName()}</h4>
                            </div>
                            </a>
                            <div class="container">
                                <form class="form-horizontal" method="post">
                                    <h2><span class="glyphicon glyphicon-usd"></span> ${product.getPrice()} </h2>
                                    <button class="btn btn-success btn-md" name="productId" value="${product.getId()}"
                                            formaction="<c:url value="/root/addToCart"/>">
                                            ${requestScope.Add_to_cart}
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>

                </c:forEach>
            </div>
        </div>
    </div>

    <div class="row text-center">
        <form method="post" action="/root/selectGoods">
            <c:forEach var="count" begin="1" end="${homePagePref.getPaginationMax()}">
                <button class="btn-lg btn-info" name="pagination" value="${count}">${count}</button>
                <input name="formN" value="2" hidden="hidden">
            </c:forEach>
        </form>
    </div>
</div>

<!-- jQuery -->
<script src="${pageContext.request.contextPath}/resourseces/js/jquery-3.2.1.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="${pageContext.request.contextPath}/resourseces/js/bootstrap.min.js"></script>
</body>
</html>
