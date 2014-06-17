<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://tagutils" prefix="tg" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <div id="result-option-console" class="row option-console">
                <div class="span16">
                    <div id="result-option-console-title" class="option-console-title">
                        <fmt:message key="result.display"/>
                    </div>
                </div>
                <div class="span16">
                    <form:form modelAttribute="auditResultSortCommand" method="post" acceptCharset="UTF-8" enctype="application/x-www-form-urlencoded" class="form-stacked">
                        <form:hidden path="webResourceId"/>
                        <c:if test="${!auditResultSortCommand.displayScopeChoice}">
                            <form:hidden path="displayScope"/>
                        </c:if>
                        <form:hidden path="displayScopeChoice"/>
                        <c:if test="${auditResultSortCommand.displayScopeChoice}">
                        <div class="clearfix ${auditParameterErrorClass}">
                            <label id="set-up-view-type">
                                <fmt:message key="resultPage.view"/>
                            </label>
                            <div id="view-type" class="set-up-value input">
                                <label class="radiobutton" for="displayScope1">
                                    <form:radiobutton value="cr" path="displayScope" />
                                    <fmt:message key="resultPage.viewByCriterions"/>
                                </label>
                                <label class="radiobutton" for="displayScope2">
                                    <form:radiobutton value="tst" path="displayScope" />
                                    <fmt:message key="resultPage.viewByTests"/>
                                </label>
                            </div>
                        </div>
                        </c:if>
                        <c:forEach var="formField" items="${resultSortFormField}" >
                        <c:set var="i18nKey" scope="page" value="${formField.i18nKey}"/>
                        <div class="clearfix ${auditParameterErrorClass}">
                            <c:choose>
                                <c:when test="${tg:instanceOf(formField, 'org.opens.tgol.form.CheckboxFormField')}">
                            <div id="${i18nKey}" class="set-up-value input">
                                <ul class="inputs-list">
                                    <c:forEach items="${formField.checkboxElementList}" var="element" varStatus="pResult">
                                    <li>
                                        <label class="checkbox" for="sortOptionMap${formField.code}${pResult.index + 1}">    
                                            <form:checkbox value="${element.value}" path="sortOptionMap[${formField.code}]" />
                                            <fmt:message key="${element.i18nKey}"/>
                                        </label>
                                    </li>
                                    </c:forEach>
                                </ul>
                                </c:when>
                                <c:when test="${tg:instanceOf(formField, 'org.opens.tgol.form.SelectFormField')}">
                            <label id="set-up-${i18nKey}" for="${i18nKey}"><fmt:message key="${i18nKey}"/></label>                                    
                            <div class="set-up-value input">
                                <c:forEach items="${formField.selectElementMap}" var="group">
                                    <form:select id="${i18nKey}" path="sortOptionMap[${group.key}]">
                                        <c:forEach items="${group.value}" var="level" varStatus="loop">
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
                                            <option value="${level.value}" ${selected} ${disabled}>
                                            <c:choose>
                                                <c:when test="${loop.index != 0}">
                                                    ${loop.index}. <fmt:message key="${level.i18nKey}"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <fmt:message key="${level.i18nKey}"/>
                                                </c:otherwise>    
                                            </c:choose>
                                            </option>
                                        </c:forEach><!-- for each element of a referentiel -->
                                    </form:select>
                                </c:forEach><!-- for each referentiel -->
                                </c:when>
                                <c:otherwise>
                            <div class="set-up-value input"> 
                               <form:input id="${i18nKey}" path="sortOptionMap[${code}]" cssErrorClass="xlarge error" cssClass="xlarge"/>
                                <form:errors path="sortOptionMap[${code}]" cssClass="alert-message error" /><br/>
                                </c:otherwise>
                            </c:choose><!-- test="instanceof SelectFormField"-->
                            </div> <!-- unique div that closes the open div of the choose clause-->
                        </div>
                        </c:forEach>
                        <div id="result-option-console-update" class="actions option-console-update">
                            <input type="submit" class="update-action" value="<fmt:message key="pageList.update"/>"/>
                        </div> <!-- class="actions"-->
                    </form:form>
                </div>
            </div>
