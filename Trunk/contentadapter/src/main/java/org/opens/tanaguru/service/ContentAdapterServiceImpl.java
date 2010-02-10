package org.opens.tanaguru.service;

import java.util.List;
import org.opens.tanaguru.contentadapter.ContentsAdapter;
import org.opens.tanaguru.entity.audit.Content;

/**
 * 
 * @author ADEX
 */
public class ContentAdapterServiceImpl implements ContentAdapterService {

    private ContentsAdapter adapter;

    public ContentAdapterServiceImpl() {
        super();
    }

    public List<Content> adaptContent(List<Content> contentList) {
        adapter.setContentList(contentList);
        adapter.run();
        return adapter.getResult();
    }

    public void setAdapter(ContentsAdapter adapter) {
        this.adapter = adapter;
    }
}
