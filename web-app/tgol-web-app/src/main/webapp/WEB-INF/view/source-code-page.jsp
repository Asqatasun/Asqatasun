<%@taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html>
<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri='/WEB-INF/cewolf.tld' prefix='cewolf' %>
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
<html lang="${lang}">
    <c:set var="pageTitle" scope="page">
        <fmt:message key="sourceCode.pageTitle">
            <fmt:param>
                ${statistics.url}
            </fmt:param>
        </fmt:message>
    </c:set>
    <c:set var="addWebSnapr" scope="page" value="true"/>
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
            </div><!-- class="row" -->
            <div class="row">
                <div class="span16">
                    <c:import url="template/source-code.jsp" />
                </div><!-- class=span16"-->
            </div><!-- class="row"-->
        </div><!-- class="container"-->
        <%@include file="template/footer.jsp" %>
    </body>
</html>
</compress:html>