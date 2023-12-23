package fr.nicolas.godin.shoot_training_api.api.service;

import fr.nicolas.godin.shoot_training_api.database.entity.ValidationCode;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailerService {


    private final JavaMailSender javaMailSender;

    public void sendValidationCode(ValidationCode code) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("no-replyt@nicolas-godin.fr");
        message.setTo(code.getShooter().getEmail());
        message.setSubject("Code de validation");
        message.setText("Votre code de validation : "+ code.getCode());
        this.javaMailSender.send(message);

    }

}
