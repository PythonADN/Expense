package com.example.codeengine.expense;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
/**
 * Доступ к UI по ссылке
 * http://localhost:8080/swagger-ui.html
 */
public class SwaggerConfig {
    //TODO: Бины для автоматического создания документаци
    @Bean
    public Docket swaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.codeengine.expense"))
                .build()
                .apiInfo(apiDetails());
    }

    private ApiInfo apiDetails() {
        return new ApiInfo(
                "Тестовый проект",
                "Описание тестового проекта",
                "1.0",
                "Free to use",
                new springfox.documentation.service.Contact("ADN", "https://vk.com/dimaaverchenko", "1434-3541@mail.ru"),
                "Скачать проект",
                "https://github.com/PythonADN/Expense",
                Collections.emptyList()
        );
    }

    //TODO: Бины для загрузки документаци из yaml файла
//    @Primary
//    @Bean
//    public SwaggerResourcesProvider swaggerResourcesProvider() {
//        return () -> {
//            SwaggerResource wsResource = new SwaggerResource();
//            wsResource.setName("Documentation");
//            wsResource.setSwaggerVersion("2.0");
//            wsResource.setLocation("/swagger.yaml");
//
//            List<SwaggerResource> resources = new ArrayList<>();
//            resources.add(wsResource);
//            return resources;
//        };
//    }
}
