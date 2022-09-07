package school.sptech.server.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailSenderValidatorAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.server.model.User;
import school.sptech.server.repository.EmailRepository;
import school.sptech.server.repository.UserRepository;
import school.sptech.server.service.UserService;
import school.sptech.server.service.UserUtil;

@RestController
@RequestMapping("/password")
public class EmailController {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private UserRepository dbUserRepository;
    private UserUtil userUtil;
    @Autowired
    private UserService dbServiceUser;
    @Autowired
    private EmailRepository dbEmailRepository;

    @Bean
    public MailSenderValidatorAutoConfiguration mailSenderValidatorAutoConfiguration(JavaMailSenderImpl javaMailSender){
        return new MailSenderValidatorAutoConfiguration(javaMailSender);
    }

    String newToken = UserUtil.getToken();

    public void saveNewToken(User user, String newToken){
        dbEmailRepository.updateToken(newToken, user.getId());
    }

    @RequestMapping(path = "/{email}", method = RequestMethod.GET)
    public String sendTokenByMail(@PathVariable String email) {
        if (dbServiceUser.existsByEmail(email)) {

            User user = dbUserRepository.findByEmail(email);
            saveNewToken(user, newToken);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject("Homebox");
            message.setText(String.format("Hello, %s!\n\nThis is your token to change password: %s",
                                           user.getName(), user.getToken()));
//            message.setTo("homebox-inovacao@outlook.com");
            message.setTo("pgb61207@xcoxc.com");
            message.setFrom("homebox-inovacao@outlook.com");
            try {
                mailSender.send(message);
                return "Email enviado com sucesso!";
            } catch (Exception e) {
                e.printStackTrace();
                return "Erro ao enviar email.";
            }
        } else {
            return "Usuário incorreto. Tente novamente.";
        }
    }

    @RequestMapping(path = "reset/{email}/{token}", method = RequestMethod.GET)
    public String checkToken(@PathVariable String email, @PathVariable String token) {
        if (dbServiceUser.existsByEmail(email)) {
            User user = dbUserRepository.findByEmail(email);
            saveNewToken(user, newToken);
            if (token.equals(user.getToken())) {
                return "Token correto.";
            } else {
                return "Token incorreto.";
            }
        }  else {
            return "Usuário incorreto. Tente novamente.";
        }
    }

    @RequestMapping(path = "reset/{email}/{token}/{newPassword}", method = RequestMethod.GET)
    public String changePassword(@PathVariable String email, @PathVariable String token, @PathVariable String newPassword) {
        User user = dbUserRepository.findByEmail(email);
        if(checkToken(email,token).equalsIgnoreCase("Token correto.")){
            dbEmailRepository.updatePassword(newPassword, user.getId());
            saveNewToken(user, UserUtil.getToken());
            return "Senha alterada com sucesso!";
        } else {
            return "Não foi possível alterar sua senha";
        }
    }
}
