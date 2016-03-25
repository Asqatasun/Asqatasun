<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<head>
    <meta charset="utf-8"/>
    <title>${pageTitle}</title>
    <meta name="description" content="${pageMetaDescription}"/>
    <meta name="author" content="Asqatasun.org"/>
    <!-- Le styles -->

<c:if test="${addJqueryUI}">
    <link rel="stylesheet" type="text/css" href="<c:url value="/External-Css/jquery-ui.min.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/External-Css/prettify.min.css"/>"/>
</c:if>
    <link rel="stylesheet" type="text/css" href="<c:url value="/External-Css/bootstrap.min.css"/>" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/Css/tgm.css"/>" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/Css/font-open.css"/>" />   

   <!-- Favicon -->                        
    <link rel="apple-touch-icon" sizes="57x57" href="<c:url value="/Images/Logo/favicon/apple-touch-icon-57x57.png"/>" /> 
    <link rel="apple-touch-icon" sizes="60x60" href="<c:url value="/Images/Logo/favicon/apple-touch-icon-60x60.png"/>" /> 
    <link rel="apple-touch-icon" sizes="72x72" href="<c:url value="/Images/Logo/favicon/apple-touch-icon-72x72.png"/>" /> 
    <link rel="apple-touch-icon" sizes="76x76" href="<c:url value="/Images/Logo/favicon/apple-touch-icon-76x76.png"/>" /> 
    <link rel="apple-touch-icon" sizes="114x114" href="<c:url value="/Images/Logo/favicon/apple-touch-icon-114x114.png"/>" /> 
    <link rel="apple-touch-icon" sizes="120x120" href="<c:url value="/Images/Logo/favicon/apple-touch-icon-120x120.png"/>" /> 
    <link rel="apple-touch-icon" sizes="144x144" href="<c:url value="/Images/Logo/favicon/apple-touch-icon-144x144.png"/>" /> 
    <link rel="apple-touch-icon" sizes="152x152" href="<c:url value="/Images/Logo/favicon/apple-touch-icon-152x152.png"/>" /> 
    <link rel="apple-touch-icon" sizes="180x180" href="<c:url value="/Images/Logo/favicon/apple-touch-icon-180x180.png"/>" /> 
    <link rel="icon" type="image/png" href="<c:url value="/Images/Logo/favicon/favicon-32x32.png"/>" sizes="32x32" /> 
    <link rel="icon" type="image/png" href="<c:url value="/Images/Logo/favicon/favicon-194x194.png"/>" sizes="194x194" /> 
    <link rel="icon" type="image/png" href="<c:url value="/Images/Logo/favicon/favicon-96x96.png"/>" sizes="96x96" /> 
    <link rel="icon" type="image/png" href="<c:url value="/Images/Logo/favicon/android-chrome-192x192.png"/>" sizes="192x192" /> 
    <link rel="icon" type="image/png" href="<c:url value="/Images/Logo/favicon/favicon-16x16.png"/>" sizes="16x16" /> 
    <link rel="manifest" href="<c:url value="/Images/Logo/favicon/manifest.json"/>" /> 
    <link rel="mask-icon" href="<c:url value="/Images/Logo/favicon/safari-pinned-tab.svg"/>" color="#5bbad5" /> 
    <link rel="shortcut icon" href="<c:url value="/Images/Logo/favicon/favicon.ico"/>" /> 
    <meta name="msapplication-TileColor" content="#4e348e" /> 
    <meta name="msapplication-TileImage" content="<c:url value="/Images/Logo/favicon/mstile-144x144.png"/>" /> 
    <meta name="msapplication-config" content="<c:url value="/Images/Logo/favicon/browserconfig.xml"/>" /> 
    <meta name="theme-color" content="#ffffff" /> 

    
    <c:set var="modernizrJsUrl" scope="request">
        <c:url value="/External-Js/modernizr.min.js"/>
    </c:set>

    <!--[if lte IE 8]>
    <script type="text/javascript" src="${modernizrJsUrl}"></script>
    <![endif]-->
</head>
