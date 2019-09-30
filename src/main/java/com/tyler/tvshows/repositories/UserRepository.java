package com.tyler.tvshows.repositories;

import java.util.List;

import com.tyler.tvshows.models.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	public List<User> findAll();
	public List<User> findByEmail(String email);
}