package com.dallab.cattoy.application;


import com.dallab.cattoy.domain.User;
import com.dallab.cattoy.domain.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        userService = new UserService(userRepository);
    }

    @Test
    public void register() {
        User user = User.builder()
                .name("테스터")
                .email("tester@example.com")
                .password("pass")
                .build();

        userService.register(user);

        verify(userRepository).save(user);
    }

}
