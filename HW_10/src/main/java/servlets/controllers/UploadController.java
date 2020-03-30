package servlets.controllers;

import lombok.SneakyThrows;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import servlets.services.interfaces.AspectService;
import servlets.services.interfaces.FileUploadService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@WebServlet("/upload")
public class UploadController extends HttpServlet {

    @Autowired
    FileUploadService fileUploadService;

    @Autowired
    AspectService aspectService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        fileUploadService = springContext.getBean(FileUploadService.class);
        aspectService = springContext.getBean(AspectService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("upload.jsp").forward(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = "";
        FileItem fileItem = null;

        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(diskFileItemFactory);
        List<FileItem> items = upload.parseRequest(req);

        Iterator<FileItem> iter = items.iterator();

        while (iter.hasNext()) {
            FileItem item = iter.next();

            if (item.isFormField()) {
                email = item.getString();
            } else {
                fileItem = item;
            }
        }

        aspectService.setEmail(email);
        aspectService.setFileInfo(fileUploadService.upload(fileItem));
    }

}
