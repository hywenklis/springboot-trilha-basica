package com.springboot.estudo.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AnimePostDto {

    @NotEmpty(message = "The anime name cannot be empty")
    private String name;
}
