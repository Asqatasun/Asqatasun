<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://tagutils" prefix="tg" %>
<!DOCTYPE html>
<%@include file="/WEB-INF/view/template/template_variables.jspf" %>

<!-- external js -->
<c:set var="jqueryUrl">
<c:url value="/public/external_js/jquery-1.11.1.min.js?v${asqatasunVersion}"/>
</c:set>
<c:set var="jqueryUIUrl">
<c:url value="/public/external_js/jquery-ui-1.10.1.custom.min.js?v${asqatasunVersion}"/>
</c:set>

<!-- internal js -->
<c:set var="auditSetUpDetailsJsUrl">
<c:url value="/public/js/expand-collapse/audit-set-up-details-min.js?v${asqatasunVersion}"/>
</c:set>
<c:set var="progressBarJsUrl">
<c:url value="/public/js/progress-bar/progress-bar-min.js?v${asqatasunVersion}"/>
</c:set>

<!-- external images -->
<c:set var="processingImgUrl" scope="request">
<c:url value="/public/images/processing.gif?v${asqatasunVersion}"/>
</c:set>

<html lang="${tg:lang(pageContext)}">
    <c:set var="pageTitle" scope="page">
        <spring:message code="auditSetUpPage.pageTitle"/>
        <spring:hasBindErrors name="auditSetUpCommand">
            <spring:message code="auditSetUp.errorPageTitle"/>
        </spring:hasBindErrors>
    </c:set>
    <c:set var="addJqueryUI" scope="request" value="true"/>
    <%@include file="template/head.jsp" %>
    <body id="tgm-page-set-up">
        <%@include file="template/header-utils.jsp" %>
        <div class="container">
            <c:set var="pageName" scope="page">
                <spring:message code="auditSetUpPage.h1"/>
            </c:set>
            <ul class="breadcrumb">
                <li><a href="<c:url value="/home.html"/>"><fmt:message key="home.h1"/></a> <span class="divider"></span></li>
                <li><a href="<c:url value="/home/contract.html?cr=${param.cr}"/>">${contractName}</a> <span class="divider"></span></li>
                <li class="active">${pageName}</li>
            </ul>
            <div class="row">
                <div class="span16">
                    <h1><spring:message code="auditSetUpPage.h1"/></h1>
                </div><!-- class="span16" -->
                <%@include file="template/set-up.jsp" %>
            </div><!-- class="row" -->
        </div><!-- class="container" -->
        <%@include file="template/footer.jsp" %>
        <script type="text/javascript" src="${jqueryUrl}"></script>
        <script type="text/javascript" src="${jqueryUIUrl}"></script>
        <script type="text/javascript" src="${auditSetUpDetailsJsUrl}"></script>
        <script type="text/javascript" src="${progressBarJsUrl}"></script>        
    </body>
</html>
</compress:html>
