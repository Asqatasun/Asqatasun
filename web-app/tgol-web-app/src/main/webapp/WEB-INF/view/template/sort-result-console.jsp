<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="function" uri="http://tagutils"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <div class="row">
                <div class="span14 offset1">
                    <form:form modelAttribute="auditResultSortCommand" method="post" acceptCharset="UTF-8" enctype="application/x-www-form-urlencoded" class="option-console">
                        <form:hidden path="webResourceId"/>
                        <div class="row">
                            <div class="span6">
                                <fieldset>
                                    <legend><fmt:message key="result.display"/></legend>
                                    <c:forEach var="formField" items="${resultSortFormField}" >
                                    <c:set var="i18nKey" scope="page" value="${formField.i18nKey}"/>
                                    <div class="clearfix ${auditParameterErrorClass}">
                                        <label id="set-up-${i18nKey}" for="${i18nKey}"><fmt:message key="${i18nKey}"/></label>
                                        <div class="set-up-value input">
                                        <c:choose>
                                            <c:when test="${function:instanceOf(formField, 'org.opens.tgol.form.SelectFormField')}">
                                                <c:forEach items="${formField.selectElementMap}" var="group">
                                                <form:select id="${i18nKey}" path="sortOptionMap[${group.key}]">
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
                                                    <option value="${level.value}" ${selected} ${disabled}><fmt:message key="${level.i18nKey}"/></option>
                                                    </c:forEach><!-- for each element of a referentiel -->
                                                    </form:select>
                                                </c:forEach><!-- for each referentiel -->
                                            </c:when>
                                            <c:otherwise>
                                                <form:input id="${i18nKey}" path="sortOptionMap[${code}]" cssErrorClass="xlarge error" cssClass="xlarge"/>
                                                <form:errors path="sortOptionMap[${code}]" cssClass="alert-message error" /><br/>
                                            </c:otherwise>
                                        </c:choose><!-- test="instanceof SelectFormField"-->
                                        </div>
                                    </div>
                                    </c:forEach>
                                    <div id="display-results-options-update" class="actions">
                                        <input type="submit" class="btn" value="<fmt:message key="pageList.update"/>"/>
                                    </div> <!-- class="actions"-->
                                </fieldset>
                            </div>
                            <c:if test="${resultActionList != null && not empty resultActionList}">
                            <div class="span6 offset1">
                                <fieldset>
                                    <legend><fmt:message key="result.export"/></legend>
                                    <div id="audit-result-menu-actions" class="clearfix">
                                        <c:forEach var="action" items="${resultActionList}" varStatus="pAction">
                                            <c:choose>
                                                <c:when test="${action.isActionEnabled == 'false'}">
                                        <a href="<c:url value="${action.actionUrl}&amp;wr=${param.wr}"/>" class="btn disabled primary" title="<fmt:message key="${action.ActionAltI81NCode}"/>">
                                            <fmt:message key="${action.actionI81NCode}"/>
                                        </a>
                                                </c:when>
                                                <c:otherwise>
                                                <!-- the url actions already have a parameter (export type for example-->
                                        <a href="<c:url value="${action.actionUrl}&amp;wr=${param.wr}"/>" class="btn primary" title="<fmt:message key="${action.ActionAltI81NCode}"/>">
                                            <fmt:message key="${action.actionI81NCode}"/>
                                        </a>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </div><!--  id="audit-result-actions" -->
                                </fieldset>
                            </div>
                            </c:if>
                        </div>
                    </form:form>
                </div><!-- class=span16"-->
            </div><!-- class="row"-->