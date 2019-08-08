/**
 * Copyright (c) 2019.
 * This program and the accompanying materials are made available
 * under my granted permission provided that this note is kept intact, unmodified and unchanged.
 * @ Author: Baraa Ali -  API and implementation.
 * All rights reserved.
*/

package co.grandcircus.MoviesData.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.grandcircus.MoviesData.entity.Movie;

public interface MovieDao extends JpaRepository<Movie, Long> {

	List<Movie> findByTitleContainsIgnoreCase(String title);

	List<Movie> findByCategoryContainsIgnoreCase(String category);

	@Query("SELECT DISTINCT category FROM Movie")
	Set<String> findAllCategories();

//	List<Movie> findByTitleContainsKeyword(String keyword);

}
