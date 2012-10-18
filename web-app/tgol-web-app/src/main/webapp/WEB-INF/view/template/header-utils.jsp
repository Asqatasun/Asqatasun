<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"  %>

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
        <c:set var="currentUserName" scope="page">
            <sec:authentication property="principal.displayedUserName" />
        </c:set>
        <div class="topbar">
            <div class="fill">
                <div class="container">
                    <a class="brand" href="<c:url value="/dispatch.html"/>" title="<fmt:message key="home.home"/>">
                        <img src="${tgLogoUrl}" alt="<fmt:message key="home.home"/>" />
                    </a>
                    <ul class="nav secondary-nav">
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <li>
                            <a href="<c:url value="/admin.html"/>">Admin</a>
                        </li>    
                        </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                    <c:if test="${currentUserName != 'guest'}">
                    <c:choose>
                        <c:when test="${configProperties['enable-account-settings'] == 'true'}">
                        <li>
                            <a href="<c:url value="/account-settings.html"/>">${currentUserName}</a>
                        </li>
                        </c:when>
                        <c:otherwise>
                        <li>
                            <a href="#">${currentUserName}</a>
                        </li>
                        </c:otherwise>
                    </c:choose>
                    </c:if>
                    <li>
                        <div>
                            <a href="<c:url value="/j_spring_security_logout"/>" id="logout">
                                <fmt:message key="home.logout"/>
                                <img src="${logoutLogoUrl}" alt="" id="logout-icon"/>
                            </a>
                        </div>
                    </li>
                    </sec:authorize>    
                    <c:set var="isInTopBar" scope="page" value="true"/>
                    <%@include file="lang-box.jsp" %>
                    </ul><!--class="nav secondary-nav"-->
                </div><!-- class="container" -->
            </div><!-- class="fill" -->
        </div><!-- class="topbar" -->