<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://tagutils" prefix="tg" %>
<!DOCTYPE html>
<%@include file="/WEB-INF/view/template/template_variables.jspf" %>

<c:set var="tgLogoUrl">
    <c:url value="/public/images/Logo/Logo-asqatasun-light-w193px-h48px-bgTransp.png?v${asqatasunVersion}"/>
</c:set>

<html lang="${tg:lang(pageContext)}">
    <c:set var="pageTitle" scope="page">
        <spring:message code="sign-up.pageTitle"/>
        <spring:hasBindErrors name="createUserCommand">
            <spring:message code="sign-up.errorPageTitle"/>
        </spring:hasBindErrors>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="tgm-sign-up">
        <header class="topbar">
            <div class="fill">
                <div class="container">
                    <a href="<c:url value="/login.html"/>" title="<fmt:message key="sign-up.backToLogin"/>">
                        <img src="${tgLogoUrl}" alt="<fmt:message key="sign-up.backToLogin"/>" />
                    </a>
                    <ul class="nav secondary-nav">
                        <li>
                            <%@include file="template/lang-box.jsp" %>
                        </li>
                    </ul>
                </div> <!-- class="container"-->
            </div> <!-- class="fill"-->
        </header> <!-- class="topbar"-->
        <div class="container">
            <div class="row">
                <div class="span16">
                    <h1><spring:message code="sign-up.h1"/></h1>
                </div><!-- class="span16" -->
                <c:set var="addUrlField" scope="request" value="true"/>
                <c:set var="addAdminField" scope="request" value="false"/>
                <c:set var="addActivatedField" scope="request" value="false"/>
                <c:set var="validateButtonName" scope="request">
                    <spring:message code="sign-up.subscribe"/> &raquo;
                </c:set>
                <%@include file="template/add-user-form.jsp" %>
            </div><!-- class="row" -->
        </div><!-- class="container" -->
        <%@include file="template/footer.jsp" %>
    </body>
</html>
</compress:html>
