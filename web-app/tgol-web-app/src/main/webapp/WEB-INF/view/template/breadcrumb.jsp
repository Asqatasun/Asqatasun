<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


            <c:if test="${breadCrumb != null}">
            <div class="yui3-g">
                <div class="yui3-u" id="breadcrumb">
                    <p>
                    <c:forEach var="element" items="${breadCrumb}">
                        <a href="<c:url value="${element.key}"/>">
                        <c:choose>
                            <c:when test="${fn:containsIgnoreCase(element.value,'.h1')}">
                            <c:set var="str" value="${fn:split(element.value,';')}"/>
                            <fmt:message key="${str[0]}">
                                <c:if test="${str[1] != null}">
                                    <fmt:param>${str[1]}</fmt:param>
                                </c:if>
                            </fmt:message>
                            </c:when>
                            <c:otherwise>
                                ${element.value}
                            </c:otherwise>
                        </c:choose>
                        </a> &gt;
                    </c:forEach>
                        ${pageName}
                    </p>
                </div>
            </div>
            </c:if>
