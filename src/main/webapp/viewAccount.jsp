<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome to your profile</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/bpc_forms.css"/>">
</head>
<body>
    <div id="accountData">
        <div id="left"><img src="<c:url value="/img/unnamed.png"/>" id="accountPhoto" ></div>
        <div id="rigtht">
    <ul>
        <li>Greetings ${login}, lets check your profile.</li>
        <li>${title}  ${firstName} ${lastName}</li>
        <li>${phoneType} phone: ${phone}</li>
        <li>your email: ${email}</li>
        <li>Your ORCID: ${orcid}</li>
    </ul>
    <a href="">edit your personal data</a>
        </div>
    </div>
    <div id="institutionData">
    <ul>
        <li> Institution name and position</li>
        <li>${position} in the ${department} of ${institution}</li>
        <li> ${adress} of ${city}, ${country}</li>
    </ul>
    <a href="">edit institution related data</a>
    </div>
</body>
</html>