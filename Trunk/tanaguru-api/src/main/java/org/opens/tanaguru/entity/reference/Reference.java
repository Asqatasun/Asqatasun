package org.opens.tanaguru.entity.reference;

import java.util.List;

import com.adex.sdk.entity.Entity;
import com.adex.sdk.entity.Reorderable;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface Reference extends Entity, Reorderable {

    /**
     *
     * @param criterion
     *            the criterion to add
     */
    void addCriterion(Criterion criterion);

    /**
     *
     * @return the code
     */
    String getCode();

    /**
     *
     * @return the criterion list
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
     * @param criterionList
     *            the criterion list to set
     */
    void setCriterionList(List<? extends Criterion> criterionList);

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
