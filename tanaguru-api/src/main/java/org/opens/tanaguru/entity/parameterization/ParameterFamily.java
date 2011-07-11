package org.opens.tanaguru.entity.parameterization;


import com.adex.sdk.entity.Entity;

/**
 * 
 * @author jkowalczyk
 */
public interface ParameterFamily extends Entity {

    /**
     * 
     * @return
     */
    String getParameterFamilyCode();

    /**
     *
     * @param parameterElement
     */
    void setParameterFamilyCode(String parameterFamilyCode);

    /**
     *
     * @return
     */
    String getDescription();

    /**
     *
     * @param description
     */
    void setDescription(String description);

    /**
     *
     * @return
     */
    String getLongLabel();

    /**
     *
     * @param shortLabel
     */
    void setLongLabel(String shortLabel);

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