package org.opens.tanaguru.entity.subject;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class PageImpl extends WebResourceImpl implements Page, Serializable {

    private static final long serialVersionUID = 4535008822069775295L;
    
    public PageImpl() {
        super();
    }

    public PageImpl(String url) {
        super(url);
    }
}
