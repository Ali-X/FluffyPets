<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="activePage" required="true"%>
<%@tag pageEncoding="UTF-8"%>
<html>
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

                <li <c:if test="${activePage.equals('products')}"> class="active" </c:if> ><a href="/root/home"><c:out value="${requestScope.Products}"/></a></li>

                <c:if test="${not empty requestScope.user}">
                    <c:if test="${requestScope.user.getRoleString().equals('admin')}">
                        <li <c:if test="${activePage.equals('create')}"> class="active" </c:if>><a href="/root/admin/createProduct">
                            <span class="glyphicon glyphicon-edit"></span>
                            <c:out value="${requestScope.Create_product}"/></a></li>
                        <li <c:if test="${activePage.equals('admin')}"> class="active" </c:if>><a href="/root/admin/users">
                            <span class="glyphicon glyphicon-wrench"></span>
                            <c:out value="${requestScope.Admin_page}"/></a></li>
                    </c:if></c:if>

                <c:if test="${empty requestScope.user}">
                    <li <c:if test="${activePage.equals('signin')}"> class="active" </c:if>><a href="/root/login">
                        <span class="glyphicon glyphicon-log-in"></span>
                        <c:out value="${requestScope.Signin}"/></a></li>
                    <li <c:if test="${activePage.equals('signup')}"> class="active" </c:if>><a href="/root/signup">
                        <span class="glyphicon glyphicon-ok"></span>
                        <c:out value="${requestScope.Signup}"/></a></li>
                </c:if>

                <c:if test="${not empty requestScope.user}">
                    <li <c:if test="${activePage.equals('profile')}"> class="active" </c:if>><a href="/root/profile">
                        <span class="glyphicon glyphicon-user"></span>
                        <c:out value="${requestScope.My_profile}"/></a></li>

                    <li <c:if test="${activePage.equals('logout')}"> class="active" </c:if>><a href="/root/logout">
                        <span class="glyphicon glyphicon-log-out"></span>
                        <c:out value="${requestScope.Logout}"/></a></li>
                </c:if>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <span class="glyphicon glyphicon-shopping-cart"></span><c:out value="${requestScope.My_cart}"/><b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <c:if test="${empty requestScope.user}">
                            <li style="width: 300px"><a href="/root/login"><c:out value="${requestScope.message_L}"/></a></li>
                        </c:if>
                        <c:if test="${not empty requestScope.user}">
                            <li style="width: 300px"><a href="#"><c:out value="${requestScope.Welcome}"/> ${requestScope.user.getUserName()}!</a></li>
                        </c:if>
                        <c:if test="${not empty requestScope.cart.getProductInCarts()}">
                            <li class="divider"></li>
                            <c:forEach items="${requestScope.cart.getProductInCarts()}" var="cartItem">
                                <li class="text-center" style="width: 300px">
                                    <form method="post">
                                        <div class="row center-block">
                                                ${cartItem.getProduct().getName()} :
                                            <button class="btn-link" formaction="/root/takeFromCart"
                                                    name="productId" value="${cartItem.getProduct().getId()}">
                                                <span class="glyphicon glyphicon-minus"></span>
                                            </button>
                                                <input class="input-sm" value="${cartItem.getNumber()}" name="number" style="width: 75px"/>
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
                                    > <c:out value="${requestScope.Confirm_your_order}"/>
                                    </button>
                                </form>
                            </li>
                        </c:if>
                    </ul>
                </li>


                <c:if test="${activePage.equals('products')}">
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
                </c:if>
            </ul>
        </div>
    </div>
</nav>