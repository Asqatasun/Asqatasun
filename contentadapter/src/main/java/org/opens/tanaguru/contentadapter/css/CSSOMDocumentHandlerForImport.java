package org.opens.tanaguru.contentadapter.css;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import org.w3c.css.sac.CSSException;
import org.w3c.css.sac.DocumentHandler;
import org.w3c.css.sac.InputSource;
import org.w3c.css.sac.LexicalUnit;
import org.w3c.css.sac.SACMediaList;
import org.w3c.css.sac.SelectorList;

/**
 * 
 * @author ADEX refer to org.w3c.css.sac.DocumentHandler javadoc
 */
public class CSSOMDocumentHandlerForImport implements DocumentHandler {

    private Set<CSSImportedStyle> importedStyle;

    public CSSOMDocumentHandlerForImport() {
        super();
    }

    public CSSOMDocumentHandlerForImport(CSSResource rsrc) {
        // Not used
    }

    public void comment(String arg0) throws CSSException {
        // Not used
    }

    public void endDocument(InputSource arg0) throws CSSException {
        // Not used
    }

    public void endFontFace() throws CSSException {
        // Not used
    }

    public void endMedia(SACMediaList arg0) throws CSSException {
        // Not used
    }

    public void endPage(String arg0, String arg1) throws CSSException {
        // Not used
    }

    public void endSelector(SelectorList arg0) throws CSSException {
        // Not used
    }

    protected String getLexicalValue(LexicalUnit lexicalUnit) {
        return null;
    }

    protected Object getNodeRoot() {
        return null;
    }

    protected Stack getNodeStack() {
        return null;
    }

    public Set<CSSImportedStyle> getResult() {
        return importedStyle;
    }

    public void ignorableAtRule(String arg0) throws CSSException {
        // Not used
    }

    public void importStyle(String url, SACMediaList mediaList, String arg2)
            throws CSSException {
        CSSImportedStyle importedStyle = new CSSImportedStyleImpl();
        importedStyle.setPath(url);
        importedStyle.setSACMediaList(mediaList);
        this.importedStyle.add(importedStyle);
    }

    public void namespaceDeclaration(String arg0, String arg1)
            throws CSSException {
        // Not used
    }

    public void property(String property, LexicalUnit propertyValue,
            boolean arg2) throws CSSException {
        // Not used
    }

    protected void setNodeStack(Stack nodeStack) {
        // Not used
    }

    public void startDocument(InputSource arg0) throws CSSException {
        importedStyle = new HashSet<CSSImportedStyle>();
    }

    public void startFontFace() throws CSSException {
        // Not used
    }

    public void startMedia(SACMediaList mediaList) throws CSSException {
        // Not used
    }

    public void startPage(String arg0, String arg1) throws CSSException {
        // Not used
    }

    public void startSelector(SelectorList selectorList) throws CSSException {
        // Not used
    }

}