<%@taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html>
<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
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
        <fmt:message key="pageList.${param.status}"/>
        <fmt:message key="pageList.forTheAudit">
            <fmt:param>
                ${param.wr}
            </fmt:param>
        </fmt:message>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="tgm-page-list-${param.status}">
        <%@include file="template/header-utils.jsp" %>
        <div class="container">
            <c:if test="${auditNumber == 'true'}">
                <c:set var="forTheAuditExtension" scope="page" value="forTheAudit."/>
            </c:if>
            <c:set var="pageName" scope="page">
                <fmt:message key="pageList.${param.status}.${forTheAuditExtension}h1">
                    <fmt:param>
                        ${param.wr}
                    </fmt:param>
                </fmt:message>
            </c:set>
            <ul class="breadcrumb">
                <li><a href="<c:url value="/home.html"/>"><fmt:message key="home.h1"/></a> <span class="divider"></span></li>
                <li><a href="<c:url value="/home/contract.html?cr=${cr}"/>">${contractName}</a> <span class="divider"></span></li>
                <c:if test="${auditNumber != 'true'}">
                <c:set var="auditSynthesisName" scope="page">
                    <fmt:message key="synthesisSite.h1">
                        <fmt:param>
                            ${param.wr}
                        </fmt:param>
                    </fmt:message>
                </c:set>
                <li><a href="<c:url value="/home/contract/audit-synthesis.html?wr=${param.wr}"/>">${auditSynthesisName}</a> <span class="divider"></span></li>
                <li><a href="<c:url value="/home/contract/page-list.html?wr=${param.wr}"/>"><fmt:message key="pageList.h1"/></a> <span class="divider"></span></li>
                </c:if>
                <li class="active">${pageName}</li>
            </ul>
            <div class="row">
                <div class="span16">
                    <h1>
                        ${pageName}
                    </h1>
                </div><!-- class="span16" -->
            </div><!-- class="row" -->
            <c:choose>
                <c:when test="${param.pageSize != null}">
                    <c:set var="pageSizeValue" scope="page" value="${param.pageSize}"/>
                </c:when>
                <c:otherwise>
                    <c:set var="pageSizeValue" scope="page" value="${pageList.objectsPerPage}"/>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${param.sortCriterion != null}">
                    <c:set var="sortCriterionValue" scope="page" value="${param.sortCriterion}"/>
                </c:when>
                <c:otherwise>
                    <c:set var="sortCriterionValue" scope="page" value="${pageList.sortCriterion}"/>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${param.sortDirection != null}">
                    <c:set var="sortDirectionValue" scope="page" value="${param.sortDirection}"/>
                </c:when>
                <c:otherwise>
                    <c:set var="sortDirectionValue" scope="page" value="${pageList.sortDirection.code}"/>
                </c:otherwise>
            </c:choose>
            <div id="display-options" class="row">
                <div class="span14 offset1">
                    <form id="display-options-form" method="get" enctype="application/x-www-form-urlencoded" class="option-console">
                        <input type="hidden" name="wr" value="${param.wr}"/>
                        <input type="hidden" name="status" value="${param.status}"/>
                        <fieldset>
                            <legend><fmt:message key="pageList.displayOptions"/></legend>
                            <div id="display-options-page-size" class="clearfix">
                                <label for="pageSize"><fmt:message key="pageList.pageSize"/></label>
                                <div class="input">
                                    <select id="pageSize" name="pageSize">
                                    <c:forEach items="${authorizedPageSize}" var="currentPageSize" varStatus="pStatus">
                                        <c:choose>
                                            <c:when test="${currentPageSize != -1}">
                                        <option value="${currentPageSize}" <c:if test="${pageSizeValue == currentPageSize}">selected="selected"</c:if>>${currentPageSize}</option>
                                            </c:when>
                                            <c:otherwise>
                                        <option value="-1" <c:if test="${pageSizeValue == -1}">selected="selected"</c:if>><fmt:message key="pageList.all"/></option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div id="display-options-sort-criterion" class="clearfix">
                                <label for="sortCriterion"><fmt:message key="pageList.sortCriterion"/></label>
                                <div class="input">
                                    <select name="sortCriterion" id="sortCriterion">
                                        <c:forEach items="${authorizedSortCriterion}" var="currentSortCriterion" varStatus="pStatus">
                                        <option value="${currentSortCriterion}" <c:if test="${sortCriterionValue == currentSortCriterion}">selected="selected"</c:if>><fmt:message key="pageList.${currentSortCriterion}"/></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div id="display-options-sort-direction" class="clearfix">
                                <label for="sortDirection"><fmt:message key="pageList.sortDirection"/></label>
                                <div class="input">
                                    <select name="sortDirection" id="sortDirection">
                                        <option value="1" <c:if test="${sortDirectionValue == 1}">selected="selected"</c:if>><fmt:message key="pageList.ascending"/></option>
                                        <option value="2" <c:if test="${sortDirectionValue == 2}">selected="selected"</c:if>><fmt:message key="pageList.descending"/></option>
                                    </select>
                                </div>
                            </div>
                            <div id="display-options-sort-url" class="clearfix">
                                <label for="sortByContainingUrl"><fmt:message key="pageList.sortByUrlContaining"/></label>
                                <div class="input">
                                    <input type="text" name="sortByContainingUrl" id="sortByContainingUrl" value="${param.sortByContainingUrl}" />
                                </div>
                            </div>
                            <div id="display-options-update" class="actions">
                                <input type="submit" class="btn" value="<fmt:message key="pageList.update"/>"/>
                            </div>
                        </fieldset>
                    </form>
                </div><!-- class="span10 offset1" -->
            </div><!-- class="row" -->
            <c:set var="urlTitle" scope="page">
               <fmt:message key="pageList.urlHeader"/>
            </c:set>
            <c:set var="weigthedMarkTitle" scope="page">
               <fmt:message key="pageList.weightedMarkHeader"/>
            </c:set>
            <c:set var="rawMarkTitle" scope="page">
               <fmt:message key="pageList.rawMarkHeader"/>
            </c:set>
            <c:set var="detailedResultTitle" scope="page">
               <fmt:message key="pageList.pageDetailedResult"/>
            </c:set>
            <c:set var="httpStatusCodeTitle" scope="page">
               <fmt:message key="pageList.httpStatusCode"/>
            </c:set>
            <c:set var="summary" scope="page">
                <fmt:message key="pageList.summary"/>
            </c:set>
            <div class="row">
                <c:if test="${pageList.fullListSize != 0}">
                <div class="span16 result-items">
                    <fmt:message key="pageList.results">
                        <fmt:param>
                            ${fromValue}
                        </fmt:param>
                        <fmt:param>
                            ${toValue}
                        </fmt:param>
                        <fmt:param>
                            ${pageList.fullListSize}
                        </fmt:param>
                    </fmt:message>
                </div>
                </c:if>
                <div class="span15 offset1">
                <display:table id="page-list-${param.status}" name="pageList" sort="external" defaultsort="1" pagesize="${pageList.objectsPerPage}" partialList="true" size="${pageList.fullListSize}" requestURI="" class="zebra-striped" decorator="org.opens.tgol.report.pagination.PageListWrapper" summary="${summary}">
                <c:choose>
                <c:when test="${pageList.objectsPerPage >= 25}">
                    <display:setProperty name="paging.banner.placement" value="both" />
                </c:when>
                <c:otherwise>
                    <display:setProperty name="paging.banner.placement" value="bottom" />
                </c:otherwise>
                </c:choose>
                <display:caption>${summary}</display:caption>
                <display:column property="url" autolink="true" title="${urlTitle}" headerClass="col01" class="col01" headerScope="col"/>
            <c:choose>
                <c:when test="${param.status == 'f2xx'}">
                    <display:column property="rawMark" title="${rawMarkTitle}" headerClass="col02" class="col02" headerScope="col"/>
                    <display:column property="weightedMark" title="${weigthedMarkTitle}" headerClass="col03" class="col03" headerScope="col"/>
                    <display:column property="detailedResultLink" title="${detailedResultTitle}" headerClass="col04" class="col04" headerScope="col"/>
                </c:when>
                <c:otherwise>
                    <display:column property="httpStatusCode" title="${httpStatusCodeTitle}" headerClass="col02" class="col02" headerScope="col"/>
                </c:otherwise>
            </c:choose>
                </display:table>
                </div>
            </div><!-- class="span15 offset1"-->
        </div><!-- class="container-fluid"-->
        <%@include file="template/footer.jsp" %>
    </body>
</html>
</compress:html>