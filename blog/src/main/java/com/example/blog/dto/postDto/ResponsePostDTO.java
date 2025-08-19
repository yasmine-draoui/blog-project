package com.example.blog.dto.postDto;

import java.util.List;

import com.example.blog.dto.commentDto.CommentResponseDTO;

public record ResponsePostDTO(
        Long id,
        String title,
        String content,
        List<CommentResponseDTO> comments) {

}
