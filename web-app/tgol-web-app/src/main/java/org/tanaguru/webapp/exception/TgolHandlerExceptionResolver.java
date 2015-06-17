/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015 Tanaguru.org
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
package org.tanaguru.webapp.exception;

import org.tanaguru.webapp.util.TgolKeyStore;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

/**
 *
 * @author jkowalczyk
 */
public class TgolHandlerExceptionResolver extends DefaultHandlerExceptionResolver {

   /**
     * This exception resolver displays the audit set page for file upload
     * when the MaxUploadSizeExceededException is thrown
     *
     * @param hsr
     * @param hsr1
     * @param o
     * @param excptn
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public ModelAndView doResolveException(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, Exception excptn) {
        if (excptn instanceof MaxUploadSizeExceededException) {
            Map<String, String> model = new HashMap<String, String>();
            model.put(TgolKeyStore.CONTRACT_ID_KEY, hsr.getParameter(TgolKeyStore.CONTRACT_ID_KEY));
            return new ModelAndView(TgolKeyStore.MAX_FILE_SIZE_ERROR_VIEW_NAME, model);
        }
        return super.doResolveException(hsr, hsr1, o, excptn);
    }


}
