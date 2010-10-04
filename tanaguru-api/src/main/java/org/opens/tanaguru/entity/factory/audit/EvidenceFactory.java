package org.opens.tanaguru.entity.factory.audit;

import com.adex.sdk.entity.factory.GenericFactory;
import org.opens.tanaguru.entity.audit.Evidence;

/**
 * 
 * @author jkowalczyk
 */
public interface EvidenceFactory extends GenericFactory<Evidence> {

	Evidence create(String string);
}
