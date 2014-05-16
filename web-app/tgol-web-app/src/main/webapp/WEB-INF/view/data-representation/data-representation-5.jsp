<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://tagutils" prefix="tg" %>

<div class="row">
    <div class="span15">
        <c:set var="msgCode" scope="request" value="${remarkInfosItem.messageCode}"/>
        <c:choose>
            <c:when test="${remarkInfosItem.remarkResult == 'nmi'}">
                <p id="${testCode}nmi${nmiCounter}" class="process-remarks ${remarkInfosItem.remarkResult}-pr">
                </c:when>
                <c:when test="${remarkInfosItem.remarkResult == 'failed'}">
                <p id="${testCode}failed${failedCounter}" class="process-remarks ${remarkInfosItem.remarkResult}-pr">
                </c:when>
                <c:otherwise>
                <p class="process-remarks ${remarkInfosItem.remarkResult}-pr">
                </c:otherwise>
            </c:choose>
            <fmt:message  key="${msgCode}">
                <c:if test='${remarkInfosItem.remarkTarget != null}'>
                    <c:set var="remarkTarget">
                        ${fn:escapeXml(fn:replace(remarkInfosItem.remarkTarget,"&", "&amp;"))}
                    </c:set>
                    <fmt:param value="${remarkTarget}"></fmt:param>
                </c:if>
            </fmt:message>
        </p>
        <c:if test='${not empty remarkInfosItem.evidenceElementList}'>
            <c:set value="false" var="bgColor" scope="page"/>
            <c:set value="false" var="color" scope="page"/>
            <table class="evidence-elements-table">
                <caption><fmt:message  key="${msgCode}"/></caption>
                <!-- First parse of evidence element list to get the table headers (evidencement element key)-->
                <thead>
                    <tr>
                        <c:forEach var="evidenceElement1" items="${remarkInfosItem.evidenceElementList[0]}">
                            <th class="r${testCode}-col-${evidenceElement1.key}" scope="col"><fmt:message  key="${evidenceElement1.key}"/></th>
                                <c:if test="${evidenceElement1.key == 'Foreground-Color'}">
                                    <c:set value="true" var="color" scope="page"/>
                                </c:if>
                                <c:if test="${evidenceElement1.key == 'Background-Color'}">
                                    <c:set value="true" var="bgColor" scope="page"/>
                                </c:if>
                                <c:if test="${bgColor eq true && color eq true}">
                                <th class="r${testCode}-col-sample" scope="col"><fmt:message key="resultPageEvidenceElement.sampleKey"/></th>
                                    <c:set value="false" var="bgColor" scope="page"/>
                                    <c:set value="false" var="color" scope="page"/>
                                </c:if>
                            </c:forEach>
                            <c:if test="${not empty configProperties['contrastfinderServiceUrl']}">
                            <th class="r${testCode}-col-cf" scope="col"><fmt:message key="resultPageEvidenceElement.cfKey"/></th>
                            </c:if>
                    </tr>
                </thead>
                <!-- Second parse of evidence element list to get value corresponding to each header of each element list-->
                <tbody>
                    <c:forEach var="childRemarkItem2" items="${remarkInfosItem.evidenceElementList}">
                        <c:set value="" var="bgColor" scope="page"/>
                        <c:set value="" var="color" scope="page"/>
                        <c:set value="false" var="sampleDisplayed" scope="page"/>
                        <tr>
                            <c:forEach var="evidenceElement2" items="${childRemarkItem2}">
                                <c:if test="${sampleDisplayed eq false && bgColor != '' && color != ''}">
                                    <c:choose>
                                        <c:when test="${fn:startsWith(bgColor, 'background')==true}">
                                            <td class="sample" style="color:${color};${bgColor}"><fmt:message key="resultPageEvidenceElement.sampleContent"/></td>                                                
                                        </c:when>
                                        <c:otherwise>
                                            <td class="sample" style="color:${color};background-color: ${bgColor};"><fmt:message key="resultPageEvidenceElement.sampleContent"/></td>                                                
                                        </c:otherwise>
                                    </c:choose>
                                    <c:set value="true" var="sampleDisplayed" scope="page"/>
                                </c:if>
                                <c:choose>
                                    <c:when test="${evidenceElement2.key == 'Line-Number'}">
                                        <td class="${evidenceElement2.key}">
                                            <c:set var="lineValueTitle">
                                                <fmt:message key="${evidenceElement2.key}"/> ${evidenceElement2.value}
                                            </c:set>
                                            <a href="<c:url value="/home/contract/source-code-page.html?wr=${param.wr}#line${evidenceElement2.value}"/>" title="${lineValueTitle} <fmt:message key="evidenceElement.newWindow"/>">
                                                ${lineValueTitle}
                                            </a>
                                        </td>
                                    </c:when>
                                    <c:when test="${evidenceElement2.key == 'Snippet'}">
                                        <td class="${evidenceElement2.key}">
                                            <c:set var="snippetCode" scope="page">
                                                ${fn:trim(evidenceElement2.value)}
                                            </c:set>
                                            <code class="prettyprint">
                                                ${snippetCode}
                                            </code>
                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <c:set var="evidence" scope="page">
                                            ${fn:escapeXml(fn:replace(evidenceElement2.value, "&", "&amp;"))}
                                        </c:set>
                                        <c:choose>
                                            <c:when test="${evidenceElement2.key == 'Foreground-Color' || evidenceElement2.key == 'Background-Color'}">
                                                <c:set var="evidence" scope="page">
                                                    ${fn:replace(evidenceElement2.value, ";", ",")}
                                                </c:set>
                                                <c:set var="hexaColor">
                                                    ${tg:convertRgbToHex(evidence)}
                                                </c:set>
                                                <c:choose>
                                                    <c:when test="${evidenceElement2.key == 'Foreground-Color'}">
                                                        <c:set var="color" scope="page">
                                                            ${evidence}
                                                        </c:set>
                                                        <c:set var="fgColorHexa" scope="page">
                                                            ${hexaColor}
                                                        </c:set>
                                                    </c:when>
                                                    <c:when test="${evidenceElement2.key == 'Background-Color'}">
                                                        <c:set var="bgColor" scope="page">
                                                            ${evidence}
                                                        </c:set>
                                                        <c:set var="bgColorHexa" scope="page">
                                                            ${hexaColor}
                                                        </c:set>
                                                    </c:when>
                                                </c:choose>
                                            </c:when>
                                            <c:otherwise>
                                                <c:set var="hexaColor" value="" />
                                            </c:otherwise>
                                        </c:choose>
                                        <c:set var="emptyClass" scope="page" value=""/>
                                        <c:if test="${fn:length(evidence) == 0}">
                                            <c:set var="evidence" scope="page">
                                                <fmt:message  key="empty"/>
                                            </c:set>
                                            <c:set var="emptyClass" scope="page" value="empty"/>
                                        </c:if>
                                        <td class="${evidenceElement2.key} ${emptyClass}">
                                            ${evidence}
                                            <c:if test="${not empty hexaColor}">
                                                ${hexaColor}
                                            </c:if>
                                        </td>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>

                            <c:if test="${not empty configProperties['contrastfinderServiceUrl']}">
                                <c:set var="cfLogoDescription">
                                    <fmt:message key="resultPageEvidenceElement.cfLogoDescription">
                                        <fmt:param value="${fgColorHexa}"></fmt:param>
                                        <fmt:param value="${bgColorHexa}"></fmt:param>
                                    </fmt:message>
                                </c:set>
                                <td class="${evidenceElement2.key}">
                                    <a title="${cfLogoDescription}" target="_blank" href="${configProperties['contrastfinderServiceUrl']}/result.html?foreground=${fn:replace(fgColorHexa, "#", "%23")}&background=${fn:replace(bgColorHexa, "#", "%23")}&isBackgroundTested=false&ratio=${testResult.colorTestContrastRatio}&algo=HSV"><img alt="${cfLogoDescription}" style="margin-left: 15px;" src="<c:url value="/Images/contrast-finder.png"/>"/></a>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
</div>