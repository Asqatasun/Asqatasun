/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.tanaguru.entity.dao.parameterization;

import com.adex.sdk.entity.dao.jpa.AbstractJPADAO;
import org.opens.tanaguru.entity.parameterization.ParameterFamily;
import org.opens.tanaguru.entity.parameterization.ParameterFamilyImpl;

/**
 *
 * @author jkowalczyk
 */
public class ParameterFamilyDAOImpl extends AbstractJPADAO<ParameterFamily, Long> implements
        ParameterFamilyDAO {

    @Override
    protected Class<? extends ParameterFamily> getEntityClass() {
        return ParameterFamilyImpl.class;
    }

}
