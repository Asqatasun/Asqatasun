<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

        <footer>
            <jsp:useBean id="now" class="java.util.Date" scope="page" />
            <div id="footer-down">                
                
                <fmt:message key="footer.poweredBy"/> 
                <a href="https://gitlab.com/asqatasun/Asqatasun/-/releases">Asqatasun v${asqatasunVersion}</a>
                - 
                <fmt:message key="footer.availableUnder"/> 
                <a href="https://www.gnu.org/licenses/agpl-3.0.html">GNU AGPL v3</a>
                <br><br>
                &copy; 2009-<fmt:formatDate value="${now}" pattern="yyyy" />
                - <a href="https://asqatasun.org/">Asqatasun.org</a>


                <div id="footer-asqatasun-link">
                    <a class="asqatasun-link-svg"
                       href="https://twitter.com/Asqatasun"
                       title="<fmt:message key="footer.link-twitter"/>"
                       aria-label="<fmt:message key="footer.link-twitter"/>">
                        <svg class="icon_twitter"
                             viewBox="0 0 1792 1792"
                             xmlns="http://www.w3.org/2000/svg">
                            <path d="M1684 408q-67 98-162 167 1 14 1 42 0 130-38 259.5t-115.5 248.5-184.5 210.5-258 146-323 54.5q-271 0-496-145 35 4 78 4 225 0 401-138-105-2-188-64.5t-114-159.5q33 5 61 5 43 0 85-11-112-23-185.5-111.5t-73.5-205.5v-4q68 38 146 41-66-44-105-115t-39-154q0-88 44-163 121 149 294.5 238.5t371.5 99.5q-8-38-8-74 0-134 94.5-228.5t228.5-94.5q140 0 236 102 109-21 205-78-37 115-142 178 93-10 186-50z"/>
                        </svg>
                    </a>
                    <a class="asqatasun-link-svg"
                       href="http://forum.asqatasun.org/"
                       title="<fmt:message key="footer.link-forum"/>"
                       aria-label="<fmt:message key="footer.link-forum"/>">
                        <svg    class="icon_forum"
                                viewBox="0 0 1792 1792"
                                xmlns="http://www.w3.org/2000/svg">
                            <path d="M704 384q-153 0-286 52t-211.5 141-78.5 191q0 82 53 158t149 132l97 56-35 84q34-20 62-39l44-31 53 10q78 14 153 14 153 0 286-52t211.5-141 78.5-191-78.5-191-211.5-141-286-52zm0-128q191 0 353.5 68.5t256.5 186.5 94 257-94 257-256.5 186.5-353.5 68.5q-86 0-176-16-124 88-278 128-36 9-86 16h-3q-11 0-20.5-8t-11.5-21q-1-3-1-6.5t.5-6.5 2-6l2.5-5 3.5-5.5 4-5 4.5-5 4-4.5q5-6 23-25t26-29.5 22.5-29 25-38.5 20.5-44q-124-72-195-177t-71-224q0-139 94-257t256.5-186.5 353.5-68.5zm822 1169q10 24 20.5 44t25 38.5 22.5 29 26 29.5 23 25q1 1 4 4.5t4.5 5 4 5 3.5 5.5l2.5 5 2 6 .5 6.5-1 6.5q-3 14-13 22t-22 7q-50-7-86-16-154-40-278-128-90 16-176 16-271 0-472-132 58 4 88 4 161 0 309-45t264-129q125-92 192-212t67-254q0-77-23-152 129 71 204 178t75 230q0 120-71 224.5t-195 176.5z"/>
                        </svg>
                    </a>
                    <a  class="asqatasun-link-svg"
                        href="https://doc.asqatasun.org/"
                        title="<fmt:message key="footer.link-doc"/>"
                        aria-label="<fmt:message key="footer.link-doc"/>">
                        <svg    class="icon_doc"
                                viewBox="0 0 1792 1792"
                                xmlns="http://www.w3.org/2000/svg">
                            <path d="M1703 478q40 57 18 129l-275 906q-19 64-76.5 107.5t-122.5 43.5h-923q-77 0-148.5-53.5t-99.5-131.5q-24-67-2-127 0-4 3-27t4-37q1-8-3-21.5t-3-19.5q2-11 8-21t16.5-23.5 16.5-23.5q23-38 45-91.5t30-91.5q3-10 .5-30t-.5-28q3-11 17-28t17-23q21-36 42-92t25-90q1-9-2.5-32t.5-28q4-13 22-30.5t22-22.5q19-26 42.5-84.5t27.5-96.5q1-8-3-25.5t-2-26.5q2-8 9-18t18-23 17-21q8-12 16.5-30.5t15-35 16-36 19.5-32 26.5-23.5 36-11.5 47.5 5.5l-1 3q38-9 51-9h761q74 0 114 56t18 130l-274 906q-36 119-71.5 153.5t-128.5 34.5h-869q-27 0-38 15-11 16-1 43 24 70 144 70h923q29 0 56-15.5t35-41.5l300-987q7-22 5-57 38 15 59 43zm-1064 2q-4 13 2 22.5t20 9.5h608q13 0 25.5-9.5t16.5-22.5l21-64q4-13-2-22.5t-20-9.5h-608q-13 0-25.5 9.5t-16.5 22.5zm-83 256q-4 13 2 22.5t20 9.5h608q13 0 25.5-9.5t16.5-22.5l21-64q4-13-2-22.5t-20-9.5h-608q-13 0-25.5 9.5t-16.5 22.5z"/>
                        </svg>
                    </a>
                    <a  class="asqatasun-link-svg"
                        href="https://gitlab.com/asqatasun/Asqatasun"
                        title="<fmt:message key="footer.link-git"/>"
                        aria-label="<fmt:message key="footer.link-git"/>">
                        <svg    class="icon_git"
                                viewBox="0 0 1792 1792"
                                xmlns="http://www.w3.org/2000/svg">
                            <path d="M672 1472q0-40-28-68t-68-28-68 28-28 68 28 68 68 28 68-28 28-68zm0-1152q0-40-28-68t-68-28-68 28-28 68 28 68 68 28 68-28 28-68zm640 128q0-40-28-68t-68-28-68 28-28 68 28 68 68 28 68-28 28-68zm96 0q0 52-26 96.5t-70 69.5q-2 287-226 414-68 38-203 81-128 40-169.5 71t-41.5 100v26q44 25 70 69.5t26 96.5q0 80-56 136t-136 56-136-56-56-136q0-52 26-96.5t70-69.5v-820q-44-25-70-69.5t-26-96.5q0-80 56-136t136-56 136 56 56 136q0 52-26 96.5t-70 69.5v497q54-26 154-57 55-17 87.5-29.5t70.5-31 59-39.5 40.5-51 28-69.5 8.5-91.5q-44-25-70-69.5t-26-96.5q0-80 56-136t136-56 136 56 56 136z"/>
                        </svg>
                    </a>
                </div>
            </div>
        </footer>
        <%@include file="tracker.jsp" %>
