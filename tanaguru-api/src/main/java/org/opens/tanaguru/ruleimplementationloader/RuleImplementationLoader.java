package org.opens.tanaguru.ruleimplementationloader;

import org.opens.tanaguru.ruleimplementation.RuleImplementation;

/**
 * 
 * @author ADEX
 */
public interface RuleImplementationLoader {

    String getArchiveName();

    String getArchiveRoot();

    String getClassName();

    RuleImplementation getResult();

    void run();

    void setArchiveName(String archiveName);

    void setArchiveRoot(String ruleImplementationArchveRoot);

    void setClassName(String className);
}
