<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://tagutils" prefix="tg" %>
<!DOCTYPE html>
<html lang="${tg:lang(pageContext)}">
    <c:set var="pageTitle" scope="page">
        <spring:message code="account-settings.pageTitle"/>
        <spring:hasBindErrors name="createUserCommand">
                <spring:message code="account-settings.errorPageTitle"/>
        </spring:hasBindErrors>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="tgm-account-settings">
        <c:set var="accountSettingsActive" value="true" scope="page"/>
        <%@include file="template/header-utils.jsp" %>
        <div class="container">
            
            <c:if test="${not empty modifiableTestWeightRefs}">
            <div id="navSecondaryLevel">
                <ul class="pills">
                    <li class="active">
                        <a href="<c:url value="/account-settings.html"/>">
                            <fmt:message key="account-settings.accountSettings"/>
                        </a>
                    </li>
                    <c:forEach items="${modifiableTestWeightRefs}" var="ref">
                    <li>
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
                <fmt:message key="account-settings.h1"/>
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
                <c:if test="${updatedUserName != null}">
                <div class="span16">
                    <div class="alert-message block-message success">
                        <p>
                        <fmt:message key="admin.updatedUserPositiveMsg">
                            <fmt:param>${updatedUserName}</fmt:param>
                        </fmt:message>
                        </p>
                    </div>
                </div>
                </c:if>
                <c:if test="${createUserAttack == 'true'}">
                <div class="span16">
                    <div class="alert-message block-message warning">
                        <p>
                        <fmt:message key="admin.createUserAttack"/>
                        </p>
                    </div>
                </div>
                </c:if>              
                <c:set var="addActivatedField" scope="request" value="false"/>
                <c:set var="addAdminField" scope="request" value="false"/>
                <c:set var="activateEmailField" scope="request" value="false"/>
                <c:set var="currentUserId" scope="request">
                    <sec:authentication property="principal.user.id" />
                </c:set>
                <c:set var="changePasswordUrl" scope="request" value="/change-password.html?user=${currentUserId}&amp;token=authenticated"/>
                <%@include file="template/user-info-form.jsp" %>
            </div><!-- class="row" -->
        </div><!-- class="container" -->
        <%@include file="template/footer.jsp" %>
    </body>
</html>
</compress:html>