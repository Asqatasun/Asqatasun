package org.opens.tanaguru.contentadapter.html;

import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.PrettyXmlSerializer;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XmlSerializer;

import org.opens.tanaguru.contentadapter.HTMLCleaner;

public class HTMLCleanerImpl extends AbstractHTMLCleaner implements HTMLCleaner {
    static final String CORRECTOR_NAME = "HTMLCleaner";
    private static final String XML_ATTRIBUTE = "xmlns:xml";
    private static final String LANG_ATTRIBUTE = "xml:lang";
    private HtmlCleaner cleaner;
    private CleanerProperties props;
    private XmlSerializer serializer;

    public HTMLCleanerImpl() {
        super();
        cleaner = new HtmlCleaner();
        props = cleaner.getProperties();
        props.setOmitComments(true);
        props.setOmitDoctypeDeclaration(false);
        props.setUseCdataForScriptAndStyle(true);
        serializer = new PrettyXmlSerializer(props);
    }

    @Override
    public void run() {
        try {
//            HtmlCleaner cleaner = new HtmlCleaner();
//            CleanerProperties props = cleaner.getProperties();
//            props.setAdvancedXmlEscape(true);
//            props.setAllowHtmlInsideAttributes(false);
//            props.setAllowMultiWordAttributes(true);
//            props.setIgnoreQuestAndExclam(true);
//            props.setNamespacesAware(true);
//            props.setRecognizeUnicodeChars(true);
//            props.setTranslateSpecialEntities(false);
//            props.setTreatDeprecatedTagsAsContent(false);
//            props.setTreatUnknownTagsAsContent(false);
//            props.setUseEmptyElementTags(true);
//            props.setOmitDeprecatedTags(false);
//            props.setOmitHtmlEnvelope(false);
//            props.setOmitUnknownTags(false);
//            props.setOmitXmlDeclaration(false);
//            props.setOmitComments(true);
//            props.setOmitDoctypeDeclaration(false);
//            props.setUseCdataForScriptAndStyle(true);
            // to avoid SAX Fatal Error related to namespace
            TagNode node = cleaner.clean(dirtyHTML);
            node.removeAttribute(LANG_ATTRIBUTE);
            node.removeAttribute(XML_ATTRIBUTE);
//            serializer = new PrettyXmlSerializer(props);
            result = serializer.getAsString(node);
            node=null;
        } catch (IOException ex) {
            Logger.getLogger(HTMLCleanerImpl.class.getName()).log(Level.SEVERE,
                    null, ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String getCorrectorName() {
        return CORRECTOR_NAME;
    }
}
