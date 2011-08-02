package org.opens.tanaguru.entity.service.reference;

import org.opens.tanaguru.entity.reference.Reference;
import org.opens.tanaguru.entity.reference.Test;
import com.adex.sdk.entity.service.GenericDataService;
import java.util.List;
import java.util.Set;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.reference.Level;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface TestDataService extends GenericDataService<Test, Long> {

    /**
     *
     * @param reference
     *            the reference of the tests to find
     * @return the tests found
     */
    List<Test> findAll(Reference reference);

    /**
     *
     * @param codeArray
     * @return
     */
    List<Test> findAllByCode(String[] codeArray);

    /**
     *
     * @param codeReference
     * @param codeLevel
     * @return
     */
    List<Test> getAllByReferenceAndLevel(Reference reference, Level level);

    /**
     *
     * @param paramSet
     * @return
     */
    List<Test> getTestListFromParamSet(Set<Parameter> paramSet);

}