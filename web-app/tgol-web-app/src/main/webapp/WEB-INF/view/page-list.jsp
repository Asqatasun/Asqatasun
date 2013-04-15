<%@taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html>
<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri='/WEB-INF/cewolf.tld' prefix='cewolf' %>
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
        <fmt:message key="pageList.pageTitle">
            <fmt:param>
                ${param.wr}
            </fmt:param>
        </fmt:message>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="tgm-page-list">
        <%@include file="template/header-utils.jsp" %>
        <div class="container">
            <c:set var="pageName" scope="page">
                <fmt:message key="pageList.h1"/>
            </c:set>
            <ul class="breadcrumb">
                <li><a href="<c:url value="/home.html"/>"><fmt:message key="home.h1"/></a> <span class="divider"></span></li>
                <li><a href="<c:url value="/home/contract.html?cr=${cr}"/>">${contractName}</a> <span class="divider"></span></li>
                <c:set var="auditSynthesisName" scope="page">
                    <fmt:message key="synthesisSite.h1">
                        <fmt:param>
                            ${param.wr}
                        </fmt:param>
                    </fmt:message>
                </c:set>
                <li><a href="<c:url value="/home/contract/audit-synthesis.html?wr=${param.wr}"/>">${auditSynthesisName}</a> <span class="divider"></span></li>
                <li class="active">${pageName}</li>
            </ul>
            <div class="row">
                <div class="span16">
                    <h1>
                        ${pageName}
                    </h1>
                </div><!-- class="span16"-->
            </div><!-- class="row"-->
            <c:set var="hasSynthesisTitle" scope="request" value="true"/>
            <c:set var="hasBarChartLink" scope="request" value="false"/>
            <c:set var="hasGraphics" scope="request" value="false"/>
            <c:set var="hasPageCounter" scope="request" value="true"/>
            <c:set var="hasPagesListLink" scope="request" value="false"/>
            <c:set var="hasResultDispatchTitle" scope="request" value="false"/>
            <c:import url="template/synthesis.jsp" />
            <div class="row">
                <div class="span16">
                    <h2 id="page-list"><fmt:message key="pageList.pageList"/></h2>
                </div><!-- id="span16"-->
                <div class="span15 offset1">
                    <table summary="Audit page list" id="page-list-table" class="zebra-striped">
                        <caption>Audit page list</caption>
                        <thead>
                            <tr>
                                <th id="httpStatus" class="col01" scope="col"><fmt:message key="pageList.pagesRepartitionHeader"/></th>
                                <th id="nbOfPages" class="col02" scope="col"><fmt:message key="pageList.nbPagesHeader"/></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:choose>
                                <c:when test="${statistics.auditScope == 'DOMAIN'}">
                                    <c:set var="pageList2xxUrl" value="?wr=${param.wr}&amp;status=f2xx"/>
                                </c:when>
                                <c:when test="${statistics.auditScope == 'SCENARIO'}">
                                    <c:set var="pageList2xxUrl" value="?wr=${param.wr}&amp;status=f2xx&amp;sortCriterion=rank&amp;sortDirection=2"/>
                                </c:when>
                            </c:choose>
                            <tr>
                                <td class="col01" headers="httpStatus">
                                    <a href="${pageList2xxUrl}"><fmt:message key="pageList.f2xx"/></a> <fmt:message key="pageList.f2xxDetails"/>
                                </td>
                                <td class="col02" headers="nbOfPages">${auditedPagesCount}</td>
                            </tr>
                            <tr>
                                <td class="col01" headers="httpStatus">
                                    <a href="?wr=${param.wr}&amp;status=f3xx"><fmt:message key="pageList.f3xx"/></a> <fmt:message key="pageList.f3xxDetails"/>
                                </td>
                                <td class="col02" headers="nbOfPages">${redirectedPagesCount}</td>
                            </tr>
                            <tr>
                                <td class="col01" headers="httpStatus">
                                    <a href="?wr=${param.wr}&amp;status=f4xx"><fmt:message key="pageList.f4xx"/></a> <fmt:message key="pageList.f4xxDetails"/>
                                </td>
                                <td class="col02" headers="nbOfPages">${errorPagesCount}</td>
                            </tr>
                        </tbody>
                    </table>
                </div><!-- id="span15 offset1"-->
            </div><!-- id="row"-->
        </div><!-- class="container"-->
        <%@include file="template/footer.jsp" %>
    </body>
</html>
</compress:html>