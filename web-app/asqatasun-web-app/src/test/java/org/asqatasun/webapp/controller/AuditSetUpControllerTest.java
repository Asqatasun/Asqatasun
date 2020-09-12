/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2019  Asqatasun.org
 *
 * This file is part of Asqatasun.
 *
 * Asqatasun is free software: you can redistribute it and/or modify
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
package org.asqatasun.webapp.controller;

import org.asqatasun.entity.option.OptionImpl;
import org.asqatasun.entity.parameterization.Parameter;
import org.asqatasun.entity.parameterization.ParameterElement;
import org.asqatasun.entity.parameterization.ParameterElementImpl;
import org.asqatasun.entity.parameterization.ParameterImpl;
import org.asqatasun.entity.service.parameterization.ParameterDataService;
import org.asqatasun.entity.service.parameterization.ParameterElementDataService;
import org.asqatasun.webapp.config.TestConfiguration;
import org.asqatasun.webapp.dto.factory.DetailedContractInfoFactory;
import org.asqatasun.entity.contract.Contract;
import org.asqatasun.entity.contract.ScopeEnum;
import org.asqatasun.entity.functionality.Functionality;
import org.asqatasun.entity.option.Option;
import org.asqatasun.entity.option.OptionElementImpl;
import org.asqatasun.entity.referential.Referential;
import org.asqatasun.entity.service.contract.ActDataService;
import org.asqatasun.entity.service.contract.ContractDataService;
import org.asqatasun.entity.service.user.UserDataService;
import org.asqatasun.entity.user.User;
import org.asqatasun.webapp.exception.ForbiddenPageException;
import org.asqatasun.webapp.security.userdetails.TgolUserDetails;
import org.asqatasun.webapp.ui.form.parameterization.builder.AuditSetUpFormFieldBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.util.*;

import static org.asqatasun.webapp.util.TgolKeyStore.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 *
 * @author jkowalczyk
 */
@SpringBootTest
@ContextConfiguration(classes = TestConfiguration.class)
@ActiveProfiles({"integration"})
public class AuditSetUpControllerTest  {

    @Autowired
    private AuditSetUpController instance;

    @MockBean
    DetailedContractInfoFactory contractInfoFactory;
    @MockBean
    Authentication mockAuthentication;
    @MockBean
    ActDataService mockActDataService;
    @MockBean
    User mockUser;
    @MockBean
    UserDataService mockUserDataService;
    @MockBean
    Contract mockContract;
    @MockBean
    ContractDataService mockContractDataService;
    @MockBean
    ParameterElementDataService mockParameterElementDataService;
    @MockBean
    ParameterDataService mockParameterDataService;
    @MockBean
    @Qualifier(value = "pageOptionFormFieldBuilderMap")
    Map <String, List <AuditSetUpFormFieldBuilder>> pageOptionFormFieldBuilderMap;
    @MockBean
    @Qualifier(value = "siteOptionFormFieldBuilderMap")
    Map <String, List <AuditSetUpFormFieldBuilder>> siteOptionFormFieldBuilderMap;
    @MockBean
    @Qualifier(value = "uploadOptionFormFieldBuilderMap")
    Map <String, List <AuditSetUpFormFieldBuilder>> uploadOptionFormFieldBuilderMap;

    @BeforeEach
    protected void setUp() {
        setUpMockUser();
        setUpMockAuthenticationContext();
        setUpMockContractDataService();
        setUpMockParameterDataService();
    }

    protected void verifyMocks() {
        verifyMockUser();
        verifyMockContractDataService();
    }

    @Test
    public void testDisplayPageAuditPageSetUp() {
        Model model = new ExtendedModelMap();

        // test
        String returnedView = instance.displayPageAuditSetUp("1", model);

        // assertions
        assertEquals(AUDIT_PAGE_SET_UP_VIEW_NAME, returnedView);
        assertEquals("http://www.test1.com", model.asMap().get(URL_KEY));
        assertEquals(false, model.asMap().get(DEFAULT_PARAM_SET_KEY));

        verifyMocks();
    }

