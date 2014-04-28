package com.oceaneconsulting.tanaguru.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.oceaneconsulting.tanaguru.AbstractDbUnitTest;
import com.oceaneconsulting.tanaguru.entity.WsRole;
import com.oceaneconsulting.tanaguru.entity.WsUser;
import com.oceaneconsulting.tanaguru.entity.impl.WsUserImpl;
import com.oceaneconsulting.tanaguru.service.WsUserService;

/**
 * Classe de test du service utilisateur
 * @author msobahi
 *
 */
public class WsUserServiceImplTest extends AbstractDbUnitTest {

	/**
     * Nom du fichier xml contenant le jeu de donnees a importer
     */
    private static final String INPUT_DATA_SET_FILENAME = "src/test/resources/dataSets/userServiceTest.xml";
    
    /**
     * Service des utilisateurs 
     */
    @Autowired
    private WsUserService wsUserService;
	
    /**
     * Constructeur
     * @param testName Nom du service
     */
	public WsUserServiceImplTest(String testName) {
		super(testName);
		setInputDataFileName(INPUT_DATA_SET_FILENAME);
		wsUserService = (WsUserService)springBeanFactory.getBean("wsUserService");
	}
	
	/**
	 * Test de la methode qui recupere le nombre d'invocations faites par un utilisateur
	 */
	public void testGetUserRole(){
		
		WsUser user = new WsUserImpl();
		user.setId(new Long(3));
		user.setName("INCONNU");
		user.setFirstName("INCONNU");
		user.setActive(true);
		WsRole role = wsUserService.getUserRole(user);
		assertNotNull(role);
	}
}
