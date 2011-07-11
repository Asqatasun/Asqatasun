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
public class JavascriptContentImpl extends RelatedTextContentImpl implements
        JavascriptContent, Serializable {

    private static final long serialVersionUID = 4803044035116287774L;

    public JavascriptContentImpl() {
        super();
    }

    public JavascriptContentImpl(Date dateOfLoading, String uri) {
        super(dateOfLoading, uri);
    }

    public JavascriptContentImpl(Date dateOfLoading, String uri,
            SSP ssp) {
        super(dateOfLoading, uri, ssp);
    }

    public JavascriptContentImpl(Date dateOfLoading, String uri,
            SSP ssp, String sourceCode) {
        super(dateOfLoading, uri, ssp, sourceCode);
    }

    public JavascriptContentImpl(Date dateOfLoading, String uri,
            SSP ssp, String sourceCode, int httpStatusCode) {
        super(dateOfLoading, uri, ssp, sourceCode, httpStatusCode);
    }
}
