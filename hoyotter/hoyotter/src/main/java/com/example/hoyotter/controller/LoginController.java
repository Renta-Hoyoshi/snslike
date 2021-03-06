package com.example.hoyotter.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;

import com.example.hoyotter.model.dao.CommentRepository;
import com.example.hoyotter.model.dao.UserRepository;
import com.example.hoyotter.model.entity.Comment;
import com.example.hoyotter.model.entity.User;
import com.example.hoyotter.model.form.LoginForm;
import com.example.hoyotter.model.session.LoginSession;



@Controller
public class LoginController {
	
	@Autowired
	private LoginSession loginSession;
	
	@Autowired
	private UserRepository userRepos;
	
	@Autowired
	private CommentRepository commentRepos;
    
	@RequestMapping("/")
	public String index(Model model) {
		
		return "login";
	}
	
	
	
	@RequestMapping("/timeline")
	public String loginToTimeline(LoginForm loginForm, Model model) {
		//userReposの値とloginFormの値を比較して、合致するものを取ってくる
		List<User> users = userRepos.findByUserNameAndPassword(loginForm.getUserName(), loginForm.getPassword());
		
		
		//ログイン中以外のユーザー情報を取ってくる
		if( users != null && users.size() > 0 ) {
		List<User> u = userRepos.findAll();
		List<User> otherUsers = new ArrayList<>();
		for(User others : u ) {
			if(others == users.get(0)) {
				continue;
			}	
			otherUsers.add(others);
		}
		model.addAttribute("otherUsers", otherUsers);
		}
		
		//コメントを取ってくる
		List<Comment> comments = commentRepos.findAllByOrderByIdDesc(); 
		model.addAttribute("comments", comments);
		
		//ログイン情報をセッションへ
		if( users != null && users.size() > 0 ) {
		loginSession.setId(users.get(0).getId());
		loginSession.setProfile(users.get(0).getProfile());
		}
		loginSession.setUserName(loginForm.getUserName());
		loginSession.setPassword(loginForm.getPassword());
		model.addAttribute("loginSession", loginSession);
		
		
		
		return "timeline";
	}
}
