<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<head>
    <meta charset="utf-8"/>
    <title>${pageTitle}</title>
    <meta name="description" content="${pageMetaDescription}"/>
    <meta name="author" content="Open-S.com"/>
    <!-- Le styles -->
<c:choose>
    <c:when test="${not empty configProperties['cdnUrl']}">
        <c:if test="${addJqueryUI}">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.scheme}://${configProperties['cdnUrl']}/External-Css/jquery-ui.min.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.scheme}://${configProperties['cdnUrl']}/External-Css/prettify.min.css"/>
        </c:if>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.scheme}://${configProperties['cdnUrl']}/External-Css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Css/tgm-min.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Css/font-open-min.css"/>
    <link rel="icon" type="image/ico" href="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Images/tanaguru.ico" />
    <c:set var="modernizrJsUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/External-Js/modernizr.min.js" scope="request"/>
    </c:when>
    <c:otherwise>
        <c:if test="${addJqueryUI}">
    <link rel="stylesheet" type="text/css" href="<c:url value="/External-Css/jquery-ui.min.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/External-Css/prettify.min.css"/>"/>
        </c:if>
    <link rel="stylesheet" type="text/css" href="<c:url value="/External-Css/bootstrap.min.css"/>" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/Css/tgm-min.css"/>" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/Css/font-open-min.css"/>" />
    <link rel="icon" type="image/ico" href="<c:url value="/Images/tanaguru.ico"/>" />    
    <c:set var="modernizrJsUrl" scope="request">
        <c:url value="/External-Js/modernizr.min.js"/>
    </c:set>
    </c:otherwise>
</c:choose>
    <!--[if lte IE 8]>
    <script type="text/javascript" src="${modernizrJsUrl}"></script>
    <![endif]-->
</head>