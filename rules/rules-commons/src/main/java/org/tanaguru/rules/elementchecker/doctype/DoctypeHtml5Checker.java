/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tanaguru.rules.elementchecker.doctype;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.select.Elements;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.entity.reference.NomenclatureElement;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.TestSolutionHandler;
import org.tanaguru.rules.elementchecker.NomenclatureBasedElementChecker;
import static org.tanaguru.rules.keystore.RemarkMessageStore.INVALID_DOCTYPE_MSG;

/**
 *
 * @author tanaguru
 */
public class DoctypeHtml5Checker extends NomenclatureBasedElementChecker{
    
     private static final String CASE_SENSITIVE_DOCTYPE_NOM = 
            "RecommendedDoctypeDeclarations";
    private static final String CASE_INSENSITIVE_DOCTYPE_NOM = 
            "RecommendedCaseInsensitiveDoctypeDeclarations";
    
    /**
     * Default constructor
     */
    public DoctypeHtml5Checker() {
        super();
    }

     @Override
    protected void doCheck(
            SSPHandler sspHandler,
            Elements elements,
            TestSolutionHandler testSolutionHandler) {
        String doctype = sspHandler.getSSP().getDoctype();
         // if the page doesn't have any doctype declaration, the test is 
        // not applicable
        if (StringUtils.isBlank(doctype)) {
            testSolutionHandler.addTestSolution(TestSolution.NOT_APPLICABLE);
            return;
        }

        if (isDoctypeValideHtml5(doctype)) {

            testSolutionHandler.addTestSolution(TestSolution.NOT_TESTED);

        }
    }
      
    /*
    *Chek if doctype is valid html5 delaration from local list of known Html5 doctypes.
    */
    private boolean isDoctypeValideHtml5(String doctype) {
        //list of known doctypes,  the Html5 doctype is not case sensitive only one value can be tested 
        //but i a take the for :)
        String validKnownHtml5Doctype[] = {"<!doctype html>", "<!DOCTYPE html>"};

        for (String vKnownDocType : validKnownHtml5Doctype) {
            if (vKnownDocType.equalsIgnoreCase(doctype)) {
                return true;
            }
        }
        return false;
    }
    
    
    
}
