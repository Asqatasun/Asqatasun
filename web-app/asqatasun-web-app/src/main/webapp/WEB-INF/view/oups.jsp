<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://tagutils" prefix="tg" %>
<!DOCTYPE html>

<c:set var="oupsImg1Url">
    <c:url value="/Images/oups1.jpg"/>  
</c:set>
<c:set var="oupsImg2Url">
    <c:url value="/Images/oups2.jpg"/>  
</c:set>
<c:set var="oupsImg3Url">
    <c:url value="/Images/oups3.jpg"/>  
</c:set>
<c:set var="creativeCommonLogoUrl">
    <c:url value="/Images/creative_common_logo.png"/>
</c:set>

<html lang="${tg:lang(pageContext)}">
    <c:set var="pageTitle" scope="page">
        <fmt:message key="oups.pageTitle"/>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="tgm-oups">
        <%@include file="template/header-utils.jsp" %>
        <div class="container no-bg-container">
            <div class="row">
                <div class="span16">
                    <div class="alert-message block-message">
                        <h1>
                            <fmt:message key="oups.h1"/>
                        </h1>
                        <p><fmt:message key="oups.message"/></p>
                        <div class="alert-actions">
                            <a href="<c:url value="/dispatch.html" />" class="btn small"><fmt:message key="accessDeniedPage.backToHome"/></a>
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
                    <img src="${oupsImg2Url}" alt=""/>
                </div><!-- class="span16 main-logo" -->
            </div><!-- class="row" -->
            <div class="row">
                <div class="span4 offset9">
                    <a title="Creative Commons Attribution 3.0 License" href="http://creativecommons.org/licenses/by/3.0/">
                        <img src="${creativeCommonLogoUrl}" alt="License"/>
                    </a>
                    <a title="Flickr: Galerie de Dr Case" href="http://www.flickr.com/photos/14192845@N03/">Dr Case</a>
                </div><!-- class="span4 offset9" -->
            </div><!-- class="row" -->
            </c:when>
            <c:when test="${fn:contains(sessionId, '1')}">
            <div class="row">
                <div class="span16 main-logo">
                    <img src="${oupsImg1Url}" alt=""/>
                </div><!-- class="span16 main-logo" -->
            </div><!-- class="row" -->
            <div class="row">
                <div class="span4 offset11">
                    <a title="Creative Commons Attribution 3.0 License" href="http://creativecommons.org/licenses/by/3.0/">
                        <img src="${creativeCommonLogoUrl}" alt="License"/>
                    </a>
                    <a title="Flickr: Galerie de Be holder" href="http://www.flickr.com/photos/14192845@N03/">Be holder</a>
                </div><!-- class="span4 offset9" -->
            </div><!-- class="row" -->            
            </c:when>
            <c:otherwise>
            <div class="row">
                <div class="span16 main-logo">
                    <img src="${oupsImg3Url}" alt=""/>
                </div><!-- class="span16 main-logo" -->
            </div><!-- class="row" -->
            <div class="row">
                <div class="span4 offset9">
                    <a title="Creative Commons Attribution 3.0 License" href="http://creativecommons.org/licenses/by/3.0/">
                        <img src="${creativeCommonLogoUrl}" alt="License"/>
                    </a>
                    <a title="Flickr: Galerie de OliBac" href="http://www.flickr.com/photos/olibac/">OliBac</a>
                </div><!-- class="span4 offset9" -->
            </div><!-- class="row" -->
            </c:otherwise>
        </c:choose>
        </div><!-- class="container" -->
        <%@include file="template/footer.jsp" %>
    </body>
</html>
</compress:html>