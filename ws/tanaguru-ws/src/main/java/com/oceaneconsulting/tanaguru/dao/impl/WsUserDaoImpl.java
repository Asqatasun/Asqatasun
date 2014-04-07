package com.oceaneconsulting.tanaguru.dao.impl;

import org.opens.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;
import com.oceaneconsulting.tanaguru.dao.WsUserDao;
import com.oceaneconsulting.tanaguru.entity.WsUser;
import com.oceaneconsulting.tanaguru.entity.impl.WsUserImpl;

public class WsUserDaoImpl extends AbstractJPADAO<WsUser, Long> implements WsUserDao {
	@Override
    protected Class<? extends WsUser> getEntityClass() {
        return WsUserImpl.class;
    }
}