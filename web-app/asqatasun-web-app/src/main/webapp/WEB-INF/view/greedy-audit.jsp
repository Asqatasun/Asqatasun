<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://tagutils" prefix="tg" %>
<!DOCTYPE html>

<c:set var="greedyAuditImgUrl">
    <c:url value="/Images/work-in-progress.jpg"/>  
</c:set>
<c:set var="creativeCommonLogoUrl">
    <c:url value="/Images/creative_common_logo.png"/>
</c:set>

<html lang="${tg:lang(pageContext)}">
    <c:set var="pageTitle" scope="page">
        <fmt:message key="greedyAudit.pageTitle">
            <fmt:param>
                ${testedUrl}
            </fmt:param>
        </fmt:message>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="tgm-audit-in-progress">
        <%@include file="template/header-utils.jsp" %>
        <div class="container">
            <c:set var="pageName" scope="page">
                <fmt:message key="greedyAudit.h1"/>
            </c:set>
            <ul class="breadcrumb">
                <li><a href="<c:url value="/home.html"/>"><fmt:message key="home.h1"/></a> <span class="divider"></span></li>
                <li><a href="<c:url value="/home/contract.html?cr=${param.cr}"/>">${contractName}</a> <span class="divider"></span></li>
                <c:choose>
                    <c:when test="${isPageAudit == true}">
                        <li><a href="<c:url value="/home/contract/audit-page-set-up.html?cr=${param.cr}"/>"><fmt:message key="auditSetUpPage.h1"/></a> <span class="divider"></span></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="<c:url value="/home/contract/audit-upload-set-up.html?cr=${param.cr}"/>"><fmt:message key="auditSetUpUpload.h1"/></a> <span class="divider"></span></li>
                    </c:otherwise>
                </c:choose>
                <li class="active">${pageName}</li>
            </ul>
            <div class="row">
                <div class="span16">
                    <h1>
                        <fmt:message key="greedyAudit.h1"/>
                    </h1>
                </div><!-- class="span16" -->
            </div><!-- class="row" -->
            <div class="row">
                <div class="span16">
                    <div class="alert-message block-message success">
                        <p>
                            <fmt:message key="greedyAudit.message">
                                <fmt:param>${testedUrl}</fmt:param>
                            </fmt:message>
                        </p>
                        <c:if test="${isUserNotified == true}">
                            <p>
                            <fmt:message key="greedyAudit.sendEmail"/>
                            </p>
                        </c:if>
                            
                        <div class="alert-actions">
                            <c:set var="currentUserName" scope="page">
                                <sec:authentication property="principal.displayedUserName" />
                            </c:set>
                            <c:choose>
                                <c:when test="${currentUserName != 'guest'}">
                            <a href="<c:url value="/home/contract.html?cr=${param.cr}"/>" class="btn small">
                                <fmt:message key="greedyAudit.backOnContract">
                                    <fmt:param>${contractName}</fmt:param>
                                </fmt:message>
                            </a>
                                </c:when>
                                <c:otherwise>
                            <a href="<c:url value="/home/contract.html?cr=${param.cr}"/>" class="btn small"><fmt:message key="greedyAudit.backOnContractGuest"/></a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div><!-- alert-message block-message success -->
                </div><!-- class="span15 offset1" -->
            </div><!-- class="row" -->
            <div class="row">
                <div class="span16 main-logo">
                    <img src="${greedyAuditImgUrl}" alt=""/>
                </div><!-- class="span16 main-logo" -->
            </div><!-- class="row" -->
            <div class="row">
                <div class="span4 offset11">
                    <a title="Creative Commons Attribution 3.0 License" href="http://creativecommons.org/licenses/by/3.0/">
                        <img src="${creativeCommonLogoUrl}" alt="License"/>
                    </a>
                    <a title="Flickr: Galerie de blumpy" href="http://www.flickr.com/photos/blumpy/">blumpy</a>
<!--                    <a title="Creative Commons Attribution 3.0 License" href="http://creativecommons.org/licenses/by/3.0/">
                        <img src="${creativeCommonLogoUrl}" alt="License"/>
                    </a>
                    <a title="Flickr: Galerie de Patrick van IJzendoorn" href="http://www.flickr.com/photos/33630119@N07/">Patrick van IJzendoorn </a>-->
                </div><!-- class="span4 offset9" -->
            </div><!-- class="row" -->
        </div><!-- class="container"-->
        <%@include file="template/footer.jsp" %>
    </body>
</html>
</compress:html>