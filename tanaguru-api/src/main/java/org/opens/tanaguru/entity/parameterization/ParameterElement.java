package org.opens.tanaguru.entity.parameterization;


import com.adex.sdk.entity.Entity;

/**
 * 
 * @author jkowalczyk
 */
public interface ParameterElement extends Entity {

    /**
     *
     * @return
     */
    String getParameterElementCode();

    /**
     *
     * @param longValue
     */
    void setParameterElementCode(String parameterElementCode);

    /**
     * 
     * @return
     */
    ParameterFamily getParameterFamily();

    /**
     *
     * @param parameterElement
     */
    void setParameterFamily(ParameterFamily parameterFamily);

    /**
     *
     * @return
     */
    String getLongLabel();

    /**
     * 
     * @param longLabel
     */
    void setLongLabel(String longLabel);

    /**
     *
     * @return
     */
    String getShortLabel();

    /**
     * 
     * @param shortLabel
     */
    void setShortLabel(String shortLabel);

}