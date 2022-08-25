package com.mastery.java.task.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ServletConfiguration extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{
                AppConfiguration.class,
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {  //tomCat needs a servlet

        return new Class<?>[]{
                AppConfiguration.class
        };
    }

    @Override
    protected String[] getServletMappings() { //мой сервлет будет перехватывать все запросы идущий на /"
        return new String[]{"/*"};
    }
}
