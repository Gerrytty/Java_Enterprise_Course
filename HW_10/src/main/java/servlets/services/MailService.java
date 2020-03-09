package servlets.services;

import freemarker.template.TemplateException;
import servlets.models.Mail;

import javax.mail.MessagingException;
import java.io.IOException;

public interface MailService {

    void sendSimpleMessage(Mail mail) throws MessagingException, IOException, TemplateException;

}
