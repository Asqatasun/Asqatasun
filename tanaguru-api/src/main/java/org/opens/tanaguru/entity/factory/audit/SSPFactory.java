package org.opens.tanaguru.entity.factory.audit;

import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.subject.Page;
import com.adex.sdk.entity.factory.GenericFactory;
import java.util.Date;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface SSPFactory extends GenericFactory<SSP> {

    SSP create(Date dateOfLoading, String uri);

    SSP create(Date dateOfLoading, String uri, String sourceCode, Page page);
}
