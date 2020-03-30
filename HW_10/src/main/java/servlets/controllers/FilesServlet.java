package servlets.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import servlets.models.FileInfo;
import servlets.services.interfaces.FilesService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/files")
public class FilesServlet extends HttpServlet {

    @Autowired
    FilesService filesService;

    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        filesService = springContext.getBean(FilesService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String name = req.getParameter("fileName");

        FileInfo info = filesService.getFileInfoByName(name);

        req.setAttribute("path", info.getUrl() + info.getStorageFileName());

        req.getRequestDispatcher("file.jsp").forward(req, resp);
    }

}
