<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

            <c:choose>
                <c:when test="${not empty scoreId}">
            <div id="${scoreId}" class="${spanClass}">
                <div>${mark}<span class="percent">%</span></div>
            </div>                    
                </c:when>
                <c:when test="${not empty scoreClass}">
            <div class="${spanClass} ${scoreClass}">
                <div>${mark}<span class="percent">%</span></div>
            </div>                    
                </c:when>
                <c:otherwise>
            <div class="${spanClass} ${scoreClass}">
                <div>${mark}<span class="percent">%</span></div>
            </div>
            </c:otherwise>
            </c:choose>
            <c:if test="${hasScoreFormulaLink == 'true'}">
            <span id="score-formula">
                <a href="<fmt:message key="resultPage.scoreFormulaLinkURL"/>"><fmt:message key="resultPage.scoreFormulaLinkText"/></a>
            </span>
            </c:if>
            <c:if test="${hasProgressInfo == 'true'}">
                <c:choose>
                <c:when test="${not empty configProperties['cdnUrl']}">
                    <c:set var="increaseLogoUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Images/increase-narrow.png"/>
                    <c:set var="decreaseLogoUrl"value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Images/decrease-narrow.png"/>
                    <c:set var="stableLogoUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Images/stable-narrow.png"/>
                </c:when>
                <c:otherwise>
                    <c:set var="increaseLogoUrl">
                        <c:url value="/Images/increase-narrow.png"/>  
                    </c:set>
                    <c:set var="decreaseLogoUrl">
                        <c:url value="/Images/decrease-narrow.png"/>
                    </c:set>
                    <c:set var="stableLogoUrl">
                        <c:url value="/Images/stable-narrow.png"/>
                    </c:set>
                </c:otherwise>
                </c:choose>
                <c:choose>
                <c:when test="${progressValue eq 'PROGRESS'}">
                <div class="span1">
                    <img src="${increaseLogoUrl}" alt="<fmt:message key="score-increase"/>" title="<fmt:message key="score-increase"/>" class="score-progression"/>
                </div>
                </c:when>
                <c:when test="${progressValue eq 'REGRESS'}">
                <div class="span1">
                    <img src="${decreaseLogoUrl}" alt="<fmt:message key="score-decrease"/>" title="<fmt:message key="score-decrease"/>" class="score-progression"/>
                </div>
                </c:when>
                <c:when test="${progressValue eq 'STABLE'}">
                <div class="span1">
                    <img src="${stableLogoUrl}" alt="<fmt:message key="score-stable"/>" title="<fmt:message key="score-stable"/>" class="score-progression"/>
                </div>
                </c:when>
                </c:choose>
            </c:if>