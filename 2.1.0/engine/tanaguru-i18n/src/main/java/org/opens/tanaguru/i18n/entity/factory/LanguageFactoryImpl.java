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
package org.opens.tanaguru.i18n.entity.factory;

import org.opens.tanaguru.i18n.entity.LanguageImpl;
import org.opens.tanaguru.sdk.entity.i18n.Language;
import org.opens.tanaguru.sdk.entity.i18n.factory.LanguageFactory;

/**
 * 
 * @author jkowalczyk
 */
public class LanguageFactoryImpl implements LanguageFactory {

    public LanguageFactoryImpl() {
        super();
    }

    public Language create() {
        return new LanguageImpl();
    }

}