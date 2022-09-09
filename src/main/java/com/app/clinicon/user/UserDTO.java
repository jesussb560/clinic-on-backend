package com.app.clinicon.user;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Convert;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.app.clinicon.city.City;
import com.app.clinicon.converter.TrimConverter;
import com.app.clinicon.diseasesTreated.DiseaseTreated;
import com.app.clinicon.gender.Gender;
import com.app.clinicon.role.Role;
import com.app.clinicon.specialty.Specialty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {

    private long id;

    
    @Convert(converter = TrimConverter.class)
    private String rut;

    @Size(min = 2, max = 50)
    @Convert(converter = TrimConverter.class)
    private String firstName;

    @Size(min = 2, max = 50)
    @Convert(converter = TrimConverter.class)
    private String lastName;

    @Size(min = 2, max = 100)
    @Convert(converter = TrimConverter.class)
    private String address;

    private String profileImage;
    private MultipartFile profileImageMultipartFile;
    
    @Size(max = 5000)
    @Convert(converter = TrimConverter.class)
    private String aboutMe;

    @Size(min = 12, max = 12)
    @Convert(converter = TrimConverter.class)
    private String collegiateNumber;

    @Email
    @Convert(converter = TrimConverter.class)
    private String emailAddress;

    @Convert(converter = TrimConverter.class)
    private String phoneNumber;

    @Size(min = 6)
    @Convert(converter = TrimConverter.class)
    private String password;

    private int status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    
    @NotNull
    private Gender gender;

    @NotNull
    private City city;
    
    @NotNull
	private Set<Role> roles = new HashSet<>();
    private int roleId;
    
    @NotNull
    private Set<Specialty> specialties = new HashSet<>();
    
    @NotNull
    private Set<DiseaseTreated> diseasesTreated = new HashSet<>();
    
}
