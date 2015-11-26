<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"  %>
<%@ taglib uri="http://tagutils" prefix="tg" %>
<!DOCTYPE html>

<c:set var="addUser">
    <c:url value="/Images/user.png"/>  
</c:set>
<c:set var="editUser">
    <c:url value="/Images/edit.png"/>  
</c:set>
<c:set var="deleteUser">
    <c:url value="/Images/remove.png"/>  
</c:set>
<c:set var="editContract">
    <c:url value="/Images/folder_open.png"/>
</c:set>
<c:set var="deleteAudits">
    <c:url value="/Images/bin.png"/>
</c:set>
<c:set var="addContract">
    <c:url value="/Images/plus_2.png"/>
</c:set>

<c:set var="authenticatedUserId" scope="page">
    <sec:authentication property="principal.user.id" />
</c:set>

<html lang="${tg:lang(pageContext)}">
    <c:set var="pageTitle" scope="page">
        <fmt:message key="admin.pageTitle"/>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="tgm-admin">
        <c:set var="adminActive" value="true"/>
        <%@include file="template/header-utils.jsp" %>
        <div class="container">
            <c:set var="pageName" scope="page">
                <fmt:message key="admin.h1"/>
            </c:set>    
            <ul class="breadcrumb">
                <li><a href="<c:url value="/home.html"/>"><fmt:message key="home.h1"/></a> <span class="divider"></span></li>
                <li class="active">${pageName}</li>
            </ul>
            <div class="row">
                <div class="span16">
                    <h1>${pageName}</h1>
                </div>
            </div>
            <div class="row">
                <h2 class="span16 tg-table-title">
                    <fmt:message key="admin.userList"/>
                </h2>
            <c:choose>
                <c:when test="${not empty deletedUserName}">
                <div class="span16 alert-message block-message success">
                    <fmt:message key="admin.deletedUserPositiveMsg">
                        <fmt:param>${deletedUserName}</fmt:param>
                    </fmt:message>
                </div>
                </c:when>
                <c:when test="${not empty updatedUserName}">
                    <div class="span16 alert-message block-message success">
                    <fmt:message key="admin.updatedUserPositiveMsg">
                        <fmt:param>${updatedUserName}</fmt:param>
                    </fmt:message>
                </div>
                </c:when>
                <c:when test="${not empty addedUserName}">
                    <div class="span16 alert-message block-message success">
                    <fmt:message key="admin.addedUserPositiveMsg">
                        <fmt:param>${addedUserName}</fmt:param>
                    </fmt:message>
                </div>
                </c:when>
                <c:when test="${not empty addedContractToUserNames && not empty addedContractName}">
                    <div class="span16 alert-message block-message success">
                    <fmt:message key="admin.addedContractToUsersPositiveMsg">
                        <fmt:param>${addedContractName}</fmt:param>
                        <fmt:param>${addedContractToUserNames}</fmt:param>
                    </fmt:message>
                </div>
                </c:when>
                <c:when test="${not empty deletedUserAudits}">
                    <div class="span16 alert-message block-message success">
                    <fmt:message key="admin.deletedUserAuditsPositiveMsg">
                        <fmt:param>${deletedUserAudits}</fmt:param>
                    </fmt:message>
                </div>
                </c:when>
            </c:choose>
            </div>
            <div class="row">
                <div class="span12 admin-action">
                    <a class="btn btn-primary" href="<c:url value="/admin/add-user.html"/>">
                        <img src="${addUser}" alt="">
                        <fmt:message key="admin.addUser"/>
                    </a>
                    <a class="btn btn-primary" href="<c:url value="/admin/add-contract.html"/>">
                        <img src="${addContract}" alt="">
                        <fmt:message key="admin.addContract"/>
                    </a>
                </div>
            </div>
            <div class="row">
            <c:choose>
                <c:when test="${not empty userList}">
                <div class="span16 tg-table-container">
                    <table id="user-list-table" class="tg-table">
                        <caption><fmt:message key="admin.userList"/></caption>
                        <thead>
                            <tr>
                                <th id="user-id" scope="col" class="col01"><fmt:message key="admin.userId"/></th>
                                <th id="last-name" scope="col" class="col02"><fmt:message key="admin.lastName"/></th>
                                <th id="first-name" scope="col" class="col03"><fmt:message key="admin.firstName"/></th>
                                <th id="activated" scope="col" class="col04"><fmt:message key="admin.activated"/></th>
                                <th id="edit" scope="col" class="col05"><fmt:message key="admin.edit"/></th>
                                <th id="delete-user" scope="col" class="col06"><fmt:message key="admin.delete"/></th>
                                <th id="edit-contract" scope="col" class="col07"><fmt:message key="admin.manage"/></th>
                                <th id="delete-audits" scope="col" class="col08"><fmt:message key="admin.deleteAudits"/></th>
                            </tr>
                        </thead>
                        <tbody>
                    <c:forEach items="${userList}" var="user">
                            <tr>
                                <td headers="user-id" class="col01">
                                    ${user.email1}
                                </td>
                                <td headers="last-name" class="col02">
                                    ${user.name}
                                </td>
                                <td headers="first-name" class="col03">
                                    ${user.firstName}
                                </td>
                                <td headers="activated" class="col04">
                                    <c:choose>
                                        <c:when test="${user.accountActivated}">
                                            <fmt:message key="admin.yes"/>
                                        </c:when>
                                        <c:otherwise>
                                            <fmt:message key="admin.no"/>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td headers="edit" class="col05">
                                    <a href="<c:url value="/admin/edit-user.html?user=${user.id}"/>" title="<fmt:message key="admin.editUser"/> ${user.email1}">
                                        <img src="${editUser}" alt="<fmt:message key="admin.editUser"/> ${user.email1}"/>
                                    </a>
                                </td>
                                <td headers="delete-user" class="col06">
                                    <c:choose>
                                        <c:when test="${user.id ne authenticatedUserId}">
                                    <a href="<c:url value="/admin/delete-user.html?user=${user.id}"/>" title="<fmt:message key="admin.deleteUser"/> ${user.email1}">
                                        <img src="${deleteUser}" alt="<fmt:message key="admin.deleteUser"/> ${user.email1}"/>
                                    </a>
                                        </c:when>
                                        <c:otherwise>
                                            <fmt:message key="admin.impossibleAction"/>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td headers="edit-contract" class="col07">
                                    <a href="<c:url value="/admin/manage-contracts.html?user=${user.id}"/>" title="<fmt:message key="admin.manageContractsTitle"><fmt:param>${user.email1}</fmt:param></fmt:message>">
                                        <img src="${editContract}" alt="<fmt:message key="admin.manageContractsTitle"><fmt:param>${user.email1}</fmt:param></fmt:message>"/>
                                    </a>
                                </td>
                                <td headers="delete-audits" class="col08">
                                    <a class="button" href="<c:url value="/admin/delete-user-audits.html?user=${user.id}"/>" title="<fmt:message key="admin.deleteAuditsTitle"><fmt:param>${user.email1}</fmt:param></fmt:message>">
                                        <img src="${deleteAudits}" alt="<fmt:message key="admin.deleteAuditsTitle"><fmt:param>${user.email1}</fmt:param></fmt:message>"/>
                                    </a>
                                </td>
                            </tr>    
                    </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="span12 admin-action">
                    <a class="btn btn-primary" href="<c:url value="/admin/add-user.html"/>">
                        <img src="${addUser}" alt="">
                        <fmt:message key="admin.addUser"/>
                    </a>
                    <a class="btn btn-primary" href="<c:url value="/admin/add-contract.html"/>">
                        <img src="${addContract}" alt="">
                        <fmt:message key="admin.addContract"/>
                    </a>
                </div>
                </c:when>
                <c:otherwise>
                <div class="span16">
                    <fmt:message key="admin.noUser"/>
                </div>
                </c:otherwise>
            </c:choose>
            </div>
        </div><!-- class="container"-->                    
    <%@include file="template/footer.jsp" %>
    </body>
</html>
</compress:html>