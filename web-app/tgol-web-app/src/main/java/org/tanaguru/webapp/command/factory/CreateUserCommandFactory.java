/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
 *
 * This file is part of Tanaguru.
 *
 * Tanaguru is free software: you can redistribute it and/or modify
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
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.webapp.command.factory;

import java.io.Serializable;
import org.tanaguru.webapp.command.CreateUserCommand;
import org.tanaguru.webapp.entity.service.user.RoleDataService;
import org.tanaguru.webapp.entity.user.Role;
import org.tanaguru.webapp.entity.user.User;

/**
 *
 * @author jkowalczyk
 */
public class CreateUserCommandFactory  implements Serializable {

    private Long userRoleId = Long.valueOf("2");
    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }
    
    private Long adminRoleId = Long.valueOf("3");
    public void setAdminRoleId(Long adminRoleId) {
        this.adminRoleId = adminRoleId;
    }
    
    private Role userRole;
    public Role getUserRole() {
        return userRole;
    }
    
    private Role adminRole;
    public Role getAdminRole() {
        return adminRole;
    }
    
    public final void setRoleDataService (RoleDataService roleDataService){
        userRole = roleDataService.read(userRoleId);
        adminRole = roleDataService.read(adminRoleId);
    }
    
    /**
     * The holder that handles the unique instance of CreateUserCommandFactory
     */
    private static class CreateUserCommandFactoryHolder {
        private static final CreateUserCommandFactory INSTANCE = 
                new CreateUserCommandFactory();
    }
    
    /**
     * Private constructor
     */
    private CreateUserCommandFactory() {}
    
    /**
     * Singleton pattern based on the "Initialization-on-demand 
     * holder idiom". See @http://en.wikipedia.org/wiki/Initialization_on_demand_holder_idiom
     * @return the unique instance of CreateUserCommandFactory
     */
    public static CreateUserCommandFactory getInstance() {
        return CreateUserCommandFactoryHolder.INSTANCE;
    }
    
    public CreateUserCommand getInitialisedCreateUserCommand(User user) {
        CreateUserCommand createUserCommand = new CreateUserCommand();
        createUserCommand.setEmail(user.getEmail1());
        createUserCommand.setSiteUrl(user.getWebUrl1());
        createUserCommand.setFirstName(user.getFirstName());
        createUserCommand.setLastName(user.getName());
        createUserCommand.setPhoneNumber(user.getPhoneNumber());
        createUserCommand.setActivated(user.isAccountActivated());

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