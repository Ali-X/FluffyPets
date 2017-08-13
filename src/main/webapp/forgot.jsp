<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <meta charset="UTF-8">
    <title> welcome  on SubService</title>
    <link rel="stylesheet" href="<c:url value="/css/style.css"/>" type="text/css">
</head>
<body>

<div class ="container">
    <form action="<c:url value="/forgot.jsp"/>" method="POST">
        <img src="<c:url value="/img/forgot_pass.png"/>">
        <h6>Well, we will send your password</h6>
        <div class="dvsinput">
            please enter your email:    <input type="email" name="email" placeholder="Email">
        </div>
        <div>
            <br />
            <div class="centred">
                <input class="dws-submit" type="submit" name="submitUser" >
            </div>

            <a href="<c:url value="/index.jsp"/>" class="alignleft"> just sign in</a>
            <a href="<c:url value="/sign_up.jsp"/>" class="alignright"> join us!</a>
            <br />
        </div>
    </form>
</div>

</body>
</html>