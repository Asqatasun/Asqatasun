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
<c:choose>
    <c:when test="${not empty configProperties['cdnUrl']}">
        <c:set var="greedyAuditImgUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Images/greedy-audit.jpg"/>
        <c:set var="creativeCommonLogoUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Images/creative_common_logo.png"/>
    </c:when>
    <c:otherwise>
        <c:set var="greedyAuditImgUrl">
            <c:url value="/Images/greedy-audit.jpg"/>  
        </c:set>
        <c:set var="creativeCommonLogoUrl">
            <c:url value="/Images/creative_common_logo.png"/>
        </c:set>
    </c:otherwise>
</c:choose>
<html lang="${lang}">
    <c:set var="pageTitle" scope="page">
        <fmt:message key="greedyAudit.pageTitle">
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
                <fmt:message key="greedyAudit.h1"/>
            </c:set>
            <ul class="breadcrumb">
                <li><a href="<c:url value="/home.html"/>"><fmt:message key="home.h1"/></a> <span class="divider"></span></li>
                <li><a href="<c:url value="/home/contract.html?cr=${param.cr}"/>">${contractName}</a> <span class="divider"></span></li>
                <c:choose>
                    <c:when test="${isPageAudit == true}">
                        <li><a href="<c:url value="/home/contract/audit-page-set-up.html?cr=${param.cr}"/>"><fmt:message key="auditSetUpPage.h1"/></a> <span class="divider"></span></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="<c:url value="/home/contract/audit-upload-set-up.html?cr=${param.cr}"/>"><fmt:message key="auditSetUpUpload.h1"/></a> <span class="divider"></span></li>
                    </c:otherwise>
                </c:choose>
                <li class="active">${pageName}</li>
            </ul>
            <div class="row">
                <div class="span16">
                    <h1>
                        <fmt:message key="greedyAudit.h1"/>
                    </h1>
                </div><!-- class="span16" -->
            </div><!-- class="row" -->
            <div class="row">
                <div class="span16">
                    <div class="alert-message block-message success">
                        <p>
                            <fmt:message key="greedyAudit.message">
                                <fmt:param>${testedUrl}</fmt:param>
                            </fmt:message>
                        </p>
                        <p>
                            <fmt:message key="greedyAudit.refresh"/>
                        <c:if test="${isUserNotified == true}">
                            <fmt:message key="greedyAudit.sendEmail"/>
                        </c:if>
                        </p>
                        <div class="alert-actions">
                            <a href="<c:url value="/home/contract.html?cr=${param.cr}"/>" class="btn small"><fmt:message key="greedyAudit.backOnContract"/></a>
                        </div>
                    </div><!-- alert-message block-message success -->
                </div><!-- class="span15 offset1" -->
            </div><!-- class="row" -->
            <div class="row">
                <div class="span16 main-logo">
                    <img src="${greedyAuditImgUrl}" alt=""/>
                </div><!-- class="span16 main-logo" -->
            </div><!-- class="row" -->
            <div class="row">
                <div class="span4 offset9">
                    <a title="Creative Commons Attribution 3.0 License" href="http://creativecommons.org/licenses/by/3.0/">
                        <img src="${creativeCommonLogoUrl}" alt="License"/>
                    </a>
                    <a title="Flickr: Galerie de Patrick van IJzendoorn" href="http://www.flickr.com/photos/33630119@N07/">Patrick van IJzendoorn </a>
                </div><!-- class="span4 offset9" -->
            </div><!-- class="row" -->
        </div><!-- class="container"-->
        <%@include file="template/footer.jsp" %>
    </body>
</html>
</compress:html>