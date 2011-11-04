<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

                <c:if test="${testResultMap != null and not empty testResultMap}">
                <h2 id="work-done" class="cmr cml">
                    <c:choose>
                        <c:when test="${scope == 'page'}">
                    <fmt:message key="resultPage.detailedResultPage"/>
                        </c:when>
                        <c:when test="${scope == 'site'}">
                    <fmt:message key="resultPage.detailedResultSite"/>
                        </c:when>
                    </c:choose>
                </h2>
                </c:if>

                <c:forEach var="entry" items="${testResultMap}" varStatus="pResultMap">
                <h3 class="theme cmr cml" id="theme${entry.key.rank}">Theme ${entry.key.rank} : <fmt:message key="${entry.key.code}"/></h3>
                    <table id="result-table-theme${pResultMap.index}" class="result-table">
                    <c:forEach var="testResult" items="${entry.value}" varStatus="pTestResultList">
                        <c:choose>
                            <c:when test="${pTestResultList.index % 2 == 1}">
                        <tr class="test-result-compact row-odd">
                            </c:when>
                            <c:otherwise>
                        <tr class="test-result-compact row-even">
                            </c:otherwise>
                        </c:choose>
                            <td class="rule-id">
                                <h4>${testResult.testShortLabel}</h4>
                            </td>
                            <td class="rule-result ${testResult.resultCode}">
                            <c:choose>
                                <c:when test="${testResult.resultCode == 'failed' && testResult.resultCounter.failedCount > 0}">
                                <fmt:message key="${testResult.resultCode}"/> (<acronym title="${testResult.resultCounter.failedCount} <fmt:message key="resultPage.occurrences"/>">${testResult.resultCounter.failedCount}</acronym>)
                                </c:when>
                                <c:otherwise>
                                <fmt:message key="${testResult.resultCode}"/>
                                </c:otherwise>
                            </c:choose>
                            </td>
                            <td class="rule-label">
                                <fmt:message key="${testResult.testCode}"/>
                            </td>
                            <td class="rule-level">
                                <fmt:message key="${testResult.levelCode}"/>
                            </td>
                            <td class="rule-details">
                            <c:choose>
                                <c:when test="${0 == testResult.elementCounter && empty testResult.remarkInfosList}">
                                <a href="#r${testResult.testShortLabel}-detailed" title="<fmt:message key="resultPage.infosOn"/> ${testResult.testShortLabel}"><fmt:message key="resultPage.infos"/></a>
                                </c:when>
                                <c:otherwise>
                                <a href="#r${testResult.testShortLabel}-detailed" title="<fmt:message key="resultPage.detailsOn"/> ${testResult.testShortLabel}"><fmt:message key="resultPage.details"/></a>
                                </c:otherwise>
                            </c:choose>
                            </td>
                        </tr>
                        <tr class="test-result-detailed" id="r${testResult.testShortLabel}-detailed">
                            <td colspan="4">
                            <p class="more-on-test">
                                <a href="<fmt:message key="${testResult.testCode}-url"/>">
                                    <fmt:message key="resultPage.more"/> ${testResult.testShortLabel}
                                </a> <br/>
                                <c:if test="${displayAlgorithm == 'true'}">
                                <a href="${testResult.ruleDesignUrl}">
                                    <fmt:message key="resultPage.ruleDesignUrl"/> ${testResult.testShortLabel}
                                </a>
                                </c:if>
                            </p>
                            <c:if test='${0 != testResult.elementCounter}'>
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
                            </td>
                        </tr>
                    </c:forEach>
                    </table>
                </c:forEach>