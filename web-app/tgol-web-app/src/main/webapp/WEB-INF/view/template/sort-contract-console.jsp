<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://tagutils" prefix="tg" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div id="contract-option-console" class="row option-console">
    <div class="span16">
        <div id="contract-option-console-title" class="option-console-title">
            <fmt:message key="result.display"/>
        </div>
    </div>
    <div class="span16">
        <form:form modelAttribute="contractSortCommand" method="post" acceptCharset="UTF-8" enctype="application/x-www-form-urlencoded" class="form-stacked">
            <form:hidden path="userId"/>
            <c:forEach var="formField" items="${contractSortFormField}" >
            <c:set var="i18nKey" scope="page" value="${formField.i18nKey}"/>
            <div class="clearfix ${auditParameterErrorClass}">
                <label id="set-up-${i18nKey}" for="${i18nKey}"><fmt:message key="${i18nKey}"/></label>
                <div class="set-up-value input">
                <c:choose>
                    <c:when test="${tg:instanceOf(formField, 'org.tanaguru.webapp.form.SelectFormField')}">
                        <c:forEach items="${formField.selectElementMap}" var="group">
                        <form:select id="${i18nKey}" path="sortOptionMap[${group.key}]">
                            <c:forEach items="${group.value}" var="level">
                            <c:choose>
                                <c:when test="${level.defaultElement == 'true'}">
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
                        <form:input id="${i18nKey}" path="sortOptionMap[${i18nKey}]" cssErrorClass="medium error" cssClass="medium"/>
                        <form:errors path="sortOptionMap[${i18nKey}]" cssClass="alert-message error" /><br/>
                    </c:otherwise>
                </c:choose><!-- test="instanceof SelectFormField"-->
                </div>
            </div>
            </c:forEach>
            <div id="contract-option-console-update" class="actions option-console-update">
                <input type="submit" class="update-action" value="<fmt:message key="pageList.update"/>"/>
            </div> <!-- class="actions"-->
        </form:form>
    </div>
</div><!-- class="row"-->