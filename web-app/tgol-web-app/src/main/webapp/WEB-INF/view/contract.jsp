<%@taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html>
<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<c:choose>
    <c:when test="${fn:contains(pageContext.response.locale, '_')}">
        <c:set var="lang">
            ${fn:substringBefore(pageContext.response.locale, "_")}
        </c:set>
    </c:when>
    <c:otherwise>
        <c:set var="lang" value="${pageContext.response.locale}"/>
    </c:otherwise>
</c:choose>
<c:set var="imgName" scope="request">
    <fmt:message key="contract.historicSampleImgName"/>
</c:set>
<c:choose>
    <c:when test="${not empty configProperties['cdnUrl']}">
        <c:set var="gearImgUrl" value="${configProperties['cdnUrl']}/Images/gear.png"/>
        <c:set var="historicSampleImgUrl" value="${configProperties['cdnUrl']}/Images/${imgName}"/>
    </c:when>
    <c:otherwise>
        <c:set var="gearImgUrl">
            <c:url value="/Images/gear.png"/>  
        </c:set>
        <c:set var="historicSampleImgUrl">
            <c:url value="/Images/${imgName}"/>
        </c:set>
    </c:otherwise>
</c:choose>
<html lang="${lang}">
    <c:set var="pageTitle" scope="page">
        <fmt:message key="contract.pageTitle">
            <fmt:message>
                ${detailedContractInfo.label}
            </fmt:message>
        </fmt:message>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="tgm-project">
        <%@include file="template/header-utils.jsp" %>
        <div class="container">
            <c:set var="pageName" scope="page" value="${detailedContractInfo.label}"/>
            <ul class="breadcrumb">
                <li><a href="<c:url value="/home.html"/>"><fmt:message key="home.h1"/></a> <span class="divider"></span></li>
                <li class="active">${pageName}</li>
            </ul>
            <div class="row">
                <div class="span16">
                    <h1>
                        ${detailedContractInfo.label}
                        <c:if test="${detailedContractInfo.isActRunning == 'true'}">
                        <img src="${gearImgUrl}" title="<fmt:message key="home.actRunning"/>" alt="<fmt:message key="home.actRunning"/>" class="running-audit"/>
                        </c:if>
                    </h1>
                </div><!-- class="span16" -->
            </div><!-- class="row" -->
            <div id="myproject" class="row">
                <c:set var="url" scope="page" value="${detailedContractInfo.url}"/>
                <c:set var="contract" scope="page" value="${detailedContractInfo}"/>
                <c:set var="proportion" scope="page" value="span4"/>
                <c:set var="offset" scope="page" value="offset0"/>
                <c:set var="size" scope="page" value="S"/>
                <%@include file="template/thumbnail.jsp" %>
                <div class="span8 offset1">
                    <div class="project-meta-info">
                        <c:if test="${detailedContractInfo.url != ''}">
                        <div class="project-url"><a href="${detailedContractInfo.url}">${detailedContractInfo.url}</a></div>
                        </c:if>
                        <div class="project-creation-date"><fmt:message key="contract.createdOn"/> : <fmt:formatDate type="date" value="${detailedContractInfo.contractCreationDate}" dateStyle="long"/></div>
                        <c:choose>
                            <c:when test="${isContractExpired == 'true'}">
                            <div class="project-expiration"><fmt:message key="home.projectHasExpired"/></div>
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
                                    <c:when test="${contractAction.actionEnabled == 'false'}">
                                        <c:choose>
                                            <c:when test="${not empty configProperties['cdnUrl']}">
                                                <c:set var="contractActionImgUrl" value="${configProperties['cdnUrl']}${contractAction.disabledActionImageUrl}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:set var="contractActionImgUrl">
                                                    <c:url value="${contractAction.disabledActionImageUrl}"/>
                                                </c:set>
                                            </c:otherwise>
                                        </c:choose>
                                        <img src="${contractActionImgUrl}" alt="" /><br/>
                                        <fmt:message key="${contractAction.actionI81NCode}"/> (<fmt:message key="contract.disabled"/>)
                                    </c:when>
                                    <c:otherwise>
                                        <c:choose>
                                            <c:when test="${not empty configProperties['cdnUrl']}">
                                                <c:set var="contractActionImgUrl" value="${configProperties['cdnUrl']}${contractAction.enabledActionImageUrl}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:set var="contractActionImgUrl">
                                                    <c:url value="${contractAction.enabledActionImageUrl}"/>
                                                </c:set>
                                            </c:otherwise>
                                        </c:choose>
                                        <a href="<c:url value="${contractAction.actionUrl}?cr=${contractIdValue}"/>">
                                            <img src="${contractActionImgUrl}" alt="" /><br/>
                                            <fmt:message key="${contractAction.actionI81NCode}"/>
                                        </a>
                                    </c:otherwise>
                                </c:choose>
                                    </div><!-- class="action-button"-->
                                </td>
                            </c:forEach>
                            </tr>
                        </table>
                            </c:otherwise>
                        </c:choose>
                    </div><!-- class="project-meta-info" -->
                </div><!-- class="span8 offset1" -->
                <c:if test="${detailedContractInfo.lastActInfo != null && detailedContractInfo.url != ''}">
                    <c:choose>
                    <c:when test="${detailedContractInfo.lastActInfo.status == 'COMPLETED'}">
                        <c:set var="mark" scope="page" value="${detailedContractInfo.lastActInfo.rawMark}"/>
                        <c:set var="scoreClass" scope="page" value="project-score"/>
                        <c:set var="displayWeightedMark" scope="page" value="false"/>
                        <c:set var="hasProgressInfo" scope="page" value="true"/>
                        <c:set var="progressValue" scope="page" value="${detailedContractInfo.siteAuditProgression}"/>
                        <c:set var="hasScoreFormulaLink" scope="page" value="false"/>
                        <c:set var="addSpanToDiv" scope="page" value="true"/>
                        <%@include file="template/score.jsp" %>
                    </c:when>
                    <c:otherwise>
                <div class="span3">
                    <div class="project-fail">
                        <fmt:message key="${detailedContractInfo.lastActInfo.status}"/>
                    </div>
                </div><!-- class="span3" -->
                    </c:otherwise>
                    </c:choose>
                </c:if>
            </div><!-- class="row" -->
            <c:if test="${displayResultTrend == 'true'}">
            <div class="row">
                <div class="span15 offset1">
                    <c:choose>
                        <c:when test="${fn:length(detailedContractInfo.siteActInfoSet) > 1}">
                        <c:set var="actSet" scope="request" value="${detailedContractInfo.siteActInfoSet}"/>
                        <c:set var="width" scope="request" value="850"/>
                        <c:set var="height" scope="request" value="350"/>
                    <c:import url="graph/time-series-representation.jsp"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="imgName" scope="request">
                                <fmt:message key="contract.historicSampleImgName"/>
                            </c:set>
                    <img id="site-audit-history" src="${historicSampleImgUrl}" alt="<fmt:message key="contract.historicSampleImgAltAndTitle"/>" title="<fmt:message key="contract.historicSampleImgAltAndTitle"/>"/>
                        </c:otherwise>
                    </c:choose>
                </div><!-- class="span15 offset1" -->
            </div><!-- class="row" -->
            </c:if>
            <div id="contract-last-audit" class="row">
                <div class="span16 offset0">
                    <h2>
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
                </div><!-- class="span16 offset0" -->
            </div><!-- class="row" -->
            <c:if test='${not empty detailedContractInfo.lastActInfoSet}'>
            <div class="row">
                <div id="act-list" class="span15 offset1">
                    <table id="act-list-table" class="zebra-striped" summary="<fmt:message key="contract.auditHistory"/>">
                        <caption><fmt:message key="contract.auditHistory"/></caption>
                        <thead>
                            <tr>
                                <th id="page-url" scope="col" class="col01"><fmt:message key="contract.pageUrl"/></th>
                                <th id="date" scope="col" class="col02"><fmt:message key="contract.date"/></th>
                                <th id="raw-mark" scope="col" class="col03"><fmt:message key="contract.rawMark"/></th>
                                <th id="referential" scope="col" class="col04"><fmt:message key="referential"/></th>
                                <th id="scope" scope="col" class="col05"><fmt:message key="contract.scope"/></th>
                                <th id="status" scope="col" class="col06"><fmt:message key="contract.status"/></th>
                                <th id="result-page-url" scope="col" class="col07"><fmt:message key="pageList.pageDetailedResult"/></th>
                            </tr>
                        </thead>
                        <c:if test="${fn:length(detailedContractInfo.lastActInfoSet) > 0}">
                        <tbody>
                        </c:if>
                        <c:forEach var="actInfo" items="${detailedContractInfo.lastActInfoSet}" varStatus="pContractSet">
                            <tr>
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
                                            <c:set var="addSpanToDiv" scope="page" value="false"/>
                                     <%@include file="template/score.jsp" %>
                                        </c:when>
                                        <c:otherwise>
                                     <div> -- </div>
                                            </c:otherwise>
                                    </c:choose>
                                </td>
                                <td headers="referential" class="col04">
                                    <fmt:message key="${actInfo.referential}"/>
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
                        <c:if test="${fn:length(detailedContractInfo.lastActInfoSet) > 0}">
                        </tbody>
                        </c:if>
                    </table>
                </div><!-- class="span15 offset1" -->
            </div><!-- class="row" -->
            </c:if>
        </div><!-- class="container"-->
        <%@include file="template/footer.jsp" %>
    </body>
</html>
</compress:html>