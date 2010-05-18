package org.opens.tanaguru.entity.subject;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class PageImpl extends WebResourceImpl implements Page, Serializable {

    public PageImpl() {
        super();
    }

    public PageImpl(String url) {
        super(url);
    }
}
