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
public class CSSHandlerFactory {// TODO Write javadoc

    public static CSSHandler create(ProcessRemarkService processRemarkService) {
        CSSHandler cssHandler = new CSSHandlerImpl();
        cssHandler.setProcessRemarkService(processRemarkService);
        return cssHandler;
    }
}
