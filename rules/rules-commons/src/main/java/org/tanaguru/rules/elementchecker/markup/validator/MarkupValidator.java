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

package org.tanaguru.rules.elementchecker.markup.validator;

//import com.rexsl.w3c.TanaguruValidatorBuilder;
//import com.rexsl.w3c.ValidationResponse;

/**
 *
 */
public final class MarkupValidator {

//    private static Map<Long, ValidationResponse> responseMapByWebResourceId = 
//            new HashMap<Long, ValidationResponse>();
//    
    /**
     * The holder that handles the unique instance of MarkupValidator
     */
    private static class MarkupValidatorHolder {
        private static final MarkupValidator INSTANCE = new MarkupValidator();
    }
    
    /**
     * Private constructor
     */
    private MarkupValidator() {}
    
    /**
     * Singleton pattern based on the "Initialization-on-demand 
     * holder idiom". See @http://en.wikipedia.org/wiki/Initialization_on_demand_holder_idiom
     * @return the unique instance of MarkupValidator
     */
    public static MarkupValidator getInstance() {
        return MarkupValidatorHolder.INSTANCE;
    }
    
//    /**
//     *
//     * @return the unique instance of MarkupValidator
//     */
//    public boolean isHtmlValid(Long webResourceId, String html) {
//        return getValidationResponse(webResourceId, html).valid();
//    }
//    
//    /**
//     *
//     * @return the unique instance of MarkupValidator
//     */
//    public boolean isHtmlContainsDeprecatedElementValid(Long webResourceId, String html) {
//        return getValidationResponse(webResourceId, html).valid();
//    }

//    /**
//     * 
//     * @param webResourceId
//     * @param html
//     * @return 
//     */
//    private ValidationResponse getValidationResponse(Long webResourceId, String html) {
//        if (responseMapByWebResourceId.containsKey(webResourceId)) {
//            ValidationResponse vr = responseMapByWebResourceId.get(webResourceId);
//            responseMapByWebResourceId.remove(webResourceId);
//            return vr;
//        } else {
//            ValidationResponse vr = new TanaguruValidatorBuilder().html().validate(html);
//            responseMapByWebResourceId.put(webResourceId, vr);
//            return vr;
//        }
//    }

}