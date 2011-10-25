package org.opens.tanaguru.contentadapter.util;

import java.net.MalformedURLException;
import java.net.URL;
import org.apache.log4j.Logger;



public class URLIdentifierImpl implements URLIdentifier {

    protected URL absolutePath;
    protected URL url;

    public URLIdentifierImpl() {
        super();
    }

    @Override
    public URL getAbsolutePath() {
        return absolutePath;
    }

    @Override
    public URL getUrl() {
        return url;
    }

    @Override
    public URL resolve(String path) {
        try {
            absolutePath = new URL(url, path);
        } catch (MalformedURLException ex) {
            Logger.getLogger(this.getClass()).error("Url : " +url + " Path : " + path + " " +ex);
        }
        return absolutePath;
    }

    @Override
    public void setUrl(URL url) {
        this.url = url;
    }
}
