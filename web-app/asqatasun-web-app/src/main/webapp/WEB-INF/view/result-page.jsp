<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://tagutils" prefix="tg" %>
<!DOCTYPE html>

<!-- external js -->
<c:set var="jqueryUrl">
    <c:url value="/External-Js/jquery-1.9.1.min.js"/>
</c:set>        
<c:set var="jqueryUIUrl">
    <c:url value="/External-Js/jquery-ui-1.10.1.custom.min.js"/>  
</c:set>
<c:set var="d3JsUrl" scope="request">
    <c:url value="/External-Js/d3.v3.min.js"/>
</c:set> 
<c:set var="codePrettifierJsUrl" scope="request">
    <c:url value="/External-Js/prettify.min.js"/>
</c:set> 
<c:set var="r2d3JsUrl" scope="request">
    <c:url value="/External-Js/r2d3.v2.min.js"/>
</c:set> 

<!-- internal js -->
<c:set var="resultPageChartsIEJsUrl" scope="request">
    <c:url value="/Js/ie/result-page/result-page-charts-ie-min.js"/>
</c:set>
<c:set var="resultPageChartsJsUrl" scope="request">
    <c:url value="/Js/result-page/result-page-charts-min.js"/>
</c:set>
<c:set var="displayExternalImgJsUrl" scope="request">
    <c:url value="/Js/result-page/add-img-snapshot-min.js"/>
</c:set>        
<c:set var="prettyPrintJsUrl" scope="request">
    <c:url value="/Js/result-page/pretty-print-min.js"/>
</c:set> 
<c:set var="scoreJsUrl" scope="request">
    <c:url value="/Js/score/score-min.js"/>
</c:set>
<c:set var="scoreIEJsUrl" scope="request">
    <c:url value="/Js/ie/score/score-ie-min.js"/>
</c:set>
<c:set var="testDetailsJsUrl" scope="page">
    <c:url value="/Js/expand-collapse/test-details-min.js"/>
</c:set>
<c:set var="themeDetailsJsUrl" scope="page">
    <c:url value="/Js/expand-collapse/theme-details-min.js"/>
</c:set>
<c:set var="auditParametersDetailsJsUrl" scope="page">
    <c:url value="/Js/expand-collapse/audit-parameters-details-min.js"/>
</c:set>
<c:set var="progressBarJsUrl" scope="page">
    <c:url value="/Js/progress-bar/progress-bar-min.js"/>
</c:set>
<c:set var="displaySnapshotJsUrl" scope="page">
    <c:url value="/Js/snapshot/snapshot-min.js"/>
</c:set>
<c:set var="detailResultManualJsUrl">
    <c:url value="/Js/result-page/detail-result-manual-min.js"/>  
</c:set>
<c:set var="applyAutoResultsJsUrl">
    <c:url value="/Js/result-page/apply-auto-results-min.js"/>  
</c:set>

<!-- external images -->
<c:set var="testInfoLinkImg" scope="request">
    <c:url value="/Images/test-info-link.png"/>
</c:set> 
<c:set var="algoLinkImg" scope="request">
    <c:url value="/Images/algo-link.png"/>
</c:set> 
<c:set var="expandedImg" scope="request">
    <c:url value="/Images/expanded.png"/>
</c:set> 
<c:set var="expandedSmallImg" scope="request">
    <c:url value="/Images/expanded-s.png"/>
</c:set> 
<c:set var="collapsedImg" scope="request">
    <c:url value="/Images/collapsed.png"/>
</c:set> 
<c:set var="collapsedSmallImg" scope="request">
    <c:url value="/Images/collapsed-s.png"/>
</c:set> 
<c:set var="sourceCodeImg" scope="request">
    <c:url value="/Images/html-source-icon.png"/>
</c:set>
<c:set var="processingImgUrl" scope="request">
    <c:url value="/Images/processing.gif"/>  
</c:set>

