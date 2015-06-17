/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
 *
 * This file is part of Tanaguru.
 *
 * Tanaguru is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */

package org.tanaguru.rules.accessiweb21.image;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import org.apache.log4j.Logger;
import org.tanaguru.contentadapter.util.URLIdentifier;
import org.tanaguru.processor.SSPHandler;

/**
 * This class is used to determine if an image is decorative.
 * 
 * @author jkowalczyk
 */
public class ImageChecker {

    private URLIdentifier uRLIdentifier = null;

    /**
     * The holder that handles the unique instance of ImageChecker
     */
    private static class ImageCheckerHolder {
        private static final ImageChecker INSTANCE = new ImageChecker();
    }
    
    /**
     * Private constructor
     */
    private ImageChecker() {}
    
    /**
     * Singleton pattern based on the "Initialization-on-demand 
     * holder idiom". See @http://en.wikipedia.org/wiki/Initialization_on_demand_holder_idiom
     * @return the unique instance of ImageChecker
     */
    public static ImageChecker getInstance() {
        return ImageCheckerHolder.INSTANCE;
    }

    @Deprecated
    public boolean isDecorativeImage(SSPHandler sspHandler, String imgUrl)  {
        Logger.getLogger(ImageChecker.class.getName()).
                    debug("Testing if image " + uRLIdentifier.resolve(imgUrl) +
                    " is decorative");
        long beginDate = System.currentTimeMillis();
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
                        warn(ex.getMessage() + " " + uRLIdentifier.resolve(imgUrl));
            } catch (IllegalArgumentException ex){
                Logger.getLogger(ImageChecker.class.getName()).
                        warn(ex.getMessage() + " " + uRLIdentifier.resolve(imgUrl));
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
                if (isMonoColorImg || isMonoDimension) {
                    isDecorativeImg = true;
                }
            }
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(ImageChecker.class.getName()).
                    warn(ex.getMessage() + " " + uRLIdentifier.resolve(imgUrl));
        }
        Long processDuration = Long.valueOf(System.currentTimeMillis() - beginDate);
        Logger.getLogger(ImageChecker.class.getName()).
                    debug("image tested in " + processDuration.toString() +
                    " ms");
        return isDecorativeImg;
    }

    /**
     * Determines whether an image is decorative or not.
     * @param image
     * @return
     */
    public boolean isDecorativeImage(BufferedImage image)  {
        Logger.getLogger(ImageChecker.class.getName()).
                    info("Testing if image " + 
                    " is decorative");
        long beginDate = System.currentTimeMillis();
        boolean isMonoColorImg = true;
        boolean isMonoDimension = false;
        boolean isDecorativeImg = false;

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
            if (isMonoColorImg || isMonoDimension) {
                isDecorativeImg = true;
            }
        }
        Long processDuration = Long.valueOf(System.currentTimeMillis() - beginDate);
        Logger.getLogger(ImageChecker.class.getName()).
                    info("image tested in " + processDuration.toString() +
                    " ms");
        return isDecorativeImg;
    }

    public void setUrlIdentifier(URLIdentifier urlIdentifier) {
        this.uRLIdentifier = urlIdentifier;
    }

}