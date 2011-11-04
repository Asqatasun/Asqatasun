<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="${pageContext.response.locale}">
    <c:set var="pageTitle" scope="page">
        <spring:message code="sign-up.pageTitle"/>
        <spring:hasBindErrors name="userSignUpCommand">
                <spring:message code="sign-up.errorPageTitle"/>
        </spring:hasBindErrors>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="tgm-sign-up-page" class="tgm">
        <div id="meta-border">
            <c:set var="displayLogoutLink" scope="page" value="false"/>
            <%@include file="template/header-utils.jsp" %>
            <div class="yui3-g">
                <div class="yui3-u-1">
                    <h1><spring:message code="sign-up.h1"/></h1>
                </div>
            </div>
            <div class="yui3-g">
                <div class="yui3-u-1" id="mandatory-elements-message">
                    <spring:message code="sign-up.mandatoryElementsMessage"/>
                </div>
            </div>
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
            <form:form method="post" modelAttribute="userSignUpCommand" acceptCharset="UTF-8" enctype="UTF-8">
                <div class="yui3-g">
                    <div class="yui3-u-1 cml cmr">
                        <form:errors path="generalErrorMsg" cssClass="errorblock" element="div"/>
                    </div>
                </div>
                <div class="yui3-g">
                    <div class="yui3-u-1-4">
                        <div class="sign-up-label">
                            <label id="sign-up-site-url" for="siteUrl">${siteToAudit}</label>
                        </div>
                    </div>
                    <div class="yui3-u-3-4">
                        <div class="sign-up-field" >
                            <form:input path="siteUrl" cssErrorClass="errorfield"/>
                            <form:errors path="siteUrl" cssClass="error" /><br/>
                            <span class="sign-up-rule">
                                <spring:message code="sign-up.siteToAudit-rule"/>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="yui3-g">
                    <div class="yui3-u-1-4">
                        <div class="sign-up-label">
                            <label id="sign-up-email" for="email">${email}</label>
                        </div>
                    </div>
                    <div class="yui3-u-3-4">
                        <div class="sign-up-field" >
                            <form:input path="email" cssErrorClass="errorfield"/>
                            <form:errors path="email" cssClass="error" /><br/>
                            <span class="sign-up-rule">
                                <spring:message code="sign-up.email-rule"/>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="yui3-g">
                    <div class="yui3-u-1-4">
                        <div class="sign-up-label">
                            <label id="sign-up-password" for="password">${password}</label>
                        </div>
                    </div>
                    <div class="yui3-u-3-4">
                        <div class="sign-up-field" >
                            <form:password path="password" cssErrorClass="errorfield"/>
                            <form:errors path="password" cssClass="error" /><br/>
                            <span class="sign-up-rule">
                                <spring:message code="sign-up.passwordRule"/>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="yui3-g">
                    <div class="yui3-u-1-4">
                        <div class="sign-up-label">
                            <label id="sign-up-confirm-password" for="confirmPassword">${confirmPassword}</label>
                        </div>
                    </div>
                    <div class="yui3-u-3-4">
                        <div class="sign-up-field" >
                            <form:password path="confirmPassword" cssErrorClass="errorfield"/>
                            <form:errors path="confirmPassword" cssClass="error" />
                        </div>
                    </div>
                </div>
                <div class="yui3-g">
                    <div class="yui3-u-1-4">
                        <div class="sign-up-label">
                            <label id="sign-up-last-name" for="lastName">${lastName}</label>
                        </div>
                    </div>
                    <div class="yui3-u-3-4">
                        <div class="sign-up-field" >
                            <form:input path="lastName" cssErrorClass="errorfield"/>
                            <form:errors path="lastName" cssClass="error" />
                        </div>
                    </div>
                </div>
                <div class="yui3-g">
                    <div class="yui3-u-1-4">
                        <div class="sign-up-label">
                            <label id="sign-up-email-first-name" for="firstName">${firstName}</label>
                        </div>
                    </div>
                    <div class="yui3-u-3-4">
                        <div class="sign-up-field" >
                            <form:input path="firstName" cssErrorClass="errorfield"/>
                            <form:errors path="firstName" cssClass="error" />
                        </div>
                    </div>
                </div>
                <div class="yui3-g">
                    <div class="yui3-u-1-4">
                        <div class="sign-up-label">
                            <label id="sign-up-phone-number" for="phoneNumber">${phoneNumber}</label>
                        </div>
                    </div>
                    <div class="yui3-u-3-4">
                        <div class="sign-up-field" >
                            <form:input path="phoneNumber" cssErrorClass="errorfield"/>
                            <form:errors path="phoneNumber" cssClass="error" />
                        </div>
                    </div>
                </div>
                <div class="yui3-g">
                    <div class="yui3-u-3-4"></div>
                    <div class="yui3-u-1-4">
                        <button type="submit" id="input-submit" class="red awesome"><spring:message code="sign-up.subscribe"/> &raquo;</button>
                    </div>
                </div><!-- class="yui3-g" -->

            </form:form>
        </div><!--  id="meta-border" -->
        <%@include file="template/footer.jsp" %>
    </body>
</html>