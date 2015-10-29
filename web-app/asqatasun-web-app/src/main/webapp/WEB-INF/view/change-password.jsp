<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"  %>
<%@ taglib uri="http://tagutils" prefix="tg" %>
<!DOCTYPE html>
<html lang="${tg:lang(pageContext)}">
    <c:choose>
        <c:when test="${changeUserPasswordFromAdmin == true}">
            <c:set var="pageTitle" scope="page">
                <spring:message code="change-password.pageTitleForUser" arguments="${userName}"/>
                <spring:hasBindErrors name="changePasswordCommand">
                    <spring:message code="change-password.errorPageTitle"/>
                </spring:hasBindErrors>
            </c:set>
        </c:when>
        <c:otherwise>
            <c:set var="pageTitle" scope="page">
                <spring:message code="change-password.pageTitle"/>
                <spring:hasBindErrors name="changePasswordCommand">
                        <spring:message code="change-password.errorPageTitle"/>
                </spring:hasBindErrors>
            </c:set>
        </c:otherwise>
    </c:choose>
    <c:set var="authenticated" scope="request" value="false"/>
    <sec:authorize access="isAuthenticated()">
        <c:set var="authenticated" scope="request" value="true"/>
    </sec:authorize>
    <%@include file="template/head.jsp" %>
        <c:choose>
            <c:when test="${authenticated}">
    <body id="tgm-change-password">                
        <c:set var="displayLogoutLink" scope="page" value="true"/>
        <c:if test="${changeUserPasswordFromAdmin}">
            <c:set var="adminActive" value="true"/>
        </c:if>
        <%@include file="template/header-utils.jsp" %>
        <div class="container">
            <c:if test="${not empty modifiableTestWeightRefs}">
            <div id="navSecondaryLevel">
                <ul class="pills">
                    <li class="active">
                        <a href="<c:url value="/account-settings.html"/>">
                            <fmt:message key="account-settings.accountSettings"/>
                        </a>
                    </li>
                    <c:forEach items="${modifiableTestWeightRefs}" var="ref">
                    <li>
                        <a href="<c:url value="/test-weight.html?ref=${ref}"/>">
                            <fmt:message key="test-weight.h1">
                                <fmt:param>
                                    ${ref}
                                </fmt:param>
                            </fmt:message>
                        </a>
                    </li>
                    </c:forEach>
                </ul>
            </div>
            </c:if>
            </c:when>
            <c:otherwise>
        <link rel="stylesheet" type="text/css" href="<c:url value="/Css/tgm-login.css"/>" />
        <body id="tgm-reset-password">
            <div class="topbar">
                <div class="fill">
                    <div class="container">
                        <a href="<c:url value="/login.html"/>" title="<fmt:message key="sign-up.backToLogin"/>">
                            <img id="login-topbar-logo" 
                                 src="<c:url value="/Images/Logo-tanaguru.com-white-75dpi-w78px-h35px-bgTransp.png"/> " 
                                 alt="<fmt:message key="sign-up.backToLogin"/>" />
                        </a>
                        <ul class="nav secondary-nav">
                            <li>
                                <%@include file="template/lang-box.jsp" %>
                            </li>
                        </ul>
                    </div> <!-- class="container"-->
                </div> <!-- class="fill"-->
            </div> <!-- class="topbar"-->
        <div class="container not-authentified">
            </c:otherwise>
        </c:choose>
            <c:set var="pageName" scope="page">
                <fmt:message key="change-password.h1"/>
            </c:set>
            <c:choose>
                <c:when test="${changeUserPasswordFromAdmin}">
            <ul class="breadcrumb">
                <li><a href="<c:url value="/home.html"/>"><fmt:message key="home.h1"/></a> <span class="divider"></span></li>
                <li><a href="<c:url value="/admin.html"/>"><fmt:message key="admin.h1"/></a> <span class="divider"></span></li>
                <li>
                    <a href="<c:url value="/admin/edit-user.html?user=${user}"/>">
                        <fmt:message key="edit-user.h1">
                            <fmt:param>${userName}</fmt:param>
                        </fmt:message>
                    </a> 
                    <span class="divider"></span>
                </li>
                <li class="active">${pageName}</li>
            </ul>                    
                </c:when>
                <c:when test="${authenticated}">
            <ul class="breadcrumb">
                <li><a href="<c:url value="/home.html"/>"><fmt:message key="home.h1"/></a> <span class="divider"></span></li>
                <c:if test="${not empty modifiableTestWeightRefs}">
                <li>
                    <a href="<c:url value="/account-settings.html"/>">
                        <fmt:message key="account-settings.accountSettings"/>
                    </a>
                    <span class="divider"></span>
                </li>
                </c:if>
                <li><a href="<c:url value="/account-settings.html"/>"><fmt:message key="account-settings.h1"/></a> <span class="divider"></span></li>
                <li class="active">${pageName}</li>
            </ul>
            </c:when>
            </c:choose>   
            <div class="row">
                <div class="span16">
                    <h1>${pageName}</h1>
                </div><!-- class="span6" -->
            </div><!-- class="row" -->
            <c:if test="${passwordModified == 'true'}">
            <div class="row">
                <div class="span14 offset1">
                    <div class="alert-message block-message success">
                        <p><fmt:message key="change-password.passwordModified"/></p>
                        <c:if test="${not authenticated}">
                        <div class="alert-actions">
                            <a class="btn small " href="<c:url value="/login.html"/>"><fmt:message key="forgotten-password-confirmation.backToLogin"/></a>
                        </div>
                        </c:if>
                    </div>
                </div><!-- class="span16"-->
            </div><!-- class="row"-->
            </c:if>
        <c:choose>
            <c:when test="${passwordModified && not authenticated}"></c:when>
            <c:when test="${not invalidChangePasswordUrl}">
            <div class="row">
            <c:if test="${authenticated && not changeUserPasswordFromAdmin}">
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
                        <spring:hasBindErrors name="changePasswordCommand">
                        <div id="change-password-form-general-error">
                            <form:errors path="generalErrorMsg" cssClass="alert-message block-message error" element="div"/>
                        </div>
                        </spring:hasBindErrors>
                        <c:if test="${authenticated && not changeUserPasswordFromAdmin}">
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