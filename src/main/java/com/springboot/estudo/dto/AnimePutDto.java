package com.springboot.estudo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnimePutDto {
    private Long id;
    private String name;
}
