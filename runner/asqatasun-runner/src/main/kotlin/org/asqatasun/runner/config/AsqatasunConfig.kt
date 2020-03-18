package org.asqatasun.runner.config

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer
import org.springframework.core.io.ClassPathResource

/**
 * Created by meskoj on 14/05/16.
 */
@Configuration
open class AsqatasunConfig {

    @Bean
    open fun propertySourcesPlaceholderConfigurer(): PropertySourcesPlaceholderConfigurer {
        val propertySourcesPlaceholderConfigurer = PropertySourcesPlaceholderConfigurer()
        val yaml = YamlPropertiesFactoryBean()
        yaml.setResources(ClassPathResource("application.yml"))
        propertySourcesPlaceholderConfigurer.setProperties(yaml.getObject())
        return propertySourcesPlaceholderConfigurer
    }
}
