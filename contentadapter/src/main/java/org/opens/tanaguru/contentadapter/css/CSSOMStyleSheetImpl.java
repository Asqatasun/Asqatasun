package org.opens.tanaguru.contentadapter.css;

import java.util.ArrayList;
import java.util.List;

import org.opens.tanaguru.contentadapter.Resource;
import java.io.Serializable;
import org.w3c.css.sac.SACMediaList;

/**
 * 
 * @author ADEX
 */
public class CSSOMStyleSheetImpl implements CSSOMStyleSheet, Serializable {

    private int lineNumber;
    private List<CSSOMRule> rules;
    private short type;
    private SACMediaList mediaList;

    public CSSOMStyleSheetImpl() {
        super();
    }

    public CSSOMStyleSheetImpl(Resource resource) {
        super();
        rules = new ArrayList<CSSOMRule>();
        lineNumber = resource.getLineNumber();
        this.type = resource.getRsrcLocator().getRsrcLocatorType();
        if (resource instanceof CSSResource) {
            this.mediaList = ((CSSResource)resource).getCssMediaList();
        }
    }

    public boolean addCSSOMRule(CSSOMRule rule) {
        return rules.add(rule);
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public List<CSSOMRule> getRules() {
        return rules;
    }

    public short getType() {
        return type;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public void setRules(List<CSSOMRule> rules) {
        this.rules = rules;
    }

    public void setType(short type) {
        this.type = type;
    }

    @Override
    public SACMediaList getMediaList() {
        return mediaList;
    }

    @Override
    public void setMediaList(SACMediaList media) {
        this.mediaList = media;
    }

}
