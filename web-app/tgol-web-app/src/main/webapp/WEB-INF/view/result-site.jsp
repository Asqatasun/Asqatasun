<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://tagutils" prefix="tg" %>
<!DOCTYPE html>

<!-- external js -->
<c:set var="jqueryUrl">
    <c:url value="/External-Js/jquery-1.9.1.min.js"/>
</c:set>        
<c:set var="d3JsUrl" scope="request">
    <c:url value="/External-Js/d3.v3.min.js"/>
</c:set> 
<c:set var="r2d3JsUrl" scope="request">
    <c:url value="/External-Js/r2d3.v2.min.js"/>
</c:set> 
<c:set var="jqueryTableSorterUrl" scope="request">
    <c:url value="/External-Js/jquery.tablesort er.min.js"/>
</c:set> 

<!-- internal js -->
<c:set var="resultPageChartsIEJsUrl" scope="request">
    <c:url value="/Js/ie/result-page/result-page-charts-ie-min.js"/>
</c:set>
<c:set var="scoreJsUrl" scope="request">
    <c:url value="/Js/score/score-min.js"/>
</c:set>
<c:set var="siteScopeScoreJsUrl" scope="request">
    <c:url value="/Js/score/score-page-list-min.js"/>
</c:set>
<c:set var="scoreIEJsUrl" scope="request">
    <c:url value="/Js/ie/score/score-ie-min.js"/>
</c:set>
<c:set var="siteScopeScoreIEJsUrl" scope="request">
    <c:url value="/Js/ie/score/score-page-list-ie-min.js"/>
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
<c:set var="accessibleTableSorterJsUrl" scope="page">
    <c:url value="/Js/table-sorter/accessible-table-sorter-min.js"/>
</c:set>
<c:set var="displaySnapshotJsUrl" scope="page">
    <c:url value="/Js/snapshot/snapshot-min.js"/>
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

<html lang="${tg:lang(pageContext)}">
    <c:set var="pageTitle" scope="page">
        <fmt:message key="resultSite.pageTitle">
            <fmt:param>
                ${statistics.url}
            </fmt:param>
        </fmt:message>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="tgm-result-site">
        <%@include file="template/header-utils.jsp" %>
        <div class="container">
            <c:set var="pageName" scope="page">
                <fmt:message key="resultSite.h1"/>
            </c:set>
            <ul class="breadcrumb">
                <li><a href="<c:url value="/home.html"/>"><fmt:message key="home.h1"/></a> <span class="divider"></span></li>
                <li><a href="<c:url value="/home/contract.html?cr=${cr}"/>">${contractName}</a> <span class="divider"></span></li>
                <c:set var="auditSynthesisName" scope="page">
                    <fmt:message key="synthesisSite.h1">
                        <fmt:param>
                            ${audit}
                        </fmt:param>
                    </fmt:message>
                </c:set>
                <li><a href="<c:url value="/home/contract/audit-synthesis.html?audit=${audit}"/>">${auditSynthesisName}</a> <span class="divider"></span></li>
                <li class="active">${pageName}</li>
            </ul>
            <div class="row">
                <div class="span16">
                    <h1>
                        ${pageName}
                    </h1>
                </div>
            </div><!-- class="row" -->
            <c:set var="hasBarChartLink" scope="request" value="false"/>
            <c:set var="hasGraphics" scope="request" value="false"/>
            <c:set var="hasSynthesisTitle" scope="request" value="false"/>
            <c:set var="hasPageCounter" scope="request" value="true"/>
            <c:set var="hasPagesListLink" scope="request" value="false"/>
            <c:set var="hasResultDispatchTitle" scope="request" value="false"/>
            <c:import url="template/synthesis.jsp" />
            <c:import url="template/sort-result-console.jsp" />
            <div class="row">
                <div class="span16 separator">
                </div>
            </div><!-- class="row" -->
            <c:set var="displayAlgorithm" scope="request" value="true"/>
            <c:set var="scope" scope="request" value="site"/>
            <c:set var="addThemeHeader" scope="request" value="true"/>
            <c:set var="addMainTitle" scope="request" value="true"/>
            <c:set var="addShowHide" scope="request" value="true"/>
            <c:import url="template/detailed-result.jsp" />
        </div><!-- class="container"-->
        <%@include file="template/footer.jsp" %>
        <script type="text/javascript" src="${jqueryUrl}"></script>
        <script type="text/javascript" src="${testDetailsJsUrl}"></script>
        <script type="text/javascript" src="${themeDetailsJsUrl}"></script>
        <script type="text/javascript" src="${auditParametersDetailsJsUrl}"></script>
        <script type="text/javascript" src="${jqueryTableSorterUrl}"></script>
        <script type="text/javascript" src="${accessibleTableSorterJsUrl}"></script>
        <!--[if lte IE 8]>
        <script type="text/javascript" src="${r2d3JsUrl}"></script>
        <script type="text/javascript" src="${scoreIEJsUrl}"></script>
        <script type="text/javascript" src="${siteScopeScoreIEJsUrl}"></script>
        <![endif]-->
        <!--[if gt IE 8]>
        <script type="text/javascript" src="${d3JsUrl}"></script>
        <script type="text/javascript" src="${scoreJsUrl}"></script>
        <script type="text/javascript" src="${siteScopeScoreJsUrl}"></script>
        <![endif]-->
        <!--[if !IE]><!-->
        <script type="text/javascript" src="${d3JsUrl}"></script>
        <script type="text/javascript" src="${scoreJsUrl}"></script>
        <script type="text/javascript" src="${siteScopeScoreJsUrl}"></script>
        <!--<![endif]-->
	<script type="text/javascript" src="${displaySnapshotJsUrl}"></script>
    </body>
</html>
</compress:html>