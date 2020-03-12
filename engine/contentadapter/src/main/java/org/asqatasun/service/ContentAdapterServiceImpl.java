package org.asqatasun.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.asqatasun.contentadapter.*;
import org.asqatasun.contentadapter.util.URLIdentifierFactory;
import org.asqatasun.util.http.Downloader;
import org.asqatasun.entity.audit.Content;
import org.asqatasun.entity.service.audit.ContentDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 
 * @author jkowalczyk
 */
@Service("contentAdapterService")
public class ContentAdapterServiceImpl implements ContentAdapterService {

    @Value("${tempFolderRootPath:/var/tmp/asqatasun}")
    private String tempFolderRootPath;
    private ContentsAdapterFactory contentsAdapterFactory;
    private HTMLCleanerFactory htmlCleanerFactory;
    private HTMLParserFactory htmlParserFactory;
    private Set<ContentAdapterFactory> contentAdapterFactorySet;
    private URLIdentifierFactory urlIdentifierFactory;
    private ContentDataService contentDataService;

    @Autowired
    public ContentAdapterServiceImpl(ContentsAdapterFactory contentsAdapterFactory,
                                     HTMLCleanerFactory htmlCleanerFactory,
                                     HTMLParserFactory htmlParserFactory,
                                     Set<ContentAdapterFactory> contentAdapterFactorySet,
                                     URLIdentifierFactory urlIdentifierFactory,
                                     ContentDataService contentDataService) {
        this.contentsAdapterFactory = contentsAdapterFactory;
        this.htmlCleanerFactory = htmlCleanerFactory;
        this.htmlParserFactory = htmlParserFactory;
        this.contentAdapterFactorySet = contentAdapterFactorySet;
        this.urlIdentifierFactory = urlIdentifierFactory;
        this.contentDataService = contentDataService;
    }

    @Override
    public Collection<Content> adaptContent(Collection<Content> contentList) {

        Set<ContentAdapter> contentAdapterSet = new HashSet<>();
        
        for (ContentAdapterFactory contentAdapterFactory : contentAdapterFactorySet) {
            contentAdapterSet.add(contentAdapterFactory.create(
                    urlIdentifierFactory.create(),
                    contentDataService));
        }

        ContentsAdapter adapter = contentsAdapterFactory.create(
                contentList,
                tempFolderRootPath, 
                htmlCleanerFactory.create(), 
                htmlParserFactory.create(contentAdapterSet));
        adapter.run();
        return adapter.getResult();
    }
    
}
