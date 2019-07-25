/*
 *  Asqatasun - Automated webpage assessment
 *  Copyright (C) 2008-2019  Asqatasun.org
 * 
 *  This file is part of Asqatasun.
 * 
 *  Asqatasun is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 * 
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 *  Contact us by mail: asqatasun AT asqatasun DOT org
 */

package org.asqatasun.webapp.security.userdetails;

import java.util.List;
import org.asqatasun.webapp.entity.service.user.UserDataService;
import org.asqatasun.webapp.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 *
 * @author jkowalczyk
 */
public class TgolUserDetailsService extends JdbcDaoImpl {

    @Autowired
    private UserDataService userDataService;
    @Autowired
    private DataSource dataSource;

    private final static String USERS_BY_USERNAME_QUERY =
        "SELECT Email1, Password, Activated as enabled FROM TGSI_USER WHERE Email1=?";
    private static String AUTHORITIES_BY_USERNAME_QUERY =
        "SELECT TGSI_USER.Email1, TGSI_ROLE.Role_Name as authorities FROM TGSI_USER, TGSI_ROLE "
            + "WHERE TGSI_USER.Email1 = ? AND TGSI_USER.ROLE_Id_Role=TGSI_ROLE.Id_Role";

    /**
     *
     */
    public TgolUserDetailsService() {
        super();
    }

    @PostConstruct
    public void init() {
        this.setDataSource(dataSource);
        this.setUsersByUsernameQuery(USERS_BY_USERNAME_QUERY);
        this.setAuthoritiesByUsernameQuery(AUTHORITIES_BY_USERNAME_QUERY);
    }

    @Override
    protected UserDetails createUserDetails(String username, UserDetails userFromUserQuery,
                                            List<GrantedAuthority> combinedAuthorities) {

        User user = userDataService.getUserFromEmail(username);

        return new TgolUserDetails(
            username,
            userFromUserQuery.getPassword(),
            userFromUserQuery.isEnabled(),
            true,
            true,
            true,
            combinedAuthorities,
            user);
    }
    
}
