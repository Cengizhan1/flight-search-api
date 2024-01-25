package com.cengizhanyavuz.flightsearchapi.bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfigBean {

    @Bean
    public OpenAPI openAPIMethod(){
        return new OpenAPI()
                .info(
                        new Info()
                                .description("task tanımlama44")
                                .version("v1")
                                .contact(new Contact().email("cengizhany.cy@gmail.com").url("url"))
                                .title("title44")
                                .termsOfService("Software INC")
                                .license(new License().url("http://www.").name("name")));
    }
}