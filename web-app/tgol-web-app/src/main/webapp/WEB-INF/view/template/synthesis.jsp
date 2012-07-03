<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

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
                    <c:set var="proportion" scope="page" value="span4"/>
                    <c:set var="offset" scope="page" value="offset0"/>
                    <c:set var="size" scope="page" value="S"/>
                    <%@include file="../template/thumbnail.jsp" %>
                    <div id="synthesis-meta-data" class="span8 offset1">
                        <div class="project-url">
                    <c:choose>
                        <c:when test="${statistics.auditScope == 'GROUPOFFILES' || statistics.auditScope == 'FILE'}">
                            ${statistics.url}
                        </c:when>
                        <c:when test="${statistics.auditScope == 'SCENARIO'}">
                            Scenario <strong>${statistics.url}</strong>
                        </c:when>
                        <c:otherwise>
                            <a href="${statistics.url}">${statistics.url}</a>
                        </c:otherwise>
                    </c:choose>
                        </div>
                        <c:if test="${addLinkToSourceCode == 'true'}">
                        <c:set var="sourceCodeLinkTitle" scope="page">
                            <fmt:message key="resultPage.sourceCodeLinkTitle">
                                <fmt:param>
                                    ${statistics.url}
                                </fmt:param>
                            </fmt:message>
                        </c:set>
                        <div class="page-source-code-link">    
                            <a href="<c:url value="/home/contract/source-code-page.html?wr=${param.wr}"/>" title="${sourceCodeLinkTitle}" target="_blank">
                                <fmt:message key="resultPage.sourceCodeLink"/>
                            </a>
                        </div>
                        </c:if>
                        <div class="project-creation-date">
                            <fmt:message key="contract.createdOn"/> : <fmt:formatDate type="date" value="${statistics.date}" dateStyle="long"/> <fmt:formatDate type="time" value="${statistics.date}"/>
                        </div>
                        <c:if test="${hasPageCounter == 'true'}">
                        <div class="audit-nb-of-pages">
                        <fmt:message key="resultPage.pageCounter">
                            <fmt:param value="${statistics.pageCounter}"/>
                        </fmt:message>
                        </div>
                        </c:if>
                        <c:if test="${fn:length(statistics.parametersMap) != 0}">
                        <div class="project-parameters">
                            <p class="_toggle-master-display-parameters"><fmt:message key="auditSetUp.formTitle"/></p>
                            <ul class="_toggle-display-parameters">
                            <c:forEach var="entry" items="${statistics.parametersMap}">
                                <li>
                                    <span class="project-parameters-label"><spring:message code="${entry.key}"/> : </span>
                                    <span class="project-parameters-value">
                                    <c:catch var="jspTagException" >
                                        <spring:message code="${entry.value}"/>
                                    </c:catch>
                                    <c:if test = "${jspTagException != null}">
                                        ${entry.value}
                                    </c:if>
                                    </span>
                                </li>
                            </c:forEach>
                            </ul>
                        </div><!-- class="project-parameters" -->
                        </c:if>
                    </div><!-- class="span6 offset2" -->
                    <div id="synthesis-score" class="span3">
                        <c:set var="mark" scope="page" value="${statistics.rawMark}"/>
                        <c:set var="weightedMark" scope="page" value="${statistics.weightedMark}"/>
                        <c:set var="scoreClass" scope="page" value="audit-score"/>
                        <c:set var="weightedScoreClass" scope="page" value="audit-weighted-score"/>
                        <c:set var="displayWeightedMark" scope="page" value="false"/>
                        <c:set var="hasScoreFormulaLink" scope="page" value="true"/>
                        <%@include file="../template/score.jsp" %>
                        <c:if test="${hasPagesListLink == 'true'}">
                        <ul id="synthesis-action-list">
                            <c:if test="${hasSiteScopeTest == 'true'}">
                            <li><a href="<c:url value="/home/contract/audit-result.html?wr=${wr}"/>" class="large awesome blue cmt"><spring:message code="synthesisSite.siteResults"/></a></li>
                            </c:if>
                            <c:choose>
                                <c:when test="${statistics.auditScope == 'SCENARIO'}">
                                    <li><a href="<c:url value="/home/contract/page-list.html?wr=${wr}&amp;status=f2xx&amp;sortDirection=1&amp;sortCriterion=rank"/>" class="large awesome blue cmt"><spring:message code="synthesisSite.pageList"/></a></li>
                                </c:when>
                                <c:when test="${statistics.auditScope == 'DOMAIN'}">
                                    <li><a href="<c:url value="/home/contract/page-list.html?wr=${wr}"/>" class="large awesome blue cmt"><spring:message code="synthesisSite.pageList"/></a></li>
                                </c:when>
                            </c:choose>
                        </ul>
                        </c:if>
                    </div>
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
                    <div class="span4">
                        <c:set var="counter" scope="request" value="${statistics.resultCounter}"/>
                        <c:import url="template/pie-chart.jsp"/>
                    </div><!---class="span3"-->
                    </c:if>
                <c:set var="counterByThemeMap" scope="request" value="${statistics.counterByThemeMap}"/>
                <c:set var="width" scope="request" value="${themeRepartitionWidth}"/>
                <c:set var="height" scope="request" value="250"/>
                <c:set var="xLabel" scope="request" value="Themes"/>
                <c:set var="yLabel" scope="request" value="Count"/>
                <c:choose>
                    <c:when test="${hasPieChartInGraphicalResult == 'true'}">
                    <div id="barChartRepresentation" class="span12">
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