/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
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
package org.tanaguru.webapp.util;

/**
 *
 * @author jkowalczyk
 */
public final class TgolKeyStore {

    /**
     * Private constructor.  This class only handles static variables
     */
    private TgolKeyStore(){};
    
    /* url keys */
    public static final String DISPATCH_URL = "/dispatch";
    public static final String LOGIN_URL = "/login";
    public static final String LOGOUT_URL = "/logout";
    public static final String HOME_URL = "/home";
    public static final String ACCOUNT_SETTINGS_URL = "/account-settings";
    public static final String TEST_WEIGHT_URL = "/test-weight";
    public static final String CHANGE_PASSWORD_URL = "/change-password";
    public static final String CHANGE_PASSWORD_CONFIRMATION_URL = "/change-password-confirmation";
    public static final String FORGOTTEN_PASSWORD_URL = "/forgotten-password";
    public static final String FORGOTTEN_PASSWORD_CONFIRMATION_URL = "/forgotten-password-confirmation";
    public static final String SIGN_UP_URL = "/sign-up";
    public static final String SIGN_UP_CONFIRMATION_URL = "/sign-up-confirmation";
    public static final String ADMIN_URL = "/admin";
    public static final String CHANGE_PASSWORD_FROM_ADMIN_URL = "/admin/change-password";
    public static final String EDIT_USER_URL = "/admin/edit-user";
    public static final String MANAGE_CONTRACTS_URL = "/admin/manage-contracts";
    public static final String EDIT_CONTRACT_URL = "/admin/manage-contracts/edit-contract";
    public static final String ADD_USER_URL = "/admin/add-user";
    public static final String ADD_CONTRACT_FROM_CONTRACT_MNGT_URL = "/admin/manage-contracts/add-contract";
    public static final String ADD_CONTRACT_URL = "/admin/add-contract";
    public static final String DELETE_USER_URL = "/admin/delete-user";
    public static final String DELETE_USER_AUDITS_URL = "/admin/delete-user-audits";
    public static final String DELETE_USER_CONFIRMATION_URL = "/admin/confirm-user-deletion";
    public static final String DELETE_CONTRACT_URL = "/admin/manage-contracts/delete-contract";
    public static final String DELETE_CONTRACT_AUDITS_URL = "/admin/manage-contracts/delete-contract-audits";
    public static final String CONTRACT_URL = "/home/contract";
    public static final String AUDIT_SITE_SET_UP_URL = "/audit-site-set-up";
    public static final String AUDIT_SITE_SET_UP_CONTRACT_URL =
            CONTRACT_URL+AUDIT_SITE_SET_UP_URL;
    public static final String AUDIT_PAGE_SET_UP_URL = "/audit-page-set-up";
    public static final String AUDIT_PAGE_SET_UP_CONTRACT_URL =
            CONTRACT_URL+AUDIT_PAGE_SET_UP_URL;
    public static final String AUDIT_UPLOAD_SET_UP_URL = "/audit-upload-set-up";
    public static final String AUDIT_UPLOAD_SET_UP_CONTRACT_URL =
            CONTRACT_URL+AUDIT_UPLOAD_SET_UP_URL;
    public static final String AUDIT_SCENARIO_SET_UP_URL = "/audit-scenario-set-up";
    public static final String AUDIT_SCENARIO_SET_UP_CONTRACT_URL =
            CONTRACT_URL+AUDIT_SCENARIO_SET_UP_URL;
    public static final String MASS_AUDIT_SET_UP_URL = "/home/mass-audit-set-up";
    public static final String AUDIT_SCENARIO_MANAGEMENT_URL = "/scenario-management";
    public static final String AUDIT_SCENARIO_MANAGEMENT_CONTRACT_URL =
            CONTRACT_URL+AUDIT_SCENARIO_MANAGEMENT_URL;
    public static final String DOWNLOAD_SCENARIO_URL = "/download-scenario";
    public static final String DOWNLOAD_SCENARIO_URL_CONTRACT_URL =
            CONTRACT_URL+DOWNLOAD_SCENARIO_URL;
    public static final String DELETE_SCENARIO_URL = "/delete-scenario";
    public static final String DELETE_SCENARIO_URL_CONTRACT_URL =
            CONTRACT_URL+DELETE_SCENARIO_URL;
    public static final String LAUNCH_AUDIT_URL = "/launch-audit";
    public static final String LAUNCH_AUDIT_CONTRACT_URL =
            CONTRACT_URL+LAUNCH_AUDIT_URL;
    public static final String AUDIT_IN_PROGRESS_URL = "/audit-in-progress";
    public static final String AUDIT_IN_PROGRESS_CONTRACT_URL =
            CONTRACT_URL+AUDIT_IN_PROGRESS_URL;
    public static final String AUDIT_RESULT_URL = "/audit-result";
    public static final String AUDIT_RESULT_CONTRACT_URL =
            CONTRACT_URL+AUDIT_RESULT_URL;
    public static final String MANUAL_AUDIT_RESULT_URL = "/manual-audit-result";
    public static final String MANUAL_AUDIT_RESULT_CONTRACT_URL =
            CONTRACT_URL+MANUAL_AUDIT_RESULT_URL;
    public static final String MANUAL_AUDIT_PAGE_RESULT_URL = "/manual-audit-page-result";
    public static final String MANUAL_AUDIT_PAGE_RESULT_CONTRACT_URL =
            CONTRACT_URL+MANUAL_AUDIT_PAGE_RESULT_URL;
    public static final String PAGE_RESULT_URL = "/page-result";
    public static final String PAGE_RESULT_CONTRACT_URL =
            CONTRACT_URL+PAGE_RESULT_URL;
    public static final String UPDATE_MANUAL_RESULT_URL = "/update-manual-result";
    public static final String UPDATE_MANUAL_RESULT_CONTRACT_URL =
            CONTRACT_URL+UPDATE_MANUAL_RESULT_URL;
    public static final String SITE_RESULT_URL = "/site-result";
    public static final String SITE_RESULT_CONTRACT_URL =
            CONTRACT_URL+SITE_RESULT_URL;
    public static final String CRITERION_RESULT_URL = "/criterion-result";
    public static final String CRITERION_RESULT_CONTRACT_URL =
            CONTRACT_URL+CRITERION_RESULT_URL;
    public static final String TEST_RESULT_URL = "/test-result";
    public static final String TEST_RESULT_CONTRACT_URL =
            CONTRACT_URL+TEST_RESULT_URL;
    public static final String EXPORT_AUDIT_RESULT_URL = "/export-audit-result";
    public static final String EXPORT_AUDIT_RESULT_CONTRACT_URL =
            CONTRACT_URL+EXPORT_AUDIT_RESULT_URL;
    public static final String WEB_RESOURCE_SNAPSHOT_URL = "/wr-snapshot";
    public static final String WEB_RESOURCE_SNAPSHOT_CONTRACT_URL =
            CONTRACT_URL+WEB_RESOURCE_SNAPSHOT_URL;
    public static final String AUDIT_SYNTHESIS_URL = "/audit-synthesis";
    public static final String AUDIT_SYNTHESIS_CONTRACT_URL =
            CONTRACT_URL+AUDIT_SYNTHESIS_URL;
    public static final String FAILED_TEST_LIST_URL = "/failed-test-list";
    public static final String FAILED_TEST_LIST_CONTRACT_URL =
            CONTRACT_URL+FAILED_TEST_LIST_URL;
    public static final String PAGE_LIST_URL = "/page-list";
    public static final String PAGE_LIST_CONTRACT_URL =
            CONTRACT_URL+PAGE_LIST_URL;
    public static final String ACCESS_DENIED_URL = "/access-denied";
    public static final String ACCESS_DENIED_CONTRACT_URL =
            CONTRACT_URL+ACCESS_DENIED_URL;
    public static final String OUPS_URL = "/oups";
    public static final String OUPS_CONTRACT_URL = CONTRACT_URL+OUPS_URL;
    public static final String ERROR_ADAPTING_URL = "/error-adapting";
    public static final String ERROR_ADAPTING_CONTRACT_URL =
            CONTRACT_URL+ERROR_ADAPTING_URL;
    public static final String ERROR_LOADING_URL = "/error-loading";
    public static final String ERROR_LOADING_CONTRACT_URL =
            CONTRACT_URL+ERROR_LOADING_URL;
    public static final String QUOTA_EXCEEDED_URL = "/quota-exceeded";
    public static final String QUOTA_EXCEEDED_CONTRACT_URL =
            CONTRACT_URL+QUOTA_EXCEEDED_URL;
    public static final String QUOTA_BY_IP_EXCEEDED_URL = "/quota-by-ip-exceeded";
    public static final String QUOTA_BY_IP_EXCEEDED_CONTRACT_URL =
            CONTRACT_URL+QUOTA_BY_IP_EXCEEDED_URL;
    public static final String EXPORT_AUDIT_FORMAT_ERROR_URL = "/export-audit-format-error";
    public static final String EXPORT_AUDIT_FORMAT_ERROR_CONTRACT_URL =
            CONTRACT_URL+EXPORT_AUDIT_FORMAT_ERROR_URL;
    public static final String SOURCE_CODE_URL = "/source-code-page";
    public static final String SOURCE_CODE_CONTRACT_URL =
            CONTRACT_URL+SOURCE_CODE_URL;
    public static final String DEMO_URL = "/demo";

