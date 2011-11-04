<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="${pageContext.response.locale}">
    <c:set var="pageTitle" scope="page">
        <spring:message code="sign-up-confirmation.pageTitle"/>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="tgm-sign-up-confirmation-page" class="tgm">
        <div id="meta-border">
            <c:set var="displayLogoutLink" scope="page" value="false"/>
            <%@include file="template/header-utils.jsp" %>
            <div class="yui3-g">
                <div class="yui3-u-1">
                    <h1><spring:message code="sign-up-confirmation.h1"/></h1>
                </div>
            </div>
            <div class="yui3-g">
                <div class="yui3-u-1">
                    <p class="cmr cml"><spring:message code="sign-up-confirmation.content"/></p>
                </div>
            </div>
        </div><!--  id="meta-border" -->

        <%@include file="template/footer.jsp" %>
    </body>
</html>