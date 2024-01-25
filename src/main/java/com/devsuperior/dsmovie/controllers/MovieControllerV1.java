package com.devsuperior.dsmovie.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsmovie.dto.MovieGenreDTO;
import com.devsuperior.dsmovie.services.MovieService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "v1/movies")
@Tag(name = "Movies", description = "Controller for Movie")
public class MovieControllerV1 {

	@Autowired
	private MovieService service;

	

	@GetMapping(produces = "application/json")
	public Page<MovieGenreDTO> findAll(@RequestParam(value = "title", defaultValue = "") String title, Pageable pageable) {
		return service.findAllMovieGenre(title, pageable);
	}
	
	@GetMapping(value = "/{id}")
	public MovieGenreDTO findById(@PathVariable Long id) {
		return service.findByIdMovieGenre(id);
	}

	
}

	