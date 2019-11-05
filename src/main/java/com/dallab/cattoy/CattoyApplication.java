package com.dallab.cattoy;

import com.dallab.cattoy.util.JwtUtil;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class CattoyApplication {

    public static void main(String[] args) {
        SpringApplication.run(CattoyApplication.class, args);
    }

    @Bean
    public Mapper modelMapper() {
        return DozerBeanMapperBuilder.buildDefault();
    }

}
