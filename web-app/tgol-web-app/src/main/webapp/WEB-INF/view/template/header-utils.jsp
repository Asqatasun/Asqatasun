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

            <div class="yui3-g clearfix">
                <div class="yui3-u-1-4">
                    <a href="<c:url value="/dispatch.html"/>" title="<fmt:message key="home.home"/>">
                        <img src="<c:url value="/Images/Logo-Tanaguru.com-75dpi-210x95-white.png"/>" alt="<fmt:message key="home.home"/>" />
                    </a>
                </div>
                <div class="yui3-u-3-4" id="login-box">
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
                        <fmt:message key="home.hello"/> <a href="<c:url value="/account-settings.html"/>">${userName}</a>,
                        </c:when>
                        <c:otherwise>
                        <fmt:message key="home.hello"/> <a href="#">${userName}</a>,
                        </c:otherwise>
                    </c:choose>
                    </c:if>
                    <c:if test="${displayLogoutLink != 'false' && authenticatedUser != null}">
                        <a href="<c:url value="/j_spring_security_logout"/>" id="logout"><fmt:message key="home.logout"/></a> <img src="<c:url value="/Images/icon-logout.png"/>" alt="" id="logout-icon"/>
                    </c:if>
                    <c:if test="${configProperties['enable-sign-up'] == 'true'}">
                    <c:if test="${displaySignUpLink == 'true'}">
                        <a href="<c:url value="/sign-up.html"/>" id="sign-up"><fmt:message key="login.sign-up"/></a>
                    </c:if>
                    </c:if>
                    <%
                        // SORRRRRYYY !!!!!!!!!!!!!!!!
                        StringBuffer strBuffer = new StringBuffer();
                        HttpServletRequest servletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
                        strBuffer.append("?");
                        Set set = servletRequest.getParameterMap().entrySet();
                        // Get an iterator
                        Iterator i = set.iterator();
                        while(i.hasNext()) {
                            Map.Entry me = (Map.Entry)i.next();
                            if (!me.getKey().toString().equalsIgnoreCase("lang")) {
                                strBuffer.append(me.getKey());
                                strBuffer.append("=");
                                strBuffer.append(((String[])me.getValue())[0]);
                                strBuffer.append("&amp;");
                            }
                        }
                    %>
                    <a href="<%out.print(strBuffer.toString());%>lang=en" title="Switch to english" lang="en">
                        <img src="<c:url value="/Images/en-flag.gif"/>" alt="Switch to english" />
                    </a>
                    <a href="<%out.print(strBuffer.toString());%>lang=fr" title="Passer en français" lang="fr">
                        <img src="<c:url value="/Images/fr-flag.gif"/>" alt="Passer en français" />
                    </a>
                </div>
            </div>