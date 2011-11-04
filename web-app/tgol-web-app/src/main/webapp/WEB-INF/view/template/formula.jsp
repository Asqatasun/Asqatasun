<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

            <p id="score-formula">
                <fmt:message key="resultPage.scoreFormula"/> (1-ratio_nmi) * Passed / (Passed + Failed) + ratio_nmi *  <acronym title="Need More Information">NMI</acronym> / (Passed + Failed + <acronym title="Need More Information">NMI</acronym>)
                <fmt:message key="resultPage.usedWith"/> ${statistics.testCount} tests
                <a href="<fmt:message key="resultPage.scoreFormulaLinkURL"/>"><fmt:message key="resultPage.scoreFormulaLinkText"/></a>
            </p>