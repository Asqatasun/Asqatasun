package org.opens.tanaguru.entity.audit;

import java.io.Serializable;

import java.util.Date;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author jkowalczyk
 */
@Entity
@XmlRootElement
public class ImageContentImpl extends RelatedBinaryContentImpl implements
        ImageContent, Serializable {

    public ImageContentImpl() {
        super();
    }

    public ImageContentImpl(String uri, SSP ssp) {
        super(uri, ssp);
    }

    public ImageContentImpl(Date dateOfLoading, String uri) {
        super(dateOfLoading, uri);
    }

    public ImageContentImpl(Date dateOfLoading, String uri, SSP ssp) {
        super(dateOfLoading, uri, ssp);
    }

    public ImageContentImpl(Date dateOfLoading, String uri, SSP ssp,
            byte[] binaryContent) {
        super(dateOfLoading, uri, ssp, binaryContent);
    }

    public ImageContentImpl(Date dateOfLoading, String uri, SSP ssp,
            byte[] binaryContent, int httpStatusCode) {
        super(dateOfLoading, uri, ssp, binaryContent, httpStatusCode);
    }
}
