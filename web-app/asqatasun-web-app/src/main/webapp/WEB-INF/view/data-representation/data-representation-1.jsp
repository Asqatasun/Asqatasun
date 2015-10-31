<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="row">
    <div class="span14">
        <p class="process-remarks">
            <fmt:message  key="${remarkInfosItem.messageCode}">
            <c:if test='${remarkInfosItem.remarkTarget != null}'>
                <c:set var="remarkTarget">
                    ${fn:escapeXml(fn:replace(remarkInfosItem.remarkTarget,"&", "&amp;"))}
                </c:set>
                <fmt:param value="${remarkTarget}"/>
            </c:if>
            </fmt:message>
        </p>
        <c:if test='${not empty remarkInfosItem.evidenceElementList}'>
            <ul class="process-remarks">
            <c:forEach var="childRemarkItem" 
                           items="${remarkInfosItem.evidenceElementList}">
                <li>
                <c:forEach var="evidenceElement" items="${childRemarkItem}">
                    <c:choose>
                        <c:when test="${evidenceElement.key == 'Line-Number'}">
                            <c:set var="lineValueTitle">
                                <fmt:message key="${evidenceElement.key}"/> ${evidenceElement.value}
                            </c:set>
                    <a href="<c:url value="/home/contract/source-code-page.html?wr=${param.wr}#line${evidenceElement.value}"/>" 
                       target="_blank" 
                       title="${lineValueTitle} <fmt:message key="evidenceElement.newWindow"/>">
                        ${lineValueTitle}
                    </a>
                        </c:when>
                        <c:when test="${evidenceElement.key == 'Snippet'}">
                            <c:set var="snippetContent">
                                ${evidenceElement.value}
                            </c:set>
                        </c:when>
                        <c:when test="${evidenceElement.key == 'Url'}">
                    <a href="${evidenceElement.value}">${evidenceElement.value}</a>
                        </c:when>
                        <c:otherwise>
                    <fmt:message key="${evidenceElement.key}"/> ${fn:escapeXml(fn:replace(evidenceElement.value, "&", "&amp;"))}
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${snippetContent != null}">
                    <pre>${snippetContent}</pre>
                </c:if>
                </li>
            </c:forEach>
            </ul>
        </c:if>
    </div>
</div>