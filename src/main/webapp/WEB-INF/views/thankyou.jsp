<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                <img src="${pageContext.request.contextPath}/resourseces/img/success-blocks.jpg" height="250">
            </div>
            <h3>Dear, ${requestScope.user.getUserName()}!</h3>
            <h4>
                Thank you for your order, we will contact you soon.<br>
                In order to get more information check your email.</h4>
        </div>

    </div>
</div>
</body>
</html>