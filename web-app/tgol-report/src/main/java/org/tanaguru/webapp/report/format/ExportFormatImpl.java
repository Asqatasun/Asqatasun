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
package org.tanaguru.webapp.report.format;

/**
 *
 * @author jkowalczyk
 */
public class ExportFormatImpl implements ExportFormat{

    private String exporterClassName;
    @Override
    public String getExporterClassName() {
        return exporterClassName;
    }

    @Override
    public void setExporterClassName(String exporterClassName) {
        this.exporterClassName = exporterClassName;
    }

    private String fileExtension;
    @Override
    public String getFileExtension() {
        return fileExtension;
    }

    @Override
    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    private String fileType;
    @Override
    public String getFileType() {
        return fileType;
    }

    @Override
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    /**
     * Default constructor
     */
    public ExportFormatImpl(){}

    /**
     * Constructor with arguments
     * 
     * @param exporterClassName
     * @param fileExtension
     * @param fileType
     */
    public ExportFormatImpl(String exporterClassName, String fileExtension, String fileType) {
        this.exporterClassName = exporterClassName;
        this.fileExtension = fileExtension;
        this.fileType = fileType;
    }

}