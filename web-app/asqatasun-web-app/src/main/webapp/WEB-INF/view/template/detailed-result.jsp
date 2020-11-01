<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://tagutils" prefix="tg" %>
<%-- ----------------------------------
     .jsp file used with <c:import> tag
     ----------------------------------  --%>

<%-- Allowed the use of ${asqatasunVersion} --%>
<%@include file="/WEB-INF/view/template/template_variables.jspf" %>

<c:if test="${addSideBarNav}">
    <div class="theme-nav bs-docs-sidebar">
        <ul class="nav-list bs-docs-sidenav">
            <c:forEach var="entry" items="${testResultMap}" varStatus="pResultMap">
                <li>
                    <a href="#theme${entry.key.rank}">
                        <fmt:message key="${entry.key.code}"/>
                    </a>
                </li>
            </c:forEach>
        </ul>
    </div>
</c:if>
<c:choose>
    <c:when test="${testResultMap != null and not empty testResultMap}">
        <c:if test="${addMainTitle}">
            <div class="row">
                <div id="work-done" class="span16">
                    <h2>
                        <c:choose>
                            <c:when test="${scope == 'page'}">
                                <fmt:message key="resultPage.detailedResultPage"/>
                            </c:when>
                            <c:when test="${scope == 'site'}">
                                <fmt:message key="resultPage.detailedResultSite"/>
                            </c:when>
                        </c:choose>
                    </h2>
                    <button id="expand-all" class="result-page-action">
                        <fmt:message key="resultPage.expandAll"/>
                    </button>
                    <button id="collapse-all" class="result-page-action">
                        <fmt:message key="resultPage.collapseAll"/>
                    </button>
                </div><!-- class="span16" -->
            </div><!-- class="row" -->
        </c:if>
        <c:if test="${isManualAudit}">
            <div class="row"> 
                <div class="offset1 span4">
                    <button id="apply-all-auto-passed" class="result-page-action">
                        <fmt:message key="resultPage.applyAllAutoPassed"/>
                    </button>
                </div>
                <div class="offset1 span4">
                    <button id="apply-all-auto-failed" class="result-page-action">
                        <fmt:message key="resultPage.applyAllAutoFailed"/>
                    </button>
                </div>    
                <div class="offset1 span4">    
                    <button id="apply-all-auto-na" class="result-page-action ">
                        <fmt:message key="resultPage.applyAllAutoNa"/>
                    </button>
                </div>
            </div>
        </c:if>
        <c:set var="updateManualResultUrl">
            <c:url value="/home/contract/update-manual-result.html?wr=${param.wr}"/>
        </c:set>
        <form:form modelAttribute="manualAuditCommand"
                   method="post"
                   acceptCharset="UTF-8" 
                   enctype="application/x-www-form-urlencoded" 
                   action="${updateManualResultUrl}">
            <div id="all-themes">
                <spring:hasBindErrors name="manualAuditCommand">
                    <div id="sign-up-form-general-error">
                        <form:errors path="generalErrorMsg" cssClass="alert-message block-message error" element="div" />
                    </div>
                </spring:hasBindErrors>
                <c:forEach var="entry" items="${testResultMap}" varStatus="pResultMap">
                    <c:if test="${addThemeHeader}">
                        <div class="row theme-info">
                            <div class="span16 theme">
                                <div class="row">
                                    <div class="span1 theme-details-expand-action">
                                        <img alt="<fmt:message key='resultPage.hideInfosOn'> <fmt:param><fmt:message key="${entry.key.code}"/></fmt:param> </fmt:message>" src="${expandedImg}" class="hide-theme-details-link-icon">
                                        <img alt="<fmt:message key="resultPage.displayInfosOn"><fmt:param> <fmt:message key="${entry.key.code}"/></fmt:param></fmt:message>" src="${collapsedImg}" class="show-theme-details-link-icon">
                                    </div>
                                    <div class="span9 theme-label">
                                        <h3 id="theme${entry.key.rank}">
                                            <fmt:message key="result.theme"/> ${entry.key.rank} : <fmt:message key="${entry.key.code}"/>
                                        </h3>
                                    </div>
                                    <div class="span6 theme-result-repartition">
                                        <c:set scope="page" var="passedTitle">
                                            <fmt:message key="resultPage.tests">
                                                <fmt:param value="${counterByThemeMap[entry.key].passedCount}"/>
                                            </fmt:message>
                                            <fmt:message key="passed"/>
                                            <fmt:message key="resultPage.forTheTheme"/>
                                            <fmt:message key="${entry.key.code}"/>
                                        </c:set>
                                        <img src="<c:url value="/public/images/ico-passed-m-gray.png?v${asqatasunVersion}"/>" title="${passedTitle}" alt="${passedTitle}"/>
                                        <span class="theme-result" title="${passedTitle}">${counterByThemeMap[entry.key].passedCount}</span>
                                        <c:set scope="page" var="failedTitle">
                                            <fmt:message key="resultPage.tests">
                                                <fmt:param value="${counterByThemeMap[entry.key].failedCount}"/>
                                            </fmt:message>
                                            <fmt:message key="failed"/>
                                            <fmt:message key="resultPage.forTheTheme"/>
                                            <fmt:message key="${entry.key.code}"/>
                                        </c:set>
                                        <img src="<c:url value="/public/images/ico-failed-m-gray.png?v${asqatasunVersion}"/>" title="${failedTitle}" alt="${failedTitle}"/>
                                        <span class="theme-result" title="${failedTitle}">${counterByThemeMap[entry.key].failedCount}</span>
                                        <c:if test="${isManualAudit != 'true'}">
                                            <c:set scope="page" var="nmiTitle">
                                                <fmt:message key="resultPage.tests">
                                                    <fmt:param value="${counterByThemeMap[entry.key].nmiCount}"/>
                                                </fmt:message>
                                                <fmt:message key="nmi"/>
                                                <fmt:message key="resultPage.forTheTheme"/>
                                                <fmt:message key="${entry.key.code}"/>
                                                <!-- test git commit -->
                                            </c:set>
                                        <img src="<c:url value="/public/images/ico-nmi-m-gray.png?v${asqatasunVersion}"/>" title="${nmiTitle}" alt="${nmiTitle}"/>
                                        <span class="theme-result" title="${nmiTitle}">${counterByThemeMap[entry.key].nmiCount}</span>
                                        </c:if>
                                        <c:set scope="page" var="naTitle">
                                            <fmt:message key="resultPage.tests">
                                                <fmt:param value="${counterByThemeMap[entry.key].naCount}"/>
                                            </fmt:message>
                                            <fmt:message key="na"/>
                                            <fmt:message key="resultPage.forTheTheme"/>
                                            <fmt:message key="${entry.key.code}"/>
                                        </c:set>
                                        <img src="<c:url value="/public/images/ico-na-m-gray.png?v${asqatasunVersion}"/>" title="${naTitle}" alt="${naTitle}"/>
                                        <span class="theme-result" title="${naTitle}">${counterByThemeMap[entry.key].naCount}</span> 

                                        <c:set scope="page" var="ntTitle">
                                            <fmt:message key="resultPage.tests">
                                                <fmt:param value="${counterByThemeMap[entry.key].ntCount}"/>
                                            </fmt:message>
                                            <fmt:message key="nt"/>
                                            <fmt:message key="resultPage.forTheTheme"/>
                                            <fmt:message key="${entry.key.code}"/>
                                        </c:set>
                                        <img src="<c:url value="/public/images/ico-nt-m-gray.png?v${asqatasunVersion}"/>" title="${ntTitle}" alt="${ntTitle}"/>
                                        <span class="theme-result" title="${ntTitle}">${counterByThemeMap[entry.key].ntCount}</span>
                                    </div>
                                </div>
                            </div><!-- class="span16" -->
                        </div><!-- class="row" -->
                    </c:if>
                    <c:if test="${tg:lang(pageContext) != 'en' && tg:lang(pageContext) != 'fr'}">
                        <c:choose>
                            <c:when test="${fn:startsWith(statistics.parametersMap['referential'], 'Rgaa')}">
                                <c:set var="ruleLang" value=" lang=fr "/>
                            </c:when>
                            <c:otherwise>
                                <c:set var="ruleLang" value=" lang=en "/>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                    <div id="theme${entry.key.rank}-details" class="theme-details">
                        <c:forEach var="testResult" items="${entry.value}" varStatus="pTestResultList">
                            <c:choose>
                                <c:when test="${pTestResultList.index % 2 == 1}">
                                    <c:set var="rowBgClass" value="" scope="page"/>
                                </c:when>
                                <c:otherwise>
                                    <c:set var="rowBgClass" value="" scope="page"/>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${pTestResultList.index == fn:length(entry.value)-1}">
                                    <c:set var="ruleGeneralResultClass" value="rule-general-result-last"/>
                                </c:when>
                                <c:otherwise>
                                    <c:set var="ruleGeneralResultClass" value="rule-general-result"/>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${testResult.elementCounter != 0 || not empty testResult.remarkInfosList}">
                                    <c:set var="addTestDetails" value="true"/>
                                </c:when>
                                <c:otherwise>
                                    <c:set var="addTestDetails" value="false"/>
                                </c:otherwise>
                            </c:choose>
                            <div class="row">
                                <div class="span16 ${ruleGeneralResultClass} ${rowBgClass}">
                                    <div class="test-result-compact row">
                                        <div class="rule-id span2">
                                            <c:if test="${addTestDetails && addShowHide}">
                                                <span class="detail-link-icon">
                                                    <img alt="<fmt:message key="resultPage.hideTestInfosOn"> <fmt:param>${testResult.testShortLabel}</fmt:param></fmt:message>" src="${expandedSmallImg}" class="hide-test-details-link-icon">
                                                    <img alt="<fmt:message key="resultPage.displayTestInfosOn"> <fmt:param>${testResult.testShortLabel}</fmt:param></fmt:message>" src="${collapsedSmallImg}" class="show-test-details-link-icon">
                                                        </span>
                                            </c:if>
                                            <h4 id="test-${testResult.testShortLabel}">${testResult.testShortLabel} <span class="test-result sr-only"> <fmt:message key="${testResult.resultCode}"/> </span></h4>
                                            <span class="rule-detail-link">
                                                <a rel="noreferrer noopener" title="<fmt:message key="resultPage.more"/> ${testResult.testShortLabel}" href="<fmt:message key="${testResult.testCode}-url"/>">
                                                    <img alt="<fmt:message key="resultPage.more"/> ${testResult.testShortLabel}" src="${testInfoLinkImg}">
                                                </a>
                                            </span>
                                        </div>
                                        <div class="rule-label span9" ${ruleLang}>
                                            <fmt:message key="${testResult.testCode}"/>
                                        </div><!-- class="span9 rule-label" -->

                                        <div class="audit-result-container span5">
                                            <div class="row">
                                                <div class="span1 test-details">
                                                    <c:if test="${displayAlgorithm == 'true'}">
                                                        <a rel="noreferrer noopener" title="<fmt:message key="resultPage.ruleDesignUrl"/> ${testResult.testShortLabel}" href="${testResult.ruleDesignUrl}">
                                                            <img alt="<fmt:message key="resultPage.ruleDesignUrl"/> ${testResult.testShortLabel}" src="${algoLinkImg}">
                                                        </a>
                                                    </c:if>
                                                </div>
                                                <div class="${rowBgClass} span1 test-result" >
                                                    <img src="<c:url value="/public/images/ico-${testResult.resultCode}-m.png?v${asqatasunVersion}"/>" alt="test ${testResult.testShortLabel} <fmt:message key="${testResult.resultCode}"/>"/>
                                                </div>
                                                <c:choose>
                                                    <c:when test="${isManualAudit}">
                                                <div class="audit-result-manual span3">
                                                    <fieldset>
                                                        <legend><fmt:message key="resultPage.overridenResult"/><span class="offscreen">${testResult.testShortLabel}</span>
                                                        </legend>
                                                        <c:forEach items="${manualAuditCommand.statusList}" var="auditStatus">
                                                            <div class="clearfix ">
                                                                <div class="input">
                                                                    <form:radiobutton id="${auditStatus}${testResult.testShortLabel}" name="auditStatus${testResult.testShortLabel}" path="modifiedManualResultMap['${testResult.testShortLabel}'].result" value="${auditStatus}"/>
                                                                    <form:label path="" for="${auditStatus}${testResult.testShortLabel}"><fmt:message key="${auditStatus}"/></form:label>
                                                                    </div>
                                                                </div>
                                                        </c:forEach>
                                                    </fieldset>
                                                </div>   
                                                    </c:when>
                                                    <c:otherwise>
                                                <div class="audit-result-manual-empty span3"></div>
                                                    </c:otherwise>
                                                </c:choose>	    
                                            </div>
                                        </div>
                                        <c:if test="${isManualAudit}">
                                            <div class="audit-result-manual-comment span15" id="commentContainer${testResult.testShortLabel}">
                                                <label for="cmt${testResult.testShortLabel}"> <fmt:message key="resultPage.commentArea"/> <span class="offscreen">${testResult.testShortLabel}</label>
                                                <form:textarea id="cmt${testResult.testShortLabel}" path="modifiedManualResultMap['${testResult.testShortLabel}'].comment" rows="2" cols="30" maxLength="250"/>
                                            </div>
                                        </c:if>
                                    </div>
                                </div>
                                <c:if test="${addTestDetails}">
                                    <div id="r${testResult.testShortLabel}-detailed" class="span15 test-result-detailed">
                                        <c:if test='${0 != testResult.elementCounter}'>
                                            <div class="row">    
                                                <div class="span4">
                                                    <ul class="counter-remarks">
                                                        <li>
                                                            <fmt:message key="resultPage.testedElements">
                                                                <fmt:param value="${testResult.elementCounter}"/>
                                                            </fmt:message>
                                                        </li>
                                                        <c:if test="${testResult.resultCounter.failedCount > 0}">
                                                            <li class="subcounter">
                                                                <c:choose>
                                                                    <c:when test="${testResult.testRepresentation  != 'data-representation/data-representation-1.jsp'}">
                                                                        <a href="#${testResult.testCode}failed0">
                                                                            <fmt:message key="resultPage.failedElements">
                                                                                <fmt:param value="${testResult.resultCounter.failedCount}"/>
                                                                            </fmt:message>
                                                                        </a>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <fmt:message key="resultPage.failedElements">
                                                                            <fmt:param value="${testResult.resultCounter.failedCount}"/>
                                                                        </fmt:message>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </li>
                                                        </c:if>
                                                        <c:if test="${testResult.resultCounter.nmiCount > 0}">
                                                            <li class="subcounter">
                                                                <c:choose>
                                                                    <c:when test="${testResult.testRepresentation  != 'data-representation/data-representation-1.jsp'}">
                                                                        <a href="#${testResult.testCode}nmi0">
                                                                            <fmt:message key="resultPage.nmiElements">
                                                                                <fmt:param value="${testResult.resultCounter.nmiCount}"/>
                                                                            </fmt:message>
                                                                        </a>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <fmt:message key="resultPage.nmiElements">
                                                                            <fmt:param value="${testResult.resultCounter.nmiCount}"/>
                                                                        </fmt:message>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </li>
                                                        </c:if>
                                                        <c:if test="${testResult.resultCounter.suspectedPassedCount > 0}">
                                                            <li class="subcounter">
                                                                <fmt:message key="resultPage.nmiSuspectedPassedElements">
                                                                    <fmt:param value="${testResult.resultCounter.suspectedPassedCount}"/>
                                                                </fmt:message>
                                                            </li>
                                                        </c:if>
                                                        <c:if test="${testResult.resultCounter.suspectedFailedCount > 0}">
                                                            <li class="subcounter">
                                                                <fmt:message key="resultPage.nmiSuspectedFailedElements">
                                                                    <fmt:param value="${testResult.resultCounter.suspectedFailedCount}"/>
                                                                </fmt:message>
                                                            </li>
                                                        </c:if>
                                                    </ul>
                                                </div><!-- class="span3 offset1" -->
                                            </div>
                                        </c:if>
                                        <c:set var="nmiCounter" scope="request" value="0"/>
                                        <c:set var="failedCounter" scope="request" value="0"/>
                                        <c:if test="${not empty testResult.remarkInfosList}">
                                            <c:choose>
                                                <c:when test="${testResult.testRepresentation  == 'data-representation/data-representation-3.jsp' || 
                                                                testResult.testRepresentation  == 'data-representation/data-representation-4.jsp'}">
                                                    <c:set var="testCode" scope="request" value="${testResult.testCode}"/>
                                                    <c:set var="remarkInfosList" scope="request" value="${testResult.remarkInfosList}"/>
                                                    <c:import url="${testResult.testRepresentation}"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:forEach var="remarkInfosItem" items="${testResult.remarkInfosList}">
                                                        <c:set var="remarkInfosItem" scope="request" value="${remarkInfosItem}"/>
                                                        <c:set var="testCode" scope="request" value="${testResult.testCode}"/>
                                                        <c:choose>
                                                            <c:when test="${testResult.testRepresentation != null}">
                                                                <c:import url="${testResult.testRepresentation}"/>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <c:import url="data-representation/data-representation-2.jsp"/>
                                                            </c:otherwise>
                                                        </c:choose>

                                                        <c:if test="${remarkInfosItem.remarkResult == 'nmi'}">
                                                            <c:set var="nmiCounter" scope="request" value="${nmiCounter + 1}"/>
                                                        </c:if>
                                                        <c:if test="${remarkInfosItem.remarkResult == 'failed'}">
                                                            <c:set var="failedCounter" scope="request" value="${failedCounter + 1}"/>
                                                        </c:if>
                                                    </c:forEach>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:if><!-- if has remarks to display -->
                                        <c:if test="${testResult.isTruncated}">
                                            <div class="span14 all-remarks">
                                                <c:set var="allRemarksLink">
                                                    <fmt:message key="resultPage.seeAllRemarks">
                                                        <fmt:param>
                                                            ${testResult.testShortLabel}
                                                        </fmt:param>
                                                    </fmt:message>
                                                </c:set>
                                                <c:set var="allRemarksLinkTitle">
                                                    <fmt:message key="resultPage.seeAllRemarksTitle">
                                                        <fmt:param>
                                                            ${testResult.testShortLabel}
                                                        </fmt:param>
                                                    </fmt:message>
                                                </c:set>
                                                <a title="${allRemarksLinkTitle}" class="all-remarks-link" href="<c:url value="/home/contract/test-result.html?wr=${param.wr}&amp;testKey=${testResult.test.id}"/>" target="_blank">
                                                    ${allRemarksLink}
                                                </a>
                                            </div>
                                        </c:if>    
                                    </div>
                                </c:if> <!-- if test="addTestDetails"> --> 
                            </div>
                        </c:forEach>
                    </div> <!-- div id="themex-results"> --> 
                </c:forEach>
                <c:if test="${isManualAudit}">
                    <div id="manual-audit-form-submit" class="row">
                        <div class="span4 offset3">
                            <input  type="submit"  class="result-page-action manual-audit-save-btn"  name="action"  value="<fmt:message key="resultPage.saveManualResultsBtnName"/>"/>
                        </div>
                        <div class="span4 offset2">
                            <input class="result-page-action manual-audit-save-btn" type="submit" value="<fmt:message key="resultPage.closeManualAudit"/>" name="action" />
                        </div>
                    </div>
                </c:if>
            </div><!-- id="all-theme" -->
        </form:form>
    </c:when>
    <c:otherwise>
        <div class="row">
            <div id="work-done" class="span16">
                <h2>
                    <fmt:message key="resultPage.noResults"/>
                </h2>
            </div>
        </div>
    </c:otherwise>
</c:choose>
