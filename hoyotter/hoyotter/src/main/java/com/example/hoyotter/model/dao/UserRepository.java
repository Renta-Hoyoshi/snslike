package com.example.hoyotter.model.dao;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;


import com.example.hoyotter.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	List<User> findByUserNameAndPassword(String userName, String password);
	List<User> findByUserName(String userName);
	List<User> saveAndFlush(List<User> user);
	
}
