package servlets.services;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import servlets.models.FileInfo;
import servlets.models.Mail;
import servlets.services.interfaces.MailService;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@EnableAspectJAutoProxy
public class MailAspectImpl {

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    MailService mailService;

    @Autowired
    AspectServiceImpl aspectService;

    @Autowired
    TemplateForMailImpl templateForMail;

    @AfterReturning(pointcut = "execution(* servlets.services.FileUploadServiceImpl.upload(..))", returning = "fileInfo")
    public void send(Object fileInfo) {

        Map model = new HashMap<>();

        FileInfo info = (FileInfo) fileInfo;

        model.put("url", "localhost:9080/files?fileName=" + info.getStorageFileName());
        model.put("fileName", info.getOriginalFileName());
        model.put("name", aspectService.getEmail());

        Mail mail = Mail.builder()
                .to(aspectService.getEmail())
                .from("gerrytty@yandex.ru")
                .content("content")
                .subject("Upload file")
                .model(model).build();


        mailService.send(mail, templateForMail.getHtml(mail, "file.ftl"), true);

    }

}