    /* view name keys */
    public static final String DISPATCH_VIEW_REDIRECT_NAME = "dispatch-redirect";
    public static final String LOGIN_VIEW_NAME = "login";
    public static final String HOME_VIEW_NAME = "home";
    public static final String ADMIN_VIEW_NAME = "admin";
    public static final String ADMIN_VIEW_REDIRECT_NAME = "admin-redirect";
    public static final String ADD_USER_VIEW_NAME = "add-user";
    public static final String EDIT_USER_VIEW_NAME = "edit-user";
    public static final String DELETE_USER_VIEW_NAME = "delete-user";
    public static final String DELETE_AUDITS_VIEW_NAME = "delete-audits";
    public static final String ADD_CONTRACT_VIEW_NAME = "add-contract";
    public static final String EDIT_CONTRACT_VIEW_NAME = "edit-contract";
    public static final String MANAGE_CONTRACTS_VIEW_NAME = "manage-contracts";
    public static final String MANAGE_CONTRACTS_VIEW_REDIRECT_NAME = "manage-contracts-redirect";
    public static final String DELETE_CONTRACT_VIEW_NAME = "delete-contract";
    public static final String HOME_VIEW_REDIRECT_NAME = "home-redirect";
    public static final String ACCOUNT_SETTINGS_VIEW_NAME = "account-settings";
    public static final String TEST_WEIGHT_VIEW_NAME = "test-weight";
    public static final String CHANGE_PASSWORD_VIEW_NAME = "change-password";
    public static final String CHANGE_PASSWORD_CONFIRMATION_VIEW_NAME = "change-password-confirmation";
    public static final String CHANGE_PASSWORD_CONFIRMATION_VIEW_REDIRECT_NAME = "change-password-confirmation-redirect";
    public static final String FORGOTTEN_PASSWORD_VIEW_NAME = "forgotten-password";
    public static final String FORGOTTEN_PASSWORD_CONFIRMATION_VIEW_NAME = "forgotten-password-confirmation";
    public static final String FORGOTTEN_PASSWORD_CONFIRMATION_VIEW_REDIRECT_NAME = "forgotten-password-confirmation-redirect";
    public static final String SIGN_UP_VIEW_NAME = "sign-up";
    public static final String SIGN_UP_CONFIRMATION_VIEW_NAME = "sign-up-confirmation";
    public static final String SIGN_UP_CONFIRMATION_VIEW_REDIRECT_NAME = "sign-up-confirmation-redirect";
    public static final String CONTRACT_VIEW_NAME = "contract";
    public static final String CONTRACT_VIEW_NAME_REDIRECT = "contract-redirect";
    public static final String AUDIT_SITE_SET_UP_VIEW_NAME = "audit-site-set-up";
    public static final String AUDIT_PAGE_SET_UP_VIEW_NAME = "audit-page-set-up";
    public static final String AUDIT_UPLOAD_SET_UP_VIEW_NAME = "audit-upload-set-up";
    public static final String AUDIT_SCENARIO_SET_UP_VIEW_NAME = "audit-scenario-set-up";
    public static final String MASS_AUDIT_SET_UP_VIEW_NAME = "mass-audit-set-up";
    public static final String SCENARIO_MANAGEMENT_VIEW_NAME = "scenario-management";
    public static final String AUDIT_PAGE_SET_UP_REDIRECT_NAME =
            "audit-page-set-up-redirect";
    public static final String AUDIT_IN_PROGRESS_VIEW_NAME = "audit-in-progress";
    public static final String GREEDY_AUDIT_VIEW_NAME = "greedy-audit";
    public static final String EXPORT_VIEW_NAME = "export-";
    public static final String RESULT_PAGE_VIEW_NAME = "result-page";
    public static final String RESULT_PAGE_VIEW_REDIRECT_NAME =
            "result-page-redirect";
    public static final String MANUAL_AUDIT_RESULT_VIEW_REDIRECT_NAME =
            "manual-audit-page-result-redirect";
    public static final String RESULT_PAGE_BY_CRITERION_VIEW_NAME = "result-page-by-criterion";
    public static final String RESULT_PAGE_BY_CRITERION_VIEW_REDIRECT_NAME =
            "result-page-by-criterion-redirect";
    public static final String CRITERION_RESULT_VIEW_NAME = "criterion-result-page";
    public static final String TEST_RESULT_VIEW_NAME = "test-result-page";
    public static final String RESULT_SITE_VIEW_NAME = "result-site";
    public static final String SYNTHESIS_SITE_VIEW_NAME = "synthesis-site";
    public static final String SYNTHESIS_SITE_VIEW_REDIRECT_NAME =
            "synthesis-site-redirect";
    public static final String FAILED_TEST_LIST_VIEW_NAME = "failed-test-list";
    public static final String FAILED_TEST_LIST_VIEW_REDIRECT_NAME =
            "failed-test-list-redirect";
    public static final String PAGE_LIST_VIEW_NAME = "page-list";
    public static final String PAGE_LIST_XXX_VIEW_NAME = "page-list-xxx";
    public static final String PAGE_LIST_XXX_VIEW_REDIRECT_NAME = "page-list-xxx-redirect";
    public static final String ACCESS_DENIED_VIEW_NAME = "access-denied";
    public static final String ACCESS_DENIED_VIEW_REDIRECT_NAME =
            "access-denied-redirect";
    public static final String OUPS_VIEW_NAME = "oups";
    public static final String OUPS_VIEW_REDIRECT_NAME = "oups-redirect";
    public static final String ADAPTING_ERROR_VIEW_NAME = "adapting-error";
    public static final String ADAPTING_ERROR_REDIRECT_NAME =
            "adapting-error-redirect";
    public static final String LOADING_ERROR_VIEW_NAME = "loading-error";
    public static final String LOADING_ERROR_REDIRECT_NAME =
            "loading-error-redirect";
    public static final String NOT_ALLOWED_VIEW_NAME = "not-allowed";
    public static final String QUOTA_EXCEEDED_VIEW_NAME = "quota-exceeded";
    public static final String QUOTA_BY_IP_EXCEEDED_VIEW_NAME = "quota-by-ip-exceeded";
    public static final String MAX_FILE_SIZE_ERROR_VIEW_NAME = "max-file-size-error";
    public static final String EXPORT_AUDIT_FORMAT_ERROR_VIEW_NAME =
            "export-audit-format-error";
    public static final String EXPORT_AUDIT_FORMAT_ERROR_VIEW_REDIRECT_NAME =
            "export-audit-format-error-redirect";
    public static final String SOURCE_CODE_PAGE_VIEW_NAME = "source-code-page";
    public static final String NO_DEMO_AVAILABLE_VIEW_NAME = "no-demo-available";

