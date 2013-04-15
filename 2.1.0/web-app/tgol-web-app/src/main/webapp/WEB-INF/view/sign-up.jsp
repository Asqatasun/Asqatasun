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
        <spring:hasBindErrors name="createUserCommand">
            <spring:message code="sign-up.errorPageTitle"/>
        </spring:hasBindErrors>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="tgm-sign-up">
        <div class="container">
            <c:set var="addLogo" scope="page" value="true"/>
            <%@include file="template/lang-box.jsp" %>
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