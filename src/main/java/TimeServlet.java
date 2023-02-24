
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@WebServlet(value = "/time")
public class TimeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setHeader("Content-Type", "text/html; charset=utf-8");
       // resp.setHeader("Refresh", "1");

       // DateTimeFormatter dateTimeFormat = DateTimeFormatter
       //         .ofPattern("yyyy/MM/dd HH:mm:ss");
       // String currentTime = dateTimeFormat.format(LocalDateTime.now());
        resp.getWriter().write("<h1>Hello</h1>");
        resp.getWriter().close();
    }
}