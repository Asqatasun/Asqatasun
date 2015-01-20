<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://tagutils" prefix="tg" %>
<!DOCTYPE html>

<c:set var="auditInProgressImgUrl">
    <c:url value="/Images/work-in-progress.jpg"/>  
</c:set>
<c:set var="creativeCommonLogoUrl">
    <c:url value="/Images/creative_common_logo.png"/>
</c:set>

<c:choose>
    <c:when test="${sc!=null && scenarioName!=null}">
        <c:set var="i18nPrefix" value="auditScenarioInProgress"/>
        <c:set var="testedUrl" value="${scenarioName}"/>
    </c:when>
    <c:otherwise>
        <c:set var="i18nPrefix" value="auditSiteInProgress"/>
    </c:otherwise> 
</c:choose>
<html lang="${tg:lang(pageContext)}">
    <c:set var="pageTitle" scope="page">
        <fmt:message key="${i18nPrefix}.pageTitle">
            <fmt:param>
                ${testedUrl}
            </fmt:param>
        </fmt:message>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="tgm-audit-in-progress">
        <%@include file="template/header-utils.jsp" %>
        <div class="container">
            <c:set var="pageName" scope="page">
                <fmt:message key="${i18nPrefix}.h1"/>
            </c:set>
            <ul class="breadcrumb">
                <li><a href="<c:url value="/home.html"/>"><fmt:message key="home.h1"/></a> <span class="divider"></span></li>
                <li><a href="<c:url value="/home/contract.html?cr=${param.cr}"/>">${contractName}</a> <span class="divider"></span></li>
                <c:choose>
                    <c:when test="${i18nPrefix == 'auditScenarioInProgress'}">
                <li>
                    <a href="<c:url value="/home/contract/scenario-management.html?cr=${param.cr}"/>">
                        <fmt:message key="scenarioManagement.pageTitle">
                            <fmt:param>${contractName}</fmt:param>
                        </fmt:message>
                    </a> <span class="divider"></span>
                </li>
                <li>
                    <a href="<c:url value="/home/contract/audit-scenario-set-up.html?cr=${param.cr}&amp;sc=${sc}"/>">
                            <fmt:message key="auditSetUpScenario.h1"/>
                    </a> <span class="divider"></span>
                </li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="<c:url value="/home/contract/audit-site-set-up.html?cr=${param.cr}"/>"><fmt:message key="auditSetUpSite.h1"/></a> <span class="divider"></span></li>
                    </c:otherwise>
                </c:choose>
                <li class="active">${pageName}</li>
            </ul>
            <div class="row">
                <div class="span16">
                    <h1>
                        ${pageName}
                    </h1>
                </div><!-- class="span16" -->
            </div><!-- class="row" -->
            <div class="row">
                <div class="span16">
                    <div class="alert-message block-message success">
                        <p>
                            <fmt:message key="${i18nPrefix}.message">
                                <fmt:param>${testedUrl}</fmt:param>
                            </fmt:message>
                        </p>
                        <div class="alert-actions">
                            <a href="<c:url value="/home/contract.html?cr=${param.cr}" />" class="btn small"><fmt:message key="${i18nPrefix}.backOnContract"/></a>
                        </div>
                    </div><!-- alert-message block-message success -->
                </div><!-- class="span16" -->
            </div><!-- class="row" -->
            <div class="row">
                <div class="span16 main-logo">
                    <img src="${auditInProgressImgUrl}" alt=""/>
                </div><!-- class="span16 main-logo" -->
            </div><!-- class="row" -->
            <div class="row">
                <div class="span4 offset11">
                    <a title="Creative Commons Attribution 3.0 License" href="http://creativecommons.org/licenses/by/3.0/">
                        <img src="${creativeCommonLogoUrl}" alt="License"/>
                    </a>
                    <a title="Flickr: Galerie de blumpy" href="http://www.flickr.com/photos/blumpy/">blumpy</a>
                </div><!-- class="span4 offset9" -->
            </div><!-- class="row" -->
        </div><!-- class="container"-->
        <%@include file="template/footer.jsp" %>
    </body>
</html>
</compress:html>