<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>My profile</title>
    <meta charset="utf-8" >
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
                                            <button class="btn-link"  formaction="/root/takeFromCart"
                                                    name="productId"    value="${cartItem.getProduct().getId()}" >
                                                <span class="glyphicon glyphicon-minus"></span>
                                            </button>
                                                ${cartItem.getNumber()}
                                            <button class="btn-link"  formaction="/root/addToCart"
                                                    name="productId" value="${cartItem.getProduct().getId()}" >
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
                                    > Confirm your order</button>
                                </form></li>
                        </c:if>
                    </ul>
                </li>

            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <div class="row">
        <div class="col-md-10 panel-body">
            <form class="form-horizontal" method="get"
                  action="<c:url value="/root/editProfile"/>">

                <!-- Form Name -->
                <legend>Welcome to your profile</legend>

                <div class="form-group">
                    <div class="col-md-6 text-right">
                    <label>Name (Full name)</label>
                    </div>
                    <div class="col-md-6">
                        <c:if test="${not empty requestScope.userData.getFullName()}">
                            <label>${requestScope.userData.getFullName()}</label>
                        </c:if>
                        <c:if test="${empty requestScope.userData.getFullName()}">
                            <label>edit your profile and enter full name</label>
                        </c:if>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-6 text-right">
                    <label>Date Of Birth</label>
                    </div>
                    <div class="col-md-6">
                        <c:if test="${not empty requestScope.userData.getDateOfBirth()}">
                            <label>${requestScope.userData.getDateOfBirth()}</label>
                        </c:if>
                        <c:if test="${empty requestScope.userData.getDateOfBirth()}">
                            <label>unknown date of birth</label>
                        </c:if>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-6 text-right">
                    <label>Gender:</label>
                    </div>
                    <div class="col-md-6">
                        <c:if test="${not empty requestScope.userData.getGender()}">
                            <label>${requestScope.userData.getGender()}</label>
                        </c:if>
                        <c:if test="${empty requestScope.userData.getGender()}">
                            <label>gender</label>
                        </c:if>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-6 text-right">
                    <label>Marital Status:</label>
                    </div>
                    <div class="col-md-6">
                        <c:if test="${not empty requestScope.userData.getMarried()}">
                            <label>${requestScope.userData.getMarried()}</label>
                        </c:if>
                        <c:if test="${empty requestScope.userData.getMarried()}">
                            <label>are you married?</label>
                        </c:if>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-6 text-right">
                    <label>My district </label>
                    </div>
                    <div class="col-md-6">
                        <c:if test="${not empty requestScope.userData.getDistrict()}">
                            <label>${requestScope.userData.getDistrict()}</label>
                        </c:if>
                        <c:if test="${empty requestScope.userData.getDistrict()}">
                            <label>edit your country district</label>
                        </c:if>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-6 text-right">
                    <label>My area </label>
                    </div>
                    <div class="col-md-6">
                        <c:if test="${not empty requestScope.userData.getArea()}">
                            <label>${requestScope.userData.getArea()}</label>
                        </c:if>
                        <c:if test="${empty requestScope.userData.getArea()}">
                            <label>enter your vilage/town/city</label>
                        </c:if>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-6 text-right">
                    <label>My street </label>
                    </div>
                    <div class="col-md-6">
                        <c:if test="${not empty requestScope.userData.getStreet()}">
                            <label>${requestScope.userData.getStreet()}</label>
                        </c:if>
                        <c:if test="${empty requestScope.userData.getStreet()}">
                            <label>enter your sreet and building</label>
                        </c:if>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-6 text-right">
                    <label>My appartment </label>
                    </div>
                    <div class="col-md-6">
                        <c:if test="${not empty requestScope.userData.getApp()}">
                            <label>${requestScope.userData.getApp()}</label>
                        </c:if>
                        <c:if test="${empty requestScope.userData.getApp()}">
                            <label>enter appartment number</label>
                        </c:if>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-6 text-right">
                    <label>My phone number</label>
                    </div>
                    <div class="col-md-6">
                        <c:if test="${not empty requestScope.userData.getPrimaryNumber()}">
                            <label>${requestScope.userData.getPrimaryNumber()}</label>
                        </c:if>
                        <c:if test="${empty requestScope.userData.getPrimaryNumber()}">
                            <label>enter your phone</label>
                        </c:if>
                    </div>
                </div>

                <c:if test="${not empty requestScope.userData.getSecondaryNumber()}">
                    <div class="form-group">
                        <div class="col-md-6 text-right">
                        <label>My other number</label>
                        </div>
                        <div class="col-md-6">
                            <label>${requestScope.userData.getSecondaryNumber()}</label>
                        </div>
                    </div>
                </c:if>

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
