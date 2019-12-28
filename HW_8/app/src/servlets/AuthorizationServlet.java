package servlets;

import context.ApplicationContext;
import dto.UserDto;
import services.login.LoginServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/authorization")
public class AuthorizationServlet extends HttpServlet {
    private ApplicationContext context;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("auth.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto user = context.getComponent(LoginServiceImpl.class, "loginService")
                .signUp(req.getParameter("email"), req.getParameter("password"));

        if(user == null) {
            resp.sendRedirect("/authorization");
        }
        else {
            resp.sendRedirect("/main");
        }

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.context = (ApplicationContext) config.getServletContext().getAttribute("context");
    }

}
