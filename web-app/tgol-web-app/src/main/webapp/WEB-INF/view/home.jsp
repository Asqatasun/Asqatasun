<%@taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html>
<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
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
        <!-- external js -->
        <c:set var="jqueryUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/External-Js/jquery-1.9.1.min.js"/>
        <c:set var="d3JsUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Js/d3.v3.min.js" scope="request"/>
        <c:set var="raphaelJsUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Js/r2d3.v2.min.js" scope="request"/>
        
        <!-- internal js -->        
        <c:set var="scoreJsUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Js/score/score-min.js" scope="request"/>
        <c:set var="scoreIEJsUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Js/ie/score/score-ie-min.js" scope="request"/>
        <c:set var="homeScoreJsUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Js/score/score-home-min.js" scope="request"/>
        <c:set var="homeScoreIEJsUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Js/ie/score/score-home-ie-min.js" scope="request"/>
        
        <!-- external images -->
        <c:set var="gearImgUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Images/gear.png"/>
    </c:when>
    <c:otherwise>
        <!-- external js -->
        <c:set var="jqueryUrl">
            <c:url value="/External-Js/jquery-1.9.1.min.js"/>
        </c:set>
        <c:set var="d3JsUrl" scope="request">
            <c:url value="/External-Js/d3.v3.min.js"/>
        </c:set> 
        <c:set var="r2d3JsUrl" scope="request">
            <c:url value="/External-Js/r2d3.v2.min.js"/>
        </c:set> 

        <!-- internal js -->
        <c:set var="homeScoreJsUrl" scope="request">
            <c:url value="/Js/score/score-home-min.js"/>
        </c:set> 
        <c:set var="homeScoreIEJsUrl" scope="request">
            <c:url value="/Js/ie/score/score-home-ie-min.js"/>
        </c:set>
        <c:set var="scoreJsUrl" scope="request">
            <c:url value="/Js/score/score-min.js"/>
        </c:set> 
        <c:set var="scoreIEJsUrl" scope="request">
            <c:url value="/Js/ie/score/score-ie-min.js"/>
        </c:set> 

        <!-- external images -->
        <c:set var="gearImgUrl">
            <c:url value="/Images/gear.png"/>  
        </c:set>
    </c:otherwise>
