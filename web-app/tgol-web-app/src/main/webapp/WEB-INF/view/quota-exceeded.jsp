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
        <c:set var="quotaExceededImgUrl" value="${configProperties['cdnUrl']}/Images/quota_exceeded2.jpg"/>
        <c:set var="creativeCommonLogoUrl" value="${configProperties['cdnUrl']}/Images/creative_common_logo.png"/>
    </c:when>
    <c:otherwise>
        <c:set var="quotaExceededImgUrl">
            <c:url value="/Images/quota_exceeded.jpg"/>  
        </c:set>
        <c:set var="creativeCommonLogoUrl">
            <c:url value="/Images/creative_common_logo.png"/>
        </c:set>
    </c:otherwise>
</c:choose>
<html lang="${lang}">
    <c:set var="pageTitle" scope="page">
        <fmt:message key="quota-exceeded.pageTitle"/>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="tgm-quota-exceeded">
        <%@include file="template/header-utils.jsp" %>
        <div class="container no-bg-container">
            <div class="row">
                <div class="span16">
                    <div class="alert-message block-message">
                        <h1>
                            <fmt:message key="quota-exceeded.h1"/>
                        </h1>
                        <p>
                            <fmt:message key="quota-exceeded.message"/>
                        </p>
                        <div class="alert-actions">
                            <a href="<c:url value="/dispatch.html" />" class="btn small"><fmt:message key="accessDeniedPage.backToHome"/></a>
                        </div><!-- class="alert-actions"-->
                    </div><!-- class="alert-message block-message"-->
                </div><!-- class="span16" -->
            </div><!-- class="row" -->
            <div class="row">
                <div class="span16 main-logo">
                    <img src="${quotaExceededImgUrl}" alt=""/>
                </div><!-- class="span16 main-logo" -->
            </div><!-- class="row" -->
            <div class="row">
                <div class="span4 offset11">
                    <a title="Creative Commons Attribution 3.0 License" href="http://creativecommons.org/licenses/by/3.0/">
                        <img src="${creativeCommonLogoUrl}" alt="License"/>
                    </a>
                    <a title="Flickr: Galerie de Squiggle" href="http://www.flickr.com/photos/stephenr/">Squiggle</a>
                </div><!-- class="span4 offset9" -->
            </div><!-- class="row" -->
        </div><!-- class="container" -->
       <%@include file="template/footer.jsp" %>
    </body>
</html>
</compress:html>