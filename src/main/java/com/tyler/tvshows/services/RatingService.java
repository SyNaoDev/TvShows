package com.tyler.tvshows.services;

import java.util.List;

import com.tyler.tvshows.models.Rating;
import com.tyler.tvshows.repositories.RatingRepository;

import org.springframework.stereotype.Service;

@Service
public class RatingService {
	private final RatingRepository repository;
	public RatingService(RatingRepository repository) {
		this.repository = repository;
	}
	public List<Rating> findAll() {
		return repository.findAll();
	}
	public Rating create(Rating rating) {
		return repository.save(rating);
	}
}