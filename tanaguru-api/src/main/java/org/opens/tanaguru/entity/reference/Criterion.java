package org.opens.tanaguru.entity.reference;

import com.adex.sdk.entity.Entity;
import com.adex.sdk.entity.Reorderable;
import java.util.List;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface Criterion extends Entity, Reorderable {

    /**
     *
     * @param test
     *            the test to set
     */
    void addTest(Test test);

    /**
     *
     * @return the code
     */
    String getCode();

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
     * @return the reference
     */
    Reference getReference();

    /**
     *
     * @return the url
     */
    String getUrl();

    /**
     *
     * @return the tests
     */
    List<? extends Test> getTestList();

    /**
     *
     * @return the theme
     */
    Theme getTheme();

    /**
     *
     * @param code
     *            the code to set
     */
    void setCode(String code);

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

    /**
     *
     * @param reference
     *            the reference to set
     */
    void setReference(Reference reference);

    /**
     *
     * @param tests
     *            the tests to set
     */
    void setTestList(List<? extends Test> tests);

    /**
     *
     * @param theme
     *            the theme to set
     */
    void setTheme(Theme theme);

    /**
     *
     * @param url
     *            the url to set
     */
    void setUrl(String url);
}
