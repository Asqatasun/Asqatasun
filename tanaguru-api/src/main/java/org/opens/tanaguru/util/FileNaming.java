package org.opens.tanaguru.util;

/**
 *
 * @author jkowalczyk
 */
public class FileNaming {

    private static final String FILE_PROTOCOL_PREFIX="file://";
    private static final String HTTP_PROTOCOL_PREFIX = "http://";
    private static final String HTTPS_PROTOCOL_PREFIX = "https://";
    private static final String FILE_PREFIX = "/";

    /**
     * Default private constructor
     */
    private FileNaming() {}

    /**
     * This method add the protocol to the url if the protocol is missing
     * @param url
     * @return
     */
    public static String addProtocolToUrl(String url) {
        if (!url.startsWith(HTTP_PROTOCOL_PREFIX)
                && !url.startsWith(HTTPS_PROTOCOL_PREFIX)
                && !url.startsWith(FILE_PROTOCOL_PREFIX)) {

            if (url.startsWith(FILE_PREFIX)) {
                url = FILE_PROTOCOL_PREFIX + url;
            } else {
                url = HTTP_PROTOCOL_PREFIX + url;
            }
        }
        return url;
    }

    public static String removeFilePrefix(String url){
        if (url.startsWith(FILE_PROTOCOL_PREFIX)) {
            return url.substring(FILE_PROTOCOL_PREFIX.length());
        }
        return url;
    }

}