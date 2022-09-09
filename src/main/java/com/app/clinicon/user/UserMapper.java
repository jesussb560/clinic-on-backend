package com.app.clinicon.user;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper modelMapper;
    
    public UserDTO toDTO(User user){
        return modelMapper.map(user, UserDTO.class);
    }

    public User toEntity(UserDTO userDTO){
        return modelMapper.map(userDTO, User.class);
    }

}
