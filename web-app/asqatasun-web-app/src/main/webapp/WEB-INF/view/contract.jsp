<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://tagutils" prefix="tg" %>
<!DOCTYPE html>

<c:set var="imgName" scope="request">
    <fmt:message key="contract.historicSampleImgName"/>
</c:set>

<!-- external js --> 
<c:set var="jqueryUrl">
    <c:url value="/External-Js/jquery-1.9.1.min.js"/>
</c:set>        
<c:set var="raphaelJsUrl" scope="request">
    <c:url value="/External-Js/r2d3.v2.min.js"/>
</c:set> 
<c:set var="d3JsUrl">
    <c:url value="/External-Js/d3.v3.min.js"/>
</c:set>
<c:set var="jqueryTableSorterUrl" scope="request">
    <c:url value="/External-Js/jquery.tablesorter.min.js"/>
</c:set> 

<!-- internal js --> 
<c:set var="trendChartJsUrl">
    <c:url value="/Js/trend/trend-chart-min.js"/>
</c:set>
<c:set var="trendChartIEJsUrl">
    <c:url value="/Js/ie/trend/trend-chart-ie-min.js"/>
</c:set>
<c:set var="contractScoreJsUrl">
    <c:url value="/Js/score/score-contract-min.js"/>
</c:set>
<c:set var="scoreJsUrl">
    <c:url value="/Js/score/score-min.js"/>
</c:set>
<c:set var="contractScoreIEJsUrl">
    <c:url value="/Js/ie/score/score-contract-ie-min.js"/>
</c:set>
<c:set var="scoreIEJsUrl">
    <c:url value="/Js/ie/score/score-ie-min.js"/>
</c:set>
<c:set var="accessibleTableSorterJsUrl" scope="page">
    <c:url value="/Js/table-sorter/accessible-table-sorter-min.js"/>
</c:set>

<!-- images --> 
<c:set var="gearImgUrl">
    <c:url value="/Images/gear.png"/>  
</c:set>
<c:set var="historicSampleImgUrl">
    <c:url value="/Images/${imgName}"/>
</c:set>
<c:set var="goToImgUrl">
    <c:url value="/Images/window-duplicate.png"/>
</c:set>

