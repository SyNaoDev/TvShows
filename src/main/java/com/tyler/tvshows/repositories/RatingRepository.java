package com.tyler.tvshows.repositories;

import java.util.List;

import com.tyler.tvshows.models.Rating;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends CrudRepository<Rating, Long> {
	public List<Rating> findAll();
}