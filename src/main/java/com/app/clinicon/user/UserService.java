package com.app.clinicon.user;

import java.security.Principal;
import java.util.List;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.clinicon.requestsdto.LoginRequestDTO;
import com.app.clinicon.requestsdto.RegisterRequestDTO;
import com.app.clinicon.responsesdto.LoginResponseDTO;
import com.app.clinicon.role.RoleName;

public interface UserService {

    List<User> findAll();
    User findById(long id);
    User save(User user);

    User findByEmailAddress(String emailAddress);
    User findByRut(String rut);
    boolean checkEmailAvailability(String emailAddress);
    boolean checkRutAvailability(String rut);
    long countByRoleName(RoleName roleName);

    LoginResponseDTO authenticateUser(LoginRequestDTO loginRequestDTO, AuthenticationManager authenticationManager);
    User registerUser(RegisterRequestDTO registerRequestDTO);
    LoginResponseDTO refreshToken(AuthenticationManager authenticationManager);
    
    String webSignIn(Model model, RedirectAttributes redirectAttributes, Principal principal, String error);
    String dashboard(Model model);
    String index(Model model);
    String createUser(Model model, UserDTO userDTO);
    String findByIdUser(Model model, long id);
    String createUserRedirect(Model model);
    String updateUser(Model model, UserDTO userDTO);
    String deleteUser(Model model, long id);
    String activateAccount(String token);

    

}
