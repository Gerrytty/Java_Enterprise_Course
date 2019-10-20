import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Logging implements Filter {

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        FileWriter fileWriter = new FileWriter("/home/yuliya/Desktop/Logging/log.txt", true);

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String method = request.getMethod();
        String currentDate = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        StringBuffer url = request.getRequestURL();

        String out = "Current date: " + currentDate + " Method: " + method + " URL: " + url + "\n";

        fileWriter.write(out);
        fileWriter.close();

        filterChain.doFilter(servletRequest, servletResponse);
    }

}
