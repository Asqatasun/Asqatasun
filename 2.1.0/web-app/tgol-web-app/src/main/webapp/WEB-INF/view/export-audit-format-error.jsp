<%@taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html>
<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
        <fmt:message key="exportAuditFormatError.pageTitle"/>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="export-audit-format-error">
        <%@include file="template/header-utils.jsp" %>
        <div class="container no-bg-container">
            <div class="row">
                <div class="span16">
                    <div class="alert-message block-message">
                        <h1>
                            <fmt:message key="exportAuditFormatError.h1"/>
                        </h1>
                        <p>
                            <fmt:message key="exportAuditFormatError.message">
                                <fmt:param>${param.format}</fmt:param>
                           </fmt:message>
                        </p>
                        <div class="alert-actions">
                            <a href="<c:url value="/home/contract/audit-result.html?wr=${param.wr}" />"><fmt:message key="exportAuditFormatError.backToAuditResult"/></a>
                        </div><!-- class="alert-actions"-->
                    </div><!-- class="alert-message block-message"-->
                </div><!-- class="span16" -->
            </div><!-- class="row" -->
        </div><!-- class="container" -->
       <%@include file="template/footer.jsp" %>
    </body>
</html>
</compress:html>