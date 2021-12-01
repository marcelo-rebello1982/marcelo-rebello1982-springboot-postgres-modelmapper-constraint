package com.marcelo.algafood;

import com.marcelo.algafood.api.util.DateConverter;
import com.marcelo.algafood.api.util.DateConverterImpl;
import com.marcelo.algafood.infrastructure.repository.CustomJpaRepositoryImpl;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.TimeZone;


@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class AlgafoodApiApplication {


    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        System.out.println("Aplicacao Spring boot rodando em UTC timezone : " + new Date());

        DateConverter dateConvertor = new DateConverterImpl();

        OffsetDateTime offsetDateTime = OffsetDateTime.now();

        System.out.println("SqlTimestamp: " + dateConvertor.asTimestamp(offsetDateTime));
        System.out.println("Timestamp : " + dateConvertor.timestampZone(offsetDateTime));
        System.out.println(dateConvertor.timestampDate(offsetDateTime));
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {
            System.out.println("\n AlgafoodApiApplication.class : MSg 1 (check the MySQL database) ...");
            System.out.println("\n AlgafoodApiApplication.class : MSg 2  (check the PostgreSQL database) ...");

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(AlgafoodApiApplication.class, args);
    }
}
