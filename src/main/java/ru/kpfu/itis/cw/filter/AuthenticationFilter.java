package ru.kpfu.itis.cw.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/signin", "/signup", "/signout", "/profile"})
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession(false);

        boolean isAuthenticated = false;
        boolean sessionExists = session != null;
        boolean isRequestOnAuthServlet = request.getRequestURI().equals("/store/signin") || request.getRequestURI().equals("/store/signup");

        if (sessionExists) {
            isAuthenticated = session.getAttribute("profile") != null;
        }

        if (isAuthenticated && !isRequestOnAuthServlet || !isAuthenticated && isRequestOnAuthServlet) {
            filterChain.doFilter(request, response);
        } else if (isAuthenticated && isRequestOnAuthServlet) {
            response.sendRedirect("/store/profile");
        } else {
            response.sendRedirect("/store/signin");
        }
    }
}
