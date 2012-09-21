<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page import="org.springframework.web.context.request.RequestContextHolder" %>
<%@page import="org.springframework.web.context.request.ServletRequestAttributes" %>
<%@page import="java.util.Collection" %>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Set"%>

        <c:choose>
            <c:when test="${not empty configProperties['cdnUrl']}">
                <c:set var="tgLogoUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Images/Logo-tanaguru.com-white-75dpi-w78px-h35px-bgTransp.png"/>
                <c:set var="logoutLogoUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Images/icon-logout.png"/>
            </c:when>
            <c:otherwise>
                <c:set var="tgLogoUrl">
                    <c:url value="/Images/Logo-tanaguru.com-white-75dpi-w78px-h35px-bgTransp.png"/>  
                </c:set>
                <c:set var="logoutLogoUrl">
                    <c:url value="/Images/icon-logout.png"/>  
                </c:set>
            </c:otherwise>
        </c:choose>
        <div class="topbar">
            <div class="fill">
                <div class="container">
                    <a class="brand" href="<c:url value="/dispatch.html"/>" title="<fmt:message key="home.home"/>">
                        <img src="${tgLogoUrl}" alt="<fmt:message key="home.home"/>" />
                    </a>
                    <ul class="nav secondary-nav">
                        <c:if test="${authenticatedUser != null && authenticatedUser.email1 != 'guest'}">
                    <c:choose>
                        <c:when test='${authenticatedUser.firstName != null && authenticatedUser.name != null}'>
                            <c:set var="userName" scope="page" value="${authenticatedUser.firstName} ${authenticatedUser.name}"/>
                        </c:when>
                        <c:when test='${authenticatedUser.name != null}'>
                            <c:set var="userName" scope="page" value="${authenticatedUser.name}"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="userName" scope="page" value="${authenticatedUser.email1}"/>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${configProperties['enable-account-settings'] == 'true'}">
                        <li>
                            <a href="<c:url value="/account-settings.html"/>">${userName}</a>
                        </li>
                        </c:when>
                        <c:otherwise>
                        <li>
                            <a href="#">${userName}</a>
                        </li>
                        </c:otherwise>
                    </c:choose>
                    </c:if>
                    <c:if test="${displayLogoutLink != 'false' && authenticatedUser != null}">
                        <li>
                            <div>
                                <a href="<c:url value="/j_spring_security_logout"/>" id="logout">
                                    <fmt:message key="home.logout"/>
                                    <img src="${logoutLogoUrl}" alt="" id="logout-icon"/>
                                </a>
                            </div>
                        </li>
                    </c:if>
                    <c:if test="${configProperties['enable-sign-up'] == 'true'}">
                    <c:if test="${displaySignUpLink == 'true'}">
                        <li>
                            <a href="<c:url value="/sign-up.html"/>" id="sign-up"><fmt:message key="login.sign-up"/></a>
                        </li>
                    </c:if>
                    </c:if>
                    <c:set var="isInTopBar" scope="page" value="true"/>
                    <%@include file="lang-box.jsp" %>
                    </ul><!--class="nav secondary-nav"-->
                </div><!-- class="container" -->
            </div><!-- class="fill" -->
        </div><!-- class="topbar" -->