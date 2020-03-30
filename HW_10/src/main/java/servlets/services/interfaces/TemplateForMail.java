package servlets.services.interfaces;

import servlets.models.Mail;

public interface TemplateForMail {

    String getHtml(Mail mail, String templateName);

}
