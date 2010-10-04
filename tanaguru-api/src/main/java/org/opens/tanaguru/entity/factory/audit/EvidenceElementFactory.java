package org.opens.tanaguru.entity.factory.audit;

import com.adex.sdk.entity.factory.GenericFactory;
import org.opens.tanaguru.entity.audit.EvidenceElement;

/**
 * 
 * @author jkowalczyk
 */
public interface EvidenceElementFactory extends
        GenericFactory<EvidenceElement> {

    EvidenceElement create(String value);
}
