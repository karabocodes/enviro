package org.enviro.assessment.grad001.karabokhunou;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info =  @Info(
				title = "KaraboCodes",
				description = "an invest app",
				version = "v1.0",
				contact = @Contact(
						name = "karabo",
						email = "karaborkhuou@gmail.com",
						url = "https://github.com/karabocodes"
				),
				license = @License (
						name = "karabocodes",
						url = "https://github.com/karabocodes"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Working with Apis ",
				url = "https://github.com/karabocodes"
		)
)
public class KarabokhunouApplication {

	public static void main(String[] args) {
		SpringApplication.run(KarabokhunouApplication.class, args);
	}

}
