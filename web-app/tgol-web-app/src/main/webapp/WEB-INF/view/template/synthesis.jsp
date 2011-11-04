<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

                <c:if test="${hasSynthesisTitle == 'true'}">
                <div class="yui3-g tgm-grid">
                    <div class="yui3-u-1">
                        <h2 id="synthesis" class="cmr cml"><fmt:message key="resultPage.synthesis"/></h2>
                    </div>
                </div>
                </c:if>
                <div class="yui3-g tgm-grid" id="synthesis-result">
                    <c:set var="url" scope="page" value="${statistics.url}"/>
                    <c:set var="scope" scope="page" value="${statistics.auditScope}"/>
                    <c:set var="proportion" scope="page" value="3"/>
                    <c:set var="size" scope="page" value="S"/>
                    <%@include file="../template/thumbnail.jsp" %>
                    <c:choose>
                    <c:when test="${hasPagesListLink == 'true'}">
                    <div class="yui3-u-1-3">
                    </c:when>
                    <c:otherwise>
                    <div class="yui3-u-1-3">
                    </c:otherwise>
                    </c:choose>
                        <c:set var="mark" scope="page" value="${statistics.rawMark}"/>
                        <c:set var="weightedMark" scope="page" value="${statistics.weightedMark}"/>
                        <c:set var="scoreClass" scope="page" value="audit-score"/>
                        <c:set var="weightedScoreClass" scope="page" value="audit-weighted-score"/>
                        <c:set var="displayWeightedMark" scope="page" value="true"/>
                        <c:set var="hasScoreFormulaLink" scope="page" value="true"/>
                        <%@include file="../template/score.jsp" %>
                        <c:if test="${hasPageCounter == 'true'}">
                        <div class="audit-nb-of-pages">
                        <fmt:message key="resultPage.pageCounter">
                            <fmt:param value="${statistics.pageCounter}"/>
                        </fmt:message>
                        </div>
                        </c:if>
                        <div class="project-url">
                    <c:choose>
                        <c:when test="${statistics.auditScope == 'GROUPOFFILES' || statistics.auditScope == 'FILE'}">
                            ${statistics.url}
                        </c:when>
                        <c:otherwise>
                            <a href="${statistics.url}">${statistics.url}</a>
                        </c:otherwise>
                    </c:choose>
                        </div>
                        <div class="project-creation-date"><fmt:message key="contract.createdOn"/> : <fmt:formatDate type="date" value="${statistics.date}" dateStyle="long"/> <fmt:formatDate type="time" value="${statistics.date}"/></div>
                        <c:if test="${fn:length(statistics.parametersMap) != 0}">
                        <div class="project-parameters">
                            <p class="_toggle-master-display-parameters"><fmt:message key="auditSetUp.formTitle"/></p>
                            <c:forEach var="entry" items="${statistics.parametersMap}">
                                <p class="_toggle-display-parameters project-parameters-label"><spring:message code="${entry.key}"/> :
                                <span class="project-parameters-value">
                                <c:catch var="jspTagException" >
                                    <spring:message code="${entry.value}"/>
                                </c:catch>
                                <c:if test = "${jspTagException != null}">
                                    ${entry.value}
                                </c:if>
                                </span>
                            </p>
                            </c:forEach>
                        </div><!-- class="project-parameters" -->
                        </c:if>
                    </div><!-- class="yui3-u-1-3" -->
                    <c:if test="${hasPagesListLink == 'true'}">
                    <div class="yui3-u-1-3">
                        <ul id="synthesis-action-list" class="cmr">
                            <c:if test="${hasSiteScopeTest == 'true'}">
                            <li><a href="<c:url value="/home/contract/audit-result.html?wr=${wr}"/>" class="large awesome blue cmt"><spring:message code="synthesisSite.siteResults"/></a></li>
                            </c:if>
                            <li><a href="<c:url value="/home/contract/page-list.html?wr=${wr}"/>" class="large awesome blue cmt"><spring:message code="synthesisSite.pageList"/></a></li>
                        </ul>
                    </div>
                    </c:if>
                    <c:if test="${resultActionList != null && not empty resultActionList}">
                    <div class="yui3-u-1-3">
                        <div id="audit-result-actions">
                            <ul>
                            <c:forEach var="action" items="${resultActionList}" varStatus="pAction">
                                <li>
                                <c:choose>
                                    <c:when test="${action.isActionEnabled == 'false'}">
                                        <img src="<c:url value="${action.disabledActionImageUrl}"/>" alt="" />
                                        <fmt:message key="${action.actionI81NCode}"/> (<fmt:message key="contract.disabled"/>)
                                    </c:when>
                                    <c:otherwise>
                                    <!-- the url actions already have a parameter (export type for example-->
                                        <a href="<c:url value="${action.actionUrl}&amp;wr=${param.wr}"/>">
                                        <img src="<c:url value="${action.enabledActionImageUrl}"/>" alt="" />
                                        <fmt:message key="${action.actionI81NCode}"/>
                                        </a>
                                        </c:otherwise>
                                    </c:choose>
                                </li>
                           </c:forEach>
                           </ul>
                       </div><!--  id="audit-result-actions" -->
                    </div>
                    </c:if>
                </div> <!-- class="yui3-g tgm-grid" id="synthesis-result" -->
                <c:if test="${hasResultDispatchTitle == 'true'}">
                <div class="yui3-u-1">
                    <h2 id="result-dispatch" class="cmr cml"><spring:message code="synthesisSite.resultDispatch"/></h2>
                </div>
                </c:if>
                <c:if test="${hasGraphics == 'true'}">
                <div class="yui3-g tgm-grid" id="graphical-synthesis-result">
                    <c:set var="counter" scope="request" value="${statistics.resultCounter}"/>
                    <div class="yui3-u-1-4">
                        <div id="tgm-piechart">
                            <c:set var="hasTableAlternative" scope="request" value="false"/>
                            <c:set var="width" scope="request" value="100"/>
                            <c:set var="height" scope="request" value="134"/>
                            <c:import url="graph/pie-representation.jsp"/>
                        </div>
                        <div>
                            <table summary="<fmt:message key="graph.resultRepartitionSummaryAndCaption"/>" id="result-synthetized-text">
                                <caption><fmt:message key="graph.resultRepartitionSummaryAndCaption"/></caption>
                                <tr>
                                    <th class="col01 passed" scope="row">Passed</th>
                                    <td class="col02">${counter.passedCount}</td>
                                </tr>
                                <tr>
                                    <th class="col01 failed" scope="row">Failed</th>
                                    <td class="col02">${counter.failedCount}</td>
                                </tr>
                                <tr>
                                    <th class="col01 nmi" scope="row">
                                    <abbr title="Need More Information">NMI</abbr>
                                </th>
                                    <td class="col02">${counter.nmiCount}</td>
                                </tr>
                                <tr>
                                    <th class="col01 na" scope="row">
                                        <abbr title="Not Applicable">NA</abbr>
                                    </th>
                                    <td class="col02">${counter.naCount}</td>
                                </tr>
                            </table>
                        </div>
                    </div><!---div class="yui3-u-1-4"-->
                
                <c:set var="counterByThemeMap" scope="request" value="${statistics.counterByThemeMap}"/>
                <c:set var="width" scope="request" value="700"/>
                <c:set var="height" scope="request" value="200"/>
                <c:set var="xLabel" scope="request" value="Themes"/>
                <c:set var="yLabel" scope="request" value="Count"/>
                    <div id="barChartRepresentation" class="yui3-u-3-4">
                        <c:import url="graph/bar-chart-representation.jsp"/>
                    </div>
                </div><!--div class="yui3-g tgm-grid" id="graphical-synthesis-result"-->
                </c:if>