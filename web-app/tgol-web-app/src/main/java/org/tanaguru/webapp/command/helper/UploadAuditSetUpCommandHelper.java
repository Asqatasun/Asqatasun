/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
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
package org.tanaguru.webapp.command.helper;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.tanaguru.crawler.util.CrawlUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * @author jkowalczyk
 */
public final class UploadAuditSetUpCommandHelper  {

    /**
     * Local Map to deal with uploaded files with the same name
     */
    private static Map<String, Integer> fileNameCounterMap = 
            new HashMap<String, Integer>();
    
    /**
     * This method converts the uploaded files into a map where the key is the
     * file name and the value is the file content.
     */
    public synchronized  static Map<String, String> convertFilesToMap(CommonsMultipartFile[] fileInputList ) {
        Map<String, String> fileMap = new LinkedHashMap<String, String>();
        CommonsMultipartFile tmpMultiFile;
        String tmpCharset;
        fileNameCounterMap.clear();
        for (int i = 0; i < fileInputList.length; i++) {
            tmpMultiFile = fileInputList[i];
            try {
                if (tmpMultiFile != null && !tmpMultiFile.isEmpty() && tmpMultiFile.getInputStream() != null) {
                    tmpCharset = CrawlUtils.extractCharset(tmpMultiFile.getInputStream());
                    fileMap.put(
                            getFileName(tmpMultiFile.getOriginalFilename()),
                            tmpMultiFile.getFileItem().getString(tmpCharset));
                }
            } catch (IOException e) {}
        }
        return fileMap;
    }
    
    /**
     * This method formats the file name of the uploaded file (prefix by "/")
     * and suffix files with the same name with an index.
     *
     * @param originalFileName
     * @return
     */
    private static String getFileName(String originalFileName){
        StringBuilder fileName = new StringBuilder();
        fileName.append('/');
        if (fileNameCounterMap.containsKey(originalFileName)){
            fileNameCounterMap.put(originalFileName, Integer.valueOf(fileNameCounterMap.get(originalFileName)+1));
            fileName.append(originalFileName);
            fileName.append('_');
            fileName.append(fileNameCounterMap.get(originalFileName));
            return fileName.toString();
        } else {
            fileNameCounterMap.put(originalFileName, Integer.valueOf(1));
            fileName.append(originalFileName);
            return fileName.toString();
        }
    }
    
}