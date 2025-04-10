package com.hh.product_service;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;

@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		classes = ProductServiceApplication.class)
class ProductServiceApplicationTests {

	@ServiceConnection
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:8.0.6");

	@LocalServerPort
	private Integer port;
	@BeforeEach
	void startup() {
		RestAssured.baseURI="http://localhost";
		RestAssured.port= port;
	}
	static {
		mongoDBContainer.start();
	}

	@Test
	void shouldCreateProduct() {
		String requestBody="""
				{
				   "name": "Wireless Headphones",
				   "description": "Noise-cancelling over-ear Bluetooth headphones with 40-hour battery life.",
				   "price": 129.99
				 }
				
				""";
		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/api/product")
				.then()
				.statusCode(201)
				.body("id", Matchers.notNullValue())
				.body("name", Matchers.equalTo("Wireless Headphones"))
				.body("description", Matchers.equalTo("Noise-cancelling over-ear Bluetooth headphones with 40-hour battery life."))
				.body("price", Matchers.equalTo(129.99F));
	}

}
