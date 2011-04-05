package org.opens.tanaguru.cli;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.EvidenceElement;
import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.service.audit.AuditDataService;
import org.opens.tanaguru.entity.service.audit.ProcessRemarkDataService;
import org.opens.tanaguru.entity.service.audit.ProcessResultDataService;
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
    private AuditService auditService = null;
    private AnalyserService analyserService = null;
    private AuditDataService auditDataService = null;
    private WebResourceDataService webResourceDataService = null;
    private ProcessResultDataService processResultDataService = null;
    private ProcessRemarkDataService processRemarkDataService = null;
    private FileWriter fileWriter=null;
    private static final char CARRIAGE_RETURN_CHAR = '\n';
    private String resultFilePath = null;
    
    public static void main(String[] args) {
        if (args != null && args.length == 1 && args[0] != null) {
            if (args[0].equals("-h") || args[0].equals("--help")) {
                displayHelp();
            } else if(args[0].startsWith("-tmu")) {
                System.out.println("Too Many Urls");
                displayHelp();
            } else if(args[0].startsWith("-")) {
                System.out.println("Invalid argument");
                displayHelp();
            }
        } else if (args != null && args.length == 2 && args[0] != null && args[1] != null) {
            if (args[0].startsWith("-h") || args[0].startsWith("--help")) {
                displayHelp();
            } else if(args[0].startsWith("-tmu")) {
                System.out.println("Too Many Urls");
                displayHelp();
            } else if(args[0].startsWith("-")) {
                System.out.println("Invalid argument");
                displayHelp();
            } else if (args[0].startsWith("http://")) {
                new Tanaguru().run(args[0], args[1]);
//                System.exit(0);
            } else {
                displayHelp();
            }
        } else {
            displayHelp();
        }
    }

    public Tanaguru() {
        super();
    }

    public void run(String urlTab, String resultFilePath) {
        this.resultFilePath = resultFilePath;
        ApplicationContext springApplicationContext = new FileSystemXmlApplicationContext(APPLICATION_CONTEXT_FILE_PATH);
        BeanFactory springBeanFactory = springApplicationContext;
        auditService = (AuditService) springBeanFactory.getBean("auditService");
        auditDataService = (AuditDataService) springBeanFactory.getBean("auditDataService");
        webResourceDataService = (WebResourceDataService) springBeanFactory.getBean("webResourceDataService");
        analyserService = (AnalyserService) springBeanFactory.getBean("analyserService");
        processResultDataService = (ProcessResultDataService) springBeanFactory.getBean("processResultDataService");
        processRemarkDataService = (ProcessRemarkDataService) springBeanFactory.getBean("processRemarkDataService");
        auditService.add(this);
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("tests/tests.properties"));
        } catch (IOException ex) {
            Logger.getLogger(Tanaguru.class.getName()).log(Level.SEVERE, null, ex);
        }

        String[] pageUrlList = urlTab.split(";");
        String[] testCodeList = props.getProperty("testCodeList").split(";");
        if (pageUrlList.length > 1) {
            auditService.auditSite("site:" + pageUrlList[0], pageUrlList, testCodeList);
        } else {
            auditService.auditPage(pageUrlList[0], testCodeList);
        }
    }

    @Override
    public void auditCompleted(Audit audit) {
        try {
            fileWriter = new FileWriter(resultFilePath);
        } catch (IOException ex) {
            Logger.getLogger(Tanaguru.class.getName()).log(Level.SEVERE, null, ex);
        }
        audit = auditDataService.getAuditWithWebResource(audit.getId());
        List<ProcessResult> processResultList = (List<ProcessResult>) processResultDataService.getNetResultFromAudit(audit);
        try {
            fileWriter.write("Audit terminated with success at " + audit.getDateOfCreation()+CARRIAGE_RETURN_CHAR);
            fileWriter.write(CARRIAGE_RETURN_CHAR);
            fileWriter.write("Audit Mark : " + Float.valueOf(analyserService.analyse(processResultList)).intValue() + "%" + CARRIAGE_RETURN_CHAR);
            if (audit.getSubject() instanceof Site) {
                int numberOfChildWebResource = webResourceDataService.getNumberOfChildWebResource(audit.getSubject()).intValue();
                for (int i = 0; i < numberOfChildWebResource; i++) {
                    displayWebResourceResult(webResourceDataService.getWebResourceFromItsParent(audit.getSubject(), i, 1).iterator().next(),
                            processResultList);
                }
            } else {
                displayWebResourceResult(audit.getSubject(), processResultList);
            }
            fileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(Tanaguru.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("");
        System.out.println("see @ " + resultFilePath + " for results");
        System.exit(0);
    }

    private void displayWebResourceResult(WebResource wr, List<ProcessResult> processResultList) {
        try {
            fileWriter.write(CARRIAGE_RETURN_CHAR);
            fileWriter.write("Subject : " + wr.getURL()+CARRIAGE_RETURN_CHAR);
        } catch (IOException ex) {
            Logger.getLogger(Tanaguru.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<ProcessResult> prList = new ArrayList<ProcessResult>();
        for (ProcessResult netResult : processResultList) {
            if (netResult.getSubject().getURL().equalsIgnoreCase(wr.getURL())) {
                prList.add(netResult);
            }
        }
        try {
            fileWriter.write("Mark : " + Float.valueOf(analyserService.analyse(prList)).intValue() + "%" + CARRIAGE_RETURN_CHAR);
            for (ProcessResult result : prList) {
                fileWriter.write(result.getTest().getCode() + ": " + result.getValue() + CARRIAGE_RETURN_CHAR);
                Set<ProcessRemark> processRemarkList = (Set<ProcessRemark>) processRemarkDataService.findAllByProcessResult(result);
                for (ProcessRemark processRemark : processRemarkList) {
                    fileWriter.write(" ->  " + processRemark.getIssue()
                            + " " + processRemark.getMessageCode() + CARRIAGE_RETURN_CHAR);
                    for (EvidenceElement el : processRemark.getElementList()) {
                        fileWriter.write("    -> " +el.getEvidence().getCode() + ":"+ el.getValue() + CARRIAGE_RETURN_CHAR);
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Tanaguru.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void auditCrashed(Audit audit, Throwable exception) {
        System.out.println("crash (id+message): " + audit.getId() + " " + exception.getMessage());
    }

    private static void displayHelp(){
        System.out.println("Usage : start-tanaguru [OPTIONS] [URL...]");
        System.out.println("OPTIONS");
        System.out.println("  --help or -h : Print this message");
        System.out.println("ARGUMENTS");
        System.out.println("  URL : from 1 to 10 Url separated by blank. Each url has to start with 'http://' ");
        System.out.println("");
    }

}