<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

                <c:if test="${displayWeightedMark == 'true'}">
                <c:choose>
                    <c:when test='${weightedMark >= 90}'>
                        <c:set var="weightedMarkGrade" scope="page" value="grade-a"/>
                    </c:when>
                    <c:when test='${weightedMark < 90 && weightedMark >=80}'>
                        <c:set var="weightedMarkGrade" scope="page" value="grade-b"/>
                    </c:when>
                    <c:when test='${weightedMark < 80 && weightedMark >=70}'>
                        <c:set var="weightedMarkGrade" scope="page" value="grade-c"/>
                    </c:when>
                    <c:when test='${weightedMark < 70 && weightedMark >=60}'>
                        <c:set var="weightedMarkGrade" scope="page" value="grade-d"/>
                    </c:when>
                    <c:when test='${weightedMark < 60 && weightedMark >=50}'>
                        <c:set var="weightedMarkGrade" scope="page" value="grade-e"/>
                    </c:when>
                    <c:when test='${weightedMark < 50}'>
                        <c:set var="weightedMarkGrade" scope="page" value="grade-f"/>
                    </c:when>
                </c:choose>
                </c:if>
                <c:choose>
                    <c:when test='${mark >= 90}'>
                        <c:set var="markGrade" scope="page" value="grade-a"/>
                    </c:when>
                    <c:when test='${mark < 90 && mark >=80}'>
                        <c:set var="markGrade" scope="page" value="grade-b"/>
                    </c:when>
                    <c:when test='${mark < 80 && mark >=70}'>
                        <c:set var="markGrade" scope="page" value="grade-c"/>
                    </c:when>
                    <c:when test='${mark < 70 && mark >=60}'>
                        <c:set var="markGrade" scope="page" value="grade-d"/>
                    </c:when>
                    <c:when test='${mark < 60 && mark >=50}'>
                        <c:set var="markGrade" scope="page" value="grade-e"/>
                    </c:when>
                    <c:when test='${mark < 50}'>
                        <c:set var="markGrade" scope="page" value="grade-f"/>
                    </c:when>
                </c:choose>

                <div class=" ${scoreClass} ${markGrade}">
                    ${mark}%
                    <c:if test="${displayWeightedMark == 'true'}">
                        <span class="weighted-mark-label"> <fmt:message key="resultPage.weightedMark"/> </span>
                        <span class="${weightedScoreClass} ${weightedMarkGrade}">${weightedMark}%</span>
                    </c:if>
                    <c:if test="${hasProgressInfo == 'true'}">
                        <c:choose>
                            <c:when test="${progressValue == 'PROGRESS'}">
                                <img src="<c:url value="/Images/increase-narrow.png"/>" alt="<fmt:message key="score-increase"/>" title="<fmt:message key="score-increase"/>" class="score-progression"/>
                            </c:when>
                            <c:when test="${progressValue == 'REGRESS'}">
                                <img src="<c:url value="/Images/decrease-narrow.png"/>" alt="<fmt:message key="score-decrease"/>" title="<fmt:message key="score-decrease"/>" class="score-progression"/>
                            </c:when>
                            <c:when test="${progressValue == 'STABLE'}">
                                <img src="<c:url value="/Images/stable-narrow.png"/>" alt="<fmt:message key="score-stable"/>" title="<fmt:message key="score-stable"/>" class="score-progression"/>
                            </c:when>
                        </c:choose>
                    </c:if>
                    <c:if test="${hasScoreFormulaLink == 'true'}">
                    <p id="score-formula">
                        <a href="<fmt:message key="resultPage.scoreFormulaLinkURL"/>"><fmt:message key="resultPage.scoreFormulaLinkText"/></a>
                    </p>
                    </c:if>
                </div>