package org.opens.tanaguru.entity.factory.audit;

import org.opens.tanaguru.entity.audit.Audit;
import com.adex.sdk.entity.factory.GenericFactory;
import java.util.Date;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface AuditFactory extends GenericFactory<Audit> {

    Audit create(Date dateOfCreation);
}
