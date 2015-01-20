<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="email" scope="page">
    <fmt:message key="account-settings.email"/>
</c:set>
<c:set var="lastName" scope="page">
    <fmt:message key="account-settings.lastName"/>
</c:set>
<c:set var="firstName" scope="page">
    <fmt:message key="account-settings.firstName"/>
</c:set>
<c:set var="phoneNumber" scope="page">
    <fmt:message key="account-settings.phoneNumber"/>
</c:set>
<c:set var="activated" scope="page">
    <fmt:message key="account-settings.activated"/>
</c:set>
<c:set var="activated" scope="page">
    <spring:message code="add-user.activated"/>
</c:set>
<c:set var="admin" scope="page">
    <spring:message code="add-user.admin"/>
</c:set>
<c:set var="authenticatedUserEmail" scope="page">
    <sec:authentication property="principal.username"/>
</c:set>
<c:set var="currentModifiedUserEmail" scope="page" value="${createUserCommand.email}"/>
<c:set var="inhibActivateAndAdmin" scope="page" value="false"/>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <c:if test="${createUserCommand.email eq (fn:replace(fn:replace(fn:replace(fn:replace(authenticatedUserEmail, '&#46;', '.'), '&#45;','-'),'&#95;','_'),'&#64;', '@'))}">
        <c:set var="inhibActivateAndAdmin" scope="page" value="true"/>
    </c:if>
</sec:authorize>
<div class="span16">
    <div id="account-settings-form">
        <form:form method="post" 
                   modelAttribute="createUserCommand" 
                   acceptCharset="UTF-8" 
                   enctype="application/x-www-form-urlencoded">
            <spring:hasBindErrors name="createUserCommand">
            <div id="sign-up-form-general-error">
                <form:errors path="generalErrorMsg" 
                             cssClass="alert-message block-message error" 
                             element="div"/>
            </div>
            </spring:hasBindErrors>
            <c:set var="lastNameError">
                <form:errors path="lastName"/>
            </c:set>
            <c:if test="${not empty lastNameError}">
                <c:set var="lastNameErrorClass" value="error"/>
            </c:if>
            <div class="clearfix ${lastNameErrorClass}">
                <label id="account-settings-last-name" 
                       for="lastName">
                    ${lastName}
                </label>
                <div class="input account-settings-field" >
                    <form:input path="lastName" 
                                cssErrorClass="xlarge error" 
                                cssClass="xlarge"/>
                    <form:errors path="lastName" 
                                 cssClass="alert-message error" />
                </div>
            </div><!-- class="clearfix"-->
            <c:set var="firstNameError">
                <form:errors path="firstName"/>
            </c:set>
            <c:if test="${not empty firstNameError}">
                <c:set var="firstNameErrorClass" value="error"/>
            </c:if>
            <div class="clearfix ${firstNameErrorClass}">
                <label id="account-settings-email-first-name" 
                       for="firstName">${firstName}
                </label>
                <div class="input account-settings-field" >
                    <form:input path="firstName" 
                                cssErrorClass="xlarge error" 
                                cssClass="xlarge"/>
                    <form:errors path="firstName" 
                                 cssClass="alert-message error" />
                </div>
            </div><!-- class="clearfix"-->
            <div class="clearfix">
                <label id="account-settings-email" 
                       for="email">${email}</label>
                <div class="input account-settings-field" >
                    <c:choose>
                        <c:when test="${activateEmailField == 'false'}">
                    <form:input path="email" 
                                cssErrorClass="xlarge error" 
                                cssClass="xlarge uneditable-input" 
                                readonly="true"/>
                    <form:errors path="email" cssClass="alert-message error" />
                    <span class="help-block">
                        <spring:message code="account-settings.email-rule"/>
                    </span>
                        </c:when>
                        <c:otherwise>
                    <form:input path="email" 
                                cssErrorClass="xlarge error" 
                                cssClass="xlarge"/>
                    <form:errors path="email" 
                                 cssClass="alert-message error" />
                        </c:otherwise>
                    </c:choose>    
                </div>
            </div><!-- class="clearfix"-->
            <c:set var="phoneNumberError">
                <form:errors path="phoneNumber"/>
            </c:set>
            <c:if test="${not empty phoneNumberError}">
                <c:set var="phoneNumberErrorClass" value="error"/>
            </c:if>
            <div class="clearfix ${phoneNumberErrorClass}">
                <label id="account-settings-phone-number" 
                       for="phoneNumber">
                    ${phoneNumber}
                </label>
                <div class="input account-settings-field">
                    <form:input path="phoneNumber" 
                                cssErrorClass="xlarge error" 
                                cssClass="xlarge"/>
                    <form:errors path="phoneNumber" 
                                 cssClass="alert-message error" />
                </div>
            </div><!-- class="clearfix"-->
            <c:if test="${addActivatedField eq 'true'}">
            <div class="clearfix ">
                <label id="account-settings-activated" 
                       for="activated1">
                    ${activated}
                </label>
                <div class="input account-settings-field">
                    <c:choose>
                        <c:when test="${inhibActivateAndAdmin}">
                    <form:checkbox path="activated" 
                                   cssErrorClass="xlarge error" 
                                   cssClass="xlarge uneditable-input" 
                                   disabled="true" />
                        </c:when>
                        <c:otherwise>
                    <form:checkbox path="activated" 
                                   cssErrorClass="xlarge error" 
                                   cssClass="xlarge"/>                                            
                        </c:otherwise>
                    </c:choose>
                </div>
            </div><!-- class="clearfix"-->
            </c:if>
            <c:if test="${addAdminField eq 'true'}">
            <div class="clearfix ">
                <label id="account-settings-admin" 
                       for="admin1">
                    ${admin}
                </label>
                <div class="input account-settings-field">
                    <c:choose>
                        <c:when test="${inhibActivateAndAdmin}">
                    <form:checkbox path="admin" 
                                   cssErrorClass="xlarge error" 
                                   cssClass="xlarge uneditable-input" 
                                   disabled="true"/>
                        </c:when>
                        <c:otherwise>
                    <form:checkbox path="admin" 
                                   cssErrorClass="xlarge error" 
                                   cssClass="xlarge"/>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div><!-- class="clearfix"-->
            </c:if>
            <div id="change-password-link" class="actions">
                <a class="btn large" 
                   href="<c:url value="${changePasswordUrl}"/>">
                    <fmt:message key="account-settings.changePassword"/>
                </a>
            </div>
            <div id="account-settings-form-submit" class="actions">
                <input class="btn primary" 
                       type="submit" 
                       value="<spring:message code="account-settings.saveChanges"/> &raquo;"/>
            </div>
        </form:form>
    </div><!-- id="account-settings-form" -->
</div><!-- class="span16" -->