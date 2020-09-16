/*
 *  Asqatasun - Automated webpage assessment
 *  Copyright (C) 2008-2020  Asqatasun.org
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
package org.asqatasun.model

enum class Referential(val code: String) {
    RGAA_4_0("Rgaa40"),
    RGAA_3_0("Rgaa30"),
    ACCESSIWEB_2_2("Aw22"),
    SEO("Seo");

    companion object {
        private val map = values().associateBy(Referential::code)
        fun fromCode(code: String): Referential? = map[code]
    }
}

enum class Level(val code: String) {
    AAA("Or"),
    AA("Ar"),
    A("Bz");

    companion object {
        private val map = values().associateBy(Level::code)
        fun fromCode(code: String): Level? = map[code]
    }
}
