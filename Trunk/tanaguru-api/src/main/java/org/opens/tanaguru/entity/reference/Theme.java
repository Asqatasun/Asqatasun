package org.opens.tanaguru.entity.reference;

import com.adex.sdk.entity.Entity;
import com.adex.sdk.entity.Reorderable;
import java.util.List;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface Theme extends Entity, Reorderable {

    /**
     *
     * @return the code
     */
    String getCode();

    /**
     *
     * @return the criteria
     */
    List<? extends Criterion> getCriterionList();

    /**
     *
     * @return the description
     */
    String getDescription();

    /**
     *
     * @return the label
     */
    String getLabel();

    /**
     *
     * @param code
     *            the code to set
     */
    void setCode(String code);

    /**
     *
     * @param criteria
     *            the criteria to set
     */
    void setCriterionList(List<? extends Criterion> criteria);

    /**
     *
     * @param description
     *            the description to set
     */
    void setDescription(String description);

    /**
     *
     * @param label
     *            the label to set
     */
    void setLabel(String label);
}
