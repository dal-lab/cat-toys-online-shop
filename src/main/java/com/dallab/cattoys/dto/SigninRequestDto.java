package com.dallab.cattoys.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SigninRequestDto {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

}
