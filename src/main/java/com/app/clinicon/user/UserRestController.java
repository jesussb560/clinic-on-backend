package com.app.clinicon.user;


import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.clinicon.requestsdto.LoginRequestDTO;
import com.app.clinicon.requestsdto.RegisterRequestDTO;
import com.app.clinicon.responsesdto.LoginResponseDTO;

import lombok.RequiredArgsConstructor;

/**
 * @author jesussb560
 */
@RestController
@RequestMapping(value = "/api/user")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping(value = "/sign-in")
    public ResponseEntity<LoginResponseDTO> signIn(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
        return new ResponseEntity<LoginResponseDTO>( userService.authenticateUser(loginRequestDTO, authenticationManager) ,HttpStatus.OK);
    }

    @PostMapping(value = "/sign-up")
    public ResponseEntity<User> signUp (@RequestBody @Valid RegisterRequestDTO registerRequestDTO){
        return new ResponseEntity<User>( userService.registerUser(registerRequestDTO) ,HttpStatus.CREATED );
    }

    @GetMapping(value = "/find-by-id/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<User> findById (@PathVariable long id){
        return new ResponseEntity<User>(userService.findById(id) ,HttpStatus.OK);
    }

    @GetMapping(value = "/check-email-avalability/{emailAddress}")
    public ResponseEntity<Boolean> checkEmailAvailability (@PathVariable String emailAddress) {
        return new ResponseEntity<Boolean>(userService.checkEmailAvailability(emailAddress) ,HttpStatus.OK);
    }

    @GetMapping(value = "/check-rut-avalability/{rut}")
    public ResponseEntity<Boolean> checkRutAvailability (@PathVariable String rut) {
        return new ResponseEntity<Boolean>(userService.checkRutAvailability(rut) ,HttpStatus.OK);
    }

    @GetMapping(value = "/refresh-token")
    public ResponseEntity<?> refreshToken () {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
