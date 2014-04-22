package com.oceaneconsulting.tanaguru.service.impl;

import com.oceaneconsulting.tanaguru.AbstractDbUnitTest;
import com.oceaneconsulting.tanaguru.entity.WsUser;
import com.oceaneconsulting.tanaguru.entity.impl.WsRoleImpl;
import com.oceaneconsulting.tanaguru.entity.impl.WsUserImpl;
import com.oceaneconsulting.tanaguru.service.WsInvocationService;

/**
 * Classe du test du service d'invocation
 * @author msobahi
 *
 */
public class WsInvocationServiceImplTest extends AbstractDbUnitTest{

	/**
     * Nom du fichier xml contenant le jeu de donnees a importer
     */
    private static final String INPUT_DATA_SET_FILENAME = "src/test/resources/dataSets/invocationServiceTest.xml";
    
    /**
     * 
     */
    private WsInvocationService wsInvocationService;
	
	public WsInvocationServiceImplTest(String testName) {
		super(testName);
		setInputDataFileName(INPUT_DATA_SET_FILENAME);
		wsInvocationService = (WsInvocationService)springBeanFactory.getBean("wsInvocationService");
	}
	
	/**
	 * Test de la methode qui recupere le nombre d'invocations faites par un utilisateur
	 */
	public void testGetCountInvocByUser(){
		WsRoleImpl role = new WsRoleImpl();
		role.setId(new Long(2));
		role.setRole("USER");
		role.setLabel("Known user");
		
		WsUser user = new WsUserImpl();
		user.setId(new Long(3));
		user.setName("INCONNU");
		user.setFirstName("INCONNU");
		user.setActive(true);
		user.setRole(role);
		int nbrInvocationByUser = wsInvocationService.getCountInvocByUser(user);
		assertEquals(5, nbrInvocationByUser);
	}
	
	/**
	 * Test de la methode qui recupere le nombre d'invocations faites par un utilisateur pour un host
	 */
	public void testGetCountInvocByUserHostNameHostIpRole(){
		WsRoleImpl role = new WsRoleImpl();
		role.setId(new Long(2));
		role.setRole("USER");
		role.setLabel("Known user");
		
		WsUser user = new WsUserImpl();
		user.setId(new Long(3));
		user.setName("INCONNU");
		user.setFirstName("INCONNU");
		user.setActive(true);
		user.setRole(role);
		int nbrInvocationByUser = wsInvocationService.getCountInvocByUser(user,"www.oceaneconsulting.com","192.168.15.2",role);
		assertEquals(5, nbrInvocationByUser);
	}
	
	/**
	 * Test de la methode qui recupere le nombre d'invocations faites par un utilisateur via son identifiant
	 */
	public void testGetCountInvocByUserId(){
		Long userId = new Long(3);
		int nbrInvocationByUser = wsInvocationService.getCountInvocByUserId(userId);
		assertEquals(5, nbrInvocationByUser);
	}
	
	/**
	 * Test de la methode de verification du depassement du nombre limite d'invocation
	 */
	public void testCheckLimitationOverflow(){
		WsRoleImpl role = new WsRoleImpl();
		role.setId(new Long(2));
		role.setRole("USER");
		role.setLabel("Known user");
		
		WsUser user = new WsUserImpl();
		user.setId(new Long(3));
		user.setName("INCONNU");
		user.setFirstName("INCONNU");
		user.setActive(true);
		user.setRole(role);
		boolean isLimitOverflowed = wsInvocationService.checkLimitationOverflow(user,"www.oceaneconsulting.com","192.168.15.2",role);
		assertTrue(isLimitOverflowed);
	}
}
