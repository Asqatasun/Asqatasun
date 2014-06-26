<@page contentType="text/html;charset=UTF-8">
<@page pageEncoding="UTF-8">
<@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" >
<@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c">
                        <c:if test="${addSpan == 'true'}">
                            <div class="span4">
                        </c:if>
                            <div id="result-repartition-pie-graph"></div>
                            <c:set var="totalNumber" scope="page">
                                ${counter.passedCount + counter.failedCount + counter.nmiCount + counter.naCount}
                            </c:set>
                        <c:if test="${totalNumber == 0}">
                            <c:set var="totalNumber" scope="page" value="1"/>
                        </c:if>
                            <div>
                                <table id="result-synthetized-text">
                                    <tr>
                                        <td class="col01 passed" ></td>
                                        <td class="col02" ><fmt:message key="passed"/></td>
                                        <td class="col03">${counter.passedCount}</td>
                                    </tr>
                                    <tr>
                                        <td class="col01"></td>
                                        <td colspan="2">
                                            <span class="fd-slider-wrapper">
                                                <c:set var="passedPercent" scope="page">
                                                    ${(counter.passedCount / totalNumber) * 100 }
                                                </c:set>
                                                <c:set var="passedLeftOffset" scope="page">
                                                    ${100 - passedPercent}
                                                </c:set>
                                                <span class="fd-slider-range passed" style="left: ${passedLeftOffset}%; width: ${passedPercent}%;"></span>
                                                <span class="fd-slider-bar"></span>
                                            </span>
                                        </td>
                                    </tr>   
                                    <tr>
                                        <td class="col01 failed"></td>
                                        <td class="col02" ><fmt:message key="failed"/></td>
                                        <td class="col03">${counter.failedCount}</td>
                                    </tr>
                                    <tr>
                                        <td class="col01"></td>
                                        <td colspan="2">
                                            <span class="fd-slider-wrapper">
                                                <c:set var="failedPercent" scope="page">
                                                    ${(counter.failedCount / totalNumber) * 100 }
                                                </c:set>
                                                <c:set var="failedLeftOffset" scope="page">
                                                    ${100 - failedPercent }
                                                </c:set>
                                                <span class="fd-slider-range failed" style="left: ${failedLeftOffset}%; width: ${failedPercent}%;"></span>
                                                <span class="fd-slider-bar"></span>
                                            </span>
                                        </td>
                                    </tr>   
                                    <tr>
                                        <td class="col01 na"></td>
                                        <td class="col02" ><fmt:message key="na"/></td>
                                        <td class="col03">${counter.naCount}</td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td colspan="2">
                                            <span class="fd-slider-wrapper">
                                                <c:set var="naPercent" scope="page">
                                                    ${(counter.naCount / totalNumber) * 100 }
                                                </c:set>
                                                <c:set var="naLeftOffset" scope="page">
                                                    ${100 - naPercent}
                                                </c:set>
                                                <span class="fd-slider-range na" style="left: ${naLeftOffset}%; width: ${naPercent}%;"></span>
                                                <span class="fd-slider-bar"></span>
                                            </span>
                                        </td>
                                    </tr>                                    
                                    <tr>
                                        <td class="col01 nmi"></td>
                                        <td class="col02"><fmt:message key="nmi"/></td>
                                        <td class="col03">${counter.nmiCount}</td>
                                        
                                    </tr>
                                    <tr>
                                        <td class="col01"></td>
                                        <td colspan="2">
                                            <span class="fd-slider-wrapper">
                                                <c:set var="nmiPercent" scope="page">
                                                    ${(counter.nmiCount / totalNumber) * 100 }
                                                </c:set>
                                                <c:set var="nmiLeftOffset" scope="page">
                                                    ${100 - nmiPercent}
                                                </c:set>
                                                <span class="fd-slider-range nmi" style="left: ${nmiLeftOffset}%; width: ${nmiPercent}%;"></span>
                                                <span class="fd-slider-bar"></span>
                                            </span>
                                        </td>
                                    </tr>   
                                    <tr>
                                        <td class="col01 nt"></td>
                                        <td class="col02"><fmt:message key="nt"/></td>
                                        <td class="col03">${counter.ntCount}</td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td colspan="2">
                                            <span class="fd-slider-wrapper">
                                                <span class="fd-slider-range na" style="width: 0"></span>
                                                <span class="fd-slider-bar"></span>
                                            </span>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        <c:if test="${addSpan == 'true'}">
                        </div>
                        </c:if>

