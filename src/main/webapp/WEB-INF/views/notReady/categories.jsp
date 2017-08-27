<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Categories</title>
</head>
<body>
Hello from Categories
<c:forEach var="e" items="${categories}">
    <h1><c:out value="${e.name}"/></h1>
</c:forEach>
<br>
<a href="<c:url value="/root/home"/> ">Home</a>
</body>
</html>