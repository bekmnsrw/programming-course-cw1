package ru.kpfu.itis.cw.servlets.forms;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.kpfu.itis.cw.dto.UserDto;
import ru.kpfu.itis.cw.dto.forms.SignInForm;
import ru.kpfu.itis.cw.exceptions.IncorrectPasswordException;
import ru.kpfu.itis.cw.exceptions.NotFoundException;
import ru.kpfu.itis.cw.services.UserService;

import java.io.IOException;

@WebServlet("/signin")
public class SignInServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        userService = (UserService) servletContext.getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/WEB-INF/views/forms/signin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("signInEmail");
        String password = req.getParameter("signInPassword");

        SignInForm signInForm = new SignInForm(email, password);

        UserDto userProfile;

        try {
            userProfile = userService.signIn(signInForm);
        } catch (NotFoundException e) {
            throw new NotFoundException("No user with such email");
        } catch (IncorrectPasswordException e) {
            throw new IncorrectPasswordException("Invalid password");
        }

        HttpSession session = req.getSession(true);
        session.setAttribute("profile", userProfile);
        session.setAttribute("role", userProfile.getIsAdmin());

        resp.sendRedirect("profile");
    }
}
