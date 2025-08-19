package com.example.blog.dto.commentDto;

import jakarta.validation.constraints.NotBlank;

public record CommentRequestDTO(
        @NotBlank String content) {

}
