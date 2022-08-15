package com.app.clinicon.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.app.clinicon.configuration.TokenPropertiesConfig;
import com.app.clinicon.user.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private  JwtProvider jwtProvider;

    @Autowired
    private  TokenPropertiesConfig tokenPropertiesConfig;

    @Autowired
    private  JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {

            String jwt = getJwtFromRequest(request);

            if (StringUtils.hasText(jwt) && jwtProvider.validateToken(jwt)) {

                Long userId = jwtProvider.getUserIdFromJWT(jwt);
                
                UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(userService.findById(userId).getRut());

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);

            }
            
        } catch (Exception ex) {
            log.error("Could not set user authentication in security context, ", ex);
        }

        filterChain.doFilter(request, response);

    }

    private String getJwtFromRequest(HttpServletRequest request) {

        String header = request.getHeader(tokenPropertiesConfig.getHeader());

        if ( header != null && header.startsWith(tokenPropertiesConfig.getPrefix()) ) {
            return header.replace("Bearer ", "");
        }

        return null;
    }

}
