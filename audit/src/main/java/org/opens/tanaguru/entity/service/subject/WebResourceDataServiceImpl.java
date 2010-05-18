package org.opens.tanaguru.entity.service.subject;

import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.entity.factory.subject.WebResourceFactory;
import org.opens.tanaguru.entity.dao.subject.WebResourceDAO;
import com.adex.sdk.entity.service.AbstractGenericDataService;

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
    public WebResource findByUrl(String url) {
        return ((WebResourceDAO) entityDao).findByUrl(url);
    }

    /**
     * This method add the protocol to the url if the protocol is missing
     * @param url
     * @return
     */
    private String addProtocolToUrl(String url) {
//        Pattern p = Pattern.compile(windowsPathPattern);
//        Matcher m = p.matcher(url);

        if (!url.startsWith(HTTP_PROTOCOL_PREFIX)
                && !url.startsWith(HTTPS_PROTOCOL_PREFIX)
                && !url.startsWith(FILE_PROTOCOL_PREFIX)) {

            if (url.startsWith(FILE_PREFIX)) {
//                    m.find()) {
                url = FILE_PROTOCOL_PREFIX + url;
            } else {
                url = HTTP_PROTOCOL_PREFIX + url;
            }

        }
        return url;
    }
}
