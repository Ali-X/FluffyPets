<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="validator" required="true" type="com.fluffypets.mvc.page_objects.FieldStatus" %>
<%@ attribute name="basic" required="false"%>

<c:choose>
    <c:when test="${not empty validator}">
        <c:if test="${validator.isStatus()}">class="form-control ${basic}"</c:if>
        <c:if test="${not validator.isStatus()}">class="form-control alert-danger ${basic}"</c:if>
    </c:when>
    <c:otherwise>
        class="form-control ${basic}"
    </c:otherwise>
</c:choose>

