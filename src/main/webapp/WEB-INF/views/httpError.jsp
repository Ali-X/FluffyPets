<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <title>Sign up</title>
    <meta charset="utf-8">
    <link href="${pageContext.request.contextPath}/resourseces/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resourseces/css/bootstrap-theme.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row text-center">
        <div class="col-sm-6 col-sm-offset-3">
            <div>
                <img src="${pageContext.request.contextPath}/resourseces/img/sadDog.jpg" height="250">
            </div>
            <h1>Something goes wrong</h1>
            <h2 class="text-warning"> Status code: ${pageContext.errorData.statusCode}</h2>

            <h4 class="text-warning"> Request that failed: ${pageContext.errorData.requestURI} <br>
                <c:if test="${not empty pageContext.errorData.throwable}">
                Exception: ${pageContext.errorData.throwable}</c:if>


        </div>

    </div>
</div>
</body>
</html>