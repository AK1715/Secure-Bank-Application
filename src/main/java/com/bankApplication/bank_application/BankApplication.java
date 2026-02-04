package com.bankApplication.bank_application;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Welcome to AK Bank App",
                description = "Backend Rest API's for AK Bank App",
                version = "v1.0",
                contact = @Contact(
                        name = "Akshay Vishwakarma",
                        email = "akshayvishwakarma770@gmail.com",
                        url = "https://github.com/AK1715/Secure-Bank-Application"
                ),
                license = @License(
                        name = "Akshay Vishwakarma",
                        url = "https://github.com/AK1715/Secure-Bank-Application"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "AK Bank App Documentation",
                url = "https://github.com/AK1715/Secure-Bank-Application"
        )
)
public class BankApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}

}
