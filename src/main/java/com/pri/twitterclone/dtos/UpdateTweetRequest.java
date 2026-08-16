package com.pri.twitterclone.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateTweetRequest {

    @NotBlank
    private String content;
}
