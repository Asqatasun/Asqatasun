<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="${pageContext.response.locale}">
    <c:set var="pageTitle" scope="page">
        <spring:message code="change-password.pageTitle"/>
        <spring:hasBindErrors name="userSignUpCommand">
                <spring:message code="change-password.errorPageTitle"/>
        </spring:hasBindErrors>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="tgm-change-password-page" class="tgm">
        <div id="meta-border">
            <c:set var="displayLogoutLink" scope="page" value="true"/>
            <%@include file="template/header-utils.jsp" %>
            <c:set var="pageName" scope="page">
                <fmt:message key="change-password.h1"/>
            </c:set>
            <c:if test="${authenticatedUser != null}">
            <%@include file="template/breadcrumb.jsp" %>
            </c:if>
            <c:choose>
            <c:when test="${passwordModified == 'true'}">
            <div class="yui3-g">
                <div class="yui3-u-1">
                    <h1>${pageName}</h1>
                    <p class="message-positif cml cmr">
                        <fmt:message key="change-password.passwordModified"/>
                    </p>
                </div>
            </div>
            <c:choose>
                <c:when test="${authenticatedUser != null}">
            <div class="yui3-u-1">
                <a class="cml cmr" href="<c:url value="/home.html"/>"><fmt:message key="home.home"/></a>
            </div>
                </c:when>
                <c:otherwise>
            <div class="yui3-u-1">
                <a class="cml cmr" href="<c:url value="/login.html"/>"><fmt:message key="forgotten-password-confirmation.backToLogin"/></a>
            </div>
                </c:otherwise>
            </c:choose>
            </c:when>
            <c:when test="${invalidChangePasswordUrl != 'true'}">
            <div class="yui3-g">
                <div class="yui3-u-1">
                    <h1>${pageName}</h1>
                </div>
            </div>
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
            <form:form method="post" modelAttribute="changePasswordCommand" acceptCharset="UTF-8" enctype="UTF-8">
                <div class="yui3-g">
                    <div class="yui3-u-1">
                        <form:hidden path="userEmail"/>
                        <form:errors path="generalErrorMsg" cssClass="errorblock" element="div"/>
                    </div>
                </div>
                <c:if test="${authenticatedUser != null}">
                <div class="yui3-g">
                    <div class="yui3-u-1-3">
                        <div class="change-password-label cml">
                            <label id="change-password-current-password" for="currentPassword">${currentPassword}</label>
                        </div>
                    </div>
                    <div class="yui3-u-2-3">
                        <div class="change-password-field cmr" >
                            <form:password path="currentPassword" cssErrorClass="errorfield"/>
                            <form:errors path="currentPassword" cssClass="error" /><br/>
                        </div>
                    </div>
                </div>
                </c:if>
                <div class="yui3-g">
                    <div class="yui3-u-1-3">
                        <div class="change-password-label cml">
                            <label id="change-password-new-password" for="newPassword">${newPassword}</label>
                        </div>
                    </div>
                    <div class="yui3-u-2-3">
                        <div class="change-password-field cmr" >
                            <form:password path="newPassword" cssErrorClass="errorfield"/>
                            <form:errors path="newPassword" cssClass="error" /><br/>
                            <span class="change-password-rule">
                                <spring:message code="change-password.newPasswordRule"/>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="yui3-g">
                    <div class="yui3-u-1-3">
                        <div class="change-password-label cml">
                            <label id="change-password-confirm-new-password" for="confirmNewPassword">${confirmNewPassword}</label>
                        </div>
                    </div>
                    <div class="yui3-u-2-3">
                        <div class="change-password-field cmr" >
                            <form:password path="confirmNewPassword" cssErrorClass="errorfield"/>
                            <form:errors path="confirmNewPassword" cssClass="error" />
                        </div>
                    </div>
                </div>
                <div class="yui3-g">
                    <div class="yui3-u-1-2"></div>
                    <div class="yui3-u-1-2">
                        <button type="submit" id="input-submit" class="red awesome cmt"><spring:message code="change-password.saveChanges"/> &raquo;</button>
                    </div>
                </div><!-- class="yui3-g" -->

            </form:form>
            </c:when>
            <c:otherwise>
            <div class="yui3-g">
                <div class="yui3-u-1">
                    <h1>${pageName}</h1>
                </div>
                <div class="yui3-u-1">
                    <p class="cml cmr">
                        <fmt:message key="change-password.invalidChangePasswordUrl"/>
                    </p>
                </div>
                <div class="yui3-u-1">
                    <a class="cml cmr" href="<c:url value="/login.html"/>"><fmt:message key="forgotten-password-confirmation.backToLogin"/></a>
                </div>
            </div>
            </c:otherwise>
            </c:choose>
        </div><!--  id="meta-border" -->
        <%@include file="template/footer.jsp" %>
    </body>
</html>