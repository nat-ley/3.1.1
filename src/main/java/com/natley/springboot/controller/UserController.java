package com.natley.springboot.controller;

import com.natley.springboot.model.User;
import com.natley.springboot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/")
	public String users(Model model) {
		model.addAttribute("users_list", userService.getAllUsers());
		return "users";
	}

	@RequestMapping("/new")
	public String newUserForm(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "new_user";
	}

	@RequestMapping("/edit/{id}")
	public String editUserForm(@PathVariable long id, Model model) {
		User user = userService.readUser(id);
		model.addAttribute("user", user);
		return "edit_users";
	}

	@RequestMapping("/save")
	public String saveUser(@ModelAttribute("user") User user) {
		userService.save(user);
		return "redirect:/";
	}

	@RequestMapping("/delete/{id}")
	public String deleteUserForm(@PathVariable long id) {
		userService.deleteUser(id);
		return "redirect:/";
	}

}