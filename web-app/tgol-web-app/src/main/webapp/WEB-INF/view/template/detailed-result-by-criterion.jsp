<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://tagutils" prefix="tg" %>

<div class="theme-nav bs-docs-sidebar">
    <ul class="nav-list bs-docs-sidenav">
        <c:forEach var="entry" items="${criterionResultMap}" varStatus="pResultMap">
        <li>
            <a href="#theme${entry.key.rank}">
                <fmt:message key="${entry.key.code}"/>
            </a>
        </li>
        </c:forEach>
    </ul>
</div>   
<div class="row">
    <div id="work-done" class="span16">
        <h2>
            <c:choose>
                <c:when test="${scope == 'page'}">
            <fmt:message key="resultPage.detailedResultPage"/>
                </c:when>
                <c:when test="${scope == 'site'}">
            <fmt:message key="resultPage.detailedResultSite"/>
                </c:when>
            </c:choose>
        </h2>
        <button id="expand-all" class="result-page-action">
            <fmt:message key="resultPage.expandAll"/>
        </button>
        <button id="collapse-all" class="result-page-action">
            <fmt:message key="resultPage.collapseAll"/>
        </button>
    </div><!-- class="span16" -->
</div><!-- class="row" -->
<c:if test="${tg:lang(pageContext) != 'en' && tg:lang(pageContext) != 'fr'}">
    <c:choose>
        <c:when test="${fn:startsWith(statistics.parametersMap['referential'], 'RGAA')}">
            <c:set var="ruleLang" value=" lang=fr "/>
        </c:when>
        <c:otherwise>
            <c:set var="ruleLang" value=" lang=en "/>
        </c:otherwise>
    </c:choose>
