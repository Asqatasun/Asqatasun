<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="${pageContext.response.locale}">
    <c:set var="pageTitle" scope="page">
        <fmt:message key="home.pageTitle"/>
    </c:set>
    <c:set var="addWebSnapr" scope="page" value="true"/>
    <%@include file="template/head.jsp" %>
    <body id="tgm-home" class="tgm">
        <div id="meta-border">
            <%@include file="template/header-utils.jsp" %>
            <div class="yui3-g">
                <div class="yui3-u">
                    <h1><fmt:message key="home.h1"/></h1>
                    ${isActivated}
                </div>
            </div>
            <c:forEach var="contract" items="${contractList}" varStatus="pContractSet">
                <div class="yui3-g">
                    <div id="project-${pContractSet.index}" class="one-project">
                        <c:set var="url" scope="page" value="${contract.url}"/>
                        <c:set var="contract" scope="page" value="${contract}"/>
                        <c:set var="proportion" scope="page" value="8"/>
                        <c:set var="size" scope="page" value="T"/>
                        <%@include file="template/thumbnail.jsp" %>
                    <c:choose>
                        <c:when test="${contract.isContractExpired != 'true'}">
                        <div class="yui3-u-5-8">
                            <h2 class="project-name"><a href="home/contract.html?cr=${contract.id}">${contract.label}</a></h2>
                            <div class="project-url"><a href="${contract.url}">${contract.url}</a></div>
                            <c:choose>
                                <c:when test='${contract.lastActInfo != null}'>
                                    <div class="project-status"><fmt:message key="home.lastAudit"/> : <fmt:formatDate type="date" value="${contract.lastActInfo.date}" dateStyle="long"/> <fmt:message key="home.at"/> <fmt:formatDate type="time" value="${contract.lastActInfo.date}"/></div>
                                </c:when>
                                <c:otherwise>
                                    <div class="project-status"><fmt:message key="home.noAudit"/></div>
                                </c:otherwise>
                            </c:choose>
                        </div><!-- class="yui3-u-3-4" -->
                        <div class="yui3-u-1-4">
                        <c:if test='${contract.lastActInfo != null}'>
                            <c:choose>
                                <c:when test="${contract.lastActInfo.status == 'COMPLETED'}">
                            <c:set var="mark" scope="page" value="${contract.lastActInfo.rawMark}"/>
                            <c:set var="scoreClass" scope="page" value="project-score"/>
                            <c:set var="displayWeightedMark" scope="page" value="false"/>
                            <c:set var="hasProgressInfo" scope="page" value="true"/>
                            <c:set var="progressValue" scope="page" value="${contract.siteAuditProgression}"/>
                            <c:set var="hasScoreFormulaLink" scope="page" value="false"/>
                            <%@include file="template/score.jsp" %>
                                </c:when>
                                <c:otherwise>
                            <div class="project-fail">
                                <fmt:message key="${contract.lastActInfo.status}"/>
                            </div>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                        </div><!-- class="yui3-u-1-8" -->
                        </c:when>
                        <c:otherwise>
                            <div class="yui3-u-3-4">
                                <h2 class="project-name">${contract.label}</h2>
                                <div class="project-expiration"><fmt:message key="home.projectHasExpired"/></div>
                                <div class="project-url"><a href="${contract.url}">${contract.url}</a></div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                    </div><!-- class="one-project" -->
                </div><!-- class="yui3-g" -->
            </c:forEach>
        </div>
        <div class="yui3-g" id="ft">
            <div class="yui3-u">
                &copy; <a href="http://www.Open-S.com/">Open-S</a>
            </div>
        </div>
    </body>
</html>

