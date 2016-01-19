<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="height" value="170"/>
<c:set var="width" value="270"/>

<div class="${proportion} ${offset}">
    <div class="thumbnail" >
        <c:choose>
            <c:when test="${url != '' && 
                            scope != 'GROUPOFFILES' && 
                            scope != 'FILE' && 
                            scope != 'SCENARIO' && 
                            not empty configProperties['snapshotServiceUrl']}">
        <img src="${configProperties['snapshotServiceUrl']}&amp;${configProperties['snapshotServiceWidthKey']}=${width}&amp;${configProperties['snapshotServiceHeightKey']}=${height}&amp;url=${url}" 
             alt="${url}" />
        <br/>
            </c:when>
            <c:otherwise>
                <c:set var="tgLogoUrl">
                    <c:url value="/Images/Logo/Logo-Asqatasun-G-w${width}-h${height}-75dpi-bgWhite.png"/>
                </c:set>
        <img src="${tgLogoUrl}" 
             alt="" 
             class="tg-logo"/><br/>
            </c:otherwise>
        </c:choose>
    </div>
</div>