    /* data model keys */
    public static final String AUDIT_SET_UP_COMMAND_KEY = "auditSetUpCommand";
    public static final String ADD_SCENARIO_COMMAND_KEY = "addScenarioCommand";
    public static final String FORGOTTEN_PASSWORD_COMMAND_KEY = "forgottenPasswordCommand";
    public static final String CHANGE_PASSWORD_COMMAND_KEY = "changePasswordCommand";
    public static final String AUDIT_RESULT_SORT_COMMAND_KEY="auditResultSortCommand";
    public static final String CONTRACT_SORT_COMMAND_KEY="contractSortCommand";
    public static final String WEBRESOURCE_ID_KEY = "wr";
    public static final String IS_MANUAL_AUDIT_KEY = "isManualAudit";
    public static final String AUDIT_ID_KEY = "audit";
    public static final String DISPLAY_SCOPE_KEY = "lvl";
    public static final String EXPORT_FORMAT_KEY = "format";
    public static final String ACT_ID_KEY = "act";
    public static final String USER_ID_KEY = "user";
    public static final String USER_NAME_KEY = "userName";
    public static final String USER_ID_TO_DELETE_KEY = "userIdToDelete";
    public static final String USER_NAME_TO_DELETE_KEY = "userNameToDelete";
    public static final String CONTRACT_ID_TO_DELETE_KEY = "contractIdToDelete";
    public static final String CONTRACT_NAME_TO_DELETE_KEY = "contractNameToDelete";
    public static final String DELETED_USER_NAME_KEY = "deletedUserName";
    public static final String DELETED_USER_AUDITS_KEY = "deletedUserAudits";
    public static final String DELETED_CONTRACT_NAME_KEY = "deletedContractName";
    public static final String DELETED_CONTRACT_AUDITS_NAME_KEY = "deletedContractAuditsName";
    public static final String ADDED_USER_NAME_KEY = "addedUserName";
    public static final String ADDED_CONTRACT_NAME_KEY = "addedContractName";
    public static final String ADDED_CONTRACT_USERS_NAME_KEY = "addedContractToUserNames";
    public static final String UPDATED_USER_NAME_KEY = "updatedUserName";
    public static final String UPDATED_CONTRACT_NAME_KEY = "updatedContractName";
    public static final String CONTRACT_ID_KEY = "cr";
    public static final String REFERENTIAL_CD_KEY = "ref";
    public static final String CONTRACT_TO_DELETE_KEY = "contractToDelete";
    public static final String SCENARIO_ID_KEY = "sc";
    public static final String AUDIT_URL_KEY = "auditUrl";
    public static final String LEVEL_LIST_KEY = "levelList";
    public static final String RESULT_PAGE_NAME_KEY = "result-page-name";
    public static final String DATE_KEY = "date";
    public static final String AUDIT_DATE_KEY = "auditDate";
    public static final String URL_KEY = "url";
    public static final String STATISTICS_KEY = "statistics";
    public static final String DATA_REPORT_KEY = "dataReport";
    public static final String TOP5_SORTED_THEME_MAP = "top5SortedThemeMap";
    public static final String PAGE_LIST_KEY = "pageList";
    public static final String LOCALE_KEY = "locale";
    public static final String TEST_RESULT_LIST_KEY ="testResultMap";
    public static final String CRITERION_RESULT_LIST_KEY ="criterionResultMap";
    public static final String CRITERION_CODE_KEY ="crit";
    public static final String TEST_CODE_KEY ="testKey";
    public static final String CRITERION_LABEL_KEY ="criterionLabel";
    public static final String TEST_LABEL_KEY ="testLabel";
    public static final String TEST_LIST_KEY ="testList";
    public static final String MODIFIABLE_TEST_WEIGHT_REFS_KEY ="modifiableTestWeightRefs";
    
