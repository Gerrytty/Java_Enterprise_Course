package servlets.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import servlets.exceptions.MailException;
import servlets.models.Mail;
import servlets.services.interfaces.MailService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void send(Mail mail, String text, boolean isHtml) {

        MimeMessage message = emailSender.createMimeMessage();

        try {

            MimeMessageHelper helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            helper.setTo(mail.getTo());
            helper.setText(text, isHtml);
            helper.setSubject(mail.getSubject());
            helper.setFrom(mail.getFrom());

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new MailException();
        }

        emailSender.send(message);
    }
}
