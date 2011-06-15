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
        private HtmlCleaner cleaner;
    private CleanerProperties props;
    private XmlSerializer serializer;

    public HTMLCleanerImpl() {
        super();
        cleaner = new HtmlCleaner();
        props = cleaner.getProperties();
        props.setOmitComments(true);
        props.setOmitXmlDeclaration(true);
        props.setOmitDoctypeDeclaration(true);
        props.setUseCdataForScriptAndStyle(true);
        props.setNamespacesAware(true);
        serializer = new PrettyXmlSerializer(props);
    }

    @Override
    public void run() {
        try {
            TagNode node = cleaner.clean(dirtyHTML);
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
