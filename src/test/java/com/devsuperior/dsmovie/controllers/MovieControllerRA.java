package com.devsuperior.dsmovie.controllers;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings("unused")
public class MovieControllerRA {
	
	
	
	
	
	@BeforeEach
	 public void setUp(){
	 baseURI = "http://localhost:8080";
	 
	 
	 
	}
	@Test
	public void findAllShouldReturnOkWhenMovieNoArgumentsGiven() {
		
		given()
	.when()
		.get("/movies")
	.then()
		.statusCode(200)
		.body("numberOfElements", is(20));
		
  }
	
	
}
