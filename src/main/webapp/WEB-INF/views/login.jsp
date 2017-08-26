<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Ali-X
  Date: 24.07.2017
  Time: 20:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <form method="post" action="<c:url value="/root/login"/>">
        <input title="UserName" type="text" name="userName">
        <br>
        <input title="Password" type="password" name="password">
        <br>
        <button type="submit">Log in</button>
    </form>
    <br>
    <a href="<c:url value="/root/home"/> ">Home</a>
</body>
</html>
