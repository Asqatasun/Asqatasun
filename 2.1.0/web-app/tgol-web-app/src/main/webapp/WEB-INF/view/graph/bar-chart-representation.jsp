<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri='/WEB-INF/cewolf.tld' prefix='cewolf' %>

                <jsp:useBean id="barChartSeriesPaint" class="de.laures.cewolf.cpp.SeriesPaintProcessor" />
                <jsp:useBean id="barChartRenderer" class="de.laures.cewolf.cpp.BarRendererProcessor" />
		<jsp:useBean id="testByThemeStatistics" class="org.opens.tgol.charts.data.TestByThemeStatistics">
                    <jsp:setProperty name="testByThemeStatistics" property="testByThemeStatics" value="${counterByThemeMap}"/>
                    <jsp:setProperty name="testByThemeStatistics" property="locale" value="${locale}"/>
                </jsp:useBean>
                <cewolf:chart id="barChart${index}"
                       type="stackedVerticalBar"
                       title=""
                       showlegend="${showLegend}"
                       xaxislabel="${xLabel}"
                       yaxislabel="${yLabel}"
                       plotbackgroundcolor="#FAFAFA"
                       yaxisinteger="true"
                       xticklabelsvisible="${showAxisLabel}"
                       yticklabelsvisible="${showAxisLabel}"
                       xtickmarksvisible="${showAxisLabel}"
                       ytickmarksvisible="${showAxisLabel}"
                       bordervisible="false"
                       plotbordervisible="false">
                    <cewolf:data>
                        <cewolf:producer id="testByThemeStatistics" />
                    </cewolf:data>
                    <cewolf:chartpostprocessor id="barChartRenderer">
                        <cewolf:param name="showItemLabels" value="false"/>
                        <cewolf:param name="itemLabelColor" value="#FFFFFF"/>
                    </cewolf:chartpostprocessor>
                    <cewolf:chartpostprocessor id="barChartSeriesPaint">
                        <cewolf:param name="0" value='<%= new String("#C43C35")%>'/>
			<cewolf:param name="1" value='<%= new String("#9FC54E")%>'/>
                        <cewolf:param name="2" value='<%= new String("#4EBAFF")%>'/>
                        <cewolf:param name="3" value='<%= new String("#AAAAAA")%>'/>
                    </cewolf:chartpostprocessor>
                </cewolf:chart>
                <cewolf:imgurl
                    var="barChartUrl"
                    chartid="barChart${index}"
                    width="${width}"
                    height="${height}"
                    renderer="cewolf"/>
                <object id="nb-results-by-theme-${index}" class="chart-object" type="image/png" data="<c:url value="/${barChartUrl}"/>" usemap="#barChart${index}">
                    <table summary="<fmt:message key="graph.resultRepartitionByThemeSummaryAndCaption"/>">
                        <caption><fmt:message key="graph.resultRepartitionByThemeSummaryAndCaption"/></caption>
                        <tr>
                            <td></td>
                            <c:forEach var="categories" items="${testByThemeStatistics.categoriesI18NLabel}" varStatus="categoriesIndex">
                            <th id="theme${index}categories${categoriesIndex.index}">${categories}</th>
                            </c:forEach>
                        </tr>
                        <c:forEach var="series" items="${testByThemeStatistics.series}" varStatus="seriesIndex">
                        <tr>
                            <th id="theme${index}series${seriesIndex.index}">${series}</th>
                            <c:forEach var="categories" items="${testByThemeStatistics.categories}" varStatus="categoriesIndex">
                            <td headers="theme${index}series${seriesIndex.index} theme${index}categories${categoriesIndex.index}">${testByThemeStatistics.endValues[seriesIndex.index][categoriesIndex.index]}</td>
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
                        <cewolf:map divId="barChartDiv${index}" chartId="barChart${index}" tooltipgeneratorid="testByThemeStatistics" width="${width}" height="${height}"/>
                    </c:otherwise>
                </c:choose>                
