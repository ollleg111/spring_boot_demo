package com.example.spring_boot_demo;

import com.example.spring_boot_demo.dao.StudentDAO;
import com.example.spring_boot_demo.model.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentDAO studentDAO){
        return args -> {
            Student st1 = new Student(
                    "oleg",
                    "oleg@gmail.com",
                    LocalDate.of(1977, Month.NOVEMBER, 14)
            );

            Student st2 = new Student(
                    "alex",
                    "alex@gmail.com",
                    LocalDate.of(1987, Month.DECEMBER, 15)
            );

            Student st3 = new Student(
                    "bob",
                    "bob@gmail.com",
                    LocalDate.of(2000, Month.JANUARY, 16)
            );
            studentDAO.saveAll(
                    List.of(st1, st2, st3)
            );
        };
    }
}

