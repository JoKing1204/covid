package com.example.demo.game;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;


import static java.time.Month.*;

@Configuration
public class GameConfig {
    @Bean
    CommandLineRunner commandLineRunner(GameRepository repository){
        return args ->{
            Game xbox360= new Game (
                    "Xbox 360",
                    "Microsoft",
                    LocalDate.of(2005, NOVEMBER, 22)

            );
            Game ps3= new Game (
                    "Playstation 3",
                    "Sony Interactive Entertainment",
                    LocalDate.of(2006, NOVEMBER, 17)

            );

            repository.saveAll(
                    List.of(xbox360, ps3)
            );
        };
    }
}
