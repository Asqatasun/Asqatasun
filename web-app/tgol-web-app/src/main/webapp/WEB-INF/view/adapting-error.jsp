<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page import = "java.util.Date,java.text.SimpleDateFormat,java.text.ParseException"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="${pageContext.response.locale}">
    <c:set var="pageTitle" scope="page">
        <spring:message code="errorProcessingPage.pageTitle"/>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="tgm-adapting-error" class="tgm">
        <div id="meta-border">
            <%@include file="template/header-utils.jsp" %>
            <div class="yui3-g">
                <div class="yui3-u-1">
                    <h1>
                        <spring:message code="errorProcessingPage.h1"/>
                    </h1>
                </div><!-- class="yui3-u-1" -->
            </div><!-- class="yui3-g" -->
            <div class="yui3-g">
                <div class="yui3-u-1">
                    <h2><spring:message code="errorProcessingPage.message"/></h2>
                    <table summary="Audit meta-data" id="result-meta">
                    <caption>Audit meta-data</caption>
                        <tr>
                            <th id="meta-url" scope="row" class="col01"><spring:message code="resultPage.url"/></th>
                            <td class="col02"><a href="${param.auditUrl}">${param.auditUrl}</a></td>
                        </tr>
                        <%
                            String dateStr = request.getParameter("auditDate");
                            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                            Date date = formater.parse(dateStr);
                            request.setAttribute("auditDate", date);
                        %>
                        <tr>
                            <th id="meta-date" scope="row" class="col01"><spring:message code="resultPage.date"/></th>
                            <td class="col02"><fmt:formatDate type="both" value="${auditDate}" dateStyle="long" timeStyle="medium"/></td>
                        </tr>
                    </table>
                </div><!-- class="yui3-u-1" -->
                <div class="yui3-u-1">
                    <a href="<c:url value="/dispatch.html" />"><spring:message code="accessDeniedPage.backToHome"/></a>
                </div><!-- class="yui3-g" -->
            </div><!-- class="yui3-g" -->
        </div><!-- class="meta-border" -->
       <%@include file="template/footer.jsp" %>
    </body>
</html>

