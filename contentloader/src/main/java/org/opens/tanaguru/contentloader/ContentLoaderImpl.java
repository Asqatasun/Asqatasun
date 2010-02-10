package org.opens.tanaguru.contentloader;

import java.util.ArrayList;
import org.opens.tanaguru.entity.factory.audit.ContentFactory;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.subject.WebResource;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author ADEX
 */
public class ContentLoaderImpl implements ContentLoader {

    private ContentFactory contentFactory;
    private Downloader downloader;
    private List<Content> result;
    private WebResource webResource;

    public ContentLoaderImpl() {
        super();
    }

    public List<Content> getResult() {
        return result;
    }

    public WebResource getWebResource() {
        return webResource;
    }

    public void run() {
        result = run(webResource);
    }

    private List<Content> run(WebResource webResource) {
        List<Content> localResult = new ArrayList<Content>();

        if (webResource instanceof Page) {
            downloader.setURL(webResource.getURL());
            downloader.run();
            String stringContent = downloader.getResult();
            Content content = contentFactory.createSSP(new Date(), webResource.getURL(), stringContent, (Page) webResource);
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

    public void setContentFactory(ContentFactory contentFactory) {
        this.contentFactory = contentFactory;
    }

    public void setDownloader(Downloader downloader) {
        this.downloader = downloader;
    }

    public void setWebResource(WebResource webResource) {
        this.webResource = webResource;
    }
}
