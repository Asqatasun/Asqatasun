package com.oceaneconsulting.tanaguru.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oceaneconsulting.tanaguru.dao.WsInvocationDao;
import com.oceaneconsulting.tanaguru.entity.WsInvocation;
import com.oceaneconsulting.tanaguru.entity.WsRole;
import com.oceaneconsulting.tanaguru.entity.WsUser;
import com.oceaneconsulting.tanaguru.service.WsInvocationService;

/**
 * Classe d'implementation du service d'invocation
 * @author msobahi
 *
 */       
@Service("wsInvocationService")
public class WsInvocationServiceImpl /*extends AbstractGenericDataService<WsInvocation, Long> */implements WsInvocationService {

	/**
	 * Max invocation
	 */
	private static final int NBR_INVOCATION_MAX = 5; 
	
	@Autowired
	private WsInvocationDao invocationDao;
	
	@Override
	public void create(WsInvocation wsInvocation) {
		invocationDao.create(wsInvocation);
	}
	
	@Override
	public int getCountInvocByUser(WsUser user) {
		return invocationDao.getCountInvocByUser(user);
	}

	@Override
	public int getCountInvocByUser(WsUser user, String hostName, String hostIp, WsRole role) {
		return invocationDao.getCountInvocByUser(user, hostName, hostIp, role);
	}

	@Override
	public int getCountInvocByUserId(Long userId) {
		return invocationDao.getCountInvocByUserId(userId);
	}

	@Override
	public boolean checkLimitationOverflow(WsUser user, String hostName, String hostIp, WsRole role){
		int nbrInvocation = invocationDao.getCountInvocByUser(user, hostName, hostIp, role);		
		return nbrInvocation <= NBR_INVOCATION_MAX;
	}

}
