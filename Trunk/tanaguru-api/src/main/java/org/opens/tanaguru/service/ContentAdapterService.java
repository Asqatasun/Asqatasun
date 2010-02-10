package org.opens.tanaguru.service;

import java.util.List;
import org.opens.tanaguru.contentadapter.ContentsAdapter;
import org.opens.tanaguru.entity.audit.Content;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author ADEX
 */
@XmlTransient
public interface ContentAdapterService {// TODO Write javadoc

    /**
     *
     * @param contentList
     * @return
     */
    List<Content> adaptContent(List<Content> contentList);

    /**
     *
     * @param adapter
     */
    void setAdapter(ContentsAdapter adapter);
}
