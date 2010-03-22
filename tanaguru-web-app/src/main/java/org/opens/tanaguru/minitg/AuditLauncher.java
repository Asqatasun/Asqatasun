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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
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
                        if (!((SSP)content).getSource().isEmpty()) {
                            hasContent = true;
                            break;
                        }
                    }
                }

                if (!hasContent){
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
                    if (o1.getTest().getRank() < o2.getTest().getRank()) {
                        return -1;
                    }
                    if (o1.getTest().getRank() > o2.getTest().getRank()) {
                        return 1;
                    }
                    return 0;
                }
            });

            myHtml.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
            myHtml.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
            myHtml.append("    <head>");
            myHtml.append("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>");
            myHtml.append("        <meta http-equiv=\"Author\" content=\"Open-S.com // Matthieu Faure\" lang=\"fr\" />");
            myHtml.append("        <title>Audit result for " + resourceUrl + "</title>");
            myHtml.append("        <link rel=\"stylesheet\" type=\"text/css\" href=\"http://yui.yahooapis.com/combo?2.8.0r4/build/reset-fonts/reset-fonts.css&amp;2.8.0r4/build/base/base-min.css\"/>");
            myHtml.append("        <link rel=\"stylesheet\" type=\"text/css\" href=\"Css/tanaguru.css\" />");
            myHtml.append("    </head>");
            myHtml.append("    <body id=\"audit-result\">");
            myHtml.append("    <div id=\"doc2\">");
            myHtml.append("        <div id=\"hd\">");
            myHtml.append("            <h1>Result of accessibility audit</h1>");
            myHtml.append("        </div>");
            myHtml.append("");
            myHtml.append("        <div id=\"bd\">");
            myHtml.append("            <h2>Synthesis</h2>");
            myHtml.append("");

            // audit meta-data
            myHtml.append("            <table summary=\"Audit meta-data\" id=\"result-meta\">");
            myHtml.append("                <caption>Audit meta-data</caption>");
            myHtml.append("                <tr>");
            myHtml.append("                    <th id=\"meta-score\" scope=\"row\" class=\"col01\">Score:</th>");
            myHtml.append("                    <td id=\"meta-score-data\" class=\"col02\">" + myAuditMark.intValue() + "%</td>");
            myHtml.append("                </tr>");
            myHtml.append("                <tr>");
            myHtml.append("                    <th id=\"meta-url\" scope=\"row\" class=\"col01\">URL:</th>");
            myHtml.append("                    <td class=\"col02\"><a href=\"" + resourceUrl + "\">" + resourceUrl + "</a></td>");
            myHtml.append("                </tr>");
            myHtml.append("                <tr>");
            myHtml.append("                    <th id=\"meta-date\" scope=\"row\" class=\"col01\">Date:</th>");
            myHtml.append("                    <td class=\"col02\">" + myAuditDate + "</td>");
            myHtml.append("                </tr>");
            myHtml.append("            </table>");
            myHtml.append("");
            /*
            myHtml.append("            <dl class=\"table-display clearfix\">");
            myHtml.append("                <dt id=\"score-dt\">Score:</dt><dd id=\"score-dd\">" + audit.getMark() + "%</dd>");
            myHtml.append("                <dt>URL:</dt><dd><a href=\"" + resourceUrl + "\">" + resourceUrl + "</a></dd>");
            myHtml.append("                <dt>Date:</dt><dd>" + myAuditCalendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.FRENCH) + "</dd>");
            myHtml.append("            </dl>");
            myHtml.append("");
             */

            // synthetised results
            myHtml.append("            <table summary=\"Synthetised result of accessibility audit\" id=\"result-synthetised\">");
            myHtml.append("                <caption>Synthetised result of accessibility audit</caption>");
            myHtml.append("                <tr>");
            myHtml.append("                    <th id=\"passed\" scope=\"row\" class=\"col01\">Passed</th>");
            myHtml.append("                    <td class=\"col02\">" + passedCount + "</td>");
            myHtml.append("                </tr>");
            myHtml.append("                <tr>");
            myHtml.append("                    <th id=\"failed\" scope=\"row\" class=\"col01\">Failed</th>");
            myHtml.append("                    <td class=\"col02\">" + failedCount + "</td>");
            myHtml.append("                </tr>");
            myHtml.append("                <tr>");
            myHtml.append("                    <th id=\"nmi\" scope=\"row\" class=\"col01\"><abbr title=\"Need More Information\">NMI</abbr></th>");
            myHtml.append("                    <td class=\"col02\">" + nmiCount + "</td>");
            myHtml.append("                </tr>");
            myHtml.append("                <tr>");
            myHtml.append("                    <th id=\"na\" scope=\"row\" class=\"col01\"><abbr title=\"Not Applicable\">NA</abbr></th>");
            myHtml.append("                    <td class=\"col02\">" + naCount + "</td>");
            myHtml.append("                </tr>");
            myHtml.append("            </table>");
            myHtml.append("");

            // detailed results
            myHtml.append("            <h2>Detailed result</h2>");
            myHtml.append("            <table summary=\"Detailed result of accessibility audit\" id=\"result-detailed\">");
            myHtml.append("                <caption>Detailed result of accessibility audit</caption>");
            myHtml.append("                <tr>");
            myHtml.append("                    <th scope=\"col\" class=\"col01\">Test</th>");
            myHtml.append("                    <th scope=\"col\" class=\"col02\">Result</th>");
            myHtml.append("                    <th scope=\"col\" class=\"col03\">Remark</th>");
            myHtml.append("                </tr>");
            for (ProcessResult processResult : audit.getNetResultList()) {
                myHtml.append("                <tr>");
                myHtml.append("                    <td class=\"col01\">" + processResult.getTest().getCode() + "</td>");
                myHtml.append("                    <td class=\"col02\">" + processResult.getValue() + "</td>");
                formatTestResults(myHtml, processResult);
                myHtml.append("                </tr>");
            }
            myHtml.append("            </table>");
            myHtml.append("        </div><!-- id=\"bd\" -->");
            myHtml.append("");
            myHtml.append("        <div id=\"ft\">");
            myHtml.append("            &copy; <a href=\"http://www.Open-S.com/\">Open-S</a>");
            myHtml.append("        </div>");
            myHtml.append("    </div>");
            myHtml.append("    </body>");
            myHtml.append("</html>");

            out.println(myHtml);
        } finally {
            out.close();
        }
    }

    private String returnPageLoadingProblem(String resourceUrl){
        String dateFormat = "yyyy-MM-dd HH:mm:ss";
        Calendar calendar = GregorianCalendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String myAuditDate = sdf.format(calendar.getTime());

        StringBuilder myHtml = new StringBuilder();
        myHtml.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
        myHtml.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
        myHtml.append("    <head>");
        myHtml.append("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>");
        myHtml.append("        <meta http-equiv=\"Author\" content=\"Open-S.com // Matthieu Faure\" lang=\"fr\" />");
        myHtml.append("        <title>Audit result for " + resourceUrl + "</title>");
        myHtml.append("        <link rel=\"stylesheet\" type=\"text/css\" href=\"http://yui.yahooapis.com/combo?2.8.0r4/build/reset-fonts/reset-fonts.css&amp;2.8.0r4/build/base/base-min.css\"/>");
        myHtml.append("        <link rel=\"stylesheet\" type=\"text/css\" href=\"Css/tanaguru.css\" />");
        myHtml.append("    </head>");
        myHtml.append("    <body id=\"audit-result\">");
        myHtml.append("    <div id=\"doc2\">");
        myHtml.append("        <div id=\"hd\">");
        myHtml.append("            <h1>Result of accessibility audit</h1>");
        myHtml.append("        </div>");
        myHtml.append("");
        myHtml.append("        <div id=\"bd\">");
        myHtml.append("            <h2>A problem occured while loading page content</h2>");
        myHtml.append("");
        // audit meta-data
        myHtml.append("            <table summary=\"Audit meta-data\" id=\"result-meta\">");
        myHtml.append("                <caption>Audit meta-data</caption>");
        myHtml.append("                <tr>");
        myHtml.append("                    <th id=\"meta-url\" scope=\"row\" class=\"col01\">URL:</th>");
        myHtml.append("                    <td class=\"col02\"><a href=\"" + resourceUrl + "\">" + resourceUrl + "</a></td>");
        myHtml.append("                </tr>");
        myHtml.append("                <tr>");
        myHtml.append("                    <th id=\"meta-date\" scope=\"row\" class=\"col01\">Date:</th>");
        myHtml.append("                    <td class=\"col02\">" + myAuditDate + "</td>");
        myHtml.append("                </tr>");
        myHtml.append("            </table>");
        myHtml.append("");
        myHtml.append("        </div><!-- id=\"bd\" -->");
        myHtml.append("");
        myHtml.append("        <div id=\"ft\">");
        myHtml.append("            &copy; <a href=\"http://www.Open-S.com/\">Open-S</a>");
        myHtml.append("        </div>");
        myHtml.append("    </div>");
        myHtml.append("    </body>");
        myHtml.append("</html>");

        return myHtml.toString();
    }

    private String returnPageProcessingProblem(String resourceUrl){
        String dateFormat = "yyyy-MM-dd HH:mm:ss";
        Calendar calendar = GregorianCalendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String myAuditDate = sdf.format(calendar.getTime());

        StringBuilder myHtml = new StringBuilder();
        myHtml.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
        myHtml.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
        myHtml.append("    <head>");
        myHtml.append("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>");
        myHtml.append("        <meta http-equiv=\"Author\" content=\"Open-S.com // Matthieu Faure\" lang=\"fr\" />");
        myHtml.append("        <title>Audit result for " + resourceUrl + "</title>");
        myHtml.append("        <link rel=\"stylesheet\" type=\"text/css\" href=\"http://yui.yahooapis.com/combo?2.8.0r4/build/reset-fonts/reset-fonts.css&amp;2.8.0r4/build/base/base-min.css\"/>");
        myHtml.append("        <link rel=\"stylesheet\" type=\"text/css\" href=\"Css/tanaguru.css\" />");
        myHtml.append("    </head>");
        myHtml.append("    <body id=\"audit-result\">");
        myHtml.append("    <div id=\"doc2\">");
        myHtml.append("        <div id=\"hd\">");
        myHtml.append("            <h1>Result of accessibility audit</h1>");
        myHtml.append("        </div>");
        myHtml.append("");
        myHtml.append("        <div id=\"bd\">");
        myHtml.append("            <h2>A problem occured while processing page</h2>");
        myHtml.append("");

        // audit meta-data
        myHtml.append("            <table summary=\"Audit meta-data\" id=\"result-meta\">");
        myHtml.append("                <caption>Audit meta-data</caption>");
        myHtml.append("                <tr>");
        myHtml.append("                    <th id=\"meta-url\" scope=\"row\" class=\"col01\">URL:</th>");
        myHtml.append("                    <td class=\"col02\"><a href=\"" + resourceUrl + "\">" + resourceUrl + "</a></td>");
        myHtml.append("                </tr>");
        myHtml.append("                <tr>");
        myHtml.append("                    <th id=\"meta-date\" scope=\"row\" class=\"col01\">Date:</th>");
        myHtml.append("                    <td class=\"col02\">" + myAuditDate + "</td>");
        myHtml.append("                </tr>");
        myHtml.append("            </table>");
        myHtml.append("");
        myHtml.append("        </div><!-- id=\"bd\" -->");
        myHtml.append("");
        myHtml.append("        <div id=\"ft\">");
        myHtml.append("            &copy; <a href=\"http://www.Open-S.com/\">Open-S</a>");
        myHtml.append("        </div>");
        myHtml.append("    </div>");
        myHtml.append("    </body>");
        myHtml.append("</html>");

        return myHtml.toString();
    }

    private void formatTestResults(StringBuilder htmlResponse, ProcessResult processResult){
        htmlResponse.append("                    <td class=\"col03\">");
        Map <String, List<String>> remarks = new HashMap<String, List<String>>();
        for (ProcessRemark remark : processResult.getRemarkList()) {
            if (!remarks.containsKey(remark.getMessageCode())) {
                List<String> remarksInfos = new ArrayList<String>();
                if (remark instanceof SourceCodeRemark) {
                    remarksInfos.add(
                        "element " + ((SourceCodeRemark)remark).getTarget().toLowerCase() + " " +
                        "at line " + ((SourceCodeRemark)remark).getLineNumber() + " " + 
                        "and character position " + ((SourceCodeRemark)remark).getCharacterPosition());
                } else if (remark instanceof ProcessRemark) {
                    remarksInfos.add(
                        " element " + remark.getSelectedElement());
                }
                remarks.put(remark.getMessageCode(), remarksInfos);
            } else {
                if (remark instanceof SourceCodeRemark) {
                    remarks.get(remark.getMessageCode()).add(
                        "element " + ((SourceCodeRemark)remark).getTarget().toLowerCase() + " " +
                        "at line " + ((SourceCodeRemark)remark).getLineNumber() + " " +
                        "and character position " + ((SourceCodeRemark)remark).getCharacterPosition());
                } else if (remark instanceof ProcessRemark) {
                    remarks.get(remark.getMessageCode()).add(
                        "element " + remark.getSelectedElement());
                }
            }
        }
        for (String messageCode : remarks.keySet()) {
            htmlResponse.append("                         <p>");
            for (String messageInfo : remarks.get(messageCode)) {
                htmlResponse.append(messageCode + ":" + messageInfo + "<br/>");
            }
            htmlResponse.append("                         </p>");
        }

        htmlResponse.append("                    </td>");
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
