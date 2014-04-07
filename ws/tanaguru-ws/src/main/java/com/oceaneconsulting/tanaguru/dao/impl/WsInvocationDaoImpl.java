package com.oceaneconsulting.tanaguru.dao.impl;

import org.opens.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;
import com.oceaneconsulting.tanaguru.dao.WsInvocationDao;
import com.oceaneconsulting.tanaguru.entity.WsInvocation;
import com.oceaneconsulting.tanaguru.entity.impl.WsInvocationImpl;

public class WsInvocationDaoImpl extends AbstractJPADAO<WsInvocation, Long> implements WsInvocationDao {
	
	@Override
    protected Class<? extends WsInvocation> getEntityClass() {
        return WsInvocationImpl.class;
    }
	
}