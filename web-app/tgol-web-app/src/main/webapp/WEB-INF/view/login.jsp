<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@page language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@page import="org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="${pageContext.response.locale}">
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
    <body id="tgm-login" class="tgm">
        <div id="meta-border">
            <c:set var="displayLogoutLink" scope="page" value="false"/>
            <c:set var="displaySignUpLink" scope="page" value="true"/>
            <%@include file="template/header-utils.jsp" %>
            <div class="yui3-g">
                <div class="yui3-u-1">
                    <h1>
                        <fmt:message key="login.h1"/>
                    </h1>
                </div><!-- class="yui3-u-1" -->
            </div><!-- class="yui3-g" -->
            <c:if test="${not empty errorOnPage}">
            <div class="yui3-g">
                <div class="yui3-u-1-2"></div>
                <div class="yui3-u-1-2">
                    <div class="error">${errorOnPage}</div>
                </div>
            </div>
            </c:if>
            <form method="post" action="<c:url value="j_spring_security_check"/>">
                <div class="yui3-g" id="login-user">
                    <div class="yui3-u-1-2" id="login-user-label">
                        <label for="j_username"><fmt:message key="login.id"/></label>
                    </div>
                    <div class="yui3-u-1-2" id="login-user-input">
                        <div>
                            <input type="text" name="j_username" id="j_username" <c:if test="${not empty param.login_error}"> value="<%= session.getAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_LAST_USERNAME_KEY)%>"</c:if> />
                        </div>
                    </div>
                </div>
                <div class="yui3-g" id="login-pwd">
                    <div class="yui3-u-1-2" id="login-pwd-label">
                        <label for="j_password"><spring:message code="login.password"/></label>
                    </div>
                    <div class="yui3-u-1-2" id="login-pwd-input">
                        <div>
                            <input type="password" name="j_password" id="j_password" />
                            <c:if test="${configProperties['enable-account-settings'] == 'true'}">
                            <a href="<c:url value="/forgotten-password.html"/>"> <spring:message code="login.passwordForgotten"/></a>
                            </c:if>
                        </div>
                    </div>
                </div>
                <div class="yui3-g" id="login-submit">
                    <div class="yui3-u-1-2" id="login-submit-button">
                        <input type="submit" name="Login" value="<spring:message code="login.submit"/>"/>
                    </div>
                </div>
            </form>
            <c:if test="${configProperties['enable-sign-up'] == 'true'}">
            <div class="yui3-g" id="login-sign-up">
                <div class="yui3-u-3-4"></div>
                <div class="yui3-u-1-4">
                    <a href="sign-up.html"><spring:message code="login.sign-up"/></a>
                </div>
            </div>
            </c:if>
        </div>
        <%@include file="template/footer.jsp" %>
    </body>
</html>

