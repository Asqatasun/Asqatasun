package org.opens.tanaguru.entity.audit;

import java.util.Set;

/**
 *
 * @author nselly
 */
public interface RelatedContent {// TODO Write javadoc

    /**
     *
     * @return the list of related raw content of the ssp
     */
    Set<? extends Content> getParentContentSet();

    /**
     *
     * @param contentList
     *          The content list to add
     */
    public void addAllParentContent(Set<? extends Content> contentList);

    /**
     *
     * @param content
     *              The content to add
     */
    public void addParentContent(Content content);
}
