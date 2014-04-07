package com.oceaneconsulting.tanaguru.dao.impl;

import org.opens.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;
import com.oceaneconsulting.tanaguru.dao.WsRoleDao;
import com.oceaneconsulting.tanaguru.entity.WsRole;
import com.oceaneconsulting.tanaguru.entity.impl.WsRoleImpl;

public class WsRoleDaoImpl extends AbstractJPADAO<WsRole, Long> implements WsRoleDao {
	@Override
    protected Class<? extends WsRole> getEntityClass() {
        return WsRoleImpl.class;
    }
}