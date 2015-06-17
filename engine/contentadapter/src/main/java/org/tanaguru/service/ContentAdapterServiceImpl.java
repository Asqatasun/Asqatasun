package org.tanaguru.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.tanaguru.contentadapter.*;
import org.tanaguru.contentadapter.util.URLIdentifierFactory;
import org.tanaguru.contentloader.DownloaderFactory;
import org.tanaguru.entity.audit.Content;
import org.tanaguru.entity.service.audit.ContentDataService;

/**
 * 
 * @author jkowalczyk
 */
public class ContentAdapterServiceImpl implements ContentAdapterService {

    private boolean writeCleanHtmlInFile;
    private String tempFolderRootPath;
    private ContentsAdapterFactory contentsAdapterFactory;
    private HTMLCleanerFactory htmlCleanerFactory;
    private HTMLParserFactory htmlParserFactory;
    private Set<ContentAdapterFactory> contentAdapterFactorySet;
    private URLIdentifierFactory urlIdentifierFactory;
    private DownloaderFactory downloaderFactory;
    private ContentDataService contentDataService;

    public ContentAdapterServiceImpl() {
        super();
    }

    @Override
    public void setTempFolderRootPath(String tempFolderRootPath) {
        this.tempFolderRootPath = tempFolderRootPath;
    }

    @Override
    public Collection<Content> adaptContent(Collection<Content> contentList) {

        Set<ContentAdapter> contentAdapterSet = new HashSet<>();
        
        for (ContentAdapterFactory contentAdapterFactory : contentAdapterFactorySet) {
            contentAdapterSet.add(contentAdapterFactory.create(
                    urlIdentifierFactory.create(), 
                    downloaderFactory.create(), 
                    contentDataService));
        }

        ContentsAdapter adapter = contentsAdapterFactory.create(
                contentList, 
                writeCleanHtmlInFile, 
                tempFolderRootPath, 
                htmlCleanerFactory.create(), 
                htmlParserFactory.create(contentAdapterSet));
        adapter.run();
        return adapter.getResult();
    }

    @Override
    public void setWriteCleanHtmlInFile(boolean writeCleanHtmlInFile) {
        this.writeCleanHtmlInFile = writeCleanHtmlInFile;
    }

    @Override
    public void setContentsAdapterFactory(ContentsAdapterFactory contentsAdapterFactory) {
        this.contentsAdapterFactory = contentsAdapterFactory;
    }

    @Override
    public void setHtmlCleanerFactory(HTMLCleanerFactory htmlCleanerFactory) {
        this.htmlCleanerFactory = htmlCleanerFactory;
    }

    @Override
    public void setHtmlParserFactory(HTMLParserFactory htmlParserFactory) {
        this.htmlParserFactory = htmlParserFactory;
    }

    @Override
    public void setContentAdapterFactorySet(Set<ContentAdapterFactory> contentAdapterFactorySet) {
        this.contentAdapterFactorySet = contentAdapterFactorySet;
    }

    @Override
    public void setUrlIdentifierFactory(URLIdentifierFactory urlIdentifierFactory) {
        this.urlIdentifierFactory = urlIdentifierFactory;
    }

    @Override
    public void setDownloaderFactory(DownloaderFactory downloaderFactory) {
        this.downloaderFactory = downloaderFactory;
    }

    @Override
    public void setContentDataService(ContentDataService contentDataService) {
        this.contentDataService = contentDataService;
    }
    
}