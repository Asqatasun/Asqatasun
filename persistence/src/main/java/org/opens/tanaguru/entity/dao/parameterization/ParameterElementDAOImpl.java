/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.tanaguru.entity.dao.parameterization;

import com.adex.sdk.entity.dao.jpa.AbstractJPADAO;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import org.opens.tanaguru.entity.parameterization.ParameterElement;
import org.opens.tanaguru.entity.parameterization.ParameterElementImpl;

/**
 *
 * @author jkowalczyk
 */
public class ParameterElementDAOImpl extends AbstractJPADAO<ParameterElement, Long> implements
        ParameterElementDAO {

    @Override
    protected Class<? extends ParameterElement> getEntityClass() {
        return ParameterElementImpl.class;
    }

    @Override
    public ParameterElement findParameterElementFromCode(String parameterElementCode) {
        Query query = entityManager.createQuery("SELECT pe FROM "
                + getEntityClass().getName() + " pe"
                + " WHERE pe.parameterElementCode = :parameterElementCode");
        query.setParameter("parameterElementCode", parameterElementCode);
        try {
            return (ParameterElement) query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        } catch (NonUniqueResultException nure) {
            return null;
        }
    }

}