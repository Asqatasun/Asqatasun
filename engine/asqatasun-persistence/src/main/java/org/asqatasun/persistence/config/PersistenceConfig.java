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
package org.asqatasun.persistence.config;

/**
 * Created by koj on 15/05/16.
 */

import org.apache.commons.lang3.StringUtils;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * Persistence configuration.
 *
 */
@Configuration
@EnableTransactionManagement
@ImportResource({"classpath*:aop.xml"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class PersistenceConfig extends PersistenceCommonConfig{

    @Value("${jdbc.user:asqatasunDatabaseUserLogin}")
    private String username;
    @Value("${jdbc.password:asqatasunDatabaseUserP4ssword}")
    private String password;
    @Value("${jdbc.url:jdbc:mysql://localhost:3306/asqatasun}")
    private String url;
    @Value("${jdbc.url_query_options:?useSSL=false&useUnicode=true&characterEncoding=UTF-8&characterSetResults=UTF-8}")
    private String url_query_options;
    @Value("${app.engine.persistence.useComboPool:false}")
    private Boolean useComboPool;
    @Value("#{'${app.engine.persistence.packagesToScan:org.asqatasun.entity}'.split(',')}")
    private String[] packagesToScan;

    @PostConstruct
    private void init() {
        if (StringUtils.contains(url,MYSQL_KEY)) url = url+url_query_options;
    }

    @Bean(name = "dataSource")
    DataSource dataSource() {
        if (useComboPool) {
            try {
                return setUpComboPooledDataSource(url, username, password);
            } catch (PropertyVetoException ex) {
                return setUpBasicDataSource(url, username, password);
            }
        }
        return setUpBasicDataSource(url, username, password);
    }

    @Bean(name="flyway", initMethod = "migrate")
    public Flyway dbInitialization() {
        return setUpFlyway(dataSource(), url);
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        return entityManagerFactory(
            dataSource(),
            url,
            packagesToScan);
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory().getObject());
    }

}
