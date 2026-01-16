package com.ang.nav.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class AuditorConfig {

    @Bean
    public AuditorAware<String> auditorProvider(){
        return () -> Optional.of("ANGEL_NAVARRO");
    }

    @Bean
    public OpenAPI customOpenAPIO(){
        return new OpenAPI()
                .info(new Info()
                .title("API CLIENTES")
                .version("1.0")
                .description("Documentación del API CLIENTES"));
    }
}
