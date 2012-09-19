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
<c:choose>
    <c:when test="${not empty configProperties['cdnUrl']}">
        <c:set var="jqueryUrl" value="$${pageContext.request.scheme}://${configProperties['cdnUrl']}/Js/jquery.min.js"/>
        <c:set var="auditSetUpJsUrl" value="$${pageContext.request.scheme}://${configProperties['cdnUrl']}/Js/audit-set-up.js"/>
    </c:when>
    <c:otherwise>
        <c:set var="jqueryUrl">
            <c:url value="/Js/jquery.min.js"/>  
        </c:set>
        <c:set var="auditSetUpJsUrl">
            <c:url value="/Js/audit-set-up.js"/>
        </c:set>
    </c:otherwise>
</c:choose>
<html lang="${lang}">
    <c:set var="pageTitle" scope="page">
        <spring:message code="auditSetUpPage.pageTitle"/>
        <spring:hasBindErrors name="auditSetUpCommand">
            <spring:message code="auditSetUp.errorPageTitle"/>
        </spring:hasBindErrors>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="tgm-page-set-up">
        <%@include file="template/header-utils.jsp" %>
        <div class="container">
            <c:set var="pageName" scope="page">
                <spring:message code="auditSetUpPage.h1"/>
            </c:set>
            <ul class="breadcrumb">
                <li><a href="<c:url value="/home.html"/>"><fmt:message key="home.h1"/></a> <span class="divider"></span></li>
                <li><a href="<c:url value="/home/contract.html?cr=${param.cr}"/>">${contractName}</a> <span class="divider"></span></li>
                <li class="active">${pageName}</li>
            </ul>
            <div class="row">
                <div class="span16">
                    <h1><spring:message code="auditSetUpPage.h1"/></h1>
                </div><!-- class="span16" -->
                <%@include file="template/set-up.jsp" %>
            </div><!-- class="row" -->
        </div><!-- class="container" -->
        <script type="text/javascript" src="${jqueryUrl}"></script>
        <script type="text/javascript" src="${auditSetUpJsUrl}"></script>
        <%@include file="template/footer.jsp" %>
    </body>
</html>
</compress:html>