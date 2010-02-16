/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.rule.test;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.opens.tanaguru.ruleimplementation.RuleImplementation;
import org.opens.tanaguru.ruleimplementationloader.RuleImplementationLoader;

/**
 *
 * @author lralambomanana
 */
public class RuleImplementationLoaderImpl implements RuleImplementationLoader {

    private String className;
    private RuleImplementation result;

    public RuleImplementationLoaderImpl() {
        super();
    }

    @Override
    public String getArchiveName() {
        return null;
    }

    @Override
    public String getArchiveRoot() {
        return null;
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public RuleImplementation getResult() {
        return result;
    }

    @Override
    public void run() {
        result = loadClass(className);
    }

    @Override
    public void setArchiveName(String archiveName) {
    }

    @Override
    public void setArchiveRoot(String ruleImplementationArchveRoot) {
    }

    @Override
    public void setClassName(String className) {
        this.className = className;
    }

    public RuleImplementation loadClass(String className) {
        try {
            return (RuleImplementation) Class.forName(className).newInstance();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RuleImplementationLoaderImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(RuleImplementationLoaderImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(RuleImplementationLoaderImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }
}
