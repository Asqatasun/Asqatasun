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
        <c:set var="gearImgUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Images/gear.png"/>
    </c:when>
    <c:otherwise>
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
            <div class="row">
                <div class="span14 offset1">
                <c:forEach var="contract" items="${contractList}" varStatus="pContractSet">
                    <c:choose>
                        <c:when test="${fn:length(contractList) == pContractSet.index + 1}">
                            <c:set var="projectClass" value="one-project-last"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="projectClass" value="one-project"/>
                        </c:otherwise>
                    </c:choose>
                    <div id="project-${pContractSet.index}" class="row ${projectClass}">
                        <c:set var="url" scope="page" value="${contract.url}"/>
                        <c:set var="contract" scope="page" value="${contract}"/>
                        <c:set var="proportion" scope="page" value="span2"/>
                        <c:set var="size" scope="page" value="T"/>
                        <%@include file="template/thumbnail.jsp" %>
                        <c:choose>
                            <c:when test="${contract.isContractExpired != 'true'}">
                        <div class="span9">
                            <h2 class="project-name">
                                <a href="home/contract.html?cr=${contract.id}">${contract.label}</a>
                                <c:if test="${contract.isActRunning == 'true'}">
                                <img src="${gearImgUrl}" title="<fmt:message key="home.actRunning"/>" alt="<fmt:message key="home.actRunning"/>" class="running-audit"/>
                                </c:if>
                            </h2>
                            <div class="project-url"><a href="${contract.url}">${contract.url}</a></div>
                            <c:choose>
                                <c:when test='${contract.lastActInfo != null}'>
                                    <div class="project-status"><fmt:message key="home.lastAudit"/> : <fmt:formatDate type="date" value="${contract.lastActInfo.date}" dateStyle="long"/> <fmt:message key="home.at"/> <fmt:formatDate type="time" value="${contract.lastActInfo.date}"/></div>
                                </c:when>
                                <c:otherwise>
                                    <div class="project-status"><fmt:message key="home.noAudit"/></div>
                                </c:otherwise>
                            </c:choose>
                        </div><!-- class="span9"-->
                        <c:if test='${contract.lastActInfo != null}'>
                            <c:choose>
                                <c:when test="${contract.lastActInfo.status == 'COMPLETED'}">
                            <c:set var="mark" scope="page" value="${contract.lastActInfo.rawMark}"/>
                            <c:set var="scoreClass" scope="page" value="project-score"/>
                            <c:set var="displayWeightedMark" scope="page" value="false"/>
                            <c:set var="hasProgressInfo" scope="page" value="true"/>
                            <c:set var="progressValue" scope="page" value="${contract.siteAuditProgression}"/>
                            <c:set var="hasScoreFormulaLink" scope="page" value="false"/>
                            <c:set var="addSpanToDiv" scope="page" value="true"/>
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
                </c:forEach>
                </div><!-- class="span14 offset1" -->
            </div><!-- class="row" -->
        </div><!-- class="container" -->
        <%@include file="template/footer.jsp" %>
    </body>
</html>
</compress:html>