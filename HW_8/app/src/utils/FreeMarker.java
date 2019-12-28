package utills;

import freemarker.template.Configuration;

import freemarker.template.TemplateExceptionHandler;

import javax.servlet.ServletContext;

public class FreeMarker {

    public Configuration getCfg(ServletContext servletContext) {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);

        cfg.setServletContextForTemplateLoading(servletContext,"/WEB-INF/templates");

        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);

        return cfg;
    }
}
