package servlets;

import context.ApplicationContext;
import dto.ProductDto;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import services.product.ListOfProductsService;
import services.product.ListOfProductsServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/listOfProducts")
public class ListOfProduct extends HttpServlet {
    private ApplicationContext context;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ListOfProductsService listOfProductsService =
                context.getComponent(ListOfProductsServiceImpl.class, "listOfProductsService");
        List<ProductDto> productList = listOfProductsService.findAll();

        ServletContext servletContext = getServletContext();
        Configuration cfg = new utills.FreeMarker().getCfg(servletContext);

        cfg.setServletContextForTemplateLoading(servletContext,"/WEB-INF/templates");

        Map root = new HashMap();

        root.put("productsList", productList);

        Template t = cfg.getTemplate("listOfProducts.ftlh");
        try {
            t.process(root, resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }



    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.context = (ApplicationContext) config.getServletContext().getAttribute("context");
    }
}
