package kr.basic.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SecurityApplication {

    @Bean
    public BCryptPasswordEncoder encodePw(){
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }

}
