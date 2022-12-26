package ru.kpfu.itis.cw.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebServlet("/upload-image/*")
public class UploadImageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String imagePath = (req.getPathInfo().substring(1));
        InputStream inputStream = Files.newInputStream(Paths.get("..\\ImagesForWebSite\\ContentImages" + req.getPathInfo() + ".png"));
        byte[] imageBytes = inputStream.readAllBytes();

        resp.setContentType(getServletContext().getMimeType(imagePath));
        resp.setContentLength(imageBytes.length);
        resp.getOutputStream().write(imageBytes);
    }
}
