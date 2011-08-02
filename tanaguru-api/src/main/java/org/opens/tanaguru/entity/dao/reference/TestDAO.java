package org.opens.tanaguru.entity.dao.reference;

import org.opens.tanaguru.entity.reference.Criterion;
import org.opens.tanaguru.entity.reference.Reference;
import org.opens.tanaguru.entity.reference.Test;
import com.adex.sdk.entity.dao.GenericDAO;
import java.util.List;
import org.opens.tanaguru.entity.reference.Level;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface TestDAO extends GenericDAO<Test, Long> {

    /**
     *
     * @param reference
     *            the reference of the tests to find
     * @return the collection of tests found
     */
    List<Test> retrieveAll(Reference reference);

    /**
     * 
     * @param reference
     * @param criterion
     * @return
     */
    List<Test> retrieveAllByReferenceAndCriterion(Reference reference, List<Criterion> criterion);

    /**
     *
     * @param codeArray
     * @return
     */
    List<Test> retrieveAllByCode(String[] codeArray);

    /**
     * 
     * @param referenceCode
     * @param levelCode
     * @return
     */
    List<Test> retrieveAllByReferenceAndLevel(Reference reference, Level level);

    /**
     * 
     * @param levelDAO
     */
    void setLevelDAO(LevelDAO levelDAO);

    /**
     * 
     * @param bronzeIdIndex
     */
    void setBronzeIdIndex(int bronzeIdIndex);

}