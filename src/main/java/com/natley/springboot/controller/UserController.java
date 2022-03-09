package com.natley.springboot.controller;

import com.natley.springboot.model.User;
import com.natley.springboot.service.RoleService;
import com.natley.springboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	private final RoleService roleService;

	@RequestMapping("/")
	public String index() {
		return "redirect:/login";
	}


	@RequestMapping("/admin")
	public String admin(Model model) {
		model.addAttribute("users_list", userService.getAllUsers());
		return "admin";
	}

	@RequestMapping("/new")
	public String newUserForm(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("allRoles", roleService.getAll());
		return "new_user";
	}

	@RequestMapping("/edit/{id}")
	public String editUserForm(@PathVariable long id, Model model) {
		User user = userService.readUser(id);
		model.addAttribute("user", user);
		model.addAttribute("allRoles", roleService.getAll());
		return "edit_users";
	}

	@RequestMapping("/save")
	public String saveUser(@ModelAttribute("user") User user) {
		userService.save(user);
		return "redirect:/admin";
	}

	@RequestMapping("/delete/{id}")
	public String deleteUserForm(@PathVariable long id) {
		userService.deleteUser(id);
		return "redirect:/admin";
	}
	@RequestMapping("/user")
	public String users(Principal principal, Model model) {
		model.addAttribute("user", userService.findByEmail(principal.getName()));
		return "user";
	}

}