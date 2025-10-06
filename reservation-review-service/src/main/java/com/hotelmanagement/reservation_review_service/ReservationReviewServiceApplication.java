package com.hotelmanagement.reservation_review_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Reservation and Review microservice REST API Documentation",
				description = "Hotel Management Reservation and Review microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Krish Mahorkar",
						email = "krish.gangadhar-mahorkar@capgemini.com",
						url = "https://github.com/Tarun-Sheoran/Hotel-Management-System/tree/krish"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.krish.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description =  "Hotel Management Reservation and Review microservice REST API Documentation",
				url = "https://github.com/Tarun-Sheoran/Hotel-Management-System/tree/krish"
		)
)
public class ReservationReviewServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationReviewServiceApplication.class, args);
	}

}
