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
        <c:set var="notDemoAvailable1ImgUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Images/access_denied2.jpg"/>
        <c:set var="notDemoAvailable2ImgUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Images/access_denied2.JPG"/>
        <c:set var="creativeCommonLogoUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Images/creative_common_logo.png"/>
    </c:when>
    <c:otherwise>
        <c:set var="notDemoAvailable1ImgUrl">
            <c:url value="/Images/access_denied1.jpg"/>  
        </c:set>
        <c:set var="notDemoAvailable2ImgUrl">
            <c:url value="/Images/access_denied2.JPG"/>  
        </c:set>
        <c:set var="creativeCommonLogoUrl">
            <c:url value="/Images/creative_common_logo.png"/>
        </c:set>
    </c:otherwise>
</c:choose>
<html lang="${lang}">
    <c:set var="pageTitle" scope="page">
        <fmt:message key="noDemoAvailablePage.pageTitle"/>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="tgm-access-denied">
        <%@include file="template/header-utils.jsp" %>
        <div class="container no-bg-container">
            <div class="row">
                <div class="span16">
                    <div class="alert-message block-message">
                        <h1>
                            <fmt:message key="noDemoAvailablePage.message"/>
                        </h1>
                        <div class="alert-actions">
                            <a href="<c:url value="/login.html" />" class="btn small"><fmt:message key="noDemoAvailablePage.backToHome"/></a>
                        </div><!-- class="alert-actions"-->
                    </div><!-- class="alert-message block-message"-->
                </div><!-- class="span16" -->
            </div><!-- class="row" -->
            <c:set var="sessionId" scope="page">
                <%=session.getId()%>
            </c:set>
            <c:choose>
            <c:when test="${fn:contains(sessionId, 'A')}">
            <div class="row">
                <div class="span16 main-logo">
                    <img src="${notDemoAvailable2ImgUrl}" alt=""/>
                </div><!-- class="span16 main-logo" -->
            </div><!-- class="row" -->
            </c:when>
            <c:otherwise>
            <div class="row">
                <div class="span16 main-logo">
                    <img src="${notDemoAvailable2ImgUrl}" alt=""/>
                </div><!-- class="span16 main-logo" -->
            </div><!-- class="row" -->
            <div class="row">
                <div class="span4 offset10">
                    <a title="Creative Commons Attribution 3.0 License" href="http://creativecommons.org/licenses/by/3.0/">
                        <img src="${creativeCommonLogoUrl}" alt="License"/>
                    </a>
                    <a title="Flickr: Galerie de Yung Grasshopper" href="http://www.flickr.com/photos/yung-grasshopper/with/5196300452/">Yung Grasshopper</a>
                </div><!-- class="span4 offset9" -->
            </div><!-- class="row" -->
            </c:otherwise>
            </c:choose>
        </div><!-- class="container" -->
        <%@include file="template/footer.jsp" %>
    </body>
</html>
</compress:html>