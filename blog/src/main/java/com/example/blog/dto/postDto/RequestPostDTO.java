package com.example.blog.dto.postDto;

import jakarta.validation.constraints.NotBlank;

public record RequestPostDTO(
        @NotBlank String title,
        @NotBlank String content) {
}
