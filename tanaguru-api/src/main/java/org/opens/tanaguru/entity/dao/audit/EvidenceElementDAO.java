package org.opens.tanaguru.entity.dao.audit;

import java.util.Collection;
import com.adex.sdk.entity.dao.GenericDAO;
import org.opens.tanaguru.entity.audit.Evidence;
import org.opens.tanaguru.entity.audit.EvidenceElement;

/**
 * 
 * @author jkowalczyk
 */
public interface EvidenceElementDAO extends
        GenericDAO<EvidenceElement, Long> {

    Collection<EvidenceElement> retrieveAll(Evidence nomenclature,
            String nomenclatureValue);
}
