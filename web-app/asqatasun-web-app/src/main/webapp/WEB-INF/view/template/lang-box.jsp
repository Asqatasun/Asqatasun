<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>




<c:set var="properQueryString" scope="page" value="${fn:replace(pageContext.request.queryString, '&', '&amp;')}"/>
<c:choose>
    <c:when test="${not empty pageContext.request.queryString}">
        <c:choose>
            <c:when test="${fn:contains(pageContext.request.queryString, 'lang=de')}">
                <c:set var="deUrl" scope="request" value="?${properQueryString}"/>
                <c:set var="frUrl" scope="request" value="?${fn:replace(properQueryString,
                    'lang=de', 'lang=fr')}" />
                <c:set var="esUrl" scope="request" value="?${fn:replace(properQueryString,
                    'lang=de', 'lang=es')}" />
                <c:set var="enUrl" scope="request" value="?${fn:replace(properQueryString,
                    'lang=de', 'lang=en')}" />
            </c:when>
            <c:when test="${fn:contains(pageContext.request.queryString, 'lang=en')}">
                <c:set var="enUrl" scope="request" value="?${properQueryString}"/>
                <c:set var="frUrl" scope="request" value="?${fn:replace(properQueryString,
                    'lang=en', 'lang=fr')}" />
                <c:set var="esUrl" scope="request" value="?${fn:replace(properQueryString,
                    'lang=en', 'lang=es')}" />
                <c:set var="deUrl" scope="request" value="?${fn:replace(properQueryString,
                    'lang=en', 'lang=de')}" />
            </c:when>
            <c:when test="${fn:contains(pageContext.request.queryString, 'lang=fr')}">
                <c:set var="frUrl" scope="request" value="?${properQueryString}"/>
                <c:set var="enUrl" scope="request" value="?${fn:replace(properQueryString,
                    'lang=fr', 'lang=en')}" />
                <c:set var="esUrl" scope="request" value="?${fn:replace(properQueryString,
                    'lang=fr', 'lang=es')}" />
                <c:set var="deUrl" scope="request" value="?${fn:replace(properQueryString,
                    'lang=fr', 'lang=de')}" />
            </c:when>
            <c:when test="${fn:contains(pageContext.request.queryString, 'lang=es')}">
                <c:set var="esUrl" scope="request" value="?${properQueryString}"/>
                <c:set var="enUrl" scope="request" value="?${fn:replace(properQueryString,
                    'lang=es', 'lang=en')}" />
                <c:set var="frUrl" scope="request" value="?${fn:replace(properQueryString,
                    'lang=es', 'lang=fr')}" />
                <c:set var="deUrl" scope="request" value="?${fn:replace(properQueryString,
                    'lang=es', 'lang=de')}" />
            </c:when>
            <c:otherwise>
                <c:set var="deUrl" scope="request" value="?${properQueryString}&amp;lang=de"/>
                <c:set var="frUrl" scope="request" value="?${properQueryString}&amp;lang=fr"/>
                <c:set var="esUrl" scope="request" value="?${properQueryString}&amp;lang=es"/>
                <c:set var="enUrl" scope="request" value="?${properQueryString}&amp;lang=en"/>
            </c:otherwise>
        </c:choose>
    </c:when>
    <c:otherwise>
        <c:set var="deUrl" scope="request" value="?lang=de"/>
        <c:set var="frUrl" scope="request" value="?lang=fr"/>
        <c:set var="esUrl" scope="request" value="?lang=es"/>
        <c:set var="enUrl" scope="request" value="?lang=en"/>
    </c:otherwise>
</c:choose>
<nav id="lang-switcher">
    <a href="${deUrl}" title="Umsteigen auf Deutsch" lang="de">
        <abbr title="Deutsch">DE</abbr>
    </a>
    <a href="${enUrl}" title="Switch to english" lang="en">
        <abbr title="English">EN</abbr>
    </a>
    <a href="${frUrl}" title="Passer en français" lang="fr">
        <abbr title="Français">FR</abbr>
    </a>
    <a href="${esUrl}" title="Cambaiar a español" lang="es">
        <abbr title="Español">ES</abbr>
    </a>
</nav>
