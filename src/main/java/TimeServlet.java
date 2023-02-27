import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/time")
public class TimeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setHeader("Content-Type", "text/html; charset=utf-8");
        resp.setHeader("Refresh", "0.5");

        TimeZone timeZone = new TimeZone(req.getParameter("timezone"));

        resp.getWriter().print("<h1>" +
                timeZone.getTimeWithTimeZone()
                + "</h1>");
        resp.getWriter().close();
    }
}
