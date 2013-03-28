<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

		<jsp:useBean id="failedTestStatics" class="org.opens.tgol.charts.data.FailedTestStatistics">
                    <jsp:setProperty name="failedTestStatics" property="locale" value="${locale}"/>
                    <jsp:setProperty name="failedTestStatics" property="failedTestStatics" value="${counterByThemeMap}"/>
                </jsp:useBean>
                <table id="top5-failed-theme" summary="<fmt:message key="graph.mostInvalidedThemesSummaryAndCaption"/>">
                    <caption><fmt:message key="graph.mostInvalidedThemesSummaryAndCaption"/></caption>
                    <tr>
                        <td></td>
                        <c:forEach var="categories" items="${failedTestStatics.categories}" varStatus="categoriesIndex">
                        <th id="top5theme${index}categories${categoriesIndex.index}">${categories}</th>
                        </c:forEach>
                    </tr>
                    <c:forEach var="series" items="${failedTestStatics.series}" varStatus="seriesIndex">
                    <tr>
                        <th id="top5theme${index}series${seriesIndex.index}">${series}</th>
                        <c:forEach var="categories" items="${failedTestStatics.categories}" varStatus="categoriesIndex">
                        <td headers="top5theme${index}series${seriesIndex.index} top5theme${index}categories${categoriesIndex.index}">${failedTestStatics.endValues[seriesIndex.index][categoriesIndex.index]}</td>
                        </c:forEach>
                    </tr>
                    </c:forEach>
                </table>
                <div id="top5-failed-theme-graph"></div>
