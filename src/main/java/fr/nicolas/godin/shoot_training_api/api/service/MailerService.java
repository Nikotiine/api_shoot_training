package fr.nicolas.godin.shoot_training_api.api.service;

import fr.nicolas.godin.shoot_training_api.database.entity.ActivationCode;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailerService {


    private final JavaMailSender javaMailSender;


    public void sendValidationCode(ActivationCode code) {
        SimpleMailMessage message = this.generateMessage(1,code);
        this.javaMailSender.send(message);

    }

    public void sendNewPasswordCode(ActivationCode code) {

        SimpleMailMessage message = this.generateMessage(2,code);
        this.javaMailSender.send(message);

    }

    private SimpleMailMessage generateMessage(int type,ActivationCode code) {
        String shooterEmail = code.getShooter().getEmail();
        Dotenv dotenv = Dotenv.load();
        String startText = type == 1 ? "Lien de validation " : "Lien de réinitialisation ";
        String url = type == 1 ? "authentication/account-activation/" : "authentication/new-password/";
        String subject = type == 1 ? "Code de validation" : "Code de réinitialisation";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(dotenv.get("EMAIL_USERNAME"));
        message.setTo(shooterEmail);
        message.setSubject(subject);

        message.setText(startText+ dotenv.get("WEBSITE_URL")+url+shooterEmail+"/activate"+" Votre code  : "+ code.getCode());
        return message;
    }

}
