<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="Author" content="Open-S.com // Matthieu Faure" lang="fr" />
    <title>
        ${pageTitle}
    </title>
    <link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/combo?3.2.0/build/cssreset/reset-min.css&amp;3.2.0/build/cssgrids/grids-min.css&amp;3.2.0/build/cssfonts/fonts-min.css&amp;3.2.0/build/cssbase/base-min.css" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/Css/tgm.css"/>" />
    <link rel="icon" type="image/ico" href="<c:url value="/Images/tanaguru.ico"/>" />
    <c:if test="${addWebSnapr == 'true'}">
        <script type="text/javascript" src="http://www.websnapr.com/js/websnapr.js"></script>
    </c:if>
    <c:if test="${not empty configProperties['google-analytics-code']}">
    <script type="text/javascript">
        var _gaq = _gaq || [];
        _gaq.push(['_setAccount', '${configProperties['google-analytics-code']}']);
        _gaq.push(['_trackPageview']);
        (function() {
            var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
            ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
            var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
        })();
    </script>
    </c:if>
    <c:if test="${addJQueryLib == 'true'}">
<!--        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
        <script type="text/javascript" src="<c:url value="/Js/jquery.uniform.min.js"/>"></script>
        <link rel="stylesheet" href="<c:url value="/Css/uniform.default.css"/>" type="text/css" media="screen" charset="utf-8" />-->
    </c:if>
</head>