</c:if>
<form id="result-by-criterion-form" action="#">
    <c:forEach var="entry" 
               items="${criterionResultMap}" 
               varStatus="pResultMap">
    <div class="row theme-info">
        <div class="span16 theme">
            <div class="row">
                <div class="span1 theme-details-expand-action">
                    <img alt="<fmt:message key="resultPage.hideInfosOn">
                                 <fmt:param> 
                                    <fmt:message key="${entry.key.code}"/>
                                 </fmt:param>
                               </fmt:message>" 
                         src="${expandedImg}" 
                         class="hide-theme-details-link-icon">
                    <img alt="<fmt:message key="resultPage.displayInfosOn"> 
                                 <fmt:param>
                                    <fmt:message key="${entry.key.code}"/>
                                 </fmt:param>
                              </fmt:message>" 
                         src="${collapsedImg}" 
                         class="show-theme-details-link-icon">
                </div>
                <div class="span9 theme-label">
                    <h3 id="theme${entry.key.rank}">
                        <fmt:message key="result.theme"/> ${entry.key.rank} : <fmt:message key="${entry.key.code}"/>
                    </h3>
                </div>
                <div class="span6 theme-result-repartition">
                    <c:set scope="page" var="passedTitle">
                        <fmt:message key="resultPage.criteria">
                            <fmt:param value="${counterByThemeMap[entry.key].passedCount}"/>
                        </fmt:message>
                        <fmt:message key="passed"/>
                        <fmt:message key="resultPage.forTheTheme"/>
                        <fmt:message key="${entry.key.code}"/>
                    </c:set>
                    <img src="<c:url value="/Images/ico-passed-m-gray.png"/>" title="${passedTitle}" alt="${passedTitle}"/>
                    <span class="theme-result" title="${passedTitle}">
                        ${counterByThemeMap[entry.key].passedCount}
                    </span>
                    <c:set scope="page" var="failedTitle">
                        <fmt:message key="resultPage.criteria">
                            <fmt:param value="${counterByThemeMap[entry.key].failedCount}"/>
                        </fmt:message>
                        <fmt:message key="passed"/>
                        <fmt:message key="resultPage.forTheTheme"/>
                        <fmt:message key="${entry.key.code}"/>
                    </c:set>
                    <img src="<c:url value="/Images/ico-failed-m-gray.png"/>" title="${failedTitle}" alt="${failedTitle}"/>
                    <span class="theme-result" title="${failedTitle}">
                        ${counterByThemeMap[entry.key].failedCount}
                    </span>
                    <c:set scope="page" var="nmiTitle">
                        <fmt:message key="resultPage.criteria">
                            <fmt:param value="${counterByThemeMap[entry.key].nmiCount}"/>
                        </fmt:message>
                        <fmt:message key="failed"/>
                        <fmt:message key="resultPage.forTheTheme"/>
                        <fmt:message key="${entry.key.code}"/>
                    </c:set>
                    <img src="<c:url value="/Images/ico-nmi-m-gray.png"/>" title="${nmiTitle}" alt="${nmiTitle}"/>
                    <span class="theme-result" title="${nmiTitle}">
                        ${counterByThemeMap[entry.key].nmiCount}
                    </span>
                    <c:set scope="page" var="naTitle">
                        <fmt:message key="resultPage.criteria">
                            <fmt:param value="${counterByThemeMap[entry.key].naCount}"/>
                        </fmt:message>
                        <fmt:message key="na"/>
                        <fmt:message key="resultPage.forTheTheme"/>
                        <fmt:message key="${entry.key.code}"/>
                    </c:set>
                    <img src="<c:url value="/Images/ico-na-m-gray.png"/>" title="${naTitle}" alt="${naTitle}"/>
                    <span class="theme-result" title="${naTitle}">
                        ${counterByThemeMap[entry.key].naCount}
                    </span>
                    <c:set scope="page" var="ntTitle">
                        <fmt:message key="resultPage.criteria">
                            <fmt:param value="${counterByThemeMap[entry.key].ntCount}"/>
                        </fmt:message>
                        <fmt:message key="nt"/>
                        <fmt:message key="resultPage.forTheTheme"/>
                        <fmt:message key="${entry.key.code}"/>
                    </c:set>
                    <img src="<c:url value="/Images/ico-nt-m-gray.png"/>" title="${ntTitle}" alt="${ntTitle}"/>
                    <span class="theme-result" title="${ntTitle}">
                        ${counterByThemeMap[entry.key].ntCount}
                    </span>
                </div>
            </div>
        </div><!-- class="span16" -->
    </div><!-- class="row" -->
    <div id="theme${entry.key.rank}-details" class="theme-details">
        <c:forEach var="criterionResult" 
                   items="${entry.value}" 
                   varStatus="pCriterionResultList">
            <c:choose>
                <c:when test="${pCriterionResultList.index % 2 == 1}">
                    <c:set var="rowBgClass" value="" scope="page"/>
                </c:when>
                <c:otherwise>
                    <c:set var="rowBgClass" value="" scope="page"/>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${pCriterionResultList.index == fn:length(entry.value)-1}">
                    <c:set var="ruleGeneralResultClass" value="rule-general-result-last"/>
                </c:when>
                <c:otherwise>
                    <c:set var="ruleGeneralResultClass" value="rule-general-result"/>
                </c:otherwise>
            </c:choose>

        <div class="row">
            <div class="span16 ${ruleGeneralResultClass} ${rowBgClass}">
                <div class="row criterion-result-compact">
                    <div class="span1 criterion-id ">
                        <h4>${criterionResult.criterion.label}</h4>
                    </div><!-- class="span1 rule-id" -->
                    <div class="span11 criterion-label" ${ruleLang}>
                        <fmt:message key="${criterionResult.criterion.code}"/>
                    </div><!-- class="span9 rule-label" -->
                    <div class="span1 criterion-result offset2">
                        <img src="<c:url value="/Images/ico-${criterionResult.resultCode}-m.png"/>" 
                             alt="<fmt:message key="resultPage.criterion"/>
                                      ${criterionResult.criterion.label}
                                      <fmt:message key="${criterionResult.resultCode}"/>
                                 "/> 
                    </div>
                    <div class="span1 criterion-details">
                        <a href="<c:url value="/home/contract/criterion-result.html?wr=${param.wr}&amp;crit=${criterionResult.criterion.id}"/>" 
                           class="criterion-result-compact" 
                           title="<fmt:message key="resultPage.displayCriterionInfosOn">
                                    <fmt:param>${criterionResult.criterion.label}</fmt:param>
                                  </fmt:message>">
                            <img src="<c:url value="/Images/criterion-detail.png"/>" 
                                 alt="<fmt:message key="resultPage.displayCriterionInfosOn">
                                    <fmt:param>${criterionResult.criterion.label}</fmt:param>
                                  </fmt:message>" />
                        </a>
                    </div><!-- class="span1 criterion-details" -->
                </div><!-- class="row" -->
            </div><!-- class="span15 offset1 rule-general-result rowBgClass " -->
        </div><!-- class="row" -->
        </c:forEach>
    </div><!-- class="row" -->
    </c:forEach>
</form>
