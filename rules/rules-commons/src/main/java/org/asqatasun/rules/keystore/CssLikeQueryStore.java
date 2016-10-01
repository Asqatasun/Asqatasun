/*
 *  Asqatasun - Automated webpage assessment
 *  Copyright (C) 2008-2015  Asqatasun.org
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
package org.asqatasun.rules.keystore;

/**
 * Utility class that stores css-like queries as static String
 */
public final class CssLikeQueryStore {
    
    // Image theme css-like queries
    public static final String IMG_WITHOUT_ALT_CSS_LIKE_QUERY="img:not([alt])"; 
    public static final String IMG_WITH_ALT_CSS_LIKE_QUERY="img[alt]"; 
    public static final String IMG_WITH_ALT_NOT_IN_LINK_CSS_LIKE_QUERY=
                    "img[alt]:not(a img)"; 
    public static final String IMG_WITH_ALT_NOT_IN_LINK_WITHOUT_LONGDESC_CSS_LIKE_QUERY=
                    "img[alt]:not(a img):not([longdesc])"; 
    public static final String IMG_NOT_IN_LINK_CSS_LIKE_QUERY="img:not(a img)"; 
    public static final String IMG_WITH_ISMAP_ATTR_CSS_LIKE_QUERY=
                    "img[ismap] , "
                    +"input[type=image][ismap]"; 
    public static final String IMG_WITH_ALT_WITHOUT_LONGDESC_CSS_LIKE_QUERY=
                    "img[alt]:not([longdesc])"; 
    public static final String APPLET_WITH_ALT_CSS_LIKE_QUERY=
                    "applet[alt]"; 
    public static final String APPLET_WITH_ALT_NOT_IN_LINK_CSS_LIKE_QUERY=
                    "applet[alt]:not(a applet)"; 
    public static final String APPLET_NOT_IN_LINK_CSS_LIKE_QUERY=
                    "applet:not(a applet)"; 
    public static final String OBJECT_TYPE_IMG_CSS_LIKE_QUERY=
                    "object[type^=image]"; 
    public static final String OBJECT_TYPE_IMG_NOT_IN_LINK_CSS_LIKE_QUERY=
                    "object[type^=image]:not(a object)"; 
    public static final String EMBED_TYPE_IMG_CSS_LIKE_QUERY=
                    "embed[type^=image]"; 
    public static final String EMBED_TYPE_IMG_NOT_IN_LINK_CSS_LIKE_QUERY=
                    "embed[type^=image]:not(a embed)"; 
    public static final String AREA_WITH_ALT_CSS_LIKE_QUERY=
                    "area[alt]"; 
    public static final String AREA_WITH_ALT_NOT_IN_LINK_CSS_LIKE_QUERY=
                    "area[alt]:not(a area)"; 
    public static final String AREA_NOT_IN_LINK_CSS_LIKE_QUERY=
                    "area:not(a area)"; 
    public static final String AREA_WITH_ALT_WITHOUT_HREF_ATTR_CSS_LIKE_QUERY=
                    "area[alt]:not([href])"; 
    public static final String FORM_BUTTON_CSS_LIKE_QUERY="input[type=image]"; 
    public static final String FORM_BUTTON_WITH_ALT_CSS_LIKE_QUERY=
                    "input[type=image][alt]";
    public static final String MAP_WITH_AREA_CHILD_AND_NAME_ATTR_CSS_LIKE_QUERY = 
                    "map:has(area)[name]:not([name~=^\\s*$])";
    public static final String NOT_EMPTY_ALT_ATTR_NOT_IN_LINK_CSS_LIKE_QUERY = 
                    "[alt]:not([alt~=^\\s*$]):not(a [alt])";

    //aria
    public static final String ARIA_ATTRIBUTES_CSS_LIKE_QUERY="[^aria]"; 
    public static final String ARIA_DESCRIBEDBY_CSS_LIKE_QUERY="[aria-describedby]"; 
    public static final String ARIA_LABEL_CSS_LIKE_QUERY="[aria-label]"; 
    public static final String ARIA_LABELLEDBY_CSS_LIKE_QUERY="[aria-labelledby]"; 
    
