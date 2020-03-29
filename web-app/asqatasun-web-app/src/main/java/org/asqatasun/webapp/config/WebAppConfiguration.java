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
package org.asqatasun.webapp.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;
import java.io.IOException;
import java.util.*;

@Configuration
@EnableWebMvc
public class WebAppConfiguration implements WebMvcConfigurer {

    /**
     * Redirect '/' to login page
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    /**
     * Load all i18n file from the classpath
     * @return
     */
    @Bean(name = "messageSource")
    public ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource() {
        ClassLoader cl = this.getClass( ).getClassLoader( );
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(cl);
        Resource[] resources = new Resource[0];

        // load dynamically all i18 files. They have to be in the classpath under an i18n folder.
        try {
            resources = resolver.getResources("classpath*:i18n/*I18N.properties");
        } catch (IOException e) {
            e.printStackTrace( );
        }
        List <String> baseNames = new ArrayList <>( );

        for (Resource resource : resources) {
            baseNames.add("classpath:i18n/" + StringUtils.remove(resource.getFilename( ), ".properties"));
        }

        ReloadableResourceBundleMessageSource bundleMessageSource =
            new ReloadableResourceBundleMessageSource( );
        bundleMessageSource.setDefaultEncoding("UTF-8");
        bundleMessageSource.setBasenames(baseNames.toArray(new String[baseNames.size( )]));
        return bundleMessageSource;
    }

    /**
     * Needed to expose assets
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
            .addResourceHandler("/public/**")
            .addResourceLocations("classpath:/public/");
    }

    @Bean
    public ViewResolver viewResolver(){
        ResourceBundleViewResolver resolver = new ResourceBundleViewResolver();
        resolver.setBasename("view");
        return resolver;
    }

    @Bean
    public CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSize(104857600L);
        return resolver;
    }

}
