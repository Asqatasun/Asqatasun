package org.asqatasun.web.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.*

@Configuration
@EnableSwagger2
class SwaggerConfig {

    /**
     * ##### Warning ####
     * Don't use paths and apis methods as they use Predicate guava interface.
     * Swagger uses guava in version 19 as selenium uses 25-jre version. To avoid runtime exception,
     * we avoid to use guava objects through swagger.
     */
    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .ignoredParameterTypes(AuthenticationPrincipal::class.java)
            .select()
            .build()
            .apiInfo(getApiInfo())
    }

    private fun getApiInfo(): ApiInfo =
        ApiInfo("Asqatasun Api Documentation", "Asqatasun Api Documentation", "1.0", "urn:tos",
            Contact("Asqatasun", "https://asqatasun.org/", "support@asqatasun.org"), "", "", ArrayList())

}

@Configuration
class WebConfig : WebMvcConfigurer {
    override fun addViewControllers(registry: ViewControllerRegistry) {
        registry.addRedirectViewController("/", "/swagger-ui.html")
    }
}
