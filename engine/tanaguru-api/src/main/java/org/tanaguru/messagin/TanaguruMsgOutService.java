package org.tanaguru.messagin;

/**
 * 
 * @author OCEANECONSULITNG
 *
 */
public interface TanaguruMsgOutService {

	/**
	 * send message in queu  
	 * 
	 * @param urlPage
	 * @return
	 */
	boolean send(String urlPage);

	/**
	 * send and store message in db and in fileSystem
	 * 
	 * @param urlPage
	 * @param fileName
	 * @param id
	 */
	void sendAndSave(String urlPage, String fileName, String id);
	
}
