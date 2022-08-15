package com.app.clinicon.jwt;

import javax.persistence.EntityNotFoundException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.clinicon.user.User;
import com.app.clinicon.user.UserPrincipal;
import com.app.clinicon.user.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String rut) throws UsernameNotFoundException {

        User user = userService.findByRut(rut);

        if(user.getStatus() == 0) throw new EntityNotFoundException("User not found");

        return UserPrincipal.create(user);
        
    }

    public UserDetails loadUserById(Long id) {

    	User user = userService.findById(id);
        log.info("USER ES {}", user.getId());
        return UserPrincipal.create(user);
        
    }
    
}
