package org.opens.tanaguru.contentadapter.css;

import java.util.ArrayList;
import java.util.List;

import java.io.Serializable;

/**
 * 
 * @author ADEX
 */
public class CSSOMRuleImpl implements CSSOMRule, Serializable {

	private List<CSSOMDeclaration> declarations = new ArrayList<CSSOMDeclaration>();
	private CSSOMStyleSheet ownerStyle;
	private List<CSSOMSelector> selectors = new ArrayList<CSSOMSelector>();

	public CSSOMRuleImpl() {
		super();
	}

	public CSSOMRuleImpl(List<CSSOMSelector> selectors,
			List<CSSOMDeclaration> declarations, CSSOMStyleSheet ownerStyle,
			String ruleTxt) {
		super();
		this.selectors = selectors;
		this.declarations = declarations;
		this.ownerStyle = ownerStyle;
	}

	public boolean addCSSOMDeclaration(CSSOMDeclaration declaration) {
		return declarations.add(declaration);
	}

	public boolean addCSSOMSelector(CSSOMSelector selector) {
		return selectors.add(selector);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		CSSOMRuleImpl other = (CSSOMRuleImpl) obj;
		if (declarations == null) {
			if (other.declarations != null) {
				return false;
			}
		} else if (!declarations.equals(other.declarations)) {
			return false;
		}
		if (selectors == null) {
			if (other.selectors != null) {
				return false;
			}
		} else if (!selectors.equals(other.selectors)) {
			return false;
		}
		return true;
	}

	public List<CSSOMDeclaration> getDeclarations() {
		return declarations;
	}

	public CSSOMStyleSheet getOwnerStyle() {
		return ownerStyle;
	}

	public List<CSSOMSelector> getSelectors() {
		return selectors;
	}

	public void setDeclarations(List<CSSOMDeclaration> declarations) {
		this.declarations = declarations;
	}

	public void setOwnerStyle(CSSOMStyleSheet ownerStyle) {
		this.ownerStyle = ownerStyle;
	}

	public void setSelectors(List<CSSOMSelector> selectors) {
		this.selectors = selectors;
	}
}
