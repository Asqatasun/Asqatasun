package org.opens.tanaguru.ruleimplementationloader;

import org.opens.tanaguru.ruleimplementation.RuleImplementation;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author ADEX
 */
public class RuleImplementationLoaderImpl implements RuleImplementationLoader {

    private String archiveName;
    private String archiveRoot;
    private String className;
    private RuleImplementation result;

    public RuleImplementationLoaderImpl() {
        super();
    }

    public String getArchiveName() {
        return archiveName;
    }

    public String getArchiveRoot() {
        return archiveRoot;
    }

    public String getClassName() {
        return className;
    }

    public RuleImplementation getResult() {
        return result;
    }

    public void run() {
        try {
            URL rulesPackagesRootURL = new File(archiveRoot + File.separator + archiveName + ".jar").toURI().toURL();
            URLClassLoader classLoader = new URLClassLoader(
                    new URL[]{rulesPackagesRootURL}, this.getClass().getClassLoader());
            result = (RuleImplementation) Class.forName(className, true,
                    classLoader).newInstance();
        } catch (InstantiationException ex) {
            Logger.getLogger(RuleImplementationLoaderImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(RuleImplementationLoaderImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RuleImplementationLoaderImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(RuleImplementationLoaderImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

    public void setArchiveName(String archiveName) {
        this.archiveName = archiveName;
    }

    public void setArchiveRoot(String archiveRoot) {
        this.archiveRoot = archiveRoot;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