    //svg children
    public static final String NOT_EMPTY_ARIA_TITLE_CSS_LIKE_QUERY = 
            "title:not(:matchesOwn(^\\s*$))";
    public static final String NOT_EMPTY_ARIA_DESC_CSS_LIKE_QUERY =
            "desc:not(:matchesOwn(^\\s*$))";
    public static final String SVG_NOT_IN_LINK_CSS_LIKE_QUERY=
                    "svg:not(a svg)"; 
    public static final String SVG_NOT_IN_LINK_WITH_DESC_CHILD_CSS_LIKE_QUERY=
                    "svg:not(a svg):has(desc:not(:matchesOwn(^\\s*$))"; 
    public static final String SVG_NOT_IN_LINK_WITH_ARIA_LABEL_CSS_LIKE_QUERY=
                    "svg[aria-label]:not([aria-label~=^\\s*$]:not(a svg)"; 
    public static final String SVG_NOT_IN_LINK_WITH_DESC_CHILD_AND_ROLE_IMG_CSS_LIKE_QUERY=
                    "svg[role=img]:not(a svg):has(desc:not(:matchesOwn(^\\s*$))"; 
    public static final String SVG_NOT_IN_LINK_WITH_ARIA_LABEL_AND_ROLE_IMG_CSS_LIKE_QUERY=
                    "svg[role=img][aria-label]:not([aria-label~=^\\s*$]:not(a svg)"; 
    public static final String CANVAS_NOT_IN_LINK_CSS_LIKE_QUERY=
                    "canvas:not(a canvas)"; 
    public static final String CANVAS_NOT_IN_LINK_WITH_NOT_EMPTY_CONTENT_CSS_LIKE_QUERY=
                    "canvas:not(a canvas):not(:matchesOwn(^\\s*$))";
 
    // Table theme css-like queries
    public static final String TABLE_WITH_SUMMARY_CSS_LIKE_QUERY="table[summary]"; 
    public static final String TABLE_WITH_CAPTION_CSS_LIKE_QUERY="table:has(caption)"; 
    public static final String TABLE_WITH_TH_CSS_LIKE_QUERY="table:has(th)";
    public static final String TABLE_WITH_TH_OR_TD_CSS_LIKE_QUERY=
                    "table:has(th), table:has(td)";
    public static final String DATA_TABLE_MARKUP_CSS_LIKE_QUERY =
                      "caption , "
                    + "th , "
                    + "thead , "
                    + "tfoot , "
                    + "colgroup , "
                    + "td[scope] , "
                    + "td[headers] , "
                    + "td[axis]";
    
    // Frame theme css-like queries
    public static final String FRAME_WITH_TITLE_CSS_LIKE_QUERY="frame[title]"; 
    public static final String IFRAME_WITH_TITLE_CSS_LIKE_QUERY="iframe[title]"; 
    public static final String IFRAME_WITH_NOT_EMPTY_TITLE_CSS_LIKE_QUERY=
                    "iframe[title]:not([title~=^\\s*$])"; 
    public static final String FRAME_WITH_NOT_EMPTY_TITLE_CSS_LIKE_QUERY=
                    "frame[title]:not([title~=^\\s*$])"; 

