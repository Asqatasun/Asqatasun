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
        <fmt:message key="forgotten-password-confirmation.pageTitle"/>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="tgm-forgotten-password-confirmation">
        <div class="container">
            <c:set var="addLogo" scope="page" value="true"/>
            <%@include file="template/lang-box.jsp" %>
            <div class="row">
                <div class="span16">
                    <h1><fmt:message key="forgotten-password-confirmation.h1"/></h1>
                </div><!-- class="span16" -->
            </div><!-- class="row" -->
            <div class="row">
                <div class="span16">
                    <div class="alert-message block-message success">
                        <p><fmt:message key="forgotten-password-confirmation.message"><fmt:param value="${url}"/></fmt:message></p>
                        <div class="alert-actions">
                            <a class="btn small" href="<c:url value="/login.html"/>"><fmt:message key="forgotten-password-confirmation.backToLogin"/></a>
                        </div>
                    </div>
                </div><!-- class="span15 offset1" -->
            </div><!-- class="row" -->
        </div><!--  class="container" -->
        <%@include file="template/footer.jsp" %>
    </body>
</html>
</compress:html>