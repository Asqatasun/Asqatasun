<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="${pageContext.response.locale}">
    <c:set var="pageTitle" scope="page">
        <fmt:message key="quota-exceeded.pageTitle"/>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="tgm-quota-exceeded" class="tgm">
        <div id="meta-border">
            <c:set var="displayLogoutLink" scope="page" value="false"/>
            <%@include file="template/header-utils.jsp" %>
            <div class="yui3-g">
                <div class="yui3-u-1">
                    <h1>
                        <fmt:message key="quota-exceeded.h1"/>
                    </h1>
                </div><!-- class="yui3-g" -->
                <div class="yui3-u-1">
                    <p><fmt:message key="quota-exceeded.message"/></p>
                </div><!-- class="yui3-g" -->
                <div class="yui3-u-1">
                    <a href="<c:url value="/dispatch.html" />"><fmt:message key="accessDeniedPage.backToHome"/></a>
                </div><!-- class="yui3-g" -->
            </div><!-- class="yui3-g" -->
        </div><!-- class="meta-border" -->
       <%@include file="template/footer.jsp" %>
    </body>
</html>