package org.opens.tanaguru.entity.audit;

import java.io.Serializable;

import java.util.Date;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author ADEX
 */
@Entity
@XmlRootElement
public class StylesheetContentImpl extends RelatedTextContentImpl implements
        StylesheetContent, Serializable {

    public StylesheetContentImpl() {
        super();
    }

    public StylesheetContentImpl(Date dateOfLoading, String uri) {
        super(dateOfLoading, uri);
    }

    public StylesheetContentImpl(String uri,SSP ssp) {
        super(uri, ssp);
    }

    public StylesheetContentImpl(Date dateOfLoading, String uri, SSP ssp) {
        super(dateOfLoading, uri, ssp);
    }

    public StylesheetContentImpl(Date dateOfLoading, String uri, SSP ssp,
            String sourceCode) {
        super(dateOfLoading, uri, ssp, sourceCode);
    }

    public StylesheetContentImpl(Date dateOfLoading, String uri, SSP ssp,
            String sourceCode, int httpStatusCode) {
        super(dateOfLoading, uri, ssp, sourceCode, httpStatusCode);
    }
}