    @Test
    public void testDisplayPageAuditSiteSetUp() {
        Model model = new ExtendedModelMap();

        // test
        String returnedView = instance.displaySiteAuditSetUp("1", model);

        // assertions
        assertEquals(AUDIT_SITE_SET_UP_VIEW_NAME, returnedView);
        assertEquals("http://www.test1.com", model.asMap().get(URL_KEY));
        assertEquals(true, model.asMap().get(DEFAULT_PARAM_SET_KEY));

        verifyMocks();
    }

    @Test
    public void testDisplayPageAuditUploadSetUp() {
        Model model = new ExtendedModelMap();

        // test
        String returnedView = instance.displayUploadAuditSetUp("1", model);

        // assertions
        assertEquals(AUDIT_UPLOAD_SET_UP_VIEW_NAME, returnedView);
        assertEquals("http://www.test1.com", model.asMap().get(URL_KEY));
        assertEquals(false, model.asMap().get(DEFAULT_PARAM_SET_KEY));

        verifyMocks();
    }

    @Test
    public void testDisplayPageAuditPageSetUpWithWrongContractId() {
        // the contract Id cannot be converted as a Long. An exception is caught
        assertThrows(ForbiddenPageException.class, () -> {
            instance.displayPageAuditSetUp("Not a number", new ExtendedModelMap());
        });
    }

    @Test
    public void testDisplayPageAuditUploadSetUpWithWrongContractId() {
        // the contract Id cannot be converted as a Long. An exception is caught
        assertThrows(ForbiddenPageException.class, () -> {
            instance.displayUploadAuditSetUp("Not a number", new ExtendedModelMap());
        });
    }

    @Test
    public void testDisplayPageAuditSiteSetUpWithWrongContractId() {
        // the contract Id cannot be converted as a Long. An exception is caught
        assertThrows(ForbiddenPageException.class, () -> {
            instance.displaySiteAuditSetUp("Not a number", new ExtendedModelMap());
        });
    }

    @Test
    public void testDisplayPageAuditPageSetUpWithUnauthorisedFunctionality() {
        // the functionality associated with the contract is not allowed
        // regarding the viewFunctionalityBindingMap. An exception is caught
        assertThrows(ForbiddenPageException.class, () -> {
            instance.displayPageAuditSetUp("2", new ExtendedModelMap());
        });
    }

    @Test
    public void testDisplayPageAuditSiteSetUpWithUnauthorisedFunctionality() {
        // the functionality associated with the contract is not allowed
        // regarding the viewFunctionalityBindingMap. An exception is caught
        assertThrows(ForbiddenPageException.class, () -> {
            instance.displaySiteAuditSetUp("2", new ExtendedModelMap());
        });
    }

    @Test
    public void testDisplayPageAuditUploadSetUpWithUnauthorisedFunctionality() {
        // the functionality associated with the contract is not allowed
        // regarding the viewFunctionalityBindingMap. An exception is caught
        assertThrows(ForbiddenPageException.class, () -> {
            instance.displayUploadAuditSetUp("2", new ExtendedModelMap());
        });
    }

    private void setUpMockAuthenticationContext(){
        // initialise the context with the user identified by the email
        // "test1@test.com" seen as authenticated

        TgolUserDetails tud = new TgolUserDetails(
            "test1@test.com", "", true, false, true, true, Collections.emptyList(), mockUser);

        SecurityContextImpl securityContextImpl = new SecurityContextImpl();
        securityContextImpl.setAuthentication(mockAuthentication);
        SecurityContextHolder.setContext(securityContextImpl);
        when(mockAuthentication.getName()).thenReturn("test1@test.com");
        when(mockAuthentication.getPrincipal()).thenReturn(tud);
    }

    private void setUpMockUser() {
        when(mockUser.getId()).thenReturn(1L);
        when(mockUser.getEmail1()).thenReturn("test1@test.com");
        when(mockUser.getName()).thenReturn("");
        when(mockUser.getFirstName()).thenReturn("");
    }

    private void verifyMockUser() {
        verify(mockUser, atLeastOnce()).getId();
        verify(mockUser, atLeastOnce()).getFirstName();
        verify(mockUser, atLeastOnce()).getName();
        verify(mockUser, atLeastOnce()).getEmail1();
    }

