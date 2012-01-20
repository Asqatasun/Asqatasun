<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page import="org.springframework.web.context.request.RequestContextHolder" %>
<%@page import="org.springframework.web.context.request.ServletRequestAttributes" %>
<%@page import="java.util.Collection" %>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Set"%>

        <div class="topbar">
            <div class="fill">
                <div class="container">
                    <a class="brand" href="<c:url value="/index.html"/>"><fmt:message key="top-bar.mainTitle"/></a>
                    <ul class="nav">
                    <c:choose>
                        <c:when test="${categoriesPageSelected == true}">
                        <li class="active"><a href="#"><fmt:message key="top-bar.categories"/></a></li>
                        </c:when>
                        <c:otherwise>
                        <li><a href="<c:url value="/categories.html"/>"><fmt:message key="top-bar.categories"/></a></li>
                        </c:otherwise>
                    </c:choose>
                        <li><a href="#about"><fmt:message key="footer.about"/></a></li>
                    </ul>
                    <ul class="nav secondary-nav">
                    <%
                        // SORRRRRYYY !!!!!!!!!!!!!!!!
                        StringBuffer strBuffer = new StringBuffer();
                        HttpServletRequest servletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
                        strBuffer.append("?");
                        Set set = servletRequest.getParameterMap().entrySet();
                        // Get an iterator
                        Iterator i = set.iterator();
                        while(i.hasNext()) {
                            Map.Entry me = (Map.Entry)i.next();
                            if (!me.getKey().toString().equalsIgnoreCase("lang")) {
                                strBuffer.append(me.getKey());
                                strBuffer.append("=");
                                strBuffer.append(((String[])me.getValue())[0]);
                                strBuffer.append("&amp;");
                            }
                        }
                    %>
                        <li>
                            <a href="<%out.print(strBuffer.toString());%>lang=en" title="Switch to english" lang="en">
                                <img src="<c:url value="/Images/en-flag2.jpeg"/>" alt="Switch to english" />
                            </a>
                        </li>
                        <li>
                            <a href="<%out.print(strBuffer.toString());%>lang=fr" title="Passer en français" lang="fr">
                                <img src="<c:url value="/Images/fr-flag2.jpeg"/>" alt="Passer en français" />
                            </a>
                        </li>
                    </ul>
                </div><!-- class="container" -->
            </div><!-- class="fill" -->
        </div><!-- class="topbar" -->