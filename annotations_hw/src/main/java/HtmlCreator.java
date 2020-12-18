import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class HtmlCreator {
    private Configuration configuration;

    public HtmlCreator() {
        configuration = new Configuration(Configuration.VERSION_2_3_30);
        configuration.setClassForTemplateLoading(this.getClass(), "/");
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        configuration.setLogTemplateExceptions(false);
        configuration.setWrapUncheckedExceptions(true);
    }

    public void create(String path, Form form) {
        try {
            Path out = Paths.get(path);
            BufferedWriter writer = new BufferedWriter(new FileWriter(out.toFile()));
            Template t = configuration.getTemplate("template.ftlh");
            HashMap<String, Object> root = new HashMap<>();
            root.put("form", form);
            t.process(root, writer);
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }
}