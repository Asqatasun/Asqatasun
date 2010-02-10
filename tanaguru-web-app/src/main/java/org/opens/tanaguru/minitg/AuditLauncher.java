/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.minitg;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.ProcessResult;
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

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AuditLauncher</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/resulttable.css\"/>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AuditLauncher at " + request.getContextPath() + "</h1>");

            out.println("<p>Note: " + audit.getMark() + "</p>");

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

            out.println("<table>");
            out.println("<tr>");
            out.println("<th>Passed</th>");
            out.println("<th>Failed</th>");
            out.println("<th>NMI</th>");
            out.println("<th>NA</th>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>" + passedCount + "</td>");
            out.println("<td>" + failedCount + "</td>");
            out.println("<td>" + nmiCount + "</td>");
            out.println("<td>" + naCount + "</td>");
            out.println("</tr>");
            out.println("</table>");

            String resourceUrl = audit.getSubject().getURL();
            out.println("<h2>URL: " + resourceUrl + "</h2>");
            out.println("<h3>Thématique: " + "" + "</h3>");
            out.println("<h4>Critère: " + "" + "</h4>");

            out.println("<table class=\"outertable\" border=\"1\">");
            out.println("<tr>");
            out.println("<th>Test</th>");
            out.println("<th>Résultat</th>");
            out.println("<th class=\"remarkcolumnheader\">Remarques</th>");
            out.println("</tr>");

            for (ProcessResult processResult : audit.getNetResultList()) {
                out.println("<tr>");
                out.println("<td>" + processResult.getTest().getCode() + "</td>");
                out.println("<td>" + processResult.getValue() + "</td>");
                out.println("<td>");
                for (ProcessRemark remark : processResult.getRemarkList()) {
                    out.println("<p>" + remark.getMessageCode() + "</p>");
                }
                out.println("</td>");
                out.println("</tr>");
            }
            out.println("</table>");




            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
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
