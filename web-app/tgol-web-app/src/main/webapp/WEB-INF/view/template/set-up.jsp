<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="function" uri="http://tagutils"%>

                <form:form id="audit-set-up" modelAttribute="auditSetUpCommand" action="" method="post" class="audit-parameters-detailed cml cmr" enctype="multipart/form-data">
                    <div class="yui3-u-1">
                    <form:errors path="generalErrorMsg" cssClass="errorblock" element="div"/>
                    </div><!-- class="yui3-g" -->
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
                            <c:forEach items="${auditSetUpCommand.urlList}" varStatus="pUrlIndex">
                                <c:if test="${pUrlIndex.index > 0}">
                                    <c:set var="toggleUrl" scope="page" value="_toggle-url"/>
                                </c:if>
                    <div class="yui3-g ${toggleUrl}">
                        <div class="yui3-u-1-4">
                            <div class="url-label">
                                <label id="label-url${pUrlIndex.index+1}" for="urlList${pUrlIndex.index}">URL ${pUrlIndex.index+1}</label>
                            </div>
                        </div>
                        <div class="yui3-u-3-4">
                            <div class="url-input">
                                <form:input path="urlList[${pUrlIndex.index}]" cssClass="url-field" cssErrorClass="url-field errorfield" title="Url${pUrlIndex.index+1}"/>
                                <form:errors path="urlList[${pUrlIndex.index}]" cssClass="error" />
                                <c:if test="${pUrlIndex.index == 0}">
                                <span class="_toggle-master-url ${expanded}">
                                   <fmt:message key="auditSetUp.addUrls"/>
                                </span>
                                </c:if>
                            </div>
                        </div>
                    </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${auditSetUpCommand.fileInputList}" varStatus="pFileInput">
                                <c:if test="${pFileInput.index > 0}">
                                    <c:set var="toggleUrl" scope="page" value="_toggle-url"/>
                                </c:if>
                                <c:set var="fileLabel" scope="page">
                                    <fmt:message key="auditSetUp.file"/> ${pFileInput.index+1}
                                </c:set>
                    <div class="yui3-g ${toggleUrl}">
                        <div class="yui3-u-1-4">
                            <div class="file-label">
                                <label id="file-input${pFileInput.index+1}" for="fileInputList${pFileInput.index}">${fileLabel}</label>
                            </div>
                        </div>
                        <div class="yui3-u-3-4">
                            <div class="url-input">
                                <form:input path="fileInputList[${pFileInput.index}]" cssClass="file-input" cssErrorClass="file-input-field errorfield" title="${fileLabel}" type="file"/>
                                <form:errors path="fileInputList[${pFileInput.index}]" cssClass="error" />
                                <c:if test="${pFileInput.index == 0}">
                                <span class="_toggle-master-url ${expanded}">
                                   <fmt:message key="auditSetUp.addInputs"/>
                                </span>
                                </c:if>
                            </div>
                        </div>
                    </div>
                            </c:forEach>
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
                        <legend class="parameters-legend"><fmt:message key="${entry.key}"/></legend>
                        <c:set var="fieldsetInfo" scope="page">
                            <spring:message code="${entry.key}-info"/>
                        </c:set>
                        <c:if test="${fieldsetInfo != ''}">
                            <p class="fieldset-info">${fieldsetInfo}</p>
                        </c:if>
                        <c:forEach var="parameter" items="${entry.value}" >
                        <c:set var="i18nKey" scope="page" value="${parameter.formField.i18nKey}"/>
                        <c:set var="code" scope="page" value="${parameter.parameterElement.parameterElementCode}"/>
                        <div class="yui3-g">
                            <div class="yui3-u-1-4">
                                <div class="set-up-label">
                                    <label id="set-up-${i18nKey}" for="${i18nKey}"><fmt:message key="${i18nKey}"/></label>
                                </div>
                            </div>
                            <div class="yui3-u-3-4">
                                <div class="set-up-value">
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
                                    <form:input id="${i18nKey}" path="auditParameter[${code}]" cssErrorClass="errorfield"/>
                                    <form:errors path="auditParameter[${code}]" cssClass="error" /><br/>
                                    <span class="set-up-rule">
                                    <c:choose>
                                        <c:when test="${function:instanceOf(parameter.formField, 'com.opens.tgol.form.NumericalFormField')}">
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
                        </div>
                        </c:forEach>
                    </fieldset>
                    </c:forEach>
                    <div class="yui3-g" id="audit-set-up-submit">
                        <div class="yui3-u-3-4"></div>
                        <div class="yui3-u-1-4">
                            <div id="audit-set-up-submit-button">
                                <input id="input-submit" type="submit" value="<fmt:message key="auditSetUp.launchAudit"/>"  class="awesome large orange"/>
                            </div>
                        </div>
                    </div>
                </form:form>