<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Sign up</title>
    <meta charset="utf-8">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css" rel="stylesheet">
</head>
<body>

<t:navbar activePage="signup"/>

<div class="container">
    <div id="signupbox" style="margin-top:50px" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <div class="panel panel-info">
            <div class="panel-heading">
                <div class="panel-title">Sign up on Fluffy.pets.com</div>
            </div>
            <div class="panel-body">
                <form id="signupform" class="form-horizontal" method="post" action="/root/signup">

                    <div class="form-group">
                        <label for="form-email" class="col-md-3 control-label">Email</label>
                        <div class="col-md-9">
                            <input type="email" required
                            <c:if test="${not empty requestScope.validationSignUp.getValidationObject().getEmail()}">
                                   value="${requestScope.validationSignUp.getValidationObject().getEmail()}"</c:if>
                            <t:formInputAlert validator="${requestScope.validationSignUp.getFieldStatuses().get(0)}"/>
                                   id="form-email" name="email" placeholder="Email Address">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="form-userName" class="col-md-3 control-label">Login</label>
                        <div class="col-md-9">
                            <input type="text" required
                            <c:if test="${not empty requestScope.validationSignUp.getValidationObject().getUsername()}">
                                   value="${requestScope.validationSignUp.getValidationObject().getUsername()}" </c:if>
                            <t:formInputAlert validator="${requestScope.validationSignUp.getFieldStatuses().get(1)}"/>
                                   required id="form-userName" name="username" placeholder="Enter login">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="form-password" class="col-md-3 control-label">Password</label>
                        <div class="col-md-9">
                            <input type="password"
                            <t:formInputAlert validator="${requestScope.validationSignUp.getFieldStatuses().get(2)}"/>
                                   required id="form-password" name="password" placeholder="Password">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="form-password2" class="col-md-3 control-label">Password</label>
                        <div class="col-md-9">
                            <input type="password"
                            <t:formInputAlert validator="${requestScope.validationSignUp.getFieldStatuses().get(3)}"/>
                                   required id="form-password2" name="password2" placeholder="repeat it">
                        </div>
                    </div>

                    <c:if test="${not empty requestScope.validationSignUp}"><label
                            class="text-danger">${requestScope.validationSignUp.getValidationMessage()}</label></c:if>

                    <div class="form-group">
                        <!-- Button -->
                        <div class="col-md-offset-3 col-md-9">
                            <button type="submit" class="btn btn-success btn-lg">
                                Sign Up
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

    </div>
</div>
<!-- jQuery -->
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>
