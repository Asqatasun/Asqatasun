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
    <link rel="stylesheet" type="text/css" href="<c:url value="/public/external_css/jquery-ui.min.css?v${asqatasunVersion}"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/public/external_css/prettify.min.css?v${asqatasunVersion}"/>"/>
</c:if>
    <link rel="stylesheet" type="text/css" href="<c:url value="/public/external_css/bootstrap.min.css?v${asqatasunVersion}"/>" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/public/css/app.asqatasun.css?v${asqatasunVersion}"/>" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/public/css/font-open.css?v${asqatasunVersion}"/>" />
    <style type="text/css"> .asqatasun-link-svg  svg { width:1em; height:1em; } </style>

   <!-- Favicon -->
    <link rel="apple-touch-icon" sizes="57x57" href="<c:url value="/public/images/Logo/favicon/apple-touch-icon-57x57.png?v${asqatasunVersion}"/>" />
    <link rel="apple-touch-icon" sizes="60x60" href="<c:url value="/public/images/Logo/favicon/apple-touch-icon-60x60.png?v${asqatasunVersion}"/>" />
    <link rel="apple-touch-icon" sizes="72x72" href="<c:url value="/public/images/Logo/favicon/apple-touch-icon-72x72.png?v${asqatasunVersion}"/>" />
    <link rel="apple-touch-icon" sizes="76x76" href="<c:url value="/public/images/Logo/favicon/apple-touch-icon-76x76.png?v${asqatasunVersion}"/>" />
    <link rel="apple-touch-icon" sizes="114x114" href="<c:url value="/public/images/Logo/favicon/apple-touch-icon-114x114.png?v${asqatasunVersion}"/>" />
    <link rel="apple-touch-icon" sizes="120x120" href="<c:url value="/public/images/Logo/favicon/apple-touch-icon-120x120.png?v${asqatasunVersion}"/>" />
    <link rel="apple-touch-icon" sizes="144x144" href="<c:url value="/public/images/Logo/favicon/apple-touch-icon-144x144.png?v${asqatasunVersion}"/>" />
    <link rel="apple-touch-icon" sizes="152x152" href="<c:url value="/public/images/Logo/favicon/apple-touch-icon-152x152.png?v${asqatasunVersion}"/>" />
    <link rel="apple-touch-icon" sizes="180x180" href="<c:url value="/public/images/Logo/favicon/apple-touch-icon-180x180.png?v${asqatasunVersion}"/>" />
    <link rel="icon" type="image/png" href="<c:url value="/public/images/Logo/favicon/favicon-32x32.png?v${asqatasunVersion}"/>" sizes="32x32" />
    <link rel="icon" type="image/png" href="<c:url value="/public/images/Logo/favicon/favicon-194x194.png?v${asqatasunVersion}"/>" sizes="194x194" />
    <link rel="icon" type="image/png" href="<c:url value="/public/images/Logo/favicon/favicon-96x96.png?v${asqatasunVersion}"/>" sizes="96x96" />
    <link rel="icon" type="image/png" href="<c:url value="/public/images/Logo/favicon/android-chrome-192x192.png?v${asqatasunVersion}"/>" sizes="192x192" />
    <link rel="icon" type="image/png" href="<c:url value="/public/images/Logo/favicon/favicon-16x16.png?v${asqatasunVersion}"/>" sizes="16x16" />
    <link rel="manifest" href="<c:url value="/public/images/Logo/favicon/manifest.json"/>" />
    <!-- <link rel="mask-icon" href="<c:url value="/public/images/Logo/favicon/safari-pinned-tab.svg"/>" color="#5bbad5" /> -->
    <link rel="shortcut icon" href="<c:url value="/public/images/Logo/favicon/favicon.ico"/>" />
    <meta name="msapplication-TileColor" content="#4e348e" />
    <meta name="msapplication-TileImage" content="<c:url value="/public/images/Logo/favicon/mstile-144x144.png?v${asqatasunVersion}"/>" />
    <meta name="msapplication-config" content="<c:url value="/public/images/Logo/favicon/browserconfig.xml"/>" />
    <meta name="theme-color" content="#ffffff" />

    <!-- social networks - Twitter Cards -->
    <meta name="twitter:card" content="summary_large_image" />
    <!-- <meta name="twitter:card" content="summary" /> -->
    <meta name="twitter:site" content="@Asqatasun" />
    <meta name="twitter:title" content="Asqatasun: accessibility testing. Automated, reliable, efficient (really)" />
    <meta name="twitter:description" content="Opensource software. Speed your accessibility and SEO testing width Asqatasun. Asqatasun automates SEO tests and accessibility tests (RGAA 3). Evaluating a page, an entire site or a web application is reliable and intuitive." />
    <meta name="twitter:image" content="https://forum.asqatasun.org/uploads/default/original/1X/e16a2b9b7f5a4dc756f03630923290c695c762c9.png?v${asqatasunVersion}" />
    <meta name="twitter:image:alt" content="Asqatasun" />

    <!-- browsers not send referrer information  -->
    <meta name="referrer" content="never">
            <!-- Documentation
                 https://html.spec.whatwg.org/multipage/semantics.html#meta-referrer    -->

    <c:set var="modernizrJsUrl" scope="request">
        <c:url value="/public/external_js/modernizr.min.js?v${asqatasunVersion}"/>
    </c:set>

    <!--[if lte IE 8]>
    <script type="text/javascript" src="${modernizrJsUrl}"></script>
    <![endif]-->
</head>
