package br.com.tp.lanchescaieiras;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "br.com.tp.lanchescaieiras")
public class LanchesCaieiras {

    public static void main(String[] args) {
        SpringApplication.run(LanchesCaieiras.class, args);
    }
}
