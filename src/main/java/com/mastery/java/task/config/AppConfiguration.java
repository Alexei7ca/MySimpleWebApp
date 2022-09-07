package com.mastery.java.task.config;

//@Configuration
//@EnableWebMvc
//@PropertySource("classpath:/application.properties")
//@ComponentScan("com.mastery.java.task")
public class AppConfiguration {

//    class ResourceBundle - Resource bundles contain locale-specific objects.
//    When your program needs a locale-specific resource
//    your program can load it from the resource bundle that is appropriate for the current user's locale.
//    In this way, you can write program code that is largely independent of the user's locale isolating most,
//    if not all, of the locale-specific information in resource bundles.

//    @Bean
//    public MessageSource messageSource() {  //Strategy interface for resolving messages, with support for the parameterization and internationalization of such messages.
//        ResourceBundleMessageSource resource = new ResourceBundleMessageSource(); // implementation that accesses resource bundles using specified basenames
//        resource.setBasenames ("errorMessages");   // the name of the file where you have the messages
//        return resource;
//    }
//
//    @Bean(name="validator")
//            public LocalValidatorFactoryBean validator(MessageSource resource){  //This is the central class for javax.validation
//            LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
//            bean.setValidationMessageSource(resource);  //Specify a custom Spring MessageSource for resolving validation messages, instead of relying on JSR-303's default "ValidationMessages.properties" bundle in the classpath.
//            return bean;
//}
//    public Validator getValidator(LocalValidatorFactoryBean validator) {
//        return validator;
//    }
}

