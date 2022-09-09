package com.app.clinicon.user;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;

/**
 * @author jesussb560
 */
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/web/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/sign-in")
    public String signIn(Model model, Principal principal, RedirectAttributes redirectAttributes,
            @RequestParam(value = "error", required = false) String error) {
        return userService.webSignIn(model, redirectAttributes, principal, error);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/dashboard")
    public String dashboard(Model model){
        return userService.dashboard(model);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/index")
    public String index(Model model){
        return userService.index(model);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/create")
    public String create(Model model){
        return userService.createUserRedirect(model);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/create")
    public String create(Model model, @Valid UserDTO userDTO, BindingResult result){
        
        if (result.hasErrors()) {
            model.addAttribute("userDTO", userDTO);
            return "user/create";
        }
        
        return userService.createUser(model, userDTO);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/{id}")
    public String findById(Model model, @PathVariable long id){
        return userService.findByIdUser(model, id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/update/{id}")
    public String update(Model model, @Valid UserDTO userDTO, BindingResult result){

        if (result.hasErrors()) {
            model.addAttribute("userDTO", userDTO);
            return "user/update";
        }

        return userService.updateUser(model, userDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/delete/{id}")
    public String delete(Model model, @PathVariable long id){
        return userService.deleteUser(model, id);
    }

    @GetMapping(value = "/account-activation/{token}")
    public String accountActivation(@PathVariable String token) {
        return userService.activateAccount(token);
    }

}
