<!--
  Tanaguru - Automated webpage assessment
  Copyright (C) 2008-2015  Tanaguru.org

  This file is part of Tanaguru.
 
  Tanaguru is free software: you can redistribute it and/or modify
  it under the terms of the GNU Affero General Public License as
  published by the Free Software Foundation, either version 3 of the
  License, or (at your option) any later version.
 
  This program is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU Affero General Public License for more details.
 
  You should have received a copy of the GNU Affero General Public License
  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 
  Contact us by mail: tanaguru AT tanaguru DOT org
-->
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<table id="top5-failed-theme">
    <caption>
        <fmt:message key="graph.mostInvalidedThemesSummaryAndCaption"/>
    </caption>
    <tr>
        <td></td>
    <c:forEach var="counterByTheme" items="${counterByThemeMap}" varStatus="categoriesIndex">
        <c:if test="${categoriesIndex.index < 5}">
        <th id="top5Theme${categoriesIndex.index}" title="<fmt:message key="${counterByTheme.key.code}"/>">
            <fmt:message key="${counterByTheme.key.code}"/>
        </th>
        </c:if>
    </c:forEach>
    </tr>
    <tr>
        <th id="top5ResultFailed">
            <fmt:message key="failed"/>
        </th>
    <c:forEach var="counterByTheme" items="${counterByThemeMap}" varStatus="categoriesIndex">
        <c:if test="${categoriesIndex.index < 5}">
        <td headers="top5ResultFailed top5Theme${categoriesIndex.index}">
            ${counterByTheme.value.failedCount}
        </td>
        </c:if>
    </c:forEach>
    </tr>
</table>
<div id="top5-failed-theme-graph">

</div>
