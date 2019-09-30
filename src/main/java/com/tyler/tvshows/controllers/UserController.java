package com.tyler.tvshows.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.tyler.tvshows.models.User;
import com.tyler.tvshows.services.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
	static private final String SID = "TVSHOWS_USER_ID";
	public static String getSID() {
		return SID;
	}
	private final UserService service;
	public UserController(UserService service) {
		this.service = service;
	}
	@RequestMapping("/")
	public String index(HttpSession session) {
		Object o = session.getAttribute(SID);
		if (o == null) {
			return "redirect:/login";
		}
		return "redirect:/shows";
	}
	@RequestMapping("/login")
	public String login() {
		return "user/login.jsp";
	}
	@PostMapping("/login")
	public String login(@RequestParam(value="email") String email, @RequestParam(value="password") String password, HttpSession session) {
		boolean authentic = service.authenticate(email, password);
		if (authentic) {
			List<User> users = service.findByEmail(email);
			User user = users.get(0);
			session.setAttribute(SID, user.getId());
			return "redirect:/shows"; 
		}
		return "redirect:/login";
	}
	@RequestMapping("/register")
	public String register(@ModelAttribute("user") User user) {
		return "user/register.jsp";
	}
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("user") User user, BindingResult result) {
		if (result.hasErrors()) {
			return "user/register.jsp";
		}
		String password = user.getPassword();
		String passwordConfirm = user.getPasswordConfirm();
		if (password.compareTo(passwordConfirm) != 0) {
			return "user/register.jsp";
		}
		String email = user.getEmail();
		if (service.emailInUse(email)) {
			return "user/register.jsp"; 
		}
		service.create(user);
		return "redirect:/login";
	}
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute(SID);
		return "redirect:/login";
	}
}