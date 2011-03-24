/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.ruleimplementationloader;

/**
 *
 * @author enzolalay
 */
public class RuleImplementationLoaderFactoryImpl implements RuleImplementationLoaderFactory {// TODO Write javadoc

    public RuleImplementationLoader create(String archiveRoot, String ruleArchiveName, String ruleClassName) {
        return new RuleImplementationLoaderImpl(archiveRoot, ruleArchiveName, ruleClassName);
    }
}
