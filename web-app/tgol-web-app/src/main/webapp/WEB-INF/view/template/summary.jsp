<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

            <div class="yui3-g">
                <div class="yui3-u-1">
                    <h1>
                        <fmt:message key="resultPage.title"/>
                    </h1>
                </div>
            </div>

            <div class="yui3-g">
                <div class="yui3-u-1">
                    <ol>
                        <li><a href="#synthesis"><fmt:message key="resultPage.synthesis"/></a></li>
                        <c:choose>
                            <c:when test="${scope == 'page'}">
                        <li><a href="#work-done"><fmt:message key="resultPage.detailedResultPage"/></a></li>
                        <li><a href="#source-code"><fmt:message key="resultPage.sourceCode"/></a></li>
                            </c:when>
                            <c:when test="${scope == 'site'}">
                        <li><a href="#pageList"><fmt:message key="resultPage.pageList"/></a></li>
                        <c:if test="${testResultMap != null and not empty testResultMap}">
                        <li><a href="#work-done"><fmt:message key="resultPage.detailedResultSite"/></a></li>
                        </c:if>
                        </c:when>
                        </c:choose>
                    </ol>
                </div>
            </div>
