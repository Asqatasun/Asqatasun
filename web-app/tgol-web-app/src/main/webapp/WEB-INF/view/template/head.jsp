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
    <link rel="stylesheet" href="$${pageContext.request.scheme}://${configProperties['cdnUrl']}/Css/bootstrap-min.css"/>
    <link rel="stylesheet" type="text/css" href="$${pageContext.request.scheme}://${configProperties['cdnUrl']}/Css/tgm.css"/>
    <link rel="icon" type="image/ico" href="$${pageContext.request.scheme}://${configProperties['cdnUrl']}/Images/tanaguru.ico" />
    </c:when>
    <c:otherwise>
    <link rel="stylesheet" href="<c:url value="/Css/bootstrap-min.css"/>" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/Css/tgm.css"/>" />
    <link rel="icon" type="image/ico" href="<c:url value="/Images/tanaguru.ico"/>" />    
    </c:otherwise>
</c:choose>
</head>