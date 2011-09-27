package org.opens.tanaguru.service;

import java.util.List;
import java.util.Map;
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

    @Override
    public List<Content> loadContent(WebResource webResource) {
        ContentLoader contentLoader = contentLoaderFactory.create(contentFactory, downloaderFactory.create(), null);
        contentLoader.setWebResource(webResource);
        contentLoader.run();
        return contentLoader.getResult();
    }
    
    @Override
    public List<Content> loadContent(WebResource webResource, Map<String, String> fileMap) {
        ContentLoader contentLoader = contentLoaderFactory.create(contentFactory, null, fileMap);
        contentLoader.setWebResource(webResource);
        contentLoader.run();
        return contentLoader.getResult();
    }

    @Override
    public void setContentFactory(ContentFactory contentFactory) {
        this.contentFactory = contentFactory;
    }

    @Override
    public void setContentLoaderFactory(ContentLoaderFactory contentLoaderFactory) {
        this.contentLoaderFactory = contentLoaderFactory;
    }

    @Override
    public void setDownloaderFactory(DownloaderFactory downloaderFactory) {
        //DO NOTHING HERE
    }

}