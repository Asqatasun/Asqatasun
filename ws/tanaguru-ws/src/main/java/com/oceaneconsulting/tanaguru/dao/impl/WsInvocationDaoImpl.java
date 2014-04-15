package com.oceaneconsulting.tanaguru.dao.impl;

import javax.persistence.Query;

import org.opens.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;
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
		Query query = entityManager.createQuery("SELECT COUNT(inv.id) "
			    + "FROM " + getEntityClass().getName() + " inv " //
				+ "WHERE inv.user = :user " //
				+ " AND inv.hostName = :hostName " // 
				+ "AND inv.hostIp = :hostIp " //
				+ "AND inv.user.role = :role");
		query.setParameter("user", user);
		query.setParameter("hostName", hostName);
		query.setParameter("hostIp", hostIp);
		query.setParameter("role", role);
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