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

                <li><a href="<c:url value="/root/home"/> ">Products</a></li>

                <c:if test="${not empty requestScope.user}">
                    <c:if test="${requestScope.user.getRoleString().equals('admin')}">
                        <li class="active"><a href="<c:url value="/root/createProduct"/>">
                            <span class="glyphicon glyphicon-edit"></span>
                            Create product</a></li>
                        <li><a href="<c:url value="/root/admin"/>">
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

<div class="container-fluid center-block">
    <div class="row">
        <div class="col-md-4 center-block hidden-sm hidden-xs">
            <div class="thumbnail">
                <a href="${pageContext.request.contextPath}/resourseces/img/toy-bear.jpg" target="_blank">
                    <img src="${pageContext.request.contextPath}/resourseces/img/toy-bear.jpg" alt="Nature"
                         style="width:100%">
                </a>
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
                        <div class="col-md-2"><input id="price" type="number" name="price" placeholder="?" required
                                                     class="form-control"></div>
                    </div>

                    <div class="row">
                        <div class="col-md-3">
                            <button class="btn btn-success btn-md" formaction="/root/createProduct">Create good</button>
                        </div>
                        <c:if test="${not empty requestScope.categories}">
                            <div class="col-md-3">
                                <select class="form-control bg-warning" id="sel1" name="categorySelId">
                                    <c:forEach items="${requestScope.categories}" var="category">
                                        <option value="${category.getId()}"><c:out
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
                            <img src="${pageContext.request.contextPath}/resourseces/img/Category.jpg" alt="Nature"
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
                                <label for="comment2">Insert category description</label>
                                <textarea class="form-control" name="categoryDescription" rows="2" required
                                          id="comment2"></textarea>
                                <br>

                                <button class="btn btn-success btn-md" formaction="/root/createCategory">Create
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
<script src="${pageContext.request.contextPath}/resourseces/js/jquery-3.2.1.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="${pageContext.request.contextPath}/resourseces/js/bootstrap.min.js"></script>
</body>
</html>
