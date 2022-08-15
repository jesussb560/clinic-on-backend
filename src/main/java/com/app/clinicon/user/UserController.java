package com.app.clinicon.user;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;

/**
 * @author jesussb560
 */
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/web/user")
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
        return "dashboard/dashboard";
    }

    @GetMapping(value = "/account-activation/{token}")
    public String accountActivation(@PathVariable String token) {
        return userService.activateAccount(token);
    }

}
