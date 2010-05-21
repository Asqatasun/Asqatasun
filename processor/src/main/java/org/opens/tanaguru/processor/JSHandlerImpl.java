package org.opens.tanaguru.processor;

import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.audit.SourceCodeRemark;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.factory.audit.ProcessRemarkFactory;
import org.opens.tanaguru.entity.factory.audit.SourceCodeRemarkFactory;
import org.opens.tanaguru.contentadapter.js.JSResource;
import org.opens.tanaguru.contentadapter.RsrcLocator;
import com.thoughtworks.xstream.XStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.opens.tanaguru.entity.audit.JavascriptContent;
import org.opens.tanaguru.entity.audit.RelatedContent;

/**
 * 
 * @author ADEX
 */
public class JSHandlerImpl implements JSHandler {

    private boolean initialized = false;
    protected Set<JSResource> javaScriptSet;
    protected ProcessRemarkFactory processRemarkFactory;
    protected Collection<ProcessRemark> remarkList;
    protected Collection<JSResource> selectedJSList;
    protected SourceCodeRemarkFactory sourceCodeRemarkFactory;
    protected SSP ssp;

    public JSHandlerImpl() {
        super();
    }

    public JSHandlerImpl(SSP ssp) {
        this.ssp = ssp;
    }

    private void addSourceCodeRemark(TestSolution processResult,
            JSResource jsResource, String messageCode, String attrName) {
        SourceCodeRemark remark = sourceCodeRemarkFactory.create();
        remark.setIssue(processResult);
        remark.setMessageCode(messageCode);
        remark.setLineNumber(jsResource.getLineNumber());
        // remark.setCharacterPosition(index + 1);
        remark.setTarget(attrName);
    }

    public JSHandler beginSelection() {
        initialize();

        selectedJSList = new ArrayList<JSResource>();
        remarkList = new ArrayList<ProcessRemark>();
        return this;
    }

    public ProcessRemarkFactory getProcessRemarkFactory() {
        return processRemarkFactory;
    }

    public Collection<ProcessRemark> getRemarkList() {
        return remarkList;
    }

    public SourceCodeRemarkFactory getSourceCodeRemarkFactory() {
        return sourceCodeRemarkFactory;
    }

    public SSP getSSP() {
        return ssp;
    }

    private void initialize() {
        if (initialized) {
            return;
        }

        XStream xstream = new XStream();

        for (RelatedContent relatedContent : ssp.getRelatedContentSet()) {
            if (relatedContent instanceof JavascriptContent) {
                if (javaScriptSet == null) {
                    javaScriptSet = new HashSet<JSResource>();
                }
                javaScriptSet.addAll(
                        (Set<JSResource>) xstream.fromXML(
                        ((JavascriptContent) relatedContent).getAdaptedContent()));
            }
        }
        initialized = true;
    }

    public JSHandler selectAllJS() {
        selectedJSList = javaScriptSet;
        return this;
    }

    public JSHandler selectExternalJS() {
        Collection<JSResource> externalJsList = new ArrayList<JSResource>();
        for (JSResource jsResource : javaScriptSet) {
            RsrcLocator location = jsResource.getRsrcLocator();
            if (location.getRsrcLocatorType() == RsrcLocator.EXTERNAL) {
                externalJsList.add(jsResource);
            }
        }
        selectedJSList = externalJsList;
        return this;
    }

    public JSHandler selectInlineJS() {
        Collection<JSResource> inlineJsList = new ArrayList<JSResource>();
        for (JSResource jsResource : javaScriptSet) {
            RsrcLocator location = jsResource.getRsrcLocator();
            if (location.getRsrcLocatorType() == RsrcLocator.INLINE) {
                inlineJsList.add(jsResource);
            }
        }
        selectedJSList = inlineJsList;
        return this;
    }

    public JSHandler selectLocalJS() {
        Collection<JSResource> localJsList = new ArrayList<JSResource>();
        for (JSResource jsResource : javaScriptSet) {
            RsrcLocator location = jsResource.getRsrcLocator();
            if (location.getRsrcLocatorType() == RsrcLocator.LOCAL) {
                localJsList.add(jsResource);
            }
        }
        selectedJSList = localJsList;
        return this;
    }

    public void setProcessRemarkFactory(
            ProcessRemarkFactory processRemarkFactory) {
        this.processRemarkFactory = processRemarkFactory;
    }

    public void setRemarkList(Collection<ProcessRemark> remarkList) {
        this.remarkList = remarkList;
    }

    public void setSourceCodeRemarkFactory(
            SourceCodeRemarkFactory sourceCodeRemarkFactory) {
        this.sourceCodeRemarkFactory = sourceCodeRemarkFactory;
    }

    public void setSSP(SSP ssp) {
        this.ssp = ssp;
        initialized = false;
    }
}
