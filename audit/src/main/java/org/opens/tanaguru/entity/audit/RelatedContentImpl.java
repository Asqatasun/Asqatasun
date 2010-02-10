package org.opens.tanaguru.entity.audit;

import java.io.Serializable;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlTransient;

@Entity
public abstract class RelatedContentImpl extends ContentImpl implements
        RelatedContent, Serializable {

    @ManyToOne
    @JoinColumn(name = "Id_Ssp")
    protected SSPImpl ssp;

    public RelatedContentImpl() {
        super();
    }

    public RelatedContentImpl(Date dateOfLoading, String uri) {
        super(dateOfLoading, uri);
    }

    public RelatedContentImpl(Date dateOfLoading, String uri, SSP ssp) {
        super(dateOfLoading, uri);
        this.ssp = (SSPImpl) ssp;
    }

    @XmlTransient
    public SSP getSSP() {
        return ssp;
    }

    public void setSSP(SSP ssp) {
        this.ssp = (SSPImpl) ssp;
    }
}
