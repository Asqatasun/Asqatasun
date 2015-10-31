<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://tagutils" prefix="tg" %>
<!DOCTYPE html>

<c:set var="tgLogoUrl">
    <c:url value="/Images/Logo-tanaguru.com-white-75dpi-w78px-h35px-bgTransp.png"/>  
</c:set>

<html lang="${tg:lang(pageContext)}">
    <c:set var="pageTitle" scope="page">
        <fmt:message key="forgotten-password-confirmation.pageTitle"/>
    </c:set>
    <%@include file="template/head.jsp" %>
    <link rel="stylesheet" type="text/css" href="<c:url value="/Css/tgm-login.css"/>" />
    <body id="tgm-forgotten-password-confirmation">
        <div class="topbar">
            <div class="fill">
                <div class="container">
                    <a href="<c:url value="/login.html"/>" title="<fmt:message key="sign-up.backToLogin"/>">
                        <img id="login-topbar-logo" src="${tgLogoUrl}" alt="<fmt:message key="sign-up.backToLogin"/>" />
                    </a>
                    <ul class="nav secondary-nav">
                        <li>
                            <%@include file="template/lang-box.jsp" %>
                        </li>
                    </ul>
                </div> <!-- class="container"-->
            </div> <!-- class="fill"-->
        </div> <!-- class="topbar"-->
        <div class="container">
            <div class="row">
                <div class="span16">
                    <h1><fmt:message key="forgotten-password-confirmation.h1"/></h1>
                </div><!-- class="span16" -->
            </div><!-- class="row" -->
            <div class="row">
                <div class="span10 offset3">
                    <div class="alert-message block-message success">
                        <p><fmt:message key="forgotten-password-confirmation.message"><fmt:param value="${url}"/></fmt:message></p>
                        <div class="alert-actions">
                            <a class="btn small" href="<c:url value="/login.html"/>"><fmt:message key="forgotten-password-confirmation.backToLogin"/></a>
                        </div>
                    </div>
                </div><!-- class="span15 offset1" -->
            </div><!-- class="row" -->
        </div><!--  class="container" -->
        <%@include file="template/footer.jsp" %>
    </body>
</html>
</compress:html>