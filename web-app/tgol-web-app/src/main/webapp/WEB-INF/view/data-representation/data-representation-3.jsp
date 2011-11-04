<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

                        <c:set var="msgCode" scope="request" value="${remarkInfosItem.messageCode}"/>
                        <p class="process-remarks ${remarkInfosItem.remarkResult}">
                            <strong><fmt:message  key="${msgCode}"/></strong>
                        </p>
                        <c:if test='${not empty remarkInfosItem.evidenceElementList}'>
                            <table summary="<fmt:message  key="${msgCode}"/>">
                                <caption><fmt:message  key="${msgCode}"/></caption>
                                    <tr>
                                        <th class="r${testCode}-col-Link-Text" scope="col"><fmt:message  key="Link-Text"/></th>
                                        <th class="r${testCode}-col-Link-Title-Attribut" scope="col"><fmt:message  key="Link-Title-Attribut"/></th>
                                        <th class="r${testCode}-col-Line-Number" scope="col"><fmt:message  key="Line-Number"/></th>
                                        <th class="r${testCode}-col-Link-Href-Attribut" scope="col"><fmt:message  key="Link-Href-Attribut"/></th>
                                    </tr>
                                <c:forEach var="childRemarkItem2" items="${remarkInfosItem.evidenceElementList}">
                                    <tr>
                                        <c:forEach var="evidenceElement2" items="${childRemarkItem2}">
                                            <c:choose>
                                                <c:when test="${evidenceElement2.key == 'Line-Number'}">
                                                <td class="r${testShortLabel}-col-${evidenceElement2.key}">
                                                    <a href="#line${evidenceElement2.value}">${evidenceElement2.value}</a>
                                                </td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td class="r${testCode}-col-${evidenceElement2.key}">${evidenceElement2.value}</td>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:if>
