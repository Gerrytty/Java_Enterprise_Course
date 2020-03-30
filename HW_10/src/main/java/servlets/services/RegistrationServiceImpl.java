package servlets.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import servlets.models.Mail;
import servlets.models.User;
import servlets.repositories.interfaces.UsersRepository;
import servlets.services.interfaces.MailService;
import servlets.services.interfaces.RegistrationService;
import servlets.services.interfaces.TemplateForMail;

import java.util.HashMap;
import java.util.Map;

@Component(value = "myRegistrationService")
public class RegistrationServiceImpl implements RegistrationService {

    @Value("${spring.mail.username}")
    private String from;

    @Value("${link.to.confirm}")
    private String link;

    @Value("${mail.template}")
    private String templateName;

    @Autowired
    private MailService emailService;

    @Autowired
    private TemplateForMail template;

    @Autowired
    private UsersRepository usersRepository;

    private void send(User user) {

        Mail mail = new Mail();
        mail.setFrom(from);
        mail.setTo(user.getEmail());
        mail.setSubject("Confirm registration");

        Map model = new HashMap();
        model.put("name", user.getEmail());
        model.put("location", "Russia");
        model.put("link", link);
        mail.setModel(model);

        String html = template.getHtml(mail, templateName);

        emailService.send(mail, html, true);
    }

    @Override
    public void register(User user) {
        usersRepository.save(user);
        send(user);
    }
}
