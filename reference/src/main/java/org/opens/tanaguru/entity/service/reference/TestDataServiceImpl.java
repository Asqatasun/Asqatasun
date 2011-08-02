package org.opens.tanaguru.entity.service.reference;

import org.opens.tanaguru.entity.reference.Level;
import org.opens.tanaguru.entity.reference.Reference;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.dao.reference.TestDAO;
import com.adex.sdk.entity.service.AbstractGenericDataService;
import java.util.List;
import java.util.Set;
import org.apache.log4j.Logger;
import org.opens.tanaguru.entity.parameterization.Parameter;

/**
 * 
 * @author ADEX
 */
public class TestDataServiceImpl extends AbstractGenericDataService<Test, Long>
        implements TestDataService {

    private static final Logger LOGGER = Logger.getLogger(TestDataServiceImpl.class);

    private String levelParameterCode = "LEVEL";
    public void setLevelParameterCode(String levelParameterCode) {
        this.levelParameterCode = levelParameterCode;
    }

    private LevelDataService levelDataService;
    public void setLevelDataService(LevelDataService levelDataService) {
        this.levelDataService = levelDataService;
    }

    private ReferenceDataService referenceDataService;
    public void setReferenceDataService(ReferenceDataService referenceDataService) {
        this.referenceDataService = referenceDataService;
    }
    
    public TestDataServiceImpl() {
        super();
    }

    @Override
    public List<Test> findAll(Reference reference) {
        return ((TestDAO) entityDao).retrieveAll(reference);
    }

    @Override
    public List<Test> findAllByCode(String[] codeArray) {
        return ((TestDAO) entityDao).retrieveAllByCode(codeArray);
    }

    @Override
    public List<Test> getAllByReferenceAndLevel(Reference reference, Level level) {
        return ((TestDAO) entityDao).retrieveAllByReferenceAndLevel(reference, level);
    }

    /**
     * The parameter set contains a unique parameter that combines the referential
     * and the level parameters.
     * This method extracts these parameters to retrieve the appropriate tests.
     *
     * @param paramSet
     * @return
     */
    @Override
    public List<Test> getTestListFromParamSet(Set<Parameter> paramSet) {
        Reference reference = null;
        Level level = null;
        for (Parameter param : paramSet) {
            String paramElemcode = param.getParameterElement().getParameterElementCode();
            if (paramElemcode.equals(levelParameterCode)) {
                String[] referentialAndLevel = param.getValue().split(";");
                reference = referenceDataService.getByCode(referentialAndLevel[0]);
                level = levelDataService.getByCode(referentialAndLevel[1]);
                break;
            }
        }
        List<Test> testList = getAllByReferenceAndLevel(reference, level);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Retrieved " + testList.size() + " test for the referential "
                + reference.getLabel() + " and the level " + level.getLabel());
        }
        return testList;
    }
}