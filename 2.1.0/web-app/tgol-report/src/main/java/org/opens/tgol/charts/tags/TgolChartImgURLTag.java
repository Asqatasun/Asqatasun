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
package org.opens.tgol.charts.tags;

import de.laures.cewolf.taglib.tags.ChartImgTag;
import java.io.IOException;
import javax.servlet.jsp.JspException;

/**
 *
 * @author ${user}
 */
public class TgolChartImgURLTag extends ChartImgTag {

    private static final long serialVersionUID = -9039646236358020600L;
    /**
     *
     */
    public static final String VAR_NAME = "var";
    private String var = null;

    /**
     * 
     * @return
     * @throws JspException
     */
    @Override
    public int doEndTag() throws JspException {
        try {
            if (var == null) {
                pageContext.getOut().write(getImgURL());
            } else {
                pageContext.setAttribute(var, getImgURL());
            }
        } catch (IOException ioex) {
            System.err.println("ChartImgTag.doEndTag: " + ioex.getMessage());
            throw new JspException(ioex.getMessage());
        }

//        try {
//            Storage storage = Configuration.getInstance(pageContext.getServletContext()).getStorage();
//            storage.removeChartImage(sessionKey, (HttpServletRequest) pageContext.getRequest());
//        } catch (CewolfException cwex) {
//            throw new JspException(cwex.getMessage());
//        }

        return doAfterEndTag(EVAL_PAGE);
    }

    /**
     * @see de.laures.cewolf.taglib.tags.CewolfBodyTag#reset()
     */
    @Override
    protected void reset() {
        super.reset();
        var = null;
    }

    /**
     * Returns the var.
     * @return String
     */
    public String getVar() {
        return var;
    }

    /**
     * Sets the var.
     * @param var The var to set
     */
    public void setVar(String var) {
        this.var = var;
    }
}
