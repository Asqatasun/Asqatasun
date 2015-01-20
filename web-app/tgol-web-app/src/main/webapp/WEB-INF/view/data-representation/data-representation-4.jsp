<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="row">
    <div class="span15">
        <c:set var="msgCode" scope="request" value="${remarkInfosList[0].messageCode}"/>
        <c:choose>
            <c:when test="${remarkInfosList[0].remarkResult == 'nmi'}">
        <p id="${testCode}nmi${nmiCounter}" class="process-remarks ${remarkInfosList[0].remarkResult}-pr">
            </c:when>
            <c:when test="${remarkInfosList[0].remarkResult == 'failed'}">
        <p id="${testCode}failed${failedCounter}" class="process-remarks ${remarkInfosList[0].remarkResult}-pr">
            </c:when>
            <c:otherwise>
        <p class="process-remarks ${remarkInfosList[0].remarkResult}-pr">
            </c:otherwise>
        </c:choose>
            <fmt:message  key="${msgCode}"/>
        </p>
        <table class="evidence-elements-table sortable-table">
            <caption><fmt:message  key="${msgCode}"/></caption>
<!--    First parse of evidence element list to get the table headers (evidencement element key)-->
            <thead>
                <tr>
                    <th class="r${testCode}-col-Occurences occurences" scope="col"><fmt:message  key="Failed-Occurences"/></th>
                    <th class="r${testCode}-col-page-list" scope="col"><fmt:message  key="Page-List"/></th>
                </tr>
            </thead>
<!--    Second parse of evidence element list to get value corresponding to each header of each element list-->
            <tbody>
            <c:forEach var="remarkInfosItem" items="${remarkInfosList}">
                <tr>
                    <td class="occurences">
                        ${fn:length(remarkInfosItem.evidenceElementList)}
                    </td>
                    <td class="page-list">
                        <ul>
                        <c:forEach var="evidenceElementList" items="${remarkInfosItem.evidenceElementList}">
                            <c:forEach var="evidenceElement" items="${evidenceElementList}">
                            <li>
                                <a href="${evidenceElement.value}" title="${evidenceElement.value}">${evidenceElement.value}</a>
                            </li>
                            </c:forEach>
                        </c:forEach>
                        </ul>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
