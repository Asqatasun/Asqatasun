<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri='/WEB-INF/cewolf.tld' prefix='cewolf' %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="${pageContext.response.locale}">
    <c:set var="pageTitle" scope="page">
        <fmt:message key="resultSite.pageTitle">
            <fmt:param>
                ${statistics.url}
            </fmt:param>
        </fmt:message>
    </c:set>
    <c:set var="addWebSnapr" scope="page" value="true"/>
    <%@include file="template/head.jsp" %>
    <body id="tgm-result-site" class="tgm">
        <div id="meta-border">
            <%@include file="template/header-utils.jsp" %>
            <c:set scope="page" var="pageName">
                <fmt:message key="resultSite.h1"/>
            </c:set>
            <%@include file="template/breadcrumb.jsp" %>
            <div class="yui3-g">
                <div class="yui3-u-1">
                    <h1>
                        ${pageName}
                    </h1>
                </div>
            </div>
            <div class="yui3-g">
                <div class="yui3-u-1 cml cmr">
                    <ol>
                        <li><a href="#synthesis"><fmt:message key="resultPage.synthesis"/></a></li>
                        <li><a href="#work-done"><fmt:message key="resultPage.detailedResultSite"/></a></li>
                    </ol>
                </div>
            </div>
            <c:set var="hasBarChartLink" scope="request" value="false"/>
            <c:set var="hasGraphics" scope="request" value="false"/>
            <c:set var="hasSynthesisTitle" scope="request" value="true"/>
            <c:set var="hasPageCounter" scope="request" value="true"/>
            <c:set var="hasPagesListLink" scope="request" value="false"/>
            <c:set var="hasResultDispatchTitle" scope="request" value="false"/>
            <c:import url="template/synthesis.jsp" />
            <c:set var="displayAlgorithm" scope="request" value="true"/>
            <c:set var="scope" scope="request" value="site"/>
            <c:import url="template/detailed-result.jsp" />
        </div><!-- id="meta-border"-->
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
        <script type="text/javascript" src="http://asset.open-s.com/Corporate/TGOL/Js/audit-result.js"></script>
        <script type="text/javascript" src="http://asset.open-s.com/Corporate/TGOL/Js/audit-set-up.js"></script>
        <%@include file="template/footer.jsp" %>
    </body>
</html>