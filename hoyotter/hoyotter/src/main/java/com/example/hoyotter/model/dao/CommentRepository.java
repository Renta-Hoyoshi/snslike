package com.example.hoyotter.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hoyotter.model.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
    List<Comment> findAllByOrderByIdDesc();
    List<Comment> findByUserName(String userName);
}
