/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2019  Asqatasun.org
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
package org.asqatasun.ruleimplementation;

import java.util.*;
import javax.annotation.Nonnull;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.asqatasun.entity.parameterization.Parameter;
import org.asqatasun.processor.SSPHandler;
import org.asqatasun.rules.elementchecker.ElementChecker;
import org.asqatasun.rules.elementselector.ElementSelector;
import static org.asqatasun.rules.keystore.AttributeStore.ROLE_ATTR;
import static org.asqatasun.rules.keystore.HtmlElementStore.AREA_ELEMENT;
import static org.springframework.beans.factory.xml.BeanDefinitionParserDelegate.MAP_ELEMENT;

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
    private final ElementHandler<Element> selectionWithMarkerHandler = new ElementHandlerImpl();

    public ElementHandler<Element> getSelectionWithMarkerHandler() {
        return selectionWithMarkerHandler;
    }

    /**
     * The elements not identified by the markers
     */
    private final ElementHandler<Element> selectionWithoutMarkerHandler = new ElementHandlerImpl();
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
        sortMarkerElements(selectionWithMarkerHandler, selectionWithoutMarkerHandler);
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
     */
    protected void sortMarkerElements(ElementHandler<Element> selectionWithMarkerHandler,
                                      ElementHandler<Element> selectionWithoutMarkerHandler) {
        if ((CollectionUtils.isEmpty(markerList) && CollectionUtils.isEmpty(inverseMarkerList))
                || selectionWithoutMarkerHandler.isEmpty()) {
            return;
        }
        Iterator<Element> iter = selectionWithoutMarkerHandler.get().iterator();
        Element el;
        while (iter.hasNext()) {
            el = iter.next();
            Collection<String> ids = new ArrayList<>(Collections.singletonList(el.id()));
            Collection<String> classNames = el.classNames();
            Collection<String> roles = new ArrayList<>(Collections.singletonList(el.attr(ROLE_ATTR)));
            if (el.tagName().equals(AREA_ELEMENT)) {
                Optional<Element> mapElement = el.parents().stream().filter(e -> e.tagName().equals(MAP_ELEMENT)).findFirst();
                if (mapElement.isPresent()) {
                    classNames.addAll(mapElement.get().classNames());
                    ids.add(mapElement.get().id());
                    roles.add(mapElement.get().attr(ROLE_ATTR));
                }
            }
            // if the element does contain an "id" OR a "class" attribute OR
            // a "role" attribute AND one the values belongs to the marker list, 
            // it is removed from the global selection and added to the 
            // marker element selection.
            if (CollectionUtils.isNotEmpty(ids) || CollectionUtils.isNotEmpty(classNames)
                    || CollectionUtils.isNotEmpty(roles)) {
                if (checkAttributeBelongsToMarkerList(ids, classNames, roles, markerList)) {
                    selectionWithMarkerHandler.add(el);
                    iter.remove();
                }
                // if the element belongs to the inverse marker list, it is
                // removed from the global collection
                if (checkAttributeBelongsToMarkerList(ids, classNames, roles, inverseMarkerList)) {
                    iter.remove();
                }
            }
        }
    }

    /**
     * @param ids
     * @param classNames
     * @param roles
     * @return whether one of the string given as argument belongs to a marker
     * list
     */
    private static boolean checkAttributeBelongsToMarkerList(
            Collection<String> ids,
            Collection<String> classNames,
            Collection<String> roles,
            Collection<String> markerList) {
        if (CollectionUtils.isEmpty(markerList)) {
            return false;
        }
        Collection<String> elAttr = new ArrayList<>();
        elAttr.addAll(ids);
        elAttr.addAll(classNames);
        elAttr.addAll(roles);
        return CollectionUtils.containsAny(markerList, elAttr);
    }

    /**
     *
     * Utility method to extract marker values, and apply a trim on values set
     * by the user
     *
     * @param parameters
     * @param markers
     */
    private void addMarkersToList(String parameters, Collection<String> markers) {
        for (String markerValue : parameters.split(";")) {
            markers.add(markerValue.trim());
        }
    }

}
