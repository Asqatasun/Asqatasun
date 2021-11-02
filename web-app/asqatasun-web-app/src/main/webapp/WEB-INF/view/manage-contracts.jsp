<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html>
    <%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%@ taglib uri="http://tagutils" prefix="tg" %>
    <!DOCTYPE html>
<%@include file="/WEB-INF/view/template/template_variables.jspf" %>

    <!-- external js -->
    <c:set var="jqueryUrl">
        <c:url value="/public/external_js/jquery-1.11.1.min.js?v${asqatasunVersion}"/>
    </c:set>
    <c:set var="jqueryTableSorterUrl" scope="request">
        <c:url value="/public/external_js/jquery.tablesorter.min.js?v${asqatasunVersion}"/>
    </c:set>

    <!-- internal js -->
    <c:set var="accessibleTableSorterJsUrl" scope="page">
        <c:url value="/public/js/table-sorter/accessible-table-sorter-min.js?v${asqatasunVersion}"/>
    </c:set>

    <!-- images -->
    <c:set var="editContract">
        <c:url value="/public/images/edit.png?v${asqatasunVersion}"/>
    </c:set>
    <c:set var="deleteContract">
        <c:url value="/public/images/remove.png?v${asqatasunVersion}"/>
    </c:set>
    <c:set var="deleteAudits">
        <c:url value="/public/images/bin.png?v${asqatasunVersion}"/>
    </c:set>
    <c:set var="addContract">
        <c:url value="/public/images/plus_2.png?v${asqatasunVersion}"/>
    </c:set>

    <html lang="${tg:lang(pageContext)}">
    <c:set var="pageTitle" scope="page">
        <fmt:message key="manage-contracts.pageTitle">
            <fmt:param>${userName}</fmt:param>
        </fmt:message>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="tgm-contracts-mngt">
    <c:set var="adminActive" value="true"/>
    <%@include file="template/header-utils.jsp" %>
    <main class="container">
        <c:set var="pageName" scope="page">
            <fmt:message key="manage-contracts.h1">
                <fmt:param>${userName}</fmt:param>
            </fmt:message>
        </c:set>
        <ul class="breadcrumb">
            <li><a href="<c:url value="/home.html"/>"><fmt:message key="home.h1"/></a> <span class="divider"></span>
            </li>
            <li><a href="<c:url value="/admin.html"/>"><fmt:message key="admin.h1"/></a> <span class="divider"></span>
            </li>
            <li class="active">${pageName}</li>
        </ul>
        <div class="row">
            <div class="span16">
                <h1>${pageName}</h1>
            </div>
        </div>
        <c:import url="template/sort-contract-console.jsp"/>
        <div class="row">
            <h2 class="span16 tg-table-title">
                <fmt:message key="manage-contracts.contractList"/>
            </h2>
            <c:choose>
                <c:when test="${deletedContractName != null}">
                    <div class="span16 alert-message block-message success">
                        <fmt:message key="manage-contracts.deletedContractPositiveMsg">
                            <fmt:param>${deletedContractName}</fmt:param>
                        </fmt:message>
                    </div>
                </c:when>
                <c:when test="${updatedContractName != null}">
                    <div class="span16 alert-message block-message success">
                        <fmt:message key="manage-contracts.updatedContractPositiveMsg">
                            <fmt:param>${updatedContractName}</fmt:param>
                        </fmt:message>
                    </div>
                </c:when>
                <c:when test="${addedContractName != null}">
                    <div class="span16 alert-message block-message success">
                        <fmt:message key="manage-contracts.addedContractPositiveMsg">
                            <fmt:param>${addedContractName}</fmt:param>
                        </fmt:message>
                    </div>
                </c:when>
                <c:when test="${deletedContractAuditsName != null}">
                    <div class="span16 alert-message block-message success">
                        <fmt:message key="manage-contracts.deletedContractAuditsPositiveMsg">
                            <fmt:param>${deletedContractAuditsName}</fmt:param>
                        </fmt:message>
                    </div>
                </c:when>
            </c:choose>
        </div>
        <div class="row">
            <div class="span8 admin-action">
                <a class="btn btn-primary"
                   href="<c:url value="/admin/manage-contracts/add-contract.html?user=${param.user}"/>">
                    <img src="${addContract}" alt="">
                    <fmt:message key="manage-contracts.addContract"/>
                </a>
            </div>
        </div>
        <div class="row">
            <c:choose>
                <c:when test="${not empty contractList}">
                    <div class="span16 tg-table-container">
                        <table id="contract-list-table" class="tg-table sortable-table">
                            <caption><fmt:message key="manage-contracts.contractList"/></caption>
                            <thead>
                            <tr>
                                <th id="contract-label" scope="col" class="col01"><fmt:message
                                        key="manage-contracts.contractName"/></th>
                                <th id="contract-begin-date" scope="col" class="col02"><fmt:message
                                        key="manage-contracts.beginDate"/></th>
                                <th id="contract-end-date" scope="col" class="col03"><fmt:message
                                        key="manage-contracts.endDate"/></th>
                                <th id="edit-contract" scope="col" class="col04"><fmt:message
                                        key="manage-contracts.edit"/></th>
                                <th id="delete-contract" scope="col" class="col05"><fmt:message
                                        key="manage-contracts.delete"/></th>
                                <th id="delete-audits" scope="col" class="col06"><fmt:message
                                        key="manage-contracts.deleteAudits"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${contractList}" var="contract">
                                <tr>
                                    <td headers="contract-label" class="col01">
                                            ${contract.label}
                                    </td>
                                    <td headers="contract-begin-date" class="col02">
                                        <fmt:formatDate type="date" value="${contract.beginDate}" dateStyle="short"/>
                                    </td>
                                    <td headers="contract-end-date" class="col03">
                                        <fmt:formatDate type="date" value="${contract.endDate}" dateStyle="short"/>
                                    </td>
                                    <td headers="edit-contract" class="col04">
                                        <a href="<c:url value="/admin/manage-contracts/edit-contract.html?cr=${contract.id}"/>"
                                           title="<fmt:message key="manage-contracts.editContractTitle"><fmt:param>${contract.label}</fmt:param></fmt:message>">
                                            <img src="${editContract}"
                                                 alt="<fmt:message key="manage-contracts.editContractAlt"><fmt:param>${contract.label}</fmt:param></fmt:message>"/>
                                        </a>
                                    </td>
                                    <td headers="delete-contract" class="col05">
                                        <a href="<c:url value="/admin/manage-contracts/delete-contract.html?cr=${contract.id}"/>"
                                           title="<fmt:message key="manage-contracts.deleteContractTitle"><fmt:param>${contract.label}</fmt:param></fmt:message>">
                                            <img src="${deleteContract}"
                                                 alt="<fmt:message key="manage-contracts.deleteContractAlt"><fmt:param>${contract.label}</fmt:param></fmt:message>"/>
                                        </a>
                                    </td>
                                    <td headers="delete-audits" class="col06">
                                        <a href="<c:url value="/admin/manage-contracts/delete-contract-audits.html?cr=${contract.id}"/>"
                                           title="<fmt:message key="manage-contracts.deleteAuditsTitle"><fmt:param>${contract.label}</fmt:param></fmt:message>">
                                            <img src="${deleteAudits}"
                                                 alt="<fmt:message key="manage-contracts.deleteAuditsTitle"><fmt:param>${contract.label}</fmt:param></fmt:message>"/>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <div class="admin-action">
                            <a class="btn btn-primary"
                               href="<c:url value="/admin/manage-contracts/add-contract.html?user=${param.user}"/>">
                                <img src="${addContract}" alt="">
                                <fmt:message key="manage-contracts.addContract"/>
                            </a>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="span16">
                        <fmt:message key="manage-contracts.noContract"/>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </main><!-- class="container"-->
    <%@include file="template/footer.jsp" %>
    <script type="text/javascript" src="${jqueryUrl}"></script>
    <script type="text/javascript" src="${jqueryTableSorterUrl}"></script>
    <script type="text/javascript" src="${accessibleTableSorterJsUrl}"></script>
    </body>
    </html>
</compress:html>
