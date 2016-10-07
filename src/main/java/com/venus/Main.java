package com.venus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.venus.frame.init.SystemInitializer;

@SpringBootApplication
@ServletComponentScan
public class Main extends WebMvcConfigurerAdapter
{
    public static void main(String[] args)
    {
        SpringApplication application = new SpringApplication(Main.class);
        application.addListeners(new SystemInitializer());
        application.run(args);
    }

    /*public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/**");
    }*/
}
