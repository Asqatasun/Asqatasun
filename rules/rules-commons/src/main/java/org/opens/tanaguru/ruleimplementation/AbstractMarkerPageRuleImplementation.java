/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2013  Open-S Company
 *
 * This program is free software: you can redistribute it and/or modify
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
package org.opens.tanaguru.ruleimplementation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.annotation.Nonnull;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.rules.elementchecker.ElementChecker;
import org.opens.tanaguru.rules.elementchecker.NomenclatureBasedElementChecker;
import org.opens.tanaguru.rules.elementselector.ElementSelector;
import static org.opens.tanaguru.rules.keystore.AttributeStore.ROLE_ATTR;

/**
 * This class should be overridden by concrete {@link RuleImplementation}
 * classes which implement tests with page scope.
 * <p>
 * It deals with the selection of elements identified by markers. These markers
 * correspond to a specific value of either the "id" attribute, either the
 * "class" attribute or the "role" attribute of a HTML element.</p>
 * <p>
 * Two kind of markers can be defined :
 * <ul>
 * <li>
 * Direct marker that enable to include elements into the selection and
 * determine their nature
 * </li>
 * <li>
 * Inverse marker that identify elements of same type but with another nature.
 * These elements have to be excluded from the selection
 * </li>
 * </ul>
 * </p>
 *
 */
