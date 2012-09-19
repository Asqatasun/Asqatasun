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
        <c:set var="tgLogo1Url" value="$${pageContext.request.scheme}://${configProperties['cdnUrl']}/Images/Logo-Tanaguru-G-w500-h600-75dpi-bgTransp.png"/>
        <c:set var="tgLogo2Url" value="$${pageContext.request.scheme}://${configProperties['cdnUrl']}/Images/Logo-tanaguru.com-black-w140px-h63px-bgTransp.png"/>
    </c:when>
    <c:otherwise>
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
                    <spring:message code="login.sessionTimeout"/>
                </c:set>
            </c:when>
            <c:when test="${param.error == 'errorOnLogin'}">
                <c:set scope="page" var="springException">
                    ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].class.simpleName}
                </c:set>
                <c:set scope="page" var="errorOnPage">
                    <spring:message code="${springException}"/>
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
        <div class="container">
            <c:set var="isInNotAuthentifiedView" scope="page" value="true"/>
            <%@include file="template/lang-box.jsp" %>
            <div id="login-main-row" class="row">
                <div class="span6">
                    <div id="login-logo">
                        <img src="${tgLogo1Url}" alt="" />
                    </div>
                </div>
                <div class="span7 offset3">
                    <h1>
                        <img src="${tgLogo2Url}" alt="Tanaguru.com" />
                    </h1>
                    <c:if test="${not empty errorOnPage}">
                    <div class="alert-message error">
                        ${errorOnPage}
                    </div>
                    </c:if>
                    <div id="login-form">
                        <form method="post" action="<c:url value="j_spring_security_check"/>" class="form-stacked">
                            <div class="clearfix">
                                <div id="login-user-label">
                                    <label for="j_username"><fmt:message key="login.id"/></label>
                                </div>
                                <div id="login-user-input" class="input">
                                    <input type="text" class="xlarge" name="j_username" id="j_username" <c:if test="${not empty param.login_error}"> value="<%= session.getAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_LAST_USERNAME_KEY)%>"</c:if> />
                                </div>
                            </div>
                            <div class="clearfix">
                                <div id="login-pwd-label">
                                    <label for="j_password"><spring:message code="login.password"/></label>
                                </div>
                                <div id="login-pwd-input" class="input">
                                    <div>
                                        <input type="password" name="j_password" id="j_password" class="xlarge" />
                                        <c:if test="${configProperties['enable-account-settings'] == 'true'}">
                                        <span class="help-block"><a href="<c:url value="/forgotten-password.html"/>"> <spring:message code="login.passwordForgotten"/></a></span>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                            <div class="actions" id="login-submit">
                                <input class="btn" type="submit" name="Login" value="<spring:message code="login.submit"/>"/>
                            </div>
                        </form>
                    </div><!-- id="login-form" -->
                    <c:if test="${configProperties['enable-demo-link'] == 'true'}">
                    <div id="login-demo" class="alert-message">
                        <p>
                            <spring:message code="login.demo"/>
                        </p>
                        <form id="login-demo-form" method="post" action="<c:url value="j_spring_security_check"/>" >
                            <div>
                                <input id="j_username_demo" type="hidden" value="guest" name="j_username">
                                <input id="j_password_demo" type="hidden" value="guest" name="j_password">
                                <input class="btn" type="submit" value="<spring:message code="login.onlineDemo"/>" name="Login">
                            </div>
                        </form>
                    </div><!-- id="login-demo" -->
                    </c:if>
                    <c:if test="${configProperties['enable-sign-up'] == 'true'}">
                    <div id="login-sign-up">
                        <a class="awesome big magenta" href="sign-up.html"><spring:message code="login.sign-up"/></a>
                    </div>
                    </c:if>
                </div><!-- class="span8 offset4" -->
            </div><!-- class="row" -->
        </div><!-- class="container" -->
        <%@include file="template/footer.jsp" %>
    </body>
</html>
</compress:html>