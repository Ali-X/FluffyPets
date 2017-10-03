<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Fluffy Pets</title>
    <meta charset="utf-8">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css" rel="stylesheet">
</head>
<body>
<meta name="viewport" content="width=device-width, initial-scale=1">

<t:navbar activePage="products"/>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-3 col-sm-4 visible-md visible-sm visible-lg"><img
                src="<c:url value="/resources/img/pooh.jpg"/>" height="250"></div>
        <div class="col-md-6 col-sm-5 col-xs-12 text-center"><img src="<c:url value="/resources/img/toys.jpg"/>"
                                                                  height="250"></div>
        <div class="col-md-3 col-sm-3 visible-md visible-sm visible-lg"><img
                src="<c:url value="/resources/img/redman.jpg"/>" height="250"></div>
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
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