</c:choose>
<html lang="${lang}">
    <c:set var="pageTitle" scope="page">
        <fmt:message key="home.pageTitle"/>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="tgm-home">
        <%@include file="template/header-utils.jsp" %>
        <div class="container">
            <div class="row">
                <div class="span16">
                    <h1><fmt:message key="home.h1"/></h1>
                </div>
            </div>
            <c:if test="${fn:length(contractList) > 1}">
            <c:import url="template/sort-contract-console.jsp" />
            </c:if>
            <div class="row">
                <div class="span16">
                <c:forEach var="contract" items="${contractList}" varStatus="pContractSet">
                    <div id="project-${pContractSet.index}" class="row one-project">
                        <div class="span16 project-border"></div>
                        <c:set var="url" scope="page" value="${contract.url}"/>
                        <c:set var="contract" scope="page" value="${contract}"/>
                        <c:set var="proportion" scope="page" value="span3"/>
                        <c:set var="size" scope="page" value="T"/>
                        <%@include file="template/thumbnail.jsp" %>
                        <c:choose>
                            <c:when test="${contract.isContractExpired != 'true'}">
                        <div class="span10">
                            <h2 class="project-name">
                                <a href="home/contract.html?cr=${contract.id}">${contract.label}</a>
                                <c:if test="${contract.isActRunning == 'true'}">
                                <img src="${gearImgUrl}" title="<fmt:message key="home.actRunning"/>" alt="<fmt:message key="home.actRunning"/>" class="running-audit"/>
                                </c:if>
                            </h2>
                             <!--   
                            <div class="project-actions">
                                <c:forEach var="contractAction" items="${contract.actionList}" varStatus="pContractAction">
                                    <c:if test="${contractAction.actionEnabled}">
                                    <c:choose>
                                        <c:when test="${not empty configProperties['cdnUrl']}">
                                            <c:set var="contractActionImgUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}${contractAction.enabledActionImageUrl}"/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:set var="contractActionImgUrl">
                                                <c:url value="${contractAction.enabledActionImageUrl}"/>
                                            </c:set>
                                        </c:otherwise>
                                    </c:choose>    
                                <span>
                                    <a href="<c:url value="${contractAction.actionUrl}?cr=${contract.id}"/>" title="<fmt:message key="${contractAction.actionI81NCode}"/>">
                                        <img src="${contractActionImgUrl}" alt="<fmt:message key="${contractAction.actionI81NCode}"/>" class="action-s"/>
                                    </a>
                                </span>
                                    </c:if>
                                </c:forEach>
                            </div>-->
                            <c:if test="${not empty contract.url}">
                            <div class="project-url"><a href="${contract.url}">${contract.url}</a></div>
                            </c:if>
                            <c:choose>
                                <c:when test='${contract.lastActInfo != null}'>
                                    <div class="project-status"><span class="last-audit-label"><fmt:message key="home.lastAudit"/></span> : <fmt:formatDate type="date" value="${contract.lastActInfo.date}" dateStyle="long"/> <fmt:message key="home.at"/> <fmt:formatDate type="time" value="${contract.lastActInfo.date}"/></div>
                                </c:when>
                                <c:otherwise>
                                    <div class="project-status"><span class="last-audit-label"><fmt:message key="home.noAudit"/></span></div>
                                </c:otherwise>
                            </c:choose>
                        </div><!-- class="span9"-->
                        <c:if test='${contract.lastActInfo != null}'>
                            <c:choose>
                                <c:when test="${contract.lastActInfo.status == 'COMPLETED'}">
                            <c:set var="mark" scope="page" value="${contract.lastActInfo.rawMark}"/>
                            <c:set var="scoreClass" scope="page" value="one-project-score"/>
                            <c:set var="scoreId" scope="page" value=""/>
                            <c:set var="hasProgressInfo" scope="page" value="true"/>
                            <c:set var="progressValue" scope="page" value="${contract.siteAuditProgression}"/>
                            <c:set var="hasScoreFormulaLink" scope="page" value="false"/>
                            <c:set var="spanClass" scope="page" value="span2"/>
                            <%@include file="template/score.jsp" %>
                                </c:when>
                                <c:otherwise>
                        <div class="span2">
                            <div class="project-fail">
                                <c:if test="${isActRunning == 'true'}">
                                    <img src="" alt="<fmt:message key="home.noAudit"/>"/>
                                </c:if>
                                <fmt:message key="${contract.lastActInfo.status}"/>
                            </div>
                            </div><!-- class="span3" -->
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                            </c:when>
                            <c:otherwise>
                        <div class="span12">
                            <h2 class="project-name">${contract.label}</h2>
                            <div class="project-expiration"><fmt:message key="home.projectHasExpired"/></div>
                            <div class="project-url"><a href="${contract.url}">${contract.url}</a></div>
                        </div><!-- class="span11 one-project" -->
                            </c:otherwise>
                        </c:choose>
                    </div><!-- id="project-index" class="row one-project" -->
                    <c:if test="${fn:length(contractList) == pContractSet.index + 1}">
                        <div class="row one-project">
                            <div class="span16 project-border"></div>
                        </div>
                    </c:if>
                </c:forEach>
                </div><!-- class="span16" -->
            </div><!-- class="row" -->
        </div><!-- class="container" -->
        <%@include file="template/footer.jsp" %>
        <script type="text/javascript" src="${jqueryUrl}"></script>
        <!--[if lte IE 8]>
        <script type="text/javascript" src="${r2d3JsUrl}"></script>
        <script type="text/javascript" src="${scoreIEJsUrl}"></script>
        <script type="text/javascript" src="${homeScoreIEJsUrl}"></script>
        <![endif]-->
        <!--[if gt IE 8]>
        <script type="text/javascript" src="${d3JsUrl}"></script>
        <script type="text/javascript" src="${scoreJsUrl}"></script>
        <script type="text/javascript" src="${homeScoreJsUrl}"></script>
        <![endif]-->
        <!--[if !IE]><!-->
        <script type="text/javascript" src="${d3JsUrl}"></script>
        <script type="text/javascript" src="${scoreJsUrl}"></script>
        <script type="text/javascript" src="${homeScoreJsUrl}"></script>
        <!--<![endif]-->
    </body>
</html>
</compress:html>