package org.opens.tanaguru.contentloader;

import java.util.ArrayList;
import java.util.Map;
import org.opens.tanaguru.entity.factory.audit.ContentFactory;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.subject.WebResource;
import java.util.Date;
import java.util.List;
import org.apache.commons.httpclient.HttpStatus;
import org.opens.tanaguru.entity.service.audit.ContentDataService;
import org.opens.tanaguru.util.FileNaming;

/**
 * 
 * @author jkowalczyk
 */
public class FileContentLoaderImpl implements ContentLoader {

    private ContentFactory contentFactory;
    private Map<String, String> fileMap;
    private List<Content> result;
    private WebResource webResource;

    /**
     * Constructor
     * 
     * @param contentFactory
     * @param fileMap
     * @param contentDataService
     */
    FileContentLoaderImpl(
            ContentFactory contentFactory,
            Map<String, String> fileMap,
            ContentDataService contentDataService) {
        super();
        this.contentFactory = contentFactory;
        this.fileMap = fileMap;
    }

    @Override
    public List<Content> getResult() {
        return result;
    }

    @Override
    public WebResource getWebResource() {
        return webResource;
    }

    @Override
    public void run() {
        result = run(webResource);
    }

    private List<Content> run(WebResource webResource) {
        List<Content> localResult = new ArrayList<Content>();
        if (webResource instanceof Page) {
            Content content = contentFactory.createSSP(
                    new Date(),
                    webResource.getURL(),
                    fileMap.get(FileNaming.removeFilePrefix(webResource.getURL())),
                    (Page) webResource,
                    HttpStatus.SC_OK);
            localResult.add(content);
        }
        if (webResource instanceof Site) {
            Site site = (Site) webResource;
            for (WebResource component : site.getComponentList()) {
                localResult.addAll(run(component));
            }
        }

        return localResult;
    }

    @Override
    public void setContentFactory(ContentFactory contentFactory) {
        this.contentFactory = contentFactory;
    }

    @Override
    public void setDownloader(Downloader downloader) {
//        this.downloader = downloader;
    }

    @Override
    public void setWebResource(WebResource webResource) {
        this.webResource = webResource;
    }

}