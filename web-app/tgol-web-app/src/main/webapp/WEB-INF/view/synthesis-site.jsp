<%@taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html>
<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://tagutils" prefix="tg" %>
<!DOCTYPE html>

<!-- external js-->
<c:set var="jqueryUrl">
    <c:url value="/External-Js/jquery-1.9.1.min.js"/>
</c:set>        
<c:set var="d3JsUrl" scope="request">
    <c:url value="/External-Js/d3.v3.min.js"/>
</c:set> 
<c:set var="r2d3JsUrl" scope="request">
    <c:url value="/External-Js/r2d3.v2.min.js"/>
</c:set> 

<!-- internal js-->
<c:set var="top5FailedThemeGraphJsUrl" scope="request">
    <c:url value="/Js/synthesis/repartition-by-failed-theme-min.js"/>
</c:set> 
<c:set var="top5FailedThemeGraphIEJsUrl" scope="request">
    <c:url value="/Js/ie/synthesis/repartition-by-failed-theme-ie-min.js"/>
</c:set> 
<c:set var="resultPageChartsIEJsUrl" scope="request">
    <c:url value="/Js/ie/result-page/result-page-charts-ie-min.js"/>
</c:set>
<c:set var="resultPageChartsJsUrl" scope="request">
    <c:url value="/Js/result-page/result-page-charts-min.js"/>
</c:set>
<c:set var="auditParametersDetailsJsUrl">
    <c:url value="/Js/expand-collapse/audit-parameters-details-min.js"/>
</c:set>
<c:set var="scoreJsUrl" scope="request">
    <c:url value="/Js/score/score-min.js"/>
</c:set>
<c:set var="scoreIEJsUrl" scope="request">
    <c:url value="/Js/ie/score/score-ie-min.js"/>
</c:set>
<c:set var="displaySnapshotJsUrl" scope="page">
    <c:url value="/Js/snapshot/snapshot-min.js"/>
</c:set>
<!-- images --> 
<c:set var="goToImgUrl">
    <c:url value="/Images/window-duplicate.png"/>
</c:set>

