<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"  %>

<c:set var="tgLogoUrl">
    <c:url value="/public/images/Logo/Logo-asqatasun-light-w193px-h48px-bgTransp.png?v${asqatasunVersion}"/>
</c:set>
<c:set var="logoutLogoUrl">
    <c:url value="/public/images/icon-logout.png?v${asqatasunVersion}"/>
</c:set>
<c:set var="adminLogoUrl">
    <c:url value="/public/images/icon-admin.png?v${asqatasunVersion}"/>
</c:set>

<c:set var="currentUserName" scope="page">
    <c:catch var="notAuthenException" >
        <sec:authentication property="principal.displayedUserName" />
    </c:catch>
</c:set>
<c:if test="${accountSettingsActive == 'true'}">
    <c:set var="accountSettingsActive" scope="page" value=""/>
</c:if>
<c:if test="${adminActive == 'true'}">
    <c:set var="adminActive" scope="page" value=""/>
</c:if>
<div class="topbar">
    <div class="fill">
        <div class="container">
            <c:choose>
                <c:when test="${empty notAuthenException}">
                <a class="brand" 
                   href="<c:url value="/"/>"
                   title="<fmt:message key="home.home"/>">
                    <img src="${tgLogoUrl}" alt="<fmt:message key="home.home"/>"/>
                </a>
                </c:when>
                <c:otherwise>
                <a class="brand" 
                   href="<c:url value="/login.html"/>" 
                   title="Login">
                    <img src="${tgLogoUrl}" alt="Login" />
                </a>
                </c:otherwise>
            </c:choose>
            <ul class="nav secondary-nav">

            <sec:authorize access="isAuthenticated()">
                <li>
                    <c:choose>
                    <c:when test="${! fn:startsWith(currentUserName, 'guest')}">
                    <div id="account-settings">
                        <a href="<c:url value="/account-settings.html"/>" 
                           title="<fmt:message key="account-settings.accountSettingsLinkTitle"><fmt:param>${currentUserName}</fmt:param></fmt:message>">
                            ${currentUserName}
                        </a>
                    </div>
                    <%@include file="lang-box.jsp" %>
                    </c:when>
                    <c:otherwise>
                        <div class="lang-switcher-offset">
                            <%@include file="lang-box.jsp" %>
                        </div>
                    </c:otherwise>
                    </c:choose>
                </li>
            </sec:authorize>
            <c:set var="isInTopBar" scope="page" value="true"/>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <li ${adminActive}>
                    <a href="<c:url value="/admin.html"/>" 
                       title="<fmt:message key="home.admin"/>">
                        <img src="${adminLogoUrl}" 
                             alt="<fmt:message key="home.admin"/>"/>
                    </a>
                </li>    
            </sec:authorize>
            <c:if test="${empty notAuthenException}">
                <li>
                    <a href="<c:url value="/logout"/>"
                       id="logout" 
                       title="<fmt:message key="home.logout"/>">
                        <img src="${logoutLogoUrl}" 
                             alt="<fmt:message key="home.logout"/>" 
                             id="logout-icon"/>
                    </a>
                </li>
            </c:if>
            </ul><!--class="nav secondary-nav"-->
        </div><!-- class="container" -->
    </div><!-- class="fill" -->
</div><!-- class="topbar" -->
