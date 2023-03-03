import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/time")
public class TimezoneValidateFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        TimeZone timeZone = new TimeZone(httpRequest.getParameter("timezone"));
        timeZone.convertTimeZoneParameterToInt();


        if (timeZone.isTimeZoneValid()) {

            chain.doFilter(request, response);
        } else {
            try {
                new CookieService(httpRequest.getCookies()).getCookie("lastTimeZone");
                chain.doFilter(request, response);
            } catch (NullPointerException ex) {
                httpResponse.sendError
                        (HttpServletResponse.SC_BAD_REQUEST,
                                "Invalid timezone range, try enter from -12 till +13");
            }
        }
    }



    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}