<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri='/WEB-INF/cewolf.tld' prefix='cewolf' %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="${pageContext.response.locale}">
    <c:set var="pageTitle" scope="page">
        <fmt:message key="auditInProgress.pageTitle">
            <fmt:param>
                ${testedUrl}
            </fmt:param>
        </fmt:message>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="tgm-audit-in-progress" class="tgm">
        <div id="meta-border">
            <%@include file="template/header-utils.jsp" %>
            <c:set var="pageName" scope="page">
                <fmt:message key="auditInProgress.h1"/>
            </c:set>
            <%@include file="template/breadcrumb.jsp" %>
            <div class="yui3-g">
                <div class="yui3-u-1">
                    <h1>
                        <fmt:message key="auditInProgress.h1"/>
                    </h1>
                </div><!-- class="yui3-u-1" -->
            </div><!-- class="yui3-g" -->
            <div class="yui3-g">
                <div class="yui3-u-1">
                    <p class="cml cmr">
                    <fmt:message key="auditInProgress.message">
                        <fmt:param>${testedUrl}</fmt:param>
                    </fmt:message>
                    </p>
                </div><!-- class="yui3-u-1" -->
                <div class="yui3-u-1">
                    <a href="<c:url value="/dispatch.html" />" class="cml cmr"><fmt:message key="accessDeniedPage.backToHome"/></a>
                </div><!-- class="yui3-g" -->
            </div><!-- class="yui3-g" -->
        </div><!-- id="meta-border"-->
        <%@include file="template/footer.jsp" %>
    </body>
</html>