    /**
     *
     */
    private void setUpMockContractDataService() {
        when(mockContract.getUser()).thenReturn(mockUser);
        when(mockContract.getId()).thenReturn(Long.valueOf(1));
        when(mockContract.getLabel()).thenReturn("Contract1");
        when(mockContract.getFunctionalitySet()).thenReturn(getFunctionalitySet());
        when(mockContract.getReferentialSet()).thenReturn(getReferentialSet());
        when(mockContract.getOptionElementSet()).thenReturn(getOptionElementSet());

        when(mockContractDataService.read((long) 1)).thenReturn(mockContract);
        when(mockContractDataService.getUrlFromContractOption(mockContract)).thenReturn("http://www.test1.com");
    }

    private void verifyMockContractDataService() {
        verify(mockContract, atLeastOnce()).getUser();
        verify(mockContract, atLeastOnce()).getId();
        verify(mockContract, atLeastOnce()).getLabel();
        verify(mockContract, atLeastOnce()).getFunctionalitySet();
        verify(mockContract, atLeastOnce()).getReferentialSet();
        verify(mockContract, atLeastOnce()).getOptionElementSet();

        verify(mockContractDataService, atLeastOnce()).read(anyLong());
        verify(mockContractDataService, atLeastOnce()).getUrlFromContractOption(mockContract);
    }

    private Set<Functionality> getFunctionalitySet() {
        Functionality domain = new Functionality();
        domain.setCode("DOMAIN");
        Functionality page = new Functionality();
        page.setCode("PAGES");
        Functionality upload = new Functionality();
        upload.setCode("UPLOAD");
        return new HashSet<Functionality>(){{ add(domain); add(page); add(upload);}};
    }

    private Set<Referential> getReferentialSet() {
        Referential ref = new Referential();
        ref.setCode("");
        return Collections.singleton(ref);
    }

    private Set<OptionElementImpl> getOptionElementSet() {
        OptionElementImpl oe = new OptionElementImpl();
        OptionImpl option = new OptionImpl();
        option.setCode("");
        oe.setOption(option);
        return Collections.singleton(oe);
    }

    private void setUpMockParameterDataService() {
        ParameterElement mockParameterElementTextualFormField =
            new ParameterElementImpl(){{setParameterElementCode("TEXTUAL_FORMFIELD");}};
        ParameterElement mockParameterElementLevel =
            new ParameterElementImpl(){{setParameterElementCode("LEVEL");}};
        when(mockParameterElementDataService.getParameterElement("TEXTUAL_FORMFIELD")).thenReturn(mockParameterElementTextualFormField);
        when(mockParameterElementDataService.getParameterElement("LEVEL")).thenReturn(mockParameterElementLevel);

        Parameter param1 = new ParameterImpl(){{setValue("PARAMETER1");}};
        Parameter param2 = new ParameterImpl(){{setValue("PARAMETER2");}};
        Set<Parameter> paramSet = new HashSet<Parameter>() {{
            add(param1);
            add(param2);
        }};

        when(mockParameterDataService.getDefaultParameterSet()).thenReturn(paramSet);
        when(mockParameterDataService.getDefaultParameter(mockParameterElementLevel)).thenReturn(param1);
        when(mockParameterDataService.getDefaultParameter(mockParameterElementTextualFormField)).thenReturn(param2);
        when(mockParameterDataService.getDefaultLevelParameter()).thenReturn(param1);
        when(mockParameterDataService.getLastParameterValueFromUser(
            1L,
                mockParameterElementLevel,
                ScopeEnum.DOMAIN)).thenReturn("PARAMETER1");
        when(mockParameterDataService.getLastParameterValueFromUser(
            2L,
                mockParameterElementLevel,
                ScopeEnum.DOMAIN)).thenReturn("PARAMETER1");
        when(mockParameterDataService.getLastParameterValueFromUser(
            1L,
                mockParameterElementTextualFormField,
                ScopeEnum.DOMAIN)).thenReturn("PARAMETER2");
        when(mockParameterDataService.getLastParameterValueFromUser(
            2L,
                mockParameterElementTextualFormField,
                ScopeEnum.DOMAIN)).thenReturn("PARAMETER2");

    }

}
