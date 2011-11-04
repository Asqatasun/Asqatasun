<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="${pageContext.response.locale}">
    <c:set var="pageTitle" scope="page">
        <spring:message code="account-settings.pageTitle"/>
        <spring:hasBindErrors name="userSignUpCommand">
                <spring:message code="account-settings.errorPageTitle"/>
        </spring:hasBindErrors>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="tgm-account-settings-page" class="tgm">
        <div id="meta-border">
            <c:set var="displayLogoutLink" scope="page" value="true"/>
            <%@include file="template/header-utils.jsp" %>
            <c:set var="pageName" scope="page">
                <fmt:message key="account-settings.h1"/>
            </c:set>
            <%@include file="template/breadcrumb.jsp" %>
            <div class="yui3-g">
                <div class="yui3-u-1">
                    <h1>${pageName}</h1>
                    <c:if test="${accountDataUpdated == 'true'}">
                    <p class="message-positif cml cmr">
                        <fmt:message key="account-settings.accountDataUpdated"/>
                    </p>
                </c:if>
                </div>
            </div>
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
            <form:form method="post" modelAttribute="userSignUpCommand" acceptCharset="UTF-8" enctype="UTF-8">
                <div class="yui3-g">
                    <div class="yui3-u-1">
                        <form:errors path="generalErrorMsg" cssClass="errorblock" element="div"/>
                    </div>
                </div>
                <div class="yui3-g">
                    <div class="yui3-u-1-4">
                        <div class="account-settings-label">
                            <label id="account-settings-last-name" for="lastName">${lastName}</label>
                        </div>
                    </div>
                    <div class="yui3-u-3-4">
                        <div class="account-settings-field" >
                            <form:input path="lastName" cssErrorClass="errorfield"/>
                            <form:errors path="lastName" cssClass="error" />
                        </div>
                    </div>
                </div>
                <div class="yui3-g">
                    <div class="yui3-u-1-4">
                        <div class="account-settings-label">
                            <label id="account-settings-email-first-name" for="firstName">${firstName}</label>
                        </div>
                    </div>
                    <div class="yui3-u-3-4">
                        <div class="account-settings-field" >
                            <form:input path="firstName" cssErrorClass="errorfield"/>
                            <form:errors path="firstName" cssClass="error" />
                        </div>
                    </div>
                </div>
                <div class="yui3-g">
                    <div class="yui3-u-1-4">
                        <div class="account-settings-label">
                            <label id="account-settings-email" for="email">${email}</label>
                        </div>
                    </div>
                    <div class="yui3-u-3-4">
                        <div class="account-settings-field" >
                            <form:input path="email" cssErrorClass="errorfield" readonly="true"/>
                            <form:errors path="email" cssClass="error" /><br/>
                            <span class="account-settings-rule">
                                <spring:message code="account-settings.email-rule"/>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="yui3-g">
                    <div class="yui3-u-1-4">
                        <div class="account-settings-label">
                            <label id="account-settings-phone-number" for="phoneNumber">${phoneNumber}</label>
                        </div>
                    </div>
                    <div class="yui3-u-3-4">
                        <div class="account-settings-field" >
                            <form:input path="phoneNumber" cssErrorClass="errorfield"/>
                            <form:errors path="phoneNumber" cssClass="error" />
                        </div>
                    </div>
                </div>
                <div class="yui3-g">
                    <div class="yui3-u-1-4"></div>
                    <div class="yui3-u-3-4 cmt">
                        <a class="cml cmr cmt" href="<c:url value="/change-password.html?email=${userSignUpCommand.email}&amp;token=authenticated"/>"><fmt:message key="account-settings.changePassword"/></a>
                    </div>
                </div>
                <div class="yui3-g">
                    <div class="yui3-u-2-3"></div>
                    <div class="yui3-u-1-3">
                        <button type="submit" id="input-submit" class="red awesome"><spring:message code="account-settings.saveChanges"/> &raquo;</button>
                    </div>
                </div><!-- class="yui3-g" -->

            </form:form>
        </div><!--  id="meta-border" -->

        <%@include file="template/footer.jsp" %>
    </body>
</html>