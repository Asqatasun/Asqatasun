<%@taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://tagutils" prefix="tg" %>

                <div class="span16">
                    <div id="mandatory-elements-message" class="alert-message block-message warning">
                        <spring:message code="sign-up.mandatoryElementsMessage"/>
                    </div><!-- id="mandatory-elements-message" class="alert-message block-message warning"-->
                </div><!-- class="span15 offset1" -->
                <div class="span16">
                    <div id="account-settings-form">
                        <form:form method="post" modelAttribute="createContractCommand" acceptCharset="UTF-8" enctype="application/x-www-form-urlencoded">
                            <spring:hasBindErrors name="createContractCommand">
                            <div id="sign-up-form-general-error">
                                <form:errors path="generalErrorMsg" cssClass="alert-message block-message error" element="div"/>
                            </div>
                            </spring:hasBindErrors>
                            <c:if test="${not empty userList}">
                                <c:set var="userListError"><form:errors path="userList"/></c:set>
                                <c:if test="${not empty userListError}">
                                    <c:set var="userListErrorClass" value="error"/>
                                </c:if>
                            <div class="clearfix ${userListErrorClass}">
                                <label id="user-list-label" for="userList"><fmt:message key="add-contract.users"/></label>
                                <div class="input" >
                                <c:choose>
                                    <c:when test="${fn:length(userList) < 10}">
                                        <c:set var="selectSize" value="${fn:length(userList)}"/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:set var="selectSize" value="10"/>
                                    </c:otherwise>
                                </c:choose>
                                <form:select multiple="true" path="userList" size="${selectSize}" class="xlarge" items="${userList}" itemValue="id" itemLabel="email1"/>
                                <form:errors path="userList" cssClass="alert-message error" />
                                </div>
                            </div>
                            </c:if>
                            <c:set var="labelError"><form:errors path="label"/></c:set>
                            <c:if test="${not empty labelError}">
                                <c:set var="labelErrorClass" value="error"/>
                            </c:if>
                            <div class="clearfix ${labelErrorClass}">
                                <label id="label-label" for="label"><fmt:message key="edit-contract.contractLabel"/></label>
                                <div class="input" >
                                    <form:input path="label" cssErrorClass="xlarge error" cssClass="xlarge"/>
                                    <form:errors path="label" cssClass="alert-message error" />
                                </div>
                            </div>
                            <c:set var="beginDateError"><form:errors path="beginDate"/></c:set>
                            <c:if test="${not empty beginDateError}">
                                <c:set var="beginDateErrorClass" value="error"/>
                            </c:if>
                            <div class="clearfix ${beginDateErrorClass}">
                                <label id="begin-date-label" for="beginDate"><fmt:message key="edit-contract.contractBeginDate"/></label>
                                <div class="input" >
                                    <form:input path="beginDate" cssErrorClass="xlarge error" cssClass="xlarge"/>
                                    <form:errors path="beginDate" cssClass="alert-message error" />
                                    <span class="help-block">
                                        <fmt:message key="edit-contract.contractBeginDateRule"/>
                                    </span>
                                </div>
                            </div><!-- class="clearfix"-->
                            <c:set var="endDateError"><form:errors path="endDate"/></c:set>
                            <c:if test="${not empty endDateError}">
                                <c:set var="endDateErrorClass" value="error"/>
                            </c:if>
                            <div class="clearfix ${endDateErrorClass}">
                                <label id="end-date-label" for="endDate"><fmt:message key="edit-contract.contractEndDate"/></label>
                                <div class="input" >
                                    <form:input path="endDate" cssErrorClass="xlarge error" cssClass="xlarge"/>
                                    <form:errors path="endDate" cssClass="alert-message error" />
                                    <span class="help-block">
                                        <fmt:message key="edit-contract.contractEndDateRule"/>
                                    </span>
                                </div>
                            </div><!-- class="clearfix"-->
                            <c:set var="contractUrlError"><form:errors path="contractUrl"/></c:set>
                            <c:if test="${not empty contractUrlError}">
                                <c:set var="contractUrlErrorClass" value="error"/>
                            </c:if>
                            <div class="clearfix ${contractUrlErrorClass}">
                                <label id="url-label" for="contractUrl"><fmt:message key="edit-contract.contractUrl"/></label>
                                <div class="input" >
                                    <form:input path="contractUrl" cssErrorClass="xlarge error" cssClass="xlarge"/>
                                    <form:errors path="contractUrl" cssClass="alert-message error" />
                                </div>
                            </div><!-- class="clearfix"-->
                            <fieldset>
                                <legend><fmt:message key="edit-contract.referentials"/></legend>
                                <div class="clearfix edit-contract-checkbox-block">
                                <c:forEach items="${createContractCommand.referentialMap}" var="ref" varStatus="pRefInput">
                                    <span class="edit-contract-ref">
                                    <c:set var="refKey">${ref.key}</c:set>
                                    <label id="${refKey}" for="referentialMap${refKey}1" class="edit-contract-label"><fmt:message key="edit-contract.${refKey}"/></label>
                                    <form:checkbox path="referentialMap[${refKey}]" cssErrorClass="error" value="${ref.value}" />
                                    <form:errors path="referentialMap[${refKey}]" cssClass="alert-message error" />
                                    </span>
                                </c:forEach>
                                </div>
                            </fieldset>
                            <fieldset>
                                <legend><fmt:message key="edit-contract.functionalities"/></legend>
                                <div class="clearfix edit-contract-checkbox-block">
                                <c:forEach items="${createContractCommand.functionalityMap}" var="funct" varStatus="pFunctInput">
                                    <span class="edit-contract-funct">
                                        <c:set var="functKey">${funct.key}</c:set>
                                        <label id="${functKey}" for="functionalityMap${functKey}1" class="edit-contract-label"><fmt:message key="edit-contract.${functKey}"/></label>
                                        <form:checkbox path="functionalityMap[${functKey}]" cssErrorClass="error"  value="${funct.value}"/>
                                        <form:errors path="functionalityMap[${functKey}]" cssClass="alert-message error" />
                                    </span>
                                </c:forEach>
                                </div>
                            </fieldset>
                            <c:forEach var="entry" items="${optionsMap}">
                            <fieldset>
                                <legend><fmt:message key="${entry.key}"/></legend>
                                <c:if test="${entry.key == 'crawl-options'}">
                                <div class="alert-message warning" >
                                    <p class="fieldset-info"><fmt:message key="edit-contract.crawlOptionsInfo"/></p>
                                </div>
                                </c:if>
                                <c:forEach var="option" items="${entry.value}" >
                                <c:set var="i18nKey" scope="page" value="${option.formField.i18nKey}"/>
                                <c:set var="code" scope="page" value="${option.option.code}"/>
                                <c:set var="contractOptionError"><form:errors path="optionMap[${code}]"/></c:set>
                                <c:choose>
                                    <c:when test="${not empty contractOptionError}">
                                        <c:set var="contractOptionErrorClass" value="error"/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:set var="contractOptionErrorClass" value=""/>
                                    </c:otherwise>
                                </c:choose>
                                <div class="clearfix ${contractOptionErrorClass}">
                                    <label id="set-up-${i18nKey}" for="${i18nKey}"><fmt:message key="${i18nKey}"/></label>
                                    <div class="set-up-value input">
                                        <form:input id="${i18nKey}" path="optionMap[${code}]" cssErrorClass="xlarge error" cssClass="xlarge"/>
                                            <form:errors path="optionMap[${code}]" cssClass="alert-message error" /><br/>
                                            <span class="help-block">
                                            <c:choose>
                                                <c:when test="${tg:instanceOf(option.formField, 'org.opens.tgol.form.NumericalFormField')}">
                                                    <fmt:message key="${i18nKey}-rule">
                                                        <fmt:param>${option.formField.maxValue}</fmt:param>
                                                    </fmt:message>
                                                </c:when>
                                                <c:otherwise>
                                                    <fmt:message key="${i18nKey}-rule"/>
                                                </c:otherwise>
                                            </c:choose><!-- test="instanceof NumericalFormField"-->
                                            </span>
                                        </div>
                                    </div>
                                </c:forEach>
                            </fieldset>
                            </c:forEach>
                            <div id="edit-contract-form-submit" class="actions">
                                <input class="btn primary" type="submit" value="${validateButtonName}"/>
                            </div>
                        </form:form>
                    </div><!-- id="account-settings-form" -->
                </div><!-- class="span16" -->