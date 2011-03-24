package org.opens.tanaguru.service;

import java.util.List;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.contentloader.ContentLoader;
import org.opens.tanaguru.contentloader.ContentLoaderFactory;
import org.opens.tanaguru.contentloader.DownloaderFactory;
import org.opens.tanaguru.entity.factory.audit.ContentFactory;

/**
 * 
 * @author ADEX
 */
public class ContentLoaderServiceImpl implements ContentLoaderService {

    private ContentFactory contentFactory;
    private ContentLoaderFactory contentLoaderFactory;
    private DownloaderFactory downloaderFactory;

    public ContentLoaderServiceImpl() {
        super();
    }

    public List<Content> loadContent(WebResource webResource) {
        ContentLoader contentLoader = contentLoaderFactory.create(contentFactory, downloaderFactory.create());
        contentLoader.setWebResource(webResource);
        contentLoader.run();
        return contentLoader.getResult();
    }

    public void setContentFactory(ContentFactory contentFactory) {
        this.contentFactory = contentFactory;
    }

    public void setContentLoaderFactory(ContentLoaderFactory contentLoaderFactory) {
        this.contentLoaderFactory = contentLoaderFactory;
    }

    public void setDownloaderFactory(DownloaderFactory downloaderFactory) {
        this.downloaderFactory = downloaderFactory;
    }
}
