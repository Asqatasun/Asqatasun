package org.opens.tanaguru.entity.audit;

import java.util.List;

/**
 *
 * @author nselly
 */
public interface RelatedContent {// TODO Write javadoc

    /**
     *
     * @return the list of related raw content of the ssp
     */
    List<? extends Content> getParentContentList();

    /**
     *
     * @param contentList
     *          The content list to add
     */
    public void addAllParentContent(List<? extends Content> contentList);

    /**
     *
     * @param content
     *              The content to add
     */
    public void addParentContent(Content content);
}
