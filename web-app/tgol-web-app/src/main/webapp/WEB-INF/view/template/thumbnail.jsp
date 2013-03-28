<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!--                        <img src="<c:url value="/home/contract/wr-snapshot.html?wr=${param.wr}"/>" alt="${url}" /><br/> -->
                <c:set var="height" value="170"/>
                <c:set var="width" value="270"/>
                <div class="${proportion} ${offset}">
                    <div class="thumbnail" >
                        <c:choose>
                            <c:when test="${url != '' && scope != 'GROUPOFFILES' && scope != 'FILE' && scope != 'SCENARIO' && 
                                            not empty configProperties['snapshotServiceUrl']}">
<!--                        <img src="${pageContext.request.scheme}://${configProperties['snapshotServiceUrl']}?userId=${configProperties['snapshotServiceUserId']}&amp;q=90&amp;w=${width}&amp;sdx=1024&amp;url=${url}" alt="" /><br/>-->
                        <img src="${pageContext.request.scheme}://${configProperties['snapshotServiceUrl']}&amp;width=${width}&amp;height=${height}&amp;screen=1024&amp;url=${url}" alt="${url}" /><br/>
                            </c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${not empty configProperties['cdnUrl']}">
                                        <c:set var="tgLogoUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Images/Logo-Tanaguru-G-w${width}-h${height}-75dpi-bgTransp.png"/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:set var="tgLogoUrl">
                                            <c:url value="/Images/Logo-Tanaguru-G-w${width}-h${height}-75dpi-bgTransp.png"/>
                                        </c:set>
                                    </c:otherwise>
                                </c:choose>
                        <img src="${tgLogoUrl}" alt="" class="tg-logo"/><br/>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>