<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="function" uri="http://tagutils"%>
            <c:if test="${auditSetUpCommand.auditSite != 'true'}">
            <div class="span14 offset1">
                <div id="mandatory-elements-message" class="alert-message block-message warning">
                    <spring:message code="sign-up.mandatoryElementsMessage"/>
                </div><!-- id="mandatory-elements-message" class="alert-message block-message warning"-->
            </div><!-- class="span14 offset1" -->
            </c:if>
            <div class="span14 offset1">
                <div id="audit-set-up-form">
                    <form:form modelAttribute="auditSetUpCommand" action="" method="post" enctype="multipart/form-data">
                        <spring:hasBindErrors name="auditSetUpCommand">
                        <div id="sign-up-form-general-error">
                            <form:errors path="generalErrorMsg" cssClass="alert-message block-message error" element="div"/>
                        </div>
                        </spring:hasBindErrors>
                        <div>
                            <form:hidden path="auditSite"/>
                            <form:hidden path="uploadAudit"/>
                            <form:hidden path="contractId"/>
                        </div>
                    <c:choose>
                        <c:when test="${auditSetUpCommand.auditSite == 'true'}">
                        <div>
                        <form:hidden path="urlList"/>
                        </div>
                        </c:when>
                        <c:when test="${not empty auditSetUpCommand.urlList}">
                        <fieldset>
                            <legend><spring:message code="auditSetUp.enterUrl"/></legend>
                            <c:forEach items="${auditSetUpCommand.urlList}" varStatus="pUrlIndex">
                                <c:if test="${pUrlIndex.index > 0}">
                                <c:set var="toggleUrl" scope="page" value="_toggle-url"/>
                                </c:if>
                                <c:set var="urlListIndexError"><form:errors path="urlList[${pUrlIndex.index}]"/></c:set>
                                <c:choose>
                                    <c:when test="${not empty urlListIndexError}">
                                        <c:set var="urlListIndexErrorClass" value="error"/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:set var="urlListIndexErrorClass" value=""/>
                                    </c:otherwise>
                                </c:choose>
                            <div class="clearfix ${urlListIndexErrorClass} ${toggleUrl}">
                                <label id="label-url${pUrlIndex.index+1}" for="urlList${pUrlIndex.index}">
                                    <c:if test="${pUrlIndex.index == 0}"><span class="mandatory">* </span></c:if>URL ${pUrlIndex.index+1}
                                </label>
                                <div class="url-input input">
                                    <form:input path="urlList[${pUrlIndex.index}]" cssErrorClass="xlarge error" cssClass="xlarge" />
                                    <form:errors path="urlList[${pUrlIndex.index}]" cssClass="alert-message error" />
                                    <c:if test="${pUrlIndex.index == 0}">
                                    <span class="_toggle-master-url ${expanded}">
                                       <fmt:message key="auditSetUp.addUrls"/>
                                    </span>
                                    <span class="help-block">
                                        <fmt:message key="required.mandatoryFieldHelpMsg"/>
                                    </span>
                                    </c:if>
                                </div>
                            </div>
                            </c:forEach>
                        </fieldset>
                        </c:when>
                        <c:otherwise>
                        <fieldset>
                            <legend><spring:message code="auditSetUp.enterFile"/></legend>
                            <c:forEach items="${auditSetUpCommand.fileInputList}" varStatus="pFileInput">
                                <c:if test="${pFileInput.index > 0}">
                                    <c:set var="toggleUrl" scope="page" value="_toggle-url"/>
                                </c:if>
                                <c:set var="fileLabel" scope="page">
                                    <fmt:message key="auditSetUp.file"/> ${pFileInput.index+1}
                                </c:set>
                                <c:set var="fileIndexError"><form:errors path="urlList[${pFileInput.index}]"/></c:set>
                                <c:choose>
                                    <c:when test="${not empty fileIndexError}">
                                        <c:set var="fileIndexErrorClass" value="error"/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:set var="fileIndexErrorClass" value=""/>
                                    </c:otherwise>
                                </c:choose>
                            <div class="clearfix ${fileIndexErrorClass} ${toggleUrl}">
                                <label id="file-input${pFileInput.index+1}" for="fileInputList${pFileInput.index}">
                                    <c:if test="${pFileInput.index == 0}"><span class="mandatory">* </span></c:if>${fileLabel}
                                </label>
                                <div class="file-input input">
                                    <form:input path="fileInputList[${pFileInput.index}]" cssClass="input-file" cssErrorClass="input-file error" title="${fileLabel}" type="file"/>
                                    <form:errors path="fileInputList[${pFileInput.index}]" cssClass="alert-message error" />
                                    <c:if test="${pFileInput.index == 0}">
                                    <span class="_toggle-master-url ${expanded}">
                                       <fmt:message key="auditSetUp.addInputs"/>
                                    </span>
                                    <span class="help-block">
                                        <fmt:message key="required.noFileUploadedHelpMsg"/>
                                    </span>
                                    </c:if>
                                </div>
                            </div>
                            </c:forEach>
                        </fieldset>
                        </c:otherwise>
                    </c:choose>
                        <spring:hasBindErrors name="auditSetUpCommand">
                            <c:set var="expanded" scope="page" value="_expanded"/>
                        </spring:hasBindErrors>
                        <h2 class="_toggle-master-parameters ${expanded}">
                            <fmt:message key="auditSetUp.formTitle"/>
                        </h2>
                        <c:forEach var="entry" items="${parametersMap}">
                        <fieldset class="_toggle-parameters ">
                            <legend><fmt:message key="${entry.key}"/></legend>
                            <c:set var="fieldsetInfo" scope="page">
                                <spring:message code="${entry.key}-info"/>
                            </c:set>
                            <c:if test="${fieldsetInfo != ''}">
                                <div class="alert-message warning">
                                    <p class="fieldset-info">${fieldsetInfo}</p>
                                </div>
                            </c:if>
                            <c:forEach var="parameter" items="${entry.value}" >
                            <c:set var="i18nKey" scope="page" value="${parameter.formField.i18nKey}"/>
                            <c:set var="code" scope="page" value="${parameter.parameterElement.parameterElementCode}"/>
                            <c:set var="auditParameterError"><form:errors path="auditParameter[${code}]"/></c:set>
                            <c:choose>
                                <c:when test="${not empty auditParameterError}">
                                    <c:set var="auditParameterErrorClass" value="error"/>
                                </c:when>
                                <c:otherwise>
                                    <c:set var="auditParameterErrorClass" value=""/>
                                </c:otherwise>
                            </c:choose>
                            <div class="clearfix ${auditParameterErrorClass}">
                                <label id="set-up-${i18nKey}" for="${i18nKey}"><fmt:message key="${i18nKey}"/></label>
                                <div class="set-up-value input">
                                <c:choose>
                                    <c:when test="${function:instanceOf(parameter.formField, 'org.opens.tgol.form.SelectFormField')}">
                                        <form:select id="${i18nKey}" path="auditParameter[${code}]">
                                        <c:forEach items="${parameter.formField.selectElementMap}" var="group">
                                        <optgroup label="<spring:message code="${group.key}"/>">
                                            <c:forEach items="${group.value}" var="level">
                                            <c:choose>
                                                <c:when test="${level.default == 'true'}">
                                                    <c:set var="selected" scope="page" value="selected=\"selected\""/>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:set var="selected" scope="page" value=""/>
                                                </c:otherwise>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${level.enabled == 'false'}">
                                                    <c:set var="disabled" scope="page" value="disabled=\"disabled\""/>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:set var="disabled" scope="page" value=""/>
                                                </c:otherwise>
                                            </c:choose>
                                            <option value="${level.value}" ${selected} ${disabled}><spring:message code="${level.i18nKey}"/></option>
                                            </c:forEach><!-- for each element of a referentiel -->
                                        </optgroup>
                                        </c:forEach><!-- for each referentiel -->
                                        </form:select>
                                    </c:when>
                                    <c:otherwise>
                                        <form:input id="${i18nKey}" path="auditParameter[${code}]" cssErrorClass="xlarge error" cssClass="xlarge"/>
                                        <form:errors path="auditParameter[${code}]" cssClass="alert-message error" /><br/>
                                        <span class="help-block">
                                        <c:choose>
                                            <c:when test="${function:instanceOf(parameter.formField, 'org.opens.tgol.form.NumericalFormField')}">
                                                <fmt:message key="${i18nKey}-rule">
                                                    <fmt:param>${parameter.formField.maxValue}</fmt:param>
                                                </fmt:message>
                                            </c:when>
                                            <c:otherwise>
                                                <fmt:message key="${i18nKey}-rule"/>
                                            </c:otherwise>
                                        </c:choose><!-- test="instanceof NumericalFormField"-->
                                        </span>
                                    </c:otherwise>
                                </c:choose><!-- test="instanceof SelectFormField"-->
                                    </div>
                                </div>
                            </c:forEach>
                        </fieldset>
                        </c:forEach>
                        <div id="audit-set-up-form-submit" class="actions">
                            <input id="input-submit" type="submit" value="<fmt:message key="auditSetUp.launchAudit"/>" class="large awesome orange"/>
                        </div>
                    </form:form>
                </div> <!-- id="audit-set-up-form"-->
            </div><!-- class="span14 offset1"-->