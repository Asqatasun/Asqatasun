package org.opens.tanaguru.contentadapter.js;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.opens.tanaguru.contentadapter.ContentAdapter;
import org.opens.tanaguru.contentadapter.Resource;
import org.opens.tanaguru.contentadapter.util.URLIdentifier;
import org.opens.tanaguru.contentloader.Downloader;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.factory.audit.ContentFactory;

/**
 * 
 * @author ADEX
 */
public abstract class AbstractContentAdapter implements ContentAdapter {

    protected Downloader downloader;
    protected Resource resource;
    protected SSP ssp;
    protected URLIdentifier urlIdentifier;
    protected List<Content> contentList = new ArrayList<Content>();
    protected ContentFactory contentFactory;

    public AbstractContentAdapter(ContentFactory contentFactory, URLIdentifier urlIdentifier, Downloader downloader) {
        super();
        this.contentFactory = contentFactory;
        this.urlIdentifier = urlIdentifier;
        this.downloader = downloader;
    }

    public SSP getSSP() {
        return ssp;
    }

    public void setDownloader(Downloader downloader) {
        this.downloader = downloader;
    }

    public void setSSP(SSP ssp) {
        this.ssp = ssp;
        contentList = new ArrayList<Content>();
        try {
            urlIdentifier.setUrl(new URL(ssp.getURI()));
        } catch (MalformedURLException ex) {
            Logger.getLogger(AbstractContentAdapter.class.getName()).log(Level.WARNING, null, ex);
        }
    }

    public void setURLIdentifier(URLIdentifier urlIdentifier) {
        this.urlIdentifier = urlIdentifier;
    }

    public List<Content> getContentList() {
        return contentList;
    }

    public void setContentFactory(ContentFactory contentFactory) {
        this.contentFactory = contentFactory;
    }
}
