<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="${pageContext.response.locale}">
    <c:set var="pageTitle" scope="page">
        <spring:message code="auditSetUpPage.pageTitle"/>
        <spring:hasBindErrors name="auditSetUpCommand">
            <spring:message code="auditSetUp.errorPageTitle"/>
        </spring:hasBindErrors>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="tgm-page-set-up" class="tgm">
        <div id="meta-border">
            <%@include file="template/header-utils.jsp" %>
            <c:set var="pageName" scope="page">
                <spring:message code="auditSetUpPage.h1"/>
            </c:set>
            <%@include file="template/breadcrumb.jsp" %>
            <div class="yui3-g">
                <div class="yui3-u-1">
                    <h1><spring:message code="auditSetUpPage.h1"/></h1>
                    <h2 class="cmr cml"><spring:message code="auditSetUp.enterUrl"/></h2>
                </div>
            </div>
            <div class="yui3-g">
                <div class="yui3-u-1">
                    <%@include file="template/set-up.jsp" %>
                </div><!-- class="yui3-g" -->
            </div>
        </div><!--  id="meta-border" -->
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
        <script type="text/javascript" src="<c:url value="/Js/audit-set-up.js"/>"></script>
        <%@include file="template/footer.jsp" %>
    </body>
</html>

