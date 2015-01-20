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

<!-- external js -->
<c:set var="jqueryUrl">
    <c:url value="/External-Js/jquery-1.9.1.min.js"/>
</c:set>

<!-- internal js -->
<c:set var="auditSetUpDetailsJsUrl">
    <c:url value="/Js/expand-collapse/audit-set-up-details-min.js"/>
</c:set>

<html lang="${tg:lang(pageContext)}">
    <c:set var="pageTitle" scope="page">
        <fmt:message key="auditSetUpScenario.pageTitle"/>
        <spring:hasBindErrors name="auditSetUpCommand">
            <fmt:message key="auditSetUp.errorPageTitle"/>
        </spring:hasBindErrors>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="tgm-site-set-up">
        <%@include file="template/header-utils.jsp" %>
        <div class="container">
            <c:set var="pageName" scope="page">
                <fmt:message key="auditSetUpScenario.h1"/>
            </c:set>
            <ul class="breadcrumb">
                <li><a href="<c:url value="/home.html"/>"><fmt:message key="home.h1"/></a> <span class="divider"></span></li>
                <li><a href="<c:url value="/home/contract.html?cr=${param.cr}"/>">${contractName}</a> <span class="divider"></span></li>
                <li>
                    <a href="<c:url value="/home/contract/scenario-management.html?cr=${param.cr}"/>">
                        <fmt:message key="scenarioManagement.pageTitle">
                            <fmt:param>${contractName}</fmt:param>
                        </fmt:message>    
                    </a> <span class="divider"></span>
                </li>
                <li class="active">${pageName}</li>
            </ul>
            <div class="row">
                <div class="span16">
                    <h1>
                        <fmt:message key="auditSetUpScenario.h1"/>
                    </h1>
                </div><!-- class="span16" -->
                <c:if test="${defaultParamSet == 'false'}">
                <div class="span16">
                    <div class="alert-message block-message warning">
                        <fmt:message key="auditSetUp.keptLastAuditParameters">
                            <fmt:param>${scenario.label}</fmt:param>
                        </fmt:message>
                    </div><!-- class="alert-message warning" -->
                </div><!-- class="span14 offset1" -->
                </c:if>
                <div class="span16">
                    <div class="alert-message block-message info">
                        <p>
                            <fmt:message key="auditSetUpScenario.message">
                                <fmt:param>${scenario.label}</fmt:param>
                            </fmt:message>
                        </p>
                    </div>
                </div><!-- class="span16" -->
                <c:set var="postUrl" scope="page">
                    <c:url value="/home/contract/launch-audit-scenario.html?cr=${contractIdValue}&ampsc=${param.sc}"/>
                </c:set>
                <%@include file="template/set-up.jsp" %>
            </div><!-- class="row" -->
        </div><!-- class="container"-->
    <%@include file="template/footer.jsp" %>
    <script type="text/javascript" src="${jqueryUrl}"></script>
    <script type="text/javascript" src="${auditSetUpDetailsJsUrl}"></script>
    </body>
</html>
</compress:html>