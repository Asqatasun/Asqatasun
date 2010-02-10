package org.opens.tanaguru.entity.factory.reference;

import org.opens.tanaguru.entity.reference.NomenclatureElement;
import com.adex.sdk.entity.factory.GenericFactory;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface NomenclatureElementFactory extends
		GenericFactory<NomenclatureElement> {

	NomenclatureElement create(String value);
}
