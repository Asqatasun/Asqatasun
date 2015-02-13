<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://tagutils" prefix="tg" %>
<!DOCTYPE html>
<html lang="${tg:lang(pageContext)}">
    <c:set var="pageTitle" scope="page">
        <fmt:message key="scenarioManagement.pageTitle">
            <fmt:param>${contractName}</fmt:param>
        </fmt:message>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="tgm-scenario-mngt">
        <%@include file="template/header-utils.jsp" %>
        <div class="container">
            <c:set var="pageName" scope="page">
                <fmt:message key="scenarioManagement.h1"/>
            </c:set>
            <ul class="breadcrumb">
                <li><a href="<c:url value="/home.html"/>"><fmt:message key="home.h1"/></a> <span class="divider"></span></li>
                <li><a href="<c:url value="/home/contract.html?cr=${param.cr}"/>">${contractName}</a> <span class="divider"></span></li>
                <li class="active">${pageName}</li>
            </ul>
            <div class="row">
                <div class="span16">
                    <h1>
                        ${pageName}
                    </h1>
                </div><!-- class="span16" -->
            </div><!-- class="row" -->
            <div class="row">
                <c:if test="${newScenarioName != null}">
                <div class="span15 alert-message block-message info">
                    <fmt:message key="scenarioManagement.addScenarioPositiveMsg">
                        <fmt:param>${newScenarioName}</fmt:param>
                    </fmt:message>
                </div>
                </c:if>
                <div class="span16 tg-table-title">
                    <h2>
                        <fmt:message key="scenarioManagement.addScenario"/>
                    </h2>
                </div>
                <div class="span16">
                    <div id="mandatory-elements-message" class="alert-message block-message warning">
                        <spring:message code="sign-up.mandatoryElementsMessage"/>
                    </div><!-- id="mandatory-elements-message" class="alert-message block-message warning"-->
                </div><!-- class="span15 offset1" -->
                <div class="span16">
                    <div id="add-scenario-form">
                        <form:form modelAttribute="addScenarioCommand" action="" method="post" enctype="multipart/form-data">
                            <spring:hasBindErrors name="addScenarioCommand">
                            <div id="sign-up-form-general-error">
                                <form:errors path="generalErrorMsg" cssClass="alert-message block-message error" element="div"/>
                            </div>
                            </spring:hasBindErrors>
                            <form:hidden path="contractId"/>
                            <c:set var="scenarioLabelError"><form:errors path="scenarioLabel"/></c:set>
                            <c:if test="${not empty scenarioLabelError}">
                                <c:set var="scenarioLabelErrorClass" value="error"/>
                            </c:if>
                            <div class="clearfix ${scenarioLabelErrorClass}">
                                <c:set var="scenarioLabel">
                                    <fmt:message key="scenarioManagement.scenarioName"/>
                                </c:set>
                                <label id="scenario-label-label" for="scenarioLabel">
                                    <span class="mandatory">* </span>${scenarioLabel}
                                </label>
                                <div class="scenario-label-input input">
                                    <form:input path="scenarioLabel" cssClass="large" cssErrorClass="large error" title="${scenarioLabel}"/>
                                    <form:errors path="scenarioLabel" cssClass="alert-message error" />
                                </div>
                            </div>
                            <c:set var="scenarioFileError"><form:errors path="scenarioFile"/></c:set>
                            <c:if test="${not empty scenarioFileError}">
                                <c:set var="scenarioLabelFileClass" value="error"/>
                            </c:if>
                            <div class="clearfix ${scenarioLabelFileClass}">
                                <c:set var="scenarioFile">
                                    <fmt:message key="scenarioManagement.scenarioFile"/>
                                </c:set>
                                <label id="scenario-file-label" for="scenarioFile">
                                    <span class="mandatory">* </span>${scenarioFile}
                                </label>
                                <div class="scenario-file-input input">
                                    <form:input path="scenarioFile" cssClass="input-file" cssErrorClass="input-file error" title="${scenarioFile}" type="file"/>
                                    <form:errors path="scenarioFile" cssClass="alert-message error" />
                                </div>
                            </div>
                            <div id="add-scenario-form-submit" class="actions">
                                <input id="input-submit" type="submit" value="<fmt:message key="scenarioManagement.addScenarioFile"/>" class="large awesome orange"/>
                            </div>
                        </form:form>
                    </div><!-- class="add-scenario-form"-->
                </div><!-- class="span15 offset1"-->
            </div><!-- class="row"-->
            <div class="row">
                <div class="span16 tg-table-title">
                    <h2>
                        <fmt:message key="scenarioManagement.myScenario"/>
                    </h2>
                </div>
                <c:if test="${not empty deletedScenarioName}">
                <div class="span16 alert-message block-message info">
                    <fmt:message key="scenarioManagement.deletedScenarioPositiveMsg">
                        <fmt:param>${deletedScenarioName}</fmt:param>
                    </fmt:message>
                </div>
                </c:if>
            <c:choose>
                <c:when test="${not empty scenarioList}">
                <div class="span16 tg-table-container">
                    <table id="scenario-list-table" class="tg-table">
                        <caption><fmt:message key="auditSetUpScenario.scenarioList"/></caption>
                        <thead>
                            <tr>
                                <th id="label" scope="col" class="col01"><fmt:message key="scenarioManagement.label"/></th>
                                <th id="date" scope="col" class="col02"><fmt:message key="scenarioManagement.date"/></th>
                                <th id="launch" scope="col" class="col03"><fmt:message key="scenarioManagement.launch"/></th>
                                <th id="download" scope="col" class="col05"><fmt:message key="scenarioManagement.download"/></th>
                                <th id="delete" scope="col" class="col06"><fmt:message key="scenarioManagement.delete"/></th>
                            </tr>
                        </thead>
                        <tbody>
                    <c:forEach items="${scenarioList}" var="scenario">
                            <tr>
                                <td headers="label" class="col01">
                                    ${scenario.label}
                                </td>
                                <td headers="date" class="col02">
                                    <fmt:formatDate type="both" value="${scenario.dateOfCreation}" dateStyle="short" timeStyle="short"/>
                                </td>
                                <c:set var="launchTitle">
                                    <fmt:message key="scenarioManagement.launchTitle">
                                        <fmt:param>${scenario.label}</fmt:param>
                                    </fmt:message>
                                </c:set>
                                <td headers="launch" class="col03">
                                    <a href="<c:url value="/home/contract/audit-scenario-set-up.html?cr=${param.cr}&amp;sc=${scenario.id}"/>" title="${launchTitle}">
                                        <fmt:message key="scenarioManagement.launch"/>
                                    </a>
                                </td>
                                <c:set var="downloadLinkTitle">
                                    <fmt:message key="scenarioManagement.downloadTitle">
                                        <fmt:param>${scenario.label}</fmt:param>
                                    </fmt:message>
                                </c:set>
                                <td headers="download" class="col05">
                                    <a href="<c:url value="/home/contract/download-scenario.html?cr=${param.cr}&amp;sc=${scenario.id}"/>" title="${downloadLinkTitle}">
                                        <fmt:message key="scenarioManagement.download"/>
                                    </a>
                                </td>
                                <c:set var="deleteLinkTitle">
                                    <fmt:message key="scenarioManagement.deleteTitle">
                                        <fmt:param>${scenario.label}</fmt:param>
                                    </fmt:message>
                                </c:set>
                                <td headers="delete" class="col06">
                                    <a href="<c:url value="/home/contract/delete-scenario.html?cr=${param.cr}&amp;sc=${scenario.id}"/>" title="${deleteLinkTitle}">
                                        <fmt:message key="scenarioManagement.delete"/>
                                    </a>
                                </td>
                            </tr>
                    </c:forEach>
                        </tbody>
                    </table>
                </div>
                </c:when>
                <c:otherwise>
                <div class="span8">
                    <fmt:message key="scenarioManagement.noScenario"/>
                </div><!-- class="span16"-->
                </c:otherwise>
            </c:choose>
            </div><!-- class="row"-->
        </div><!-- class="container"-->                    
    <%@include file="template/footer.jsp" %>
    </body>
</html>
</compress:html>