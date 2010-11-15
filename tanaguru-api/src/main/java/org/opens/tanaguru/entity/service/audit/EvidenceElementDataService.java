package org.opens.tanaguru.entity.service.audit;

import org.opens.tanaguru.entity.audit.EvidenceElement;
import com.adex.sdk.entity.service.GenericDataService;
import java.util.Collection;
import org.opens.tanaguru.entity.audit.SourceCodeRemark;

/**
 * 
 * @author jkowalczyk
 */
public interface EvidenceElementDataService extends
		GenericDataService<EvidenceElement, Long> {

    public Collection<EvidenceElement> findAllBySourceCodeRemark(SourceCodeRemark sourceCodeRemark);

}
