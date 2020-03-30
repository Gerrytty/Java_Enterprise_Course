package servlets.services;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import servlets.models.Mail;
import servlets.services.interfaces.TemplateForMail;

import java.io.IOException;

@Service
public class TemplateForMailImpl implements TemplateForMail {

    @Autowired
    private Configuration freemarkerConfig;

    public String getHtml(Mail mail, String templateName) {

        try {
            Template t = freemarkerConfig.getTemplate(templateName);
            return FreeMarkerTemplateUtils.processTemplateIntoString(t, mail.getModel());
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }

        return "";
    }

}
