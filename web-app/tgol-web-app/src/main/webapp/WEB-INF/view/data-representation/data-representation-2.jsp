<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

                        <c:set var="msgCode" scope="request" value="${remarkInfosItem.messageCode}"/>
                        <c:choose>
                            <c:when test="${remarkInfosItem.remarkResult == 'nmi'}">
                        <p id="${testCode}nmi${nmiCounter}" class="process-remarks ${remarkInfosItem.remarkResult}">
                            </c:when>
                            <c:when test="${remarkInfosItem.remarkResult == 'failed'}">
                        <p id="${testCode}failed${failedCounter}" class="process-remarks ${remarkInfosItem.remarkResult}">
                            </c:when>
                            <c:otherwise>
                        <p class="process-remarks ${remarkInfosItem.remarkResult}">
                            </c:otherwise>
                        </c:choose>
                            <strong>
                                <fmt:message  key="${msgCode}">
                                <c:if test='${remarkInfosItem.remarkTarget != null}'>
                                    <c:set var="remarkTarget">
                                        ${fn:escapeXml(fn:replace(remarkInfosItem.remarkTarget,"&", "&amp;"))}
                                    </c:set>
                                    <fmt:param value="${remarkTarget}"></fmt:param>
                                </c:if>
                                </fmt:message>
                            </strong>
                        </p>
                        <c:if test='${not empty remarkInfosItem.evidenceElementList}'>
                        <table summary="<fmt:message  key="${msgCode}"/>">
                            <caption><fmt:message  key="${msgCode}"/></caption>
                            <!-- First parse of evidence element list to get the table headers (evidencement element key)-->
                            
                                <tr>
                                    <c:forEach var="evidenceElement1" items="${remarkInfosItem.evidenceElementList[0]}">
                                    <th class="r${testCode}-col-${evidenceElement1.key}" scope="col"><fmt:message  key="${evidenceElement1.key}"/></th>
                                    </c:forEach>
                                </tr>
                            
                            <!-- Second parse of evidence element list to get value corresponding to each header of each element list-->
                            <c:forEach var="childRemarkItem2" items="${remarkInfosItem.evidenceElementList}">

                                <tr>
                                <c:forEach var="evidenceElement2" items="${childRemarkItem2}">
                                    <c:choose>
                                        <c:when test="${evidenceElement2.key == 'Line-Number'}">
                                    <td class="r${testCode}-col-${evidenceElement2.key}">
                                        <a href="#line${evidenceElement2.value}">${evidenceElement2.value}</a>
                                    </td>
                                        </c:when>
                                        <c:otherwise>
                                    <td class="r${testCode}-col-${evidenceElement2.key}">${fn:escapeXml(fn:replace(evidenceElement2.value, "&", "&amp;"))}</td>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                                </tr>
                            </c:forEach>
                        </table>
                        </c:if>
