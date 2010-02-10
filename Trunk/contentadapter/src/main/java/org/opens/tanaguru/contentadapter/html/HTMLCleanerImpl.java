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

    public HTMLCleanerImpl() {
        super();
    }

    public void run() {
        try {
            HtmlCleaner cleaner = new HtmlCleaner();
            TagNode node = cleaner.clean(dirtyHTML);
            // to avoid SAX Fatal Error related to namespace
            node.removeAttribute("xml:lang");
            node.removeAttribute("xmlns:xml");
            // node.removeAttribute("xml:lang");
            CleanerProperties props = cleaner.getProperties();
            props.setUseCdataForScriptAndStyle(false);
            XmlSerializer serializer = new PrettyXmlSerializer(props);
            result = serializer.getXmlAsString(node);
        } catch (IOException ex) {
            Logger.getLogger(HTMLCleanerImpl.class.getName()).log(Level.SEVERE,
                    null, ex);
            throw new RuntimeException(ex);
        }
    }

    public String getCorrectorName() {
        return CORRECTOR_NAME;
    }
}
