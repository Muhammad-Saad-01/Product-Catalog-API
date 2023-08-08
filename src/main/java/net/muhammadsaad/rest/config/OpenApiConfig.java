package net.muhammadsaad.rest.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    Contact contact = new Contact();

    License license = new License();

    Info info = new Info();

    @Bean
    public OpenAPI customOpenAPI() {
        contact.setName("Muhammad Saad");
        contact.setEmail("Muhammad-Saad-01@Outlook.com");
        contact.setUrl("https://Muhammad-saad.net");

        license.setName("Apache 2.0");
        license.setUrl("https://www.apache.org/licenses/LICENSE-2.0");

        info.setTitle("Product Catalog API");
        info.setVersion("1.0.0");
        info.setDescription("This is a web-based software for managing a product catalog, adding products, categories, brands");

        info.setContact(contact);
        info.setLicense(license);
        info.setSummary("E-Commerce API");

        return new OpenAPI().info(info);
    }


}
