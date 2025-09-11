package com.example.demo.Config;

import com.example.demo.Dto.PersonDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class PersonDtoConfig {

    @Bean
    public PersonDto person03() {
        return PersonDto.builder()
                .name("뿡빵삥")
                .age(12)
                .build();
    }

    @Bean(name = "personBean")
    public PersonDto person04() {
        return PersonDto.builder()
                .name("우얄라고")
                .age(12)
                .build();
    }
}
