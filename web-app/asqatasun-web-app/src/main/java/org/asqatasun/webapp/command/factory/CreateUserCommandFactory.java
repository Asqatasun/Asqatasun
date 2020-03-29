/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2019  Asqatasun.org
 *
 * This file is part of Asqatasun.
 *
 * Asqatasun is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.asqatasun.webapp.command.factory;

import java.io.Serializable;
import org.asqatasun.webapp.command.CreateUserCommand;
import org.asqatasun.webapp.entity.service.user.RoleDataService;
import org.asqatasun.webapp.entity.user.Role;
import org.asqatasun.webapp.entity.user.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 *
 * @author jkowalczyk
 */
@Component
public class CreateUserCommandFactory  implements Serializable {

    private static final Long USER_ROLE_ID = 2L;
    private static final Long ADMIN_ROLE_ID = 3L;
    private Role userRole;
    public Role getUserRole() {return userRole;}
    private Role adminRole;
    public Role getAdminRole() {return adminRole;}

    private final RoleDataService roleDataService;

    /**
     * Private constructor
     */
    private CreateUserCommandFactory(RoleDataService roleDataService) {
        this.roleDataService = roleDataService;
    }

    @PostConstruct
    private void init () {
        userRole = roleDataService.read(USER_ROLE_ID);
        adminRole = roleDataService.read(ADMIN_ROLE_ID);
    }

    public CreateUserCommand getInitialisedCreateUserCommand(User user) {
        if (userRole == null || adminRole == null) {
            init();
        }
        CreateUserCommand createUserCommand = new CreateUserCommand();
        createUserCommand.setEmail(user.getEmail1());
        createUserCommand.setSiteUrl(user.getWebUrl1());
        createUserCommand.setFirstName(user.getFirstName());
        createUserCommand.setLastName(user.getName());
        createUserCommand.setPhoneNumber(user.getPhoneNumber());
        createUserCommand.setActivated(user.isAccountActivated());
        System.out.println(user.getRole().getId());
        System.out.println(adminRole.getId());
        if (user.getRole().getId().equals(adminRole.getId())) {
            createUserCommand.setAdmin(true);
        } else {
            createUserCommand.setAdmin(false);
        }
        createUserCommand.setActivated(user.isAccountActivated());
        return createUserCommand;
    }
    
    public CreateUserCommand getNewCreateUserCommand() {
        CreateUserCommand createUserCommand = new CreateUserCommand();
        createUserCommand.setAdmin(false);
        return createUserCommand;
    }

}
