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
import org.opens.tanaguru.contentadapter.RsrcLocator;
import org.w3c.css.sac.SelectorFactory;

/**
 * 
 * @author ADEX refer to org.w3c.css.sac.DocumentHandler javadoc
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

    public void comment(String arg0) throws CSSException {
        // Not used
    }

    public void endDocument(InputSource arg0) throws CSSException {
        // pop the rule list and the style sheet nodes
        getNodeStack().pop();
        nodeRoot = getNodeStack().pop();
    }

    public void endFontFace() throws CSSException {
        getNodeStack().pop();
        nodeRoot = getNodeStack().pop();
    }

    public void endMedia(SACMediaList arg0) throws CSSException {
        currentMediaList = null;
    }

    public void endPage(String arg0, String arg1) throws CSSException {
        getNodeStack().pop();
        nodeRoot = getNodeStack().pop();
    }

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

    public void ignorableAtRule(String arg0) throws CSSException {
        // Not used
    }

    public void importStyle(String arg0, SACMediaList arg1, String arg2)
            throws CSSException {
        // Not used
    }

    public void namespaceDeclaration(String arg0, String arg1)
            throws CSSException {
        // Not used
    }

    public void property(String property, LexicalUnit propertyValue,
            boolean arg2) throws CSSException {
        ((List<CSSOMDeclarationImpl>) getNodeStack().peek()).add(
                new CSSOMDeclarationImpl(
                    property,
                    getLexicalValue(propertyValue),
                    propertyValue.getLexicalUnitType()));

    }

    protected void setNodeStack(Stack nodeStack) {
        this.nodeStack = nodeStack;
    }

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

    public void startMedia(SACMediaList mediaList) throws CSSException {
        currentMediaList = mediaList;
    }

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