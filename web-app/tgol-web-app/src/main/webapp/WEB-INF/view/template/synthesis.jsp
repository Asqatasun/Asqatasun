 <%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

                <c:if test="${hasSynthesisTitle == 'true'}">
                <div class="row">
                    <div class="span16 offset0">
                        <h2 id="synthesis"><fmt:message key="resultPage.synthesis"/></h2>
                    </div><!-- class="span16 offset0"-->
                </div><!-- class="row"-->
                </c:if>
                <div id="synthesis-result" class="row">
                    <c:set var="url" scope="page" value="${statistics.url}"/>
                    <c:set var="scope" scope="page" value="${statistics.auditScope}"/>
                    <c:set var="proportion" scope="page" value="span5"/>
                    <c:set var="offset" scope="page" value="offset0"/>
                    <c:set var="size" scope="page" value="S"/>
                    <%@include file="thumbnail.jsp" %>
                    <div id="synthesis-meta-data" class="span8 offset0">
                        <div id="project-url">
                    <c:choose>
                        <c:when test="${statistics.auditScope == 'GROUPOFFILES' || statistics.auditScope == 'FILE'}">
                            <span class="synthesis-meta-title">Url : </span>${statistics.url}
                        </c:when>
                        <c:when test="${statistics.auditScope == 'SCENARIO'}">
                            <span class="synthesis-meta-title">Scenario : </span> ${statistics.url}
                        </c:when>
                        <c:otherwise>
                            <span class="synthesis-meta-title">Url : </span><a href="${statistics.url}">${statistics.url}</a>
                        </c:otherwise>
                    </c:choose>
                        <c:if test="${addLinkToSourceCode == 'true'}">
                        <c:set var="sourceCodeLinkTitle" scope="page">
                            <fmt:message key="resultPage.sourceCodeLinkTitle">
                                <fmt:param>
                                    ${statistics.url}
                                </fmt:param>
                            </fmt:message>
                        </c:set>
                            <span id="page-source-code-link">    
                                <a href="<c:url value="/home/contract/source-code-page.html?wr=${param.wr}"/>" title="${sourceCodeLinkTitle}" target="_blank" class="result-page-action">
                                    Code HTML
                                </a>
                            </span>
                        </c:if>
                        </div>
                        <div id="project-creation-date">
                            <span class="synthesis-meta-title"><fmt:message key="contract.createdOn"/> : </span><fmt:formatDate type="date" value="${statistics.date}" dateStyle="long"/> <fmt:formatDate type="time" value="${statistics.date}"/>
                        </div>
                        <c:if test="${hasPageCounter == 'true'}">
                        <div class="audit-nb-of-pages">
                            <c:set var="pageCounterLinkTitle" scope="page">
                                <fmt:message key="resultPage.pageCounter">
                                    <fmt:param value="${statistics.pageCounter}"/>
                                </fmt:message>
                            </c:set>
                            <c:choose>
                                <c:when test="${hasPagesListLink == 'true'}">
                                <c:choose>
                                    <c:when test="${statistics.auditScope == 'SCENARIO'}">
                                        <a href="<c:url value="/home/contract/page-list.html?wr=${wr}&amp;status=f2xx&amp;sortDirection=2&amp;sortCriterion=rank"/>" >${pageCounterLinkTitle}</a>
                                    </c:when>
                                    <c:when test="${statistics.auditScope == 'DOMAIN'}">
                                        <a href="<c:url value="/home/contract/page-list.html?wr=${wr}"/>">${pageCounterLinkTitle}</a>
                                    </c:when>
                                </c:choose>
                                </c:when>
                                <c:otherwise>
                                    ${pageCounterLinkTitle}
                                </c:otherwise>
                            </c:choose>
                        <c:if test="${hasSiteScopeTest == 'true'}">
                            <span id="synthesis-action-list">
                                <a href="<c:url value="/home/contract/audit-result.html?wr=${wr}"/>" class="result-page-action"><spring:message code="synthesisSite.siteResults"/></a></li>
                            </span>
                        </c:if>                                        
                        </div>
                        </c:if>
                        <c:if test="${fn:length(statistics.parametersMap) != 0}">
                        <div id="audit-ref">
                            <span class="synthesis-meta-title"><spring:message code="referential"/> : </span>
                            <c:set var="refCode" scope="page">
                                ${statistics.parametersMap["referential"]}
                            </c:set>
                            <span class="synthesis-meta-value">${refCode}</span>
                        </div>
                        <div id="audit-level">
                            <span class="synthesis-meta-title"><spring:message code="level"/> : </span>
                            <c:set var="levelCode" scope="page">
                                ${statistics.parametersMap["level"]}
                            </c:set>
                            <span class="synthesis-meta-value"><spring:message code="${levelCode}"/></span>
                        </div>
                        <div>
                            <span id="master-audit-parameters"><fmt:message key="auditSetUp.formTitle"/></span>
                        </div>
                        <div id="audit-parameters">
                            <ul>
                            <c:forEach var="entry" items="${statistics.parametersMap}">
                                <c:if test="${entry.key != 'referential' && entry.key != 'level'}">
                                <li>
                                    <span class="synthesis-meta-title"><spring:message code="${entry.key}"/> : </span>
                                    <span class="synthesis-meta-value">
                                    <c:catch var="jspTagException" >
                                        <spring:message code="${entry.value}"/>
                                    </c:catch>
                                    <c:if test = "${jspTagException != null}">
                                        ${entry.value}
                                    </c:if>
                                    </span>
                                </li>
                                </c:if>
                            </c:forEach>
                            </ul>
                        </div><!-- class="project-parameters" -->
                        </c:if>
                    </div><!-- class="span6 offset2" -->
                    <c:set var="mark" scope="page" value="${statistics.rawMark}"/>
                    <c:set var="weightedMark" scope="page" value="${statistics.weightedMark}"/>
                    <c:set var="scoreId" scope="page" value="audit-score"/>
                    <c:set var="hasScoreFormulaLink" scope="page" value="false"/>
                    <c:set var="spanClass" scope="page" value="span3"/>
                    <%@include file="../template/score.jsp" %>
                </div> <!-- class="row" -->
                <c:if test="${hasResultDispatchTitle == 'true'}">
                <div class="row">
                    <div class="span16 offset0">
                        <h2 id="result-dispatch"><spring:message code="synthesisSite.resultDispatch"/></h2>
                    </div><!-- class="span16 offset0" -->
                </div> <!-- class="row" -->
                </c:if>
                <c:if test="${hasGraphics == 'true'}">
                <div id="graphical-synthesis-result" class="row">
                    <c:if test="${hasPieChartInGraphicalResult == 'true'}">
                    <div class="span5">
                        <c:set var="counter" scope="request" value="${statistics.resultCounter}"/>
                        <c:import url="template/pie-chart.jsp"/>
                    </div><!---class="span5"-->
                    </c:if>
                <c:set var="counterByThemeMap" scope="request" value="${statistics.counterByThemeMap}"/>
                <c:set var="width" scope="request" value="${themeRepartitionWidth}"/>
                <c:set var="height" scope="request" value="250"/>
                <c:set var="xLabel" scope="request" value="Themes"/>
                <c:set var="yLabel" scope="request" value="Count"/>
                <c:choose>
                    <c:when test="${hasPieChartInGraphicalResult == 'true'}">
                    <div id="barChartRepresentation" class="span11">
                        <c:import url="graph/bar-chart-representation.jsp"/>
                    </div>
                    </c:when>
                    <c:otherwise>
                    <div id="barChartRepresentation" class="span16">
                        <c:import url="graph/bar-chart-representation.jsp"/>
                    </div>
                    </c:otherwise>
                </c:choose>
                </div><!--div class="row" id="graphical-synthesis-result"-->
                </c:if>