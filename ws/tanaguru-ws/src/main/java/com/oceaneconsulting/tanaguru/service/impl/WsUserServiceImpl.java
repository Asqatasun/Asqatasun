package com.oceaneconsulting.tanaguru.service.impl;

import org.tanaguru.sdk.entity.service.AbstractGenericDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oceaneconsulting.tanaguru.dao.WsUserDao;
import com.oceaneconsulting.tanaguru.entity.WsRole;
import com.oceaneconsulting.tanaguru.entity.WsUser;
import com.oceaneconsulting.tanaguru.service.WsUserService;

/**
 * Classe d'implementation du service des utilisateurs
 *
 * @author msobahi
 *
 */
@Service("wsUserService")
public class WsUserServiceImpl extends AbstractGenericDataService<WsUser, Long> implements WsUserService {

    @Autowired
    private WsUserDao userDao;

    @Override
    public WsRole getUserRole(WsUser user) {
        return userDao.getUserRole(user);
    }

    @Override
    public WsUser getUser(String login) {
        return userDao.getUser(login);
    }
}
