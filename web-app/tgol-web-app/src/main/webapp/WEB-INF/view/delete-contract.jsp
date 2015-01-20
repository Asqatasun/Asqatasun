<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://tagutils" prefix="tg" %>
<!DOCTYPE html>

<c:set var="warningImgUrl">
    <c:url value="/Images/warning.jpg"/>  
</c:set>
<c:set var="creativeCommonLogoUrl">
    <c:url value="/Images/creative_common_logo.png"/>
</c:set>

<html lang="${tg:lang(pageContext)}">
    <c:set var="pageTitle" scope="page">
        <fmt:message key="delete-contract.pageTitle">
            <fmt:param>
                ${contractNameToDelete}
            </fmt:param>
        </fmt:message>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="tgm-delete-contracts">
        <c:set var="adminActive" value="true"/>
        <%@include file="template/header-utils.jsp" %>
        <div class="container">
            <c:set var="pageName" scope="page">
                <fmt:message key="delete-contract.h1">
                    <fmt:param>
                    ${contractNameToDelete}
                    </fmt:param>
                </fmt:message>
            </c:set>
            <ul class="breadcrumb">
                <li><a href="<c:url value="/home.html"/>"><fmt:message key="home.h1"/></a> <span class="divider"></span></li>
                <li><a href="<c:url value="/admin.html"/>"><fmt:message key="admin.h1"/></a> <span class="divider"></span></li>
                <li>
                    <a href="<c:url value="/admin/manage-contracts.html?user=${user}"/>">
                        <fmt:message key="manage-contracts.h1">
                            <fmt:param>${userName}</fmt:param>
                        </fmt:message>
                    </a>
                    <span class="divider"></span>
                </li>
                <li class="active">${pageName}</li>
            </ul>
            <div class="row">
                <div class="span16">
                    <h1>${pageName}</h1>
                </div>
            </div>
            <div class="row">
                <div class="span16 alert-message block-message warning">
                    <fmt:message key="delete-contract.deletion-msg">
                        <fmt:param>${contractNameToDelete}</fmt:param>
                    </fmt:message>
                    <div class="alert-actions">
                        <form method="post">
                            <input type="submit" value="<fmt:message key="delete-contract.confirm-deletion"/>"/>
                        </form>
                    </div><!-- class="alert-actions"-->
                </div>
            </div><!-- class="row"-->
            <div class="row">
                <div class="span16 main-logo">
                    <img src="${warningImgUrl}" alt=""/>
                </div><!-- class="span16 main-logo" -->
            </div><!-- class="row" -->
            <div class="row">
                <div class="span4 offset10">
                    <a title="Creative Commons Attribution 3.0 License" href="http://creativecommons.org/licenses/by/3.0/">
                        <img src="${creativeCommonLogoUrl}" alt="License"/>
                    </a>
                    <a title="Flickr: Galerie de Patrick van IJzendoorn" href="http://www.flickr.com/photos/sleepishly/">Jessica Diamond</a>
                </div><!-- class="span4 offset9" -->
            </div><!-- class="row" -->
        </div><!-- class="container"-->                    
    <%@include file="template/footer.jsp" %>
    </body>
</html>
</compress:html>