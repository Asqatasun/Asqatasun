package com.oceaneconsulting.tanaguru.service.impl;

import org.tanaguru.sdk.entity.service.AbstractGenericDataService;
import org.springframework.stereotype.Service;

import com.oceaneconsulting.tanaguru.entity.WsRole;
import com.oceaneconsulting.tanaguru.service.WsRoleService;

/**
 * Classe d'implementation du service des roles
 * @author msobahi
 *
 */
@Service("wsRoleService")
public class WsRoleServiceImpl extends AbstractGenericDataService<WsRole, Long> implements WsRoleService {

}
