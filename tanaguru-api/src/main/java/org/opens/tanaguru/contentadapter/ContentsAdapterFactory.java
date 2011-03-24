/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.contentadapter;

import java.util.List;
import org.opens.tanaguru.entity.audit.Content;

/**
 *
 * @author enzolalay
 */
public interface ContentsAdapterFactory {// TODO Write javadoc

    ContentsAdapter create(List<Content> contentList, boolean writeCleanHtmlInFile, String tempFolderRootPath, HTMLCleaner htmlCleaner, HTMLParser htmlParser);
}
