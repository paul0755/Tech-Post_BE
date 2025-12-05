package com.ureka.techpost.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI techPostAPI(){
        Info info = new Info()
                .title("Tech Post API")
                .version("1.0")
                .description("미니 프로젝트 3조 API 명세서");

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }

}
