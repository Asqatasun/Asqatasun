<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri='/WEB-INF/cewolf.tld' prefix='cewolf' %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="${pageContext.response.locale}">
    <c:set var="pageTitle" scope="page">
        <fmt:message key="pageList.pageTitle">
            <fmt:param>
                ${param.wr}
            </fmt:param>
        </fmt:message>
    </c:set>
    <c:set var="addWebSnapr" scope="page" value="true"/>
    <%@include file="template/head.jsp" %>
    <body id="tgm-page-list" class="tgm">
        <div id="meta-border">
            <%@include file="template/header-utils.jsp" %>
            <c:set var="pageName" scope="page">
                <fmt:message key="pageList.h1"/>
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
                        <li><a href="#pageList"><fmt:message key="pageList.pageList"/></a></li>
                    </ol>
                </div>
            </div>
            <c:set var="hasSynthesisTitle" scope="request" value="true"/>
            <c:set var="hasBarChartLink" scope="request" value="false"/>
            <c:set var="hasGraphics" scope="request" value="false"/>
            <c:set var="hasPageCounter" scope="request" value="true"/>
            <c:set var="hasPagesListLink" scope="request" value="false"/>
            <c:set var="hasResultDispatchTitle" scope="request" value="false"/>
            <c:import url="template/synthesis.jsp" />
            <h2 id="page-list" class="cml cmr"><fmt:message key="pageList.pageList"/></h2>
            <table summary="Audit page list" id="page-list-table" class="data-table-list">
                <caption></caption>
                <tr>
                    <th id="httpStatus" class="col01" scope="col"><fmt:message key="pageList.pagesRepartitionHeader"/></th>
                    <th id="nbOfPages" class="col02" scope="col"><fmt:message key="pageList.nbPagesHeader"/></th>
                </tr>
                <tr class="row-odd">
                    <td class="col01" headers="httpStatus">
                        <a href="?wr=${param.wr}&amp;status=f2xx&amp;pageSize=50&amp;sortCriterion=mark&amp;sortDirection=2"><fmt:message key="pageList.f2xx"/></a> <fmt:message key="pageList.f2xxDetails"/>
                    </td>
                    <td class="col02" headers="nbOfPages">${auditedPagesCount}</td>
                </tr>
                <tr class="row-even">
                    <td class="col01" headers="httpStatus">
                        <a href="?wr=${param.wr}&amp;status=f3xx&amp;sortCriterion=httpStatusCode&amp;sortDirection=1"><fmt:message key="pageList.f3xx"/></a> <fmt:message key="pageList.f3xxDetails"/>
                    </td>
                    <td class="col02" headers="nbOfPages">${redirectedPagesCount}</td>
                </tr>
                <tr class="row-odd">
                    <td class="col01" headers="httpStatus">
                        <a href="?wr=${param.wr}&amp;status=f4xx&amp;sortCriterion=httpStatusCode&amp;sortDirection=1"><fmt:message key="pageList.f4xx"/></a> <fmt:message key="pageList.f4xxDetails"/>
                    </td>
                    <td class="col02" headers="nbOfPages">${errorPagesCount}</td>
                </tr>
            </table>
        </div><!-- id="meta-border"-->
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
        <script type="text/javascript" src="http://asset.open-s.com/Corporate/TGOL/Js/audit-set-up.js"></script>
        <%@include file="template/footer.jsp" %>
    </body>
</html>