<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>My profile</title>
    <meta charset="utf-8" >
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css" rel="stylesheet">
</head>
<body>

<t:navbar activePage="profile"/>

<div class="container">
    <div class="row">
        <div class="col-md-10 panel-body">
            <form class="form-horizontal" method="get"
                  action="<c:url value="/root/editProfile"/>">

                <!-- Form Name -->
                <legend>Welcome to your profile</legend>

                <div class="form-group">
                    <div class="col-md-6 text-right">
                    <label>Name (Full name)</label>
                    </div>
                    <div class="col-md-6">
                        <c:if test="${not empty requestScope.userAdress.getFullName()}">
                            <label>${requestScope.userAdress.getFullName()}</label>
                        </c:if>
                        <c:if test="${empty requestScope.userAdress.getFullName()}">
                            <label>edit your profile and enter full name</label>
                        </c:if>
                    </div>
                </div>


                <div class="form-group">
                    <div class="col-md-6 text-right">
                    <label>My district </label>
                    </div>
                    <div class="col-md-6">
                        <c:if test="${not empty requestScope.userAdress.getDistrict()}">
                            <label>${requestScope.userAdress.getDistrict()}</label>
                        </c:if>
                        <c:if test="${empty requestScope.userAdress.getDistrict()}">
                            <label>edit your country district</label>
                        </c:if>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-6 text-right">
                    <label>My area </label>
                    </div>
                    <div class="col-md-6">
                        <c:if test="${not empty requestScope.userAdress.getArea()}">
                            <label>${requestScope.userAdress.getArea()}</label>
                        </c:if>
                        <c:if test="${empty requestScope.userAdress.getArea()}">
                            <label>enter your vilage/town/city</label>
                        </c:if>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-6 text-right">
                    <label>My street </label>
                    </div>
                    <div class="col-md-6">
                        <c:if test="${not empty requestScope.userAdress.getStreet()}">
                            <label>${requestScope.userAdress.getStreet()}</label>
                        </c:if>
                        <c:if test="${empty requestScope.userAdress.getStreet()}">
                            <label>enter your sreet and building</label>
                        </c:if>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-6 text-right">
                    <label>My appartment </label>
                    </div>
                    <div class="col-md-6">
                        <c:if test="${not empty requestScope.userAdress.getApp()}">
                            <label>${requestScope.userAdress.getApp()}</label>
                        </c:if>
                        <c:if test="${empty requestScope.userAdress.getApp()}">
                            <label>enter appartment number</label>
                        </c:if>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-6 text-right">
                    <label>My phone number</label>
                    </div>
                    <div class="col-md-6">
                        <c:if test="${not empty requestScope.userAdress.getPhone()}">
                            <label>${requestScope.userAdress.getPhone()}</label>
                        </c:if>
                        <c:if test="${empty requestScope.userAdress.getPhone()}">
                            <label>enter your phone</label>
                        </c:if>
                    </div>
                </div>


                <button type="submit" class="btn btn-warning btn-lg center-block"> Edit profile</button>
            </form>
        </div>
    </div>
</div>
<!-- jQuery -->
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
