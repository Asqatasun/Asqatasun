/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2020  Asqatasun.org
 *
 * This file is part of Asqatasun.
 *
 * Asqatasun is free software: you can redistribute it and/or modify
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
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.asqatasun.webapp.entity.dao.contract;

import java.util.Collection;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.asqatasun.entity.dao.AbstractJPADAO;
import org.asqatasun.webapp.entity.contract.Contract;
import org.asqatasun.webapp.entity.user.User;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jkowalczyk
 */
@Repository("contractDAO")
public class ContractDAO extends AbstractJPADAO<Contract, Long> {

    public ContractDAO() {
        super();
    }

    @Override
    protected Class<? extends Contract> getEntityClass() {
        return Contract.class;
    }

    @SuppressWarnings("unchecked")
    public Collection<Contract> findAllContractsByUser(User user) {
        Query query = entityManager.createQuery("SELECT distinct(c) FROM "
                + getEntityClass().getName() + " c"
                + " LEFT JOIN FETCH c.optionElementSet o"
                + " LEFT JOIN FETCH c.functionalitySet f"
                + " LEFT JOIN FETCH c.referentialSet r"
                + " LEFT JOIN FETCH c.scenarioSet s"
                + " WHERE c.user = :user");
        query.setParameter("user", user);
        return query.getResultList();
    }

    @Override
    public Contract read(Long id) {
        try {
            Query query = entityManager.createQuery("SELECT c FROM "
                    + getEntityClass().getName() + " c"
                    + " LEFT JOIN FETCH c.optionElementSet o"
                    + " LEFT JOIN FETCH c.functionalitySet f"
                    + " LEFT JOIN FETCH c.referentialSet r"
                    + " LEFT JOIN FETCH c.scenarioSet s"
                    + " WHERE c.id = :id");
            query.setParameter("id", id);
            Contract contract = (Contract) query.getSingleResult();
            // to ensure the options associated with the contract is 
            // retrieved
            contract.getOptionElementSet().size();
            contract.getFunctionalitySet().size();
            contract.getScenarioSet().size();
            contract.getReferentialSet().size();
            return contract;
        } catch (NoResultException nre) {
            return null;
        }
    }

}
