<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<table>
    <caption><fmt:message key="graph.resultRepartitionSummaryAndCaption"/></caption>
    <tr>
        <th class="col01 passed" scope="row">
            Passed
        </th>
        <td class="col02">
            ${counter.passedCount}
        </td>
    </tr>
    <tr>
        <th class="col01 failed" scope="row">
            Failed
        </th>
        <td class="col02">
            ${counter.failedCount}
        </td>
    </tr>
    <tr>
        <th class="col01 nmi" scope="row">
            <abbr title="Need More Information">NMI</abbr>
        </th>
        <td class="col02">${counter.nmiCount}</td>
    </tr>
    <tr>
        <th class="col01 na" scope="row">
            <abbr title="Not Applicable">NA</abbr>
        </th>
        <td class="col02">${counter.naCount}</td>
    </tr>
</table>