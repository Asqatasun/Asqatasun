<%@taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html>
<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
        <spring:message code="change-password.pageTitle"/>
        <spring:hasBindErrors name="userSignUpCommand">
                <spring:message code="change-password.errorPageTitle"/>
        </spring:hasBindErrors>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="tgm-change-password">
        <c:choose>
            <c:when test="${authenticatedUser != null}">
        <c:set var="displayLogoutLink" scope="page" value="true"/>
        <%@include file="template/header-utils.jsp" %>
        <div class="container">
            </c:when>
            <c:otherwise>
        <c:set var="isInNotAuthentifiedView" scope="page" value="true"/>
        <c:set var="addLogo" scope="page" value="true"/>
        <div class="container not-authentified">
        <%@include file="template/lang-box.jsp" %>
            </c:otherwise>
        </c:choose>
            <c:set var="pageName" scope="page">
                <fmt:message key="change-password.h1"/>
            </c:set>
            <c:if test="${authenticatedUser != null}">
            <ul class="breadcrumb">
                <li><a href="<c:url value="/home.html"/>"><fmt:message key="home.h1"/></a> <span class="divider"></span></li>
                <li><a href="<c:url value="/account-settings.html"/>"><fmt:message key="account-settings.h1"/></a> <span class="divider"></span></li>
                <li class="active">${pageName}</li>
            </ul>
            </c:if>
            <div class="row">
                <div class="span16">
                    <h1>${pageName}</h1>
                </div><!-- class="span6" -->
            </div><!-- class="row" -->
            <c:if test="${passwordModified == 'true'}">
            <div class="row">
                <div class="span16">
                    <div class="alert-message block-message success">
                        <p><fmt:message key="change-password.passwordModified"/></p>
                        <c:if test="${authenticatedUser == null}">
                        <div class="alert-actions">
                            <a class="btn small " href="<c:url value="/login.html"/>"><fmt:message key="forgotten-password-confirmation.backToLogin"/></a>
                        </div>
                        </c:if>
                    </div>
                </div><!-- class="span16"-->
            </div><!-- class="row"-->
            </c:if>
            <c:choose>
            <c:when test="${invalidChangePasswordUrl != 'true'}">
            <div class="row">
            <c:if test="${authenticatedUser != null}">
                <c:set var="currentPassword" scope="page">
                    <spring:message code="change-password.currentPassword"/>
                </c:set>
            </c:if>
            <c:set var="newPassword" scope="page">
                <spring:message code="change-password.newPassword"/>
            </c:set>
            <c:set var="confirmNewPassword" scope="page">
                <spring:message code="change-password.confirmNewPassword"/>
            </c:set>
                <div class="span15 offset1">
                    <div id="change-password-form">
                    <form:form method="post" modelAttribute="changePasswordCommand" acceptCharset="UTF-8" enctype="application/x-www-form-urlencoded">
                        <form:hidden path="userEmail"/>
                        <spring:hasBindErrors name="userSignUpCommand">
                        <div id="change-password-form-general-error">
                            <form:errors path="generalErrorMsg" cssClass="alert-message block-message error" element="div"/>
                        </div>
                        </spring:hasBindErrors>
                        <c:if test="${authenticatedUser != null}">
                        <c:set var="currentPasswordError"><form:errors path="currentPassword"/></c:set>
                        <c:if test="${not empty currentPasswordError}">
                            <c:set var="currentPasswordErrorClass" value="error"/>
                        </c:if>
                        <div class="clearfix ${currentPasswordErrorClass}">
                            <label id="change-password-current-password" for="currentPassword">${currentPassword}</label>
                            <div class="change-password-field input">
                                <form:password path="currentPassword" cssErrorClass="xlarge error" cssClass="xlarge"/>
                                <form:errors path="currentPassword" cssClass="alert-message error" />
                            </div>
                        </div><!-- class="clearfix"-->
                        </c:if>
                        <c:set var="newPasswordError"><form:errors path="newPassword"/></c:set>
                        <c:if test="${not empty newPasswordError}">
                            <c:set var="newPasswordErrorClass" value="error"/>
                        </c:if>
                        <div class="clearfix ${newPasswordErrorClass}">
                            <label id="change-password-new-password" for="newPassword">${newPassword}</label>
                            <div class="change-password-field input">
                                <form:password path="newPassword" cssErrorClass="xlarge error" cssClass="xlarge"/>
                                <form:errors path="newPassword" cssClass="alert-message error" />
                                <span class="help-block">
                                    <spring:message code="change-password.newPasswordRule"/>
                                </span>
                            </div>
                        </div><!-- class="clearfix"-->
                        <c:set var="confirmNewPasswordError"><form:errors path="confirmNewPassword"/></c:set>
                        <c:if test="${not empty confirmNewPasswordError}">
                            <c:set var="confirmNewPasswordErrorClass" value="error"/>
                        </c:if>
                        <div class="clearfix ${confirmNewPasswordErrorClass}">
                            <label id="change-password-confirm-new-password" for="confirmNewPassword">${confirmNewPassword}</label>
                            <div class="change-password-field input">
                                <form:password path="confirmNewPassword" cssErrorClass="xlarge error" cssClass="xlarge"/>
                                <form:errors path="confirmNewPassword" cssClass="alert-message error" />
                            </div>
                        </div><!-- class="clearfix"-->
                        <div id="change-password-form-submit" class="actions">
                            <input class="btn primary" type="submit" value="<spring:message code="change-password.saveChanges"/> &raquo;"/>
                        </div><!-- id="change-password-form-submit" class="action" -->
                    </form:form>
                    </div><!-- class="change-password-form"-->
                </div><!-- class="span14 offset1" -->
            </div><!-- class="row" -->
            </c:when>
            <c:otherwise>
            <div class="row">
                <div class="span14 offset1">
                    <div class="alert-message block-message error">
                        <p><fmt:message key="change-password.invalidChangePasswordUrl"/></p>
                        <div class="alert-actions">
                            <a class="btn small" href="<c:url value="/login.html"/>"><fmt:message key="forgotten-password-confirmation.backToLogin"/></a>
                        </div>
                    </div><!-- class="alert-message block-message error" -->
                </div><!-- class="span6" -->
            </div><!-- class="row" -->
            </c:otherwise>
            </c:choose>
        </div><!-- class="container" -->
        <%@include file="template/footer.jsp" %>
    </body>
</html>
</compress:html>