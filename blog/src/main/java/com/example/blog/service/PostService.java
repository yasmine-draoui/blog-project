package com.example.blog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.blog.dto.commentDto.CommentRequestDTO;
import com.example.blog.dto.commentDto.CommentResponseDTO;
import com.example.blog.model.Comment;
import com.example.blog.model.Post;
import com.example.blog.dto.postDto.RequestPostDTO;
import com.example.blog.dto.postDto.ResponsePostDTO;
import com.example.blog.repository.CommentRepository;
import com.example.blog.repository.PostRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PostService {
    private PostRepository postRepository;
    private CommentRepository commentRepository;

    public PostService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    /*
     * // create a new post
     * public Post createPost(Post post) {
     * return postRepository.save(post);
     * }
     */
    // with dto
    public ResponsePostDTO createPost(RequestPostDTO dto) {
        Post post = new Post();
        post.setTitle(dto.title());
        post.setContent(dto.content());
        post.setComments(new ArrayList<>());
        Post saved = postRepository.save(post);
        return mapToPostResponse(saved);
    }

    /*
     * // display all the posts
     * public List<Post> getAllPosts() {
     * return postRepository.findAll();
     * }
     */
    // with dto
    public List<ResponsePostDTO> getAllPosts() {
        return postRepository.findAll().stream().map(this::mapToPostResponse).toList();
    }

    /*------------------- Comment ------------------- */
    public CommentResponseDTO addComment(Long postId, CommentRequestDTO dto) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("Post not found"));
        Comment comment = new Comment();
        comment.setContent(dto.content());
        comment.setPost(post);
        Comment saved = commentRepository.save(comment);
        return new CommentResponseDTO(saved.getId(), saved.getContent());
    }

    public List<CommentResponseDTO> getCommentsByPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException(""));
        return post.getComments().stream().map(c -> new CommentResponseDTO(c.getId(), c.getContent())).toList();
    }

    private ResponsePostDTO mapToPostResponse(Post post) {
        List<CommentResponseDTO> commentDTOs = post.getComments().stream()
                .map(c -> new CommentResponseDTO(c.getId(), c.getContent())).toList();
        return new ResponsePostDTO(post.getId(), post.getContent(), post.getTitle(), commentDTOs);
    }
}
