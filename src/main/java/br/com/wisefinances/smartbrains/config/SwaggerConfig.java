package br.com.wisefinances.smartbrains.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    private static final String DESCRICAO = """
            Esta API foi projetada com o intuito de ajudar as pessoas a exercerem um controle mais eficaz sobre seus
            gastos financeiros. Com ela, os usuários podem acessar e interagir de forma prática e eficiente com seus
            dados financeiros, permitindo o monitoramento detalhado de suas despesas e a organização de suas finanças.
            Por meio dos endpoints disponíveis, a API oferece uma maneira ágil e segura de integrar e analisar
            informações, facilitando a tomada de decisões mais conscientes e o planejamento financeiro pessoal.
            """;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().components(new Components()
                        .addSecuritySchemes("Authorization",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .info(new Info()
                        .title("SmartBrainsAPI")
                        .description(DESCRICAO)
                        .contact(new Contact()
                                .name("Gustavo")
                                .email("gustavo.gcc05@hotmail.com"))
                        .license(new License()
                                .name("LICENSE")
                                .url("https://github.com/WiseFinances/SmartBrainsAPI/blob/main/LICENSE")));
    }
}