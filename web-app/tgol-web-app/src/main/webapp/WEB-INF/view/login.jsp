<%@taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html>
<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@page language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter" %>
<%@page import="org.springframework.web.context.request.RequestContextHolder" %>
<%@page import="org.springframework.web.context.request.ServletRequestAttributes" %>
<%@page import="java.util.Collection" %>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Set"%>
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
        <c:set var="tgLogoUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Images/Logo-tanaguru.com-white-75dpi-w78px-h35px-bgTransp.png"/>
        <c:set var="tgLogo1Url" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Images/Logo-Tanaguru-G-w500-h600-75dpi-bgTransp.png"/>
        <c:set var="tgLogo2Url" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Images/Logo-tanaguru.com-black-w140px-h63px-bgTransp.png"/>
    </c:when>
    <c:otherwise>
        <c:set var="tgLogoUrl">
            <c:url value="/Images/Logo-tanaguru.com-white-75dpi-w78px-h35px-bgTransp.png"/>  
        </c:set>
        <c:set var="tgLogo1Url">
            <c:url value="/Images/Logo-Tanaguru-G-w500-h600-75dpi-bgTransp.png"/>  
        </c:set>
        <c:set var="tgLogo2Url">
            <c:url value="/Images/Logo-tanaguru.com-black-w140px-h63px-bgTransp.png"/>  
        </c:set>
    </c:otherwise>
</c:choose>
<html lang="${lang}">
    <c:if test="${not empty param.error}">
        <c:choose>
            <c:when test="${param.error == 'sessionTimeout'}">
                <c:set scope="page" var="errorOnPage">
                    <fmt:message key="login.sessionTimeout"/>
                </c:set>
            </c:when>
            <c:when test="${param.error == 'errorOnLogin'}">
                <c:set scope="page" var="springException">
                    ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].class.simpleName}
                </c:set>
                <c:set scope="page" var="errorOnPage">
                    <fmt:message key="${springException}"/>
                </c:set>
            </c:when>
        </c:choose>
    </c:if>
    <c:set var="pageTitle" scope="page">
        <fmt:message key="login.pageTitle"/>
        <c:if test="${not empty errorOnPage}">
            - ${errorOnPage}
        </c:if>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="tgm-login">
        <div class="topbar">
            <div class="fill">
                <div class="container">
                    <c:choose>
                        <c:when test="${configProperties['enable-demo-link'] == 'true' && empty errorOnPage}">
                    <h1>
                    <img id="login-topbar-logo" class="brand" src="${tgLogoUrl}" alt="Tanaguru.com"/>
                    </h1>
                        </c:when>
                        <c:otherwise>
                    <img id="login-topbar-logo" class="brand" src="${tgLogoUrl}" alt=""/>
                        </c:otherwise>
                    </c:choose>
                    <ul class="nav secondary-nav">
                        <c:if test="${configProperties['enable-demo-link'] == 'true' && empty errorOnPage}">
                        <li>
                            <c:set var="inline" value="true"/>
                            <%@include file="template/login-form.jsp" %>
                        </li>
                        </c:if>
                        <li>
                            <%@include file="template/lang-box.jsp" %>
                        </li>
                    </ul>
                </div> <!-- class="container"-->
            </div> <!-- class="fill"-->
        </div> <!-- class="topbar"-->
        <div class="container">
            <div id="login-main-row" class="row">
                <div class="span6">
                    <div id="login-logo">
                        <img src="${tgLogo1Url}" alt="" />
                    </div>
                </div>
                <div class="span7 offset3">
                    <c:if test="${configProperties['enable-demo-link'] == 'false' || not empty errorOnPage}">
                        <h1>
                            <img src="${tgLogo2Url}" alt="Tanaguru.com" />
                        </h1>
                        <c:if test="${not empty errorOnPage}">
                        <div class="alert-message error">
                            ${errorOnPage}
                        </div>
                        </c:if>
                        <%@include file="template/login-form.jsp" %>
                    </c:if>
                    <c:if test="${configProperties['enable-demo-link'] == 'true' && empty errorOnPage}">
                    <div id="login-demo">
                        <form id="login-demo-form" method="post" action="<c:url value="/demo.html"/>" >
                            <input type="submit" name="Login-Demo" value="<fmt:message key="login.demo"/>"/>
                        </form>
                    </div><!-- id="login-demo" -->
                    </c:if>
                    <c:if test="${configProperties['enable-sign-up'] == 'true'}">
                    <div id="login-sign-up">
                        <a class="awesome big magenta" href="sign-up.html"><fmt:message key="login.sign-up"/></a>
                    </div>
                    </c:if>
                </div><!-- class="span8 offset4" -->
            </div><!-- class="row" -->
        </div><!-- class="container" -->
        <%@include file="template/footer.jsp" %>
    </body>
</html>
</compress:html>