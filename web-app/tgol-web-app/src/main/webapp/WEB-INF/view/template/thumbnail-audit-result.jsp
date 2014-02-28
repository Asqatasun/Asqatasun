<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

                <c:set var="height" value="170"/>
                <c:set var="width" value="270"/>
                <c:set var="tgLogoUrl">
                    <c:url value="/Images/Logo-Tanaguru-G-w${width}-h${height}-75dpi-bgTransp.png"/>
                </c:set>
                <div class="${proportion} ${offset}">
                    <div class="thumbnail" >
                        <c:choose>
                            <c:when test="${url != '' && scope != 'FILE' && scope != 'SCENARIO' && not empty configProperties['snapshotServiceUrl']}">
                            <div id="snapshot-service-url" style="display:none;">
                                ${configProperties['snapshotServiceUrl']}&amp;${configProperties['snapshotServiceWidthKey']}=${width}&amp;${configProperties['snapshotServiceHeightKey']}=${height}&amp;url=${fn:replace(fn:replace(url,"&amp;","%26"),"&","%26")}
                            </div>
                            <div id="fail-snapshot-url" style="display:none;">
                                ${tgLogoUrl}
                            </div>
                        <img id="snapshot" src="<c:url value="/Images/processing.gif"/>" alt="${url}" style="height: ${height};width: ${width};"/><br/>
                            </c:when>
                            <c:otherwise>
                        <img src="${tgLogoUrl}" alt="" class="tg-logo"/><br/>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>