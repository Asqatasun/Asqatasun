<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://tagutils" prefix="tg" %>
<!DOCTYPE html>
<%@include file="/WEB-INF/view/template/template_variables.jspf" %>

<!-- external js -->
<c:set var="jqueryUrl">
    <c:url value="/public/external_js/jquery-1.11.1.min.js"/>
</c:set>        
<c:set var="codePrettifierJsUrl" scope="request">
    <c:url value="/public/external_js/prettify.min.js"/>
</c:set> 
<c:set var="jqueryTableSorterUrl" scope="request">
    <c:url value="/public/external_js/jquery.tablesorter.min.js"/>
</c:set> 

<!-- internal js -->
<c:set var="displayExternalImgJsUrl" scope="request">
    <c:url value="/public/js/result-page/add-img-snapshot-min.js"/>
</c:set>        
<c:set var="prettyPrintJsUrl" scope="request">
    <c:url value="/public/js/result-page/pretty-print-min.js"/>
</c:set> 
<c:set var="accessibleTableSorterJsUrl" scope="page">
    <c:url value="/public/js/table-sorter/accessible-table-sorter-min.js"/>
</c:set>

<!-- external images -->
<c:set var="testInfoLinkImg" scope="request">
    <c:url value="/public/images/test-info-link.png"/>
</c:set> 
<c:set var="algoLinkImg" scope="request">
    <c:url value="/public/images/algo-link.png"/>
</c:set> 
<c:set var="sourceCodeImg" scope="request">
    <c:url value="/public/images/html-source-icon.png"/>
</c:set>

<html lang="${tg:lang(pageContext)}">
    <c:set var="pageTitle" scope="page">
        <fmt:message key="testResultPage.pageTitle">
            <fmt:param>
                ${testLabel}
            </fmt:param>
            <fmt:param>
                ${url}
            </fmt:param>
        </fmt:message>
    </c:set>
    <c:set var="addJqueryUI" scope="request" value="true"/>
    <%@include file="template/head.jsp" %>
    <body id="tgm-test-result-page">
        <%@include file="template/header-utils.jsp" %>
        <div class="container">
            <c:set var="pageName" scope="page">
                <fmt:message key="testResultPage.h1">
                    <fmt:param>
                        ${testLabel}
                    </fmt:param>
                    <fmt:param>
                        <a href="${url}">${url}</a>
                    </fmt:param>
                </fmt:message>
            </c:set>
            <ul class="breadcrumb">
                <li><a href="<c:url value="/home.html"/>"><fmt:message key="home.h1"/></a> <span class="divider"></span></li>
                <li><a href="<c:url value="/home/contract.html?cr=${cr}"/>">${contractName}</a> <span class="divider"></span></li>
            <c:choose>
                <c:when test="${siteScopeTestDetails == 'true'}">
                    <c:set var="auditSynthesisName" scope="page">
                        <fmt:message key="synthesisSite.h1">
                            <fmt:param>
                                ${audit}
                            </fmt:param>
                        </fmt:message>
                    </c:set>
                <li><a href="<c:url value="/home/contract/audit-synthesis.html?audit=${audit}"/>">${auditSynthesisName}</a> <span class="divider"></span></li>
                <li><a href="<c:url value="/home/contract/site-result.html?wr=${param.wr}"/>"><fmt:message key="resultSite.h1"/></a> <span class="divider"></span></li>
                </c:when>
                <c:otherwise>
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
                <li><a href="<c:url value="/home/contract/page-list.html?audit=${audit}&amp;status=f2xx"/>"><fmt:message key="pageList.f2xx.h1"/></a> <span class="divider"></span></li>
                    </c:if>
                <li><a href="<c:url value="/home/contract/page-result.html?wr=${param.wr}"/>"><fmt:message key="resultPage.h1"/></a> <span class="divider"></span></li>
                </c:otherwise>
            </c:choose>
                <li class="active">${pageTitle}</li>
            </ul>
            <div class="row">
                <div class="span16">
                    <h1>
                        ${pageName}
                    </h1>
                </div>
            </div><!-- class="row"-->
            <c:choose>
                <c:when test="${siteScopeTestDetails == 'true'}">
                    <c:set var="backUrl">
                        <c:url value="/home/contract/site-result.html?wr=${param.wr}"/>
                    </c:set>
                </c:when>
                <c:otherwise>
                    <c:set var="backUrl">
                        <c:url value="/home/contract/page-result.html?wr=${param.wr}"/>
                    </c:set>
                </c:otherwise>
            </c:choose>        
            <div class="row">
                <div class="span16 back">
                    <a href="${backUrl}" class="back-link">
                        <fmt:message key="testResultPage.backToAuditResultByTest"/>
                    </a>
                </div>
            </div><!-- class="row"-->
            <c:set var="counterByThemeMap" scope="request" value="${statistics.counterByThemeMap}"/>
            <c:set var="addShowHide" scope="request" value="false"/>
            <c:import url="template/detailed-result.jsp" />
            <div class="row">
                <div class="span16 back">
                    <a href="${backUrl}" class="back-link">
                        <fmt:message key="testResultPage.backToAuditResultByTest"/>
                    </a>
                </div>
            </div><!-- class="row"-->
        </div><!-- id="container"-->
        <%@include file="template/footer.jsp" %>
        <script type="text/javascript" src="${jqueryUrl}"></script>
        <script type="text/javascript" src="${displayExternalImgJsUrl}"></script>
        <script type="text/javascript" src="${codePrettifierJsUrl}"></script>
        <script type="text/javascript" src="${prettyPrintJsUrl}"></script>
        <script type="text/javascript" src="${jqueryTableSorterUrl}"></script>
        <script type="text/javascript" src="${accessibleTableSorterJsUrl}"></script>
    </body>
</html>
</compress:html>
