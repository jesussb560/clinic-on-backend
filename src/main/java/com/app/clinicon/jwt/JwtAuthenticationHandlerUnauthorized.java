package com.app.clinicon.jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


@Component
public class JwtAuthenticationHandlerUnauthorized implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {

                response.setContentType("application/json;charset=UTF-8");
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED );
                response.getOutputStream().println("{ \"error\": \"" + authException.getMessage() + "\" }");
        
    }
    
}
