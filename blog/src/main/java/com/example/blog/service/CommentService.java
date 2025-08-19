package com.example.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.blog.model.Comment;
import com.example.blog.repository.CommentRepository;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    // add a new comment
    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    // display all the comments
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }
}
