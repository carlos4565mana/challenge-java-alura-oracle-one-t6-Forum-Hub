package br.com.challenge_alura_one_t6.AluraForum.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Carlos",
                        email = "carloscal61@gmail.com"
                ),
                description = "OpenApi documentation for Challanger ForumHub",
                title = "OpenApi specification - AluraOnet6-ForumHub ",
                version = "v1",
                license = @License(
                        name = "Apache 2.0",
                        url = "https://springdoc.org"
                ),termsOfService = ""

        ),servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8080"
                )
},
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        }

)
@SecurityScheme(
        name="bearerAuth",
        description = "JWT authentication",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
