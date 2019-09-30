package com.tyler.tvshows.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.tyler.tvshows.models.Rating;
import com.tyler.tvshows.models.TvShow;
import com.tyler.tvshows.models.User;
import com.tyler.tvshows.services.RatingService;
import com.tyler.tvshows.services.TvShowService;
import com.tyler.tvshows.services.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TvShowController {
	private final UserService userService;
	private final TvShowService tvShowService;
	private final RatingService ratingService;
	public TvShowController(UserService userService, TvShowService tvShowService,  RatingService ratingService) {
		this.userService = userService;
		this.tvShowService = tvShowService;
		this.ratingService = ratingService;
	}
	@RequestMapping("/shows")
	public String shows(HttpSession session, Model model) {
		Object o = session.getAttribute(UserController.getSID());
		if(o == null) {
			return "redirect:/login";
		}
		Long id = (Long)o;
		User user = userService.findById(id);
		List<TvShow> shows = tvShowService.findAll();
		model.addAttribute("user", user);
		model.addAttribute("shows", shows);
		return "home/shows.jsp";
	}
	@RequestMapping("/shows/new")
	public String create(@ModelAttribute("show") TvShow show, HttpSession session) {
		Object o = session.getAttribute(UserController.getSID());
		if (o == null) {
			return "redirect:/login";
		}
		return "home/new.jsp";
	}
	@PostMapping("/shows/new")
	public String create(@Valid @ModelAttribute("show") TvShow show, BindingResult result) {
		if (result.hasErrors()) {
			return "home/new.jsp";
		}
		String title = show.getTitle();
		if (!tvShowService.titleAvailable(title)) {
			return "home/new.jsp";
		}
		tvShowService.create(show);
		return "redirect:/shows";
	}
	@PostMapping("/shows/{id}/update")
	public String update(@PathVariable("id") Long id, @RequestParam("title") String title, @RequestParam("network") String network, HttpSession session) {
		Object o = session.getAttribute(UserController.getSID());
		if (o == null) {
			return "redirect:/login";
		}
		if (!tvShowService.titleAvailable(title)) {
			String sid = Long.toString(id);
			return "redirect:/shows/" + sid + "/edit";
		}
		if (title.length() < 3 || network.length() < 3) {
			String sid = Long.toString(id);
			return "redirect:/shows/" + sid + "/edit";
		}
		TvShow show = tvShowService.findById(id);
		show.setTitle(title);
		show.setNetwork(network);
		tvShowService.save(show);
		return "redirect:/shows";
	}
	@RequestMapping("/shows/{id}")
	public String display(@PathVariable("id") Long id, HttpSession session, Model model) {
		Object o = session.getAttribute(UserController.getSID());
		if (o == null) {
			return "redirect:/login";
		}
		TvShow show = tvShowService.findById(id);
		model.addAttribute("show", show);
		return "home/display.jsp";
	}
	@PostMapping("/shows/{id}/delete")
	public String delete(@PathVariable("id") Long id, HttpSession session) {
		Object o = session.getAttribute(UserController.getSID());
		if (o == null) {
			return "redirect:/login";
		}
		TvShow show = tvShowService.findById(id);
		tvShowService.delete(show);
		return "redirect:/shows";
	}
	@RequestMapping("/shows/{id}/edit")
	public String edit(@PathVariable("id") Long id, HttpSession session, Model model) {
		Object o = session.getAttribute(UserController.getSID());
		if (o == null) {
			return "redirect:/login";
		}
		TvShow show = tvShowService.findById(id);
		model.addAttribute("show", show);
		return "home/edit.jsp";
	}
	@PostMapping("/shows/{id}/rate")
	public String rate(@PathVariable("id") Long id, @RequestParam("value") String strval, HttpSession session) {
		Object o = session.getAttribute(UserController.getSID());
		if (o == null) {
			return "redirect:/login";
		}
		double value = Double.parseDouble(strval);
		value = Math.max(1.0, Math.min(5.0, value));
		User user = userService.findById((Long)o);
		TvShow tvshow = tvShowService.findById(id);
		if (user.hasRatedShowBefore(tvshow)) {
			String sid = Long.toString(id);
			return "redirect:/shows/" + sid;
		}
		Rating rating = new Rating(value, user, tvshow);
		user.addRating(rating);
		tvshow.addRating(rating);
		rating = ratingService.create(rating);
		return "redirect:/shows";
	}
}