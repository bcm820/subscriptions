package com.project.belt.controllers;

import java.security.Principal;
import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.belt.models.Subscription;
import com.project.belt.models.User;
import com.project.belt.services.SubService;
import com.project.belt.services.UserService;
import com.project.belt.validators.UserValidator;

@Controller
public class Main {
	
	private UserService us;
	private SubService ss;
	private UserValidator uv;
	public Main (UserService us, SubService ss, UserValidator uv) {
		this.us = us;
		this.ss = ss;
		this.uv = uv;
	}
	
	// Login & Registration //
	
	@RequestMapping("/")
	public String landing(Model m,
		@Valid @ModelAttribute("user") User u, Principal p,
		@RequestParam(value="error", required=false) String e,
		@RequestParam(value="logout", required=false) String l) {
		
		if (p != null) { return "redirect:/selection"; } // if logged in, go to selection
		if (e != null) { m.addAttribute("error", "Invalid credentials."); }
		if (l != null) { m.addAttribute("logout", "Thanks for visiting!"); }
		return "landing";
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("user") User u,
		BindingResult r, Model m, RedirectAttributes f) {
		
		uv.validate(u, r);
		if (r.hasErrors()) { m.addAttribute("errors", "!"); return "landing"; }
		if (us.getByLevel(2).isEmpty()) { us.createAdmin(u); }
		else { us.createUser(u); } f.addFlashAttribute("thanks", "Thanks for signing up!");
		return "redirect:/";
	}

	// ADMIN //

	@RequestMapping("/admin") // show customers, show subs, create sub form
	public String admin(Principal p, Model m, @ModelAttribute("subscription") Subscription s) {
		m.addAttribute("customers", us.getByLevel(1));
		m.addAttribute("subs", ss.getAll());
		return "admin";
	}

	@PostMapping("/admin/create")
	public String create(@Valid @ModelAttribute("subscription") Subscription s,
		BindingResult r, Model m, RedirectAttributes f) {

		if (r.hasErrors()) { m.addAttribute("errors", "!"); return "admin"; } // add msg on page
		else { ss.create(s); }
		return "redirect:/admin";
	}

	@RequestMapping("/admin/sub{id}/activate")
	public String activate(@PathVariable("id") Long s) {
		ss.activate(ss.get(s));
		return "redirect:/admin";
	}
	
	@RequestMapping("/admin/sub{id}/deactivate")
	public String deactivate(@PathVariable("id") Long s) {
		ss.deactivate(ss.get(s));
		return "redirect:/admin";
	}
	
	@RequestMapping("/admin/sub{id}/delete")
	public String delete(@PathVariable("id") Long s) {
		ss.delete(ss.get(s));
		return "redirect:/admin";
	}
	
	// CUSTOMERS //

	@RequestMapping("/profile")
	public String profile(Principal p, Model m) {
		User user = us.getByUsername(p.getName());
		if (user.getLevel() == 2) { return "redirect:/admin"; }
		if (!user.isSubscribed()) { return "redirect:/selection"; }
		else { m.addAttribute("user", user); return "profile"; }
	}
	
	@RequestMapping("/selection") // add sub to user via sub.id in selection form
	public String dash(Principal p, Model m) {
		User user = us.getByUsername(p.getName());
		m.addAttribute("user", user);
		m.addAttribute("subs", ss.getActive(true)); // list active subs
		
		// Get length of month
		int length = LocalDate.now().lengthOfMonth();
		m.addAttribute("last", length);
		
		return "dash";
	}
	
	@PostMapping("/selection/subscribe")
	public String sub(RedirectAttributes f, Principal p,
		@RequestParam("day") String day,
		@RequestParam("sub") Long s) {	
		
		User user = us.getByUsername(p.getName());
		us.subscribe(user, ss.get(s), Integer.parseInt(day));
		
		return "redirect:/profile";
	}
	
}
