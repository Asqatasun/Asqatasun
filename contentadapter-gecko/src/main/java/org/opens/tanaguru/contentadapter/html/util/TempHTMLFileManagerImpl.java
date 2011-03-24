package org.opens.tanaguru.contentadapter.html.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author ADEX
 */
public class TempHTMLFileManagerImpl implements TempHTMLFileManager {

	final static String PROTOCOL = "file://";
	private String sourceCode;
	private File tempFile;
	private String tempFolder = System.getProperty("java.io.tmpdir");

	public TempHTMLFileManagerImpl() {
		super();
	}

	public void create() {
		{
			OutputStream out = null;
			try {
				UUID fileName = UUID.randomUUID();

				tempFile = new File(tempFolder + File.separator + fileName.toString());
				tempFile.createNewFile();

				byte[] data = sourceCode.getBytes("UTF-8");

				out = new BufferedOutputStream(new FileOutputStream(tempFile));
				out.write(data, 0, data.length);
			} catch (UnsupportedEncodingException ex) {
				Logger.getLogger(TempHTMLFileManagerImpl.class.getName()).log(
						Level.SEVERE, null, ex);
				throw new RuntimeException(ex);
			} catch (FileNotFoundException ex) {
				Logger.getLogger(TempHTMLFileManagerImpl.class.getName()).log(
						Level.SEVERE, null, ex);
				throw new RuntimeException(ex);
			} catch (IOException ex) {
				Logger.getLogger(TempHTMLFileManagerImpl.class.getName()).log(
						Level.SEVERE, null, ex);
				throw new RuntimeException(ex);
			} finally {
				try {
					out.close();
				} catch (IOException ex) {
					Logger.getLogger(TempHTMLFileManagerImpl.class.getName())
							.log(Level.SEVERE, null, ex);
					throw new RuntimeException(ex);
				}
			}
		}
	}

	public void delete() {
		tempFile.delete();
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public String getTempFolder() {
		return tempFolder;
	}

	public String getURL() {
		if (System.getProperty("os.name").toLowerCase().contains("win")) {
			return PROTOCOL + "/" + tempFile.getAbsolutePath();
		}
		return PROTOCOL + tempFile.getAbsolutePath();
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public void setTempFolder(String tempFolder) {
		this.tempFolder = tempFolder;
	}
}
