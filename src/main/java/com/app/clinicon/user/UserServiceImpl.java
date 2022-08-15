package com.app.clinicon.user;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.clinicon.activationtoken.ActivationToken;
import com.app.clinicon.activationtoken.ActivationTokenService;
import com.app.clinicon.configuration.UtilsConfig;
import com.app.clinicon.jwt.JwtProvider;
import com.app.clinicon.mail.TemplateMailSender;
import com.app.clinicon.requestsdto.LoginRequestDTO;
import com.app.clinicon.requestsdto.RegisterRequestDTO;
import com.app.clinicon.responsesdto.LoginResponseDTO;
import com.app.clinicon.role.Role;
import com.app.clinicon.role.RoleService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    private final RoleService roleService;
    private final ActivationTokenService activationTokenService;
    
    private final JwtProvider jwtProvider;
    private final TemplateMailSender templateMailSender;
    private final PasswordEncoder passwordEncoder;
    private final UtilsConfig utilsConfig;

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public User findById(long id) {
        return userDAO.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Override
    public User save(User user) {
        return userDAO.save(user);
    }

    @Override
    public User findByEmailAddress(String emailAddress) {
        return userDAO.findByEmailAddress(emailAddress);
    }

    @Override
    public User findByRut(String rut) {
        return userDAO.findByRut(rut);
    }

    @Override
    public LoginResponseDTO authenticateUser(LoginRequestDTO loginRequestDTO,AuthenticationManager authenticationManager) {
            
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getRut(), loginRequestDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new LoginResponseDTO(jwtProvider.generateToken(authentication));

    }

    @Override
    public boolean checkEmailAvailability(String emailAddress) {
        return userDAO.existsByEmailAddress(emailAddress);
    }

    @Override
    public boolean checkRutAvailability(String rut) {
        return userDAO.existsByRut(rut);
    }

    @Override
    public User registerUser(RegisterRequestDTO registerRequestDTO) {

        Set<Role> roles = new HashSet<>();
        roles.add(roleService.findById(registerRequestDTO.getRoleId()));

        User user = User.builder()
                .rut(registerRequestDTO.getRut().trim())
                .firstName(registerRequestDTO.getFirstName().trim())
                .lastName(registerRequestDTO.getLastName().trim())
                .address(registerRequestDTO.getAddress().trim())
                .emailAddress(registerRequestDTO.getEmailAddress().trim())
                .password(passwordEncoder.encode(registerRequestDTO.getPassword().trim()))
                .roles(roles)
                .build();

        user = userDAO.save(user);

        ActivationToken activationToken = ActivationToken.builder()
                .token(utilsConfig.generateSecureToken(20))
                .user(user)
                .build();

        activationToken = activationTokenService.save(activationToken);

        //templateMailSender.sendAccountActivationMail(user, activationToken);

        return user;

    }

    @Override
    public LoginResponseDTO refreshToken(AuthenticationManager authenticationManager) {
        return null;
    }

    @Override
    public String activateAccount(String token) {

        ActivationToken activationToken = activationTokenService.findByToken(token);

        if (activationToken.getStatus() == 0) {
            return "response/error";
        }

        User user = activationToken.getUser();
        user.setStatus(1);
        userDAO.save(user);

        activationTokenService.save(activationToken);

        return "user/account-activation-success";

    }

    @Override
    public String webSignIn(Model model, RedirectAttributes redirectAttributes, Principal principal, String error) {

        if (principal != null) {
            redirectAttributes.addFlashAttribute("info", "Ya tienes una sesión activa.");
            return "redirect:/web/user/dashboard";
        }

        if (error != null) {
            model.addAttribute("error", "Credenciales incorrectas.");
        }

        return "user/sign-in";

    }

}
