<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"  %>

<c:set var="tgLogoUrl">
    <c:url value="/public/${asqatasunVersion}/images/Logo/Logo-asqatasun-light-w193px-h48px-bgTransp.png"/>
</c:set>
<c:set var="logoutLogoUrl">
    <c:url value="/public/${asqatasunVersion}/images/icon-logout.png"/>
</c:set>
<c:set var="adminLogoUrl">
    <c:url value="/public/${asqatasunVersion}/images/icon-admin.png"/>
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
            <c:choose>
                <c:when test="${configProperties['enable-account-settings'] == 'true'}">
                <li ${accountSettingsActive}>
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
                </c:when>
                <c:otherwise>
                <li ${accountSettingsActive}>
                    <c:choose>
                        <c:when test="${currentUserName != 'guest'}">
                    <div id="account-settings">
                        <a href="#">${currentUserName}</a>
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
                </c:otherwise>
            </c:choose>
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
