/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2011  Open-S Company
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
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tgol.report.service;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import org.opens.tgol.presentation.data.AuditStatistics;
import org.opens.tgol.report.format.ExportFormat;
import java.io.ByteArrayOutputStream;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.log4j.Logger;
import org.opens.tgol.report.layout.LayoutFactory;
import org.opens.tgol.report.service.exception.NotSupportedExportFormatException;

/**
 * Service for processing DynamicJasper reports
 * 
 * @author jkowalczyk
 */
public final class ExportService {

    private static final Logger LOGGER = Logger.getLogger(ExportService.class);
    private static final String REPORT_BASE_FILE_NAME="Report_";
    private static final String CONTENT_DISPOSITION = "Content-Disposition";
    private static final String INLINE_FILENAME = "inline; filename=";

    Map<String, ExportFormat> exportFormatMap;
    public Map<String, ExportFormat> getExporterMap() {
        return exportFormatMap;
    }

    public void setExportFormatMap(Map<String, ExportFormat> exportFormatMap) {
        this.exportFormatMap = exportFormatMap;
    }

    private static ExportService exportService;

    /**
     * Private constructor
     */
    private ExportService() {
    }

    public static synchronized ExportService getInstance() {
        if (exportService == null) {
            exportService = new ExportService();
        }
        return exportService;
    }

    /**
     * Processes the download for Excel format
     */
    @SuppressWarnings("unchecked")
    public void export (
            HttpServletResponse response,
            long resourceId,
            AuditStatistics auditStatistics,
            Collection<?> dataSource,
            Locale locale,
            String format)
            throws ColumnBuilderException, ClassNotFoundException, JRException, NotSupportedExportFormatException {

        if (!exportFormatMap.containsKey(format)) {
            throw new NotSupportedExportFormatException(format);
        }
        ExportFormat exportFormat = exportFormatMap.get(format);

        DynamicReport dr = LayoutFactory.getInstance().buildReportLayout(locale, auditStatistics);
        // Retrieve our data source
        JRDataSource ds = new JRBeanCollectionDataSource(dataSource);

        // params is used for passing extra parameters
        JasperPrint jp =
                DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), ds);
        
        // Create our output byte stream
        // This is the stream where the data will be written
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        JRExporter exporter = null;
        try {
            exporter = (JRExporter) Class.forName(exportFormat.getExporterClassName()).newInstance();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
            exporter.exportReport();
            response.setHeader(CONTENT_DISPOSITION, INLINE_FILENAME
                + getFileName(resourceId,exportFormat.getFileExtension()));
            // Make sure to set the correct content type
            // Each format has its own content type
            response.setContentType(exportFormat.getFileType());
            response.setContentLength(baos.size());
            // Write to reponse stream
            writeReportToResponseStream(response, baos);
        } catch (InstantiationException ex) {
            LOGGER.warn(ex);
        } catch (IllegalAccessException ex) {
            LOGGER.warn(ex);
        }

    }

    /**
     * Writes the report to the output stream
     */
    private void writeReportToResponseStream(HttpServletResponse response,
            ByteArrayOutputStream baos) {

        LOGGER.debug("Writing report to the stream");
        try {
            // Retrieve the output stream
            ServletOutputStream outputStream = response.getOutputStream();
            // Write to the output stream
            baos.writeTo(outputStream);
            // Flush the stream
            outputStream.flush();

        } catch (Exception e) {
            LOGGER.error("Unable to write report to the output stream");
        }
    }

    private String getFileName(long resourceId , String extension) {
        StringBuilder fileName = new StringBuilder();
        fileName.append(REPORT_BASE_FILE_NAME);
        fileName.append(resourceId);
        fileName.append(extension);
        return fileName.toString();
    }

}