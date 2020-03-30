package servlets.services.interfaces;

import freemarker.template.TemplateException;
import servlets.models.FileInfo;

import javax.mail.MessagingException;
import java.io.IOException;

public interface MailAspect {

    void send(FileInfo fileInfo, String email) throws MessagingException, IOException, TemplateException;

}
