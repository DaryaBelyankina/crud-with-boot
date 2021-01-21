package ru.belyankina.crudwithboot.security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ru.belyankina.crudwithboot.model.Role;
import ru.belyankina.crudwithboot.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    private final UserDetailsService userDetailsService;

    public LoginSuccessHandler(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        System.out.println("in handler");
        if (authentication.getAuthorities().contains(new Role("ADMIN"))){
            httpServletResponse.sendRedirect("/admin");
        } else {
            long id =((User) (userDetailsService.loadUserByUsername(authentication.getName()))).getId();
            httpServletResponse.sendRedirect("/user/" + id);
        }
    }
}