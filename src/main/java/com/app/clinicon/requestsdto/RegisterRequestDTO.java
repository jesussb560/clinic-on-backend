package com.app.clinicon.requestsdto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Convert;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.app.clinicon.converter.TrimConverter;
import com.app.clinicon.diseasesTreated.DiseaseTreated;
import com.app.clinicon.userspecialty.UserSpecialty;

import lombok.Data;

@Data
public class RegisterRequestDTO {

    private long id;

    @NotEmpty
    @Pattern(regexp="^\\d{1,2}\\d{3}\\d{3}[-][0-9kK]{1}$")
    @Convert(converter = TrimConverter.class)
    private String rut;

    @NotEmpty
    @Size(max = 100)
    @Convert(converter = TrimConverter.class)
    private String firstName;

    @NotEmpty
    @Size(max = 100)
    @Convert(converter = TrimConverter.class)
    private String lastName;

    @Size(max = 100)
    @Convert(converter = TrimConverter.class)
    private String address;

    @Email
    @Convert(converter = TrimConverter.class)
    private String emailAddress;

    @NotEmpty
    @Size(min = 6)
    @Convert(converter = TrimConverter.class)
    private String password;

    @NotNull
    private int roleId;

    private Set<UserSpecialty> userSpecialties = new HashSet<>();
    
    private Set<DiseaseTreated> diseaseTreateds = new HashSet<>();
    
}
