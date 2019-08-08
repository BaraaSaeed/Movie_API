/**
 * Copyright (c) 2019.
 * This program and the accompanying materials are made available
 * under my granted permission provided that this note is kept intact, unmodified and unchanged.
 * @ Author: Baraa Ali -  API and implementation.
 * All rights reserved.
*/
/**
 * 
 */
package co.grandcircus.MoviesData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.grandcircus.MoviesData.dao.MovieDao;
import co.grandcircus.MoviesData.entity.Movie;

@RestController
public class MovieApiController {

	@Autowired
	private MovieDao dao;

	@GetMapping("/movies")
	public List<Movie> listMovies(@RequestParam(value = "category", required = false) String category,
			@RequestParam(value = "title", required = false) String title) {
		if (category != null && !category.isEmpty()) {
			return dao.findByCategoryContainsIgnoreCase(category);
		} else if (title != null && !title.isEmpty()) {
			List<Movie> movies = dao.findAll();
			List<Movie> similarMovies = new ArrayList<>();
			for (Movie movie : movies) {
				if (movie.getTitle().contains(title)) {
					similarMovies.add(movie);
				}
			}
			return similarMovies;

		} else {
			return dao.findAll();
		}
	}

	@GetMapping("/movies/random")
	public Movie getRandomMovie(@RequestParam(value = "category", required = false) String category) {

		if (category == null || category.isEmpty()) {
			Long leftLimit = 1L;
			Long rightLimit = 16L;
			Long id = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
			return dao.findById(id).get();
		} else {
			List<Movie> movieList = dao.findByCategoryContainsIgnoreCase(category);
			Long leftLimit = 0L;
			Long rightLimit = Long.valueOf(movieList.size());
			Long id = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
			Movie Randommovie = movieList.get(id.intValue());
			return Randommovie;
		}
	}

	@GetMapping("/movies/random-list") // do shuffle
	public List<Movie> randomMoviePicks(@RequestParam(value = "quantity", required = false) Integer quantity) {
		List<Movie> movies = new ArrayList<>();
		movies = dao.findAll();
		Collections.shuffle(movies);
		List<Movie> randomMovies = new ArrayList<>();
		for (int i = 0; i < quantity; i++) {
			randomMovies.add(movies.get(i));
		}
//		Long leftLimit = 1L;
//		Long rightLimit = 16L;
//		Long oldId = -1L;
//		Long id;
//		for (Integer i = 0; i < quantity; i++) {
//			do {
//				id = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
//			} while (id == oldId);
//			randomMovies.add(dao.findById(id).get());
//			oldId = id;
//		}
		return randomMovies;
	}

	@GetMapping("/movies/category")
	public Set<String> movieCategories() {
		Set<String> categories = dao.findAllCategories();
		// List<Movie> movies = dao.findAll();
		// List<String> categories = new ArrayList<>();
//		for (Movie movie : movies) {
//			categories.add(movie.getCategory());
//		}
		return categories;
	}

	@GetMapping("/movies/{id}")
	public Movie movieInfo(@PathVariable("id") Long id) {
		Movie movie = dao.findById(id).get();
		return movie;
	}

//	@GetMapping("/movies")
//	public List<Movie> movieInfo(@RequestParam(value = "title", required = false) String title) {
//		System.out.println("title" + title);
//		List<Movie> movies = dao.findAll();
//		List<Movie> similarMovies = new ArrayList<>();
//		for (Movie movie : movies) {
//			if (movie.getTitle().contains(title)) {
//				similarMovies.add(movie);
//			}
//		}
//
//		return similarMovies;
//	}
}
