<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Secure page</title>
</head>
<body>
<h1>Admin Secure page</h1>
    Hello ${requestScope.user.userName}!
<br>
<a href="<c:url value="/root/home"/> ">Home</a>
</body>
</html>
