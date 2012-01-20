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
        <spring:message code="forgotten-password.pageTitle"/>
        <spring:hasBindErrors name="userSignUpCommand">
            <spring:message code="forgotten-password.errorPageTitle"/>
        </spring:hasBindErrors>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="tgm-forgotten-password">
        <div class="container">
            <c:set var="isInNotAuthentifiedView" scope="page" value="true"/>
            <c:set var="addLogo" scope="page" value="true"/>
            <%@include file="template/lang-box.jsp" %>
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