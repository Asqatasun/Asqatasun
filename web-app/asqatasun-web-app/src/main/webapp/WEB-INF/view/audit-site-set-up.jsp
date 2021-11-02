<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://tagutils" prefix="tg" %>
<!DOCTYPE html>
<%@include file="/WEB-INF/view/template/template_variables.jspf" %>

<c:set var="jqueryUrl">
    <c:url value="/public/external_js/jquery-1.11.1.min.js?v${asqatasunVersion}"/>
</c:set>
<c:set var="auditSetUpDetailsJsUrl">
    <c:url value="/public/js/expand-collapse/audit-set-up-details-min.js?v${asqatasunVersion}"/>
</c:set>

<html lang="${tg:lang(pageContext)}">
    <c:set var="pageTitle" scope="page">
        <fmt:message key="auditSetUpSite.pageTitle"/>
        <spring:hasBindErrors name="auditSetUpCommand">
            <fmt:message key="auditSetUp.errorPageTitle"/>
        </spring:hasBindErrors>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="tgm-site-set-up">
        <%@include file="template/header-utils.jsp" %>
        <main class="container">
            <c:set var="pageName" scope="page">
                <fmt:message key="auditSetUpSite.h1"/>
            </c:set>
            <ul class="breadcrumb">
                <li><a href="<c:url value="/home.html"/>"><fmt:message key="home.h1"/></a> <span class="divider"></span></li>
                <li><a href="<c:url value="/home/contract.html?cr=${param.cr}"/>">${contractName}</a> <span class="divider"></span></li>
                <li class="active">${pageName}</li>
            </ul>
            <div class="row">
                <div class="span16">
                    <h1>
                        <fmt:message key="auditSetUpSite.h1"/>
                    </h1>
                </div><!-- class="span16" -->
                <c:choose>
                    <c:when test="${url == ''}">
                        <div class="alert-message block-message error span16">
                            <h2><fmt:message key="auditSetUp.projectConfigurationError"/> </h2>
                            <p><fmt:message key="auditSetUp.errorNoSiteURL"/>    </p>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${defaultParamSet == 'false'}">
                            <div class="span16">
                                <div class="alert-message block-message warning">
                                    <fmt:message key="auditSetUp.keptLastAuditParameters">
                                        <fmt:param>${url}</fmt:param>
                                    </fmt:message>
                                </div><!-- class="alert-message warning" -->
                            </div><!-- class="span16" -->
                        </c:if>
                        <div class="span16">
                            <div class="alert-message block-message info">
                                URL : <a rel="noreferrer noopener" href="${url}">${url}</a>
                                <br>
                                <p><fmt:message key="auditSetUp.message"/></p>
                            </div>
                        </div><!-- class="span16" -->
                        <c:set var="postUrl" scope="page">
                            <c:url value="/home/contract/launch-audit-site.html?cr=${contractIdValue}"/>
                        </c:set>
                        <%@include file="template/set-up.jsp" %>
                    </c:otherwise>
                </c:choose>
            </div><!-- class="row" -->
        </main><!-- class="container"-->
    <%@include file="template/footer.jsp" %>
    <script type="text/javascript" src="${jqueryUrl}"></script>
    <script type="text/javascript" src="${auditSetUpDetailsJsUrl}"></script>
    </body>
</html>
</compress:html>
