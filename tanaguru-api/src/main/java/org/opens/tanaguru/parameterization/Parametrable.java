/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.tanaguru.parameterization;

import java.util.Set;
import org.opens.tanaguru.entity.parameterization.Parameter;

/**
 *
 * @author jkowalczyk
 */
public interface Parametrable {

    /**
     *
     * @return
     *      The collection of parameters
     */
    Set<Parameter> getParameterSet();

    /**
     * Set the collection of parameters
     * @param paramSet
     */
    void setParameterSet(Set<Parameter> paramSet);

}