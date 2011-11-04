<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="${pageContext.response.locale}">
    <c:set var="pageTitle" scope="page">
        <fmt:message key="contract.pageTitle">
            <fmt:message>
                ${detailedContractInfo.label}
            </fmt:message>
        </fmt:message>
    </c:set>
    <c:set var="addWebSnapr" scope="page" value="true"/>
    <%@include file="template/head.jsp" %>
    <body id="tgm-project" class="tgm">
        <div id="meta-border">
            <%@include file="template/header-utils.jsp" %>
            <c:set var="pageName" scope="page" value="${detailedContractInfo.label}"/>
            <%@include file="template/breadcrumb.jsp" %>
            <div class="yui3-g">
                <div class="yui3-u-1">
                    <h1>${detailedContractInfo.label}</h1>
                </div><!-- class="yui3-u-1" -->
            </div><!-- class="yui3-g" -->

            <div class="yui3-g" id="myproject">
                <c:set var="url" scope="page" value="${detailedContractInfo.url}"/>
                <c:set var="contract" scope="page" value="${detailedContractInfo}"/>
                <c:set var="proportion" scope="page" value="4"/>
                <c:set var="size" scope="page" value="S"/>
                <c:set var="cml" scope="page" value="cml"/>
                <%@include file="template/thumbnail.jsp" %>
                <div class="yui3-u-1-2">
                    <div class="project-meta-info">
                        <c:if test="${detailedContractInfo.url != ''}">
                        <div class="project-url"><a href="${detailedContractInfo.url}">${detailedContractInfo.url}</a></div>
                        </c:if>
                        <div class="project-creation-date"><fmt:message key="contract.createdOn"/> : <fmt:formatDate type="date" value="${detailedContractInfo.contractCreationDate}" dateStyle="long"/></div>
                        <c:choose>
                            <c:when test="${isContractExpired == 'true'}">
                                <div class="project-expiration"><fmt:message key="home.projectHasExpired"/></div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${detailedContractInfo.lastActInfo != null && detailedContractInfo.url != ''}">
                                <div class="project-status"><fmt:message key="home.lastAudit"/> : <fmt:formatDate type="date" value="${detailedContractInfo.lastActInfo.date}" dateStyle="long"/> <fmt:message key="home.at"/> <fmt:formatDate type="time" value="${detailedContractInfo.lastActInfo.date}"/></div>
                            </c:if>
                            <table id="audit-actions" summary="" class="link-underline">
                                <tr>
                                    <c:forEach var="contractAction" items="${contractActionList}" varStatus="pContractAction">
                                    <td>
                                        <div class="action-button">
                                        <c:choose>
                                            <c:when test="${contractAction.isActionEnabled == 'false'}">
                                                <img src="<c:url value="${contractAction.disabledActionImageUrl}"/>" alt="" /><br/>
                                                <fmt:message key="${contractAction.actionI81NCode}"/> (<fmt:message key="contract.disabled"/>)
                                            </c:when>
                                            <c:otherwise>
                                                <a href="<c:url value="${contractAction.actionUrl}?cr=${contractIdValue}"/>">
                                                    <img src="<c:url value="${contractAction.enabledActionImageUrl}"/>" alt="" /><br/>
                                                    <fmt:message key="${contractAction.actionI81NCode}"/>
                                                </a>
                                            </c:otherwise>
                                        </c:choose>
                                        </div>
                                    </td>
                                    </c:forEach>
                                </tr>
                            </table>
                        </div>
                    </div><!-- class="yui3-u-5-8" -->
                    <div class="yui3-u-1-5 cmr">
                        <c:if test="${detailedContractInfo.lastActInfo != null && detailedContractInfo.url != ''}">
                            <c:choose>
                                <c:when test="${detailedContractInfo.lastActInfo.status == 'COMPLETED'}">
                                    <c:set var="mark" scope="page" value="${detailedContractInfo.lastActInfo.rawMark}"/>
                                    <c:set var="scoreClass" scope="page" value="project-score"/>
                                    <c:set var="displayWeightedMark" scope="page" value="false"/>
                                    <c:set var="hasProgressInfo" scope="page" value="true"/>
                                    <c:set var="progressValue" scope="page" value="${detailedContractInfo.siteAuditProgression}"/>
                                    <c:set var="hasScoreFormulaLink" scope="page" value="false"/>
                                    <%@include file="template/score.jsp" %>
                                </c:when>
                                <c:otherwise>
                                    <div class="project-fail">
                                        <fmt:message key="${detailedContractInfo.lastActInfo.status}"/>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                    </div><!-- class="yui3-u-1-8" -->
                    <div class="yui3-g">
                        <div class="yui3-u-1">
                            <h2 class="cml cmr">
                                <c:choose>
                                    <c:when test='${detailedContractInfo.numberOfDisplayedAct == 0}'>
                                        <fmt:message key="contract.noAudit">
                                            <fmt:param>${detailedContractInfo.numberOfDisplayedAct}</fmt:param>
                                        </fmt:message>
                                    </c:when>
                                    <c:when test='${detailedContractInfo.numberOfDisplayedAct == 1}'>
                                        <fmt:message key="contract.oneAudit">
                                            <fmt:param>${detailedContractInfo.numberOfDisplayedAct}</fmt:param>
                                        </fmt:message>
                                    </c:when>
                                    <c:when test='${detailedContractInfo.numberOfDisplayedAct > 1}'>
                                        <fmt:message key="contract.lastAudits">
                                            <fmt:param>${detailedContractInfo.numberOfDisplayedAct}</fmt:param>
                                        </fmt:message>
                                    </c:when>
                                </c:choose>
                            </h2>
                        </div><!-- class="yui3-u-1" -->
                    <c:if test='${not empty detailedContractInfo.lastActInfoSet}'>
                        <div id="act-list "class="yui3-u-1 cmr cml">
                            <table id="act-list-table" summary="<fmt:message key="contract.auditHistory"/>">
                                <caption><fmt:message key="contract.auditHistory"/></caption>
                                <tr>
                                    <th id="page-url" scope="col" class="col01"><fmt:message key="contract.pageUrl"/></th>
                                    <th id="date" scope="col" class="col02"><fmt:message key="contract.date"/></th>
                                    <th id="raw-mark" scope="col" class="col03"><fmt:message key="contract.rawMark"/></th>
                                    <th id="weighted-mark" scope="col" class="col04"><fmt:message key="contract.weightedMark"/></th>
                                    <th id="scope" scope="col" class="col05"><fmt:message key="contract.scope"/></th>
                                    <th id="status" scope="col" class="col06"><fmt:message key="contract.status"/></th>
                                    <th id="result-page-url" scope="col" class="col07"><fmt:message key="pageList.pageDetailedResult"/></th>
                                </tr>
                                <c:forEach var="actInfo" items="${detailedContractInfo.lastActInfoSet}" varStatus="pContractSet">
                                    <c:choose>
                                        <c:when test="${pContractSet.index % 2 == 1}">
                                <tr class="row-odd">
                                            </c:when>
                                            <c:otherwise>
                                <tr class="row-even">
                                                </c:otherwise>
                                            </c:choose>
                                    <td headers="page-url" class="col01">
                                        <c:choose>
                                        <c:when test="${actInfo.scope == 'GROUPOFFILES' || actInfo.scope == 'FILE'}">
                                            ${actInfo.url}
                                        </c:when>
                                        <c:otherwise>
                                            <a href="${actInfo.url}">${actInfo.url}</a>
                                        </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td headers="date" class="col02"><fmt:formatDate type="both" value="${actInfo.date}" dateStyle="short" timeStyle="short"/></td>
                                    <td headers="raw-mark" class="col03">
                                                <c:choose>
                                                    <c:when test="${actInfo.status == 'COMPLETED'}">
                                                        <c:set var="mark" scope="page" value="${actInfo.rawMark}"/>
                                                        <c:set var="scoreClass" scope="page" value=""/>
                                                        <c:set var="displayWeightedMark" scope="page" value="false"/>
                                                        <c:set var="hasProgressInfo" scope="page" value="false"/>
                                                        <%@include file="template/score.jsp" %>
                                                    </c:when>
                                                    <c:otherwise>
                                         <div> -- </div>
                                                    </c:otherwise>
                                                </c:choose>
                                     </td>
                                     <td headers="weighted-mark" class="col04">
                                                <c:choose>
                                                    <c:when test="${actInfo.status == 'COMPLETED'}">
                                                        <c:set var="mark" scope="page" value="${actInfo.weightedMark}"/>
                                                        <c:set var="scoreClass" scope="page" value=""/>
                                                        <c:set var="displayWeightedMark" scope="page" value="false"/>
                                         <%@include file="template/score.jsp" %>
                                                    </c:when>
                                                    <c:otherwise>
                                         <div> -- </div>
                                                    </c:otherwise>
                                                </c:choose>
                                     </td>
                                     <td headers="scope" class="col05"><fmt:message key="${actInfo.scope}"/></td>
                                     <td headers="status" class="col06"><fmt:message key="${actInfo.status}"/></td>
                                        <c:choose>
                                            <c:when test="${actInfo.scope == 'PAGE' || actInfo.scope == 'FILE'}">
                                                <c:set var="resultUrl" scope="page" value="/home/contract/audit-result.html?wr="/>
                                            </c:when>
                                            <c:when test="${actInfo.scope == 'GROUPOFPAGES' || actInfo.scope == 'GROUPOFFILES'}">
                                                <c:set var="resultUrl" scope="page" value="/home/contract/page-list.html?status=f2xx&amp;wr="/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:set var="resultUrl" scope="page" value="/home/contract/audit-synthesis.html?wr="/>
                                            </c:otherwise>
                                        </c:choose>
                                     <td headers="result-page-url" class="col07">
                                         <a href="<c:url value="${resultUrl}${actInfo.webresourceId}"/>" title="<fmt:message key="pageList.pageDetailedResult"></fmt:message> <fmt:message key="pageList.for"></fmt:message> ${actInfo.url}"><fmt:message key="pageList.pageDetailedResult"/></a>
                                     </td>
                                </tr>
                                    </c:forEach>
                            </table>
                        </div><!-- class="yui3-u-1" -->
                    </c:if>
                </div><!-- class="yui3-g" -->
                <c:if test="${displayResultTrend == 'true'}">
                <div class="yui3-g">
                    <div class="cml cmr">
                        <div class="yui3-u-1">
                            <c:choose>
                                <c:when test="${fn:length(detailedContractInfo.siteActInfoSet) > 1}">
                                <c:set var="actSet" scope="request" value="${detailedContractInfo.siteActInfoSet}"/>
                                <c:set var="width" scope="request" value="900"/>
                                <c:set var="height" scope="request" value="350"/>
                            <c:import url="graph/time-series-representation.jsp"/>
                                </c:when>
                                <c:otherwise>
                                    <c:set var="imgName" scope="request">
                                        <fmt:message key="contract.historicSampleImgName"/>
                                    </c:set>
                            <img id="site-audit-history" src="<c:url value="/Images/${imgName}"/>" alt="<fmt:message key="contract.historicSampleImgAltAndTitle"/>" title="<fmt:message key="contract.historicSampleImgAltAndTitle"/>"/>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
                </c:if>
                </c:otherwise>
            </c:choose>
            </div><!-- class="yui3-g" -->
        </div><!-- class="meta-border" -->
        <div class="yui3-g" id="ft">
            <div class="yui3-u">
                &copy; <a href="http://www.Open-S.com/">Open-S</a>
            </div>
        </div>
    </body>
</html>