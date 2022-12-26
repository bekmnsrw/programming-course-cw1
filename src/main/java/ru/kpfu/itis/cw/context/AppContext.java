package ru.kpfu.itis.cw.context;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.annotation.WebServlet;
import ru.kpfu.itis.cw.exceptions.DbConnectionException;
import ru.kpfu.itis.cw.exceptions.DbDriverException;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import ru.kpfu.itis.cw.jdbc_connection.ConnectionProvider;
import ru.kpfu.itis.cw.repository.ContentRepository;
import ru.kpfu.itis.cw.repository.FavoritesRepository;
import ru.kpfu.itis.cw.repository.UserRepository;
import ru.kpfu.itis.cw.repository.impl.ContentRepositoryImpl;
import ru.kpfu.itis.cw.repository.impl.FavoritesRepositoryImpl;
import ru.kpfu.itis.cw.repository.impl.TagRepository;
import ru.kpfu.itis.cw.repository.impl.UserRepositoryImpl;
import ru.kpfu.itis.cw.services.*;
import ru.kpfu.itis.cw.services.impl.*;
import ru.kpfu.itis.cw.services.validation.Validator;

import java.sql.Connection;

@WebListener
public class AppContext implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        try {
            Connection connection = ConnectionProvider.getInstance().getConnection();

            UserRepository userRepository = new UserRepositoryImpl(connection);
            servletContext.setAttribute("userRepository", userRepository);

            ContentRepository contentRepository = new ContentRepositoryImpl(connection);
            servletContext.setAttribute("contentRepository", contentRepository);

            HashService hashService = new HashServiceImpl();
            servletContext.setAttribute("hashService", hashService);

            Validator validator = new ValidatorImpl(userRepository);
            servletContext.setAttribute("validator", validator);

            UserMapper userMapper = new UserMapperImpl(hashService);
            servletContext.setAttribute("userMapper", userMapper);

            ContentMapper contentMapper = new ContentMapperImpl();
            servletContext.setAttribute("contentMapper", contentMapper);

            UserService userService = new UserServiceImpl(userRepository, userMapper, validator, hashService);
            servletContext.setAttribute("userService", userService);

            ContentService contentService = new ContentServiceImpl(contentRepository, contentMapper);
            servletContext.setAttribute("contentService", contentService);

            FavoritesRepository favoritesRepository = new FavoritesRepositoryImpl(connection);
            servletContext.setAttribute("favoritesRepository", favoritesRepository);

            servletContext.setAttribute("serviceMapper", new ServiceMapper(favoritesRepository, contentRepository, new TagRepository(connection)));

            servletContext.setAttribute("tagRepository", new TagRepository(connection));

        } catch (DbConnectionException | DbDriverException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
