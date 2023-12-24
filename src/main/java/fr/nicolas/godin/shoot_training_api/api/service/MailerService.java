package fr.nicolas.godin.shoot_training_api.api.service;

import fr.nicolas.godin.shoot_training_api.database.entity.ValidationCode;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailerService {


    private final JavaMailSender javaMailSender;


    public void sendValidationCode(ValidationCode code) {
        Dotenv dotenv = Dotenv.load();
        SimpleMailMessage message = new SimpleMailMessage();
        String shooterEmail = code.getShooter().getEmail();
        message.setFrom("no-replyt@nicolas-godin.fr");
        message.setTo(shooterEmail);
        message.setSubject("Code de validation");
        message.setText("Lien de validation "+ dotenv.get("WEBSITE_URL")+shooterEmail+" Votre code de validation : "+ code.getCode());
        this.javaMailSender.send(message);

    }

}
