package com.example.blog.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.model.Comment;
import com.example.blog.service.CommentService;

@RestController
public class CommentController {
    final private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/api/allcomments")
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    @PostMapping("/api/comment")
    public Comment addComment(Comment comment) {
        return commentService.addComment(comment);
    }
}
