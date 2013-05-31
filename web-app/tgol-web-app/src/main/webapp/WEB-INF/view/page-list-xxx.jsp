<%@taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html>
<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
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
<c:choose> 
   <c:when test="${not empty configProperties['cdnUrl']}">
        <!-- external js-->
        <c:set var="jqueryUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/External-Js/jquery-1.9.1.min.js"/>
        <c:set var="d3JsUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/External-Js/d3.v3.min.js" scope="request"/>
        <c:set var="r2d3JsUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/External-Js/r2d3.v2.min.js" scope="request"/>

        <!-- internal js-->
        <c:set var="scoreJsUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Js/score/score-min.js" scope="request"/>
        <c:set var="pageListScoreJsUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Js/score/score-page-list-min.js" scope="request"/>
        <c:set var="scoreIEJsUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Js/score/score-ie-min.js" scope="request"/>
        <c:set var="pageListScoreIEJsUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Js/score/score-page-list-ie-min.js" scope="request"/>
        
        <!-- images -->
        <c:set var="externalLinkImgUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Images/window-duplicate.png"/>
    </c:when>
    <c:otherwise>
        <!-- external js-->
        <c:set var="jqueryUrl">
            <c:url value="/External-Js/jquery-1.9.1.min.js"/>
        </c:set>        
        <c:set var="d3JsUrl" scope="request">
            <c:url value="/External-Js/d3.v3.min.js"/>
        </c:set> 
        <c:set var="r2d3JsUrl" scope="request">
            <c:url value="/External-Js/r2d3.v2.min.js"/>
        </c:set> 
        
        <!-- internal js-->
        <c:set var="scoreJsUrl" scope="request">
            <c:url value="/Js/score/score-min.js"/>
        </c:set>
        <c:set var="scoreIEJsUrl" scope="request">
            <c:url value="/Js/ie/score/score-ie-min.js"/>
        </c:set>
        <c:set var="pageListScoreJsUrl" scope="request">
            <c:url value="/Js/score/score-page-list-f2xx-min.js"/>
        </c:set>
        <c:set var="pageListScoreIEJsUrl" scope="request">
            <c:url value="/Js/ie/score/score-page-list-f2xx-ie-min.js"/>
        </c:set>
        
        <!--images -->
        <c:set var="externalLinkImgUrl">
            <c:url value="/Images/window-duplicate.png"/>  
        </c:set>
    </c:otherwise>
</c:choose>
<c:set var="invalidTestCriterion" value="false"/>
<c:if test="${not empty param.test}">
    <c:set var="invalidTestCriterion" value="true"/>
