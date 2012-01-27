/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2011  Open-S Company
 *
 * This file is part of Tanaguru.
 *
 * Tanaguru is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tanaguru.cli;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.EvidenceElement;
import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.parameterization.ParameterElement;
import org.opens.tanaguru.entity.service.audit.AuditDataService;
import org.opens.tanaguru.entity.service.audit.ProcessRemarkDataService;
import org.opens.tanaguru.entity.service.audit.ProcessResultDataService;
import org.opens.tanaguru.entity.service.parameterization.ParameterDataService;
import org.opens.tanaguru.entity.service.parameterization.ParameterElementDataService;
import org.opens.tanaguru.entity.service.statistics.WebResourceStatisticsDataService;
import org.opens.tanaguru.entity.service.subject.WebResourceDataService;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.service.AnalyserService;
import org.opens.tanaguru.service.AuditService;
import org.opens.tanaguru.service.AuditServiceListener;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * This class launches Tanaguru with urls passed as arguments by the user.
 *
 * @author jkowalczyk
 */
public class Tanaguru implements AuditServiceListener {

    private static final String APPLICATION_CONTEXT_FILE_PATH = "conf/context/application-context.xml";
    private static final String AW21_REF_CODE = "AW21";
    private static final String LEVEL_PARAMETER_ELEMENT_CODE = "LEVEL";

    private AuditService auditService = null;
    private AuditDataService auditDataService = null;
    private WebResourceDataService webResourceDataService = null;
    private WebResourceStatisticsDataService webResourceStatisticsDataService = null;
    private ProcessResultDataService processResultDataService = null;
    private ProcessRemarkDataService processRemarkDataService = null;
    private ParameterDataService parameterDataService = null;
    private ParameterElementDataService parameterElementDataService = null;
    
    public static void main(String[] args) {
        if (args != null && args.length == 3 && args[0] != null && args[1] != null && args[2] != null) {
            new Tanaguru().run(args[0], args[1], args[2]);
        }
    }

    public Tanaguru() {
        super();
    }

    public void run(String urlTab, String tanaguruHome, String auditLevel) {
        ApplicationContext springApplicationContext = new FileSystemXmlApplicationContext(tanaguruHome+"/"+APPLICATION_CONTEXT_FILE_PATH);
        BeanFactory springBeanFactory = springApplicationContext;
        auditService = (AuditService) springBeanFactory.getBean("auditService");
        auditDataService = (AuditDataService) springBeanFactory.getBean("auditDataService");
        webResourceDataService = (WebResourceDataService) springBeanFactory.getBean("webResourceDataService");
        webResourceStatisticsDataService = (WebResourceStatisticsDataService) springBeanFactory.getBean("webResourceStatisticsDataService");
        processResultDataService = (ProcessResultDataService) springBeanFactory.getBean("processResultDataService");
        processRemarkDataService = (ProcessRemarkDataService) springBeanFactory.getBean("processRemarkDataService");
        parameterDataService = (ParameterDataService) springBeanFactory.getBean("parameterDataService");
        parameterElementDataService = (ParameterElementDataService) springBeanFactory.getBean("parameterElementDataService");
        auditService.add(this);
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(tanaguruHome+"/"+"tests/tests.properties"));
        } catch (IOException ex) {
            Logger.getLogger(Tanaguru.class.getName()).log(Level.SEVERE, null, ex);
        }
        ParameterElement levelParameterElement = parameterElementDataService.getParameterElement(LEVEL_PARAMETER_ELEMENT_CODE);
        Parameter levelParameter = parameterDataService.getParameter(levelParameterElement, AW21_REF_CODE+";"+auditLevel);
        Set<Parameter> paramSet = parameterDataService.getDefaultParameterSet();
        paramSet = parameterDataService.updateParameter(paramSet, levelParameter);
        String[] pageUrlTab = urlTab.split(";");
        List<String> pageUrlList = new ArrayList<String>();
        pageUrlList.addAll(Arrays.asList(pageUrlTab));
        if (pageUrlList.size() > 1) {
            auditService.auditSite("site:" + pageUrlList.get(0), pageUrlList, paramSet);
        } else {
            auditService.auditPage(pageUrlList.get(0), getAuditPageParameterSet(paramSet));
        }
    }

    @Override
    public void auditCompleted(Audit audit) {
        audit = auditDataService.getAuditWithWebResource(audit.getId());
        List<ProcessResult> processResultList = (List<ProcessResult>) processResultDataService.getNetResultFromAudit(audit);
        System.out.println("Audit terminated with success at " + audit.getDateOfCreation());
        System.out.println("");
        System.out.println("RawMark : " + webResourceStatisticsDataService.getWebResourceStatisticsByWebResource(audit.getSubject()).getRawMark() + "%");
        System.out.println("WeightedMark : " + webResourceStatisticsDataService.getWebResourceStatisticsByWebResource(audit.getSubject()).getMark() + "%");
        if (audit.getSubject() instanceof Site) {
            int numberOfChildWebResource = webResourceDataService.getNumberOfChildWebResource(audit.getSubject()).intValue();
            for (int i = 0; i < numberOfChildWebResource; i++) {
                displayWebResourceResult(webResourceDataService.getWebResourceFromItsParent(audit.getSubject(), i, 1).iterator().next(),
                        processResultList);
            }
        } else {
            displayWebResourceResult(audit.getSubject(), processResultList);
        }
        System.out.println("");
        System.exit(0);
    }

    private void displayWebResourceResult(WebResource wr, List<ProcessResult> processResultList) {
        System.out.println("");
        System.out.println("Subject : " + wr.getURL());
        List<ProcessResult> prList = new ArrayList<ProcessResult>();
        for (ProcessResult netResult : processResultList) {
            if (netResult.getSubject().getURL().equalsIgnoreCase(wr.getURL())) {
                prList.add(netResult);
            }
        }
        System.out.println("RawMark : " + webResourceStatisticsDataService.getWebResourceStatisticsByWebResource(wr).getRawMark() + "%");
        System.out.println("WeightedMark : " + webResourceStatisticsDataService.getWebResourceStatisticsByWebResource(wr).getMark() + "%");
        for (ProcessResult result : prList) {
            System.out.println(result.getTest().getCode() + ": " + result.getValue());
            Set<ProcessRemark> processRemarkList = (Set<ProcessRemark>) processRemarkDataService.findAllByProcessResult(result);
            for (ProcessRemark processRemark : processRemarkList) {
                System.out.println(" ->  " + processRemark.getIssue()
                        + " " + processRemark.getMessageCode());
                for (EvidenceElement el : processRemark.getElementList()) {
                    System.out.println("    -> " +el.getEvidence().getCode() + ":"+ el.getValue());
                }
            }
        }
    }

    @Override
    public void auditCrashed(Audit audit, Exception exception) {
        exception.printStackTrace();
        System.out.println("crash (id+message): " + audit.getId() + " " + exception.fillInStackTrace());
    }

    /**
     * The default parameter set embeds a depth value that corresponds to the
     * site audit. We need here to replace this parameter by a parameter value
     * equals to 0.
     * @return
     */
    private Set<Parameter> getAuditPageParameterSet(Set<Parameter> defaultParameterSet) {
         ParameterElement parameterElement = parameterElementDataService.getParameterElement("DEPTH");
         Parameter depthParameter = parameterDataService.getParameter(parameterElement, "0");
         Set<Parameter> auditPageParamSet = parameterDataService.updateParameter(
                 defaultParameterSet, depthParameter);
         return auditPageParamSet;
    }

}