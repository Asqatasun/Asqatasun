package com.oceaneconsulting.tanaguru.dao.impl;

import javax.persistence.Query;

import org.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;
import org.springframework.stereotype.Repository;

import com.oceaneconsulting.tanaguru.dao.WsInvocationDao;
import com.oceaneconsulting.tanaguru.entity.WsInvocation;
import com.oceaneconsulting.tanaguru.entity.WsRole;
import com.oceaneconsulting.tanaguru.entity.WsUser;
import com.oceaneconsulting.tanaguru.entity.impl.WsInvocationImpl;

/**
 * Classe de DAO des invocations
 * 
 * @author msobahi
 * 
 */
@Repository("invocationDao")
public class WsInvocationDaoImpl extends AbstractJPADAO<WsInvocation, Long> implements WsInvocationDao {

	/**
	 * Option de la mise en cache de la requete
	 */
	private static final String CACHEABLE_OPTION = "org.hibernate.cacheable";

	@Override
	protected Class<? extends WsInvocation> getEntityClass() {
		return WsInvocationImpl.class;
	}

	@Override
	public int getCountInvocByUser(WsUser user) {
		Query query = entityManager.createQuery("SELECT COUNT(inv.id) FROM " + getEntityClass().getName() + " inv "
				+ " WHERE inv.user = :user");
		query.setParameter("user", user);
		query.setHint(CACHEABLE_OPTION, "true");
		return Long.valueOf((Long) query.getSingleResult()).intValue();
	}

	@Override
	public int getCountInvocByUser(WsUser user, String hostName, String hostIp, WsRole role) {
		StringBuffer querySB= new StringBuffer();
		
		querySB.append("SELECT COUNT(inv.id) ");
		querySB.append( "FROM " + getEntityClass().getName() + " inv WHERE 1=1 " );
		if(user != null){
			querySB.append("AND inv.user = :user " );
		}
		if(role != null) {
			querySB.append("AND inv.user.role = :role " );
		}
		if(hostName != null || hostIp!= null){
			querySB.append("AND ( " );
				if(hostName != null){
					querySB.append("inv.hostName = :hostName " );
				}
				if(hostName != null && hostIp != null){
					querySB.append(" OR " );
				}
				if(hostIp != null){
					querySB.append("inv.hostIp = :hostIp " );
				}
			querySB.append(")" );
		}
		Query query = entityManager.createQuery(querySB.toString());
		if (user != null) {
			query.setParameter("user", user);
		}
		if (role != null) {
			query.setParameter("role", role);
		}
		if (hostName != null) {
			query.setParameter("hostName", hostName);
		}
		if (hostIp != null) {
			query.setParameter("hostIp", hostIp);
		}

		query.setHint(CACHEABLE_OPTION, "true");
		return Long.valueOf((Long) query.getSingleResult()).intValue();
	}

	@Override
	public int getCountInvocByUserId(Long userId) {
		Query query = entityManager.createQuery("SELECT COUNT(inv.id) FROM " + getEntityClass().getName() + " inv "
				+ " WHERE inv.user.id = :userId");
		query.setParameter("userId", userId);
		query.setHint(CACHEABLE_OPTION, "true");
		return Long.valueOf((Long) query.getSingleResult()).intValue();
	}

}