package org.opens.tanaguru.entity.service.subject;

import java.util.List;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.entity.factory.subject.WebResourceFactory;
import org.opens.tanaguru.entity.dao.subject.WebResourceDAO;
import com.adex.sdk.entity.service.AbstractGenericDataService;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.util.FileNaming;

/**
 * 
 * @author ADEX
 */
public class WebResourceDataServiceImpl extends AbstractGenericDataService<WebResource, Long> implements
        WebResourceDataService {

    public WebResourceDataServiceImpl() {
        super();
    }

    @Override
    public Page createPage(String url) {
        return ((WebResourceFactory) entityFactory).createPage(FileNaming.addProtocolToUrl(url));
    }

    @Override
    public Site createSite(String url) {
        return ((WebResourceFactory) entityFactory).createSite(url);
    }

    @Override
    public WebResource getByUrl(String url) {
        return ((WebResourceDAO) entityDao).findByUrl(url);
    }

    @Override
    public WebResource read(Long key) {
        WebResource entity = super.read(key);
        return entity;
    }

    protected void deepLoad(ProcessResult processResult) {
        for (ProcessResult childResult : processResult.getChildResultList()) {
            deepLoad(childResult);
        }
    }

    @Override
    public WebResource getByUrlAndParentWebResource(String url, WebResource webResource) {
        return ((WebResourceDAO) entityDao).findByUrlAndParentWebResource(url, webResource);
    }

    @Override
    public List<WebResource> getWebResourceFromItsParent(WebResource webResource, int start, int chunkSize) {
        return ((WebResourceDAO) entityDao).findWebResourceFromItsParent(
                webResource,
                start,
                chunkSize);
    }

    @Override
    public Long getNumberOfChildWebResource(WebResource webResource) {
        return ((WebResourceDAO) entityDao).findNumberOfChildWebResource(webResource);
    }

}