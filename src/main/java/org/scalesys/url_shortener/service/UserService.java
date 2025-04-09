package org.scalesys.url_shortener.service;

import org.scalesys.url_shortener.entity.AppUser;
import org.scalesys.url_shortener.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<AppUser> get() {
        return userRepository.findAll();
    }

    public AppUser getCurrentUser() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUserName(userName);
    }

    public AppUser getByUserName(String userName) {
        return userRepository.findByName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
