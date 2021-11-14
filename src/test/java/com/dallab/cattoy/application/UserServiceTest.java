package com.dallab.cattoy.application;


import com.dallab.cattoy.domain.User;
import com.dallab.cattoy.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class UserServiceTest {

    private UserService userService;

    private PasswordEncoder passwordEncoder;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        passwordEncoder = new BCryptPasswordEncoder();

        userService = new UserService(userRepository, passwordEncoder);
    }

    @BeforeEach
    public void mockUserRepository() {
        User user = User.builder()
                .name("테스터")
                .email("tester@example.com")
                .password(passwordEncoder.encode("pass"))
                .build();

        given(userRepository.findByEmail("tester@example.com"))
                .willReturn(Optional.of(user));
    }

    @Test
    public void register() {
        User user = User.builder()
                .name("테스터")
                .email("tester@example.com")
                .password("pass")
                .build();

        userService.register(user);

        assertThat(user.getPassword()).isNotEqualTo("pass");

        verify(userRepository).save(user);
    }

    @Test
    public void authenticateWithValidAttributes() {
        User user = userService.authenticate("tester@example.com", "pass");

        assertThat(user).isNotNull();
    }

    @Test
    public void authenticateWithNotExistedEmail() {
        given(userRepository.findByEmail("x@example.com"))
                .willThrow(new EntityNotFoundException());

        assertThrows(EntityNotFoundException.class, () -> {
            userService.authenticate("x@example.com", "x");
        });
    }

    @Test
    public void authenticateWithWrongPassword() {
        User user = userService.authenticate("tester@example.com", "x");

        assertThat(user).isNull();
    }

}
