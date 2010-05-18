package org.opens.tanaguru.entity.factory.reference;

import org.opens.tanaguru.entity.reference.StandardMessage;
import com.adex.sdk.entity.factory.GenericFactory;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface StandardMessageFactory extends GenericFactory<StandardMessage> {

	StandardMessage create(String code, String text);
}
