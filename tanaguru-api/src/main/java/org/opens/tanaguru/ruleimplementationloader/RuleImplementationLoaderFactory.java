/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.ruleimplementationloader;

/**
 *
 * @author enzolalay
 */
public interface RuleImplementationLoaderFactory {// TODO Write javadoc
    RuleImplementationLoader create(String archiveRoot, String ruleArchiveName, String ruleClassName);
}
