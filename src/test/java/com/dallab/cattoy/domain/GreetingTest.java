package com.dallab.cattoy.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GreetingTest {

    @Test
    public void getMessage() throws Exception {
        Greeting greeting = Greeting.builder().build();
        assertThat(greeting.getMessage()).isEqualTo("Hello");
    }

    @Test
    public void getMessageWithName() throws Exception {
        Greeting greeting = Greeting.builder().name("JOKER").build();
        assertThat(greeting.getMessage()).isEqualTo("Hello, JOKER");
    }

}
