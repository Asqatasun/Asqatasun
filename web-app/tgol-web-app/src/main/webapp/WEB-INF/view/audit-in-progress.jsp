<%@taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html>
<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
        <fmt:message key="auditInProgress.pageTitle">
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
                <fmt:message key="auditInProgress.h1"/>
            </c:set>
            <ul class="breadcrumb">
                <li><a href="<c:url value="/home.html"/>"><fmt:message key="home.h1"/></a> <span class="divider"></span></li>
                <li><a href="<c:url value="/home/contract.html?cr=${param.cr}"/>">${contractName}</a> <span class="divider"></span></li>
                <li><a href="<c:url value="/home/contract/audit-site-set-up.html?cr=${param.cr}"/>"><fmt:message key="auditSetUpSite.h1"/></a> <span class="divider"></span></li>
                <li class="active">${pageName}</li>
            </ul>
            <div class="row">
                <div class="span16">
                    <h1>
                        <fmt:message key="auditInProgress.h1"/>
                    </h1>
                </div><!-- class="span16" -->
            </div><!-- class="row" -->
            <div class="row">
                <div class="span14 offset1">
                    <div class="alert-message block-message success">
                        <p>
                            <fmt:message key="auditInProgress.message">
                                <fmt:param>${testedUrl}</fmt:param>
                            </fmt:message>
                        </p>
                        <div class="alert-actions">
                            <a href="<c:url value="/dispatch.html" />" class="btn small"><fmt:message key="accessDeniedPage.backToHome"/></a>
                        </div>
                    </div><!-- alert-message block-message success -->
                </div><!-- class="span15 offset1" -->
            </div><!-- class="row" -->
            <div class="row">
                <div class="span16 main-logo">
                    <img src="<c:url value="/Images/work-in-progress.jpg"/>" alt=""/>
                </div><!-- class="span16 main-logo" -->
            </div><!-- class="row" -->
            <div class="row">
                <div class="span4 offset11">
                    <a title="Creative Commons Attribution 3.0 License" href="http://creativecommons.org/licenses/by/3.0/">
                        <img src="<c:url value="/Images/creative_common_logo.png"/>" alt="License"/>
                    </a>
                    <a title="Flickr: Galerie de blumpy" href="http://www.flickr.com/photos/blumpy/">blumpy</a>
                </div><!-- class="span4 offset9" -->
            </div><!-- class="row" -->
        </div><!-- class="container"-->
        <%@include file="template/footer.jsp" %>
    </body>
</html>
</compress:html>