package com.oceaneconsulting.tanaguru.dao.impl;

import com.oceaneconsulting.tanaguru.AbstractDbUnitTest;
import com.oceaneconsulting.tanaguru.dao.WsUserDao;
import com.oceaneconsulting.tanaguru.entity.WsRole;
import com.oceaneconsulting.tanaguru.entity.WsUser;
import com.oceaneconsulting.tanaguru.entity.impl.WsUserImpl;

public class UserDaoImplTest extends AbstractDbUnitTest {

	/**
     * Nom du fichier xml contenant le jeu de donnees a importer
     */
    private static final String INPUT_DATA_SET_FILENAME = "src/test/resources/dataSets/userDaoTest.xml";
    
    /**
     * LA DAO
     */
    private WsUserDao userDao;
	
	
    /**
     * Constructeur
     * @param testName Nom du test
     */
	public UserDaoImplTest(String testName) {
		super(testName);
		setInputDataFileName(INPUT_DATA_SET_FILENAME);
		userDao = (WsUserDao)springBeanFactory.getBean("userDao");
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
		WsRole role = userDao.getUserRole(user);
		assertNotNull(role);
	}

}