</c:if>
<html lang="${lang}">
    <c:set var="pageTitle" scope="page">
        <fmt:message key="pageList.${param.status}"/>
        <fmt:message key="pageList.forTheAudit">
            <fmt:param>
                ${param.audit}
            </fmt:param>
        </fmt:message>
        <c:if test="${invalidTestCriterion}">
            <fmt:message key="pageList.invalidatingTheTest">
                <fmt:param>
                    ${param.test}
                </fmt:param>
            </fmt:message>
        </c:if>
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
                        ${param.audit}
                    </fmt:param>
                </fmt:message>
                <c:if test="${invalidTestCriterion}">
                    <fmt:message key="pageList.invalidatingTheTest">
                        <fmt:param>
                            ${param.test}
                        </fmt:param>
                    </fmt:message>
                </c:if>
            </c:set>
            <ul class="breadcrumb">
                <li><a href="<c:url value="/home.html"/>"><fmt:message key="home.h1"/></a> <span class="divider"></span></li>
                <li><a href="<c:url value="/home/contract.html?cr=${cr}"/>">${contractName}</a> <span class="divider"></span></li>
                <c:if test="${auditNumber != 'true'}">
                <c:set var="auditSynthesisName" scope="page">
                    <fmt:message key="synthesisSite.h1">
                        <fmt:param>
                            ${param.audit}
                        </fmt:param>
                    </fmt:message>
                </c:set>
                <li><a href="<c:url value="/home/contract/audit-synthesis.html?audit=${param.audit}"/>">${auditSynthesisName}</a> <span class="divider"></span></li>
                    <c:if test="${not invalidTestCriterion}">
                <li><a href="<c:url value="/home/contract/page-list.html?audit=${param.audit}"/>"><fmt:message key="pageList.h1"/></a> <span class="divider"></span></li>
                    </c:if>
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
            <div id="page-list-option-console" class="row option-console">
                <div class="span16">
                    <div id="page-list-option-console-title" class="option-console-title">
                        <fmt:message key="pageList.displayOptions"/>
                    </div>
                </div>
                <div class="span16">
                    <form method="get" enctype="application/x-www-form-urlencoded" class="form-stacked">
                        <input type="hidden" name="audit" value="${param.audit}"/>
                        <input type="hidden" name="status" value="${param.status}"/>
                        <c:if test="${param.test != null}">
                        <input type="hidden" name="test" value="${param.test}"/>
                        </c:if>
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
                                    <option value="2" <c:if test="${sortDirectionValue == 2}">selected="selected"</c:if>><fmt:message key="pageList.ascending"/></option>
                                    <option value="1" <c:if test="${sortDirectionValue == 1}">selected="selected"</c:if>><fmt:message key="pageList.descending"/></option>
                                </select>
                            </div>
                        </div>
                        <div id="display-options-sort-url" class="clearfix">
                            <label for="sortByContainingUrl"><fmt:message key="pageList.sortByUrlContaining"/></label>
                            <div class="input">
                                <input type="text" name="sortByContainingUrl" id="sortByContainingUrl" value="${param.sortByContainingUrl}" />
                            </div>
                        </div>
                        <div id="display-options-update" class="actions option-console-update">
                            <input type="submit" class="update-action" value="<fmt:message key="pageList.update"/>"/>
                        </div>
                    </form>
                </div><!-- class="span10 offset1" -->
            </div><!-- class="row" -->
            <c:set var="rankTitle" scope="page">
               <fmt:message key="pageList.rankHeader"/>
            </c:set>
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
                <div class="span16 tg-table-container">
                <display:table id="page-list-${param.status}" name="pageList" sort="external" defaultsort="2" pagesize="${pageList.objectsPerPage}" partialList="true" size="${pageList.fullListSize}" requestURI="" class="tg-table" decorator="org.opens.tgol.report.pagination.PageListWrapper" summary="${summary}">
                    <c:choose>
                    <c:when test="${pageList.objectsPerPage >= 25}">
                        <display:setProperty name="paging.banner.placement" value="both" />
                    </c:when>
                    <c:otherwise>
                        <display:setProperty name="paging.banner.placement" value="bottom" />
                    </c:otherwise>
                    </c:choose>
                    <display:caption>${summary}</display:caption>
                    <c:choose>
                        <c:when test="${statistics.auditScope == 'SCENARIO'}">
                            <display:column property="rank" title="${rankTitle}" headerClass="rankCol" class="rankCol" headerScope="col"/>
                            <display:column property="urlWithPageResultLinkAndExternalLink" title="${urlTitle}" headerClass="urlCol" class="urlCol" headerScope="col"/>
                            <c:choose>
                                <c:when test="${param.status == 'f2xx'}">
                                    <display:column property="rawMark" title="${rawMarkTitle}" headerClass="markCol" class="markCol" headerScope="col"/>
                                </c:when>
                                <c:otherwise>
                                    <display:column property="httpStatusCode" title="${httpStatusCodeTitle}" headerClass="statusCol" class="statusCol" headerScope="col"/>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            <c:choose>
                                <c:when test="${param.status == 'f2xx'}">
                                    <display:column property="urlWithPageResultLinkAndExternalLink" title="${urlTitle}" headerClass="urlCol" class="urlCol" headerScope="col"/>
                                    <display:column property="rawMark" title="${rawMarkTitle}" headerClass="markCol" class="markCol" headerScope="col"/>
                                    <display:column property="rank" title="${rankTitle}" headerClass="rankCol" class="rankCol" headerScope="col"/>
                                </c:when>
                                <c:otherwise>
                                    <display:column property="urlWithExternalLink" title="${urlTitle}" headerClass="urlCol" class="urlCol" headerScope="col"/>
                                    <display:column property="httpStatusCode" title="${httpStatusCodeTitle}" headerClass="statusCol" class="statusCol" headerScope="col"/>
                                </c:otherwise>
                            </c:choose>
                        </c:otherwise>
                    </c:choose>
                </display:table>
                </div><!-- class="span16"-->
            </div><!-- class="row"-->
        </div><!-- class="container"-->
        <%@include file="template/footer.jsp" %>
        <script type="text/javascript" src="${jqueryUrl}"></script>
        <!--[if lte IE 8]>
        <script type="text/javascript" src="${r2d3JsUrl}"></script>
        <script type="text/javascript" src="${scoreIEJsUrl}"></script>
        <script type="text/javascript" src="${pageListScoreIEJsUrl}"></script>
        <![endif]-->
        <!--[if gt IE 8]>
        <script type="text/javascript" src="${d3JsUrl}"></script>
        <script type="text/javascript" src="${scoreJsUrl}"></script>
        <script type="text/javascript" src="${pageListScoreJsUrl}"></script>
        <![endif]-->
        <!--[if !IE]><!-->
        <script type="text/javascript" src="${d3JsUrl}"></script>
        <script type="text/javascript" src="${scoreJsUrl}"></script>
        <script type="text/javascript" src="${pageListScoreJsUrl}"></script>
        <!--<![endif]-->
    </body>
</html>
</compress:html>