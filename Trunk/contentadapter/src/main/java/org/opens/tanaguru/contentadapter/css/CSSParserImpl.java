package org.opens.tanaguru.contentadapter.css;

import org.opens.tanaguru.contentadapter.util.AbstractContentParser;
import java.io.IOException;
import java.io.StringReader;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.css.sac.CSSException;
import org.w3c.css.sac.InputSource;
import org.w3c.flute.parser.Parser;

/**
 * 
 * @author ADEX
 */
public class CSSParserImpl extends AbstractContentParser implements CSSParser {

	private CSSOMStyleSheet result;

	public CSSParserImpl() {
		super();
	}

	public CSSOMStyleSheet getResult() {
		return result;
	}

	public void run() {
		try {
			String rsrc = resource.getResource();
			InputSource is = new InputSource(new StringReader(rsrc));
			Parser parser = new Parser();
			CSSOMDocumentHandlerImpl handler = new CSSOMDocumentHandlerImpl(
					(CSSResource) resource);
			parser.setDocumentHandler(handler);
			parser.parseStyleSheet(is);
			result = handler.getResult();
		} catch (CSSException ex) {
			Logger.getLogger(CSSParserImpl.class.getName()).log(Level.SEVERE,
					null, ex);
			throw new RuntimeException(ex);
		} catch (IOException ex) {
			Logger.getLogger(CSSParserImpl.class.getName()).log(Level.SEVERE,
					null, ex);
			throw new RuntimeException(ex);
		}
	}
}