    public static final String SOURCE_CODE_KEY = "sourceCode";
    public static final String RESULT_ACTION_LIST_KEY = "resultActionList";
    public static final String HOME_ACTION_LIST_KEY = "homeActionList";
    public static final String DISPLAY_RESULT_TREND_KEY = "displayResultTrend";
    public static final String CONTRACT_WITH_MANUAL_AUDIT_KEY = "displayManualAuditOption";
    public static final String CONTRACT_LIST_KEY = "contractList";
    public static final String USER_LIST_KEY = "userList";
    public static final String RETURN_PAGE_URL_KEY="returnPageUrl";
    public static final String DETAILED_CONTRACT_INFO="detailedContractInfo";
    public static final String CONTRACT_ID_VALUE = "contractIdValue";
    public static final String RELATIVE_PATH_KEY = "relativePath";
    public static final String BREAD_CRUMB_KEY = "breadCrumb";
    public static final String TESTED_URL_KEY = "testedUrl";
    public static final String MY_PROJECT_KEY = "home.h1";
    public static final String ACCOUNT_SETTINGS_KEY = "account-settings.h1";
    public static final String FAILED_PAGE_INFO_BY_OCCURRENCE_SET_KEY =
            "failedPageInfoByOccurrenceSet";
    public static final String FAILED_PAGE_INFO_BY_TEST_SET_KEY =
            "failedPageInfoByTestSet";
    public static final String FAILED_TEST_INFO_BY_OCCURRENCE_SET_KEY =
            "failedTestInfoByPageSet";
    public static final String HAS_SITE_SCOPE_TEST_KEY =
            "hasSiteScopeTest";
    public static final String HAS_SSP_KEY = "hasSSP";
    public static final String STATUS_KEY = "status";
    public static final String TEST_KEY = "test";
    public static final String COMPLETED_KEY = "COMPLETED";
    public static final String ONGOING_KEY = "ONGOING";
    public static final String ERROR_LOADING_KEY = "ERROR_LOADING";
    public static final String ERROR_ADAPTING_KEY = "ERROR_ADAPTING";
    public static final String ERROR_UNKNOWN_KEY = "ERROR_UNKNOWN";
    public static final String AUDIT_NUMBER_KEY = "auditNumber";
    public static final String AUDITED_PAGES_COUNT_KEY = "auditedPagesCount";
    public static final String REDIRECTED_PAGES_COUNT_KEY = "redirectedPagesCount";
    public static final String ERROR_PAGES_COUNT_KEY = "errorPagesCount";
    public static final String REL_CANONICAL_PAGES_COUNT_KEY = "relCanonicalPagesCount";
    public static final String AUTHORIZED_PAGE_SIZE_KEY = "authorizedPageSize";
    public static final String AUTHORIZED_SORT_CRITERION_KEY = "authorizedSortCriterion";
    public static final String AUTHORIZED_SCOPE_FOR_PAGE_LIST = "authorizedScopeForPageList";
    public static final String SITE_SCOPE_TEST_DETAILS_KEY = "siteScopeTestDetails";
    public static final String CREATE_USER_COMMAND_KEY = "createUserCommand";
    public static final String CREATE_CONTRACT_COMMAND_KEY = "createContractCommand";
    public static final String CREATE_USER_ATTACK_COMMAND_KEY = "createUserAttack";
    public static final String CHANGE_TEST_WEIGHT_COMMAND_KEY ="changeTestWeightCommand";
    public static final String MANUAL_AUDIT_COMMAND_KEY ="manualAuditCommand";
    public static final String TEST_WEIGHT_SUCCESSFULLY_UPDATED_KEY="testWeightSuccessfullyUpdated";
    public static final String IS_CONTRACT_EXPIRED_KEY = "isContractExpired";
    public static final String PARAMETERS_MAP_KEY = "parametersMap";
    public static final String OPTIONS_MAP_KEY = "optionsMap";
    public static final String AUDIT_RESULT_SORT_FIELD_LIST_KEY="resultSortFormField";
    public static final String CONTRACT_SORT_FIELD_LIST_KEY="contractSortFormField";
    public static final String AUDIT_PARAM_SET_KEY = "auditParamSet";
    public static final String DEFAULT_PARAM_SET_KEY = "defaultParamSet";
    public static final String ACCOUNT_DATA_UPDATED_KEY = "accountDataUpdated";
    public static final String PASSWORD_MODIFIED_KEY = "passwordModified";
    public static final String CHANGE_PASSWORD_FROM_ADMIN_KEY = "changeUserPasswordFromAdmin";
    public static final String TOKEN_KEY = "token";
    public static final String EMAIL_KEY = "email";
    public static final String INVALID_CHANGE_PASSWORD_URL_KEY = "invalidChangePasswordUrl";
    public static final String EXPANDED_KEY = "expanded";
    public static final String EXPANDED_VALUE = "expanded";
    public static final String CONTRACT_NAME_KEY = "contractName";
    public static final String IS_PAGE_AUDIT_KEY = "isPageAudit";
    public static final String IS_GENERATED_HTML_KEY = "isGeneratedHtml";
    public static final String IS_USER_NOTIFIED_KEY = "isUserNotified";
    public static final String SCENARIO_LIST_KEY = "scenarioList";
    public static final String NEW_SCENARIO_NAME_KEY = "newScenarioName";
    public static final String DELETED_SCENARIO_NAME_KEY = "deletedScenarioName";
    public static final String SCENARIO_NAME_KEY = "scenarioName";
    public static final String SCENARIO_KEY = "scenario";
    
