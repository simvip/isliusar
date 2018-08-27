package my.jpa.utils;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Ivan Sliusar on 20.06.2018.
 * Red Line Soft corp.
 */
public class LogFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(LogFilter.class);

    public LogFilter() {
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        LOGGER.info("LogFilter init!");
    }

    @Override
    public void destroy() {
        LOGGER.info("LogFilter destroy!");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        if (!request.getRequestURI().contains("/signin")) {
            HttpSession session = request.getSession();
            if (session.getAttribute("login") == null) {
                response.sendRedirect(String.format("%s/signin", request.getContextPath()));
                return;
            }
        }
        chain.doFilter(req, resp);
    }

}
