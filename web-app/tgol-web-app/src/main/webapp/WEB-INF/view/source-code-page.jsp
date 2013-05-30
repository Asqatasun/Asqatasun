<%@taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html>
<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<c:choose>
    <c:when test="${fn:contains(pageContext.response.locale, '_')}">
        <c:set var="lang">
            ${fn:substringBefore(pageContext.response.locale, "_")}
        </c:set>
    </c:when>
    <c:otherwise>
        <c:set var="lang" value="${pageContext.response.locale}"/>
    </c:otherwise>
</c:choose>
<c:choose>
    <c:when test="${not empty configProperties['cdnUrl']}">
        <!-- external js -->
        <c:set var="jqueryUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/External-Js/jquery-1.9.1.min.js"/>
        <!-- internal js -->
        <c:set var="highlightSelectedLineJsUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Js/highlighter/highlight-selected-line-min.js"/>
    </c:when>
    <c:otherwise>
        <!-- external js -->
        <c:set var="jqueryUrl">
            <c:url value="/External-Js/jquery-1.9.1.min.js"/>
        </c:set>
        
        <!-- internal js -->
        <c:set var="highlightSelectedLineJsUrl">
            <c:url value="/Js/highlighter/highlight-selected-line-min.js"/>
        </c:set>
    </c:otherwise>
</c:choose>
<html lang="${lang}">
    <c:set var="pageTitle" scope="page">
        <fmt:message key="sourceCode.pageTitle">
            <fmt:param>
                ${statistics.url}
            </fmt:param>
        </fmt:message>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="tgm-source-code">
        <%@include file="template/header-utils.jsp" %>
        <div class="container">
            <c:set var="pageName" scope="page">
                <fmt:message key="sourceCode.h1">
                    <fmt:param>${statistics.url}</fmt:param>
                </fmt:message>
            </c:set>
            <div class="row">
                <div class="span16">
                    <h1>
                        ${pageName}
                    </h1>

                </div>
                <div class="span16">
                    <a href="${configProperties['generatedHtmlExplanationLink']}">
                        <fmt:message key="sourceCode.characteristic"/>
                    </a>
                </div>
            </div><!-- class="row" -->
            <div class="row">
                <div class="span16">
                    <c:import url="template/source-code.jsp" />
                </div><!-- class=span16"-->
            </div><!-- class="row"-->
        </div><!-- class="container"-->
        <%@include file="template/footer.jsp" %>
        <script type="text/javascript" src="${jqueryUrl}"></script>
        <script type="text/javascript" src="${highlightSelectedLineJsUrl}"></script>
    </body>
</html>
</compress:html>