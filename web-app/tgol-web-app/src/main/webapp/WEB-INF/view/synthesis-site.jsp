<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri='/WEB-INF/cewolf.tld' prefix='cewolf' %>
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
<html lang="${lang}">
    <c:set var="pageTitle" scope="page">
        <fmt:message key="synthesisSite.pageTitle">
            <fmt:param>
                ${param.wr}
            </fmt:param>
        </fmt:message>
    </c:set>
    <c:set var="addWebSnapr" scope="page" value="true"/>
    <%@include file="template/head.jsp" %>
    <body id="tgm-synthesis-site">
        <%@include file="template/header-utils.jsp" %>
        <div class="container">
            <c:set var="pageName" scope="page">
                <fmt:message key="synthesisSite.h1">
                    <fmt:param>
                        ${param.wr}
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
                <c:set var="hasResultDispatchTitle" scope="request" value="true"/>
                <c:set var="hasTableAlternative" scope="request" value="false"/>
                <c:set var="showLegend" scope="request" value="true"/>
                <c:set var="showAxisLabel" scope="request" value="true"/>
                <c:set var="themeRepartitionWidth" scope="request" value="690"/>
                <c:set var="hasPieChartInGraphicalResult" scope="request" value="true"/>
                <c:import url="template/synthesis.jsp" />
            <div class="row">
                <div class="span16 offset0">
                    <h2 id="top5-failed-tests"><fmt:message key="synthesisSite.topx-failed-tests"><fmt:param>${fn:length(failedTestInfoByPageSet)}</fmt:param></fmt:message></h2>
                </div><!-- class="span16 offset0"-->
                <div class="span15 offset1">
                    <table summary="<fmt:message key="synthesisSite.failed-test-by-page"/>" id="top5-failed-test-by-page" class="zebra-striped">
                        <caption><fmt:message key="synthesisSite.failed-test-by-page"/></caption>
                        <thead>
                            <tr>
                                <th id="testTop5FailedTestByPage" scope="col" class="col01"><spring:message code="synthesisSite.test"/></th>
                                <th id="nbOfPagesTop5FailedTestByPage" scope="col" class="col02"><spring:message code="synthesisSite.nbOfPages"/></th>
                                <th id="urlListTop5FailedTestByPage" scope="col" class="col03"><spring:message code="synthesisSite.urlListCsv"/></th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="failedTestByPageInfo" items="${failedTestInfoByPageSet}" varStatus="pFailedTestInfoByPageSet">
                            <tr>
                                <td headers="testTop5FailedTestByPage" class="col01">
                                    <a href="<spring:message code="${failedTestByPageInfo.testCode}-url"/>">
                                        ${failedTestByPageInfo.testLabel}
                                    </a>
                                </td>
                                <td headers="nbOfPagesTop5FailedTestByPage" class="col02">${failedTestByPageInfo.pageCounter}</td>
                                <td headers="urlListTop5FailedTestByPage" class="col03"><fmt:message key="synthesisSite.getUrlListCsv"><fmt:param>${failedTestByPageInfo.testLabel}</fmt:param></fmt:message></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div><!-- class="span15 offset1"-->
                <div class="span16 offset0">
                    <h2 id="top5-invalid-theme"><spring:message code="synthesisSite.top5-invalid-theme"/></h2>
                </div><!-- class="span16 offset0"-->
                <div id="top5FailedThemeRepresentation" class="span15 offset1">
                    <c:set var="counterByThemeMap" scope="request" value="${top5SortedThemeMap}"/>
                    <c:set var="index" scope="request" value="2"/>
                    <c:import url="graph/stacked-horizontal-bar-representation.jsp"/>
                </div><!-- class="span15 offset1"-->
                <div class="span16 offset0">
                    <h2 id="top5-failed-URL"><fmt:message key="synthesisSite.topx-failed-URL"><fmt:param>${fn:length(failedPageInfoByTestSet)}</fmt:param></fmt:message> <fmt:message key="synthesisSite.sorted-by-test"/></h2>
                </div><!-- class="span16 offset0"-->
                <div class="span15 offset1">
                    <table summary="<spring:message code="synthesisSite.failed-url-by-test"/>" id="top5-failed-url-by-test" class="zebra-striped">
                        <caption><spring:message code="synthesisSite.failed-url-by-test"/></caption>
                        <thead>
                            <tr>
                                <th id="pageUrlTop5FailedUrlByTest" scope="col" class="col01"><spring:message code="synthesisSite.pageUrl"/></th>
                                <th id="nbOfInvalidTestTop5FailedUrlByTest" scope="col" class="col02"><spring:message code="synthesisSite.nbOfInvalidTest"/></th>
                                <th id="nbOfInvalidOccurrenceTop5FailedUrlByTest" scope="col" class="col03"><spring:message code="synthesisSite.nbOfInvalidOccurrence"/></th>
                                <th id="resultPageUrlByTest" scope="col" class="col04"><spring:message code="pageList.pageDetailedResult"/></th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="failedPageInfo" items="${failedPageInfoByTestSet}" varStatus="pFailedPageInfoByTestSet">
                            <tr>
                                <td headers="pageUrlTop5FailedUrlByTest" class="col01"><a href="${failedPageInfo.webResourceUrl}">${failedPageInfo.webResourceUrl}</a></td>
                                <td headers="nbOfInvalidTestTop5FailedUrlByTest" class="col02">${failedPageInfo.failedTestCounter}</td>
                                <td headers="nbOfInvalidOccurrenceTop5FailedUrlByTest" class="col03">${failedPageInfo.failedOccurrenceCounter}</td>
                                <td headers="resultPageUrlByTest" class="col04"><a href="<c:url value="/home/contract/audit-result.html?wr=${failedPageInfo.webResourceId}"/>" title="<spring:message code="pageList.pageDetailedResult"/> <spring:message code="pageList.for"/> ${failedPageInfo.webResourceUrl}"><spring:message code="pageList.pageDetailedResult"/></a></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div><!-- class="span15 offset1"-->
                <div class="span16 offset0">
                    <h2><fmt:message key="synthesisSite.topx-failed-URL"><fmt:param>${fn:length(failedPageInfoByOccurrenceSet)}</fmt:param></fmt:message> <fmt:message key="synthesisSite.sorted-by-occurrence"/></h2>
                </div><!-- class="span16 offset0"-->
                <div class="span15 offset1">
                    <table summary="<fmt:message key="synthesisSite.failed-url-by-occurrence"/>" id="top5-failed-url-by-occurrence" class="zebra-striped">
                        <caption><fmt:message key="synthesisSite.failed-url-by-occurrence"/></caption>
                        <thead>
                            <tr>
                                <th id="pageUrlTop5FailedUrlByOccurrence" scope="col" class="col01"><spring:message code="synthesisSite.pageUrl"/></th>
                                <th id="nbOfInvalidOccurrenceTop5FailedUrlByOccurrence" scope="col" class="col02"><spring:message code="synthesisSite.nbOfInvalidOccurrence"/></th>
                                <th id="nbOfInvalidTestTop5FailedUrlByOccurrence" scope="col" class="col03"><spring:message code="synthesisSite.nbOfInvalidTest"/></th>
                                <th id="resultPageUrlByOccurrence" scope="col" class="col04"><spring:message code="pageList.pageDetailedResult"/></th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="failedPageInfo" items="${failedPageInfoByOccurrenceSet}" varStatus="pFailedPageInfoByOccurrenceSet">
                            <tr>
                                <td headers="pageUrlTop5FailedUrlByOccurrence" class="col01"><a href="${failedPageInfo.webResourceUrl}">${failedPageInfo.webResourceUrl}</a></td>
                                <td headers="nbOfInvalidOccurrenceTop5FailedUrlByOccurrence" class="col02">${failedPageInfo.failedOccurrenceCounter}</td>
                                <td headers="nbOfInvalidTestTop5FailedUrlByOccurrence" class="col03">${failedPageInfo.failedTestCounter}</td>
                                <td headers="resultPageUrlByOccurrence" class="col04"><a href="<c:url value="/home/contract/audit-result.html?wr=${failedPageInfo.webResourceId}"/>" title="<spring:message code="pageList.pageDetailedResult"/> <spring:message code="pageList.for"/> ${failedPageInfo.webResourceUrl}"><spring:message code="pageList.pageDetailedResult"/></a></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div><!-- class="span15 offset1"-->
            </div><!-- class="row"-->
            </c:when>
            <c:otherwise>
            <div id="synthesis-result" class="row">
                <div class="span16 offset0">
                   <spring:message code="${status}"/>
                </div>
                <div class="span15 offset1">
                    <table summary="Audit meta-data" id="result-meta" class="">
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
                <div class="span15 offset1">
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
                    </ul>
                </div>
                    </c:when>
                    <c:when test="${status == 'ERROR_ADAPTING'}">
                <div class="span15 offset1">
                    <spring:message code="errorProcessingPage.message"/>
                </div>
                    </c:when>
                    <c:otherwise>
                <div class="span15 offset1">
                    <spring:message code="oups.message"/>
                </div>
                    </c:otherwise>
                </c:choose>
            </div><!-- class="row"-->
            </c:otherwise>
        </c:choose>
        </div><!-- class="container"-->
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
        <script type="text/javascript" src="http://asset.open-s.com/Corporate/TGOL/Js/audit-set-up.js"></script>
        <%@include file="template/footer.jsp" %>
    </body>
</html>