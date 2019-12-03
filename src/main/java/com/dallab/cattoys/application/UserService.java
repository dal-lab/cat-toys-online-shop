package com.dallab.cattoys.application;

import com.dallab.cattoys.domain.User;
import com.dallab.cattoys.domain.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(User user) {
        user.hashPassword(passwordEncoder);

        return userRepository.save(user);
    }

    public User authenticate(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(EntityNotFoundException::new);

        if (!user.checkPassword(password, passwordEncoder)) {
            return null;
        }

        return user;
    }

}
