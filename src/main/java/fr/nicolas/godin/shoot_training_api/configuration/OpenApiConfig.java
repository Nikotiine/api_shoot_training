package fr.nicolas.godin.shoot_training_api.configuration;

import io.github.cdimascio.dotenv.Dotenv;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI myOpenAPI() {
        Dotenv dotenv = Dotenv.load();
        Contact contact = new Contact();
        contact.setEmail(dotenv.get("OPEN_API_EMAIL"));
        contact.setName(dotenv.get("OPEN_API_USERNAME"));
        contact.setUrl(dotenv.get("OPEN_API_WEBSITE_URL"));

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("API SHOOT TRAINING")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to manage tutorials.").termsOfService("https://nicolas-godin.fr")
                .license(mitLicense);

        return new OpenAPI().info(info);
    }
}