public abstract class AbstractMarkerPageRuleImplementation
        extends AbstractPageRuleMarkupImplementation {

    /**
     * The elements identified with the markers
     */
    private final ElementHandler<Element> selectionWithMarkerHandler
            = new ElementHandlerImpl();

    public ElementHandler<Element> getSelectionWithMarkerHandler() {
        return selectionWithMarkerHandler;
    }

    /**
     * The elements not identified by the markers
     */
    private final ElementHandler<Element> selectionWithoutMarkerHandler
            = new ElementHandlerImpl();

    public ElementHandler<Element> getSelectionWithoutMarkerHandler() {
        return selectionWithoutMarkerHandler;
    }

    /**
     * The marker code that identifies the targeted elements
     */
    private final String markerCode;

    /**
     * The marker code that identifies elements of same type but with another
     * nature.
     */
    private final String inverseMarkerCode;

    /**
     * The collection of marker that enable to identify the targeted elements
     */
    private Collection<String> markerList;

    /**
     * The collection of marker that enable to identify elements of the same
     * type but with another nature.
     */
    private Collection<String> inverseMarkerList;

    /**
     * The elementSelector used by the rule
     */
    private ElementSelector elementSelector;

    public void setElementSelector(ElementSelector elementSelector) {
        this.elementSelector = elementSelector;
    }

    /**
     * The elementChecker used by the rule
     */
    private ElementChecker elementChecker;

    public void setElementChecker(ElementChecker elementChecker) {
        this.elementChecker = elementChecker;
    }

    /**
     * The elementChecker used by the rule for marker elements
     */
    private ElementChecker markerElementChecker;

    public void setMarkerElementChecker(ElementChecker markerElementChecker) {
        this.markerElementChecker = markerElementChecker;
    }

    /**
     * Constructor
     *
     * @param markerCode
     * @param inverseMarkerCode
     */
    public AbstractMarkerPageRuleImplementation(
            @Nonnull String markerCode,
            @Nonnull String inverseMarkerCode) {
        super();
        this.markerCode = markerCode;
        this.inverseMarkerCode = inverseMarkerCode;
    }

    /**
     * Constructor
     *
     * @param elementSelector
     * @param markerCode
     * @param inverseMarkerCode
     * @param markerElementChecker
     * @param elementChecker
     */
    public AbstractMarkerPageRuleImplementation(
            @Nonnull ElementSelector elementSelector,
            @Nonnull String markerCode,
            @Nonnull String inverseMarkerCode,
            @Nonnull ElementChecker markerElementChecker,
            @Nonnull ElementChecker elementChecker) {
        super();
        this.markerCode = markerCode;
        this.inverseMarkerCode = inverseMarkerCode;
        this.elementSelector = elementSelector;
        this.elementChecker = elementChecker;
        this.markerElementChecker = markerElementChecker;
    }

    @Override
    protected void select(SSPHandler sspHandler, ElementHandler<Element> elementHandler) {
        elementSelector.selectElements(sspHandler, selectionWithoutMarkerHandler);
        extractMarkerListFromAuditParameter(sspHandler);
        sortMarkerElements();
    }

    @Override
    protected void check(
            SSPHandler sspHandler,
            ElementHandler<Element> selectionHandler,
            TestSolutionHandler testSolutionHandler) {
         if (!selectionWithMarkerHandler.isEmpty()) {
            setServicesToChecker(markerElementChecker);
            markerElementChecker.check(
                    sspHandler,
                    selectionWithMarkerHandler,
                    testSolutionHandler);
        }
        if (!selectionWithoutMarkerHandler.isEmpty()) {
            setServicesToChecker(elementChecker);
            elementChecker.check(
                    sspHandler,
                    selectionWithoutMarkerHandler,
                    testSolutionHandler);
        }
    }

    @Override
    public int getSelectionSize() {
         return this.selectionWithoutMarkerHandler.get().size()
                + this.selectionWithMarkerHandler.get().size();
    }

    /**
     * Retrieves the parameter value from audit parameters and return the marker
     * list
     *
     * @param sspHandler
     */
    protected void extractMarkerListFromAuditParameter(SSPHandler sspHandler) {
        boolean markerFound = false;
        boolean inverseMarkerFound = false;
        for (Parameter parameter : sspHandler.getSSP().getAudit().getParameterSet()) {
            if (parameter.getParameterElement().getParameterElementCode().equalsIgnoreCase(markerCode)) {
                String markerTab = parameter.getValue();
                if (StringUtils.isNotEmpty(markerTab)) {
                    markerList = new ArrayList();
                    inverseMarkerFound = initMarkerList(markerTab, markerList);
                }
            }
            if (parameter.getParameterElement().getParameterElementCode().equalsIgnoreCase(inverseMarkerCode)) {
                String markerTab = parameter.getValue();
                if (StringUtils.isNotEmpty(markerTab)) {
                    inverseMarkerList = new ArrayList();
                    inverseMarkerFound = initMarkerList(markerTab, inverseMarkerList);
                }
            }
            if (inverseMarkerFound && markerFound) {
                break;
            }
        }
    }

    /**
     * To sort marker elements, we extract for each of them the value of the
     * "id" attribute the value of the "class" attribute and the value of the
     * "role" attribute. If one of these three values belongs to the marker
     * value list set by the user, we consider that the element is characterised
     * and we add it to the "elementMarkerList".
     *
     * @param nodeList
     */
    private void sortMarkerElements() {
        if ((CollectionUtils.isEmpty(markerList) && CollectionUtils.isEmpty(inverseMarkerList))
                || selectionWithoutMarkerHandler.isEmpty()) {
            return;
        }
        Iterator<Element> iter = selectionWithoutMarkerHandler.get().iterator();
        Element el;
        while (iter.hasNext()) {
            el = iter.next();
            String id = el.id();
            Collection<String> classNames = el.classNames();
            String role = el.attr(ROLE_ATTR);
            // if the element does contain an "id" OR a "class" attribute OR
            // a "role" attribute AND one the values belongs to the marker list, 
            // it is removed from the global selection and added to the 
            // marker element selection.
            if (StringUtils.isNotBlank(id) || CollectionUtils.isNotEmpty(classNames)
                    || StringUtils.isNotBlank(role)) {
                if (checkAttributeBelongsToMarkerList(id, classNames, role, markerList)) {
                    selectionWithMarkerHandler.add(el);
                    iter.remove();
                }
                // if the element belongs to the inverse marker list, it is
                // removed from the global collection
                if (checkAttributeBelongsToMarkerList(id, classNames, role, inverseMarkerList)) {
                    iter.remove();
                }
            }
        }
    }

    /**
     * @param id
     * @param classNames
     * @param role
     * @return whether one of the string given as argument belongs to a marker
     * list
     */
    private boolean checkAttributeBelongsToMarkerList(
            String id,
            Collection<String> classNames,
            String role,
            Collection<String> markerList) {
        if (CollectionUtils.isEmpty(markerList)) {
            return false;
        }
        Collection<String> elAttr = new ArrayList();
        elAttr.add(id);
        elAttr.addAll(classNames);
        elAttr.add(role);
        return CollectionUtils.containsAny(markerList, elAttr);
    }

    /**
     * Set service to elementChecker depending on their nature.
     *
     * @param elementChecker
     */
    private void setServicesToChecker(ElementChecker elementChecker) {
        if (elementChecker instanceof NomenclatureBasedElementChecker) {
            ((NomenclatureBasedElementChecker) elementChecker).
                    setNomenclatureLoaderService(nomenclatureLoaderService);
        }
    }

    /**
     *
     * Utility method to extract marker values, and apply a trim on values set
     * by the user
     *
     * @param parameterValue
     * @param markerList
     * @return whether the marker list is correctly initialised
     */
    private boolean initMarkerList(String parameters, Collection<String> markerList) {
        for (String markerValue : parameters.split(";")) {
            markerList.add(markerValue.trim());
        }
        return true;
    }

}
