package servlets.services.interfaces;

import servlets.models.Mail;


public interface MailService {

    void send(Mail mail, String text, boolean isHtml);
}
