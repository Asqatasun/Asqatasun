package org.opens.tanaguru.contentadapter.css;

import org.opens.tanaguru.contentadapter.util.AbstractResource;
import org.opens.tanaguru.contentadapter.RsrcLocator;
import org.w3c.css.sac.SACMediaList;

public class CSSResourceImpl extends AbstractResource implements CSSResource {

    private SACMediaList mediaList;
    private final String resourceName = "#StyleSheet";
    private final String defaultMediaValue = "all";
    private final int resourceType = 1;

    public CSSResourceImpl() {
        super();
    }

    public CSSResourceImpl(
            String resource,
            int lineNumber,
            RsrcLocator location) {

        super(resource, lineNumber, location);
        mediaList = new SACMediaListImpl();
        ((SACMediaListImpl) mediaList).addItem(defaultMediaValue);
    }

    public CSSResourceImpl(
            String resource,
            int lineNumber,
            RsrcLocator location,
            SACMediaList mediaList) {

        super(resource, lineNumber, location);
        this.setCssMediaList(mediaList);
    }

    @Override
    public String getRsrcName() {
        return resourceName;
    }

    @Override
    public short getRsrcType() {
        return resourceType;
    }

    @Override
    public SACMediaList getCssMediaList() {
        return mediaList;
    }

    @Override
    public void setCssMediaList(SACMediaList mediaList) {
        this.mediaList = mediaList;
    }
}
