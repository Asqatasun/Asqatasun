package org.opens.tanaguru.contentadapter.css;

import java.util.ArrayList;
import java.util.List;

import org.w3c.css.sac.AttributeCondition;
import org.w3c.css.sac.CharacterDataSelector;
import org.w3c.css.sac.CombinatorCondition;
import org.w3c.css.sac.Condition;
import org.w3c.css.sac.ConditionalSelector;
import org.w3c.css.sac.ContentCondition;
import org.w3c.css.sac.DescendantSelector;
import org.w3c.css.sac.ElementSelector;
import org.w3c.css.sac.LangCondition;
import org.w3c.css.sac.NegativeCondition;
import org.w3c.css.sac.NegativeSelector;
import org.w3c.css.sac.PositionalCondition;
import org.w3c.css.sac.ProcessingInstructionSelector;
import org.w3c.css.sac.Selector;
import org.w3c.css.sac.SiblingSelector;
import org.w3c.css.sac.SimpleSelector;

import java.io.Serializable;

public class CSSOMSelectorImpl implements CSSOMSelector, Serializable {

    private List<CSSOMDeclaration> ownerDeclaration = new ArrayList<CSSOMDeclaration>();
    private Selector selector;
    private String selectorTxt;

    public CSSOMSelectorImpl() {
        super();
    }

    public CSSOMSelectorImpl(Selector selector,
            List<CSSOMDeclaration> selectorDeclaration) {
        super();
        this.selectorTxt = "";
        this.selector = selector;
        this.ownerDeclaration = selectorDeclaration;
    }

    @Override
    public boolean addCSSOMDeclaration(CSSOMDeclaration declaration) {
        return ownerDeclaration.add(declaration);
    }

    @Override
    public String conditionToString(Condition condition) {

        if (condition instanceof AttributeCondition) {
            AttributeCondition attributeCondition = (AttributeCondition) condition;

            short conditionType = attributeCondition.getConditionType();

            switch (conditionType) {
                case (Condition.SAC_CLASS_CONDITION):
                    selectorTxt += ".";
                    break;
                case (Condition.SAC_ID_CONDITION):
                    selectorTxt += "#";
                    break;
                case (Condition.SAC_PSEUDO_CLASS_CONDITION):
                    selectorTxt += ":";
                    break;

            }

            selectorTxt += attributeCondition.getValue();
        }
        if (condition instanceof LangCondition) {
            LangCondition langCondition = (LangCondition) condition;
            selectorTxt += ":lang(" + langCondition.getLang() + ")";
        }
        if (condition instanceof NegativeCondition) {
            NegativeCondition negativeCondition = (NegativeCondition) condition;
            conditionToString(negativeCondition.getCondition());
        }
        if (condition instanceof CombinatorCondition) {
            CombinatorCondition combinatorCondition = (CombinatorCondition) condition;
            conditionToString(combinatorCondition.getFirstCondition());
            conditionToString(combinatorCondition.getSecondCondition());
        }

        return selectorTxt;
    }

    @Override
    public List<CSSOMDeclaration> getOwnerDeclaration() {
        return ownerDeclaration;
    }

    @Override
    public Selector getSelector() {
        return selector;
    }

    @Override
    public String getSelectorTxt() {
        return selectorTxt;
    }

    @Override
    public String selectorToString(Selector selector) {

        if (selector instanceof SimpleSelector) {

            SimpleSelector simpleSelector = (SimpleSelector) selector;
            if (simpleSelector instanceof ElementSelector) {
                ElementSelector elementSelector = (ElementSelector) simpleSelector;

                if (elementSelector.getLocalName() == null) {
                    selectorTxt += "*";
                } else {
                    selectorTxt += elementSelector.getLocalName();
                }
            }
            if (simpleSelector instanceof ConditionalSelector) {
                ConditionalSelector conditionalSelector = (ConditionalSelector) simpleSelector;

                selectorToString(conditionalSelector.getSimpleSelector());
                conditionToString(conditionalSelector.getCondition());
            }

        }
        if (selector instanceof DescendantSelector) {
            DescendantSelector ds = (DescendantSelector) selector;

            selectorToString(ds.getAncestorSelector());

            short descendentType = ds.getSelectorType();

            switch (descendentType) {
                case (Selector.SAC_CHILD_SELECTOR):
                    selectorTxt += " > ";
                    break;
                case (Selector.SAC_DESCENDANT_SELECTOR):
                    selectorTxt += " ";
                    break;
            }
            selectorToString(ds.getSimpleSelector());

        }
        if (selector instanceof SiblingSelector) {
            SiblingSelector siblingSelector = (SiblingSelector) selector;

            selectorToString(siblingSelector.getSelector());
            selectorTxt += " + ";
            selectorToString(siblingSelector.getSiblingSelector());

        }
        return selectorTxt;
    }

    @Override
    public void setSelector(Selector selector) {
        this.selector = selector;
    }

    @Override
    public void setSelectorDeclaration(
            List<CSSOMDeclaration> selectorDeclaration) {
        this.ownerDeclaration = selectorDeclaration;
    }

    @Override
    public void setSelectorTxt(String selectorTxt) {
        this.selectorTxt = selectorTxt;
    }
}
