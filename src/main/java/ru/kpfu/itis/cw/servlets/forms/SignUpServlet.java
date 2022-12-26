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
import ru.kpfu.itis.cw.dto.forms.SignUpForm;
import ru.kpfu.itis.cw.services.UserMapper;
import ru.kpfu.itis.cw.services.UserService;

import java.io.IOException;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
    private UserService userService;
    private UserMapper userMapper;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        userService = (UserService) servletContext.getAttribute("userService");
        userMapper = (UserMapper) servletContext.getAttribute("userMapper");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/forms/signup.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("signUpFirstName");
        String secondName = req.getParameter("signUpSecondName");
        String email = req.getParameter("signUpEmail");
        String password = req.getParameter("signUpPassword");

        SignUpForm signUpForm = new SignUpForm(firstName, secondName, email, password);

        HttpSession session = req.getSession(true);

        UserDto userProfile = userService.signUp(signUpForm);

        session.setAttribute("profile", userProfile);
        session.setAttribute("role", userProfile.getIsAdmin());

        userMapper.toUserFromSignUpForm(signUpForm);

        resp.sendRedirect("profile");
    }
}