    public static final String REFERENTIAL_PARAM_KEY = "referential";
    public static final String LEVEL_PARAM_KEY = "LEVEL";
    
    public static final String CRITERION_DISPLAY_SCOPE_VALUE = "cr";
    public static final String TEST_DISPLAY_SCOPE_VALUE = "tst";

    /* functionality keys */
    public static final String MANUAL_AUDIT_FUNCTIONALITY_KEY = "MANUAL";
    
    /* role keys */
    public static final String ROLE_GUEST_KEY = "ROLE_GUEST";
    public static final String ROLE_USER_KEY = "ROLE_USER";
    public static final String ROLE_ADMIN_KEY = "ROLE_ADMIN";

    /* extension keys */
    public static final String HTML_EXTENSION_KEY = ".html";

    /* HTTP PREFIX*/
    public static final String HTTP_PREFIX = "http://";
    public static final String HTTPS_PREFIX = "https://";
    public static final String FILE_PREFIX = "file:";
    public static final char SLASH_CHAR = '/';

    public static final String ACT_QUOTA_EXCEEDED = "actQuotaExceeded";
    public static final String ACT_QUOTA_BY_IP_EXCEEDED = "actQuotaByIpExceeded";
    public static final String ACT_ALLOWED = "actAllowed";
    
    /* audit param keys */
    public static final String PROXY_HOST_PARAM_KEY = "PROXY_HOST";
    public static final String PROXY_PORT_PARAM_KEY = "PROXY_PORT";
    public static final String PROXY_USER_PARAM_KEY = "PROXY_USER";
    public static final String PROXY_PASSWORD_PARAM_KEY = "PROXY_PASSWORD";
    public static final String DEPTH_PARAM_KEY = "DEPTH";
    public static final String MAX_DOCUMENT_PARAM_KEY = "MAX_DOCUMENTS";
    public static final String MAX_DURATION_PARAM_KEY = "MAX_DURATION";
    public static final String EXCLUSION_URL_LIST_PARAM_KEY = "EXCLUSION_REGEXP";
    public static final String DEPTH_PAGE_PARAM_VALUE = "0";
    public static final String PROXY_HOST_CONF_KEY = "proxyHost";
    public static final String PROXY_PORT_CONF_KEY = "proxyPort";
    public static final String PROXY_USER_CONF_KEY = "proxyUser";
    public static final String PROXY_PASSWORD_CONF_KEY = "proxyPassword";
    public static final String PROXY_EXCLUSION_URL_CONF_KEY = "proxyExclusionUrl";
    public static final String EMAIL_SENT_TO_USER_EXCLUSION_CONF_KEY = "emailSentToUserExclusionList";
    
