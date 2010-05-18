package org.opens.tanaguru.contentadapter.util;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.logging.Level;
import java.util.logging.Logger;

public class URLIdentifierImpl implements URLIdentifier {

    protected URL absolutePath;
    protected URL url;

    public URLIdentifierImpl() {
        super();
    }

    public URL getAbsolutePath() {
        return absolutePath;
    }

    public URL getUrl() {
        return url;
    }

    public URL resolve(String path) {
        try {
            absolutePath = new URL(url, path);
            return absolutePath;
        } catch (MalformedURLException ex) {
            Logger.getLogger(URLIdentifierImpl.class.getName()).log(
                    Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

    public void setUrl(URL url) {
        this.url = url;
    }
}
