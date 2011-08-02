package org.opens.tanaguru.entity.dao.reference;

import javax.persistence.Query;
import org.opens.tanaguru.entity.reference.Level;

import org.opens.tanaguru.entity.reference.Reference;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.reference.TestImpl;
import com.adex.sdk.entity.dao.jpa.AbstractJPADAO;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.opens.tanaguru.entity.reference.Criterion;

public class TestDAOImpl extends AbstractJPADAO<Test, Long> implements TestDAO {

    /**
     * The gold level code
     */
    private String goldLevelCode = "Or";
    public void setGoldLevelCode(String goldLevelCode) {
        this.goldLevelCode = goldLevelCode;
    }

    /**
     * The silver level code
     */
    private String silverLevelCode = "Ar";
    public void setSilverLevelCode(String silverLevelCode) {
        this.silverLevelCode = silverLevelCode;
    }

    private LevelDAO levelDAO;
    @Override
    public void setLevelDAO(LevelDAO levelDAO) {
        this.levelDAO = levelDAO;
        if (bronzeIdIndex != -1) {
            bronzeLevel = levelDAO.read(Long.valueOf(bronzeIdIndex));
        }
    }

    private int bronzeIdIndex = -1;
    @Override
    public void setBronzeIdIndex(int bronzeIdIndex) {
        this.bronzeIdIndex = bronzeIdIndex;
        if (levelDAO != null) {
            bronzeLevel = levelDAO.read(Long.valueOf(bronzeIdIndex));
        }
    }

    private Level bronzeLevel;

    public TestDAOImpl() {
        super();
    }

    @Override
    protected Class<TestImpl> getEntityClass() {
        return TestImpl.class;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Test> retrieveAll(Reference reference) {
        Query query = entityManager.createQuery("SELECT t FROM "
                + getEntityClass().getName() + " t"
                + " WHERE t.criterion.reference = :reference");
        query.setParameter("reference", reference);
        query.setHint("org.hibernate.cacheable", "true");
        return (List<Test>)query.getResultList();
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<Test> retrieveAllByCode(String[] codeArray) {
        if (codeArray.length == 0) {
            return new ArrayList<Test>();
        }

        StringBuilder stringBuilder = new StringBuilder("SELECT t FROM "
                + getEntityClass().getName() + " t" + " WHERE t.code IN (");

        boolean first = true;
        for (String code : codeArray) {
            if (!first) {
                stringBuilder.append(',');
            } else {
                first = false;
            }
            stringBuilder.append("'");
            stringBuilder.append(code);
            stringBuilder.append("'");
        }
        stringBuilder.append(')');
        Query query = entityManager.createQuery(stringBuilder.toString());
        return (List<Test>)query.getResultList();
    }

    @Override
    public List<Test> retrieveAllByReferenceAndLevel(Reference reference, Level level) {
        if (StringUtils.equalsIgnoreCase(level.getCode(), goldLevelCode)) {
            return retrieveAll(reference);
        } else  {
            StringBuilder queryStr = new StringBuilder();
            queryStr.append("SELECT t FROM ");
            queryStr.append(getEntityClass().getName());
            queryStr.append(" t WHERE");
            if (StringUtils.equalsIgnoreCase(level.getCode(), silverLevelCode)) {
                queryStr.append(" (");
            }
            queryStr.append(" t.level = :bronzeLevel");
            if (StringUtils.equalsIgnoreCase(level.getCode(), silverLevelCode)) {
                queryStr.append(" OR t.level = :silverLevel)");
            }
            queryStr.append(" AND t.criterion.reference = :reference");
            Query query = entityManager.createQuery(queryStr.toString());
            query.setParameter("bronzeLevel", bronzeLevel);
            if (StringUtils.equalsIgnoreCase(level.getCode(), silverLevelCode)) {
                query.setParameter("silverLevel", level);
            }
            query.setParameter("reference", reference);
            query.setHint("org.hibernate.cacheable", "true");
            return query.getResultList();
        }
    }

    @Override
    public List<Test> retrieveAllByReferenceAndCriterion(
            Reference reference,
            List<Criterion> criterion) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}