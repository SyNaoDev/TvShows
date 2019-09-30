package com.tyler.tvshows.services;

import java.util.List;
import java.util.Optional;

import com.tyler.tvshows.models.User;
import com.tyler.tvshows.repositories.UserRepository;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	private final UserRepository repository;
	public UserService(UserRepository repository) {
		this.repository = repository;
	}
	public List<User> findAll() {
		return repository.findAll();
	}
	public List<User> findByEmail(String email) {
		return repository.findByEmail(email);
	}
	public User findById(Long id) {
		Optional<User> optional = repository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	public boolean emailInUse(String email) {
		List<User> users = repository.findByEmail(email);
		return users.size() > 0;
	}
	public boolean authenticate(String email, String password) {
		List<User> users = repository.findByEmail(email);
		if (users.isEmpty()) {
			return false;
		}
		User user = users.get(0);
		if (!BCrypt.checkpw(password, user.getPassword())) {
			return false;
		}
		return true;
	}
	public User create(User user) {
		String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashedPassword);
		return repository.save(user);
	}
}