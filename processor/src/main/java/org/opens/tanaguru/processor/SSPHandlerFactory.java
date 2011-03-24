/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.processor;

import org.opens.tanaguru.contentadapter.util.URLIdentifier;
import org.opens.tanaguru.service.NomenclatureLoaderService;
import org.opens.tanaguru.service.ProcessRemarkService;

/**
 *
 * @author enzolalay
 */
public class SSPHandlerFactory {

    public static SSPHandler create(ProcessRemarkService processRemarkService, NomenclatureLoaderService nomenclatureLoaderService, URLIdentifier urlIdentifier) {
        return new SSPHandlerImpl(nomenclatureLoaderService, urlIdentifier, CSSHandlerFactory.create(processRemarkService), DOMHandlerFactory.create(processRemarkService), processRemarkService);
    }
}
