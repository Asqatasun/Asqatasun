/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2011  Open-S Company
 *
 * This file is part of Tanaguru.
 *
 * Tanaguru is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tanaguru.entity.dao.reference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import org.opens.tanaguru.entity.reference.*;
import org.opens.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;

/**
 * 
 * @author jkowalczyk
 */
public class TestDAOImpl extends AbstractJPADAO<Test, Long> implements TestDAO {

    /**
     * The gold level code
     */
    private List<String> goldLevelCodeList = new ArrayList<String>();
    public List<String> getGoldLevelCodeList() {
        return this.goldLevelCodeList;
    }
    
    public void setGoldLevelCodeList(List<String> goldLevelCodeList) {
        this.goldLevelCodeList.addAll(goldLevelCodeList);
    }

    /**
     * The silver level code
     */
    private List<String> silverLevelCodeList = new ArrayList<String>();
    public List<String> getSilverLevelCodeList() {
        return this.silverLevelCodeList;
    }
    
    public void setSilverLevelCodeList(List<String> silverLevelCodeList) {
        this.silverLevelCodeList.addAll(silverLevelCodeList);
    }

    private LevelDAO levelDAO;
    @Override
    public void setLevelDAO(LevelDAO levelDAO) {
        this.levelDAO = levelDAO;
        if (!bronzeLevelCodeByRefMap.isEmpty()) {
            for (Map.Entry<String, String> entry : bronzeLevelCodeByRefMap.entrySet()) {
                this.bronzeLevelByRefMap.put(entry.getKey(), levelDAO.retrieveByCode(entry.getValue()));
            }
        }
    }

    private Map<String, String> bronzeLevelCodeByRefMap = new HashMap<String, String>();
    private Map<String, Level> bronzeLevelByRefMap = new HashMap<String, Level>();
    @Override
    public void setBronzeLevelCodeByRefMap(Map<String, String> bronzeLevelCodeByRefMap) {
        this.bronzeLevelCodeByRefMap = bronzeLevelCodeByRefMap;
        if (levelDAO != null) {
            for (Map.Entry<String, String> entry : bronzeLevelCodeByRefMap.entrySet()) {
                this.bronzeLevelByRefMap.put(entry.getKey(), levelDAO.retrieveByCode(entry.getValue()));
            }
        }
    }

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
        if (goldLevelCodeList.contains(level.getCode())) {
            return retrieveAll(reference);
        } else  {
            StringBuilder queryStr = new StringBuilder();
            queryStr.append("SELECT t FROM ");
            queryStr.append(getEntityClass().getName());
            queryStr.append(" t WHERE");
            if (silverLevelCodeList.contains(level.getCode())) {
                queryStr.append(" (");
            }
            queryStr.append(" t.level = :bronzeLevel");
            if (silverLevelCodeList.contains(level.getCode())) {
                queryStr.append(" OR t.level = :silverLevel)");
            }
            queryStr.append(" AND t.criterion.reference = :reference");
            Query query = entityManager.createQuery(queryStr.toString());
            query.setParameter("bronzeLevel", bronzeLevelByRefMap.get(reference.getCode()));
            if (silverLevelCodeList.contains(level.getCode())) {
                query.setParameter("silverLevel", level);
            }
            query.setParameter("reference", reference);
            query.setHint("org.hibernate.cacheable", "true");
            return query.getResultList();
        }
    }
    
    @Override
    public List<Test> retrieveAllByCriterion(Criterion criterion) {
        StringBuilder queryStr = new StringBuilder();
        queryStr.append("SELECT t FROM ");
        queryStr.append(getEntityClass().getName());
        queryStr.append(" t WHERE");
        queryStr.append(" t.criterion = :criterion");
        Query query = entityManager.createQuery(queryStr.toString());
        query.setParameter("criterion", criterion);
        query.setHint("org.hibernate.cacheable", "true");
        return query.getResultList();
    }

    @Override
    public List<Test> retrieveAllByReferenceAndCriterion(
            Reference reference,
            List<Criterion> criterion) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}