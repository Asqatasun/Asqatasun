<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:choose>
    <c:when test="${isGeneratedHtml}">
<h2 id="source-code">
    <fmt:message key="resultPage.generatedHtmlsourceCode"/>
</h2>
    </c:when>
    <c:otherwise>
<h2 id="source-code"><fmt:message key="resultPage.sourceCode"/></h2>
    </c:otherwise>
</c:choose>

<div id="source-code-container">
    ${sourceCode}
</div>