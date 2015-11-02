/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
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
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.tanaguru.ruleimplementation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import javax.annotation.Nonnull;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.tanaguru.entity.parameterization.Parameter;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.rules.elementchecker.ElementChecker;
import org.tanaguru.rules.elementselector.ElementSelector;
import static org.tanaguru.rules.keystore.AttributeStore.ROLE_ATTR;

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
        extends AbstractPageRuleWithSelectorAndCheckerImplementation {

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
     * The marker codes that identifies the targeted elements
     */
    private final String[] markerCodes;

    /**
     * The marker codes that identifies elements of same type but with another
     * nature.
     */
    private final String[] inverseMarkerCodes;

    /**
     * The collection of marker that enable to identify the targeted elements
     */
    private final Collection<String> markerList = new HashSet<>();

    /**
     * The collection of marker that enable to identify elements of the same
     * type but with another nature.
     */
    private final Collection<String> inverseMarkerList = new HashSet<>();

    /**
     * The elementChecker used by the rule for marker elements
     */
    private ElementChecker markerElementChecker;
    public void setMarkerElementChecker(ElementChecker markerElementChecker) {
        this.markerElementChecker = markerElementChecker;
    }
    
    /**
     * The elementChecker used by the rule for regular elements, i.e not 
     * identified by marker
     * @return 
     */
    public ElementChecker getRegularElementChecker() {
        return getElementChecker();
    }
    
    public final void setRegularElementChecker(ElementChecker regularElementChecker) {
        this.setElementChecker(regularElementChecker);
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
        this(new String[]{markerCode}, new String[]{inverseMarkerCode});
    }
    
    /**
     * Constructor
     *
     * @param markerCode
     * @param inverseMarkerCode
     */
    public AbstractMarkerPageRuleImplementation(
            @Nonnull String[] markerCode,
            @Nonnull String[] inverseMarkerCode) {
        super();
        this.markerCodes = markerCode;
        this.inverseMarkerCodes = inverseMarkerCode;
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
        this(
                elementSelector,
                new String[]{markerCode},
                new String[]{inverseMarkerCode}, 
                markerElementChecker,
                elementChecker
            );
    }
    
    /**
     * Constructor
     *
     * @param elementSelector
     * @param markerCode
     * @param inverseMarkerCode
     * @param markerElementChecker
     * @param regularElementChecker
     */
    public AbstractMarkerPageRuleImplementation(
            @Nonnull ElementSelector elementSelector,
            @Nonnull String[] markerCode,
            @Nonnull String[] inverseMarkerCode,
            @Nonnull ElementChecker markerElementChecker,
            @Nonnull ElementChecker regularElementChecker) {
        super();
        this.markerCodes = markerCode;
        this.inverseMarkerCodes = inverseMarkerCode;
        setElementSelector(elementSelector);
        this.setElementChecker(regularElementChecker);
        this.markerElementChecker = markerElementChecker;
    }

    @Override
    protected void select(SSPHandler sspHandler) {
        getElementSelector().selectElements(sspHandler, selectionWithoutMarkerHandler);
        extractMarkerListFromAuditParameter(sspHandler);
        sortMarkerElements();
    }
        
    @Override
    protected void check(
            SSPHandler sspHandler,
            TestSolutionHandler testSolutionHandler) {
        if (!selectionWithMarkerHandler.isEmpty()) {
            setServicesToChecker(markerElementChecker);
            markerElementChecker.check(
                    sspHandler,
                    selectionWithMarkerHandler,
                    testSolutionHandler);
        }
        if (!selectionWithoutMarkerHandler.isEmpty()) {
            setServicesToChecker(getRegularElementChecker());
            getRegularElementChecker().check(
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

        for (Parameter parameter : sspHandler.getSSP().getAudit().getParameterSet()) {
            String paramElCode = parameter.getParameterElement().getParameterElementCode();
            for (String markerCode : markerCodes) {
                if (StringUtils.equalsIgnoreCase(paramElCode, markerCode)) {
                    String markerTab = parameter.getValue();
                    if (StringUtils.isNotEmpty(markerTab)) {
                        addMarkersToList(markerTab, markerList);
                    }
                }
            }
            for (String inverseMarkerCode : inverseMarkerCodes) {
                if (StringUtils.equalsIgnoreCase(paramElCode, inverseMarkerCode)) {
                    String markerTab = parameter.getValue();
                    if (StringUtils.isNotEmpty(markerTab)) {
                        addMarkersToList(markerTab, inverseMarkerList);
                    }
                }
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
        Collection<String> elAttr = new ArrayList<>();
        elAttr.add(id);
        elAttr.addAll(classNames);
        elAttr.add(role);
        return CollectionUtils.containsAny(markerList, elAttr);
    }

    /**
     *
     * Utility method to extract marker values, and apply a trim on values set
     * by the user
     *
     * @param parameterValue
     * @param markers
     */
    private void addMarkersToList(String parameters, Collection<String> markers) {
        for (String markerValue : parameters.split(";")) {
            markers.add(markerValue.trim());
        }
    }

}