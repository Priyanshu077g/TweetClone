package com.pri.twitterclone.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateTweetRequest {

    @NotBlank
    public String content;
    @NotNull
    private Long userId;
}
