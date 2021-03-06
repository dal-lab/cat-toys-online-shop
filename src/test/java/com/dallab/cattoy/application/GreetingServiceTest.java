package com.dallab.cattoy.application;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GreetingServiceTest {

    private GreetingService greetingService;

    @Before
    public void setUp() {
        greetingService = new GreetingService();
    }

    @Test
    public void getMessage() throws Exception {
        assertThat(greetingService.getMessage(null))
                .isEqualTo("Hello");
    }

    @Test
    public void getMessageWithName() throws Exception {
        assertThat(greetingService.getMessage("JOKER"))
                .isEqualTo("Hello, JOKER");
    }

}
