package org.opens.tanaguru.service;

import java.util.List;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.subject.WebResource;
import javax.xml.bind.annotation.XmlTransient;
import org.opens.tanaguru.contentloader.ContentLoaderFactory;
import org.opens.tanaguru.contentloader.DownloaderFactory;
import org.opens.tanaguru.entity.factory.audit.ContentFactory;

/**
 * 
 * @author ADEX
 */
@XmlTransient
public interface ContentLoaderService {// TODO Write javadoc

    List<Content> loadContent(WebResource webResource);

    void setContentFactory(ContentFactory contentFactory);

    void setContentLoaderFactory(ContentLoaderFactory contentLoaderFactory);

    void setDownloaderFactory(DownloaderFactory downloaderFactory);
}
