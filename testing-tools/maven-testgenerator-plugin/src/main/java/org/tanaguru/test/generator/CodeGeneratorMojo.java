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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;

/**
 * Mojo for the code generator plugin
 * 
 * @goal generate
 * @author Vivien Barousse
 */
public class CodeGeneratorMojo extends AbstractMojo {

    /**
     * The location where to generate sources
     *
     * @parameter default-value="${project.build.directory}/generated-test-sources"
     */
    private File generatedSourceLocation;

    /**
     * The generated folder name (the folder containing generated sources)
     *
     * @parameter default-value="testgenerator"
     */
    private String generatedSourceFolderName;

    /**
     * The code template
     * @parameter
     */
    private File template;

    /**
     * Data file
     * @parameter
     */
    private File dataFile;

    /**
     * Character used to delimit strings in data file
     *
     * @parameter default-value=":"
     */
    private String delimiter;

    /**
     * @parameter default-value="${project}"
     */
    private MavenProject mavenProject;

    public void execute() throws MojoExecutionException, MojoFailureException {
        File rootFolder = new File(generatedSourceLocation, generatedSourceFolderName);
        
        try {
            FileReader fin = new FileReader(dataFile);
            BufferedReader reader = new BufferedReader(fin);

            String readed = reader.readLine();
            String[] fieldNames = readed.split(delimiter);

            CodeGenerator generator = new CodeGenerator(template);
            while ((readed = reader.readLine()) != null) {
                String[] fields = readed.split(delimiter);
                if (fields.length != fieldNames.length) {
                    getLog().warn("Incorrect line, skipping: " + readed);
                    continue;
                }

                Map<String, String> templateData = new LinkedHashMap<String, String>();
                for (int i = 0; i < fields.length; i++) {
                    templateData.put(fieldNames[i], fields[i]);
                }
                generator.setData(templateData);

                generator.generateClass();

                File packageFolder = getPackageFolder(rootFolder, generator.getGeneratedClassPackage());
                File classFile = new File(packageFolder, generator.getGeneratedClassName() + ".java");

                FileWriter writer = new FileWriter(classFile);
                writer.write(generator.getGenerated());
                writer.close();
            }

            reader.close();
            fin.close();
        } catch (IOException ex) {
            throw new MojoExecutionException("Error reading data file", ex);
        } catch (GeneratorException ex) {
            throw new MojoExecutionException("Error generating class", ex);
        }

        mavenProject.addTestCompileSourceRoot(rootFolder.getAbsolutePath());
    }

    private File getPackageFolder(File rootFolder, String packageName) {
        File packageFolder = rootFolder;
        String[] packParts = packageName.split("\\.");
        for (String part : packParts) {
            packageFolder = new File(packageFolder, part);
        }
        if (!packageFolder.exists()) {
            packageFolder.mkdirs();
        }
        return packageFolder;
    }

}