    // Form theme css-like queries
    public static final String FORM_WITHOUT_FIELDSET_CSS_LIKE_QUERY = 
                    "form:not(:has(fieldset))"; 
    public static final String FORM_ELEMENT_CSS_LIKE_QUERY=
                    "textarea , "
                   +"select , "
                   +"datalist , "
                   +"keygen , "
                   +"input[type=password] , "
                   +"input[type=checkbox] , "
                   +"input[type=file] , "
                   +"input[type=text] , "
                   +"input[type=search] , "
                   +"input[type=tel] , "
                   +"input[type=email] , "
                   +"input[type=number] , "
                   +"input[type=url] , "
                   +"input[type=date] , "
                   +"input[type=range] , "
                   +"input[type=color] , "
                   +"input[type=time] , "
                   +"input[type=radio]"; 
    public static final String INPUT_ELEMENT_INSIDE_FORM_CSS_LIKE_QUERY=
                    "form textarea:not([title]):not([aria-label]):not([aria-labelledby]) , "
                   +"form select:not([title]):not([aria-label]):not([aria-labelledby]) , "
                   +"form datalist:not([title]):not([aria-label]):not([aria-labelledby]) , "
                   +"form keygen:not([title]):not([aria-label]):not([aria-labelledby]) , "
                   +"form input[type=password]:not([title]):not([aria-label]):not([aria-labelledby]) , "
                   +"form input[type=checkbox]:not([title]):not([aria-label]):not([aria-labelledby]) , "
                   +"form input[type=file]:not([title]):not([aria-label]):not([aria-labelledby]) , "
                   +"form input[type=text]:not([title]):not([aria-label]):not([aria-labelledby]) , "
                   +"form input[type=search]:not([title]):not([aria-label]):not([aria-labelledby]) , "
                   +"form input[type=tel]:not([title]):not([aria-label]):not([aria-labelledby]) , "
                   +"form input[type=email]:not([title]):not([aria-label]):not([aria-labelledby]) , "
                   +"form input[type=number]:not([title]):not([aria-label]):not([aria-labelledby]) , "
                   +"form input[type=url]:not([title]):not([aria-label]):not([aria-labelledby]) , "
                   +"form input[type=date]:not([title]):not([aria-label]):not([aria-labelledby]) , "
                   +"form input[type=range]:not([title]):not([aria-label]):not([aria-labelledby]) , "
                   +"form input[type=color]:not([title]):not([aria-label]):not([aria-labelledby]) , "
                   +"form input[type=time]:not([title]):not([aria-label]):not([aria-labelledby]) , "
                   +"form input[type=radio]:not([title]):not([aria-label]):not([aria-labelledby])"; 
    public static final String INPUT_ELEMENT_WITH_ARIA_INSIDE_FORM_CSS_LIKE_QUERY=
                    "form textarea[aria-labelledby] , "
                   +"form select[aria-labelledby] , "
                   +"form datalist[aria-labelledby] , "
                   +"form keygen[aria-labelledby] , "
                   +"form input[type=password][aria-labelledby] , "
                   +"form input[type=checkbox][aria-labelledby] , "
                   +"form input[type=file][aria-labelledby] , "
                   +"form input[type=text][aria-labelledby] , "
                   +"form input[type=search][aria-labelledby] , "
                   +"form input[type=tel][aria-labelledby] , "
                   +"form input[type=email][aria-labelledby] , "
                   +"form input[type=number][aria-labelledby] , "
                   +"form input[type=url][aria-labelledby] , "
                   +"form input[type=date][aria-labelledby] , "
                   +"form input[type=range][aria-labelledby] , "
                   +"form input[type=color][aria-labelledby] , "
                   +"form input[type=time][aria-labelledby] , "
                   +"form input[type=radio][aria-labelledby]"; 
    public static final String FORM_ELEMENT_WITH_ID_CSS_LIKE_QUERY = 
                    "textarea[id] , "
                    + "select[id] , "
                    + "datalist[id] , "
                    + "keygen[id] , "
                    + "input[type=password][id] , "
                    + "input[type=checkbox][id] , "
                    + "input[type=file][id] , "
                    + "input[type=text][id] , "
                    + "input[type=search][id] , "
                    + "input[type=tel][id] , "
                    + "input[type=email][id] , "
                    + "input[type=number][id] , "
                    + "input[type=url][id] , "
                    + "input[type=date][id] , "
                    + "input[type=range][id] , "
                    + "input[type=color][id] , "
                    + "input[type=time][id] , "
                    + "input[type=radio][id]";
    public static final String FORM_ELEMENT_WITH_TITLE_CSS_LIKE_QUERY = 
                    "textarea[title] , "
                    + "select[title] , "
                    + "datalist[title] , "
                    + "keygen[title] , "
                    + "input[type=password][title] , "
                    + "input[type=checkbox][title] , "
                    + "input[type=file][title] , "
                    + "input[type=text][title] , "
                    + "input[type=search][title] , "
                    + "input[type=tel][title] , "
                    + "input[type=email][title] , "
                    + "input[type=number][title] , "
                    + "input[type=url][title] , "
                    + "input[type=date][title] , "
                    + "input[type=range][title] , "
                    + "input[type=color][title] , "
                    + "input[type=time][title] , "
                    + "input[type=radio][title]";
    public static final String FORM_TEXT_INPUT_CSS_LIKE_QUERY = 
                    "form:has(textarea) , "
                    + "form:has(input[type=password]) , "
                    + "form:has(input[type=text])";
    public static final String LABEL_WITHIN_FORM_CSS_LIKE_QUERY=
                    "form:has("+ FORM_ELEMENT_CSS_LIKE_QUERY +") label"; 
    public static final String FORM_LABEL_WITH_INNER_FORM_ELEMENT_CSS_LIKE_QUERY=
                    "form label:has(input[type=text]) , "
                    + "form label:has(input[type=password]) , "
                    + "form label:has(input[type=checkbox]) , "
                    + "form label:has(input[type=radio]) , "
                    + "form label:has(input[type=file]) , "
                    + "form label:has(input[type=search]) , "
                    + "form label:has(input[type=tel]) , "
                    + "form label:has(input[type=email]) , "
                    + "form label:has(input[type=number]) , "
                    + "form label:has(input[type=url]) , "
                    + "form label:has(input[type=date]) , "
                    + "form label:has(input[type=range]) , "
                    + "form label:has(input[type=color]) , "
                    + "form label:has(input[type=time]) , "
                    + "form label:has(textarea) , "
                    + "form label:has(select) , "
                    + "form label:has(datalist) , "
                    + "form label:has(keygen)";
    public static final String LEGEND_WITHIN_FIELDSET_CSS_LIKE_QUERY = 
                    "fieldset legend";
    public static final String SELECT_WITHOUT_OPTGROUP_CSS_LIKE_QUERY = 
                    "select:not(:has(optgroup))";     
    public static final String SELECT_WITHIN_FORM_CSS_LIKE_QUERY = 
                    "form select";
    public static final String OPTGROUP_WITH_LABEL_ATTR_CSS_LIKE_QUERY = 
                    "optgroup[label]";
    public static final String OPTGROUP_WITHIN_SELECT_WITH_LABEL_ATTR_CSS_LIKE_QUERY = 
                    "select optgroup[label]";
    public static final String OPTGROUP_WITHIN_SELECT_CSS_LIKE_QUERY = 
                    "select optgroup";
    public static final String BUTTON_FORM_CSS_LIKE_QUERY = 
                      "form input[type=submit] , "
                    + "form input[type=reset] , "
                    + "form input[type=button] , "
                    + "form input[type=image] , "
                    + "form button ";

