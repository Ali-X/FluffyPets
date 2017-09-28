<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
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
                            Create profile</a></li>
                        <li><a href="<c:url value="/root/admin/users"/>">
                            <span class="glyphicon glyphicon-wrench"></span>
                            Admin page</a></li>
                    </c:if></c:if>

                <c:if test="${empty requestScope.user}">
                    <li class="active"><a href="<c:url value="/root/login"/>">
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

                    <li class="text-warning"><a href="<c:url value="/root/logout"/>">
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

<div class="container">
    <div class="row">
        <div class="col-md-10 ">
            <form onsubmit="return validateForm()" method="post"
                  action="/root/submitOder" class="form-horizontal">

                <!-- Form Name -->
                <legend>Update your address</legend>
                <br>
                <div class="form-group">
                    <label class="col-md-4 control-label" for="Fullname">Name (Full name)</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <div class="input-group-addon">
                                <i class="glyphicon glyphicon-user"> </i>
                            </div>
                            <input id="Fullname" name="Fullname" type="text" placeholder="Name (Full name)"
                                   required class="form-control input-md"
                            <c:if test="${not empty requestScope.userData.getFullName()}">
                                   value="${requestScope.userData.getFullName()}"</c:if>>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label col-xs-12" for="District">Address</label>
                    <div class="col-md-2  col-xs-4">
                        <input id="District" type="text" name="District"
                               required placeholder="District" class="form-control input-md "
                        <c:if test="${not empty requestScope.userData.getDistrict()}">
                               value="${requestScope.userData.getDistrict()}"</c:if>>
                    </div>
                    <div class="col-md-2 col-xs-4">
                        <input id="Area" type="text" name="Area"
                               placeholder="Area" class="form-control input-md "
                        <c:if test="${not empty requestScope.userData.getArea()}">
                               value="${requestScope.userData.getArea()}"</c:if>>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 invisible col-xs-12" for="Street">Address</label>
                    <div class="col-md-2  col-xs-4">
                        <input id="Street" type="text" name="Street"
                               required placeholder="Street" class="form-control input-md "
                        <c:if test="${not empty requestScope.userData.getStreet()}">
                               value="${requestScope.userData.getStreet()}"</c:if>>
                    </div>
                    <div class="col-md-2  col-xs-4">
                        <input id="App" type="text" name="App"
                               required placeholder="App. №" class="form-control input-md "
                        <c:if test="${not empty requestScope.userData.getApp()}">
                               value="${requestScope.userData.getApp()}"</c:if>>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="Phone number ">Phone number </label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <div class="input-group-addon">
                                <i class="glyphicon glyphicon-earphone"></i>

                            </div>
                            <input id="Phone number " name="PhoneNumber" type="text"
                                   required placeholder="Primary Phone number " class="form-control input-md"
                            <c:if test="${not empty requestScope.userData.getPrimaryNumber()}">
                                   value="${requestScope.userData.getPrimaryNumber()}"</c:if>>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="orderComment">When should we deliver?</label>
                    <div class="col-md-4">
                        <textarea id="orderComment" required  name="orderComment"></textarea>
                    </div>
                </div>

                <br>
                <legend>Your order contains products:</legend>
                <br>
                <c:if test="${not empty requestScope.cart}">
                <div class="table-responsive">
                    <table class="table">
                        <tr>
                            <th>Product</th>
                            <th>Quantity</th>
                            <th>Price</th>
                        </tr>
                        <c:forEach items="${requestScope.cart.getProductInCarts()}" var="orderItem">
                            <tr>
                                <td>
                                    <input value="${orderItem.getProduct().getName()}" readonly>
                                </td>
                                <td>
                                    <input value="${orderItem.getNumber()}" readonly>
                                </td>
                                <td>
                                    <input value="${orderItem.getProduct().getPrice()}" readonly>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    </div>
                    <br>
                    <legend> Your order cost ${requestScope.cart.getTotalPrice()}$</legend>
                </c:if>


                <div class="form-group">
                    <label class="col-md-4 control-label"></label>
                    <div class="col-md-4">
                        <button type="submit" class="btn btn-success"><span
                                class="glyphicon glyphicon-thumbs-up"></span> Buy
                        </button>
                        <button type="reset" class="btn btn-warning"><span
                                class="glyphicon glyphicon-remove-sign"></span> Cancel
                        </button>
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
<script>
    function validateForm() {
        var name = document.forms["formEditUser"]["Fullname"].value;

        if (name.length < 5) {
            alert("Your login should be longer");
            return false;
        }
    }
</script>
</body>
</html>
