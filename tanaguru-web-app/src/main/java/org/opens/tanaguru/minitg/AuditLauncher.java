/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.minitg;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.AuditStatus;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.audit.SourceCodeRemark;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.dao.reference.TestDAO;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.service.AuditService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author ADEX
 */
public class AuditLauncher extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    synchronized protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            ApplicationContext springApplicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
            BeanFactory springBeanFactory = springApplicationContext;
            AuditService auditService = (AuditService) springBeanFactory.getBean("auditService");
            TestDAO testDAO = (TestDAO) springBeanFactory.getBean("testDAO");// TODO Use TestDataService

            List<Test> testList = (List<Test>) testDAO.findAll();
            String[] testCodeList = new String[testList.size()];
            for (int i = 0; i < testCodeList.length; i++) {
                testCodeList[i] = testList.get(i).getCode();
            }

            String siteURL = null;
            String[] pageURLList = new String[]{request.getParameter("pageUrl")};

            Audit audit = null;
            if (siteURL != null) {
                audit = auditService.auditSite(siteURL, (String[]) testCodeList);
            } else {
                audit = auditService.auditPage(pageURLList[0], testCodeList);
            }

            String resourceUrl = audit.getSubject().getURL();

            if (audit.getStatus().equals(AuditStatus.ERROR)) {
                boolean hasContent = false;
                for (Content content : audit.getContentList()) {
                    if (content instanceof SSP) {
                        //We check that some content has been downloaded and has to
                        //adapter. For the moment we ignore the return error code @TODO
                        if (!((SSP) content).getSource().isEmpty()) {
                            hasContent = true;
                            break;
                        }
                    }
                }

                if (!hasContent) {
                    out.println(returnPageLoadingProblem(resourceUrl));
                    return;
                }

                if (audit.getGrossResultList().isEmpty()) {
                    out.println(returnPageProcessingProblem(resourceUrl));
                    return;
                }

            }
            StringBuilder myHtml = new StringBuilder();
            // define meta-values for audit
            //String dateFormat = "yyyy-MM-dd HH:mm:ss";
            String dateFormat = "yyyy-MM-dd";
            Calendar calendar = GregorianCalendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            String myAuditDate = sdf.format(calendar.getTime());
            Float myAuditMark = audit.getMark();

            // Count nb of each type of result for synthetised table
            int passedCount = 0;
            int failedCount = 0;
            int nmiCount = 0;
            int naCount = 0;
            for (ProcessResult processResult : audit.getNetResultList()) {
                TestSolution result = (TestSolution) processResult.getValue();
                switch (result) {
                    case PASSED:
                        passedCount++;
                        break;
                    case FAILED:
                        failedCount++;
                        break;
                    case NEED_MORE_INFO:
                        nmiCount++;
                        break;
                    case NOT_APPLICABLE:
                        naCount++;
                        break;
                }
            }

            // Sorts net result list by test rank
            Collections.sort(audit.getNetResultList(), new Comparator<ProcessResult>() {

                @Override
                public int compare(ProcessResult o1, ProcessResult o2) {
                    return String.CASE_INSENSITIVE_ORDER.compare(
                            o1.getTest().getCode(),
                            o2.getTest().getCode());
                }
            });

            myHtml.append(getResultPageHeader(resourceUrl));

            myHtml.append("    <body id=\"audit-result\">\n");
            myHtml.append("    <div id=\"doc2\">\n");
            myHtml.append("        <div id=\"hd\">\n");
            myHtml.append("            <h1>Result of accessibility audit</h1>\n");
            myHtml.append("        </div>\n");
            myHtml.append("\n");
            myHtml.append("         <ol>\n");
            myHtml.append("             <li><a href=\"#synthesis\">Synthesis</a></li>\n");
            myHtml.append("             <li><a href=\"#work-done\">Detailed result (what's been checked)</a></li>\n");
            myHtml.append("             <li><a href=\"#work-todo\">Remaing points to check</a></li>\n");
            myHtml.append("         </ol>\n");
            myHtml.append("\n");
            myHtml.append("         <div id=\"bd\">\n");
            myHtml.append("            <h2 id=\"synthesis\">Synthesis</h2>\n");
            myHtml.append("\n");

            // audit meta-data
            myHtml.append("            <table summary=\"Audit meta-data\" id=\"result-meta\">\n");
            myHtml.append("                <caption>Audit meta-data</caption>\n");
            myHtml.append("                <tr>\n");
            myHtml.append("                    <th id=\"meta-score\" scope=\"row\" class=\"col01\">Score <sup><a href=\"#score-formula\" title=\"Note 1: score formula detail\">1</a></sup> :</th>\n");
            myHtml.append("                    <td id=\"meta-score-data\" class=\"col02\"><span>" + myAuditMark.intValue() + "%</span></td>\n");
            myHtml.append("                </tr>\n");
            myHtml.append("                <tr>\n");
            myHtml.append("                    <th id=\"meta-url\" scope=\"row\" class=\"col01\">URL:</th>\n");
            myHtml.append("                    <td class=\"col02\"><a href=\"" + resourceUrl + "\">" + resourceUrl + "</a></td>\n");
            myHtml.append("                </tr>\n");
            myHtml.append("                <tr>\n");
            myHtml.append("                    <th id=\"meta-date\" scope=\"row\" class=\"col01\">Date:</th>\n");
            myHtml.append("                    <td class=\"col02\">" + myAuditDate + "</td>\n");
            myHtml.append("                </tr>\n");
            myHtml.append("            </table>\n");
            myHtml.append("\n");

            // synthetised results
            myHtml.append("            <table summary=\"Synthetised result of accessibility audit\" id=\"result-synthetised\">\n");
            myHtml.append("                <caption>Synthetised result of accessibility audit</caption>\n");
            myHtml.append("                <tr>\n");
            myHtml.append("                    <th class=\"col01 passed\" scope=\"row\">Passed</th>\n");
            myHtml.append("                    <td class=\"col02\">" + passedCount + "</td>\n");
            myHtml.append("                </tr>\n");
            myHtml.append("                <tr>\n");
            myHtml.append("                    <th class=\"col01 failed\" scope=\"row\">Failed</th>\n");
            myHtml.append("                    <td class=\"col02\">" + failedCount + "</td>\n");
            myHtml.append("                </tr>\n");
            myHtml.append("                <tr>\n");
            myHtml.append("                    <th class=\"col01 nmi\" scope=\"row\"><abbr title=\"Need More Information\">NMI</abbr></th>\n");
            myHtml.append("                    <td class=\"col02\">" + nmiCount + "</td>\n");
            myHtml.append("                </tr>\n");
            myHtml.append("                <tr>\n");
            myHtml.append("                    <th class=\"col01 na\" scope=\"row\"><abbr title=\"Not Applicable\">NA</abbr></th>\n");
            myHtml.append("                    <td class=\"col02\">" + naCount + "</td>\n");
            myHtml.append("                </tr>\n");
            myHtml.append("            </table>\n");
            myHtml.append("\n");

            // detailed results
            myHtml.append("            <h2 id=\"work-done\">Detailed result (what's been checked)</h2>\n");
            myHtml.append("            <table summary=\"Detailed result of accessibility audit\" id=\"result-detailed\">\n");
            myHtml.append("                <caption>Detailed result of accessibility audit</caption>\n");
            myHtml.append("                <tr>\n");
            myHtml.append("                    <th scope=\"col\" class=\"col01\">Test</th>\n");
            myHtml.append("                    <th scope=\"col\" class=\"col02\">Result</th>\n");
            myHtml.append("                    <th scope=\"col\" class=\"col03\">Remark</th>\n");
            myHtml.append("                </tr>\n");
            for (ProcessResult processResult : audit.getNetResultList()) {
                myHtml.append("                <tr>\n");
                myHtml.append("                    <td class=\"col01\"><a href=\"" + processResult.getTest().getDescription() + "\">" + processResult.getTest().getLabel() + "</a></td>\n");
                myHtml.append("                    <td class=\"col02");
                if (processResult.getValue().toString().equalsIgnoreCase("FAILED")) {
                    myHtml.append(" failed");
                } else if (processResult.getValue().toString().equalsIgnoreCase("PASSED")) {
                    myHtml.append(" passed");
                } else if (processResult.getValue().toString().equalsIgnoreCase("NEED_MORE_INFO")) {
                    myHtml.append(" nmi");
                } else if (processResult.getValue().toString().equalsIgnoreCase("NOT_APPLICABLE")) {
                    myHtml.append(" na");
                }
                myHtml.append("\">" + processResult.getValue() + "</td>\n");
                formatTestResults(myHtml, processResult);
                myHtml.append("                </tr>\n");
            }
            myHtml.append("            </table>\n");

            // table of remaining points to check
            myHtml.append("            <h2 id=\"work-todo\">Remaining points to check</h2>\n");
            if (nmiCount == 0) {
                myHtml.append("<p>Nothing more to check.</p>");
                myHtml.append("<p>Cool, you can go fishing ! :)</p>");
            } else {
                myHtml.append("            <p>... on <a href=\"" + resourceUrl + "\">" + resourceUrl + "</a></p>\n");
                myHtml.append("            <table summary=\"Remaing points to check\" id=\"remaining-work\">\n");
                myHtml.append("                <caption>Remaing points to check</caption>\n");
                myHtml.append("                <tr>\n");
                myHtml.append("                    <th scope=\"col\" class=\"col01\">Test</th>\n");
                myHtml.append("                    <th scope=\"col\" class=\"col02\">Result</th>\n");
                myHtml.append("                    <th scope=\"col\" class=\"col03\">Remark</th>\n");
                myHtml.append("                </tr>\n");

                for (ProcessResult processResult : audit.getNetResultList()) {
                    if (processResult.getValue().toString().equalsIgnoreCase("NEED_MORE_INFO")) {
                        myHtml.append("                <tr>\n");
                        myHtml.append("                    <td class=\"col01\"><a href=\"" + processResult.getTest().getDescription() + "\">" + processResult.getTest().getLabel() + "</a></td>\n");
                        myHtml.append("                    <td class=\"col02 nmi\">" + processResult.getValue() + "</td>\n");
                        formatTestResults(myHtml, processResult);
                        myHtml.append("                </tr>\n");
                    }
                }

                myHtml.append("            </table>\n");
            }
            myHtml.append("\n");
            myHtml.append("            <p id=\"score-formula\">\n");
            myHtml.append("                Score formula : (Passed + <acronym title=\"Not Applicable\">NA</acronym>) / (Total tests - <acronym title=\"Need More Information\">NMI</acronym>)\n");
            myHtml.append("            </p>\n");

            myHtml.append("        </div><!-- id=\"bd\" -->\n");

            myHtml.append(getResultPageFooter());

            out.println(myHtml);
        } finally {
            out.close();
        }
    }

    private String returnPageLoadingProblem(String resourceUrl) {
        String dateFormat = "yyyy-MM-dd HH:mm:ss";
        Calendar calendar = GregorianCalendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String myAuditDate = sdf.format(calendar.getTime());

        StringBuilder myHtml = new StringBuilder();
        myHtml.append(getResultPageHeader(resourceUrl));
        myHtml.append("    <body id=\"audit-result\">\n");
        myHtml.append("    <div id=\"doc2\">\n");
        myHtml.append("        <div id=\"hd\">\n");
        myHtml.append("            <h1>Result of accessibility audit</h1>\n");
        myHtml.append("        </div>\n");
        myHtml.append("\n");
        myHtml.append("        <div id=\"bd\">\n");
        myHtml.append("            <h2>A problem occured while loading page content</h2>\n");
        myHtml.append("\n\n");
        // audit meta-data
        myHtml.append("            <table summary=\"Audit meta-data\" id=\"result-meta\">\n");
        myHtml.append("                <caption>Audit meta-data</caption>\n");
        myHtml.append("                <tr>\n");
        myHtml.append("                    <th id=\"meta-url\" scope=\"row\" class=\"col01\">URL:</th>\n");
        myHtml.append("                    <td class=\"col02\"><a href=\"" + resourceUrl + "\">" + resourceUrl + "</a></td>\n");
        myHtml.append("                </tr>\n");
        myHtml.append("                <tr>\n");
        myHtml.append("                    <th id=\"meta-date\" scope=\"row\" class=\"col01\">Date:</th>\n");
        myHtml.append("                    <td class=\"col02\">" + myAuditDate + "</td>\n");
        myHtml.append("                </tr>\n");
        myHtml.append("            </table>\n");
        myHtml.append("\n\n");
        myHtml.append("        </div><!-- id=\"bd\" -->\n");
        myHtml.append(getResultPageFooter());

        return myHtml.toString();
    }

    private String returnPageProcessingProblem(String resourceUrl) {
        String dateFormat = "yyyy-MM-dd HH:mm:ss";
        Calendar calendar = GregorianCalendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String myAuditDate = sdf.format(calendar.getTime());

        StringBuilder myHtml = new StringBuilder();

        myHtml.append(getResultPageHeader(resourceUrl));
        myHtml.append("    <body id=\"audit-result\">\n");
        myHtml.append("    <div id=\"doc2\">\n");
        myHtml.append("        <div id=\"hd\">\n");
        myHtml.append("            <h1>Result of accessibility audit</h1>\n");
        myHtml.append("        </div>\n");
        myHtml.append("\n\n");
        myHtml.append("        <div id=\"bd\">\n");
        myHtml.append("            <h2>A problem occured while processing page</h2>\n");
        myHtml.append("\n\n");

        // audit meta-data
        myHtml.append("            <table summary=\"Audit meta-data\" id=\"result-meta\">\n");
        myHtml.append("                <caption>Audit meta-data</caption>\n");
        myHtml.append("                <tr>\n");
        myHtml.append("                    <th id=\"meta-url\" scope=\"row\" class=\"col01\">URL:</th>\n");
        myHtml.append("                    <td class=\"col02\"><a href=\"" + resourceUrl + "\">" + resourceUrl + "</a></td>\n");
        myHtml.append("                </tr>\n");
        myHtml.append("                <tr>\n");
        myHtml.append("                    <th id=\"meta-date\" scope=\"row\" class=\"col01\">Date:</th>\n");
        myHtml.append("                    <td class=\"col02\">" + myAuditDate + "</td>\n");
        myHtml.append("                </tr>\n");
        myHtml.append("            </table>\n");
        myHtml.append("\n\n");
        myHtml.append("        </div><!-- id=\"bd\" -->\n");
        myHtml.append(getResultPageFooter());

        return myHtml.toString();
    }

    private StringBuilder getResultPageHeader(String resourceUrl) {
        StringBuilder myHtml = new StringBuilder();
        myHtml.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n");
        myHtml.append("<html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"en\">\n");
        myHtml.append("    <head>\n");
        myHtml.append("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\n");
        myHtml.append("        <meta http-equiv=\"Author\" content=\"Open-S.com // Matthieu Faure\" lang=\"fr\" />\n");
        myHtml.append("        <title>Audit result for " + resourceUrl + "</title>\n");
        myHtml.append("        <link rel=\"stylesheet\" type=\"text/css\" href=\"http://yui.yahooapis.com/combo?2.8.0r4/build/reset-fonts/reset-fonts.css&amp;2.8.0r4/build/base/base-min.css\"/>\n");
        myHtml.append("        <link rel=\"stylesheet\" type=\"text/css\" href=\"Css/tanaguru.css\" />\n");
        myHtml.append("    </head>\n");
        myHtml.append("\n");
        return myHtml;
    }

    private StringBuilder getResultPageFooter() {
        StringBuilder myHtml = new StringBuilder();
        myHtml.append("\n");
        myHtml.append("        <div id=\"ft\">\n");
        myHtml.append("            &copy; <a href=\"http://www.Open-S.com/\">Open-S</a>\n");
        myHtml.append("        </div>\n");
        myHtml.append("    </div>\n");
        myHtml.append("    </body>\n");
        myHtml.append("</html>\n");
        return myHtml;
    }

    private void formatTestResults(StringBuilder htmlResponse, ProcessResult processResult) {
        htmlResponse.append("                    <td class=\"col03\">\n");
        Map<String, List<String>> remarks = new HashMap<String, List<String>>();
        for (ProcessRemark remark : processResult.getRemarkList()) {
            if (!remarks.containsKey(remark.getMessageCode())) {
                List<String> remarksInfos = new ArrayList<String>();
                if (remark instanceof SourceCodeRemark) {
                    remarksInfos.add(
                            "element <code>" + ((SourceCodeRemark) remark).getTarget().toLowerCase() + "</code> "
                            + "at line " + ((SourceCodeRemark) remark).getLineNumber() + " "
                            + "and character position " + ((SourceCodeRemark) remark).getCharacterPosition());
                }
                remarks.put(remark.getMessageCode(), remarksInfos);
            } else {
                if (remark instanceof SourceCodeRemark) {
                    remarks.get(remark.getMessageCode()).add(
                            "element <code>" + ((SourceCodeRemark) remark).getTarget().toLowerCase() + "</code> "
                            + "at line " + ((SourceCodeRemark) remark).getLineNumber() + " "
                            + "and character position " + ((SourceCodeRemark) remark).getCharacterPosition());
                }
            }
        }

        for (String messageCode : remarks.keySet()) {
            htmlResponse.append("<p class=\"process-remarks\">\n");
            htmlResponse.append("<strong>" + messageCode + "</strong>:</p>\n");
            if (!remarks.get(messageCode).isEmpty()) {
                htmlResponse.append("<ul class=\"process-remarks\">\n");
                for (String messageInfo : remarks.get(messageCode)) {
                    htmlResponse.append("<li>" + messageInfo + "</li>\n");
                }
                htmlResponse.append("</ul>\n");
            }
        }

        htmlResponse.append("                    </td>\n");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
