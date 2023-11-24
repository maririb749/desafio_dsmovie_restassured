package com.devsuperior.dsmovie.controllers;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.json.JSONException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.devsuperior.dsmovie.tests.TokenUtil;

import io.restassured.http.ContentType;

@SuppressWarnings("unused")
public class MovieControllerRA {
	
	private String adminUsername, adminPassword, clientusername, clientpassword;
	
	private Long existingMovieId, nonExistingMovieId;
	
	private String adminToken, clientToken;
	
	private String moviesTitle;
	
	private Map<String, Object> postMovieInstance;
	
	
	
	@BeforeEach
	 public void setUp() throws JSONException{
	 baseURI = "http://localhost:8080";
	 
	 adminUsername = "maria@gmail.com";
	 adminPassword = "123456";
	 clientusername = "alex@gmail.com";
	 clientpassword = "123456";
	 
	 existingMovieId = 11L;
	 nonExistingMovieId = 100L;
	 
	adminToken = TokenUtil.obtainAccessToken(adminUsername, adminPassword);
	clientToken = TokenUtil.obtainAccessToken(clientusername, adminPassword);
	 
	 moviesTitle = "Harry Potter e as Relíquias da Morte - Parte 1";
	 
	    postMovieInstance = new HashMap<>();
		postMovieInstance.put("title", "Me 123");
		postMovieInstance.put("count", 0);
		postMovieInstance.put("score", 0.0);
		postMovieInstance.put("image", "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/jBJWaqoSCiARWtfV0GlqHrcdidd.jpg");
		
	 
	 	 
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
	
	@Test
	public void insertShouldReturnUnprocessableEntityWhenAdminLoggedAndBlankTitle() throws JSONException {	
		postMovieInstance.put("title", "ab");
		
		JSONObject newMovie = new JSONObject(postMovieInstance);
		
		given()
			.header("Content-type", "application/json")
			.header("Authorization", "Bearer " + adminToken)
			.body(newMovie.toString())
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post("/movies")
		.then()
			.statusCode(422)
			.body("fieldName", nullValue())
			.body("errors.message[0]", equalTo( "Tamanho deve ser entre 5 e 80 caracteres"));
	}
	@Test
	public void insertShouldReturnForbiddenWhenClientLogged() throws Exception {
		
		JSONObject newMovie = new JSONObject(postMovieInstance);
		
		given()
			.header("Content-type", "application/json")
		    .header("Authorization", "Bearer " + clientToken)
		    .body(newMovie.toString())
		    .contentType(ContentType.JSON)
		    .accept(ContentType.JSON)
		.when()
		    .post("/movies")
		.then()
		    .statusCode(403);
	}
	
	
}
