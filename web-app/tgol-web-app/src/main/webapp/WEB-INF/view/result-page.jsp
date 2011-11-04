<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri='/WEB-INF/cewolf.tld' prefix='cewolf' %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="${pageContext.response.locale}">
    <c:set var="pageTitle" scope="page">
        <fmt:message key="resultPage.pageTitle">
            <fmt:param>
                ${statistics.url}
            </fmt:param>
        </fmt:message>
    </c:set>
    <c:set var="addWebSnapr" scope="page" value="true"/>
    <%@include file="template/head.jsp" %>
    <body id="tgm-result-page" class="tgm">
        <div id="meta-border">
            <%@include file="template/header-utils.jsp" %>
            <c:set var="pageName" scope="page">
                <fmt:message key="resultPage.h1"/>
            </c:set>
            <%@include file="template/breadcrumb.jsp" %>
            <div class="yui3-g">
                <div class="yui3-u-1">
                    <h1>
                        ${pageName}
                    </h1>
                </div>
            </div>
        <c:choose>
            <c:when test="${status == 'COMPLETED'}">
            <div class="yui3-g">
                <div class="yui3-u-1 cml cmr">
                    <ol>
                        <li><a href="#synthesis"><fmt:message key="resultPage.synthesis"/></a></li>
                        <li><a href="#work-done"><fmt:message key="resultPage.detailedResultPage"/></a></li>
                        <li><a href="#source-code"><fmt:message key="resultPage.sourceCode"/></a></li>
                    </ol>
                </div>
            </div>
            <c:set var="showLegend" scope="request" value="true"/>
            <c:set var="showAxisLabel" scope="request" value="true"/>
            <c:set var="hasBarChartLink" scope="request" value="true"/>
            <c:set var="hasGraphics" scope="request" value="true"/>
            <c:set var="hasSynthesisTitle" scope="request" value="true"/>
            <c:set var="hasPageCounter" scope="request" value="false"/>
            <c:set var="hasPagesListLink" scope="request" value="false"/>
            <c:set var="hasResultDispatchTitle" scope="request" value="false"/>
            <c:import url="template/synthesis.jsp" />
            <c:set var="displayAlgorithm" scope="request" value="true"/>
            <c:set var="scope" scope="request" value="page"/>
            <c:import url="template/detailed-result.jsp" />
            <div class="yui3-g">
                <div class="yui3-u-1 cmr cml">
                    <c:import url="template/source-code.jsp" />
                </div><!-- id="yui3-u-1"-->
            </div><!-- id="yui3-g"-->
            </c:when>
            <c:otherwise>
            <div class="yui3-g" id="synthesis-result">
                <div class="yui3-u-1">
                    <div class="project-fail">
                        <fmt:message key="${status}"/>
                    </div>
                </div>
                <div class="yui3-u-1">
                    <table summary="Audit meta-data" id="result-meta">
                        <caption>Audit meta-data</caption>
                        <tr>
                            <th id="meta-url" scope="row" class="col01"><fmt:message key="resultPage.url"/></th>
                            <td class="col02"><a href="${statistics.url}">${statistics.url}</a></td>
                        </tr>
                        <tr>
                            <th id="meta-date" scope="row" class="col01"><fmt:message key="resultPage.date"/></th>
                            <td class="col02"><fmt:formatDate type="both" value="${statistics.date}" dateStyle="long" timeStyle="long"/></td>
                        </tr>
                    </table>
                </div>
                <div class="yui3-u-1">
                    <h2 id="top5-invalid-theme"><fmt:message key="errorLoadingPage.explanation"/></h2>
                </div>
                <c:choose>
                    <c:when test="${status == 'ERROR_LOADING'}">
                <div class="yui3-u-1">
                    <ul>
                        <li><fmt:message key="errorLoadingPage.explanationDetail1"/></li>
                        <li><fmt:message key="errorLoadingPage.explanationDetail2"/></li>
                        <li><fmt:message key="errorLoadingPage.explanationDetail3"/></li>
                    </ul>
                </div>
                    </c:when>
                    <c:when test="${status == 'ERROR_ADAPTING'}">
                <div class="yui3-u-1">
                    <ul>
                        <li><fmt:message key="errorProcessingPage.message"/></li>
                    </ul>
                </div>
                    </c:when>
                    <c:otherwise>
                <div class="yui3-u-1">
                    <ul>
                        <li><fmt:message key="oups.message"/></li>
                    </ul>
                </div>
                    </c:otherwise>
                </c:choose>
            </div>
            </c:otherwise>
        </c:choose>
        </div><!-- id="meta-border"-->
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
        <script type="text/javascript" src="http://asset.open-s.com/Corporate/TGOL/Js/audit-result.js"></script>
        <script type="text/javascript" src="http://asset.open-s.com/Corporate/TGOL/Js/audit-set-up.js"></script>
        <%@include file="template/footer.jsp" %>
    </body>
</html>