<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

                <h2 id="pageList"><fmt:message key="pageList.pageList"/></h2>
                <table summary="Audit page list" id="pageListTable">
                    <caption>"Tested pages"</caption>
                    <tr>
                        <th id="url"><fmt:message key="pageList.urlHeader"/></th>
                        <th id="mark"><fmt:message key="pageList.markHeader"/></th>
                        <th id="pieRepartition"><fmt:message key="resultPage.pieRepartitionHeader"/></th>
                        <th id="barChartRepartition"><fmt:message key="resultPage.barChartRepartitionHeader"/></th>
                        <th id="detailedAuditUrl"><fmt:message key="resultPage.detailedAuditUrlHeader"/></th>
                    </tr>
                    <c:forEach var="page" items="${pageList}" varStatus="pPageList">
                    <c:choose>
                        <c:when test="${pPageList.index % 2 == 1}">
                    <tr class="row-odd">
                        </c:when>
                        <c:otherwise>
                    <tr class="row-even">
                        </c:otherwise>
                    </c:choose>
                        <td headers="url"><a href="${page.url}">${page.url}</a></td>
                        <td headers="mark"><strong>${page.mark}%</strong></td>
                        <td headers="pieRepartition">
                        <c:set var="counter" scope="request" value="${page.resultCounter}"/>
                        <c:set var="width" scope="request" value="45"/>
                        <c:set var="height" scope="request" value="60"/>
                        <c:set var="index" scope="request" value="${pPageList.index}"/>
                        <c:set var="hasTableAlternative" scope="request" value="true"/>
                        <c:import url="graph/pie-representation.jsp"/>
                        </td>
                        <td headers="barChartRepartition">
                        <c:set var="counterByThemeMap" scope="request" value="${page.counterByThemeMap}"/>
                        <c:set var="width" scope="request" value="300"/>
                        <c:set var="height" scope="request" value="80"/>
                        <c:set var="showLegend" scope="request" value="false"/>
                        <c:set var="showAxisLabel" scope="request" value="false"/>
                        <c:set var="hasLink" scope="request" value="false"/>
                        <c:set var="xLabel" scope="request" value=""/>
                        <c:set var="yLabel" scope="request" value=""/>
                        <c:import url="graph/bar-chart-representation.jsp"/>
                        </td>
                        <td headers="detailedAuditUrl"><a href="${page.pageResultUrl}" title="<fmt:message key="resultPage.pageDetailedResult"/> <fmt:message key="resultPage.for"/> ${page.url}"><fmt:message key="resultPage.pageDetailedResult"/></a>
                        </td>
                    </tr>
                    </c:forEach>
                </table>