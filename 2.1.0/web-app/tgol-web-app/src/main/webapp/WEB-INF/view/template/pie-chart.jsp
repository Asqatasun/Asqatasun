<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
                        <c:if test="${addSpan == 'true'}">
                            <div class="span4">
                        </c:if>
                            <table summary="<fmt:message key="graph.resultRepartitionSummaryAndCaption"/>" id="result-synthetized-text">
                                <caption><fmt:message key="graph.resultRepartitionSummaryAndCaption"/></caption>
                                <tr>
                                    <th class="col01 passed" scope="row"><fmt:message key="passed"/></th>
                                    <td class="col02">${counter.passedCount}</td>
                                </tr>
                                <tr>
                                    <th class="col01 failed" scope="row"><fmt:message key="failed"/></th>
                                    <td class="col02">${counter.failedCount}</td>
                                </tr>
                                <tr>
                                    <th class="col01 nmi" scope="row"><fmt:message key="nmi"/></th>
                                    <td class="col02">${counter.nmiCount}</td>
                                </tr>
                                <tr>
                                    <th class="col01 na" scope="row"><fmt:message key="na"/></th>
                                    <td class="col02">${counter.naCount}</td>
                                </tr>
                            </table>
                        <c:if test="${addSpan == 'true'}">
                        </div>
                        </c:if>
                        