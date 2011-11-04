<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import = "java.util.Date,java.text.SimpleDateFormat,java.text.ParseException"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="${pageContext.response.locale}">
    <c:set var="pageTitle" scope="page">
        <fmt:message key="errorLoadingPage.pageTitle"/>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="tgm-loading-error" class="tgm">
        <div id="meta-border">
            <%@include file="template/header-utils.jsp" %>
            <div class="yui3-g">
                <div class="yui3-u-1">
                    <h1>
                        <fmt:message key="errorLoadingPage.h1"/>
                    </h1>
                </div><!-- class="yui3-u-1" -->
            </div><!-- class="yui3-g" -->
            <div class="yui3-g">
                <div class="yui3-u-1">
                    <h2><fmt:message key="errorLoadingPage.message"/></h2>
                    <table summary="Audit meta-data" id="result-meta">
                    <caption>Audit meta-data</caption>
                        <tr>
                            <th id="meta-url" scope="row" class="col01"><fmt:message key="resultPage.url"/></th>
                            <td class="col02"><a href="${param.auditUrl}">${param.auditUrl}</a></td>
                        </tr>
                        <tr>
                            <%
                                String dateStr = request.getParameter("auditDate");
                                SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                                Date date = formater.parse(dateStr);
                                request.setAttribute("auditDate", date);
                            %>
                            <th id="meta-date" scope="row" class="col01"><fmt:message key="resultPage.date"/></th>
                            <td class="col02"><fmt:formatDate type="both" value="${auditDate}" dateStyle="long" timeStyle="medium"/></td>
                        </tr>
                    </table>
                    <p><fmt:message key="errorLoadingPage.explanation"/></p>
                    <ul>
                        <li><fmt:message key="errorLoadingPage.explanationPageDetail1"/></li>
                        <li><fmt:message key="errorLoadingPage.explanationPageDetail2"/></li>
                        <li><fmt:message key="errorLoadingPage.explanationPageDetail3"/></li>
                    </ul>
                </div><!-- class="yui3-u-1" -->
                <div class="yui3-u-1">
                    <a href="<c:url value="/dispatch.html" />"><fmt:message key="accessDeniedPage.backToHome"/></a>
                </div><!-- class="yui3-g" -->
            </div><!-- class="yui3-g" -->
        </div><!-- class="meta-border" -->
       <%@include file="template/footer.jsp" %>
    </body>
</html>

