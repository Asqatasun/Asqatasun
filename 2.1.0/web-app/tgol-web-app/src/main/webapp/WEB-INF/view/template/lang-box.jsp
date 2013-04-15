<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
                                                   
<%@page import="org.springframework.web.context.request.RequestContextHolder" %>
<%@page import="org.springframework.web.context.request.ServletRequestAttributes" %>
<%@page import="java.util.Collection" %>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Set"%>
            <c:choose>
                <c:when test="${not empty configProperties['cdnUrl']}">
                    <c:set var="tgLogoUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Images/Logo-Tanaguru.com-75dpi-210x95-transp.png"/>
                    <c:set var="enFlagUrl"value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Images/en-flag2.jpeg"/>
                    <c:set var="frFlagUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Images/fr-flag2.jpeg"/>
                </c:when>
                <c:otherwise>
                    <c:set var="tgLogoUrl">
                        <c:url value="/Images/Logo-Tanaguru.com-75dpi-210x95-transp.png"/>  
                    </c:set>
                    <c:set var="enFlagUrl">
                        <c:url value="/Images/en-flag2.jpeg"/>
                    </c:set>
                    <c:set var="frFlagUrl">
                        <c:url value="/Images/fr-flag2.jpeg"/>
                    </c:set>
                </c:otherwise>
            </c:choose>
            <sec:authorize access="isAnonymous()">
            <div id="lang-box" class="row">
                <c:set var="offset" value="12"/>
                <c:if test="${addLogo == 'true'}">
                <div id="logo-box" class="span4">
                    <a href="<c:url value="/dispatch.html"/>" title="<fmt:message key="home.home"/>">
                        <img src="${tgLogoUrl}" alt="" />
                    </a>
                </div>
                <c:set var="offset" value="8"/>
                </c:if>
                <div class="span4 offset${offset}">
            </sec:authorize>
            <c:set var="properQueryString" scope="page" value="${fn:replace(pageContext.request.queryString, '&', '&amp;')}"/>
            <c:choose>
                <c:when test="${not empty pageContext.request.queryString}">
                    <c:choose>
                        <c:when test="${fn:contains(pageContext.request.queryString, 'lang=en')}">
                            <c:set var="enUrl" scope="request" value="?${properQueryString}"/>
                            <c:set var="frUrl" scope="request" value="?${fn:replace(properQueryString, 
                                'lang=en', 'lang=fr')}" />
                        </c:when>
                        <c:when test="${fn:contains(pageContext.request.queryString, 'lang=fr')}">
                            <c:set var="frUrl" scope="request" value="?${properQueryString}"/>
                            <c:set var="enUrl" scope="request" value="?${fn:replace(properQueryString, 
                                'lang=fr', 'lang=en')}" />
                        </c:when>
                        <c:otherwise>
                            <c:set var="frUrl" scope="request" value="?${properQueryString}&amp;lang=fr"/>
                            <c:set var="enUrl" scope="request" value="?${properQueryString}&amp;lang=en"/>
                        </c:otherwise>
                    </c:choose>
                </c:when>
                <c:otherwise>
                    <c:set var="frUrl" scope="request" value="?lang=fr"/>
                    <c:set var="enUrl" scope="request" value="?lang=en"/>
                </c:otherwise>
            </c:choose>
                    <c:if test="${isInTopBar == 'true'}">
                    <li class="lang-flag">
                    </c:if>
                            <a href="${enUrl}" title="Switch to english" lang="en">
                            <img src="${enFlagUrl}" alt="Switch to english" />
                        </a>
                    <c:if test="${isInTopBar == 'true'}">
                    </li>
                    <li class="lang-flag">
                    </c:if>
                            <a href="${frUrl}" title="Passer en français" lang="fr">
                            <img src="${frFlagUrl}" alt="Passer en français" />
                        </a>
                    <c:if test="${isInTopBar == 'true'}">
                    </li>
                    </c:if>
            <sec:authorize access="isAnonymous()">
                </div><!--class="span4 offset12"-->
            </div> <!-- class="row"-->
            </sec:authorize>    