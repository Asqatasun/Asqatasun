package org.opens.tanaguru.entity.audit;

import java.io.Serializable;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import org.apache.commons.lang.ArrayUtils;

@Entity
public abstract class RelatedBinaryContentImpl extends RelatedContentImpl implements
        BinaryContent, RelatedContent, Serializable {

    private static final long serialVersionUID = -7599830018001646757L;
    @Column(name = "Binary_Content", length=4000000, columnDefinition="longblob")
    protected Byte[] binaryContent;

    public RelatedBinaryContentImpl() {
        super();
    }

    public RelatedBinaryContentImpl(String uri, SSP ssp) {
        super(uri, ssp);
    }

    public RelatedBinaryContentImpl(Date dateOfLoading, String uri) {
        super(dateOfLoading, uri);
    }

    public RelatedBinaryContentImpl(Date dateOfLoading, String uri, SSP ssp) {
        super(dateOfLoading, uri, ssp);
    }

    public RelatedBinaryContentImpl(
            Date dateOfLoading,
            String uri,
            SSP ssp,
            byte[] binaryContent) {
        super(dateOfLoading, uri, ssp);
        this.binaryContent = ArrayUtils.toObject(binaryContent);
    }

    public RelatedBinaryContentImpl(
            Date dateOfLoading,
            String uri,
            SSP ssp,
            byte[] binaryContent,
            int httpStatusCode) {
        super(dateOfLoading, uri, ssp, httpStatusCode);
        this.binaryContent = ArrayUtils.toObject(binaryContent);
    }

    @Override
    public byte[] getContent() {
        return ArrayUtils.toPrimitive(binaryContent);
    }

    @Override
    public void setContent(byte[] binaryContent) {
        this.binaryContent = ArrayUtils.toObject(binaryContent);
    }
}
