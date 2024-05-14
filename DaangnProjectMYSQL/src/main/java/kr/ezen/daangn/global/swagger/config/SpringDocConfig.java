package kr.ezen.daangn.global.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SpringDocConfig {

    @Bean
    OpenAPI springOpenAPI(){
        return new OpenAPI()
                .info(new Info().title("Daangn Clone API")
                        .description("API Document")
                        .version("v0.0.1"));
    }
    
}
