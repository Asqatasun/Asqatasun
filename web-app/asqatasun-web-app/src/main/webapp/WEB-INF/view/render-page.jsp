<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://tagutils" prefix="tg" %>
<!DOCTYPE html>
<%@include file="/WEB-INF/view/template/template_variables.jspf" %>

<!-- external js -->
<c:set var="jqueryUrl">
    <c:url value="/public/external_js/jquery-1.11.1.min.js?v${asqatasunVersion}"/>
</c:set>

<!-- internal js -->
<c:set var="highlightSelectedLineJsUrl">
    <c:url value="/public/js/highlighter/highlight-selected-line-min.js?v${asqatasunVersion}"/>
</c:set>

<html lang="${tg:lang(pageContext)}">
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
        <main class="container">
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
                    <a rel="noreferrer noopener" href="${configProperties['generatedHtmlExplanationLink']}">
                        <fmt:message key="sourceCode.characteristic"/>
                    </a>
                </div>
            </div><!-- class="row" -->
            <div class="row">
                <div class="span16">
                    <c:import url="template/source-code.jsp" />
                </div><!-- class=span16"-->
            </div><!-- class="row"-->
        </main><!-- class="container"-->
        <%@include file="template/footer.jsp" %>
        <script type="text/javascript" src="${jqueryUrl}"></script>
        <script type="text/javascript" src="${highlightSelectedLineJsUrl}"></script>
    </body>
</html>
</compress:html>
