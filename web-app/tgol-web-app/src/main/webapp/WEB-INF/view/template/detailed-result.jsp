<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
                <c:forEach var="entry" items="${testResultMap}" varStatus="pResultMap">
                <c:if test="${addThemeHeader}">
                <div class="row theme-info">
                    <div class="span16 theme">
                        <div class="row">
                            <div class="span1 theme-details-expand-action">
                                <img alt="<fmt:message key="resultPage.hideInfosOn"> <fmt:param><fmt:message key="${entry.key.code}"/></fmt:param> </fmt:message>" src="${expandedImg}" class="hide-theme-details-link-icon">
                                <img alt="<fmt:message key="resultPage.displayInfosOn"><fmt:param> <fmt:message key="${entry.key.code}"/></fmt:param></fmt:message>" src="${collapsedImg}" class="show-theme-details-link-icon">
                            </div>
                            <div class="span9 theme-label">
                                <h3 id="theme${entry.key.rank}">
                                    <fmt:message key="result.theme"/> ${entry.key.rank} : <fmt:message key="${entry.key.code}"/>
                                </h3>
                            </div>
                            <div class="span6 theme-result-repartition">
                                <span class="passed-th-gray theme-result">${counterByThemeMap[entry.key].passedCount}</span>
                                <span class="failed-th-gray theme-result">${counterByThemeMap[entry.key].failedCount}</span>
                                <span class="nmi-th-gray theme-result">${counterByThemeMap[entry.key].nmiCount}</span>
                                <span class="na-th-gray theme-result">${counterByThemeMap[entry.key].naCount}</span>
                                <span class="nt-th-gray theme-result">${counterByThemeMap[entry.key].ntCount}</span>
                            </div>
                        </div>
                    </div><!-- class="span16" -->
                </div><!-- class="row" -->
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
                                <c:if test="${addTestDetails}">
                                <span class="detail-link-icon">
                                    <img alt="<fmt:message key="resultPage.hideTestInfosOn"> <fmt:param>${testResult.testShortLabel}</fmt:param></fmt:message>" src="${expandedSmallImg}" class="hide-test-details-link-icon">
                                    <img alt="<fmt:message key="resultPage.displayTestInfosOn"> <fmt:param>${testResult.testShortLabel}</fmt:param></fmt:message>" src="${collapsedSmallImg}" class="show-test-details-link-icon">
                                </span>
                                </c:if>
                                <h4>${testResult.testShortLabel}</h4>
                                <span class="rule-detail-link">
                                    <a title="<fmt:message key="resultPage.more"/> ${testResult.testShortLabel}" href="<fmt:message key="${testResult.testCode}-url"/>">
                                        <img alt="<fmt:message key="resultPage.more"/> ${testResult.testShortLabel}" src="${testInfoLinkImg}">
                                    </a>
                                </span>
                            </div>
                            <div class="rule-label span11">
                                <fmt:message key="${testResult.testCode}"/>
                            </div><!-- class="span9 rule-label" -->
                            <div class="${rowBgClass} span1 test-result">
                                <img src="<c:url value="/Images/ico-${testResult.resultCode}-m.png"/>" alt="<fmt:message key="${testResult.resultCode}"/>"/> 
                            </div>
                            <div class="span1 test-details">
                                <c:if test="${displayAlgorithm == 'true'}">
                                <a title="<fmt:message key="resultPage.ruleDesignUrl"/> ${testResult.testShortLabel}" href="${testResult.ruleDesignUrl}">
                                    <img alt="<fmt:message key="resultPage.ruleDesignUrl"/> ${testResult.testShortLabel}" src="${algoLinkImg}">
                                </a>
                                </c:if>
                            </div>
                        </div><!-- class="row" -->
                    </div>
                    <c:if test="${addTestDetails}">
                    <div id="r${testResult.testShortLabel}-detailed" class="span15 test-result-detailed">
                        <c:if test='${0 != testResult.elementCounter}'>
                        <div class="row">    
                            <div class="span4">
                                <ul class="counter-remarks">
                                    <li>
                                        ${testResult.elementCounter} <fmt:message key="resultPage.testedElements"/>
                                    </li>
                                    <c:if test='${testResult.resultCounter.failedCount > 0}'>
                                    <li class="subcounter">
                                    <c:choose>
                                        <c:when test="${testResult.testRepresentation  != 'data-representation/data-representation-1.jsp'}">
                                        <a href="#${testResult.testCode}failed0">${testResult.resultCounter.failedCount} <fmt:message key="resultPage.failedElements"/></a>
                                        </c:when>
                                        <c:otherwise>
                                        ${testResult.resultCounter.failedCount} <fmt:message key="resultPage.failedElements"/>
                                        </c:otherwise>
                                    </c:choose>
                                    </li>
                                    </c:if>
                                    <c:if test='${testResult.resultCounter.nmiCount > 0}'>
                                    <li class="subcounter">
                                    <c:choose>
                                        <c:when test="${testResult.testRepresentation  != 'data-representation/data-representation-1.jsp'}">
                                        <a href="#${testResult.testCode}nmi0">${testResult.resultCounter.nmiCount} <fmt:message key="resultPage.nmiElements"/></a>
                                        </c:when>
                                        <c:otherwise>
                                        ${testResult.resultCounter.nmiCount} <fmt:message key="resultPage.nmiElements"/>
                                        </c:otherwise>
                                    </c:choose>
                                    </li>
                                    </c:if>
                                    <c:if test='${testResult.resultCounter.suspectedPassedCount > 0}'>
                                    <li class="subcounter">
                                        ${testResult.resultCounter.suspectedPassedCount} <fmt:message key="resultPage.nmiSuspectedPassedElements"/>
                                    </li>
                                    </c:if>
                                    <c:if test='${testResult.resultCounter.suspectedFailedCount > 0}'>
                                    <li class="subcounter">
                                        ${testResult.resultCounter.suspectedFailedCount} <fmt:message key="resultPage.nmiSuspectedFailedElements"/>
                                    </li>
                                    </c:if>
                                </ul>
                            </div><!-- class="span3 offset1" -->
                        </div>
                        </c:if>
                        <c:set var="nmiCounter" scope="request" value="0"/>
                        <c:set var="failedCounter" scope="request" value="0"/>
                        <c:if test='${not empty testResult.remarkInfosList}'>
                            <c:forEach var="remarkInfosItem" items="${testResult.remarkInfosList}">
                                <c:set var="remarkInfosItem" scope="request" value="${remarkInfosItem}"/>
                                <c:set var="testCode" scope="request" value="${testResult.testCode}"/>
                                <c:import url="${testResult.testRepresentation}"/>
                                <c:if test="${remarkInfosItem.remarkResult == 'nmi'}">
                                    <c:set var="nmiCounter" scope="request" value="${nmiCounter + 1}"/>
                                </c:if>
                                <c:if test="${remarkInfosItem.remarkResult == 'failed'}">
                                    <c:set var="failedCounter" scope="request" value="${failedCounter + 1}"/>
                                </c:if>
                            </c:forEach>
                        </c:if><!-- if has remarks to display -->
                    </div>
                    </c:if> <!-- if test="addTestDetails"> --> 
                </div>
                    </c:forEach>
                </div> <!-- div id="themex-results"> --> 
                </c:forEach>
            </c:when>
            <c:otherwise>
                <div class="row">
                    <div id="work-done" class="span16"
                        <h2>
                            <fmt:message key="resultPage.noResults"/>
                        </h2>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