<c:set var="addRelaunchAction" scope="page" value="true"/>
<html lang="${tg:lang(pageContext)}">
    <c:set var="pageTitle" scope="page">
        <fmt:message key="resultPage.pageTitle">
            <fmt:param>
                ${statistics.url}
            </fmt:param>
        </fmt:message>
    </c:set>
    <c:if test="${addRelaunchAction}">
        <c:set var="addJqueryUI" scope="request" value="true"/>
    </c:if>
    <%@include file="template/head.jsp" %>
    <body id="tgm-result-page">
        <%@include file="template/header-utils.jsp" %>
        <div class="container">
            <c:set var="pageName" scope="page">
                <fmt:message key="resultPage.h1"/>
            </c:set>
            <ul class="breadcrumb">
            <li><a href="<c:url value="/home.html"/>"><fmt:message key="home.h1"/></a> <span class="divider"></span></li>
            <li><a href="<c:url value="/home/contract.html?cr=${cr}"/>">${contractName}</a> <span class="divider"></span></li>
                <c:if test="${authorizedScopeForPageList == 'true'}">
                    <c:set var="auditSynthesisName" scope="page">
                        <fmt:message key="synthesisSite.h1">
                            <fmt:param>
                                ${audit}
                            </fmt:param>
                        </fmt:message>
                    </c:set>
            <li><a href="<c:url value="/home/contract/audit-synthesis.html?audit=${audit}"/>">${auditSynthesisName}</a> <span class="divider"></span></li>
            <li><a href="<c:url value="/home/contract/page-list.html?audit=${audit}"/>"><fmt:message key="pageList.h1"/></a> <span class="divider"></span></li>
                    <c:choose>
                        <c:when test="${statistics.auditScope == 'DOMAIN'}">
                            <c:set var="pageList2xxUrl" value="/home/contract/page-list.html?audit=${audit}&amp;status=f2xx"/>
                        </c:when>
                        <c:when test="${statistics.auditScope == 'SCENARIO'}">
                            <c:set var="pageList2xxUrl" value="/home/contract/page-list.html?audit=${audit}&amp;status=f2xx&amp;sortCriterion=rank&amp;sortDirection=1"/>
                        </c:when>
                    </c:choose>
            <li><a href="<c:url value="${pageList2xxUrl}"/>"><fmt:message key="pageList.f2xx.h1"/></a> <span class="divider"></span></li>
                </c:if>
            <li class="active">${pageName}</li>
            </ul>
            <!-- little hack to insert message for guest user. That user has to be named 'guest' in the db -->
            <c:if test="${authorizedScopeForPageList != 'true' && currentUserName == 'guest'}">
            <div class="row">
                <div class="span16">
                    <div class="alert-message info like-it-message">
                        <fmt:message key="resultPage.likedItForGuest"/>
                    </div>
                </div>
            </div>
            </c:if>
            <div id="result-page-title" class="row">
                <div class="span11">
                    <h1>
                        ${contractName}
                    </h1>
                </div>
                <div class="span4 offset1">
                    <c:if test="${addRelaunchAction}">
                    <div id="relaunch-action">
                        <c:set var="postUrl">
                            <c:url value="/home/contract/audit-page-set-up.html?cr=${auditSetUpCommand.contractId}"/>
                        </c:set>
                        <form:form modelAttribute="auditSetUpCommand" action="${postUrl}" method="post" enctype="multipart/form-data">
                            <form:hidden path="urlList[0]"/>
                            <form:hidden path="scope"/>
                            <form:hidden path="auditParameter"/>
                            <form:hidden path="relaunch"/>
                            <form:hidden path="contractId"/>
                            <form:hidden path="level"/>
                            <div id="relaunch-audit-form-submit">
                                <input id="launch-audit-submit" type="submit" class="relaunch-button result-page-action" value="<fmt:message key="resultPage.relaunchAudit"/>"/>
                            </div>
                            <%@include file="template/process-pop-up.jsp" %>
                        </form:form>
                    </div>
                    </c:if>    
                    <div id="export-actions">
                        <fmt:message key="result.export"/> : 
                        <c:if test="${resultActionList != null && not empty resultActionList}">
                            <c:forEach var="action" items="${resultActionList}" varStatus="pAction">
                            <c:if test="${pAction.index != 0}">
                            <span class="action-separator"> / </span>
                            </c:if>    
                                <c:choose>
                                    <c:when test="${action.actionEnabled == 'false'}">
                            <span class="export-action">
                                <a href="<c:url value="${action.actionUrl}&amp;wr=${param.wr}"/>" class="disabled" title="<fmt:message key="${action.actionAltI81NCode}"/> (<fmt:message key="contract.disabled"/>)">
                                    <fmt:message key="${action.actionI81NCode}"/>
                                </a>
                            </span>
                                    </c:when>
                                    <c:otherwise>
                            <span class="export-action">
                                <!-- the url actions already have a parameter (export type for example-->
                                <a href="<c:url value="${action.actionUrl}&amp;wr=${param.wr}"/>" class="" title="<fmt:message key="${action.actionAltI81NCode}"/>">
                                    <fmt:message key="${action.actionI81NCode}"/>
                                </a>
                            </span>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </c:if>    
                    </div>
                </div>    
            </div><!-- class="row" -->
        <c:set var="showLegend" scope="request" value="true"/>
        <c:set var="showAxisLabel" scope="request" value="true"/>
        <c:set var="hasBarChartLink" scope="request" value="true"/>
        <c:set var="hasGraphics" scope="request" value="true"/>
        <c:set var="hasSynthesisTitle" scope="request" value="false"/>
        <c:set var="hasPageCounter" scope="request" value="false"/>
        <c:set var="hasPagesListLink" scope="request" value="false"/>
        <c:set var="hasResultDispatchTitle" scope="request" value="false"/>
        <c:set var="themeRepartitionWidth" scope="request" value="690"/>
        <c:set var="hasPieChartInGraphicalResult" scope="request" value="true"/>
        <c:set var="hasBarChartInGraphicalResult" scope="request" value="true"/>
        <c:set var="addLinkToSourceCode" scope="request" value="true"/>
        <c:import url="template/synthesis.jsp" />
        <c:import url="template/sort-result-console.jsp" />
        <c:set var="displayAlgorithm" scope="request" value="true"/>
        <c:set var="scope" scope="request" value="page"/>
        <c:set var="counterByThemeMap" scope="request" value="${statistics.counterByThemeMap}"/>
        <c:set var="addSideBarNav" scope="request" value="true"/>
        <c:set var="addThemeHeader" scope="request" value="true"/>
        <c:set var="addMainTitle" scope="request" value="true"/>
        <c:set var="addShowHide" scope="request" value="true"/>
        <c:import url="template/detailed-result.jsp" />
        </div><!-- class="container"-->
        <%@include file="template/footer.jsp" %>
        <script type="text/javascript" src="${jqueryUrl}"></script>
        <script type="text/javascript" src="${testDetailsJsUrl}"></script>
        <script type="text/javascript" src="${themeDetailsJsUrl}"></script>
        <script type="text/javascript" src="${displayExternalImgJsUrl}"></script>
        <script type="text/javascript" src="${codePrettifierJsUrl}"></script>
        <script type="text/javascript" src="${prettyPrintJsUrl}"></script>
        <script type="text/javascript" src="${auditParametersDetailsJsUrl}"></script>
        <c:if test="${addRelaunchAction}">
        <script type="text/javascript" src="${jqueryUIUrl}"></script>
        <script type="text/javascript" src="${progressBarJsUrl}"></script>
        </c:if>
        <c:if test="${isManualAudit}">
        <script type="text/javascript" src="${detailResultManualJsUrl}"></script>
        <script type="text/javascript" src="${applyAutoResultsJsUrl}"></script>
        </c:if>
        <!--[if lte IE 8]>
        <script type="text/javascript" src="${r2d3JsUrl}"></script>
        <script type="text/javascript" src="${scoreIEJsUrl}"></script>
        <script type="text/javascript" src="${resultPageChartsIEJsUrl}"></script>
        <![endif]-->
        <!--[if gt IE 8]>
        <script type="text/javascript" src="${d3JsUrl}"></script>
        <script type="text/javascript" src="${scoreJsUrl}"></script>
        <script type="text/javascript" src="${resultPageChartsJsUrl}"></script>
        <![endif]-->
        <!--[if !IE]><!-->
        <script type="text/javascript" src="${d3JsUrl}"></script>
        <script type="text/javascript" src="${scoreJsUrl}"></script>
        <script type="text/javascript" src="${resultPageChartsJsUrl}"></script>
        <!--<![endif]-->
        <script type="text/javascript" src="${displaySnapshotJsUrl}"></script>
    </body>
</html>
</compress:html>