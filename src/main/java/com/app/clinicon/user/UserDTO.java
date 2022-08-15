package com.app.clinicon.user;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {

    private long id;

    private String rut;

    private String firstName;

    private String lastName;

    private String address;

    private String emailAddress;

    private String password;

    private int status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    
}
