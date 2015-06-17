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
package org.tanaguru.test.generator;

import java.io.File;
import java.io.StringWriter;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

/**
 *
 * @author Vivien Barousse
 */
public class CodeGenerator {

    private Template template;

    private Map<String, String> data;

    private Map<String, String> results;

    private VelocityContext context;

    private String generated;

    public CodeGenerator(File templateFile) throws GeneratorException {
        data = new LinkedHashMap<String, String>();
        results = new LinkedHashMap<String, String>();
        try {
            Properties props = new Properties();
            props.setProperty("resource.loader", "file");
            props.setProperty("file.resource.loader.description",
                    "Velocity File Resource Loader");
            props.setProperty("file.resource.loader.class",
                    "org.apache.velocity.runtime.resource.loader.FileResourceLoader");
            props.setProperty("file.resource.loader.path", "/");
            VelocityEngine engine = new VelocityEngine();
            engine.init(props);

            template = engine.getTemplate(templateFile.getAbsolutePath());

            context = new VelocityContext();
            context.put("model", data);
            context.put("class", results);
        } catch (Exception ex) {
            throw new GeneratorException("Unable to init Velocity engine", ex);
        }
    }

    public void generateClass() throws GeneratorException {
        try {
            results.clear();
            
            StringWriter writer = new StringWriter();
            template.merge(context, writer);
            generated = writer.toString();
        } catch (Exception ex) {
            throw new GeneratorException("Error while executing template", ex);
        }
    }

    public String getGenerated() {
        return generated;
    }

    public String getGeneratedClassName() {
        return results.get("name");
    }

    public String getGeneratedClassPackage() {
        return results.get("package");
    }

    public Map<String, String> getData() {
        return Collections.unmodifiableMap(data);
    }

    public void setData(Map<String, String> data) {
        this.data.clear();
        this.data.putAll(data);
    }

}
