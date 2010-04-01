package org.opens.tanaguru.contentadapter.css;

import org.opens.tanaguru.contentadapter.Resource;
import org.w3c.css.sac.SACMediaList;

public interface CSSResource extends Resource {
    /**
     *
     * @return the Resource type
     */
    SACMediaList getCssMediaList();

    /**
     *
     * @return the Resource type
     */
    void setCssMediaList(SACMediaList media);
}