    // Dir css-like queries
    public static final String ELEMENT_WITH_DIR_ATTR_CSS_LIKE_QUERY =
            "html [dir]";

    // Lang css-like queries
    public static final String ELEMENT_WITH_LANG_ATTR_CSS_LIKE_QUERY = 
                    "html [lang], html [xml:lang]";
    public static final String ELEMENT_WITHOUT_LANG_ATTR_CSS_LIKE_QUERY = 
                      "html *:not(:matchesOwn(^\\s*$)):not([lang]):not([xml:lang]), "
                    + "html *[alt]:not([alt~=^\\s*$]):not([lang]):not([xml:lang]), "
                    + "html *[title]:not([title~=^\\s*$]):not([lang]):not([xml:lang]), "
                    + "html *[summary]:not([summary~=^\\s*$]):not([lang]):not([xml:lang])"
                    + "html *[content]:not([content~=^\\s*$]):not([lang]):not([xml:lang])"
                    + "hmtl *[value]:not([value~=^\\s*$]):not([lang]):not([xml:lang])";

    // Mandatory elements css-like queries
    public static final String TITLE_WITHIN_HEAD_CSS_LIKE_QUERY = 
                    "head title";
    public static final String HTML_WITH_LANG_CSS_LIKE_QUERY = 
                    "html[lang], html[xml:lang]";
    
    // Links css-like queries
    public static final String NOT_ANCHOR_LINK_CSS_LIKE_QUERY = 
                    "a:not([name]):not([id])";
    public static final String TEXT_LINK_CSS_LIKE_QUERY = 
                    "a[href]:not(:has(*))";
    public static final String LINK_WITH_CHILDREN_CSS_LIKE_QUERY = 
                    "a[href]:has(*)";
    public static final String LINK_WITH_HREF_CSS_LIKE_QUERY = 
                    "a[href]";
    public static final String IMAGE_LINK_CHILDREN_CSS_LIKE_QUERY = 
                    "img[alt] , object[type^=image], object[data^=data:image],"
                  + "object[data$=png], object[data$=jpeg], object[data$=jpg],"
                  + "object[data$=bmp], object[data$=gif], canvas" ;
    public static final String CLICKABLE_AREA_CSS_LIKE_QUERY = "area[href][alt]";
    public static final String LINK_WITHOUT_TARGET_CSS_LIKE_QUERY = 
                    "a:not([href]):not([name]):not([id])";
    public static final String FIELDSET_NOT_WITHIN_FORM_CSS_LIKE_QUERY = 
                    "fieldset:not(form fieldset):not(*[role=search] fieldset):not(*[role=form] fieldset)";
    public static final String LINK_WITH_TARGET_ATTR_CSS_LIKE_QUERY = 
                    "a[href][target]:not([target=_self]):not([target~=^\\s*$])"
                    + ":not([target=_top]):not([target=_parent])";

