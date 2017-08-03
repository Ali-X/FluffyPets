<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

    <form method="post" action="<c:url value= "/login"/>">

        <input title="Username" type="text" name="userName">

        <input title="Password" type="password" name="password">

        <button type="submit">LogIn</button>
    </form>

</body>
</html>