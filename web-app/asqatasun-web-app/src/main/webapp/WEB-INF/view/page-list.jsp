<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://tagutils" prefix="tg" %>
<!DOCTYPE html>
<%@include file="/WEB-INF/view/template/template_variables.jspf" %>

<!-- external js-->
<c:set var="jqueryUrl">
    <c:url value="/public/external_js/jquery-1.11.1.min.js?v${asqatasunVersion}"/>
</c:set>
<c:set var="d3JsUrl" scope="request">
    <c:url value="/public/external_js/d3.v3.min.js?v${asqatasunVersion}"/>
</c:set> 
<c:set var="r2d3JsUrl" scope="request">
    <c:url value="/public/external_js/r2d3.v2.min.js?v${asqatasunVersion}"/>
</c:set> 

<!-- internal js-->
<c:set var="scoreJsUrl" scope="request">
    <c:url value="/public/js/score/score-min.js?v${asqatasunVersion}"/>
</c:set>
<c:set var="pageListScoreJsUrl" scope="request">
    <c:url value="/public/js/score/score-page-list-min.js?v${asqatasunVersion}"/>
</c:set>
<c:set var="scoreIEJsUrl" scope="request">
    <c:url value="/public/js/ie/score/score-ie-min.js?v${asqatasunVersion}"/>
</c:set>
<c:set var="pageListScoreIEJsUrl" scope="request">
    <c:url value="/public/js/ie/score/score-page-list-ie-min.js?v${asqatasunVersion}"/>
</c:set>
<c:set var="displaySnapshotJsUrl" scope="page">
    <c:url value="/public/js/snapshot/snapshot-min.js?v${asqatasunVersion}"/>
</c:set>
<html lang="${tg:lang(pageContext)}">
    <c:set var="pageTitle" scope="page">
        <fmt:message key="pageList.pageTitle">
            <fmt:param>
                ${param.wr}
            </fmt:param>
        </fmt:message>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="tgm-page-list">
        <%@include file="template/header-utils.jsp" %>
        <div class="container">
            <c:set var="pageName" scope="page">
                <fmt:message key="pageList.h1"/>
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
            <div class="row">
                <div class="span16">
                    <h1>
                        ${pageName}
                    </h1>
                </div><!-- class="span16"-->
            </div><!-- class="row"-->
            <c:set var="hasSynthesisTitle" scope="request" value="false"/>
            <c:set var="hasBarChartLink" scope="request" value="false"/>
            <c:set var="hasGraphics" scope="request" value="false"/>
            <c:set var="hasPageCounter" scope="request" value="true"/>
            <c:set var="hasPagesListLink" scope="request" value="false"/>
            <c:set var="hasResultDispatchTitle" scope="request" value="false"/>
            <c:import url="template/synthesis.jsp" />
            <div class="row">
                <div class="span16 tg-table-title">
                    <h2 id="page-list">
                        <fmt:message key="pageList.pageList"/>
                    </h2>
                </div><!-- class="span16"-->
                <div class="span16 tg-table-container">
                    <table id="page-list-table" class="tg-table">
                        <caption>Audit page list</caption>
                        <thead>
                            <tr>
                                <th id="httpStatus" class="tg-textual-column" scope="col"><fmt:message key="pageList.pagesRepartitionHeader"/></th>
                                <th id="nbOfPages" class="tg-numerical-column" scope="col"><fmt:message key="pageList.nbPagesHeader"/></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:choose>
                                <c:when test="${statistics.auditScope == 'DOMAIN'}">
                                    <c:set var="pageList2xxUrl" value="?audit=${param.audit}&amp;status=f2xx"/>
                                </c:when>
                                <c:when test="${statistics.auditScope == 'SCENARIO'}">
                                    <c:set var="pageList2xxUrl" value="?audit=${param.audit}&amp;status=f2xx&amp;sortCriterion=rank&amp;sortDirection=2"/>
                                </c:when>
                            </c:choose>
                            <tr>
                                <td class="tg-textual-column" headers="httpStatus">
                                    <a href="${pageList2xxUrl}"><fmt:message key="pageList.f2xx"/></a> <fmt:message key="pageList.f2xxDetails"/>
                                </td>
                                <td class="tg-numerical-column" headers="nbOfPages">${auditedPagesCount}</td>
                            </tr>
                            <tr>
                                <td class="tg-textual-column" headers="httpStatus">
                                    <a href="?audit=${param.audit}&amp;status=f3xx"><fmt:message key="pageList.f3xx"/></a> <fmt:message key="pageList.f3xxDetails"/>
                                </td>
                                <td class="tg-numerical-column" headers="nbOfPages">${redirectedPagesCount}</td>
                            </tr>
                            <tr>
                                <td class="tg-textual-column" headers="httpStatus">
                                    <a href="?audit=${param.audit}&amp;status=f4xx"><fmt:message key="pageList.f4xx"/></a> <fmt:message key="pageList.f4xxDetails"/>
                                </td>
                                <td class="tg-numerical-column" headers="nbOfPages">${errorPagesCount}</td>
                            </tr>
                            <tr>
                                <td class="tg-textual-column" headers="httpStatus">
                                    <a href="?audit=${param.audit}&amp;status=f9xx"><fmt:message key="pageList.f9xx"/></a> <fmt:message key="pageList.f9xxDetails"/>
                                </td>
                                <td class="tg-numerical-column" headers="nbOfPages">${relCanonicalPagesCount}</td>
                            </tr>
                            <tr>
                                <td  class="total-pages-line" headers="httpStatus">
                                    <fmt:message key="pageList.total"/>
                                </td>
                                <td class="total-pages-line" headers="nbOfPages">${statistics.pageCounter}</td>
                            </tr>
                        </tbody>
                    </table>
                </div><!-- id="span16 tg-table-container"-->
            </div><!-- id="row"-->
        </div><!-- class="container"-->
        <%@include file="template/footer.jsp" %>
        <script type="text/javascript" src="${jqueryUrl}"></script>
        <!--[if lte IE 8]>
        <script type="text/javascript" src="${r2d3JsUrl}"></script>
        <script type="text/javascript" src="${scoreIEJsUrl}"></script>
        <script type="text/javascript" src="${pageListScoreIEJsUrl}"></script>
        <![endif]-->
        <!--[if gt IE 8]>
        <script type="text/javascript" src="${d3JsUrl}"></script>
        <script type="text/javascript" src="${scoreJsUrl}"></script>
        <script type="text/javascript" src="${pageListScoreJsUrl}"></script>
        <![endif]-->
        <!--[if !IE]><!-->
        <script type="text/javascript" src="${d3JsUrl}"></script>
        <script type="text/javascript" src="${scoreJsUrl}"></script>
        <script type="text/javascript" src="${pageListScoreJsUrl}"></script>
        <!--<![endif]-->
        <script type="text/javascript" src="${displaySnapshotJsUrl}"></script>
    </body>
</html>
</compress:html>
