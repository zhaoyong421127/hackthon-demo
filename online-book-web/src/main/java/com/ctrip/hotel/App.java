package com.ctrip.hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EntityScan("com.ctrip.hotel.model")
@EnableJpaRepositories
@EnableWebFlux
public class App 
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class,args);
    }
}
