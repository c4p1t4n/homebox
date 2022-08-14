package school.sptech.server.controllers;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.server.repository.UserRepository;

@RestController
public class EmailController {

    @Autowired private JavaMailSender mailSender;
    @Autowired private UserRepository user;

    @RequestMapping(path = "/email-send/{email}/{password}", method = RequestMethod.GET)
    public String sendMail(@PathVariable String email, @PathVariable String password) {
        if(user.existsByEmailAndPassword(email, password)) {
            String randomCode = RandomString.make(64);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject("Homebox");
            message.setText(String.format("Hello, %s!\n\nThis is your verification code: %s",
                            user.findByEmailAndPassword(email,password).getName(), randomCode));
            message.setTo("homebox-inovacao@outlook.com");
            message.setFrom("homebox-inovacao@outlook.com");

            try {
                mailSender.send(message);
                return "Email enviado com sucesso!";
            } catch (Exception e) {
                e.printStackTrace();
                return "Erro ao enviar email.";
            }
        } else {
            return "Usuário ou senha incorretos. Tente novamente.";
        }
    }
}
