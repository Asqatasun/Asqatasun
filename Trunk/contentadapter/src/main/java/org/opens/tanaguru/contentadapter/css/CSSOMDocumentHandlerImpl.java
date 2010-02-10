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

import org.opens.tanaguru.contentadapter.Resource;
import org.opens.tanaguru.contentadapter.RsrcLocator;

/**
 * 
 * @author ADEX refer to org.w3c.css.sac.DocumentHandler javadoc
 */
public class CSSOMDocumentHandlerImpl implements DocumentHandler {

	private Object nodeRoot = null;
	private Stack nodeStack;
	private Resource rsrc;
	private CSSOMStyleSheet styleSheet;

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
		// Not used
	}

	public void endMedia(SACMediaList arg0) throws CSSException {
		// Not used
	}

	public void endPage(String arg0, String arg1) throws CSSException {
		// Not used
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
			break;

		case LexicalUnit.SAC_OPERATOR_PLUS:
			break;

		case LexicalUnit.SAC_OPERATOR_MINUS:
			break;

		case LexicalUnit.SAC_OPERATOR_MULTIPLY:
			break;

		case LexicalUnit.SAC_OPERATOR_SLASH:
			break;

		case LexicalUnit.SAC_OPERATOR_MOD:
			break;

		case LexicalUnit.SAC_OPERATOR_EXP:
			break;

		case LexicalUnit.SAC_OPERATOR_LT:
			break;

		case LexicalUnit.SAC_OPERATOR_GT:
			break;

		case LexicalUnit.SAC_OPERATOR_LE:
			break;

		case LexicalUnit.SAC_OPERATOR_GE:
			break;

		case LexicalUnit.SAC_OPERATOR_TILDE:
			break;

		case LexicalUnit.SAC_INHERIT:
			break;

		case LexicalUnit.SAC_INTEGER:
			break;

		case LexicalUnit.SAC_REAL:
			break;

		case LexicalUnit.SAC_EM:
			break;

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
			break;

		case LexicalUnit.SAC_URI:
			break;

		case LexicalUnit.SAC_COUNTER_FUNCTION:
			break;

		case LexicalUnit.SAC_COUNTERS_FUNCTION:
			break;

		case LexicalUnit.SAC_RGBCOLOR:

			lexicalUnitValue = "RGB(" + lexicalUnit.getParameters() + ")";
			break;

		case LexicalUnit.SAC_DEGREE:
			break;

		case LexicalUnit.SAC_GRADIAN:
			break;

		case LexicalUnit.SAC_RADIAN:
			break;

		case LexicalUnit.SAC_MILLISECOND:
			break;

		case LexicalUnit.SAC_SECOND:
			break;

		case LexicalUnit.SAC_HERTZ:
			break;

		case LexicalUnit.SAC_KILOHERTZ:
			break;

		case LexicalUnit.SAC_IDENT:
			lexicalUnitValue = lexicalUnit.getStringValue();
			break;

		case LexicalUnit.SAC_STRING_VALUE:
			break;

		case LexicalUnit.SAC_ATTR:
			break;

		case LexicalUnit.SAC_RECT_FUNCTION:
			break;

		case LexicalUnit.SAC_UNICODERANGE:
			break;

		case LexicalUnit.SAC_SUB_EXPRESSION:
			break;

		case LexicalUnit.SAC_FUNCTION:
			break;

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

		((List<CSSOMDeclarationImpl>) getNodeStack().peek())
				.add(new CSSOMDeclarationImpl(property,
						getLexicalValue(propertyValue), propertyValue
								.getLexicalUnitType()));

	}

	protected void setNodeStack(Stack nodeStack) {
		this.nodeStack = nodeStack;
	}

	public void startDocument(InputSource arg0) throws CSSException {

		if (getNodeStack().empty()) {
			styleSheet = new CSSOMStyleSheetImpl(rsrc);

			// check if the style sheet is of referended type
			if (styleSheet.getType() == RsrcLocator.REFERENCED) {
				// treatment here
			}

			// create the rule list
			List<CSSOMRule> rules = new ArrayList<CSSOMRule>();
			styleSheet.setRules(rules);
			getNodeStack().push(styleSheet);
			getNodeStack().push(rules);
		} else {
			// XXX error
		}

	}

	public void startFontFace() throws CSSException {
		// Not used
	}

	public void startMedia(SACMediaList arg0) throws CSSException {
		// Not used
	}

	public void startPage(String arg0, String arg1) throws CSSException {
		// Not used
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

		if (!(getNodeStack().empty())) {
			((List<CSSOMRule>) getNodeStack().peek()).add(rule);
		}

		getNodeStack().push(rule);
		getNodeStack().push(declarations);
	}
}
