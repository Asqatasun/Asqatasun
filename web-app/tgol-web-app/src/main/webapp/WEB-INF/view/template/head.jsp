<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<head>
    <meta charset="utf-8"/>
    <title>${pageTitle}</title>
    <meta name="description" content="${pageMetaDescription}"/>
    <meta name="author" content="Tanaguru.org"/>
    <!-- Le styles -->

<c:if test="${addJqueryUI}">
    <link rel="stylesheet" type="text/css" href="<c:url value="/External-Css/jquery-ui.min.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/External-Css/prettify.min.css"/>"/>
</c:if>
    <link rel="stylesheet" type="text/css" href="<c:url value="/External-Css/bootstrap.min.css"/>" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/Css/tgm.css"/>" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/Css/font-open.css"/>" />
    <link rel="icon" type="image/ico" href="<c:url value="/Images/tanaguru.ico"/>" />    
    <c:set var="modernizrJsUrl" scope="request">
        <c:url value="/External-Js/modernizr.min.js"/>
    </c:set>

    <!--[if lte IE 8]>
    <script type="text/javascript" src="${modernizrJsUrl}"></script>
    <![endif]-->
</head>
