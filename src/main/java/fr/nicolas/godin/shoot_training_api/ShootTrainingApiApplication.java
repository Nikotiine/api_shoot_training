package fr.nicolas.godin.shoot_training_api;


import fr.nicolas.godin.shoot_training_api.database.UserRole;
import fr.nicolas.godin.shoot_training_api.database.entity.User;
import fr.nicolas.godin.shoot_training_api.database.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@AllArgsConstructor
@SpringBootApplication
public class ShootTrainingApiApplication implements CommandLineRunner {

	private final BCryptPasswordEncoder passwordEncoder;
	private UserRepository userRepository;

	public static void main(String[] args) {

		SpringApplication.run(ShootTrainingApiApplication.class, args);

	}


	/**
	 * Callback used to run the bean.
	 *
	 * @param args incoming main method arguments
	 * @throws Exception on error
	 */
	@Override
	public void run(String... args) throws Exception {
		User isExist = this.userRepository.findByEmail("admin@admin.fr");
		if (isExist == null) {
			User admin = User.builder()
				.email("admin@admin.fr")
				.password(passwordEncoder.encode("admin"))
				.firstName("Nicolas")
				.lastName("Godin")
				.role(UserRole.ADMIN)
				.build();
			admin.setActive(true);
			this.userRepository.save(admin);
		}

	}
}
