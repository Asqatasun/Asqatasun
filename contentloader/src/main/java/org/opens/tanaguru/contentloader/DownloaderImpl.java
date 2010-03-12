package org.opens.tanaguru.contentloader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DownloaderImpl implements Downloader {

    protected String result;
    protected String url;

    public DownloaderImpl() {
        super();
    }

    private String download(String url) {
        BufferedReader in = null;
        try {
            String urlContent = "";
            String thisLine;
            URL u = new URL(url);
            in = new BufferedReader(new InputStreamReader(u.openStream()));
            while ((thisLine = in.readLine()) != null) {
                //Correction of #34 bug
                urlContent = urlContent + thisLine + "\r";
            }
            return urlContent;
        } catch (IOException ex) {
            Logger.getLogger(DownloaderImpl.class.getName()).log(Level.SEVERE,
                    null, ex);
            throw new RuntimeException(ex);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(DownloaderImpl.class.getName()).log(
                        Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public String getResult() {
        return result;
    }

    @Override
    public String getURL() {
        return url;
    }

    @Override
    public void run() {
        result = download(url);
    }

    @Override
    public void setURL(String url) {
        this.url = url;
    }
}
