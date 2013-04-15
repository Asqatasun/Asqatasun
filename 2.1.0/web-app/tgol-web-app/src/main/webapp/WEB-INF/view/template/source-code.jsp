<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

            <h2 id="source-code"><fmt:message key="resultPage.sourceCode"/></h2>
            
            <c:forEach var="testResult" items="${sourceCode}">
                ${testResult}
            </c:forEach>