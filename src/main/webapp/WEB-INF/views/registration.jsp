<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign up</title>
</head>
<body>
<form method="post" action="<c:url value="/root/registration"/>">
    Login:<br>
    <input title="UserName" type="text" name="userName">
    <br><br>
    Password:<br>
    <input title="Password" type="password" name="password">
    <br><br>
    Email:<br>
    <input title="Email" type="email" name="email">
    <br><br>
    <input type="submit" value="Submit">
</form>
<br>
<a href="<c:url value="/root/home"/> ">Home</a>
</body>
</html>
