package org.opens.tanaguru.contentloader;

import java.util.Map;
import org.opens.tanaguru.entity.factory.audit.ContentFactory;
import org.opens.tanaguru.entity.service.audit.ContentDataService;

/**
 * 
 * @author ADEX
 */
public class FileContentLoaderFactoryImpl implements ContentLoaderFactory {

    private ContentDataService contentDataService;

    public void setContentDataService(ContentDataService contentDataService) {
        this.contentDataService = contentDataService;
    }
    
    @Override
    public ContentLoader create(ContentFactory contentFactory, Downloader downloader, Map<String, String> fileMap) {
        return new FileContentLoaderImpl(contentFactory, fileMap, contentDataService);
    }

}