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
        <spring:message code="account-settings.pageTitle"/>
        <spring:hasBindErrors name="userSignUpCommand">
                <spring:message code="account-settings.errorPageTitle"/>
        </spring:hasBindErrors>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="tgm-account-settings">
        <%@include file="template/header-utils.jsp" %>
        <div class="container">
            <c:set var="pageName" scope="page">
                <fmt:message key="account-settings.h1"/>
            </c:set>
            <ul class="breadcrumb">
                <li><a href="<c:url value="/home.html"/>"><fmt:message key="home.h1"/></a> <span class="divider"></span></li>
                <li class="active">${pageName}</li>
            </ul>
            <div class="row">
                <div class="span16">
                    <h1>${pageName}</h1>
                </div>
                <c:if test="${accountDataUpdated == 'true'}">
                <div class="span14 offset1">
                    <div class="alert-message block-message success">
                        <p><fmt:message key="account-settings.accountDataUpdated"/></p>
                    </div>
                </div>
                </c:if>
                <c:set var="email" scope="page">
                    <spring:message code="account-settings.email"/>
                </c:set>
                <c:set var="lastName" scope="page">
                    <spring:message code="account-settings.lastName"/>
                </c:set>
                <c:set var="firstName" scope="page">
                    <spring:message code="account-settings.firstName"/>
                </c:set>
                <c:set var="phoneNumber" scope="page">
                    <spring:message code="account-settings.phoneNumber"/>
                </c:set>
                <div class="span14 offset1">
                    <div id="account-settings-form">
                        <form:form method="post" modelAttribute="userSignUpCommand" acceptCharset="UTF-8" enctype="application/x-www-form-urlencoded">
                            <spring:hasBindErrors name="userSignUpCommand">
                            <div id="sign-up-form-general-error">
                                <form:errors path="generalErrorMsg" cssClass="alert-message block-message error" element="div"/>
                            </div>
                            </spring:hasBindErrors>
                            <c:set var="lastNameError"><form:errors path="lastName"/></c:set>
                            <c:if test="${not empty lastNameError}">
                                <c:set var="lastNameErrorClass" value="error"/>
                            </c:if>
                            <div class="clearfix ${lastNameErrorClass}">
                                <label id="account-settings-last-name" for="lastName">${lastName}</label>
                                <div class="input account-settings-field" >
                                    <form:input path="lastName" cssErrorClass="xlarge error" cssClass="xlarge"/>
                                    <form:errors path="lastName" cssClass="alert-message error" />
                                </div>
                            </div><!-- class="clearfix"-->
                            <c:set var="firstNameError"><form:errors path="firstName"/></c:set>
                            <c:if test="${not empty firstNameError}">
                                <c:set var="firstNameErrorClass" value="error"/>
                            </c:if>
                            <div class="clearfix ${firstNameErrorClass}">
                                <label id="account-settings-email-first-name" for="firstName">${firstName}</label>
                                <div class="input account-settings-field" >
                                    <form:input path="firstName" cssErrorClass="xlarge error" cssClass="xlarge"/>
                                    <form:errors path="firstName" cssClass="alert-message error" />
                                </div>
                            </div><!-- class="clearfix"-->
                            <div class="clearfix">
                                <label id="account-settings-email" for="email">${email}</label>
                                <div class="input account-settings-field" >
                                    <form:input path="email" cssErrorClass="xlarge error" cssClass="xlarge uneditable-input" readonly="true"/>
                                    <form:errors path="email" cssClass="alert-message error" />
                                    <span class="help-block">
                                        <spring:message code="account-settings.email-rule"/>
                                    </span>
                                </div>
                            </div><!-- class="clearfix"-->
                            <c:set var="phoneNumberError"><form:errors path="phoneNumber"/></c:set>
                            <c:if test="${not empty phoneNumberError}">
                                <c:set var="phoneNumberErrorClass" value="error"/>
                            </c:if>
                            <div class="clearfix ${phoneNumberErrorClass}">
                                <label id="account-settings-phone-number" for="phoneNumber">${phoneNumber}</label>
                                <div class="input account-settings-field">
                                    <form:input path="phoneNumber" cssErrorClass="xlarge error" cssClass="xlarge"/>
                                    <form:errors path="phoneNumber" cssClass="alert-message error" />
                                </div>
                            </div><!-- class="clearfix"-->
                            <div id="change-password-link" class="actions">
                                <a class="btn large" href="<c:url value="/change-password.html?email=${userSignUpCommand.email}&amp;token=authenticated"/>"><fmt:message key="account-settings.changePassword"/></a>
                            </div>
                            <div id="account-settings-form-submit" class="actions">
                                <input class="btn primary" type="submit" value="<spring:message code="account-settings.saveChanges"/> &raquo;"/>
                            </div>
                        </form:form>
                    </div><!-- id="account-settings-form" -->
                </div><!-- class="span14 offset1" -->
            </div><!-- class="row" -->
        </div><!-- class="container" -->
        <%@include file="template/footer.jsp" %>
    </body>
</html>
</compress:html>