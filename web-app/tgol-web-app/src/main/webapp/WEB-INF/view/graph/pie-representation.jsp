<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri='/WEB-INF/cewolf.tld' prefix='cewolf' %>

                <jsp:useBean id="pieSeriesPaint" class="de.laures.cewolf.cpp.SeriesPaintProcessor" />
                <jsp:useBean id="pieEnhancer" class="de.laures.cewolf.cpp.PieEnhancer" />
                <jsp:useBean id="auditStatistics" class="org.opens.tgol.charts.data.TestStatistics">
                    <jsp:setProperty name="auditStatistics" property="passedCount" value="${counter.passedCount}"/>
                    <jsp:setProperty name="auditStatistics" property="failedCount" value="${counter.failedCount}"/>
                    <jsp:setProperty name="auditStatistics" property="nmiCount" value="${counter.nmiCount}"/>
                    <jsp:setProperty name="auditStatistics" property="naCount" value="${counter.naCount}"/>
                </jsp:useBean>
                <cewolf:chart
                        id="pie${index}"
                        title=""
                        type="pie"
                        backgroundcolor="#FFFFFF"
                        plotbackgroundcolor="#FFFFFF"
                        bordervisible="false"
                        plotbordervisible="false"
                        showlegend="false">
                    <cewolf:data>
                        <cewolf:producer id="auditStatistics"/>
                    </cewolf:data>
                    <cewolf:chartpostprocessor id="pieSeriesPaint">
                        <cewolf:param name="0" value='<%= new String("#EDFF9F")%>'/>
                        <cewolf:param name="1" value='<%= new String("#FF575E")%>'/>
                        <cewolf:param name="2" value='<%= new String("#8DD5E7")%>'/>
                        <cewolf:param name="3" value='<%= new String("#CCCCCC")%>'/>
                    </cewolf:chartpostprocessor>
                    <cewolf:chartpostprocessor id="pieEnhancer">
                        <cewolf:param name="startAngle" value="90"/>
                        <cewolf:param name="simpleLabels" value="false"/>
                        <cewolf:param name="showSectionLabels" value="false"/>
                        <cewolf:param name="explode_0.10" value="Failed"/>
                        <cewolf:param name="explode_0.11" value="Passed"/>
			<cewolf:param name="explode_0.12" value="NMI"/>
			<cewolf:param name="explode_0.13" value="NA"/>
                    </cewolf:chartpostprocessor>
                </cewolf:chart>
                <cewolf:imgurl
                            chartid="pie${index}"
                            var="pieUrl"
                            renderer="cewolf"
                            width="${width}"
                            height="${height}">
                </cewolf:imgurl>
                <c:choose>
                    <c:when test="${hasTableAlternative == 'true'}">
                <object id="nb-results-${index}" class="chart-object" type="image/png" data="<c:url value="/${pieUrl}"/>" usemap="#pie${index}">
                    <table summary="<fmt:message key="graph.resultRepartitionSummaryAndCaption"/>">
                        <caption><fmt:message key="graph.resultRepartitionSummaryAndCaption"/></caption>
                        <tr>
                            <th class="col01 passed" scope="row">Passed</th>
                            <td class="col02">${counter.passedCount}</td>
                        </tr>
                        <tr>
                            <th class="col01 failed" scope="row">Failed</th>
                            <td class="col02">${counter.failedCount}</td>
                        </tr>
                        <tr>
                            <th class="col01 nmi" scope="row">
                                <abbr title="Need More Information">NMI</abbr>
                            </th>
                            <td class="col02">${counter.nmiCount}</td>
                        </tr>
                        <tr>
                            <th class="col01 na" scope="row">
                                <abbr title="Not Applicable">NA</abbr>
                            </th>
                            <td class="col02">${counter.naCount}</td>
                        </tr>
                    </table>
                </object>
                    </c:when>
                    <c:otherwise>
                <img src="<c:url value="/${pieUrl}"/>" alt="<fmt:message key="graph.resultRepartitionSummaryAndCaption"/>" usemap="#pie${index}"/>
                    </c:otherwise>
                </c:choose>

                <cewolf:map
                    divId="pieDiv${index}"
                    chartId="pie${index}"
                    tooltipgeneratorid="auditStatistics"
                    width="${width}"
                    height="${height}"/>