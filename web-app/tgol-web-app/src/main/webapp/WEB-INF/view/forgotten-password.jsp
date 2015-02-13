<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://tagutils" prefix="tg" %>
<!DOCTYPE html>
 
<c:set var="tgLogoUrl">
    <c:url value="/Images/Logo-tanaguru.com-white-75dpi-w78px-h35px-bgTransp.png"/>  
</c:set>
 
<html lang="${tg:lang(pageContext)}">
    <c:set var="pageTitle" scope="page">
        <spring:message code="forgotten-password.pageTitle"/>
        <spring:hasBindErrors name="userSignUpCommand">
            <spring:message code="forgotten-password.errorPageTitle"/>
        </spring:hasBindErrors>
    </c:set>
    <%@include file="template/head.jsp" %>
    <link rel="stylesheet" type="text/css" href="<c:url value="/Css/tgm-login.css"/>" />
    <body id="tgm-forgotten-password">
        <div class="topbar">
            <div class="fill">
                <div class="container">
                    <a href="<c:url value="/login.html"/>" title="<fmt:message key="sign-up.backToLogin"/>">
                        <img id="login-topbar-logo" src="${tgLogoUrl}" alt="<fmt:message key="sign-up.backToLogin"/>" />
                    </a>
                    <ul class="nav secondary-nav">
                        <li>
                            <%@include file="template/lang-box.jsp" %>
                        </li>
                    </ul>
                </div> <!-- class="container"-->
            </div> <!-- class="fill"-->
        </div> <!-- class="topbar"-->
        <div class="container">
            <c:set var="pageName" scope="page">
                <fmt:message key="forgotten-password.h1"/>
            </c:set>
            <div class="row">
                <div class="span16">
                    <h1>${pageName}</h1>
                </div>
                <c:set var="email" scope="page">
                    <spring:message code="forgotten-password.email"/>
                </c:set>
                <div class="span10 offset3">
                    <div id="forgotten-password-form">
                        <form:form method="post" modelAttribute="forgottenPasswordCommand" acceptCharset="UTF-8" enctype="application/x-www-form-urlencoded" class="form-stacked">
                            <spring:hasBindErrors name="forgottenPasswordCommand">
                            <div id="forgotten-password-form-general-error">
                                <form:errors path="generalErrorMsg" cssClass="alert-message block-message error" element="div"/>
                            </div><!-- id="forgotten-password-form-general-error" -->
                            </spring:hasBindErrors>
                            <div class="clearfix">
                                <label id="forgotten-password-label" for="email">${email}</label>
                                <div id="forgotten-password-field">
                                    <form:input path="email" cssErrorClass="error" cssClass="span9"/>
                                    <span class="help-block"><spring:message code="forgotten-password.rule"/></span>
                                </div>
                            </div>
                            <div id="forgotten-password-submit" class="actions">
                                <input class="btn primary" type="submit" value="<spring:message code="forgotten-password.sendInstructions"/>"/>
                            </div><!-- id="forgotten-password-submit" class="actions" -->
                        </form:form>
                    </div><!-- id="forgotten-password-form" -->
                </div><!-- class="span10 offset3" -->
            </div><!-- class="row" -->
        </div><!-- class="container" -->
        <%@include file="template/footer.jsp" %>
    </body>
</html>
</compress:html>