package org.opens.tanaguru.service;

import java.util.List;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.contentloader.ContentLoader;

/**
 * 
 * @author ADEX
 */
public class ContentLoaderServiceImpl implements ContentLoaderService {

    private ContentLoader contentLoader;

    public ContentLoaderServiceImpl() {
        super();
    }

    public List<Content> loadContent(WebResource webResource) {
        contentLoader.setWebResource(webResource);
        contentLoader.run();
        return contentLoader.getResult();
    }

    public void setContentsLoader(ContentLoader contentsLoader) {
        this.contentLoader = contentsLoader;
    }
}
