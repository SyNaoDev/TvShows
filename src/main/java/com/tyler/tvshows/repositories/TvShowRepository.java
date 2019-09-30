package com.tyler.tvshows.repositories;

import java.util.List;

import com.tyler.tvshows.models.TvShow;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TvShowRepository extends CrudRepository<TvShow, Long> {
	public List<TvShow> findAll();
	public List<TvShow> findByTitle(String title);
}