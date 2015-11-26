<<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://tagutils" prefix="tg" %>
<!DOCTYPE html>

<c:set var="warningImgUrl">
    <c:url value="/Images/warning.png"/>  
</c:set>

<html lang="${tg:lang(pageContext)}">
    <c:set var="pageTitle" scope="page">
        <fmt:message key="test-weight.pageTitle">
            <fmt:param>${param.ref}</fmt:param>
        </fmt:message>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="tgm-account-settings">
        <c:set var="accountSettingsActive" value="true" scope="page"/>
        <%@include file="template/header-utils.jsp" %>
        <div class="container">
            <c:set var="pageName" scope="page">
                <fmt:message key="test-weight.h1">
                    <fmt:param>
                        ${ref}
                    </fmt:param>
                </fmt:message>
            </c:set>
            <c:if test="${not empty modifiableTestWeightRefs}">
            <div id="navSecondaryLevel">
                <ul class="pills">
                    <li>
                        <a href="<c:url value="/account-settings.html"/>">
                            <fmt:message key="account-settings.accountSettings"/>
                        </a>
                    </li>
                    <c:forEach items="${modifiableTestWeightRefs}" var="ref">
                        <c:if test="${param.ref == ref}">
                            <c:set var="active" value="class=\"active\""/>
                        </c:if>    
                    <li ${active}>
                        <a href="<c:url value="/test-weight.html?ref=${ref}"/>">
                            <fmt:message key="test-weight.h1">
                                <fmt:param>
                                    ${ref}
                                </fmt:param>
                            </fmt:message>
                        </a>
                    </li>
                    </c:forEach>
                </ul>
            </div>
            </c:if>
            <c:set var="pageName" scope="page">
                <fmt:message key="test-weight.h1">
                    <fmt:param>
                        ${param.ref}
                    </fmt:param>
                </fmt:message>
            </c:set>
            <ul class="breadcrumb">
                <li>
                    <a href="<c:url value="/home.html"/>">
                        <fmt:message key="home.h1"/>
                    </a>
                    <span class="divider"></span>
                </li>
                <c:if test="${not empty modifiableTestWeightRefs}">
                <li>
                    <a href="<c:url value="/account-settings.html"/>">
                        <fmt:message key="account-settings.accountSettings"/>
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
                <c:if test="${testWeightSuccessfullyUpdated}">
                <div class="span16">
                    <div id="informative-message" class="alert-message block-message success">
                    <fmt:message key="test-weight.updatedTestWeightPositiveMsg">
                        <fmt:param>${param.ref}</fmt:param>
                    </fmt:message>
                    </div><!-- id="informative-message" class="alert-message block-message warning"-->
                </div>
                </c:if>
                <div class="span16">
                    <div id="informative-message" class="alert-message block-message warning">
                        <fmt:message key="test-weight.fillInElementsExplanation"/>
                    </div><!-- id="informative-message" class="alert-message block-message warning"-->
                </div>
                <div class="span16 tg-table-container">
                    <form:form method="post" modelAttribute="changeTestWeightCommand" acceptCharset="UTF-8" enctype="application/x-www-form-urlencoded">
                    <spring:hasBindErrors name="changeTestWeightCommand">
                    <div id="change-test-weight-form-general-error">
                        <form:errors path="generalErrorMsg" cssClass="alert-message block-message error"/>
                    </div>
                    </spring:hasBindErrors>
                        <c:set var="tableSummaryAndCaption">
                            <fmt:message key="test-weight.weightMngtTableCaption">
                                <fmt:param>${param.ref}</fmt:param>   
                            </fmt:message>
                        </c:set>
                        <table id="test-weight-mngt" class="tg-table">
                            <caption>${tableSummaryAndCaption}</caption>
                                <thead>
                                    <tr>
                                        <th id="test-weight-code" scope="col" class="col01"><fmt:message key="test-weight.testCode"/></th>
                                        <th id="test-weight-label" scope="col" class="col02"><fmt:message key="test-weight.testLabel"/></th>
                                        <th id="user-test-weight" scope="col" class="col03"><fmt:message key="test-weight.userTestWeight"/></th>
                                        <th id="default-test-weight" scope="col" class="col04"><fmt:message key="test-weight.defaultTestWeight"/></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="test" items="${testList}">
                                    <tr>
                                        <td headers="test-weight-code" class="col01">${test.label}</td>
                                        <td headers="test-weight-label" class="col02"><fmt:message key="${test.code}"/></td>
                                        <td headers="user-test-weight" class="col03">
                                            <c:set var="testWeightError"><form:errors path="testWeightMap[${test.code}]"/></c:set>
                                            <c:choose>
                                                <c:when test="${not empty testWeightError}">
                                                    <c:set var="testWeightErrorClass" value="error"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:set var="testWeightErrorClass" value=""/>
                                                </c:otherwise>
                                            </c:choose>
                                            <div class="clearfix ${testWeightErrorClass}">
                                            <c:set var="formInputTitle">
                                                <fmt:message key="test-weight.userTestWeightTitle">
                                                    <fmt:param>
                                                        ${test.code}
                                                    </fmt:param>
                                                </fmt:message>
                                            </c:set>
                                            <form:input path="testWeightMap[${test.code}]" cssErrorClass="mini error" cssClass="mini" title="${formInputTitle}"/>
                                            <c:if test="${not empty testWeightError}">
                                                <c:set var="formInputErrorAlt">
                                                <fmt:message key="test-weight.userTestWeightErrorAlt">
                                                    <fmt:param>
                                                        ${test.code}
                                                    </fmt:param>
                                                </fmt:message>
                                                </c:set>
                                                <img src="${warningImgUrl}" alt="${formInputErrorAlt}" title="${formInputErrorAlt}"/>
                                            </c:if>
                                            </div> 
                                        <td headers="default-test-weight" class="col04"><fmt:formatNumber type="number" value="${test.weight}" minFractionDigits="1"/></td>
                                    </tr>
                                    </c:forEach>
                                </tbody>
                        </table>
                        <div id="test-weight-form-submit" class="actions">
                            <input class="btn primary" type="submit" value="<fmt:message key="test-weight.saveChanges"/>   &raquo;"/>
                        </div>
                    </form:form> 
                </div>
            </div><!-- class="row" -->
        </div><!-- class="container" -->
        <%@include file="template/footer.jsp" %>
    </body>
</html>
</compress:html>