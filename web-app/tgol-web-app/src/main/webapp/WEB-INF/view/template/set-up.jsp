<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://tagutils" prefix="tg" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:choose>
    <c:when test="${auditSetUpCommand.scope == 'DOMAIN'}">
        <c:set var="action" scope="page" value="site"/>
    </c:when>
    <c:when test="${auditSetUpCommand.scope == 'PAGE'}">
        <c:set var="action" scope="page" value="page"/>
    </c:when>
    <c:when test="${auditSetUpCommand.scope == 'FILE'}">
        <c:set var="action" scope="page" value="upload"/>
    </c:when>
    <c:when test="${auditSetUpCommand.scope == 'SCENARIO'}">
        <c:set var="action" scope="page" value="scenario"/>
    </c:when>
</c:choose>

<c:if test="${action != 'site' && action != 'scenario'}">
<div class="span16">
    <div id="mandatory-elements-message" class="alert-message block-message warning">
        <fmt:message key="sign-up.mandatoryElementsMessage"/>
    </div><!-- id="mandatory-elements-message" class="alert-message block-message warning"-->
</div><!-- class="span16" -->
</c:if>
<div class="span16">
    <div id="audit-set-up-form">

        <form:form modelAttribute="auditSetUpCommand" 
                   action="" 
                   method="post" 
                   enctype="multipart/form-data">
            <spring:hasBindErrors name="auditSetUpCommand">
            <div id="sign-up-form-general-error">
                <form:errors path="generalErrorMsg" 
                             cssClass="alert-message block-message error" 
                             element="div"/>
                <c:set var="onError" scope="page" value="on-error"/>
            </div>
            </spring:hasBindErrors>
            <div>
                <form:hidden path="contractId"/>
                <form:hidden path="scope"/>
                <c:if test="${action == 'scenario'}">
                    <form:hidden path="scenarioId"/>
                    <form:hidden path="scenarioName"/>
                </c:if>
            </div>
        <c:choose>
            <c:when test="${action == 'page' && fn:length(auditSetUpCommand.urlList) == 1}">
                <label id="label-url" for="urlList0">
                    <span class="mandatory">* </span>
                    <fmt:message key="auditSetUp.enterOneUrl"/>
                </label>
                <div class="url-input input">
                    <form:input path="urlList[0]" 
                                cssErrorClass="xxlarge error" 
                                cssClass="xxlarge" 
                                placeholder="http://www.domain.com"/>
                    <form:errors path="urlList[0]" 
                                 cssClass="alert-message error" />
                    <span class="help-block">
                        <fmt:message key="required.mandatoryFieldHelpMsg"/> 
                        (http://www.domain.com)
                    </span>
                </div>
            </c:when>
            <c:when test="${action == 'page'}">
            <fieldset>
                <legend>
                    <fmt:message key="auditSetUp.enterUrl"/>
                </legend>
                <c:forEach items="${auditSetUpCommand.urlList}" 
                           varStatus="pUrlIndex">
                    <c:set var="urlListIndexError">
                        <form:errors path="urlList[${pUrlIndex.index}]"/>
                    </c:set>
                    <c:choose>
                        <c:when test="${not empty urlListIndexError}">
                            <c:set var="urlListIndexErrorClass" value="error"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="urlListIndexErrorClass" value=""/>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${pUrlIndex.index == 0}">
                <div class="clearfix ${urlListIndexErrorClass}">
                    <label id="label-url${pUrlIndex.index+1}" 
                           for="urlList${pUrlIndex.index}">
                        <span class="mandatory">* </span>
                        URL ${pUrlIndex.index+1}
                    </label>
                    <div class="url-input input">
                        <form:input path="urlList[${pUrlIndex.index}]" 
                                    cssErrorClass="xlarge error" 
                                    cssClass="xlarge" />
                        <form:errors path="urlList[${pUrlIndex.index}]" 
                                     cssClass="alert-message error" />
                        <span class="master-input ${onError}">
                           <fmt:message key="auditSetUp.addUrls"/>
                        </span>
                        <span class="help-block">
                            <fmt:message key="required.mandatoryFieldHelpMsg"/>
                        </span>
                    </div>
                </div>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${pUrlIndex.index == 1}">
                <div id="extra-inputs">
                            </c:if>
                    <div id="extra-input${pUrlIndex.index+1}" 
                         class="clearfix ${urlListIndexErrorClass}">
                        <label id="label-url${pUrlIndex.index+1}" 
                               for="urlList${pUrlIndex.index}">
                            URL ${pUrlIndex.index+1}
                        </label>
                        <div class="url-input input">
                            <form:input path="urlList[${pUrlIndex.index}]" 
                                        cssErrorClass="xlarge error" 
                                        cssClass="xlarge" />
                            <form:errors path="urlList[${pUrlIndex.index}]" 
                                         cssClass="alert-message error" />
                        </div>
                    </div>
                        <c:if test="${pUrlIndex.index+1 == fn:length(auditSetUpCommand.urlList)}">
                </div> <!-- id=extra-inputs-->
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </fieldset>
            </c:when>
            <c:when test="${action == 'upload'}">
            <fieldset>
                <legend>
                    <fmt:message key="auditSetUp.enterFile"/>
                </legend>
                <c:forEach items="${auditSetUpCommand.fileInputList}" 
                           varStatus="pFileInput">
                    <c:set var="fileIndexError">
                        <form:errors path="fileInputList[${pFileInput.index}]"/>
                    </c:set>
                    <c:choose>
                        <c:when test="${not empty fileIndexError}">
                            <c:set var="fileIndexErrorClass" value="error"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="fileIndexErrorClass" value=""/>
                        </c:otherwise>
                    </c:choose>
                    <c:set var="fileLabel" scope="page">
                        <fmt:message key="auditSetUp.file"/> ${pFileInput.index+1}
                    </c:set>
                    <c:choose>
                        <c:when test="${pFileInput.index == 0}">
                <div class="clearfix ${fileIndexErrorClass}">
                    <label id="label-url${pFileInput.index+1}" 
                           for="fileInputList${pFileInput.index}">
                        <span class="mandatory">* </span>
                        <fmt:message key="auditSetUp.file"/> ${fileLabel}
                    </label>
                    <div class="file-input input">
                        <form:input path="fileInputList[${pFileInput.index}]" 
                                    cssErrorClass="input-file error xlarge" 
                                    cssClass="input-file xlarge" 
                                    title="${fileLabel}" 
                                    type="file"/>
                        <form:errors path="fileInputList[${pFileInput.index}]" 
                                     cssClass="alert-message error" />
                        <span class="master-input ${onError}">
                           <fmt:message key="auditSetUp.addInputs"/>
                        </span>
                        <span class="help-block">
                            <fmt:message key="required.noFileUploadedHelpMsg"/>
                        </span>
                    </div>
                </div>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${pFileInput.index == 1}">
                <div id="extra-inputs">
                            </c:if>
                    <div id="extra-input${pFileInput.index+1}" 
                         class="clearfix ${urlListIndexErrorClass}">
                        <label id="label-url${pFileInput.index+1}" 
                               for="fileInputList${pFileInput.index}">
                            ${fileLabel}
                        </label>
                        <div class="file-input input">
                            <form:input path="fileInputList[${pFileInput.index}]" 
                                        cssErrorClass="input-file error xlarge" 
                                        cssClass="input-file xlarge" 
                                        title="${fileLabel}" 
                                        type="file"/>
                            <form:errors path="fileInputList[${pFileInput.index}]" 
                                         cssClass="alert-message error" />
                        </div>
                    </div>
                        <c:if test="${pFileInput.index+1 == fn:length(auditSetUpCommand.fileInputList)}">
                </div> <!-- id=extra-inputs-->
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>                            
            </fieldset>
            </c:when>
        </c:choose>
            <spring:hasBindErrors name="auditSetUpCommand">
                <c:set var="onError" scope="page" value="on-error"/>
            </spring:hasBindErrors>
            <h2>
                <span class="master-audit-options ${onError}">
                    <fmt:message key="auditSetUp.formTitle"/>
                </span>
            </h2>
            <div id="audit-options">
                <fieldset>
                    <legend>
                        <fmt:message key="level-parameters"/>
                    </legend>
                <c:set var="fieldsetInfo" scope="page">
                    <fmt:message key="level-parameters-info"/>
                </c:set>
                <c:if test="${fieldsetInfo != ''}">
                    <div class="alert-message block-message warning">
                        <p class="fieldset-info">${fieldsetInfo}</p>
                    </div>
                </c:if>
                <c:forEach var="level" items="${levelList}" >
                    <c:set var="i18nKey" scope="page" value="${level.i18nKey}"/>
                    <c:set var="code" scope="page" value="LEVEL"/>
                    <div class="clearfix">
                        <label id="set-up-${i18nKey}" 
                               for="${i18nKey}">
                            <fmt:message key="${i18nKey}"/>
                        </label>
                        <div class="set-up-value input">
                            <form:select id="${i18nKey}" 
                                         path="level" 
                                         cssErrorClass="xlarge error" 
                                         cssClass="xlarge">
                                <c:forEach items="${level.selectElementMap}" 
                                           var="group">
                                    <c:if test="${group.value[0].enabled}">
                                    <optgroup label="<fmt:message key="${group.key}-optgroup"/>">
                                        <c:forEach items="${group.value}" 
                                                   var="level">
                                        <c:choose>
                                            <c:when test="${level.defaultElement == 'true'}">
                                                <c:set var="selected" 
                                                       scope="page" 
                                                       value="selected=\"selected\""/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:set var="selected" 
                                                       scope="page" 
                                                       value=""/>
                                            </c:otherwise>
                                        </c:choose>
                                        <option value="${level.value}" ${selected}>
                                            <fmt:message key="${level.i18nKey}"/> 
                                            (<fmt:message key="${group.key}-optgroup"/>)
                                        </option>
                                        </c:forEach><!-- for each element of a referentiel -->
                                    </optgroup>
                                    </c:if>
                                </c:forEach>
                            </form:select>
                            <form:errors path="level" cssClass="alert-message error" /><br/>
                        </div>
                    </div>
                </fieldset>    
                </c:forEach>
                <c:forEach var="entry" items="${parametersMap}">
                <fieldset>
                    <legend><fmt:message key="${entry.key}"/></legend>
                    <c:set var="fieldsetInfo" scope="page">
                        <fmt:message key="${entry.key}-info"/>
                    </c:set>
                    <c:if test="${fieldsetInfo != ''}">
                        <div class="alert-message block-message warning">
                            <p class="fieldset-info">${fieldsetInfo}</p>
                        </div>
                    </c:if>
                    <c:forEach var="parameter" items="${entry.value}" >
                    <c:set var="i18nKey" 
                           scope="page" 
                           value="${parameter.formField.i18nKey}"/>
                    <c:set var="code" 
                           scope="page" 
                           value="${parameter.parameterElement.parameterElementCode}"/>
                    <c:set var="auditParameterError">
                        <form:errors path="auditParameter[${code}]"/>
                    </c:set>
                    <c:choose>
                        <c:when test="${not empty auditParameterError}">
                            <c:set var="auditParameterErrorClass" 
                                   value="error"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="auditParameterErrorClass" 
                                   value=""/>
                        </c:otherwise>
                    </c:choose>
                    <div class="clearfix ${auditParameterErrorClass}">
                        <c:if test="${! tg:instanceOf(parameter.formField, 'org.tanaguru.webapp.form.CheckboxFormField')}">
                        <label id="set-up-${i18nKey}" 
                               for="${i18nKey}">
                            <fmt:message key="${i18nKey}"/>
                        </label>
                        </c:if>
                        <div class="set-up-value input">
                        <c:choose>
                            <c:when test="${tg:instanceOf(parameter.formField, 'org.tanaguru.webapp.form.SelectFormField')}">
                                <form:select id="${i18nKey}" 
                                             path="auditParameter[${code}]">
                                <c:forEach items="${parameter.formField.selectElementMap}" 
                                           var="group">
                                <optgroup label="<fmt:message key="${group.key}"/>">
                                    <c:forEach items="${group.value}" 
                                               var="level">
                                    <c:choose>
                                        <c:when test="${level.defaultElement == 'true'}">
                                            <c:set var="selected" 
                                                   scope="page" 
                                                   value="selected=\"selected\""/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:set var="selected" 
                                                   scope="page" value=""/>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${level.enabled == 'false'}">
                                            <c:set var="disabled" 
                                                   scope="page" 
                                                   value="disabled=\"disabled\""/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:set var="disabled" 
                                                   scope="page"
                                                   value=""/>
                                        </c:otherwise>
                                    </c:choose>
                                    <option value="${level.value}" ${selected} ${disabled}>
                                        <fmt:message key="${level.i18nKey}"/>
                                    </option>
                                    </c:forEach><!-- for each element of a referentiel -->
                                </optgroup>
                                </c:forEach><!-- for each referentiel -->
                                </form:select>
                            </c:when>
                            <c:when test="${tg:instanceOf(parameter.formField, 'org.tanaguru.webapp.form.CheckboxFormField')}">
                                <ul class="inputs-list">
                                    <c:forEach items="${parameter.formField.checkboxElementList}" 
                                               var="element" 
                                               varStatus="pResult">
                                    <li>
                                        <label class="checkbox" 
                                               for="auditParameter${code}${pResult.index + 1}">    
                                            <form:checkbox path="auditParameter[${code}]" 
                                                           value="${element.value}"/>
                                            <fmt:message key="${i18nKey}"/>
                                        </label>
                                    </li>
                                    </c:forEach>
                                </ul>
                            </c:when>
                            <c:otherwise>
                                <form:input id="${i18nKey}" 
                                            path="auditParameter[${code}]" 
                                            cssErrorClass="xlarge error" 
                                            cssClass="xlarge"/>
                                <form:errors path="auditParameter[${code}]" 
                                             cssClass="alert-message error" /><br/>
                                <span class="help-block">
                                <c:choose>
                                    <c:when test="${tg:instanceOf(parameter.formField, 'org.tanaguru.webapp.form.NumericalFormField')}">
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
            </div>
            <div id="audit-set-up-form-submit" class="actions">
                <input id="launch-audit-submit" 
                       type="submit" 
                       value="<fmt:message key="auditSetUp.launchAudit"/>" 
                       class="large awesome orange xlarge"/>
            </div>
            <c:if test="${action == 'page' || action == 'upload'}">
                <%@include file="process-pop-up.jsp" %>
            </c:if>
        </form:form>
    </div> <!-- id="audit-set-up-form"-->
</div><!-- class="span16"-->