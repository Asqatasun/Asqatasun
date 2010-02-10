package org.opens.tanaguru.contentloader;

import java.util.List;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.entity.factory.audit.ContentFactory;

/**
 * 
 * @author ADEX
 */
public interface ContentLoader {

    List<Content> getResult();

    WebResource getWebResource();

    void run();

    void setContentFactory(ContentFactory contentFactory);

    void setDownloader(Downloader downloader);

    void setWebResource(WebResource webResource);
}
