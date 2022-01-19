package com.springboot.estudo.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class AnimePostDto {

    @NotEmpty(message = "The anime name cannot be empty")
    private String name;
}
