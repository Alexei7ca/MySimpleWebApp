package com.mastery.java.task.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public Docket configSwaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2)  //Docket is the provided class by swagger to take care of config details (details below)
                .select()
//                .apis(RequestHandlerSelectors.any()) //DEFAULT - make documentation for our entire API available through Swagger.
                .apis(RequestHandlerSelectors.basePackage("com.mastery.java.task")) // this makes documentation available for everything under the provided package
//                .paths(PathSelectors.any())  //DEFAULT .any - further defines for which paths in our APIs do we want to create documentation for.
                .paths(PathSelectors.regex(".employees.*"))  //all paths that include /employees/[+ something] with /employees included
//                .paths(PathSelectors.ant("/employees/*"))  //all paths that include /employees/[+ something]  Not including /employees

                .build().useDefaultResponseMessages(false);
    }
}

//    Docket is a builder which is intended to be the primary interface into the swagger-springmvc framework.
//    It provides sensible defaults and convenience methods for configuration.

//    After the Docket bean is defined, calling its select() method returns an instance of ApiSelectorBuilder,
//    which provides control over the endpoints exposed by Swagger.