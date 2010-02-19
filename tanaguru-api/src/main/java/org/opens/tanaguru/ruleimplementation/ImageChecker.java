/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.tanaguru.ruleimplementation;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.opens.tanaguru.contentadapter.util.URLIdentifier;
import org.opens.tanaguru.processor.SSPHandler;

/**
 * This class is used to determine if an image can be decorative
 * @author jkowalczyk
 */
public class ImageChecker {

    private URLIdentifier uRLIdentifier = null;
    private static ImageChecker imageChecker = null;

    private ImageChecker() {

    }

    public static ImageChecker getInstance() {
        if (imageChecker == null) {
            imageChecker = new ImageChecker();
        }
        return imageChecker;
    }

    public boolean isDecorativeImage(SSPHandler sspHandler, String imgUrl) {
        boolean isMonoColorImg = true;
        boolean isMonoDimension = false;
        boolean isDecorativeImg = false;
        try {
            URL src = new URL(sspHandler.getSSP().getURI());
            uRLIdentifier.setUrl(src);

            BufferedImage image = null;
            try {
                image = ImageIO.read(uRLIdentifier.resolve(imgUrl));
            } catch (IOException ex) {
                Logger.getLogger(ImageChecker.class.getName()).
                        log(Level.WARNING, null, ex);
            }

            int rgbColor = -1;
            if (image != null) {
                if (image.getWidth() < 1 || image.getHeight() < 1) {
                    isMonoDimension = true;
                } else {
                    for (int i = 0; i < image.getWidth(); i++) {
                        for (int j = 0; j < image.getHeight(); j++) {
                            if (i == 0 && j == 0) {
                                rgbColor = image.getRGB(i, j);
                            }
                            if (rgbColor != image.getRGB(i, j)) {
                                i = image.getWidth();
                                j = image.getHeight();
                                isMonoColorImg = false;
                            }
                        }
                    }
                }
            }
            if (isMonoColorImg || isMonoDimension) {
                isDecorativeImg = true;
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(ImageChecker.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        return isDecorativeImg;
    }

    public void setUrlIdentifier(URLIdentifier urlIdentifier) {
        this.uRLIdentifier = urlIdentifier;
    }

}
