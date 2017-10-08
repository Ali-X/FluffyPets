<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Edit profile</title>
    <meta charset="utf-8">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css" rel="stylesheet">
</head>
<body>

<t:navbar activePage="profile"/>

<div class="container">
    <div class="row">
        <div class="col-md-10 ">
            <form id="formEditUser" method="post"
                  action="/root/editProfile" class="form-horizontal">

                <!-- Form Name -->
                <legend>Fill your profile</legend>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="Fullname">Name (Full name)</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <div class="input-group-addon">
                                <i class="glyphicon glyphicon-user"> </i>
                            </div>
                            <input id="Fullname" name="Fullname" type="text" placeholder="Name (Full name)" required
                            <t:formInputAlert validator="${requestScope.addressVal.getFieldStatuses().get(0)}"/>
                            <c:if test="${not empty requestScope.userAddress.getFullName()}">
                                   value="${requestScope.userAddress.getFullName()}"</c:if>
                            <c:if test="${not empty requestScope.addressVal.getValidationObject().getFullName()}">
                                value="${requestScope.addressVal.getValidationObject().getFullName()}"</c:if>>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label col-xs-12" for="District">Address</label>

                    <div class="col-md-2  col-xs-4">
                        <input id="District" type="text" name="District"
                               required placeholder="District"
                        <t:formInputAlert validator="${requestScope.addressVal.getFieldStatuses().get(1)}"
                                          basic="input-md"/>
                        <c:if test="${not empty requestScope.userAddress.getDistrict()}">
                               value="${requestScope.userAddress.getDistrict()}"</c:if>
                        <c:if test="${not empty requestScope.addressVal.getValidationObject().getDistrict()}">
                               value="${requestScope.addressVal.getValidationObject().getDistrict()}"</c:if>>
                    </div>

                    <div class="col-md-2 col-xs-4">
                        <input id="Area" type="text" name="Area"
                               placeholder="Area"
                        <t:formInputAlert validator="${requestScope.addressVal.getFieldStatuses().get(2)}"
                                          basic="input-md"/>
                        <c:if test="${not empty requestScope.userAddress.getArea()}">
                               value="${requestScope.userAddress.getArea()}"</c:if>
                        <c:if test="${not empty requestScope.addressVal.getValidationObject().getArea()}">
                               value="${requestScope.addressVal.getValidationObject().getArea()}"</c:if>>
                    </div>

                </div>
                <div class="form-group">
                    <label class="col-md-4 invisible col-xs-12" for="Street">Address</label>
                    <div class="col-md-2  col-xs-4">
                        <input id="Street" type="text" name="Street"
                               required placeholder="Street"
                        <t:formInputAlert validator="${requestScope.addressVal.getFieldStatuses().get(3)}"
                                          basic="input-md"/>
                        <c:if test="${not empty requestScope.userAddress.getStreet()}">
                               value="${requestScope.userAddress.getStreet()}"</c:if>
                        <c:if test="${not empty requestScope.addressVal.getValidationObject().getStreet()}">
                               value="${requestScope.addressVal.getValidationObject().getStreet()}"</c:if>>
                    </div>
                    <div class="col-md-2  col-xs-4">
                        <input id="App" type="text" name="App"
                               required placeholder="App. №"
                        <t:formInputAlert validator="${requestScope.addressVal.getFieldStatuses().get(4)}" basic="input-md"/>
                        <c:if test="${not empty requestScope.userAddress.getApp()}">
                               value="${requestScope.userAddress.getApp()}"</c:if>
                        <c:if test="${not empty requestScope.addressVal.getValidationObject().getApp()}">
                               value="${requestScope.addressVal.getValidationObject().getApp()}"</c:if>>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="Phone number ">Phone number </label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <div class="input-group-addon">
                                <i class="glyphicon glyphicon-earphone"></i>

                            </div>
                            <input id="Phone number " name="Phone number" type="text"
                                   required placeholder="Primary Phone number "
                            <t:formInputAlert validator="${requestScope.addressVal.getFieldStatuses().get(5)}"
                                              basic="input-md"/>
                            <c:if test="${not empty requestScope.userAddress.getPhone()}">
                                   value="${requestScope.userAddress.getPhone()}"</c:if>
                            <c:if test="${not empty requestScope.addressVal.getValidationObject().getPhone()}">
                                   value="${requestScope.addressVal.getValidationObject().getPhone()}"</c:if>>
                        </div>

                    </div>
                </div>
                <div class="form-group">
                    <c:if test="${not empty requestScope.addressVal}"><label
                            class="text-danger">${requestScope.addressVal.getValidationMessage()}</label></c:if>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label"></label>
                    <div class="col-md-4">
                        <button type="submit" class="btn btn-success"><span
                                class="glyphicon glyphicon-thumbs-up"></span> Submit
                        </button>
                        <button type="reset" class="btn btn-warning"><span
                                class="glyphicon glyphicon-remove-sign"></span> Clear
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- jQuery -->
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script>
    function validateForm() {
        var name = document.forms["formEditUser"]["Fullname"].value;

        if (name.length < 5) {
            alert("Your login should be longer");
            return false;
        }
    }
</script>
</body>
</html>
