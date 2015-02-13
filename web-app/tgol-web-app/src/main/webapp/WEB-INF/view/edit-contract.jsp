<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://tagutils" prefix="tg" %>
<!DOCTYPE html>
<html lang="${tg:lang(pageContext)}">
    <c:set var="pageTitle" scope="page">
        <fmt:message key="edit-contract.pageTitle">
            <fmt:param>${contractName}</fmt:param>
        </fmt:message>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="tgm-home">
        <c:set var="adminActive" value="true"/>
        <%@include file="template/header-utils.jsp" %>
        <div class="container">
            <c:set var="pageName" scope="page">
                <fmt:message key="edit-contract.h1">
                    <fmt:param>${contractName}</fmt:param>
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
                <c:set var="validateButtonName" scope="request">
                    <fmt:message key="edit-contract.saveChanges"/> &raquo;
                </c:set>
                <%@include file="template/add-edit-contract.jsp" %>
            </div><!-- class="row"-->
        </div><!-- class="container"-->                    
    <%@include file="template/footer.jsp" %>
    </body>
</html>
</compress:html>