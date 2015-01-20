<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html>
<%@ page contentType="text/html;charset=UTF-8" 
         pageEncoding="UTF-8"  
         language="java"
         import="org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter"
         import="org.springframework.web.context.request.RequestContextHolder"
         import="org.springframework.web.context.request.ServletRequestAttributes"
         import="java.util.Collection"
         import="java.util.Iterator"
         import="java.util.Map"
         import="java.util.Set"
         %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://tagutils" prefix="tg" %>
<!DOCTYPE html>

<c:set var="tgLogoUrl">
    <c:url value="/Images/Logo-tanaguru-white-w78px-h35px-bgTransp.png"/>  
</c:set>
<c:set var="tgLogo1Url">
    <c:url value="/Images/machine.png"/>  
</c:set>

<html lang="${tg:lang(pageContext)}">
    <c:if test="${not empty param.error}">
        <c:choose>
            <c:when test="${param.error == 'sessionTimeout'}">
                <c:set scope="page" var="errorOnPage">
                    <fmt:message key="login.sessionTimeout"/>
                </c:set>
            </c:when>
            <c:when test="${param.error == 'errorOnLogin'}">
                <c:set scope="page" var="springException">
                    ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"]['class'].simpleName}
                </c:set>
                <c:set scope="page" var="errorOnPage">
                    <fmt:message key="${springException}"/>
                </c:set>
            </c:when>
        </c:choose>
    </c:if>
    <c:set var="pageTitle" scope="page">
        <fmt:message key="login.pageTitle"/>
        <c:if test="${not empty errorOnPage}">
            - ${errorOnPage}
        </c:if>
    </c:set>
    <%@include file="template/head.jsp" %>
    <link rel="stylesheet" type="text/css" href="<c:url value="/Css/tgm-login.css"/>" />
    <body id="tgm-login">
        <div class="topbar">
            <div class="fill">
                <div class="container">
                    <img id="login-topbar-logo" class="brand" src="${tgLogoUrl}" alt="Tanaguru"/>
                    <ul class="nav secondary-nav">
                        <li>
                            <%@include file="template/lang-box.jsp" %>
                        </li>
                    </ul>
                </div> <!-- class="container"-->
            </div> <!-- class="fill"-->
        </div> <!-- class="topbar"-->
        <div class="container">
            <div class="row">
                <div class="span16">
                    <h1>
                        <fmt:message key="login.h1"/>
                    </h1>
                </div>
            </div>
            <div id="login-main-row" class="row">
                <div class="span10">
                    <div id="login-logo">
                        <img src="${tgLogo1Url}" alt="" />
                    </div>
                </div>
                <div class="span5 offset1">
                    <c:if test="${not empty errorOnPage}">
                    <div class="error">
                        ${errorOnPage}
                    </div>
                    </c:if>
                    <%@include file="template/login-form.jsp" %>
                </div><!-- class="span8 offset4" -->
            </div><!-- class="row" -->
        </div><!-- class="container" -->
        <%@include file="template/footer.jsp" %>
    </body>
</html>
</compress:html>