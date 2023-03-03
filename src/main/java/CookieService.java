import jakarta.servlet.http.Cookie;

public class CookieService {
    Cookie[] cookies;

    public CookieService(Cookie[] cookies) {
        this.cookies = cookies;
    }


    public Cookie getCookie(String name) {
        for (Cookie cookie :
                cookies) {
            if (cookie.getName().equals(name)){
                return cookie;
            }
        }
       throw new NumberFormatException("Cant find cookie with name" + name);
    }
}
