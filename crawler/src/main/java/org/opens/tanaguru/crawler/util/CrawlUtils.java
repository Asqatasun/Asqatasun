/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.tanaguru.crawler.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.NoSuchElementException;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import org.apache.log4j.Logger;
import org.archive.io.RecordingInputStream;
import org.mozilla.universalchardet.UniversalDetector;
import org.opens.tanaguru.crawler.CrawlerImpl;

/**
 *
 * @author jkowalczyk
 */
public final class CrawlUtils {

    private static final Logger LOGGER = Logger.getLogger(CrawlerImpl.class);
    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final int BYTE_BUFFER_SIZE = 1000;
    private static final String DEFAULT_IMG_EXTENSION = "jpg";

    private CrawlUtils() {};

    /**
     * This method converts the sourceCode into UTF-8 charset to ensure the
     * charset compatibility with the source and the charset used for the
     * persistence.
     * @param recis
     * @param charset
     * @return
     * @throws IOException
     */
    public static String convertSourceCodeIntoUtf8(
            RecordingInputStream recis,
            String charset) throws IOException {
        if (!charset.equalsIgnoreCase(DEFAULT_CHARSET)) {
            Charset utf8charset = Charset.forName(DEFAULT_CHARSET);
            Charset incomingCharset = Charset.forName(charset);
            ByteBuffer inputBuffer = ByteBuffer.wrap(
                    recis.getReplayCharSequence(charset).toString().getBytes(charset));
            CharBuffer data = incomingCharset.decode(inputBuffer);
            ByteBuffer outputBuffer = utf8charset.encode(data);
            byte[] outputData = outputBuffer.array();
            return new String(outputData);
        } else {
            return recis.getReplayCharSequence(charset).toString();
        }
    }

    /**
     * This methods tests if a charset is valid regarding the charset nio API.
     * @param charset
     * @return
     */
    public static boolean isValidCharset(String charset) {
        try {
            Charset.forName(charset);
        } catch (UnsupportedCharsetException e) {
            return false;
        }
        return true;
    }

    /**
     * This method extracts the charset from the html source code.
     * If the charset is not specified, it is set to UTF-8 by default
     * @param is
     * @return
     */
    public static String extractCharset(InputStream is) throws java.io.IOException {
        byte[] buf = new byte[4096];
        UniversalDetector detector = new UniversalDetector(null);
        int nread;
        while ((nread = is.read(buf)) > 0 && !detector.isDone()) {
            detector.handleData(buf, 0, nread);
        }
        detector.dataEnd();

        String encoding = detector.getDetectedCharset();
        if (encoding != null) {
            LOGGER.debug("Detected encoding = " + encoding);
        } else {
            LOGGER.debug("No encoding detected.");
        }

        detector.reset();
        if (encoding != null && CrawlUtils.isValidCharset(encoding)) {
            return encoding;
        } else {
            return DEFAULT_CHARSET;
        }
    }

    /**
     * Get an image extension from the image url
     * @param imageUrl
     * @return
     */
    public static String getImageExtension(String imageUrl) {
        String ext = imageUrl.substring(imageUrl.lastIndexOf('.') + 1);
        try {
            java.util.Iterator<ImageWriter> it =
                    ImageIO.getImageWritersBySuffix(ext);
            if (it.next() != null) {
                return ext;
            }
        } catch (NoSuchElementException ex) {
            return DEFAULT_IMG_EXTENSION;
        }
        return DEFAULT_IMG_EXTENSION;
    }

    /**
     * Get the raw content of an image
     * @param is
     * @param imgExtension
     * @return
     */
    public static byte[] getImageContent(InputStream is, String imgExtension) {
        // O P E N
        ByteArrayOutputStream baos = new ByteArrayOutputStream(BYTE_BUFFER_SIZE);
        BufferedImage image = null;
        byte[] resultImageAsRawBytes = null;
        try {
            image = ImageIO.read(is);
            // W R I T E
            ImageIO.write(image, imgExtension, baos);
            // C L O S E
            baos.flush();
            resultImageAsRawBytes = baos.toByteArray();
            baos.close();
        } catch (IOException ex) {
            LOGGER.warn(ex.getMessage());
        }
        return resultImageAsRawBytes;
    }

}