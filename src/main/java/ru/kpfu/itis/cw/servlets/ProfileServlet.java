package ru.kpfu.itis.cw.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import ru.kpfu.itis.cw.models.Content;
import ru.kpfu.itis.cw.models.Tag;
import ru.kpfu.itis.cw.repository.ContentRepository;
import ru.kpfu.itis.cw.repository.impl.TagRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@MultipartConfig
@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private ContentRepository contentRepository;
    private TagRepository tagRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        contentRepository = (ContentRepository) servletContext.getAttribute("contentRepository");
        tagRepository = (TagRepository) servletContext.getAttribute("tagRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String contentName = req.getParameter("contentName");
        String isNSFW = req.getParameter("isNSFW");
        Part image = req.getPart("image");
        String tag1 = req.getParameter("tag1");
        String tag2 = req.getParameter("tag2");

        if (tagRepository.findById(tag1).isEmpty()) {
            tagRepository.save(Tag.builder().name(tag1).build());
        }

        if (tagRepository.findById(tag2).isEmpty()) {
            tagRepository.save(Tag.builder().name(tag2).build());
        }

        Boolean _isNSFW = isNSFW != null;

        Content content = Content.builder()
                .name(contentName)
                .isNSFW(_isNSFW)
                .build();

        contentRepository.save(content);

        if (image.getInputStream() instanceof FileInputStream) {
            try (InputStream inputStream = (image.getInputStream())) {
                new File("..\\ImagesForWebSite\\ContentImages").mkdirs();
                File file = new File("..\\ImagesForWebSite\\ContentImages\\" + content.getId() + ".png");
                Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new IllegalArgumentException();
            }
        }

        resp.sendRedirect("profile");
    }
}
