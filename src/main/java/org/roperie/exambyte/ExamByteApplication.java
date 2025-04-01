package org.roperie.exambyte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.roperie.exambyte")
public class ExamByteApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamByteApplication.class, args);
    }

}
