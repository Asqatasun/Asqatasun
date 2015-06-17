package com.oceaneconsulting.tanaguru.dao.impl;

import org.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;
import org.springframework.stereotype.Repository;

import com.oceaneconsulting.tanaguru.dao.WsRoleDao;
import com.oceaneconsulting.tanaguru.entity.WsRole;
import com.oceaneconsulting.tanaguru.entity.impl.WsRoleImpl;

/**
 * Classe de la DAO des rles
 * @author msobahi
 *
 */
@Repository("roleDao")
public class WsRoleDaoImpl extends AbstractJPADAO<WsRole, Long> implements WsRoleDao {
	@Override
    protected Class<? extends WsRole> getEntityClass() {
        return WsRoleImpl.class;
    }
}