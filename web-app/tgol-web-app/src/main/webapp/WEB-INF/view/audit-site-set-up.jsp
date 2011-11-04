<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri='/WEB-INF/cewolf.tld' prefix='cewolf' %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="${pageContext.response.locale}">
    <c:set var="pageTitle" scope="page">
        <fmt:message key="auditSetUpSite.pageTitle"/>
        <spring:hasBindErrors name="auditSetUpCommand">
            <fmt:message key="auditSetUp.errorPageTitle"/>
        </spring:hasBindErrors>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="tgm-site-set-up" class="tgm">
        <div id="meta-border">
            <%@include file="template/header-utils.jsp" %>
            <c:set var="pageName" scope="page">
                <fmt:message key="auditSetUpSite.h1"/>
            </c:set>
            <%@include file="template/breadcrumb.jsp" %>
            <div class="yui3-g">
                <div class="yui3-u-1">
                    <h1>
                        <fmt:message key="auditSetUpSite.h1"/>
                    </h1>
                </div><!-- class="yui3-u-1" -->
            </div><!-- class="yui3-g" -->
            <div class="yui3-g">
                <div class="yui3-u-1">
                    <c:if test="${defaultParamSet == 'false'}">
                    <p class="message-positif cml cmr">
                        <fmt:message key="auditSetUp.keptLastAuditParameters">
                            <fmt:param>${url}</fmt:param>
                        </fmt:message>
                    </p>
                    </c:if>
                </div>
                <div class="yui3-u-1">
                    <p class="cmr cml">
                        <fmt:message key="auditSetUp.message"/>
                    </p>
                </div>
                <div class="yui3-u-1">
                    <c:set var="postUrl" scope="page">
                        <c:url value="/home/contract/launch-audit-site.html?cr=${contractIdValue}"/>
                    </c:set>
                    <%@include file="template/set-up.jsp" %>
                </div><!-- class="yui3-u-1" -->
            </div><!-- class="yui3-g" -->
        </div><!-- id="meta-border"-->
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
    <script type="text/javascript" src="<c:url value="/Js/audit-set-up.js"/>"></script>
    <%@include file="template/footer.jsp" %>
    </body>
</html>