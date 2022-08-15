package com.app.clinicon.user;

import java.security.Principal;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.clinicon.requestsdto.LoginRequestDTO;
import com.app.clinicon.requestsdto.RegisterRequestDTO;
import com.app.clinicon.responsesdto.LoginResponseDTO;

public interface UserService {

    List<User> findAll();
    User findById(long id);
    User save(User user);

    User findByEmailAddress(String emailAddress);
    User findByRut(String rut);
    boolean checkEmailAvailability(String emailAddress);
    boolean checkRutAvailability(String rut);

    LoginResponseDTO authenticateUser(LoginRequestDTO loginRequestDTO, AuthenticationManager authenticationManager);
    User registerUser(RegisterRequestDTO registerRequestDTO);
    LoginResponseDTO refreshToken(AuthenticationManager authenticationManager);

    String activateAccount(String token);
    String webSignIn(Model model, RedirectAttributes redirectAttributes, Principal principal, String error);

}
