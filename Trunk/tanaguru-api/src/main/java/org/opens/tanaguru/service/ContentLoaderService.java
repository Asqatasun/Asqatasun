package org.opens.tanaguru.service;

import java.util.List;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.contentloader.ContentLoader;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author ADEX
 */
@XmlTransient
public interface ContentLoaderService {

    List<Content> loadContent(WebResource webResource);

    void setContentsLoader(ContentLoader contentsLoader);
}
