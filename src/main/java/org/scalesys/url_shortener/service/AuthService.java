package org.scalesys.url_shortener.service;

import org.scalesys.url_shortener.dto.AuthRequest;
import org.scalesys.url_shortener.entity.AppUser;
import org.scalesys.url_shortener.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @Autowired
    public AuthService(JwtUtil jwtUtil, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    public String processLoginRequest(AuthRequest authRequest) {
        final AppUser appUser = userService.getByUserName(authRequest.getUsername());
        // For demo: hardcoded username/password
        if (appUser.getPassword().equals(authRequest.getPassword())) {
            return jwtUtil.generateToken(authRequest.getUsername());
        }

        return null;
    }
}
