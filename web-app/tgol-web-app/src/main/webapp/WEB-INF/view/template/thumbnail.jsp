<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

                <div class="yui3-u-1-${proportion}">
                    <div class="thumbnail ${cml}" >
                        <c:choose>
                            <c:when test="${url != '' && scope != 'GROUPOFFILES' && scope != 'FILE'}">
                        <script type="text/javascript">wsr_snapshot('${url}', '5ozSGth2h9n7', '${size}');</script>
                            </c:when>
                            <c:otherwise>
                        <img src="<c:url value="/Images/Tanaguru-ten-pages-${size}.png"/>" alt="" /><br/>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>