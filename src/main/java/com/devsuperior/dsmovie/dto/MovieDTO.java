package com.devsuperior.dsmovie.dto;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import org.springframework.hateoas.RepresentationModel;

import com.devsuperior.dsmovie.entities.MovieEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MovieDTO extends RepresentationModel <MovieDTO>  {

	private static final DecimalFormat df = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));

	@Schema(description = " Database generation movie ID")
	private Long id;
	
	@NotBlank(message = "Campo requerido")
	@Size(min = 5, max = 80, message = "Tamanho deve ser entre 5 e 80 caracteres")
	@Schema(description = "Movie title")
	private String title;
	
	@Schema(description = "Movie score")
	private Double score;
	
	@Schema(description = "Movie integer")
	private Integer count;
	
	@Schema(description = "Movie image")
	private String image;

	public MovieDTO(Long id, String title, Double score, Integer count, String image) {
		this.id = id;
		this.title = title;
		this.score = Double.valueOf(df.format(score));
		this.count = count;
		this.image = image;
	}

	public MovieDTO(MovieEntity movie) {
		this(movie.getId(), movie.getTitle(), movie.getScore(), movie.getCount(), movie.getImage());
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Double getScore() {
		return score;
	}
	
	public Integer getCount() {
		return count;
	}

	public String getImage() {
		return image;
	}

	@Override
	public String toString() {
		return "MovieDTO [id=" + id + ", title=" + title + ", score=" + score + ", count=" + count + ", image=" + image
				+ "]";
	}
}
