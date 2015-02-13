/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.rules.doc.utils.updateAw22toRgaa30;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alingua
 */
public class CopyFromAw22ToRgaa30 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CopyFiles cf = new CopyFiles();
        try {
            cf.copyFilesAvailable();
        } catch (IOException ex) {
            Logger.getLogger(CopyFromAw22ToRgaa30.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
