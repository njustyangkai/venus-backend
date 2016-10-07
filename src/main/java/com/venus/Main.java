package com.venus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@ServletComponentScan
public class Main extends WebMvcConfigurerAdapter
{
    public static void main(String[] args)
    {
    	System.setProperty("spring.config.name", "venus");
        SpringApplication application = new SpringApplication(Main.class);
        application.run(args);
    }

    /*public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/**");
    }*/
}
