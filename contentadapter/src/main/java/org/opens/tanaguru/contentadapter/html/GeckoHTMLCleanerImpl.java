package org.opens.tanaguru.contentadapter.html;

import org.opens.tanaguru.contentadapter.html.util.TempHTMLFileManager;
import org.opens.tanaguru.contentadapter.HTMLCleaner;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.ProgressAdapter;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 * @author ADEX
 */
public class GeckoHTMLCleanerImpl extends AbstractHTMLCleaner implements
        HTMLCleaner {

    static final String CORRECTOR_NAME = "Gecko";
    static final String NS_XMLSERIALIZER_CONTRACTID = "@mozilla.org/xmlextras/xmlserializer;1";
    static final String PROPERTY_XULRUNNERPATH = "org.eclipse.swt.browser.XULRunnerPath";
    private int blankCount = 0;
    private String currentURL;
    private String greHome;
    private boolean initialized = false;
    private boolean pageLoadComplete = false;
    private Browser swtBrowser;
    private TempHTMLFileManager tempHTMLFileManager;

    public GeckoHTMLCleanerImpl() {
        super();
    }

    private void initialize() {
        if (initialized) {
            return;
        }

        System.setProperty(PROPERTY_XULRUNNERPATH, greHome);
        Display display = new Display();
        Shell shell = new Shell(display);
        swtBrowser = new Browser(shell, SWT.MOZILLA);

        // TODO Add xulrunner-1.9.0.15-sdk lib
//        nsIWebBrowserSetup nsIWebBrowserSet = (nsIWebBrowserSetup) ((nsIWebBrowser) swtBrowser.getWebBrowser()).queryInterface(nsIWebBrowserSetup.NS_IWEBBROWSERSETUP_IID);
//        nsIWebBrowserSet.setProperty(nsIWebBrowserSetup.SETUP_ALLOW_SUBFRAMES,
//                0);

        swtBrowser.addProgressListener(new ProgressAdapter() {

            @Override
            public void completed(ProgressEvent event) {
                swtBrowserCompleted(event);
            }
        });
        initialized = true;
    }

    public void run() {
        initialize();

        tempHTMLFileManager.setSourceCode(dirtyHTML);
        tempHTMLFileManager.create();
        currentURL = tempHTMLFileManager.getURL();

        blankCount = 0;
        pageLoadComplete = false;
        swtBrowser.setUrl(currentURL);
        while (!pageLoadComplete) {
            boolean readAndDispatch = swtBrowser.getDisplay().readAndDispatch();
            if (!readAndDispatch) {
                swtBrowser.getDisplay().sleep();
            }
        }
        swtBrowser.getDisplay().dispose();// Frees the display after the loading is completed.
        initialized = false;// Sets the component in un-initialized state.
    }

    public void setGreHome(String greHome) {
        this.greHome = greHome;
    }

    public void setTempHTMLFileManager(TempHTMLFileManager tempHTMLFileManager) {
        this.tempHTMLFileManager = tempHTMLFileManager;
    }

    // private nsIComponentManager getComponentManager() {
    // if (componentManager == null) {
    // Mozilla mozilla = Mozilla.getInstance();
    // componentManager = mozilla.getComponentManager();
    // }
    // return componentManager;
    // }
    private void swtBrowserCompleted(ProgressEvent event) {
        String url = swtBrowser.getUrl();

        // Mozilla may load blank pages before loading the real page.
        // It is due to the use of XULRunner and its asynchronous type of execution.
        // We allow the component to load 2 blank pages before considering it as an error and skip loading.
        if (url.equalsIgnoreCase("about:blank")) {
            blankCount++;
            if (blankCount >= 2) {
                // TODO Add log
                pageLoadComplete = true;
                swtBrowser.getDisplay().wake();
            }
            return;
        }

        // Used to get rid of swt and use only mozilla interfaces.
        // Worked on windows, not on linux.
        // nsIWebBrowser browser = (nsIWebBrowser) swtBrowser.getWebBrowser();
        // nsIDOMWindow window = browser.getContentDOMWindow();
        // nsIDOMDocument document = window.getDocument();
        // nsIDOMSerializer serializer = (nsIDOMSerializer) getComponentManager().createInstanceByContractID(NS_XMLSERIALIZER_CONTRACTID, null, nsIDOMSerializer.NS_IDOMSERIALIZER_IID);
        // String serializedDOM = serializer.serializeToString(document);

        try {
            String serializedDOM = swtBrowser.getText();
            byte[] bytes = serializedDOM.getBytes("UTF-8");
            result = new String(bytes);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(GeckoHTMLCleanerImpl.class.getName()).log(
                    Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }

        tempHTMLFileManager.delete();
        currentURL = null;// XXX Not used anymore
        pageLoadComplete = true;
        swtBrowser.getDisplay().wake();// XXX See what it is used for?
    }

    public String getCorrectorName() {
        return CORRECTOR_NAME;
    }
}
