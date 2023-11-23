package com.devsuperior.dsmovie.controllers;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings("unused")
public class MovieControllerRA {
	
	private Long existingMovieId, nonExistingMovieId;
	private String moviesTitle;
	
	
	@BeforeEach
	 public void setUp(){
	 baseURI = "http://localhost:8080";
	 
	 existingMovieId = 11L;
	 nonExistingMovieId = 100L;
	 
	 moviesTitle = "Harry Potter e as Relíquias da Morte - Parte 1";
	 
	 	 
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
	@Test
	public void findAllShouldReturnPagedMoviesWhenMovieTitleParamIsNotEmpty() {	
		given()
		.get("/movies?title={moviesTitle}", moviesTitle)
	.then()
		.statusCode(200)
		.body("content.id[0]", is(19))
		.body("content.title[0]", equalTo("Harry Potter e as Relíquias da Morte - Parte 1"))
		.body("content.score[0]", is(0.0F))
		.body("content.count[0]", is(0))
		.body("content.image[0]", equalTo("https://www.themoviedb.org/t/p/w533_and_h300_bestv2/vcrgU0KaNj5mKUCIQSUdiQwTE9y.jpg"));
	}
	
	@Test
	public void findByIdShouldReturnMovieWhenIdExists() {	
		existingMovieId = 11L;
		
		given()
			.get("/movies/{id}", existingMovieId)
		.then()
			.statusCode(200)
			.body("id", is(11))
			.body("title", equalTo("Star Wars: A Guerra dos Clones"))
			.body("image", equalTo("https://www.themoviedb.org/t/p/w533_and_h300_bestv2/uK15I3sGd8AudO9z6J6vi0HH1UU.jpg"))
			.body("score", is(0.0F));
		
  }
	@Test
	public void findByIdShouldReturnNotFoundWhenIdDoesNotExist() {	
		nonExistingMovieId = 100L;
		
		given()
			.get("/movies/{id}", nonExistingMovieId)
		.then()
			.statusCode(404)
			.body("status", is(404))
			.body("error", equalTo("Recurso não encontrado"))
			.body("path", equalTo("/movies/100"));
   }
	
	
}
