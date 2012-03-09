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
package org.opens.tanaguru.contentadapter.css;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.w3c.css.sac.CSSException;
import org.w3c.css.sac.DocumentHandler;
import org.w3c.css.sac.InputSource;
import org.w3c.css.sac.LexicalUnit;
import org.w3c.css.sac.SACMediaList;
import org.w3c.css.sac.Selector;
import org.w3c.css.sac.SelectorList;
import org.w3c.flute.parser.selectors.SelectorFactoryImpl;
import org.opens.tanaguru.contentadapter.Resource;
import org.w3c.css.sac.SelectorFactory;

/**
 * Refer to org.w3c.css.sac.DocumentHandler javadoc.
 * @author jkowalczyk
 */
public class CSSOMDocumentHandlerImpl implements DocumentHandler {

    private Object nodeRoot = null;
    private Stack nodeStack;
    private Resource rsrc;
    private CSSOMStyleSheet styleSheet;
    private SACMediaList currentMediaList;

    public CSSOMDocumentHandlerImpl() {
        super();
    }

    public CSSOMDocumentHandlerImpl(CSSResource rsrc) {
        super();
        this.rsrc = rsrc;
    }

    @Override
    public void comment(String arg0) throws CSSException {
        // Not used
    }

    @Override
    public void endDocument(InputSource arg0) throws CSSException {
        // pop the rule list and the style sheet nodes
        getNodeStack().pop();
        nodeRoot = getNodeStack().pop();
    }

    @Override
    public void endFontFace() throws CSSException {
        getNodeStack().pop();
        nodeRoot = getNodeStack().pop();
    }

    @Override
    public void endMedia(SACMediaList arg0) throws CSSException {
        currentMediaList = null;
    }

    @Override
    public void endPage(String arg0, String arg1) throws CSSException {
        getNodeStack().pop();
        nodeRoot = getNodeStack().pop();
    }

    @Override
    public void endSelector(SelectorList arg0) throws CSSException {

        getNodeStack().pop();
        nodeRoot = getNodeStack().pop();
    }

    protected String getLexicalValue(LexicalUnit lexicalUnit) {
        String lexicalUnitValue = null;
        // XXX Complete lexicalUnits for other units where case is empty.

        switch (lexicalUnit.getLexicalUnitType()) {

            case LexicalUnit.SAC_OPERATOR_COMMA:
            case LexicalUnit.SAC_OPERATOR_PLUS:
            case LexicalUnit.SAC_OPERATOR_MINUS:
            case LexicalUnit.SAC_OPERATOR_MULTIPLY:
            case LexicalUnit.SAC_OPERATOR_SLASH:
            case LexicalUnit.SAC_OPERATOR_MOD:
            case LexicalUnit.SAC_OPERATOR_EXP:
            case LexicalUnit.SAC_OPERATOR_LT:
            case LexicalUnit.SAC_OPERATOR_GT:
            case LexicalUnit.SAC_OPERATOR_LE:
            case LexicalUnit.SAC_OPERATOR_GE:
            case LexicalUnit.SAC_OPERATOR_TILDE:
            case LexicalUnit.SAC_INHERIT:
            case LexicalUnit.SAC_INTEGER:
            case LexicalUnit.SAC_REAL:
            case LexicalUnit.SAC_EM:
            case LexicalUnit.SAC_EX:
                break;

            case LexicalUnit.SAC_PIXEL:
                Float pixelValue = new Float(lexicalUnit.getFloatValue());
                lexicalUnitValue = pixelValue.toString() + "px";
                break;

            case LexicalUnit.SAC_INCH:
                Float inchValue = new Float(lexicalUnit.getFloatValue());
                lexicalUnitValue = inchValue.toString() + "in";
                break;

            case LexicalUnit.SAC_CENTIMETER:
                Float centimeterValue = new Float(lexicalUnit.getFloatValue());
                lexicalUnitValue = centimeterValue.toString() + "cm";
                break;

            case LexicalUnit.SAC_MILLIMETER:
                Float millimeterValue = new Float(lexicalUnit.getFloatValue());
                lexicalUnitValue = millimeterValue.toString() + "mm";
                break;

            case LexicalUnit.SAC_POINT:
                Float pointValue = new Float(lexicalUnit.getFloatValue());
                lexicalUnitValue = pointValue.toString() + "pt";
                break;

            case LexicalUnit.SAC_PICA:
                Float picaValue = new Float(lexicalUnit.getFloatValue());
                lexicalUnitValue = picaValue.toString() + "pc";
                break;

            case LexicalUnit.SAC_PERCENTAGE:
            case LexicalUnit.SAC_URI:
            case LexicalUnit.SAC_COUNTER_FUNCTION:
            case LexicalUnit.SAC_COUNTERS_FUNCTION:
                break;

            case LexicalUnit.SAC_RGBCOLOR:
                lexicalUnitValue = "RGB(" + lexicalUnit.getParameters() + ")";
                break;

            case LexicalUnit.SAC_DEGREE:
            case LexicalUnit.SAC_GRADIAN:
            case LexicalUnit.SAC_RADIAN:
            case LexicalUnit.SAC_MILLISECOND:
            case LexicalUnit.SAC_SECOND:
            case LexicalUnit.SAC_HERTZ:
            case LexicalUnit.SAC_KILOHERTZ:
                break;

            case LexicalUnit.SAC_IDENT:
                lexicalUnitValue = lexicalUnit.getStringValue();
                break;

            case LexicalUnit.SAC_STRING_VALUE:
            case LexicalUnit.SAC_ATTR:
            case LexicalUnit.SAC_RECT_FUNCTION:
            case LexicalUnit.SAC_UNICODERANGE:
            case LexicalUnit.SAC_SUB_EXPRESSION:
            case LexicalUnit.SAC_FUNCTION:
            case LexicalUnit.SAC_DIMENSION:
                break;

            default:
                break;
        }

        return lexicalUnitValue;
    }

