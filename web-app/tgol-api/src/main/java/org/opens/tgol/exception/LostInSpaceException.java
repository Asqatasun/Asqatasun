/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2011  Open-S Company
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
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tgol.exception;

import org.opens.tgol.entity.user.User;

/**
 *
 * @author jkowalczyk
 */
public class LostInSpaceException extends RuntimeException{

    private static final long serialVersionUID = -7648205989129967840L;

    private User user;
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LostInSpaceException() {
        super();
    }

    public LostInSpaceException(Exception e, User user) {
        super(e);
        this.user = user;
    }

    public LostInSpaceException(User user) {
        super();
        this.user = user;
    }

}