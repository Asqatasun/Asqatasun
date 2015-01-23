<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<table id="top5-failed-theme">
    <caption>
        <fmt:message key="graph.mostInvalidedThemesSummaryAndCaption"/>
    </caption>
    <tr>
        <td></td>
    <c:forEach var="counterByTheme" items="${counterByThemeMap}" varStatus="categoriesIndex">
        <c:if test="${categoriesIndex.index < 5}">
        <th id="top5Theme${categoriesIndex.index}" title="<fmt:message key="${counterByTheme.key.code}"/>">
            <fmt:message key="${counterByTheme.key.code}"/>
        </th>
        </c:if>
    </c:forEach>
    </tr>
    <tr>
        <th id="top5ResultFailed">
            <fmt:message key="failed"/>
        </th>
    <c:forEach var="counterByTheme" items="${counterByThemeMap}" varStatus="categoriesIndex">
        <c:if test="${categoriesIndex.index < 5}">
        <td headers="top5ResultFailed top5Theme${categoriesIndex.index}">
            ${counterByTheme.value.failedCount}
        </td>
        </c:if>
    </c:forEach>
    </tr>
</table>
<div id="top5-failed-theme-graph">

</div>
