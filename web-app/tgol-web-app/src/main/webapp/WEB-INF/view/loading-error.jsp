<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import = "java.util.Date,java.text.SimpleDateFormat,java.text.ParseException"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://tagutils" prefix="tg" %>
<!DOCTYPE html>

<c:set var="loadingErrorImgUrl">
    <c:url value="/Images/error_loading.jpg"/>  
</c:set>
<c:set var="creativeCommonLogoUrl">
    <c:url value="/Images/creative_common_logo.png"/>
</c:set>

<html lang="${tg:lang(pageContext)}">
    <c:set var="pageTitle" scope="page">
        <fmt:message key="errorLoadingPage.pageTitle"/>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="tgm-loading-error no-bg-container">
        <%@include file="template/header-utils.jsp" %>
        <div class="container no-bg-container">
            <div class="row">
                <div class="span16">
                    <div class="alert-message block-message">
                        <h1><fmt:message key="errorLoadingPage.message"/></h1>
                        <p>
                            <fmt:message key="resultPage.url"/> <a href="${auditUrl}">${auditUrl}</a>
                        </p>
                        <p>
                            <fmt:message key="resultPage.date"/> <fmt:formatDate type="both" value="${auditDate}" dateStyle="long" timeStyle="medium"/>
                        </p>
                        <p id="error-loading-explanation"><fmt:message key="errorLoadingPage.explanation"/></p>
                        <ul>
                            <li><fmt:message key="errorLoadingPage.explanationPageDetail1"/></li>
                            <li><fmt:message key="errorLoadingPage.explanationPageDetail2"/></li>
                            <li><fmt:message key="errorLoadingPage.explanationPageDetail3"/></li>
                            <li><fmt:message key="errorLoadingPage.explanationPageDetail4"/></li>
                        </ul>
                        <div class="alert-actions">
                            <a href="<c:url value="/dispatch.html" />" class="btn small"><fmt:message key="accessDeniedPage.backToHome"/></a>
                        </div><!-- class="alert-actions"-->
                    </div><!-- class="alert-message block-message"-->
                </div><!-- class="span16" -->
            </div><!-- class="row" -->
            <div class="row">
                <div class="span16 main-logo">
                    <img src="${loadingErrorImgUrl}" alt=""/>
                </div><!-- class="span16 main-logo" -->
            </div><!-- class="row" -->
            <div class="row">
                <div class="span4 offset10">
                    <a title="Creative Commons Attribution 3.0 License" href="http://creativecommons.org/licenses/by/3.0/">
                        <img src="${creativeCommonLogoUrl}" alt="License"/>
                    </a>
                    <a title="Flickr: Galerie de ABC Archives" href="http://www.flickr.com/photos/abcarchives/">ABC Archives</a>
                </div><!-- class="span4 offset9" -->
            </div><!-- class="row" -->
        </div><!-- class="container" -->
       <%@include file="template/footer.jsp" %>
    </body>
</html>
</compress:html>