    // Scripts css-like queries
    public static final String CHANGE_CONTEXT_SCRIPT_CSS_LIKE_QUERY = 
                    "select[onchange], "
                    + "form:has(select)"
                    +     ":not(:has(button))"
                    +     ":not(:has(input[type=submit]))"
                    +     ":not(:has(input[type=button]))"
                    +     ":not(:has(input[type=reset]))";
    
    // Consultation css-like queries
    public static final String META_WITH_REFRESH_CSS_LIKE_QUERY = 
                    "meta[http-equiv=refresh][content*=url]";
    public static final String FORM_CONTROL_CSS_LIKE_QUERY = 
                    "form, "
                    + "select:not(form select), "
                    + "textarea:not(form textarea), "
                    + "input:not(form input):not([type=hidden]), "
                    + "button:not(form button)";

    // Structuration of information css-like queries
    public static final String HEADINGS_CSS_LIKE_QUERY = 
                    "h1, h2, h3, h4, h5, h6";

    // Structuration of information css-like queries
    public static final String ARIA_HEADINGS_CSS_LIKE_QUERY = 
                    "[role=heading][aria-level]";

    // Structuration of information css-like queries
    public static final String ARIA_LEVEL1_HEADINGS_CSS_LIKE_QUERY = 
                    "[role=heading][aria-level=1]";
    
    // Elements with attributes (minus element exceptions)
    public static final String ELEMENT_WITH_WITDH_ATTR_NOT_IMG = 
                    ":not(img):not(svg)[width]:not(svg [width])";
    public static final String ELEMENT_WITH_HEIGHT_ATTR_NOT_IMG =
                    ":not(img):not(svg)[height]:not(svg [height])";
    
    // Elements with attributes (minus element exceptions)
    public static final String ELEMENT_WITH_WITDH_ATTR_NOT_IMG_V2 = 
                    ":not(img):not(svg):not(object):not(embed):not(canvas)[width]:not(svg [width])";
    public static final String ELEMENT_WITH_HEIGHT_ATTR_NOT_IMG_V2 =
                    ":not(img):not(svg):not(object):not(embed):not(canvas)[height]:not(svg [height])";
    
    public static final String IMG_CSS_LIKE_QUERY=
                    IMAGE_LINK_CHILDREN_CSS_LIKE_QUERY
                   + "embed[type^=image]" 
                   + "input[type^=image]"; 
    
    // scripts
    public static final String ONCLICK_CSS_LIKE_QUERY=
                    "*[onclick]"
                    + ":not(a[onlick])"
                    + ":not(area[onclick])"
                    + ":not(button[onlick])"
                    + ":not(input[type=button][onclick])"
                    + ":not(input[type=submit][onclick])"
                    + ":not(input[type=reset][onclick])"
                    + ":not(input[type=image][onclick])"
                    + ":not(input[type=password][onclick])"
                    + ":not(input[type=radio][onclick])"
                    + ":not(input[type=checkbox][onclick])"
                    + ":not(input[type=button][onclick])";
            
    // Seo 
    public static final String META_DESC_CSS_LIKE_QUERY = 
                    "head meta[name=description][content]";
    public static final String FLASH_CONTENT_CSS_LIKE_QUERY =
                    "[type=application/x-shockwave-flash], "
                  + "object[data$=swf], "
                  + "embed[src$=swf]";
    public static final String REL_CANONICAL_CSS_LIKE_QUERY = 
                    "head link[rel=canonical][href]";

    /**
     * Private constructor. This class handles keys and must not be instanciated
     */
    private CssLikeQueryStore() {}

}
