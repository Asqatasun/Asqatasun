/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2020  Asqatasun.org
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
package org.asqatasun.webapp.report.service;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import java.io.ByteArrayOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.asqatasun.webapp.dto.AuditStatistics;
import org.asqatasun.webapp.report.format.ExportFormat;
import org.asqatasun.webapp.report.layout.LayoutFactory;
import org.asqatasun.webapp.report.service.exception.NotSupportedExportFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service for processing DynamicJasper reports
 * 
 * @author jkowalczyk
 */
@Service
public class ExportService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExportService.class);
    private static final String REPORT_BASE_FILE_NAME="Report_";
    private static final String CONTENT_DISPOSITION = "Content-Disposition";
    private static final String INLINE_FILENAME = "inline; filename=";
    private static final Map<String, ExportFormat> EXPORT_FORMAT_MAP = new HashMap <String, ExportFormat>()
    {{
        put("ods", new ExportFormat("net.sf.jasperreports.engine.export.JRPdfExporter", ".ods", "application/vnd.oasis.opendocument.spreadsheet"));
        put("csv", new ExportFormat("net.sf.jasperreports.engine.export.JRPdfExporter", ".csv", "text/csv"));
        put("pdf", new ExportFormat("net.sf.jasperreports.engine.export.JRPdfExporter", ".pdf", "application/pdf"));
    }};

    private final LayoutFactory layoutFactory;

    public ExportService(LayoutFactory layoutFactory) {
        this.layoutFactory = layoutFactory;
    }

    /**
     * Processes the download for Excel format
     * @param response
     * @param resourceId
     * @param auditStatistics
     * @param dataSource
     * @param locale
     * @param format
     * @throws ColumnBuilderException
     * @throws ClassNotFoundException
     * @throws JRException
     * @throws NotSupportedExportFormatException 
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

        if (!EXPORT_FORMAT_MAP.containsKey(format)) {
            throw new NotSupportedExportFormatException(format);
        }
        ExportFormat exportFormat = EXPORT_FORMAT_MAP.get(format);

        DynamicReport dr = layoutFactory.buildReportLayout(locale, auditStatistics, format);
        // Retrieve our data source
        JRDataSource ds = new JRBeanCollectionDataSource(dataSource);

        // params is used for passing extra parameters
        JasperPrint jp =
                DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), ds);
        
        // Create our output byte stream
        // This is the stream where the data will be written
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        JRExporter exporter;
        try {
            exporter = (JRExporter) Class.forName(exportFormat.exporterClassName).newInstance();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
            exporter.exportReport();
            response.setHeader(CONTENT_DISPOSITION, INLINE_FILENAME
                + getFileName(resourceId,exportFormat.fileExtension));
            // Make sure to set the correct content type
            // Each format has its own content type
            response.setContentType(exportFormat.fileType);
            response.setContentLength(baos.size());
            // Write to reponse stream
            writeReportToResponseStream(response, baos);
        } catch (InstantiationException | IllegalAccessException ex) {
            LOGGER.warn(ex.getMessage());
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
