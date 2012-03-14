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
            <c:choose>
                <c:when test="${not empty configProperties['cdnUrl']}">
                    <c:set var="tgLogoUrl" value="${configProperties['cdnUrl']}/Images/Logo-Tanaguru.com-75dpi-210x95-transp.png"/>
                    <c:set var="enFlagUrl"value="${configProperties['cdnUrl']}/Images/en-flag2.jpeg"/>
                    <c:set var="frFlagUrl" value="${configProperties['cdnUrl']}/Images/fr-flag2.jpeg"/>
                </c:when>
                <c:otherwise>
                    <c:set var="tgLogoUrl">
                        <c:url value="/Images/Logo-Tanaguru.com-75dpi-210x95-transp.png"/>  
                    </c:set>
                    <c:set var="enFlagUrl">
                        <c:url value="/Images/en-flag2.jpeg"/>
                    </c:set>
                    <c:set var="frFlagUrl">
                        <c:url value="/Images/fr-flag2.jpeg"/>
                    </c:set>
                </c:otherwise>
            </c:choose>
            <c:if test="${isInNotAuthentifiedView == 'true'}">
            <div id="lang-box" class="row">
                <c:set var="offset" value="12"/>
                <c:if test="${addLogo == 'true'}">
                <div id="logo-box" class="span4">
                    <a href="<c:url value="/dispatch.html"/>" title="<fmt:message key="home.home"/>">
                        <img src="${tgLogoUrl}" alt="" />
                    </a>
                </div>
                <c:set var="offset" value="8"/>
                </c:if>
                <div class="span4 offset${offset}">
            </c:if>
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
                    <c:if test="${isInTopBar == 'true'}">
                        <li class="lang-flag">
                    </c:if>
                            <a href="<%out.print(strBuffer.toString());%>lang=en" title="Switch to english" lang="en">
                                <img src="${enFlagUrl}" alt="Switch to english" />
                            </a>
                    <c:if test="${isInTopBar == 'true'}">
                        </li>
                        <li class="lang-flag">
                    </c:if>
                            <a href="<%out.print(strBuffer.toString());%>lang=fr" title="Passer en français" lang="fr">
                                <img src="${frFlagUrl}" alt="Passer en français" />
                            </a>
                    <c:if test="${isInTopBar == 'true'}">
                        </li>
                    </c:if>
            <c:if test="${isInNotAuthentifiedView == 'true'}">
                </div><!--class="span4 offset12"-->
            </div> <!-- class="row"-->
            </c:if>