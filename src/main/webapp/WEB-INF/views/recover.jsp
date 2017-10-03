<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Recover Password</title>
    <meta charset="utf-8">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css" rel="stylesheet">
</head>
<body>

<t:navbar activePage="signin"/>

<div class="container">
    <div id="signupbox" style="margin-top:50px" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <div class="panel panel-info">
            <div class="panel-heading">
                <div class="panel-title">Let's recover your password</div>
            </div>
            <div class="panel-body">
                <form id="forgotForm" class="form-horizontal" method="post" action="<c:url value="/root/recoverPassword"/>">

                    <div class="form-group">
                        <label for="login" class="col-md-3 control-label">login</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" id="login" value="${requestScope.login}" readonly="readonly">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="pas1" class="col-md-3 control-label">enter new password</label>
                        <div class="col-md-9">
                            <input type="password" required class="form-control" name="pas1" id="pas1">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="pas2" class="col-md-3 control-label">repeat password</label>
                        <div class="col-md-9">
                            <input type="password" required class="form-control" name="pas2" id="pas2">
                        </div>
                    </div>

                    <div class="form-group">
                        <!-- Button -->
                        <div class="col-md-offset-3 col-md-9">
                            <button id="btn-signup" type="submit" class="btn btn-info btn-lg">
                                Update password
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    function validateForm() {
        var password1 = document.forms["forgotForm"]["pas1"].value;
        var password2 = document.forms["forgotForm"]["pas2"].value;

        return password1 !== password2;
    }
</script>
<!-- jQuery -->
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
