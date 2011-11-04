<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

                        <tr>
                            <th scope="col" class="col01"><fmt:message key="resultPage.test"/></th>
                            <th scope="col" class="col02"><fmt:message key="resultPage.result"/></th>
                            <th scope="col" class="col03"><fmt:message key="resultPage.remark"/></th>
                        </tr>
                        <c:forEach var="testResult" items="${testResultList}">
                        <tr>
                            <td class="col01"><a href="<c:out value="${testResult.testDescription}"/>"> <c:out value="${testResult.testLabel}"/> </a></td>
                            <td class="col02 <c:out value="${testResult.resultToLowerCase}"/> "> <c:out value="${testResult.result}"/> </td>
                            <td class="col03">
                                <p class="process-remarks">
                                <c:forEach var="item" items="${testResult.remarkInfosMap}">
                                    <strong><c:out value="${item.key}"/></strong></p>
                                    <ul class="process-remarks">
                                    <c:forEach var="value" items="${item.value}">
                                        <li><fmt:message key="resultPage.element"/>
                                            <code><c:out value="${value.target}"/></code>
                                            <fmt:message key="resultPage.line"/>
                                            <c:out value="${value.line}"/>
                                            <fmt:message key="resultPage.characterPosition"/>
                                            <c:out value="${value.characterPosition}"/>
                                        </li>
                                    </c:forEach>
                                    </ul>
                                </c:forEach>
                            </td>
                        </tr>
                        </c:forEach>                       
