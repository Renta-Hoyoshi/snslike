package com.example.hoyotter.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.hoyotter.model.dao.UserRepository;
import com.example.hoyotter.model.entity.User;
import com.example.hoyotter.model.form.RegisterForm;

@Controller
public class RegisterController {
    
	@Autowired
	private UserRepository userRepos;
	
	
	
	@RequestMapping("/register")
	public ModelAndView registerPage(ModelAndView mav) {
		System.out.println("OK");
		mav.setViewName("register");
		System.out.println("OK2");
		return mav;
	}
	
	@RequestMapping("/register_confirm")
	public String confirm(@RequestParam("userName") String userName,
			              @RequestParam("password") String password,
			              RegisterForm registerForm,
			              Model model) {
		
		
		model.addAttribute("userName",userName);
		model.addAttribute("password",password);
		return "register_confirm";
	}
	
	@RequestMapping("/register_done")
	public String addUser(RegisterForm registerForm, Model model) {
		
		User user = new User();
		user.setUserName(registerForm.getUserName());
		user.setPassword(registerForm.getPassword());
		userRepos.saveAndFlush(user);
		
		return "register_done";
	}
}
