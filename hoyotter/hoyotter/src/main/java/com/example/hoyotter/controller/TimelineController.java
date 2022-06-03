package com.example.hoyotter.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;


import com.example.hoyotter.model.dao.CommentRepository;
import com.example.hoyotter.model.dao.UserRepository;

import com.example.hoyotter.model.entity.Comment;
import com.example.hoyotter.model.entity.User;
import com.example.hoyotter.model.form.CommentForm;
import com.example.hoyotter.model.form.LoginForm;
import com.example.hoyotter.model.form.OthersForm;
import com.example.hoyotter.model.form.UploadForm;
import com.example.hoyotter.model.session.LoginSession;
import com.google.gson.Gson;

@Controller
public class TimelineController {
    
	@Autowired
	private LoginSession loginSession;
	
	@Autowired
	private UserRepository userRepos;
	
	@Autowired
	private CommentRepository commentRepos;
	
	@Autowired
	private Gson gson;
	
	@ResponseBody
	@RequestMapping("/addComment")
	public String addComment(@RequestBody CommentForm commentForm, LoginForm loginForm, Model model) {
		
		Date now = new Date();
		Comment comment = new Comment();
		comment.setComment(commentForm.getComment());
		comment.setUserName(commentForm.getUserName());
		comment.setPostedAt(now);
		commentRepos.saveAndFlush(comment);
		
		
		List<User> users = userRepos.findByUserNameAndPassword(loginSession.getUserName(), loginSession.getPassword());
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
		List<Comment> ajaxComs = commentRepos.findAllByOrderByIdDesc(); 
		/*return "forward:/timeline";*/
		return gson.toJson(ajaxComs);
	}
	
	@ResponseBody
	@PostMapping("/mypage")
	public String mypage() {
		
		List<User> userInfo = userRepos.findByUserNameAndPassword(loginSession.getUserName(), loginSession.getPassword());
		return gson.toJson(userInfo);
	}
	
	@ResponseBody
	@PostMapping("/mypage_under")
	public String mypageUnder() {
		
		List<Comment> coms = commentRepos.findByUserName(loginSession.getUserName());
		return gson.toJson(coms);
	}
	
	@ResponseBody
	@PostMapping("/otherspage")
	public String othersPage(@RequestBody OthersForm othersForm) {
		
		List<User> users = userRepos.findByUserName(othersForm.getOthersUserName());
		System.out.println(users);
		return gson.toJson(users);
	}
	
	@ResponseBody
	@PostMapping("/otherspage_under")
	public String othersPageUnder(@RequestBody OthersForm othersForm) {
		System.out.println(othersForm.getOthersUserName());
		List<Comment> coms = commentRepos.findByUserName(othersForm.getOthersUserName());
		System.out.println(coms);
		return gson.toJson(coms);
	}
	
	@ResponseBody
	@PostMapping("/edit")
	public String editMyPage() {
		
		List<User> userInfo = userRepos.findByUserNameAndPassword(loginSession.getUserName(), loginSession.getPassword());
		return gson.toJson(userInfo);
	}
	
	@ResponseBody
	@PostMapping("/upload")
	public String uploadMyPage(@RequestBody UploadForm uploadForm) {
		//プロフィール更新
		
		List<User> user = userRepos.findByUserNameAndPassword(loginSession.getUserName(), loginSession.getPassword());
		System.out.println(user);
		for(User u : user) {
			u.setId(uploadForm.getUserIdEdit());
			u.setUserName(uploadForm.getUserNameEdit());
			u.setPassword(uploadForm.getPasswordEdit());
			u.setProfile(uploadForm.getProfileEdit());
			userRepos.saveAndFlush(u);
			
		}
		
		loginSession.setId(uploadForm.getUserIdEdit());
		loginSession.setUserName(uploadForm.getUserNameEdit());
		loginSession.setPassword(uploadForm.getPasswordEdit());
		loginSession.setProfile(uploadForm.getProfileEdit());
		
		List<User> users = userRepos.findByUserNameAndPassword(uploadForm.getUserNameEdit(), uploadForm.getPasswordEdit());
		
		System.out.println(uploadForm.getProfileEdit());
		return gson.toJson(users);
	}
}
