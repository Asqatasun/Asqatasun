<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri='/WEB-INF/cewolf.tld' prefix='cewolf' %>
                <c:set var="title" scope="request">
                    <fmt:message key="graph.siteHistorySummaryAndCaption"/>
                </c:set>
                <jsp:useBean id="timeSeriesPaint" class="org.opens.tgol.charts.renderer.TanaguruSeriesPaintProcessor" >
                    <jsp:setProperty name="timeSeriesPaint" property="rangeGridlineVisible" value="true"/>
                    <jsp:setProperty name="timeSeriesPaint" property="labelVertical" value="true"/>
                </jsp:useBean>
		<jsp:useBean id="actStatistics" class="org.opens.tgol.charts.data.ActStatistics">
                    <jsp:setProperty name="actStatistics" property="actSet" value="${actSet}"/>
                    <jsp:setProperty name="actStatistics" property="locale" value="${locale}"/>
                </jsp:useBean>
                <cewolf:chart
                        id="timeChart"
                        title="${title}"
                        type="area"
                        yaxisinteger="true"
                        backgroundcolor="#FFFFFF"
                        plotbackgroundcolor="#FFFFFF"
                        bordervisible="false"
                        plotbordervisible="false"
                        showlegend="true">
                    <cewolf:data>
                        <cewolf:producer id="actStatistics"/>
                    </cewolf:data>
                    <cewolf:chartpostprocessor id="timeSeriesPaint">
			<cewolf:param name="0" value='<%= new String("#A0C8FF")%>'/>
                        <cewolf:param name="1" value='<%= new String("#A0FFA3")%>'/>
                    </cewolf:chartpostprocessor>
                </cewolf:chart>
                <cewolf:imgurl
                            chartid="timeChart"
                            var="timeChartUrl"
                            renderer="cewolf"
                            width="${width}"
                            height="${height}">
                </cewolf:imgurl>
                <object id="site-audit-history" class="chart-object" type="image/png" data="<c:url value="/${timeChartUrl}"/>" usemap="#timeChart" title="<fmt:message key="graph.siteAuditHistoryTitle"/>">
                    <table>
                    <caption>"${title}"</caption>
                        <tr>
                            <td></td>
                            <c:forEach var="mark" items="${actStatistics.seriesI18NLabel}" varStatus="markIndex">
                            <th id="mark${markIndex.index}">${mark}</th>
                            </c:forEach>
                        </tr>
                        <c:forEach var="date" items="${actStatistics.categoriesI18NLabel}" varStatus="dateIndex">
                        <tr>
                            <th id="date${dateIndex.index}">${date}</th>
                            <c:forEach var="mark" items="${actStatistics.seriesI18NLabel}" varStatus="markIndex">
                            <td headers="mark${markIndex.index} date${dateIndex.index}">${actStatistics.values[markIndex.index][dateIndex.index]}%</td>
                            </c:forEach>
                        </tr>
                       </c:forEach>
                    </table>
                </object>
                <cewolf:map divId="timeChartDiv" chartId="timeChart" tooltipgeneratorid="actStatistics" width="${width}" height="${height}"/>