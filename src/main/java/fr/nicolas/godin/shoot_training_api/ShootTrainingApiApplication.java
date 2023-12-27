package fr.nicolas.godin.shoot_training_api;

import io.github.cdimascio.dotenv.Dotenv;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShootTrainingApiApplication {




	public static void main(String[] args) {

		SpringApplication.run(ShootTrainingApiApplication.class, args);
	}

}
