<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri='/WEB-INF/cewolf.tld' prefix='cewolf' %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="${pageContext.response.locale}">
    <c:set var="pageTitle" scope="page">
        <fmt:message key="pageList.${param.status}"/>
        <fmt:message key="pageList.forTheAudit">
            <fmt:param>
                ${param.wr}
            </fmt:param>
        </fmt:message>
    </c:set>
    <c:set var="addWebSnapr" scope="page" value="true"/>
    <%@include file="template/head.jsp" %>
    <body id="tgm-page-list-${param.status}" class="tgm">
        <div id="meta-border">
            <%@include file="template/header-utils.jsp" %>
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
            <%@include file="template/breadcrumb.jsp" %>
            <div class="yui3-g">
                <div class="yui3-u-1">
                    <h1>
                        ${pageName}
                    </h1>
                </div>
            </div>
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
            
            <div class="yui3-g">
                <div class="yui3-u-1">
                    <form id="display-options" action="" method="get" enctype="application/x-www-form-urlencoded" class="cml cmr">
                    <fieldset>
                        <legend><fmt:message key="pageList.displayOptions"/></legend>
                        <div id="display-options-page-size">
                            <label for="pageSize"><fmt:message key="pageList.pageSize"/></label>
                            <select name="pageSize" id="pageSize">
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
                        <div id="display-options-sort">
                            <label for="sortCriterion"><fmt:message key="pageList.sortCriterion"/></label>
                            <select name="sortCriterion" id="sortCriterion">
                                <c:forEach items="${authorizedSortCriterion}" var="currentSortCriterion" varStatus="pStatus">
                                <option value="${currentSortCriterion}" <c:if test="${sortCriterionValue == currentSortCriterion}">selected="selected"</c:if>><fmt:message key="pageList.${currentSortCriterion}"/></option>
                                </c:forEach>
                            </select>
                            <label for="sortDirection"><fmt:message key="pageList.sortDirection"/></label>
                            <select name="sortDirection" id="sortDirection">
                                <option value="1" <c:if test="${sortDirectionValue == 1}">selected="selected"</c:if>><fmt:message key="pageList.ascending"/></option>
                                <option value="2" <c:if test="${sortDirectionValue == 2}">selected="selected"</c:if>><fmt:message key="pageList.descending"/></option>
                            </select>
                        </div>
                        <div id="display-options-sort-url">
                            <label for="sortByContainingUrl"><fmt:message key="pageList.sortByUrlContaining"/></label>
                            <input type="text" name="sortByContainingUrl" id="sortByContainingUrl" value="${param.sortByContainingUrl}" />
                        </div>
                        <div id="display-options-update">
                            <input type="hidden" name="wr" value="${param.wr}"/>
                            <input type="hidden" name="status" value="${param.status}"/>
                            <input type="submit" value="<fmt:message key="pageList.update"/>"/>
                        </div>
                    </fieldset>
                    </form>
                </div>
            </div>
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
            <div class="yui3-g">
                <c:if test="${pageList.fullListSize != 0}">
                <div class="yui3-u-1 result-items cmr cml">
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
                <div class="yui3-u-1">
                <display:table id="page-list-${param.status}" name="pageList" sort="external" defaultsort="1" pagesize="${pageList.objectsPerPage}" partialList="true" size="${pageList.fullListSize}" requestURI="" export="true" decorator="org.opens.tgol.report.pagination.PageListWrapper" summary="${summary}">
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
            </div>
        </div><!-- id="meta-border"-->
        <%@include file="template/footer.jsp" %>
    </body>
</html>