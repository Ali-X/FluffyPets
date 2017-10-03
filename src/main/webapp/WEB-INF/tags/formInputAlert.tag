<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="validator" required="true" type="com.fluffypets.mvc.page_objects.FieldStatus" %>

<c:choose>
    <c:when test="${not empty validator}">
        <c:if test="${validator.isStatus()}">class="form-control"</c:if>
        <c:if test="${not validator.isStatus()}">class="form-control alert-danger"</c:if>
    </c:when>
    <c:otherwise>
        class="form-control"
    </c:otherwise>
</c:choose>