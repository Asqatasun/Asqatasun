<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri='/WEB-INF/cewolf.tld' prefix='cewolf' %>

                <jsp:useBean id="stackedBarChartSeriesPaint" class="de.laures.cewolf.cpp.SeriesPaintProcessor" />
                <jsp:useBean id="stackedBarRenderer" class="de.laures.cewolf.cpp.BarRendererProcessor" />
		<jsp:useBean id="failedTestStatics" class="org.opens.tgol.charts.data.FailedTestStatistics">
                    <jsp:setProperty name="failedTestStatics" property="locale" value="${locale}"/>
                    <jsp:setProperty name="failedTestStatics" property="failedTestStatics" value="${counterByThemeMap}"/>
                </jsp:useBean>
                <cewolf:chart id="barChart${index}"
                       type="stackedHorizontalBar"
                       title=""
                       xaxislabel=""
                       yaxislabel=""
                       plotbackgroundcolor="#FAFAFA"
                       yaxisinteger="true"
                       xticklabelsvisible="true"
                       yticklabelsvisible="false"
                       xtickmarksvisible="false"
                       ytickmarksvisible="false"
                       bordervisible="false"
                       plotbordervisible="false"
                       showlegend="false">
                    <cewolf:data>
                        <cewolf:producer id="failedTestStatics" />
                    </cewolf:data>
                    <cewolf:chartpostprocessor id="stackedBarRenderer">
                        <cewolf:param name="showItemLabels" value="false"/>
                        <cewolf:param name="itemLabelColor" value="#FFFFFF"/>
                    </cewolf:chartpostprocessor>
                    <cewolf:chartpostprocessor id="stackedBarChartSeriesPaint">
			<cewolf:param name="0" value='<%= new String("#FC575E")%>'/>
                    </cewolf:chartpostprocessor>
                </cewolf:chart>
                <cewolf:imgurl
                    var="barChartUrl"
                    chartid="barChart${index}"
                    width="${width}"
                    height="${height}"
                    renderer="cewolf"/>
                <object id="nb-failed-results-by-theme-${index}" class="chart-object" type="image/png" data="<c:url value="/${barChartUrl}"/>" usemap="#barChart${index}">
                    <table summary="<fmt:message key="graph.mostInvalidedThemesSummaryAndCaption"/>">
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
                </object>
                <c:choose>
                    <c:when test="${hasBarChartLink == 'true'}">
                        <cewolf:map divId="barChartDiv${index}" chartId="barChart${index}" tooltipgeneratorid="testByThemeStatistics" linkgeneratorid="testByThemeStatistics" width="${width}" height="${height}"/>
                    </c:when>
                    <c:otherwise>
                        <cewolf:map divId="barChartDiv${index}" chartId="barChart${index}" tooltipgeneratorid="failedTestStatics" width="${width}" height="${height}"/>
                    </c:otherwise>
                </c:choose>                
