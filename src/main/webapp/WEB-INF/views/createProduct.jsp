<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                        <li class="active"><a href="<c:url value="/root/admin/createProduct"/>">
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
                                    > Confirm your order
                                    </button>
                                </form>
                            </li>
                        </c:if>
                    </ul>
                </li>

            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid center-block">
    <div class="row">
        <div class="col-md-4 center-block hidden-sm hidden-xs">
            <div class="thumbnail">
                <img
                        <c:if test="${empty requestScope.uploadedFile}">src="${pageContext.request.contextPath}/resourseces/img/toy-bear.jpg"</c:if>
                        <c:if test="${not empty requestScope.uploadedFile}">src="${requestScope.uploadedFile}"</c:if>
                        style="width:100%">
            </div>
        </div>
        <div class="col-xs-12 col-md-8 ">
            <div class="container center-block col-md-9">
                <div class="caption">
                    <h3>Create new product description</h3>
                </div>
                <form class="form-horizontal" method="post">
                    <label for="productName">Product name</label><br>

                    <input id="productName" type="text" name="productName"
                           placeholder="productName" class="form-control" required>

                    <label for="producer">Producer</label><br>

                    <input id="producer" type="text" name="producer"
                           placeholder="producer" class="form-control" required>

                    <label for="comment">Insert product description</label>
                    <textarea class="form-control" name="description" rows="3" id="comment" required></textarea>

                    <label for="pictureURL">Picture URL (auto set value for uploaded)</label><br>

                    <input id="pictureURL" type="text" name="pictureURL" class="form-control" readonly required
                           placeholder="upload photo using button below"
                    <c:if test="${not empty requestScope.uploadedFile}"> value="${requestScope.uploadedFile}"</c:if> >

                    <label for="price">Picture URL (auto set value for uploaded)</label><br>
                    <div class="form-group">
                        <div class="col-md-1"><span class="glyphicon glyphicon-usd"></span></div>
                        <div class="col-md-2"><input id="price" type="number" step="0.01" min="0" name="price"
                                                     placeholder="?" required
                                                     class="form-control"></div>
                    </div>

                    <div class="row">
                        <div class="col-md-3">
                            <button id="submitProduct" class="btn btn-success btn-md"
                                    formaction="/root/admin/createProduct">Create good
                            </button>
                        </div>
                        <c:if test="${not empty requestScope.categories}">
                            <div class="col-md-3">
                                <c:if test="${empty requestScope.categories}"><h1>Create Category First</h1></c:if>
                                <label for="sel1"> Select the category</label>
                                <select class="form-control bg-warning" id="sel1" name="categorySelId"
                                        style="width: 250px">
                                    <c:forEach items="${requestScope.categories}" var="category">
                                        <option style="width: 300px" value="${category.getId()}"><c:out
                                                value="${category.getName()}"/></option>
                                    </c:forEach>
                                </select>
                            </div>
                        </c:if>
                    </div>
                </form>
                <br>
                <div class="row">
                    <form method="post" action="upload" enctype="multipart/form-data" class="form-group">
                        <div class="row">
                            <div class="col-md-4"><input type="file" name="file" class="label-info"/></div>
                            <div class="col-md-4"></div>
                            <div class="col-md-4"><input type="submit" value="upload" class="btn-info"/></div>
                        </div>
                    </form>
                </div>
                <br>
                <div class="row">

                    <div class="caption">
                        <h3>Create product category</h3>
                    </div>

                    <div class="col-xs-4 col-md-3 col-lg-2 center-block">
                        <div class="thumbnail">
                            <img src="${pageContext.request.contextPath}/resources/img/Category.jpg" alt="Nature"
                                 style="max-height: 120px">
                        </div>
                    </div>

                    <div class="col-xs-8 col-md-9 col-lg-10">
                        <div class="form-group col-md-9">
                            <form class="form-horizontal" method="post">
                                <label>Category name</label><br>

                                <input id="categoryName2" type="text" name="categoryName" required
                                       placeholder="categoryName" class="form-control">

                                <br>
                                <label for="comment2">Введіть назву Україньською</label>
                                <input id="comment2" type="text" name="nameUa" required
                                       placeholder="categoryName" class="form-control">
                                <br>

                                <button class="btn btn-success btn-md" formaction="/root/admin/createCategory">Create
                                    category
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
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
