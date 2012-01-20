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
    <link rel="stylesheet" href="<c:url value="/Css/bootstrap-min.css"/>" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/Css/tgm.css"/>" />
    <link rel="icon" type="image/ico" href="<c:url value="/Images/tanaguru.ico"/>" />
    <c:if test="${addWebSnapr == 'true'}">
        <script type="text/javascript" src="http://www.websnapr.com/js/websnapr.js"></script>
    </c:if>
</head>