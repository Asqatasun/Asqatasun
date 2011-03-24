/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.processor;

import org.opens.tanaguru.service.ProcessRemarkService;

/**
 *
 * @author enzolalay
 */
public class DOMHandlerFactory {// TODO Write javadoc

    public static DOMHandler create(ProcessRemarkService processRemarkService) {
        DOMHandler domHandler = new DOMHandlerImpl();
        domHandler.setProcessRemarkService(processRemarkService);
        return domHandler;
    }
}
