package ru.kpfu.itis.cw.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.kpfu.itis.cw.dto.UserDto;
import ru.kpfu.itis.cw.repository.ContentRepository;
import ru.kpfu.itis.cw.repository.FavoritesRepository;

import java.io.IOException;

@WebServlet("/content")
public class ContentServlet extends HttpServlet {
    private FavoritesRepository favoritesRepository;
    private ContentRepository contentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        favoritesRepository = (FavoritesRepository) servletContext.getAttribute("favoritesRepository");
        contentRepository = (ContentRepository) servletContext.getAttribute("contentRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query = req.getParameter("query");

        if (query != null) {
            req.setAttribute("content", contentRepository.search(query));
        } else {
            req.setAttribute("content", contentRepository.findAll());
        }

        req.getServletContext().getRequestDispatcher("/WEB-INF/views/content.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String add = req.getParameter("add");

        if (add != null) {
            favoritesRepository.save(((UserDto) req.getSession(false).getAttribute("profile")).getId(), Long.valueOf(add));
        }

        resp.sendRedirect("content");
    }
}
