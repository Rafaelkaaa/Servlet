import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Setter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.FileTemplateResolver;

import java.io.IOException;
import java.util.Map;


@WebServlet(value = "/time")
public class TimeServlet extends HttpServlet {
    private TemplateEngine engine;


    @Override
    public void init() throws ServletException {
        engine = new TemplateEngine();
        FileTemplateResolver resolver = new FileTemplateResolver();
        resolver.setPrefix("D:/IdeaProjects/untitled1/src/main/resources/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML5");
        resolver.setOrder(engine.getTemplateResolvers().size());
        resolver.setCacheable(false);
        engine.addTemplateResolver(resolver);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setHeader("Content-Type", "text/html; charset=utf-8");
        resp.setHeader("Refresh", "0.5");


        Context context = new Context(req.getLocale(),
                Map.of("time", getTimeWithTimeZone(req,resp)));

        engine.process("time", context, resp.getWriter());

    }

    private static String getTimeWithTimeZone(HttpServletRequest req, HttpServletResponse resp) {
        TimeZone timeZone = new TimeZone(req.getParameter("timezone"));
        timeZone.convertTimeZoneParameterToInt();

        if (timeZone.isTimeZoneValid()){
            resp.addCookie(new Cookie("lastTimeZone", Integer.toString(timeZone.getTimeZone())));
        }else {
            String lastTimeZone = new CookieService(req.getCookies())
                    .getCookie("lastTimeZone")
                    .getValue();
            timeZone.setTimeZoneParameter("UTC" + (lastTimeZone));
        }
        return timeZone.getTimeWithTimeZone();
    }
}
