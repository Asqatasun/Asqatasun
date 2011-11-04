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
                        <td headers="url"><a href="${page.pageResultUrl}" title="<fmt:message key="pageList.pageDetailedResult"/> <fmt:message key="pageList.for"/> ${page.url}">${page.url}</a></td>
                        <td headers="mark">
                        <c:set var="mark" scope="page" value="${page.mark}"/>
                        <c:set var="scoreClass" scope="page" value=""/>
                        <%@include file="score.jsp" %>
                        </td>
                    </tr>
                    </c:forEach>
                </table>