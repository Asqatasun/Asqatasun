<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="function" uri="http://tagutils"%>
<%@ taglib uri="http://tagutils" prefix="tg" %>
<!DOCTYPE html>
<c:if test="${userName == null && not empty userList}">
    <c:set var="multipleUser" value="true"/>
</c:if>
<html lang="${tg:lang(pageContext)}">
    <c:choose>
        <c:when test="${multipleUser == 'true'}">
            <c:set var="pageTitle" scope="page">
                <fmt:message key="add-contract.pageTitleMultiple"/>
            </c:set>
            <c:set var="pageName" scope="page">
                <fmt:message key="add-contract.h1Multiple"/>
            </c:set>
        </c:when>
        <c:otherwise>
            <c:set var="pageTitle" scope="page">
                <fmt:message key="add-contract.pageTitle">
                    <fmt:param>${userName}</fmt:param>
                </fmt:message>
            </c:set>
            <c:set var="pageName" scope="page">
                <fmt:message key="add-contract.h1">
                    <fmt:param>${userName}</fmt:param>
                </fmt:message>
            </c:set>
        </c:otherwise>
    </c:choose>
    <%@include file="template/head.jsp" %>
    <body id="tgm-add-contract">
        <c:set var="adminActive" value="true"/>
        <%@include file="template/header-utils.jsp" %>
        <div class="container">
            <ul class="breadcrumb">
                <li><a href="<c:url value="/home.html"/>"><fmt:message key="home.h1"/></a> <span class="divider"></span></li>
                <li><a href="<c:url value="/admin.html"/>"><fmt:message key="admin.h1"/></a> <span class="divider"></span></li>
                <c:if test="${multipleUser != 'true'}">
                <li>
                    <a href="<c:url value="/admin/manage-contracts.html?user=${user}"/>">
                        <fmt:message key="manage-contracts.h1">
                            <fmt:param>${userName}</fmt:param>
                        </fmt:message>
                    </a>
                    <span class="divider"></span>
                </li>
                </c:if>
                <li class="active">${pageName}</li>
            </ul>
            <div class="row">
                <div class="span16">
                    <h1>${pageName}</h1>
                </div>
            </div>
            <div class="row">
                <c:set var="validateButtonName" scope="request">
                    <fmt:message key="add-contract.addContractButton"/> &raquo;
                </c:set>
                <%@include file="template/add-edit-contract.jsp" %>
            </div><!-- class="row"-->
        </div><!-- class="container"-->                    
    <%@include file="template/footer.jsp" %>
    </body>
</html>
</compress:html>