package com.oceaneconsulting.tanaguru.dao.impl;

import javax.persistence.Query;

import org.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;
import org.springframework.stereotype.Repository;

import com.oceaneconsulting.tanaguru.dao.WsUserDao;
import com.oceaneconsulting.tanaguru.entity.WsRole;
import com.oceaneconsulting.tanaguru.entity.WsUser;
import com.oceaneconsulting.tanaguru.entity.impl.WsUserImpl;

/**
 * Classe de la DAO des utilisateurs
 * @author msobahi
 *
 */
@Repository("userDao")
public class WsUserDaoImpl extends AbstractJPADAO<WsUser, Long> implements WsUserDao {
	
	/**
	 * Option de la mise en cache de la requete
	 */
	private static final String CACHEABLE_OPTION = "org.hibernate.cacheable";
	
	@Override
    protected Class<? extends WsUser> getEntityClass() {
        return WsUserImpl.class;
    }
	

	@Override
	public WsRole getUserRole(WsUser user) {
		Query query = entityManager.createQuery("SELECT usr.role FROM " + getEntityClass().getName() + " usr "
				+ " WHERE usr = :user");
		query.setParameter("user", user);
		query.setHint(CACHEABLE_OPTION, "true");
		return (WsRole) query.getSingleResult();
	}

	
	public WsUser getUser(String login){
		Query query = entityManager.createQuery("SELECT usr FROM " + getEntityClass().getName() + " usr "
				+ " WHERE usr.email = :login");
		query.setParameter("login", login);
		query.setHint(CACHEABLE_OPTION, "true");
		return (WsUser) query.getSingleResult();
	}
}