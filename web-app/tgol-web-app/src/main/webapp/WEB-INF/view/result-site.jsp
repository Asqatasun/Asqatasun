<%@taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html>
<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<c:choose>
    <c:when test="${fn:contains(pageContext.response.locale, '_')}">
        <c:set var="lang">
            ${fn:substringBefore(pageContext.response.locale, "_")}
        </c:set>
    </c:when>
    <c:otherwise>
        <c:set var="lang" value="${pageContext.response.locale}"/>
    </c:otherwise>
</c:choose>
<c:choose>
    <c:when test="${not empty configProperties['cdnUrl']}">
        <!-- external js -->
        <c:set var="jqueryUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/External-Js/jquery-1.9.1.min.js"/>
        <c:set var="d3JsUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/External-Js/d3.v3.min.js" scope="request"/>            
        <c:set var="r2d3JsUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/External-Js/r2d3.v2.min.js" scope="request"/>
        <c:set var="codePrettifierJsUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/External-Js/prettify.min.js" scope="request"/>

        <!-- internal js -->
        <c:set var="prettyPrintJsUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Js/result-page/pretty-print-min.js" scope="request"/>
        <c:set var="testDetailsJsUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Js/expand-collapse/test-details-min.js" scope="page"/>
        <c:set var="themeDetailsJsUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Js/expand-collapse/theme-details-min.js" scope="page"/>
        <c:set var="auditParametersDetailsJsUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Js/expand-collapse/audit-parameters-details-min.js" scope="page"/>
        <c:set var="scoreJsUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Js/score/score-min.js" scope="request"/>
        <c:set var="siteScopeScoreJsUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Js/score/score-page-list-min.js" scope="request"/>
        <c:set var="scoreIEJsUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Js/ie/score/score-ie-min.js" scope="request"/>
        <c:set var="siteScopeScoreIEJsUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Js/ie/score/score-page-list-ie-min.js" scope="request"/>

        <!-- external images -->
        <c:set var="testInfoLinkImg" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Images/test-info-link.png" scope="request"/>
        <c:set var="algoLinkImg" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Images/algo-link.png" scope="request"/>
        <c:set var="expandedSmallImg" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Images/expanded-s.png" scope="request"/>
        <c:set var="expandedImg" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Images/expanded.png" scope="request"/>
        <c:set var="collapsedImg" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Images/collapsed.png" scope="request"/>
        <c:set var="collapsedSmallImg" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Images/collapsed-s.png" scope="request"/>
        <c:set var="sourceCodeImg" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Images/html-source-icon.png" scope="request"/>
    </c:when>
    <c:otherwise>
        <!-- external js -->
        <c:set var="jqueryUrl">
            <c:url value="/External-Js/jquery-1.9.1.min.js"/>
        </c:set>        
        <c:set var="d3JsUrl" scope="request">
            <c:url value="/External-Js/d3.v3.min.js"/>
        </c:set> 
        <c:set var="codePrettifierJsUrl" scope="request">
            <c:url value="/External-Js/prettify.min.js"/>
        </c:set> 
        <c:set var="r2d3JsUrl" scope="request">
            <c:url value="/External-Js/r2d3.v2.min.js"/>
        </c:set> 

        <!-- internal js -->
        <c:set var="resultPageChartsIEJsUrl" scope="request">
            <c:url value="/Js/ie/result-page/result-page-charts-ie-min.js"/>
        </c:set>
        <c:set var="prettyPrintJsUrl" scope="request">
            <c:url value="/Js/result-page/pretty-print-min.js"/>
        </c:set> 
        <c:set var="scoreJsUrl" scope="request">
            <c:url value="/Js/score/score-min.js"/>
        </c:set>
        <c:set var="siteScopeScoreJsUrl" scope="request">
            <c:url value="/Js/score/score-page-list-min.js"/>
        </c:set>
        <c:set var="scoreIEJsUrl" scope="request">
            <c:url value="/Js/ie/score/score-ie-min.js"/>
        </c:set>
        <c:set var="siteScopeScoreIEJsUrl" scope="request">
            <c:url value="/Js/ie/score/score-page-list-ie-min.js"/>
        </c:set>
        <c:set var="testDetailsJsUrl" scope="page">
            <c:url value="/Js/expand-collapse/test-details-min.js"/>
        </c:set>
        <c:set var="themeDetailsJsUrl" scope="page">
            <c:url value="/Js/expand-collapse/theme-details-min.js"/>
        </c:set>
        <c:set var="auditParametersDetailsJsUrl" scope="page">
            <c:url value="/Js/expand-collapse/audit-parameters-details-min.js"/>
        </c:set>

        <!-- external images -->
        <c:set var="testInfoLinkImg" scope="request">
            <c:url value="/Images/test-info-link.png"/>
        </c:set> 
        <c:set var="algoLinkImg" scope="request">
            <c:url value="/Images/algo-link.png"/>
        </c:set> 
        <c:set var="expandedImg" scope="request">
            <c:url value="/Images/expanded.png"/>
        </c:set> 
        <c:set var="expandedSmallImg" scope="request">
            <c:url value="/Images/expanded-s.png"/>
        </c:set> 
        <c:set var="collapsedImg" scope="request">
            <c:url value="/Images/collapsed.png"/>
        </c:set> 
        <c:set var="collapsedSmallImg" scope="request">
            <c:url value="/Images/collapsed-s.png"/>
        </c:set> 
        <c:set var="sourceCodeImg" scope="request">
            <c:url value="/Images/html-source-icon.png"/>
        </c:set>
    </c:otherwise>
