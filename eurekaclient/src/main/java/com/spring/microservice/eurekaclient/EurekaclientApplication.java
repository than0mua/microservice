package com.spring.microservice.eurekaclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@RefreshScope
public class EurekaclientApplication {
    //get text in config
    @Value("${welcome.message}")
    String welcomeText;
    public static void main(String[] args) {
        SpringApplication.run(EurekaclientApplication.class, args);
    }

    @RequestMapping(value = "/")

    public String home(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        String host = request.getServerName();
        // Spring Boot >= 2.0.0.M7
        String endpointBasePath = "/actuator";

        StringBuilder sb = new StringBuilder();

        sb.append("<h2>Sprig Boot Actuator</h2>");
        sb.append("<ul>");

        // http://localhost:8090/actuator
        String url = "http://" + host + ":9000" + contextPath + endpointBasePath;

        sb.append("<li><a href='" + url + "'>" + url + "</a></li>");

        sb.append("</ul>");

        return sb.toString();
        //return welcomeText;
    }
}
