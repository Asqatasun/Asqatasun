/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015 Tanaguru.org
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
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.webapp.validator;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MimeTypes;
import org.tanaguru.webapp.command.AuditSetUpCommand;
import org.tanaguru.webapp.entity.service.contract.ContractDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * @author jkowalczyk
 */
public class UploadAuditSetUpFormValidator extends AuditSetUpFormValidator {

    private static final String NO_FILE_UPLOADED_MSG_BUNDLE_KEY =
            "required.noFileUploaded";
    private static final String FILE_SIZE_EXCEEDED_MSG_BUNDLE_KEY =
            "required.fileSizeExceeded";
    private static final String NOT_HTML_MSG_BUNDLE_KEY =
            "required.notHtmlFileFound";
    private static final String ID_INPUT_FILE_PREFIX = "fileInputList";

    // Default = 2MB
    private long maxFileSize=2097152;
    public void setMaxFileSize(long maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

    @Autowired
    public UploadAuditSetUpFormValidator(ContractDataService contractDataService) {
        super(contractDataService);
    }

    public List<String> authorizedMimeType = new ArrayList<String>();
    public List<String> getAuthorizedMimeType() {
        return authorizedMimeType;
    }

    public void setAuthorizedMimeType(List<String> authorizedMimeType) {
        this.authorizedMimeType = authorizedMimeType;
    }

    @Override
    public void validate(Object target, Errors errors) {
        AuditSetUpCommand uploadAuditSetUpCommand = (AuditSetUpCommand)target;
        validateFiles(uploadAuditSetUpCommand, errors);
        super.validate(target, errors);
    }

    /**
     * Control whether the uploaded files are of HTML type and whether their
     * size is under the maxFileSize limit.
     *
     * @param uploadAuditSetUpCommand
     * @param errors
     */
    private void validateFiles(AuditSetUpCommand uploadAuditSetUpCommand, Errors errors) {
        boolean emptyFile = true;
        Metadata metadata = new Metadata();
        MimeTypes mimeTypes = TikaConfig.getDefaultConfig().getMimeRepository();
        String mime = null;

        for (int i=0;i<uploadAuditSetUpCommand.getFileInputList().length;i++ ) {
            try {
                CommonsMultipartFile cmf = uploadAuditSetUpCommand.getFileInputList()[i];
                if (cmf.getSize() > maxFileSize) {
                    Long maxFileSizeInMega = maxFileSize / 1000000;
                    String[] arg = {maxFileSizeInMega.toString()};
                    errors.rejectValue(ID_INPUT_FILE_PREFIX + "[" + i + "]", FILE_SIZE_EXCEEDED_MSG_BUNDLE_KEY, arg, "{0}");
                }
                if (cmf.getSize() > 0) {
                    emptyFile = false;
                    mime = mimeTypes.detect(new BufferedInputStream(cmf.getInputStream()), metadata).toString();
                    LOGGER.debug("mime  " + mime + "  " +cmf.getOriginalFilename());
                    if (!authorizedMimeType.contains(mime)) {
                        errors.rejectValue(ID_INPUT_FILE_PREFIX + "[" + i + "]", NOT_HTML_MSG_BUNDLE_KEY);
                    }
                }
            } catch (IOException ex) {
                LOGGER.warn(ex);
                errors.rejectValue(ID_INPUT_FILE_PREFIX + "[" + i + "]", NOT_HTML_MSG_BUNDLE_KEY);
            }
        }
        if(emptyFile) { // if no file is uploaded
            LOGGER.debug("emptyFiles");
            errors.rejectValue(GENERAL_ERROR_MSG_KEY,
                    NO_FILE_UPLOADED_MSG_BUNDLE_KEY);
        }
    }
    
    @Override
    public boolean supports(Class clazz) {
        return AuditSetUpCommand.class.isAssignableFrom(clazz);
    }

}