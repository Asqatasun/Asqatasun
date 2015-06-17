/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2015  Tanaguru.org
 * 
 *  This file is part of Tanaguru.
 * 
 *  Tanaguru is free software: you can redistribute it and/or modify
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
 *  Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.webapp.security.userdetails;

import java.util.Collection;
import org.apache.commons.lang3.StringUtils;
import org.tanaguru.webapp.entity.user.User;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author jkowalczyk
 */
public class TgolUserDetails extends org.springframework.security.core.userdetails.User {

    private static final long serialVersionUID = 1L;
    private User user;

    public User getUser() {
        return user;
    }
    
    private String displayedUserName;
    public String getDisplayedUserName() {
        return displayedUserName;
    }

    /**
     *
     * @param username
     * @param password
     * @param enabled
     * @param accountNonExpired
     * @param credentialsNonExpired
     * @param accountNonLocked
     * @param authorities
     * @param user
     * @throws IllegalArgumentException
     */
    public TgolUserDetails(
            String username, 
            String password,
            boolean enabled, 
            boolean accountNonExpired,
            boolean credentialsNonExpired, 
            boolean accountNonLocked,
            Collection<GrantedAuthority> authorities, 
            User user)
            throws IllegalArgumentException {
        super(username, password, enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, authorities);
        this.user = user;
        this.displayedUserName = computeDisplayedUserName(user, username);
    }

    public void updateUser(User user) {
        this.user=user;
        this.displayedUserName = computeDisplayedUserName(user, getUsername());
    }
    
    /**
     * 
     * @param user
     * @param userName
     * @return 
     */
    private String computeDisplayedUserName(
            User user,
            String userName) {
        if (user != null && !StringUtils.equals(user.getEmail1(), "guest")) {
            if (!StringUtils.isEmpty(user.getFirstName().trim()) && 
                    !StringUtils.isEmpty(user.getName().trim())) {
                return user.getFirstName() + " "+ user.getName();
            } else if (!StringUtils.isEmpty(user.getName().trim())) {
                return user.getName();
            }
        }
        return userName;
    }
    
}