<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CreateProduct</title>
</head>
<body>
<h3>Add new product</h3>
<form method="post" action="<c:url value="/root/createProduct"/>">
    Product Name:<br>
    <input title="Product Name" type="text" name="name">
    <br><br>
    Product description:<br>
    <input title="Product description" type="text" name="description">
    <br><br>
    Category Name:<br>
    <select title="Category name" name="c_name">
        <c:forEach var="c" items="${categories}">
            <option value="${c.name}">
                <%--<c--%>
            </option>
        </c:forEach>
    </select>

    <%--<input title="Category Name" type="text" name="c_name">--%>
    <br><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
