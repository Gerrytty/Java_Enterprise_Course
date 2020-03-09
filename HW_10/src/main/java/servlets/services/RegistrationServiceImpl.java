package servlets.services;

import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import servlets.models.Mail;
import servlets.models.User;
import servlets.repositories.UsersRepository;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component(value = "myRegistrationService")
public class RegistrationServiceImpl implements RegistrationService {

    @Value("${spring.mail.username}")
    private String from;

    @Value("${link.to.confirm}")
    private String link;

    @Autowired
    MailService emailService;

    private void send(User user) {
        Mail mail = new Mail();
        mail.setFrom(from);
        mail.setTo(user.getEmail());
        mail.setSubject("Confirm registration");

        Map model = new HashMap();
        model.put("name", user.getEmail());
        model.put("location", "Russia");
//        model.put("signature", "https://memorynotfound.com");
        model.put("link", link);
        mail.setModel(model);

        try {
            emailService.sendSimpleMessage(mail);
        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public void register(User user) {
        usersRepository.save(user);
        send(user);
    }
}
