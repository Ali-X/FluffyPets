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

<t:navbar activePage="create"/>

<div class="container-fluid center-block">
    <div class="row">
        <div class="col-md-4 center-block hidden-sm hidden-xs">
            <div class="thumbnail">
                <img
                        <c:if test="${empty requestScope.uploadedFile}">src="${pageContext.request.contextPath}/resources/img/toy-bear.jpg"</c:if>
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
