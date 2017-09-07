package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
public class JspdemoApplication extends SpringBootServletInitializer {

    @RequestMapping("index")
    public String index() {
        System.out.println("index");
        return "index";
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(JspdemoApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(JspdemoApplication.class, args);
    }
}
