package org.opens.tanaguru.contentadapter.html;

import org.opens.tanaguru.contentadapter.ContentAdapter;
import org.opens.tanaguru.contentadapter.html.util.DOMContentHandlerDecorator;
import org.opens.tanaguru.contentadapter.html.util.DOMContentHandlerDecoratorImpl;
import java.io.IOException;
import java.io.StringReader;

import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Set;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.contentadapter.HTMLParser;

public class HTMLParserImpl implements HTMLParser {

    protected Set<ContentAdapter> contentAdapterSet = new HashSet<ContentAdapter>();
    protected boolean initialized = false;
    protected XMLReader saxReader;
    protected SSP ssp;

    public HTMLParserImpl() {
        super();
    }

    public Set<ContentAdapter> getContentAdapterSet() {
        return contentAdapterSet;
    }

    public SSP getSSP() {
        return ssp;
    }

    private void initialize() throws SAXException {
        if (initialized) {
            return;
        }

        // General features
        saxReader = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");

        saxReader.setFeature("http://apache.org/xml/features/allow-java-encodings", true);
        saxReader.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
        saxReader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        saxReader.setFeature("http://xml.org/sax/features/validation",false);
        saxReader.setFeature("http://apache.org/xml/features/validation/schema",false);
        saxReader.setFeature("http://apache.org/xml/features/continue-after-fatal-error", true);

        DOMContentHandlerDecorator contentHandlerDecorator = new DOMContentHandlerDecoratorImpl();
        for (ContentAdapter contentAdapter : contentAdapterSet) {
            contentHandlerDecorator.addContentHandler((ContentHandler) contentAdapter);
        }
        saxReader.setContentHandler(contentHandlerDecorator);
        initialized = true;
    }

    public void run() {
        try {
            initialize();

            for (ContentAdapter handler : contentAdapterSet) {
                handler.setSSP(ssp);
            }

            saxReader.parse(new InputSource(new StringReader(ssp.getDOM())));
        } catch (SAXException ex) {
            Logger.getLogger(HTMLParserImpl.class.getName()).log(Level.WARNING,
                    null, ex);
            // If the corrected dom cannot be parsed, the dom is emptied
            // to avoid parsing errors while proccessing and to provide the user
            // an error message
            // Bug #41 correction
            ssp.setDOM("");
        } catch (IOException ex) {
            Logger.getLogger(HTMLParserImpl.class.getName()).log(Level.WARNING,
                    null, ex);
        }
    }

    public void setContentAdapterSet(Set<ContentAdapter> contentAdapterSet) {
        this.contentAdapterSet = contentAdapterSet;
    }

    public void setSSP(SSP ssp) {
        this.ssp = ssp;
    }
}