    protected Object getNodeRoot() {
        return nodeRoot;
    }

    protected Stack getNodeStack() {
        if (nodeStack == null) {
            nodeStack = new Stack();
        }
        return nodeStack;
    }

    public CSSOMStyleSheet getResult() {
        return styleSheet;
    }

    @Override
    public void ignorableAtRule(String arg0) throws CSSException {
        // Not used
    }

    @Override
    public void importStyle(String arg0, SACMediaList arg1, String arg2)
            throws CSSException {
        // Not used
    }

    @Override
    public void namespaceDeclaration(String arg0, String arg1)
            throws CSSException {
        // Not used
    }

    @Override
    public void property(String property, LexicalUnit propertyValue,
            boolean arg2) throws CSSException {
        boolean hasNextLexicalUnit = true;
        LexicalUnit currentLexicalUnit = propertyValue;
        while (hasNextLexicalUnit) {
            ((List<CSSOMDeclarationImpl>) getNodeStack().peek()).add(
                    new CSSOMDeclarationImpl(
                        property,
                        getLexicalValue(currentLexicalUnit),
                        currentLexicalUnit.getLexicalUnitType()));
            if (currentLexicalUnit.getNextLexicalUnit() != null) {
                currentLexicalUnit = currentLexicalUnit.getNextLexicalUnit();
            } else {
                hasNextLexicalUnit = false;
            }
        }

    }

    protected void setNodeStack(Stack nodeStack) {
        this.nodeStack = nodeStack;
    }

    @Override
    public void startDocument(InputSource arg0) throws CSSException {

        if (getNodeStack().empty()) {
            styleSheet = new CSSOMStyleSheetImpl(rsrc);

            // create the rule list
            List<CSSOMRule> rules = new ArrayList<CSSOMRule>();
            styleSheet.setRules(rules);
            getNodeStack().push(styleSheet);
            getNodeStack().push(rules);
        }
    }

    @Override
    public void startFontFace() throws CSSException {
        List<CSSOMSelector> selectors = new ArrayList<CSSOMSelector>();
        List<CSSOMDeclaration> declarations = new ArrayList<CSSOMDeclaration>();
        SelectorFactory selectorFactory = new SelectorFactoryImpl();

        // If the namespace URI is not null, a cssException is thrown
        Selector selector = selectorFactory.
                createPseudoElementSelector(null, "FontFace");
        selectors.add(new CSSOMSelectorImpl(selector, declarations));

        // create the rule and add it to the rule list
        CSSOMRuleImpl rule = new CSSOMRuleImpl(selectors, declarations,
                styleSheet, null);

        if (currentMediaList != null) {
            rule.setMediaList(currentMediaList);
        } else {
            rule.setMediaList(styleSheet.getMediaList());
        }

        if (!(getNodeStack().empty())) {
            ((List<CSSOMRule>) getNodeStack().peek()).add(rule);
        }

        getNodeStack().push(rule);
        getNodeStack().push(declarations);
    }

    @Override
    public void startMedia(SACMediaList mediaList) throws CSSException {
        currentMediaList = mediaList;
    }

    @Override
    public void startPage(String arg0, String arg1) throws CSSException {

        List<CSSOMSelector> selectors = new ArrayList<CSSOMSelector>();
        List<CSSOMDeclaration> declarations = new ArrayList<CSSOMDeclaration>();
        SelectorFactory selectorFactory = new SelectorFactoryImpl();

        // If the namespace URI is not null, a cssException is thrown
        Selector selector = selectorFactory.
                createPseudoElementSelector(null, arg1);
        selectors.add(new CSSOMSelectorImpl(selector, declarations));

        // create the rule and add it to the rule list
        CSSOMRuleImpl rule = new CSSOMRuleImpl(selectors, declarations,
                styleSheet, null);

        if (currentMediaList != null) {
            rule.setMediaList(currentMediaList);
        } else {
            rule.setMediaList(styleSheet.getMediaList());
        }

        if (!(getNodeStack().empty())) {
            ((List<CSSOMRule>) getNodeStack().peek()).add(rule);
        }

        getNodeStack().push(rule);
        getNodeStack().push(declarations);
    }

    @Override
    public void startSelector(SelectorList selectorList) throws CSSException {
        List<CSSOMSelector> selectors = new ArrayList<CSSOMSelector>();
        List<CSSOMDeclaration> declarations = new ArrayList<CSSOMDeclaration>();

        for (int i = 0; i < selectorList.getLength(); i++) {
            
            Selector selector = selectorList.item(i);
            selectors.add(new CSSOMSelectorImpl(selector, declarations));
        }

        // create the rule and add it to the rule list
        CSSOMRuleImpl rule = new CSSOMRuleImpl(selectors, declarations,
                styleSheet, null);

        if (currentMediaList != null) {
            rule.setMediaList(currentMediaList);
        } else {
            rule.setMediaList(styleSheet.getMediaList());
        }

        if (!(getNodeStack().empty())) {
            ((List<CSSOMRule>) getNodeStack().peek()).add(rule);
        }

        getNodeStack().push(rule);
        getNodeStack().push(declarations);
    }

}