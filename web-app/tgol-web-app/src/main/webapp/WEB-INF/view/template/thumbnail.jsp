<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

                <c:choose>
                    <c:when test="${size == 'T'}">
                        <c:set var="height" value="70"/>
                        <c:set var="width" value="90"/>
                    </c:when>
                    <c:when test="${size == 'S'}">
                        <c:set var="height" value="150"/>
                        <c:set var="width" value="200"/>
                    </c:when>
                </c:choose>
                <div class="${proportion} ${offset}">
                    <div class="thumbnail" >
                        <c:choose>
                            <c:when test="${url != '' && scope != 'GROUPOFFILES' && scope != 'FILE' && scope != 'SCENARIO' && 
                                            not empty configProperties['snapshotServiceUrl'] && 
                                            not empty configProperties['snapshotServiceUserId']}">
                        <img src="${configProperties['snapshotServiceUrl']}?userId=${configProperties['snapshotServiceUserId']}&q=90&w=${width}&h=${height}&sdx=1024&url=${url}" alt="" /><br/>
                            </c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${not empty configProperties['cdnUrl']}">
                                        <c:set var="tgLogoUrl" value="$${pageContext.request.scheme}://${configProperties['cdnUrl']}/Images/Logo-Tanaguru-G-w${width}-h${height}-75dpi-bgTransp.png"/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:set var="tgLogoUrl">
                                            <c:url value="/Images/Logo-Tanaguru-G-w${width}-h${height}-75dpi-bgTransp.png"/>
                                        </c:set>
                                    </c:otherwise>
                                </c:choose>
                        <img src="${tgLogoUrl}" alt="" /><br/>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>