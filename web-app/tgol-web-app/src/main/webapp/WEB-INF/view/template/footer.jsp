<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

        <footer>
            <div id="footer-down">
                &copy; 2009-2011 <a href="http://www.Open-S.com/">Open-S</a> - <fmt:message key="footer.poweredBy"/> <a href="http://www.tanaguru.org/">Tanaguru</a> - <fmt:message key="footer.availableUnder"/> <a href="http://www.gnu.org/licenses/agpl-3.0.html">GNU AGPL v3</a>
            </div>
        </footer>
        <c:if test="${not empty configProperties['google-analytics-code']}">
        <script type="text/javascript">
            var _gaq = _gaq || [];
            _gaq.push(['_setAccount', '${configProperties['google-analytics-code']}']);
            _gaq.push(['_trackPageview']);
            (function() {
                var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
                ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
                var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
            })();
        </script>
        </c:if>       