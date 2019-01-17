<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%--
CAUTION
if you ever change the values defining the grades, don't forget to modify also:
web-app/asqatasun-web-app/src/main/webapp/public/js/score/grade-page-list-f2xx.js
 --%>
<c:choose>
    <c:when test="${mark == 100}">
        <c:set var="grade" scope="page" value="A"/>
        <c:set var="gradeClass" scope="page" value="grade-a"/>
    </c:when>
    <c:when test="${mark > 90}">
        <c:set var="grade" scope="page" value="B"/>
        <c:set var="gradeClass" scope="page" value="grade-b"/>
    </c:when>
    <c:when test="${mark > 85}">
        <c:set var="grade" scope="page" value="C"/>
        <c:set var="gradeClass" scope="page" value="grade-c"/>
    </c:when>
    <c:when test="${mark > 75}">
        <c:set var="grade" scope="page" value="D"/>
        <c:set var="gradeClass" scope="page" value="grade-d"/>
    </c:when>
    <c:when test="${mark > 60}">
        <c:set var="grade" scope="page" value="E"/>
        <c:set var="gradeClass" scope="page" value="grade-e"/>
    </c:when>
    <c:otherwise>
        <c:set var="grade" scope="page" value="F"/>
        <c:set var="gradeClass" scope="page" value="grade-f"/>
    </c:otherwise>
</c:choose>
<div class="${spanClass} ${scoreClass}">
    <span class="grade-container ${gradeClass} ">
        <span class="grade-letter" >${grade}</span>
    </span>
</div>