<html lang="${tg:lang(pageContext)}">
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
            <div id="audit-actions-container" class="row">
                <div class="span16">
                    <table id="audit-actions" class="link-underline">
                        <tr>
                            <!-- <div id="audit-actions">-->
                            <c:forEach var="contractAction" items="${detailedContractInfo.actionList}" varStatus="pContractAction">
                                <td class="action-button">
                                    <!-- <span class="action-button">-->
                                    <c:choose>
                                        <c:when test="${contractAction.actionEnabled == 'false' || isContractExpired == 'true'}">
                                            <c:set var="contractActionImgUrl">
                                                <c:url value="${contractAction.disabledActionImageUrl}"/>
                                            </c:set>
                                            <img src="${contractActionImgUrl}" alt="" />
                                            <span class="action-label"><fmt:message key="${contractAction.actionI81NCode}"/> <br/><span class="desactivated"><fmt:message key="contract.disabled"/></span></span>
                                            </c:when>
                                            <c:otherwise>
                                                <c:set var="contractActionImgUrl">
                                                    <c:url value="${contractAction.enabledActionImageUrl}"/>
                                                </c:set>
                                            <a href="<c:url value="${contractAction.actionUrl}?cr=${contractIdValue}"/>">
                                                <img src="${contractActionImgUrl}" alt="" />
                                                <span class="action-label"><fmt:message key="${contractAction.actionI81NCode}"/></span>
                                            </a>
                                        </c:otherwise>
                                    </c:choose>
                                    <!--                            </span>-->
                                </td>
                            </c:forEach>
                            <!--                        </div>-->
                        </tr>
                    </table>
                </div>
            </div>
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
                            <div id="project-url">
                                <span class="project-meta-title">Url : </span>
                                <a href="${detailedContractInfo.url}">${detailedContractInfo.url}</a>
                            </div>
                        </c:if>
                        <div id="project-expiration-date"><span class="project-meta-title"><fmt:message key="contract.expireOn"/> : </span><fmt:formatDate type="date" value="${detailedContractInfo.contractExpirationDate}" dateStyle="long"/></div>
                        <c:choose>
                            <c:when test="${isContractExpired == 'true'}">
                                <div class="project-expiration"><span class="project-meta-title"><fmt:message key="home.projectHasExpired"/></span></div>
                                </c:when>
                                <c:otherwise>
                                    <c:if test="${detailedContractInfo.lastActInfo != null && detailedContractInfo.url != ''}">
        <!--                            <div id="project-status"><span class="project-meta-info-label"><fmt:message key="home.lastAudit"/> : </span><fmt:formatDate type="date" value="${detailedContractInfo.lastActInfo.date}" dateStyle="long"/> <fmt:message key="home.at"/> <fmt:formatDate type="time" value="${detailedContractInfo.lastActInfo.date}"/></div>-->
                                </c:if>

                            </c:otherwise>
                        </c:choose>
                    </div><!-- class="project-meta-info" -->
                </div><!-- class="span8 offset1" -->
                <c:if test="${detailedContractInfo.lastActInfo != null}">
                    <c:choose>
                        <c:when test="${detailedContractInfo.lastActInfo.status == 'COMPLETED'}">
                            <c:set var="mark" scope="page" value="${detailedContractInfo.lastActInfo.rawMark}"/>
                            <c:set var="scoreId" scope="page" value="project-score"/>
                            <c:set var="hasScoreFormulaLink" scope="page" value="false"/>
                            <c:set var="spanClass" scope="page" value="span2"/>
                            <%@include file="template/score.jsp" %>
                        </c:when>
                        <c:otherwise>
                            <div class="span2">
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
                    <div class="span16">
                        <div id="holder-site-audit-history-graph"></div>
                        <!-- <div id="site-audit-history-graph-sample" style="display : none;">
                                <img id="site-audit-history" src="${historicSampleImgUrl}" alt="<fmt:message key="contract.historicSampleImgAltAndTitle"/>" title="<fmt:message key="contract.historicSampleImgAltAndTitle"/>"/>
                        </div>-->
                    </div>
                </div>
            </c:if>
            <div class="row">
                <div id="contract-last-audit" class="span16 tg-table-title">
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
                <c:if test='${not empty detailedContractInfo.lastActInfoSet}'>
                    <div id="act-list" class="span16 tg-table-container">
                        <table id="act-list-table" class="tg-table sortable-table">
                            <caption><fmt:message key="contract.auditHistory"/></caption>
                            <thead>
                                <tr>
                                    <th id="page-url" scope="col" class="tg-textual-column"><fmt:message key="contract.pageUrl"/></th>
                                    <th id="date" scope="col" class="tg-textual-column"><fmt:message key="contract.date"/></th>
                                    <th id="raw-mark" scope="col" class="tg-numerical-column"><fmt:message key="contract.rawMark"/></th>
                                    <th id="referential" scope="col" class="tg-textual-column"><fmt:message key="referential"/></th>
                                    <th id="scope" scope="col" class="tg-textual-column"><fmt:message key="contract.scope"/></th>
                                    <th id="status" scope="col" class="tg-textual-column"><fmt:message key="contract.status"/></th>
                                        <c:if test='${detailedContractInfo.isManualAuditEnabled}'>
                                        <th id="manual" scope="col" class="tg-textual-column"><fmt:message key="contract.manual"/></th>
                                        </c:if>
                                </tr>
                            </thead>
                            <c:if test="${fn:length(detailedContractInfo.lastActInfoSet) > 0}">
                                <tbody>
                                </c:if>
                                <c:forEach var="actInfo" items="${detailedContractInfo.lastActInfoSet}" varStatus="pContractSet">
                                    <tr>
                                        <td headers="page-url" class="tg-textual-column">
                                            <c:if test="${actInfo.scope != 'GROUPOFFILES' && actInfo.scope != 'FILE'}">
                                                <span class="open-external-url-icon">
                                                    <a title="<fmt:message key="pageList.goTo"/> ${actInfo.url}" href="${actInfo.url}">
                                                        <img alt="<fmt:message key="pageList.goTo"/> ${actInfo.url}" src="${goToImgUrl}">
                                                    </a>
                                                </span>
                                            </c:if>
                                            <c:set var="resultUrl" scope="page" value="/home/contract/audit-result.html?audit="/>
                                            <a href="<c:url value="${resultUrl}${actInfo.auditId}"/>" title="<fmt:message key="pageList.pageDetailedResult"></fmt:message> <fmt:message key="pageList.for"></fmt:message> ${actInfo.url}">
                                                ${actInfo.url}
                                            </a>
                                        </td>
                                        <td headers="date" class="tg-textual-column"><fmt:formatDate type="both" value="${actInfo.date}" dateStyle="short" timeStyle="short"/></td>
                                        <td headers="raw-mark" class="tg-numerical-column">
                                            <c:choose>
                                                <c:when test="${actInfo.status == 'COMPLETED'}">
                                                    <c:set var="mark" scope="page" value="${actInfo.rawMark}"/>
                                                    <c:set var="scoreClass" scope="page" value="act-score"/>
                                                    <c:set var="scoreId" scope="page" value=""/>
                                                    <c:set var="displayWeightedMark" scope="page" value="false"/>
                                                    <c:set var="hasProgressInfo" scope="page" value="false"/>
                                                    <c:set var="spanClass" scope="page" value=""/>
                                                    <%@include file="template/score.jsp" %>
                                                </c:when>
                                                <c:otherwise>
                                                    <div> -- </div>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td headers="referential" class="tg-textual-column">
                                            <fmt:message key="${actInfo.referential}"/>
                                        </td>
                                        <td headers="scope" class="tg-textual-column">
                                            <fmt:message key="${actInfo.scope}"/>
                                        </td>
                                        <td headers="status" class="tg-textual-column">
                                            <a href="<c:url value="${resultUrl}${actInfo.auditId}"/>" title="<fmt:message key="pageList.pageDetailedResult"></fmt:message> <fmt:message key="pageList.for"></fmt:message> ${actInfo.url}">
                                                <fmt:message key="${actInfo.status}"/>
                                            </a>
                                        </td>
                                        <c:if test='${detailedContractInfo.isManualAuditEnabled}'>
                                            <td headers="manual" class="tg-textual-column">
                                                <c:set var="auditUrl" scope="page" value="/home/contract/manual-audit-result.html?audit=" />
                                                <c:choose>
                                                    <c:when test="${actInfo.scope == 'DOMAIN'}">

                                                    </c:when>
                                                    <c:when test="${actInfo.manual == 'true'}">
                                                        <a href="<c:url value="${auditUrl}${actInfo.auditId}"/>" 
                                                           title="<fmt:message key="pageList.resumeManualAuditTitle" ><fmt:param>${actInfo.url}</fmt:param></fmt:message>">
                                                            <fmt:message key="pageList.resumeManualAudit" />
                                                        </a>
                                                    </c:when>
                                                    <c:when test="${actInfo.manual == 'false'}">
                                                        <a href="<c:url value="${auditUrl}${actInfo.auditId}"/>"
                                                           title="<fmt:message key="pageList.startManualAuditTitle" ><fmt:param>${actInfo.url}</fmt:param></fmt:message>">
                                                            <fmt:message key="pageList.startManualAudit" />
                                                        </a>
                                                    </c:when>
                                                </c:choose>
                                            </td>
                                        </c:if>
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
        <script type="text/javascript" src="${jqueryUrl}"></script>
        <script type="text/javascript" src="${jqueryTableSorterUrl}"></script>
        <script type="text/javascript" src="${accessibleTableSorterJsUrl}"></script>
        <!--[if lte IE 8]>
        <script type="text/javascript" src="${raphaelJsUrl}"></script>
        <script type="text/javascript" src="${trendChartIEJsUrl}"></script>
        <script type="text/javascript" src="${scoreIEJsUrl}"></script>
        <script type="text/javascript" src="${contractScoreIEJsUrl}"></script>
        <![endif]-->
        <!--[if gt IE 8]>
        <script type="text/javascript" src="${d3JsUrl}"></script>
        <script type="text/javascript" src="${trendChartJsUrl}"></script>
        <script type="text/javascript" src="${scoreJsUrl}"></script>
        <script type="text/javascript" src="${contractScoreJsUrl}"></script>
        <![endif]-->
        <!--[if !IE]><!-->
        <script type="text/javascript" src="${d3JsUrl}"></script>
        <script type="text/javascript" src="${trendChartJsUrl}"></script>
        <script type="text/javascript" src="${scoreJsUrl}"></script>
        <script type="text/javascript" src="${contractScoreJsUrl}"></script>
        <!--<![endif]-->
    </body>
</html>
</compress:html>
