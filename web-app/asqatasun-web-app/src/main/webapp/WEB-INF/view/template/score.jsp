<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:choose>
    <c:when test="${not empty scoreId}">
<div id="${scoreId}" class="${spanClass}">
    <div>${mark}<span class="percent">%</span></div>
</div>                    
    </c:when>
    <c:when test="${not empty scoreClass}">
<div class="${spanClass} ${scoreClass}">
    <div>${mark}<span class="percent">%</span></div>
</div>                    
    </c:when>
    <c:otherwise>
<div class="${spanClass} ${scoreClass}">
    <div>${mark}<span class="percent">%</span></div>
</div>
</c:otherwise>
</c:choose>
<c:if test="${hasScoreFormulaLink == 'true'}">
<span id="score-formula">
    <a href="<fmt:message key="resultPage.scoreFormulaLinkURL"/>">
        <fmt:message key="resultPage.scoreFormulaLinkText"/>
    </a>
</span>
</c:if>