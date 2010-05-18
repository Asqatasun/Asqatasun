package org.opens.tanaguru.entity.factory.reference;

import org.opens.tanaguru.entity.reference.Nomenclature;
import com.adex.sdk.entity.factory.GenericFactory;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface NomenclatureFactory extends GenericFactory<Nomenclature> {

	Nomenclature create(String string);
}
