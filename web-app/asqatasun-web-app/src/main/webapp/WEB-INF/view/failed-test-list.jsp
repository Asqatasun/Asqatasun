<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://tagutils" prefix="tg" %>
<!DOCTYPE html>

<!-- external js-->
<c:set var="jqueryUrl">
    <c:url value="/External-Js/jquery-1.9.1.min.js"/>
</c:set>        
<c:set var="jqueryTableSorterUrl" scope="request">
    <c:url value="/External-Js/jquery.tablesorter.min.js"/>
</c:set> 

<!-- internal js-->
<c:set var="accessibleTableSorterJsUrl" scope="page">
    <c:url value="/Js/table-sorter/accessible-table-sorter-min.js"/>
</c:set>

<!-- images --> 
<c:set var="goToImgUrl">
    <c:url value="/Images/window-duplicate.png"/>
</c:set>

<html lang="${tg:lang(pageContext)}">
    <c:set var="pageTitle" scope="page">
        <fmt:message key="failedTestList.pageTitle">
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
                <fmt:message key="failedTestList.h1">
                    <fmt:param>
                        ${param.audit}
                    </fmt:param>
                </fmt:message>
            </c:set>
            <ul class="breadcrumb">
                <li><a href="<c:url value="/home.html"/>"><fmt:message key="home.h1"/></a> <span class="divider"></span></li>
                <li><a href="<c:url value="/home/contract.html?cr=${cr}"/>">${contractName}</a> <span class="divider"></span></li>
                <c:set var="auditSynthesisName" scope="page">
                    <fmt:message key="synthesisSite.h1">
                        <fmt:param>
                            ${param.audit}
                        </fmt:param>
                    </fmt:message>
                </c:set>
                <li><a href="<c:url value="/home/contract/audit-synthesis.html?audit=${param.audit}"/>">${auditSynthesisName}</a> <span class="divider"></span></li>
                <li class="active">${pageName}</li>
            </ul>
            <c:set var="scope" scope="request" value="site"/>
        <c:choose>
            <c:when test="${status == 'COMPLETED'}">
            <div class="row">
                <div class="span16">
                    <h1 id="top5-failed-tests">
                        ${pageName}
                    </h1>
                </div><!-- class="span16"-->
                <div class="span16 tg-table-container">
                    <table id="top5-failed-test-by-page" class="tg-table sortable-table">
                        <caption><fmt:message key="failedTestList.failed-test-by-page"/></caption>
                        <thead>
                            <tr>
                                <th id="testTop5FailedTestByPage" scope="col" class="tg-textual-column"><spring:message code="failedTestList.test"/></th>
                                <th id="testLevelTop5FailedTestByPage" scope="col" class="tg-textual-column"><spring:message code="level"/></th>
                                <th id="urlListTop5FailedTestByPage" scope="col" class="tg-textual-column"><spring:message code="failedTestList.urlListCsv"/></th>
                                <th id="nbOfPagesTop5FailedTestByPage" scope="col" class="tg-numerical-column"><spring:message code="failedTestList.nbOfPages"/></th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="failedTestByPageInfo" items="${failedTestInfoByPageSet}" varStatus="pFailedTestInfoByPageSet">
                            <tr>
                                <td headers="testTop5FailedTestByPage" class="tg-textual-column">
                                    <c:set var="abbrTitle">
                                        ${failedTestByPageInfo.testLabel} : <spring:message code="${failedTestByPageInfo.testCode}"/>
                                    </c:set>
                                    <abbr title="${fn:escapeXml(abbrTitle)}"  style="border-bottom: 1px dotted;cursor: help;">
                                        ${failedTestByPageInfo.testLabel}
                                    </abbr>
                                </td>
                                <td headers="testLevelTop5FailedTestByPage" class="tg-textual-column">
                                    <c:set var="testLevelCode">
                                        ${ref}-${failedTestByPageInfo.testLevelCode}
                                    </c:set>
                                    <fmt:message key="${testLevelCode}"/>
                                </td>
                                <td headers="urlListTop5FailedTestByPage" class="tg-textual-column">
                                    <a href="<c:url value="/home/contract/page-list.html?audit=${param.audit}&amp;status=f2xx&amp;sortDirection=2&amp;test=${failedTestByPageInfo.testLabel}"/>">
                                        <fmt:message key="failedTestList.getUrlListCsv"><fmt:param>${failedTestByPageInfo.testLabel}</fmt:param></fmt:message>
                                    </a>
                                </td>
                                <td headers="nbOfPagesTop5FailedTestByPage" class="tg-numerical-column">${failedTestByPageInfo.pageCounter}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div><!-- class="span16  tg-table-container"-->
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
                        <fmt:message key="failedTestList.h1">
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
        <script type="text/javascript" src="${jqueryTableSorterUrl}"></script>
        <script type="text/javascript" src="${accessibleTableSorterJsUrl}"></script>
        <script>
            $(document).ready(function() {
                $('#testTop5FailedTestByPage').click();
            });
        </script>
    </body>
</html>
</compress:html>