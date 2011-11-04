<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="${pageContext.response.locale}">
    <c:set var="pageTitle" scope="page">
        <spring:message code="forgotten-password.pageTitle"/>
        <spring:hasBindErrors name="userSignUpCommand">
            <spring:message code="forgotten-password.errorPageTitle"/>
        </spring:hasBindErrors>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="tgm-forgotten-password-page" class="tgm">
        <div id="meta-border">
            <c:set var="displayLogoutLink" scope="page" value="false"/>
            <%@include file="template/header-utils.jsp" %>
            <c:set var="pageName" scope="page">
                <fmt:message key="forgotten-password.h1"/>
            </c:set>
            <div class="yui3-g">
                <div class="yui3-u-1">
                    <h1>${pageName}</h1>
                </div>
            </div>
            <c:set var="email" scope="page">
                <spring:message code="forgotten-password.email"/>
            </c:set>
            <form:form method="post" modelAttribute="forgottenPasswordCommand" acceptCharset="UTF-8" enctype="UTF-8">
                <div class="yui3-g">
                    <div class="yui3-u-1 cml cmr">
                        <form:errors path="generalErrorMsg" cssClass="errorblock" element="div"/>
                    </div>
                </div>
                <div class="yui3-g">
                    <div class="yui3-u-1-4"></div>
                    <div class="yui3-u-1-2">
                        <div class="forgotten-password-label">
                            <label id="forgotten-password-email" for="email">${email}</label><br/>
                            <span class="forgotten-password-rule">
                                <spring:message code="forgotten-password.rule"/>
                            </span>
                        </div>
                        <div class="forgotten-password-field" >
                            <form:input path="email" cssErrorClass="errorfield"/>
                        </div>
                    </div>
                </div>
                <div class="yui3-g">
                    <div class="yui3-u-1-4"></div>
                    <div class="yui3-u-1-2">
                        <div class="forgotten-password-buttons">
                            <button type="submit" id="input-submit" class="red awesome"><spring:message code="forgotten-password.sendInstructions"/></button>
                            <button type="reset" id="input-cancel" class="red awesome"><spring:message code="forgotten-password.cancel"/></button>
                        </div>
                    </div>
                </div><!-- class="yui3-g" -->

            </form:form>
        </div><!--  id="meta-border" -->
        <%@include file="template/footer.jsp" %>
    </body>
</html>