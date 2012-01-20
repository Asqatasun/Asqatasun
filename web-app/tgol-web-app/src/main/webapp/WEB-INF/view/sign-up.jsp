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
        <spring:message code="sign-up.pageTitle"/>
        <spring:hasBindErrors name="userSignUpCommand">
            <spring:message code="sign-up.errorPageTitle"/>
        </spring:hasBindErrors>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="tgm-sign-up">
        <div class="container">
            <c:set var="isInNotAuthentifiedView" scope="page" value="true"/>
            <c:set var="addLogo" scope="page" value="true"/>
            <%@include file="template/lang-box.jsp" %>
            <div class="row">
                <div class="span16">
                    <h1><spring:message code="sign-up.h1"/></h1>
                </div><!-- class="span16" -->
                <div class="span15 offset1">
                    <div id="mandatory-elements-message" class="alert-message block-message warning">
                        <spring:message code="sign-up.mandatoryElementsMessage"/>
                    </div><!-- id="mandatory-elements-message" class="alert-message block-message warning"-->
                </div><!-- class="span12" -->
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
                <div class="span15 offset1">
                    <div id="sign-up-form">
                        <form:form method="post" modelAttribute="userSignUpCommand" acceptCharset="UTF-8" enctype="application/x-www-form-urlencoded">
                            <spring:hasBindErrors name="userSignUpCommand">
                            <div id="sign-up-form-general-error">
                                <form:errors path="generalErrorMsg" cssClass="alert-message block-message error" element="div"/>
                            </div>
                            </spring:hasBindErrors>
                            <c:set var="urlError"><form:errors path="siteUrl"/></c:set>
                            <c:if test="${not empty urlError}">
                                <c:set var="urlErrorClass" value="error"/>
                            </c:if>
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
                        <div class="actions" id="sign-up-submit">
                            <input class="btn primary" type="submit" value="<spring:message code="sign-up.subscribe"/> &raquo;"/>
                        </div><!-- class="actions" -->
                        </form:form>
                    </div> <!-- id="sign-in-form" -->
                </div><!-- class="span14 offset1" -->
            </div><!-- class="row" -->
        </div><!-- class="container" -->
        <%@include file="template/footer.jsp" %>
    </body>
</html>
</compress:html>