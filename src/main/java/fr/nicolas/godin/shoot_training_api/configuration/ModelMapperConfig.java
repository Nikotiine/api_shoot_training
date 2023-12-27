package fr.nicolas.godin.shoot_training_api.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    // Configuration du modelMapper
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
