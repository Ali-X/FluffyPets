<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title> welcome  on SubService</title>
    <link rel="stylesheet" href="<c:url value="/css/style.css"/>" type="text/css">
</head>
<body>

<div class ="container">
    <form action="<c:url value="/index.jsp"/>" method="POST">
        <img src="<c:url value="/img/Co-Author.png"/>">
        <h6>Sign in</h6>
        <div class="dvsinput">
        login:    <input type="text" name="userName" placeholder="user name">
            <br />
        password:<input type="password" name="password" placeholder="password">
        </div>
        <div>
            <br />
            <div class="centred">
        <input class="dws-submit" type="submit" name="submitUser" >
            </div>

        <a href="<c:url value="/forgot.jsp"/>" class="alignleft"> Forgot password ?</a>
        <a href="<c:url value="/sign_up.jsp"/>" class="alignright"> join us!</a>
            <br />
        </div>
    </form>
</div>

</body>
</html>