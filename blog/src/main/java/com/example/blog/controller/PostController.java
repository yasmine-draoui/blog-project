package com.example.blog.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.dto.commentDto.CommentRequestDTO;
import com.example.blog.dto.commentDto.CommentResponseDTO;
import com.example.blog.dto.postDto.RequestPostDTO;
import com.example.blog.dto.postDto.ResponsePostDTO;

import com.example.blog.service.PostService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    /*
     * @PostMapping("/api/posts")
     * public Post createPost(@RequestBody Post post) {
     * if(post.getComments()!=null){
     * post.getComments().forEach(comment->comment.setPost(post));
     * }
     * return postService.createPost(post);
     * }
     */
    /*
     * @GetMapping("/api/all")
     * public List<Post> getAllPosts() {
     * return postService.getAllPosts();
     * }
     */
    @PostMapping("/add")
    public ResponsePostDTO createPost(@Valid @RequestBody RequestPostDTO dto) {
        return postService.createPost(dto);
    }

    @GetMapping("/all")
    public List<ResponsePostDTO> getAllPosts() {
        return postService.getAllPosts();
    }

    @PostMapping("/{postId}/comments")
    public CommentResponseDTO addComment(@PathVariable Long postId, @Valid @RequestBody CommentRequestDTO dto) {
        return postService.addComment(postId, dto);
    }

    @GetMapping("/{postId}/comments")
    public List<CommentResponseDTO> getComments(@PathVariable Long postId) {
        return postService.getCommentsByPost(postId);
    }
}
