<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>                

<div class="span16">
    <div id="mandatory-elements-message" class="alert-message block-message warning">
        <spring:message code="sign-up.mandatoryElementsMessage"/>
    </div><!-- id="mandatory-elements-message" class="alert-message block-message warning"-->
</div><!-- class="span16" -->
<c:set var="siteToAudit" scope="page">
    <spring:message code="sign-up.siteToAudit"/>
</c:set>
<c:set var="email" scope="page">
    <spring:message code="sign-up.email"/>
</c:set>
<c:set var="lastName" scope="page">
    <spring:message code="sign-up.lastName"/>
</c:set>
<c:set var="firstName" scope="page">
    <spring:message code="sign-up.firstName"/>
</c:set>
<c:set var="phoneNumber" scope="page">
    <spring:message code="sign-up.phoneNumber"/>
</c:set>
<c:set var="password" scope="page">
    <spring:message code="sign-up.password"/>
</c:set>
<c:set var="confirmPassword" scope="page">
    <spring:message code="sign-up.confirmPassword"/>
</c:set>
<c:set var="activated" scope="page">
    <spring:message code="add-user.activated"/>
</c:set>
<c:set var="admin" scope="page">
    <spring:message code="add-user.admin"/>
</c:set>
<div class="span16">
    <div id="sign-up-form">
        <form:form method="post" modelAttribute="createUserCommand" acceptCharset="UTF-8" enctype="application/x-www-form-urlencoded">
            <spring:hasBindErrors name="createUserCommand">
            <div id="sign-up-form-general-error">
                <form:errors path="generalErrorMsg" cssClass="alert-message block-message error" element="div"/>
            </div>
            </spring:hasBindErrors>
            <c:set var="urlError"><form:errors path="siteUrl"/></c:set>
            <c:if test="${not empty urlError}">
                <c:set var="urlErrorClass" value="error"/>
            </c:if>
            <c:if test="${addUrlField == 'true'}">
            <div class="clearfix ${urlErrorClass}">
                <label id="sign-up-site-url" for="siteUrl">${siteToAudit}</label>
                <div class="input sign-up-field">
                    <form:input path="siteUrl" cssErrorClass="xlarge error" cssClass="xlarge"/>
                    <form:errors path="siteUrl" cssClass="alert-message error" />
                    <span class="help-block">
                        <spring:message code="sign-up.siteToAudit-rule"/>
                    </span>
                </div>
            </div><!-- class="clearfix"-->
            </c:if>
            <c:set var="emailError"><form:errors path="email"/></c:set>
            <c:if test="${not empty emailError}">
                <c:set var="emailErrorClass" value="error"/>
            </c:if>
            <div class="clearfix ${emailErrorClass}">
                <label id="sign-up-email" for="email">${email}</label>
                <div class="input sign-up-field">
                    <form:input path="email" cssErrorClass="xlarge error" cssClass="xlarge"/>
                    <form:errors path="email" cssClass="alert-message error"/>
                    <span class="help-block">
                        <spring:message code="sign-up.email-rule"/>
                    </span>
                </div>
            </div><!-- class="clearfix"-->
            <c:set var="passwordError"><form:errors path="password"/></c:set>
            <c:if test="${not empty passwordError}">
                <c:set var="passwordErrorClass" value="error"/>
            </c:if>
            <div class="clearfix ${passwordErrorClass}">
                <label id="sign-up-password" for="password">${password}</label>
                <div class="input sign-up-field">
                    <form:password path="password" cssErrorClass="xlarge error" cssClass="xlarge"/>
                    <form:errors path="password" cssClass="alert-message error"/>
                    <span class="help-block">
                        <spring:message code="sign-up.passwordRule"/>
                    </span>
                </div>
            </div><!-- class="clearfix"-->
            <c:set var="confirmPasswordError"><form:errors path="confirmPassword"/></c:set>
            <c:if test="${not empty confirmPasswordError}">
                <c:set var="confirmPasswordErrorClass" value="error"/>
            </c:if>
            <div class="clearfix ${confirmPasswordErrorClass}">
                <label id="sign-up-confirm-password" for="confirmPassword">${confirmPassword}</label>
                <div class="input sign-up-field">
                    <form:password path="confirmPassword" cssErrorClass="xlarge error" cssClass="xlarge"/>
                    <form:errors path="confirmPassword" cssClass="alert-message error"/>
                </div>
            </div><!-- class="clearfix"-->
            <c:set var="lastNameError"><form:errors path="lastName"/></c:set>
            <c:if test="${not empty lastNameError}">
                <c:set var="lastNameErrorClass" value="error"/>
            </c:if>
            <div class="clearfix ${lastNameErrorClass}}">
                <label id="sign-up-last-name" for="lastName">${lastName}</label>
                <div class="sign-up-field input">
                    <form:input path="lastName" cssErrorClass="xlarge error" cssClass="xlarge"/>
                    <form:errors path="lastName" cssClass="alert-message error"/>
                </div>
            </div><!-- class="clearfix"-->
            <c:set var="firstNameError"><form:errors path="firstName"/></c:set>
            <c:if test="${not empty firstNameError}">
                <c:set var="firstNameErrorClass" value="error"/>
            </c:if>
            <div class="clearfix ${firstNameErrorClass}">
                <label id="sign-up-email-first-name" for="firstName">${firstName}</label>
                <div class="input sign-up-field">
                    <form:input path="firstName" cssErrorClass="xlarge error" cssClass="xlarge"/>
                    <form:errors path="firstName" cssClass="alert-message error"/>
                </div>
            </div><!-- class="clearfix"-->
            <c:set var="phoneNumberError"><form:errors path="phoneNumber"/></c:set>
            <c:if test="${not empty phoneNumberError}">
                <c:set var="phoneNumberErrorClass" value="error"/>
            </c:if>
            <div class="clearfix ${phoneNumberErrorClass}">
                <label id="sign-up-phone-number" for="phoneNumber">${phoneNumber}</label>
                <div class="sign-up-field input" >
                    <form:input path="phoneNumber" cssErrorClass="xlarge error" cssClass="xlarge"/>
                    <form:errors path="phoneNumber" cssClass="alert-message error"/>
                </div>
            </div><!-- class="clearfix"-->
            <c:if test="${addActivatedField == 'true'}">
            <div class="clearfix ${urlErrorClass}">
                <label id="sign-up-activated" for="activated1">${activated}</label>
                <div class="input sign-up-field">
                    <form:checkbox path="activated" cssErrorClass="xlarge error" cssClass="xlarge"/>
                </div>
            </div><!-- class="clearfix"-->
            </c:if>
            <c:if test="${addAdminField == 'true'}">
            <div class="clearfix ${urlErrorClass}">
                <label id="sign-up-admin" for="admin1">${admin}</label>
                <div class="input sign-up-field">
                    <form:checkbox path="admin" cssErrorClass="xlarge error" cssClass="xlarge"/>
                </div>
            </div><!-- class="clearfix"-->
            </c:if>
        <div class="actions" id="sign-up-submit">
            <input class="btn primary" type="submit" value="${validateButtonName}"/>
        </div><!-- class="actions" -->
        </form:form>
    </div> <!-- id="sign-in-form" -->
</div><!-- class="span16" -->