</c:choose>
<html lang="${lang}">
    <c:set var="pageTitle" scope="page">
        <fmt:message key="resultSite.pageTitle">
            <fmt:param>
                ${statistics.url}
            </fmt:param>
        </fmt:message>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="tgm-result-site">
        <%@include file="template/header-utils.jsp" %>
        <div class="container">
            <c:set var="pageName" scope="page">
                <fmt:message key="resultSite.h1"/>
            </c:set>
            <ul class="breadcrumb">
                <li><a href="<c:url value="/home.html"/>"><fmt:message key="home.h1"/></a> <span class="divider"></span></li>
                <li><a href="<c:url value="/home/contract.html?cr=${cr}"/>">${contractName}</a> <span class="divider"></span></li>
                <c:set var="auditSynthesisName" scope="page">
                    <fmt:message key="synthesisSite.h1">
                        <fmt:param>
                            ${param.wr}
                        </fmt:param>
                    </fmt:message>
                </c:set>
                <li><a href="<c:url value="/home/contract/audit-synthesis.html?audit=${param.audit}"/>">${auditSynthesisName}</a> <span class="divider"></span></li>
                <li class="active">${pageName}</li>
            </ul>
            <div class="row">
                <div class="span16">
                    <h1>
                        ${pageName}
                    </h1>
                </div>
            </div><!-- class="row" -->
            <c:set var="hasBarChartLink" scope="request" value="false"/>
            <c:set var="hasGraphics" scope="request" value="false"/>
            <c:set var="hasSynthesisTitle" scope="request" value="false"/>
            <c:set var="hasPageCounter" scope="request" value="true"/>
            <c:set var="hasPagesListLink" scope="request" value="false"/>
            <c:set var="hasResultDispatchTitle" scope="request" value="false"/>
            <c:import url="template/synthesis.jsp" />
            <c:import url="template/sort-result-console.jsp" />
            <div class="row">
                <div class="span16 separator">
                </div>
            </div><!-- class="row" -->
            <c:set var="displayAlgorithm" scope="request" value="true"/>
            <c:set var="scope" scope="request" value="site"/>
            <c:set var="addThemeHeader" scope="request" value="true"/>
            <c:import url="template/detailed-result.jsp" />
        </div><!-- class="container"-->
        <%@include file="template/footer.jsp" %>
        <script type="text/javascript" src="${jqueryUrl}"></script>
        <script type="text/javascript" src="${testDetailsJsUrl}"></script>
        <script type="text/javascript" src="${themeDetailsJsUrl}"></script>
        <script type="text/javascript" src="${codePrettifierJsUrl}"></script>
        <script type="text/javascript" src="${prettyPrintJsUrl}"></script>
        <script type="text/javascript" src="${auditParametersDetailsJsUrl}"></script>
        <!--[if lte IE 8]>
        <script type="text/javascript" src="${r2d3JsUrl}"></script>
        <script type="text/javascript" src="${scoreIEJsUrl}"></script>
        <script type="text/javascript" src="${siteScopeScoreIEJsUrl}"></script>
        <![endif]-->
        <!--[if gt IE 8]>
        <script type="text/javascript" src="${d3JsUrl}"></script>
        <script type="text/javascript" src="${scoreJsUrl}"></script>
        <script type="text/javascript" src="${siteScopeScoreJsUrl}"></script>
        <![endif]-->
        <!--[if !IE]><!-->
        <script type="text/javascript" src="${d3JsUrl}"></script>
        <script type="text/javascript" src="${scoreJsUrl}"></script>
        <script type="text/javascript" src="${siteScopeScoreJsUrl}"></script>
        <!--<![endif]-->
    </body>
</html>
</compress:html>