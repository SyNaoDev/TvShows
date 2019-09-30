package com.tyler.tvshows.services;

import java.util.List;
import java.util.Optional;

import com.tyler.tvshows.models.TvShow;
import com.tyler.tvshows.repositories.TvShowRepository;

import org.springframework.stereotype.Service;

@Service
public class TvShowService {
	private final TvShowRepository repository;
	public TvShowService(TvShowRepository repository) {
		this.repository = repository;
	}
	public List<TvShow> findAll() {
		List<TvShow> shows = repository.findAll();
		for (TvShow show : shows) {
			show.calcAverageRating();
		}
		return shows;
	}
	public TvShow findById(Long id) {
		Optional<TvShow> optional = repository.findById(id);
		if (optional.isPresent()) {
			TvShow show = optional.get();
			show.sortRatings();
			return show;
		}
		return null;
	}
	public boolean titleAvailable(String title) {
		List<TvShow> shows = repository.findByTitle(title);
		return shows.size() == 0;
	}
	public TvShow save(TvShow show) {
		return repository.save(show);
	}
	public TvShow create(TvShow show) {
		return repository.save(show);
	}
	public void delete(TvShow show) {
		repository.delete(show);
	}
}