<html lang="${tg:lang(pageContext)}">
    <c:set var="pageTitle" scope="page">
        <fmt:message key="synthesisSite.pageTitle">
            <fmt:param>
                ${param.audit}
            </fmt:param>
        </fmt:message>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="tgm-synthesis-site">
        <%@include file="template/header-utils.jsp" %>
        <div class="container">
            <c:set var="pageName" scope="page">
                <fmt:message key="synthesisSite.h1">
                    <fmt:param>
                        ${param.audit}
                    </fmt:param>
                </fmt:message>
            </c:set>
            <ul class="breadcrumb">
                <li><a href="<c:url value="/home.html"/>"><fmt:message key="home.h1"/></a> <span class="divider"></span></li>
                <li><a href="<c:url value="/home/contract.html?cr=${cr}"/>">${contractName}</a> <span class="divider"></span></li>
                <li class="active">${pageName}</li>
            </ul>
            <c:set var="scope" scope="request" value="site"/>
            <div class="row">
                <div class="span16">
                    <h1>
                        ${pageName}
                    </h1>
                </div><!-- class="span16"-->
            </div><!-- class="row"-->
        <c:choose>
            <c:when test="${status == 'COMPLETED'}">
                <c:set var="hasSynthesisTitle" scope="request" value="false"/>
                <c:set var="hasBarChartLink" scope="request" value="false"/>
                <c:set var="hasGraphics" scope="request" value="true"/>
                <c:set var="hasPageCounter" scope="request" value="true"/>
                <c:set var="hasPagesListLink" scope="request" value="true"/>
                <c:set var="hasResultDispatchTitle" scope="request" value="false"/>
                <c:set var="hasTableAlternative" scope="request" value="false"/>
                <c:set var="showLegend" scope="request" value="true"/>
                <c:set var="showAxisLabel" scope="request" value="true"/>
                <c:set var="themeRepartitionWidth" scope="request" value="690"/>
                <c:set var="hasPieChartInGraphicalResult" scope="request" value="true"/>
                <c:import url="template/synthesis.jsp" />
            <div class="row">
                <div class="span16 tg-table-title">
                    <h2 id="top5-failed-tests">
                        <fmt:message key="synthesisSite.topx-failed-tests">
                            <fmt:param>
                                ${fn:length(failedTestInfoByPageSet)}
                            </fmt:param>
                        </fmt:message>
                    </h2>
                </div><!-- class="span16"-->
                <div class="span16 tg-table-container">
                    <table id="top5-failed-test-by-page" class="tg-table">
                        <caption><fmt:message key="synthesisSite.failed-test-by-page"/></caption>
                        <thead>
                            <tr>
                                <th id="testTop5FailedTestByPage" scope="col" class="tg-textual-column"><spring:message code="synthesisSite.test"/></th>
                                <th id="testLevelTop5FailedTestByPage" scope="col" class="tg-textual-column"><spring:message code="level"/></th>
                                <th id="urlListTop5FailedTestByPage" scope="col" class="tg-textual-column"><spring:message code="synthesisSite.urlListCsv"/></th>
                                <th id="nbOfPagesTop5FailedTestByPage" scope="col" class="tg-numerical-column"><spring:message code="synthesisSite.nbOfPages"/></th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:if test="${tg:lang(pageContext) != 'en' && tg:lang(pageContext) != 'fr'}">
                            <c:choose>
                                <c:when test="${fn:startsWith(statistics.parametersMap['referential'], 'RGAA')}">
                                    <c:set var="ruleLang" value=" lang=fr "/>
                                </c:when>
                                <c:otherwise>
                                    <c:set var="ruleLang" value=" lang=en "/>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                        <c:forEach var="failedTestByPageInfo" items="${failedTestInfoByPageSet}" varStatus="pFailedTestInfoByPageSet">
                            <tr>
                                <td headers="testTop5FailedTestByPage" class="tg-textual-column">
                                    <c:set var="abbrTitle">
                                        ${failedTestByPageInfo.testLabel} : <spring:message code="${failedTestByPageInfo.testCode}"/>
                                    </c:set>
                                    <abbr title="${fn:escapeXml(abbrTitle)}"  style="border-bottom: 1px dotted;cursor: help;" ${ruleLang}>
                                        ${failedTestByPageInfo.testLabel}
                                    </abbr>
                                </td>
                                <td headers="testLevelTop5FailedTestByPage" class="tg-textual-column">
                                    <c:set var="testLevelCode">
                                        ${statistics.parametersMap["referential"]}-${failedTestByPageInfo.testLevelCode}
                                    </c:set>
                                    <fmt:message key="${testLevelCode}"/>
                                </td>
                                <td headers="urlListTop5FailedTestByPage" class="tg-textual-column">
                                    <a href="<c:url value="/home/contract/page-list.html?audit=${param.audit}&amp;status=f2xx&amp;sortDirection=2&amp;test=${failedTestByPageInfo.testLabel}"/>">
                                        <fmt:message key="synthesisSite.getUrlListCsv"><fmt:param>${failedTestByPageInfo.testLabel}</fmt:param></fmt:message>
                                    </a>
                                </td>
                                <td headers="nbOfPagesTop5FailedTestByPage" class="tg-numerical-column">${failedTestByPageInfo.pageCounter}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    
                </div><!-- class="span16  tg-table-container"-->
                <div id="failed-test-list-link" class="span16">
                    <a href="<c:url value="/home/contract/failed-test-list.html?audit=${param.audit}"/>">
                        <fmt:message key="synthesisSite.displayTheCompleteListOfFailedTests"/>
                    </a>
                </div>
                <div class="span16 tg-table-title">
                    <h2 id="top5-invalid-theme">
                        <spring:message code="synthesisSite.top5-invalid-theme"/>
                    </h2>
                </div><!-- class="span16 tg-table-title"-->
                <div id="top5FailedThemeRepresentation" class="span16">
                    <c:set var="counterByThemeMap" scope="request" value="${top5SortedThemeMap}"/>
                    <c:set var="index" scope="request" value="2"/>
                    <c:import url="graph/stacked-horizontal-bar-representation.jsp"/>
                </div><!-- class="span15 offset1"-->
                <div class="span16 tg-table-title">
                    <h2 id="top5-failed-URL">
                        <fmt:message key="synthesisSite.topx-failed-URL">
                            <fmt:param>
                                ${fn:length(failedPageInfoByTestSet)}
                            </fmt:param>
                        </fmt:message> 
                        <fmt:message key="synthesisSite.sorted-by-test"/>
                    </h2>
                </div><!-- class="span16 tg-table-title"-->
                <div class="span16 tg-table-container">
                    <table id="top5-failed-url-by-test" class="tg-table">
                        <caption><spring:message code="synthesisSite.failed-url-by-test"/></caption>
                        <thead>
                            <tr>
                                <th id="pageUrlTop5FailedUrlByTest" scope="col" class="tg-textual-column">
                                    <spring:message code="synthesisSite.pageUrl"/>
                                </th>
                                <th id="nbOfInvalidTestTop5FailedUrlByTest" scope="col" class="tg-numerical-column">
                                    <spring:message code="synthesisSite.nbOfInvalidTest"/>
                                </th>
                                <th id="nbOfInvalidOccurrenceTop5FailedUrlByTest" scope="col" class="tg-numerical-column">
                                    <spring:message code="synthesisSite.nbOfInvalidOccurrence"/>
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="failedPageInfo" items="${failedPageInfoByTestSet}" varStatus="pFailedPageInfoByTestSet">
                            <tr>
                                <td headers="pageUrlTop5FailedUrlByTest" class="tg-textual-column">
                                    <span class="open-external-url-icon">
                                        <a href="${failedPageInfo.webResourceUrl}" title="<fmt:message key="pageList.goTo"/> ${failedPageInfo.webResourceUrl}">
                                            <img src="${goToImgUrl}" alt="<fmt:message key="pageList.goTo"/> ${failedPageInfo.webResourceUrl}">
                                        </a>
                                    </span>
                                    <a href="<c:url value="/home/contract/page-result.html?wr=${failedPageInfo.webResourceId}"/>" title="<fmt:message key="pageList.pageDetailedResult"></fmt:message> <fmt:message key="pageList.for"></fmt:message> ${failedPageInfo.webResourceUrl}">
                                        ${failedPageInfo.webResourceUrl}
                                    </a>
                                </td>
                                <td headers="nbOfInvalidTestTop5FailedUrlByTest" class="tg-numerical-column">
                                    ${failedPageInfo.failedTestCounter}
                                </td>
                                <td headers="nbOfInvalidOccurrenceTop5FailedUrlByTest" class="tg-numerical-column">
                                    ${failedPageInfo.failedOccurrenceCounter}
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div><!-- class="span16 tg-table-container"-->
                <div class="span16 tg-table-title">
                    <h2>
                        <fmt:message key="synthesisSite.topx-failed-URL">
                            <fmt:param>
                                ${fn:length(failedPageInfoByOccurrenceSet)}
                            </fmt:param>
                        </fmt:message> 
                        <fmt:message key="synthesisSite.sorted-by-occurrence"/>
                    </h2>
                </div><!-- class="span16 tg-table-title"-->
                <div class="span16 tg-table-container">
                    <table id="top5-failed-url-by-occurrence" class="tg-table">
                        <caption><fmt:message key="synthesisSite.failed-url-by-occurrence"/></caption>
                        <thead>
                            <tr>
                                <th id="pageUrlTop5FailedUrlByOccurrence" scope="col" class="tg-textual-column">
                                    <spring:message code="synthesisSite.pageUrl"/>
                                </th>
                                <th id="nbOfInvalidOccurrenceTop5FailedUrlByOccurrence" scope="col" class="tg-numerical-column">
                                    <spring:message code="synthesisSite.nbOfInvalidOccurrence"/>
                                </th>
                                <th id="nbOfInvalidTestTop5FailedUrlByOccurrence" scope="col" class="tg-numerical-column">
                                    <spring:message code="synthesisSite.nbOfInvalidTest"/>
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="failedPageInfo" items="${failedPageInfoByOccurrenceSet}" varStatus="pFailedPageInfoByOccurrenceSet">
                            <tr>
                                <td headers="pageUrlTop5FailedUrlByOccurrence" class="tg-textual-column">
                                    <span class="open-external-url-icon">
                                        <a href="${failedPageInfo.webResourceUrl}" title="<fmt:message key="pageList.goTo"/> ${failedPageInfo.webResourceUrl}">
                                            <img src="${goToImgUrl}" alt="<fmt:message key="pageList.goTo"/> ${failedPageInfo.webResourceUrl}">
                                        </a>
                                    </span>
                                    <a href="<c:url value="/home/contract/page-result.html?wr=${failedPageInfo.webResourceId}"/>" title="<fmt:message key="pageList.pageDetailedResult"></fmt:message> <fmt:message key="pageList.for"></fmt:message> ${failedPageInfo.webResourceUrl}">
                                        ${failedPageInfo.webResourceUrl}
                                    </a>
                                </td>
                                <td headers="nbOfInvalidOccurrenceTop5FailedUrlByOccurrence" class="tg-numerical-column">
                                    ${failedPageInfo.failedOccurrenceCounter}
                                </td>
                                <td headers="nbOfInvalidTestTop5FailedUrlByOccurrence" class="tg-numerical-column">
                                    ${failedPageInfo.failedTestCounter}
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div><!-- class="span16 tg-table-container"-->
            </div><!-- class="row"-->
            </c:when>
            <c:otherwise>
            <div id="synthesis-result" class="row">
                <div class="span16 offset0">
                   <spring:message code="${status}"/>
                </div>
                <div class="span16">
                    <table id="result-meta" class="">
                        <caption>Audit meta-data</caption>
                        <tr>
                            <th id="meta-url" scope="row" class="col01"><spring:message code="resultPage.url"/></th>
                            <td class="col02"><a href="${statistics.url}">${statistics.url}</a></td>
                        </tr>
                        <tr>
                            <th id="meta-date" scope="row" class="col01"><spring:message code="resultPage.date"/></th>
                            <td class="col02"><fmt:formatDate type="both" value="${statistics.date}" dateStyle="long" timeStyle="long"/></td>
                        </tr>
                    </table>
                </div>
                <div class="span16 offset0">
                    <h2 id="error-explanation"><spring:message code="errorLoadingPage.explanation"/></h2>
                </div>
                <c:choose>
                    <c:when test="${status == 'ERROR_LOADING'}">
                <div class="span16">
                    <ul>
                        <li><spring:message code="errorLoadingPage.explanationSiteDetail1"/></li>
                        <c:choose>
                            <c:when test="${fn:endsWith(statistics.url, '/')}">
                                <c:set var="robotsUrl" scope="page">
                                    ${statistics.url}robots.txt
                                </c:set>
                            </c:when>
                            <c:otherwise>
                                <c:set var="robotsUrl" scope="page">
                                    ${statistics.url}/robots.txt
                                </c:set>
                            </c:otherwise>
                        </c:choose>
                        <c:set var="pageName" scope="page">
                        <fmt:message key="synthesisSite.h1">
                            <fmt:param>
                        ${param.wr}
                            </fmt:param>
                        </fmt:message>
                        </c:set>
                        <li>
                            <fmt:message key="errorLoadingPage.explanationSiteDetail2">
                                <fmt:param>
                                    ${robotsUrl}
                                </fmt:param>
                            </fmt:message>
                        </li>
                        <li><spring:message code="errorLoadingPage.explanationSiteDetail3"/></li>
                    </ul>
                </div>
                    </c:when>
                    <c:when test="${status == 'ERROR_ADAPTING'}">
                <div class="span16">
                    <spring:message code="errorProcessingPage.message"/>
                </div>
                    </c:when>
                    <c:otherwise>
                <div class="span16">
                    <spring:message code="oups.message"/>
                </div>
                    </c:otherwise>
                </c:choose>
            </div><!-- class="row"-->
            </c:otherwise>
        </c:choose>
        </div><!-- class="container"-->
        <%@include file="template/footer.jsp" %>
        <script type="text/javascript" src="${jqueryUrl}"></script>
        <script type="text/javascript" src="${auditParametersDetailsJsUrl}"></script>
        <!--[if lte IE 8]>
        <script type="text/javascript" src="${r2d3JsUrl}"></script>
        <script type="text/javascript" src="${scoreIEJsUrl}"></script>
        <script type="text/javascript" src="${resultPageChartsIEJsUrl}"></script>
        <script type="text/javascript" src="${top5FailedThemeGraphIEJsUrl}"></script>
        <![endif]-->
        <!--[if gt IE 8]>
        <script type="text/javascript" src="${d3JsUrl}"></script>
        <script type="text/javascript" src="${scoreJsUrl}"></script>
        <script type="text/javascript" src="${resultPageChartsJsUrl}"></script>
        <script type="text/javascript" src="${top5FailedThemeGraphJsUrl}"></script>
        <![endif]-->
        <!--[if !IE]><!-->
        <script type="text/javascript" src="${d3JsUrl}"></script>
        <script type="text/javascript" src="${scoreJsUrl}"></script>
        <script type="text/javascript" src="${resultPageChartsJsUrl}"></script>
        <script type="text/javascript" src="${top5FailedThemeGraphJsUrl}"></script>
        <!--<![endif]-->
        <script type="text/javascript" src="${displaySnapshotJsUrl}"></script>
    </body>
</html>
</compress:html>