package org.opens.tanaguru.contentadapter;

import java.util.List;
import org.opens.tanaguru.contentadapter.util.URLIdentifier;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.contentloader.Downloader;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.factory.audit.ContentFactory;

/**
 * 
 * @author Adex
 */
public interface ContentAdapter {// TODO Write javadoc

    String getAdaptation();

    List<Content> getContentList();

    SSP getSSP();

    void setDownloader(Downloader downloader);

    void setParser(ContentParser parser);

    void setURLIdentifier(URLIdentifier urlIdentifier);

    void setContentFactory(ContentFactory contentFactory);

    void setSSP(SSP ssp);
}
