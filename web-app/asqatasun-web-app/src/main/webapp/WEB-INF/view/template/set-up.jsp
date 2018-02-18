<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://tagutils" prefix="tg" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%-- check if at least 1 referential is enabled --%>
<c:set var="noReferentialEnabled" value="1"/>  <%-- default: no referential enabled --%>
<c:forEach var="level" items="${levelList}" >
    <c:forEach items="${level.selectElementMap}"   var="group">
        <c:if test="${group.value[0].enabled == true}">
            <c:set var="noReferentialEnabled" value="0"/> <%-- 1 referential is enabled --%>
        </c:if>
    </c:forEach>
</c:forEach>

<c:choose>
    <c:when test="${noReferentialEnabled == '1'}">
        <div class="alert-message block-message error span16">
            <h2><fmt:message key="auditSetUp.projectConfigurationError"/> </h2>
            <p><fmt:message key="auditSetUp.errorNoRefentialEnabled"/>    </p>
        </div>
    </c:when>
    <c:otherwise>
        <%@include file="set-up-form.jsp" %>
    </c:otherwise>
</c:choose>