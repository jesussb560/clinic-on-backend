package com.app.clinicon.requestsdto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class RegisterRequestDTO {

    private long id;

    @NotEmpty
    @Pattern(regexp="^\\d{1,2}\\d{3}\\d{3}[-][0-9kK]{1}$")
    private String rut;

    @NotEmpty
    @Size(max = 100)
    private String firstName;

    @NotEmpty
    @Size(max = 100)
    private String lastName;

    @Size(max = 100)
    private String address;

    @Email
    private String emailAddress;

    @NotEmpty
    private String password;

    @NotNull
    private int roleId;
    
}
