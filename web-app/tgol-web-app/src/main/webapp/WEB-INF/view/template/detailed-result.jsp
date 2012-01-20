<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
            <c:choose>
                <c:when test="${testResultMap != null and not empty testResultMap}">
                <div class="row">
                    <div class="span16">
                        <h2 id="work-done">
                            <c:choose>
                                <c:when test="${scope == 'page'}">
                            <fmt:message key="resultPage.detailedResultPage"/>
                                </c:when>
                                <c:when test="${scope == 'site'}">
                            <fmt:message key="resultPage.detailedResultSite"/>
                                </c:when>
                            </c:choose>
                        </h2>
                    </div><!-- class="span16" -->
                </div><!-- class="row" -->
                <c:forEach var="entry" items="${testResultMap}" varStatus="pResultMap">
                <div class="row">
                    <div class="span16">
                        <h3 id="theme${entry.key.rank}" class="theme">
                            Theme ${entry.key.rank} : <fmt:message key="${entry.key.code}"/>
                        </h3>
                    </div><!-- class="span16" -->
                </div><!-- class="row" -->
                    <c:forEach var="testResult" items="${entry.value}" varStatus="pTestResultList">
                        <c:choose>
                            <c:when test="${pTestResultList.index % 2 == 1}">
                                <c:set var="rowBgClass" value="even"/>
                            </c:when>
                            <c:otherwise>
                                <c:set var="rowBgClass" value="odd"/>
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
                <div class="row test-result-compact">
                    <div class="span15 offset1 ${ruleGeneralResultClass} ${rowBgClass}">
                        <div class="row test-result-compact">
                            <div class="span1 rule-id ">
                                <h4>${testResult.testShortLabel}</h4>
                            </div><!-- class="span1 rule-id" -->
                            <div class="span2 rule-result ${testResult.resultCode} ${rowBgClass}">
                            <c:choose>
                                <c:when test="${testResult.resultCode == 'failed' && testResult.resultCounter.failedCount > 0}">
                                <fmt:message key="${testResult.resultCode}"/> (<abbr title="${testResult.resultCounter.failedCount} <fmt:message key="resultPage.occurrences"/>">${testResult.resultCounter.failedCount}</abbr>)
                                </c:when>
                                <c:otherwise>
                                <fmt:message key="${testResult.resultCode}"/>
                                </c:otherwise>
                            </c:choose>
                            </div><!-- class="span2 rule-result" -->
                            <div class="span9 rule-label">
                                <fmt:message key="${testResult.testCode}"/>
                            </div><!-- class="span9 rule-label" -->
                            <div class="span1 rule-level">
                                <fmt:message key="${testResult.levelCode}"/>
                            </div><!-- class="span1 rule-level" -->
                            <div class="span1 rule-details">
                            <c:choose>
                                <c:when test="${0 == testResult.elementCounter && empty testResult.remarkInfosList}">
                                <a href="#r${testResult.testShortLabel}-detailed" class="test-result-compact" title="<fmt:message key="resultPage.infosOn"/> ${testResult.testShortLabel}"><fmt:message key="resultPage.infos"/></a>
                                </c:when>
                                <c:otherwise>
                                <a href="#r${testResult.testShortLabel}-detailed" class="test-result-compact" title="<fmt:message key="resultPage.detailsOn"/> ${testResult.testShortLabel}"><fmt:message key="resultPage.details"/></a>
                                </c:otherwise>
                            </c:choose>
                            </div><!-- class="span1 rule-details" -->
                        </div><!-- class="row" -->
                    </div><!-- class="span15 offset1 rule-general-result rowBgClass " -->
                </div><!-- class="row" -->
                <div id="r${testResult.testShortLabel}-detailed" class="row test-result-detailed">
                    <c:set var="moreOnTestOffset" value="offset12"/>
                    <c:if test='${0 != testResult.elementCounter}'>
                    <c:set var="moreOnTestOffset" value="offset7"/>
                    <div class="span4 offset1">
                        <ul class="counter-remarks">
                            <li>
                                <strong>${testResult.elementCounter}</strong> <fmt:message key="resultPage.testedElements"/>
                            </li>
                            <c:if test='${testResult.resultCounter.failedCount > 0}'>
                            <li>
                            <c:choose>
                                <c:when test="${testResult.testRepresentation  != 'data-representation/data-representation-1.jsp'}">
                                <a href="#${testResult.testCode}failed0"><strong>${testResult.resultCounter.failedCount}</strong> <fmt:message key="resultPage.failedElements"/></a>
                                </c:when>
                                <c:otherwise>
                                <strong>${testResult.resultCounter.failedCount}</strong> <fmt:message key="resultPage.failedElements"/>
                                </c:otherwise>
                            </c:choose>
                            </li>
                            </c:if>
                            <c:if test='${testResult.resultCounter.nmiCount > 0}'>
                            <li>
                            <c:choose>
                                <c:when test="${testResult.testRepresentation  != 'data-representation/data-representation-1.jsp'}">
                                <a href="#${testResult.testCode}nmi0"><strong>${testResult.resultCounter.nmiCount}</strong> <fmt:message key="resultPage.nmiElements"/></a>
                                </c:when>
                                <c:otherwise>
                                <strong>${testResult.resultCounter.nmiCount}</strong> <fmt:message key="resultPage.nmiElements"/>
                                </c:otherwise>
                            </c:choose>
                            </li>
                            </c:if>
                            <c:if test='${testResult.resultCounter.suspectedPassedCount > 0}'>
                            <li>
                                <strong>${testResult.resultCounter.suspectedPassedCount}</strong> <fmt:message key="resultPage.nmiSuspectedPassedElements"/>
                            </li>
                            </c:if>
                            <c:if test='${testResult.resultCounter.suspectedFailedCount > 0}'>
                            <li>
                                <strong>${testResult.resultCounter.suspectedFailedCount}</strong> <fmt:message key="resultPage.nmiSuspectedFailedElements"/>
                            </li>
                            </c:if>
                        </ul>
                    </div><!-- class="span3 offset1" -->
                    </c:if>
                    <div class="span4 ${moreOnTestOffset} more-on-test">
                        <a href="<fmt:message key="${testResult.testCode}-url"/>">
                            <fmt:message key="resultPage.more"/> ${testResult.testShortLabel}
                        </a> <br/>
                        <c:if test="${displayAlgorithm == 'true'}">
                        <a href="${testResult.ruleDesignUrl}">
                            <fmt:message key="resultPage.ruleDesignUrl"/> ${testResult.testShortLabel}
                        </a>
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
                    </c:if>
                </div>
                    </c:forEach>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <div class="row">
                    <div class="span16-u"
                        <h2 id="work-done">
                            <fmt:message key="resultPage.noResults"/>
                        </h2>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>