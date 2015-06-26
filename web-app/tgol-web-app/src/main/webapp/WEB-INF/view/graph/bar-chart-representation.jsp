<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<table id="result-by-theme" class="zebra-striped">
    <caption>
        <fmt:message key="graph.resultRepartitionByThemeSummaryAndCaption"/>
    </caption>
    <thead>
        <tr>
            <td>#</td>
        <c:forEach var="entry" items="${counterByThemeMap}">
            <th id="category${entry.key.rank}" 
                title="<fmt:message key="${entry.key.code}"/>">
                <fmt:message key="${entry.key.code}"/>
            </th>
        </c:forEach>
        </tr>    
    </thead>
    <tbody>
        <tr>
            <th id="serie1" 
                title="<fmt:message key="failed"/>">
                <fmt:message key="failed"/>
            </th>
        <c:forEach var="entry" items="${counterByThemeMap}">    
            <td headers="serie1 category${entry.key.rank}">
                ${entry.value.failedCount}
            </td>
        </c:forEach>
        </tr>
        <tr>
            <th id="serie2" 
                title="<fmt:message key="passed"/>">
                <fmt:message key="passed"/>
            </th>
        <c:forEach var="entry" items="${counterByThemeMap}">    
            <td headers="serie2 category${entry.key.rank}">
                ${entry.value.passedCount}
            </td>
        </c:forEach>
        </tr>
        <tr>
            <th id="serie3" 
                title="<fmt:message key="nmi"/>">
                <fmt:message key="nmi"/>
            </th>
        <c:forEach var="entry" items="${counterByThemeMap}">    
            <td headers="serie3 category${entry.key.rank}">
                ${entry.value.nmiCount}
            </td>
        </c:forEach>
        </tr>
        <tr>
            <th id="serie4" 
                title="<fmt:message key="na"/>">
                <fmt:message key="na"/>
            </th>
        <c:forEach var="entry" items="${counterByThemeMap}">    
            <td headers="serie4 category${entry.key.rank}">
                ${entry.value.naCount}
            </td>
        </c:forEach>
        </tr>
    </tbody>
</table>

<div id="result-by-theme-graph"></div>