package com.dallab.cattoy.application;

import com.dallab.cattoy.domain.User;
import com.dallab.cattoy.domain.UserRepository;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(User user) {
        return userRepository.save(user);
    }

}
