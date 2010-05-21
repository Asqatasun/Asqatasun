package org.opens.tanaguru.crawler.processor;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;

import org.archive.io.RecordingInputStream;
import org.archive.modules.CrawlURI;
import org.archive.modules.Processor;
import org.archive.modules.deciderules.MatchesFilePatternDecideRule;
import org.archive.modules.extractor.Link;
import org.archive.net.UURI;
import org.opens.tanaguru.crawler.ContentType;
import org.opens.tanaguru.crawler.CrawlerImpl;
import org.opens.tanaguru.crawler.extractor.listener.ExtractorCSSListener;
import org.opens.tanaguru.crawler.extractor.listener.ExtractorHTMLListener;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.factory.audit.ContentFactory;
import org.opens.tanaguru.entity.factory.subject.WebResourceFactory;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.WebResource;

/**
Processor module that convert the results of successful fetches to
tanaguru-like Web-Resources and Contents.
@author jkowalczyk
 */
@SuppressWarnings("unchecked")
public class TanaguruWriterProcessor extends Processor
        implements ExtractorHTMLListener, ExtractorCSSListener {

    private final Logger logger =
            Logger.getLogger(TanaguruWriterProcessor.class.getName());
    private static final long serialVersionUID = 3L;
    private WebResourceFactory webResourceFactory;

    /**
     * Set the webResource Factory
     * @param webResourceFactory
     */
    public void setWebResourceFactory(WebResourceFactory webResourceFactory) {
        this.webResourceFactory = webResourceFactory;
    }
    private ContentFactory contentFactory;

    /**
     * Set the content factory 
     * @param contentFactory
     */
    public void setContentFactory(ContentFactory contentFactory) {
        this.contentFactory = contentFactory;
    }
    private List<Content> contentList = new ArrayList<Content>();

    /**
     * 
     * @return the list of contents
     */
    public List<Content> getContentList() {
        return contentList;
    }
    private Set<WebResource> webResourceSet = new HashSet<WebResource>();

    /**
     *
     * @return the list of webResources
     */
    public Set<WebResource> getWebResourceSet() {
        return webResourceSet;
    }

    private Map<String, Collection<String>> contentRelationShipMap =
            Collections.synchronizedMap(new HashMap<String, Collection<String>>());
    private Map<String, Collection<String>> cssContentRelationShipMap =
            Collections.synchronizedMap(new LinkedHashMap<String, Collection<String>>());

    /**
     *
     * @return the list of webResources
     */
    public Map<String, Collection<String>> getContentRelationShipMap() {
        associateExtractedCSSContentWithParents();
        return contentRelationShipMap;
    }
    private Pattern cssFilePattern;

    /**
     *
     * @return the css File Pattern
     */
    private Pattern getCssFilePattern() {
        if (cssFilePattern == null) {
            cssFilePattern = Pattern.compile(cssRegexp);
        }
        return cssFilePattern;
    }
    private String cssRegexp;

    /**
     * Set the css Regexp
     * @param cssRegexp
     */
    public void setCssRegexp(String cssRegexp) {
        this.cssRegexp = cssRegexp;
    }
    private Pattern htmlFilePattern;

    /**
     * 
     * @return the html File Pattern
     */
    private Pattern getHtmlFilePattern() {
        if (htmlFilePattern == null) {
            htmlFilePattern = Pattern.compile(htmlRegexp);
        }
        return htmlFilePattern;
    }
    private String htmlRegexp;

    /**
     * the regular expression used to discover an html content based-on its
     * extension
     * @param htmlRegexp
     */
    public void setHtmlRegexp(String htmlRegexp) {
        this.htmlRegexp = htmlRegexp;
    }

    /**
     * 
     * @return the images file pattern
     */
    private Pattern getImageFilePattern() {
        return MatchesFilePatternDecideRule.Preset.IMAGES.getPattern();
    }

    /**
     * @param name Name of this processor.
     */
    public TanaguruWriterProcessor() {
    }

    @Override
    protected boolean shouldProcess(CrawlURI curi) {
        return isSuccess(curi);
    }

    @Override
    protected void innerProcess(CrawlURI curi) {
        UURI uuri = curi.getUURI(); // Current URI.

        // Only http and https schemes are supported.
        String scheme = uuri.getScheme();
        if (!"http".equalsIgnoreCase(scheme)
                && !"https".equalsIgnoreCase(scheme)) {
            return;
        }
        RecordingInputStream recis = curi.getRecorder().getRecordedInput();
        if (0L == recis.getResponseContentLength()) {
            return;
        }

        if (curi.getFetchStatus() != 200) {
            ContentType resourceContentType =
                    getContentTypeFromUnreacheableResource(curi.getCanonicalString());
            switch (resourceContentType) {
                case css:
                    logger.log(Level.FINEST,
                            "Unreachable CSS Resource " + curi.getURI() + " : "
                            + curi.getFetchStatus());
                    Content cssContent = contentFactory.createStylesheetContent(
                            new Date(),
                            curi.getURI(),
                            null,
                            null,
                            curi.getFetchStatus());
                    contentList.add(cssContent);
                    break;
                case html:
                    logger.log(Level.FINEST,
                            "Unreachable HTML Resource " + curi.getURI() + " : "
                            + curi.getFetchStatus());
                    Content htmlContent = contentFactory.createSSP(
                            new Date(),
                            curi.getURI(),
                            null,
                            null,
                            curi.getFetchStatus());
                    contentList.add(htmlContent);
                    break;
                case img:
                    logger.log(Level.FINEST,
                            "Unreachable Image Resource " + curi.getURI() + " : "
                            + curi.getFetchStatus());
                    Content imgContent = contentFactory.createImageContent(
                            new Date(),
                            curi.getURI(),
                            null,
                            null,
                            curi.getFetchStatus());
                    contentList.add(imgContent);
                    break;
                case misc:
                    break;
                default:
                    break;
            }
            return;
        }

        try {
            logger.log(Level.FINEST,
                    "Writing " + curi.getURI() + " : "
                    + curi.getFetchStatus());
            if (curi.getContentType().contains(ContentType.html.getType())
                    && !curi.getURI().contains("robots.txt")) {
                WebResource webResource = webResourceFactory.createPage(curi.getURI());
                webResourceSet.add(webResource);
                Content htmlContent = contentFactory.createSSP(
                        new Date(),
                        curi.getURI(),
                        getTextContent(recis.getContentReplayInputStream()),
                        (Page) webResource,
                        curi.getFetchStatus());
                contentList.add(htmlContent);
            } else if (curi.getContentType().contains(ContentType.css.getType())) {
                Content cssContent = contentFactory.createStylesheetContent(
                        new Date(),
                        curi.getURI(),
                        null,
                        getTextContent(recis.getContentReplayInputStream()),
                        curi.getFetchStatus());
                contentList.add(cssContent);
            } else if (curi.getContentType().contains(ContentType.img.getType())) {
                Content imgContent = contentFactory.createImageContent(
                        new Date(),
                        curi.getURI(),
                        null,
                        getImageContent(recis.getContentReplayInputStream(),
                        getImageExtension(curi.getURI())),
                        curi.getFetchStatus());
                contentList.add(imgContent);
            }
        } catch (IOException e) {
            curi.getNonFatalFailures().add(e);
        }
    }

    /**
     * Get the raw text of a content (css, javascript, html)
     * @param is
     * @return
     */
    private String getTextContent(InputStream is) {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        StringBuffer sb = new StringBuffer();
        try {
            while ((line = br.readLine()) != null) {
                sb.append(line + "\r");
            }
        } catch (IOException ex) {
            Logger.getLogger(CrawlerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sb.toString();
    }

    /**
     * Get the raw content of an image
     * @param is
     * @param imgExtension
     * @return
     */
    private byte[] getImageContent(InputStream is, String imgExtension) {
        // O P E N
        ByteArrayOutputStream baos = new ByteArrayOutputStream(1000);
        BufferedImage image = null;
        byte[] resultImageAsRawBytes = null;
        try {
            image = ImageIO.read(is);
            // W R I T E
            ImageIO.write(image, imgExtension, baos);
            // C L O S E
            baos.flush();
            resultImageAsRawBytes = baos.toByteArray();
            baos.close();
        } catch (IOException ex) {
            Logger.getLogger(TanaguruWriterProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultImageAsRawBytes;
    }

    /**
     * Get an image extension from the image url
     * @param imageUrl
     * @return
     */
    public String getImageExtension(String imageUrl) {
        String ext = imageUrl.substring(imageUrl.lastIndexOf('.') + 1);
        try {
            java.util.Iterator<ImageWriter> it =
                    ImageIO.getImageWritersBySuffix(ext);
            if (it.next() != null) {
                return ext;
            }
        } catch (NoSuchElementException ex) {
            return "jpg";
        }
        return "jpg";
    }

    /**
     * 
     * @param curi
     */
    @Override
    public void computeResource(CrawlURI curi) {
        Collection<String> relatedLinkToHtml = new HashSet<String>();
        String url = null;
        for (Link link : curi.getOutLinks()) {
            url = link.getDestination().toString();
            relatedLinkToHtml.add(url);
            if (contentRelationShipMap.containsKey(url)) {
                contentRelationShipMap.get(url).add(curi.getURI());
            } else {
                Collection<String> relatedHtmlToLink = new HashSet<String>();
                relatedHtmlToLink.add(curi.getURI());
                contentRelationShipMap.put(url, relatedHtmlToLink);
            }
        }
        contentRelationShipMap.put(curi.getURI(), relatedLinkToHtml);
    }

    /**
     * In case of css extractred from another css, we combine the child
     * content with its css parents. Each CSS resource has to be associated with
     * a HTML content. By keeping this relation, we can combine a child CSS
     * with the HTML contents combined with its parents.
     * @param curi
     */
    @Override
    public void computeCSSResource(CrawlURI curi) {
        Collection<String> relatedChildToParent = new HashSet<String>();
        String url;
        for (Link link : curi.getOutLinks()) {
            url = link.getDestination().toString();
            relatedChildToParent.add(url);
        }
        if (!cssContentRelationShipMap.containsKey(curi.getURI())) {
            cssContentRelationShipMap.put(curi.getURI(), relatedChildToParent);
        } else {
            cssContentRelationShipMap.get(curi.getURI()).addAll(relatedChildToParent);
        }
    }

    /**
     * At the end of the extraction, we combine contents extracted from a css
     * with the parent content(s) of this css
     */
    private void associateExtractedCSSContentWithParents() {

        for (Map.Entry<String, Collection<String>> e :
                cssContentRelationShipMap.entrySet()) {

            if (!e.getValue().isEmpty()) {
                for (Map.Entry<String, Collection<String>> f :
                        contentRelationShipMap.entrySet()) {
                    // We test the presence of the css from which css have been
                    // extracted in the content relationship map collections
                    if (f.getValue().contains(e.getKey())) {
                        // we associate the parent content with its new
                        // children
                        contentRelationShipMap.get(f.getKey()).addAll(e.getValue());
                    }
                }
            }
        }
    }

    /**
     * This methods enables to get the type of resource from its uri.
     * In case of unreachable resource (404/403 errors), the return content is
     * a html page. So we can't use the content type of the returned page to
     * determine the type of the content we try to reach. In this case, we use
     * the uri extension, based-on regular expressions.
     * @param uri
     * @return
     */
    private ContentType getContentTypeFromUnreacheableResource(String uri) {
        if (MatchesFilePatternDecideRule.Preset.IMAGES.getPattern().matcher(uri).matches()) {
            return ContentType.img;
        } else if (getHtmlFilePattern().matcher(uri).matches()) {
            return ContentType.html;
        } else if (getCssFilePattern().matcher(uri).matches()) {
            return ContentType.css;
        }
        return ContentType.misc;
    }
}