    /* url param keys */
    public static final String CRITERION_RESULT_PAGE_KEY = "criterion-result";
    public static final String REFERER_HEADER_KEY = "referer";
    
    /* email creator keys */
    public static final String EMAIL_FROM_KEY="emailFrom";
    public static final String EMAIL_TO_KEY="emailTo";
    public static final String EMAIL_SUBJECT_KEY="emailSubject";
    public static final String EMAIL_CONTENT_KEY="emailContent";
    public static final String EMAIL_CONTENT_URL_KEY="#urlToTest";
    public static final String EMAIL_CONTENT_EMAIL_KEY="#email";
    public static final String EMAIL_CONTENT_FIRST_NAME_KEY="#firstName";
    public static final String EMAIL_CONTENT_LAST_NAME_KEY="#lastName";
    public static final String EMAIL_CONTENT_PHONE_NUMBER_KEY="#phoneNumber";
    
    /* scenario meta data keys */
    public static final String CONTENT_TYPE="text/plain";
    public static final String JSON_EXTENSION=".json";
    public static final String CONTENT_DISPOSITION="Content-Disposition";
    public static final String ATTACHMENT="attachment; filename=";
    
    /* forgotten password email creator keys */
    public static final String FORGOTTEN_PASSWD_EMAIL_FROM_KEY = "forgotten-password.emailFrom";
    public static final String FORGOTTEN_PASSWD_EMAIL_SUBJECT_KEY = "forgotten-password.emailSubject";
    public static final String FORGOTTEN_PASSWD_EMAIL_CONTENT_KEY = "forgotten-password.emailContent";
    public static final String FORGOTTEN_PASSWD_BUNDLE_NAME = "i18n/forgotten-password-page-I18N";
    public static final String FORGOTTEN_PASSWD_CHANGE_PASSWORD_URL_KEY = "changePasswordUrl";

    public static final String AUTHENTICATED_KEY = "authenticated";
    
    public static final String ROLE_ADMIN_NAME_KEY = "ROLE_ADMIN";
}