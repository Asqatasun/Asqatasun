package org.opens.tanaguru.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.opens.tanaguru.contentadapter.ContentAdapter;
import org.opens.tanaguru.contentadapter.ContentAdapterFactory;
import org.opens.tanaguru.contentadapter.ContentsAdapter;
import org.opens.tanaguru.contentadapter.ContentsAdapterFactory;
import org.opens.tanaguru.contentadapter.HTMLCleanerFactory;
import org.opens.tanaguru.contentadapter.HTMLParserFactory;
import org.opens.tanaguru.contentadapter.util.URLIdentifierFactory;
import org.opens.tanaguru.contentloader.DownloaderFactory;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.factory.audit.ContentFactory;

/**
 * 
 * @author ADEX
 */
public class ContentAdapterServiceImpl implements ContentAdapterService {

    private ContentFactory contentFactory;
    private boolean writeCleanHtmlInFile;
    private String tempFolderRootPath;
    private ContentsAdapterFactory contentsAdapterFactory;
    private HTMLCleanerFactory htmlCleanerFactory;
    private HTMLParserFactory htmlParserFactory;
    private Set<ContentAdapterFactory> contentAdapterFactorySet;
    private URLIdentifierFactory urlIdentifierFactory;
    private DownloaderFactory downloaderFactory;

    public ContentAdapterServiceImpl() {
        super();
    }

    public void setTempFolderRootPath(String tempFolderRootPath) {
        this.tempFolderRootPath = tempFolderRootPath;
    }

    public List<Content> adaptContent(List<Content> contentList) {

        Set<ContentAdapter> contentAdapterSet = new HashSet<ContentAdapter>();
        for (ContentAdapterFactory contentAdapterFactory : contentAdapterFactorySet) {
            contentAdapterSet.add(contentAdapterFactory.create(contentFactory, urlIdentifierFactory.create(), downloaderFactory.create()));
        }

        ContentsAdapter adapter = contentsAdapterFactory.create(contentList, writeCleanHtmlInFile, tempFolderRootPath, htmlCleanerFactory.create(), htmlParserFactory.create(contentAdapterSet));
        adapter.run();
        return adapter.getResult();
    }

    public void setContentFactory(ContentFactory contentFactory) {
        this.contentFactory = contentFactory;
    }

    public void setWriteCleanHtmlInFile(boolean writeCleanHtmlInFile) {
        this.writeCleanHtmlInFile = writeCleanHtmlInFile;
    }

    public void setContentsAdapterFactory(ContentsAdapterFactory contentsAdapterFactory) {
        this.contentsAdapterFactory = contentsAdapterFactory;
    }

    public void setHtmlCleanerFactory(HTMLCleanerFactory htmlCleanerFactory) {
        this.htmlCleanerFactory = htmlCleanerFactory;
    }

    public void setHtmlParserFactory(HTMLParserFactory htmlParserFactory) {
        this.htmlParserFactory = htmlParserFactory;
    }

    public void setContentAdapterFactorySet(Set<ContentAdapterFactory> contentAdapterFactorySet) {
        this.contentAdapterFactorySet = contentAdapterFactorySet;
    }

    public void setUrlIdentifierFactory(URLIdentifierFactory urlIdentifierFactory) {
        this.urlIdentifierFactory = urlIdentifierFactory;
    }

    public void setDownloaderFactory(DownloaderFactory downloaderFactory) {
        this.downloaderFactory = downloaderFactory;
    }
}
