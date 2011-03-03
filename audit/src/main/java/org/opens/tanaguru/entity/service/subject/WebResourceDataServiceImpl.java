package org.opens.tanaguru.entity.service.subject;

import java.util.List;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.entity.factory.subject.WebResourceFactory;
import org.opens.tanaguru.entity.dao.subject.WebResourceDAO;
import com.adex.sdk.entity.service.AbstractGenericDataService;
import org.opens.tanaguru.entity.audit.ProcessResult;

/**
 * 
 * @author ADEX
 */
public class WebResourceDataServiceImpl extends AbstractGenericDataService<WebResource, Long> implements
        WebResourceDataService {

    private final String HTTP_PROTOCOL_PREFIX = "http://";
    private final String HTTPS_PROTOCOL_PREFIX = "https://";
    private final String FILE_PROTOCOL_PREFIX = "file://";
    private final String FILE_PREFIX = "/";
//    private final String windowsPathPattern = " ^([a-zA-Z]:) \\{1} | ((\\{1}) [^\\] ([^/:*?&lt;&gt;&quot;|]*(?&lt;![ ])))+)$" ;

    public WebResourceDataServiceImpl() {
        super();
    }

    @Override
    public Page createPage(String url) {
        return ((WebResourceFactory) entityFactory).createPage(addProtocolToUrl(url));
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

    /**
     * This method add the protocol to the url if the protocol is missing
     * @param url
     * @return
     */
    private String addProtocolToUrl(String url) {
        if (!url.startsWith(HTTP_PROTOCOL_PREFIX)
                && !url.startsWith(HTTPS_PROTOCOL_PREFIX)
                && !url.startsWith(FILE_PROTOCOL_PREFIX)) {

            if (url.startsWith(FILE_PREFIX)) {
                url = FILE_PROTOCOL_PREFIX + url;
            } else {
                url = HTTP_PROTOCOL_PREFIX + url;
            }
        }
        return url;
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