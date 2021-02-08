package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
  
  // ? Generating default students
  @Bean
  CommandLineRunner commandLineRunner(StudentRepository repository) {
    return args -> {
      Student bellacroix = new Student(
        "Bellacroix",
        "bellacroix@gmail.com",
        LocalDate.of(1999, Month.FEBRUARY, 2)
      );

      Student ilia = new Student(
        "Ilia",
        "ilia@gmail.com",
        LocalDate.of(2002, Month.FEBRUARY, 2)
      );

      // ? JPA Method to save list of elements in database
      repository.saveAll(
        List.of(bellacroix, ilia)
      );
    };
  }
}
