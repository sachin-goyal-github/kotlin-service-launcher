package com.sg.launcher.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.util.Predicates
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SpringFoxConfig : WebMvcConfigurer {
    @Bean
    fun api(): Docket {
        // @formatter:off
        //Register the controllers to swagger
        //Also it is configuring the Swagger Docket
    return Docket(DocumentationType.SWAGGER_2).select() // .apis(RequestHandlerSelectors.any())
        .apis(Predicates.negate(RequestHandlerSelectors.basePackage("org.springframework.boot"))) // .paths(PathSelectors.any())
 // .paths(PathSelectors.ant("/swagger2-demo"))
        .build()
            // @formatter:on
    }

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        //enabling swagger-ui part for visual documentation
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/")
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/")
    }
}
