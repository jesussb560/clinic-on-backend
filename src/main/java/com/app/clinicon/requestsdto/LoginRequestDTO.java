package com.app.clinicon.requestsdto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginRequestDTO {

    @NotBlank
    private String rut;

    @NotBlank
    private String password;
}
